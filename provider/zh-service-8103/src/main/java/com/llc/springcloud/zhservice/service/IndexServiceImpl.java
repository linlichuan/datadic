package com.llc.springcloud.zhservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.util.HttpUtil;
import com.llc.springcloud.util.StringUtil;
import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.zhservice.dao.mapper.zh.StoryDetailPoMapper;
import com.llc.springcloud.zhservice.dao.mapper.zh.StoryPoMapper;
import com.llc.springcloud.zhservice.entity.Story;
import com.llc.springcloud.zhservice.entity.StoryDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class IndexServiceImpl implements IndexService{
	
	@Resource
	private StoryPoMapper storyPoMapper;
	@Resource
	private StoryDetailPoMapper storyDetailPoMapper;
	
	@Override
	public List<Story> getLatest(Date now) {
		List<Story> result = storyPoMapper.getLatestList(now);
		if (result.isEmpty() || result.size() < 6) {
			String jsonStr = HttpUtil.get("http://news-at.zhihu.com/api/4/news/latest");
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			saveStory(json, now, result);
		}
		return result;
	}
	
	@Override
	public StoryDetail getStoryDetail(Integer id) {
		StoryDetail storyDetail = storyDetailPoMapper.getStoryDetail(id);
		if (storyDetail == null) {
			String jsonStr = HttpUtil.get("https://news-at.zhihu.com/api/4/news/" + id);
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			storyDetail = new StoryDetail();
			storyDetail.setBody(json.getString("body"));
			storyDetail.setImageHue(json.getString("image_hue"));
			storyDetail.setTitle(json.getString("title"));
			storyDetail.setDetailId(json.getInteger("id"));
			storyDetail.setType(json.getInteger("type"));
			storyDetail.setShareUrl(json.getString("shareUrl"));
			storyDetail.setImage(json.getString("image"));
			storyDetail.setImageSource(json.getString("imageSource"));
			storyDetailPoMapper.insertSelective(storyDetail);
		}
		return storyDetail;
	}
	
	@Override
	public List<Story> getBefore(String date) {
		Date dt = TimeUtil.strToDate(date, "yyyy-MM-dd");
		return getBefore(dt);
	}
	
	@Override
	public List<Story> getBefore(Date date) {
		List<Story> result = storyPoMapper.getLatestList(date);
		String numStr = TimeUtil.dateToStr(date, "yyyy-MM-dd").replaceAll("-", "");
		if (result.isEmpty()) {
			
			String jsonStr = HttpUtil.get("http://news-at.zhihu.com/api/4/news/before/" + numStr);
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			saveStory(json, date, result);
		}
		return result;
	}
	
	public void saveStory(JSONObject json, Date dt, List<Story> result) {
		JSONArray array = json.getJSONArray("stories");
		Story dto = null;
		Integer storyId = null;
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			storyId = obj.getInteger("id");
			if (result.size() > 0) {
				Integer sid = storyId;
				if (result.stream().filter(r -> Objects.equals(r.getStoryId(), sid)).count() > 0) {
					continue;
				}
			}
			dto = new Story();
			dto.setCreateDate(dt);
			dto.setGaPrefix(obj.getString("ga_prefix"));
			dto.setHint(obj.getString("hint"));
			dto.setImageHue(obj.getString("image_hue"));
			dto.setTitle(obj.getString("title"));
			JSONArray images = obj.getJSONArray("images");
			String[] imgs = images.toArray(new String[0]);
			dto.setImages(StringUtil.join(imgs, ','));
			dto.setStoryId(storyId);
			dto.setUrl(obj.getString("url"));
			dto.setType(obj.getInteger("type"));
			result.add(dto);
			storyPoMapper.insertSelective(dto);
			getStoryDetail(storyId);
		}
	}
}
