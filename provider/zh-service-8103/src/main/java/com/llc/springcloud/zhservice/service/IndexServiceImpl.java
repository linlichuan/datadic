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

@Service
public class IndexServiceImpl implements IndexService{
	
	@Resource
	private StoryPoMapper storyPoMapper;
	@Resource
	private StoryDetailPoMapper storyDetailPoMapper;
	
	@Override
	public List<Story> getLatest() {
		Date now = TimeUtil.getCurrentDate();
		List<Story> result = storyPoMapper.getLatestList(now);
		if (result.isEmpty()) {
			String jsonStr = HttpUtil.get("http://news-at.zhihu.com/api/4/news/latest");
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			JSONArray array = json.getJSONArray("stories");
			Story dto = null;
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				dto = new Story();
				dto.setCreateDate(now);
				dto.setGaPrefix(obj.getString("ga_prefix"));
				dto.setHint(obj.getString("hint"));
				dto.setImageHue(obj.getString("image_hue"));
				dto.setTitle(obj.getString("title"));
				JSONArray images = obj.getJSONArray("images");
				String[] imgs = images.toArray(new String[0]);
				dto.setImages(StringUtil.join(imgs, ','));
				dto.setStoryId(obj.getInteger("id"));
				dto.setUrl(obj.getString("url"));
				dto.setType(obj.getInteger("type"));
				result.add(dto);
				storyPoMapper.insertSelective(dto);
			}
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
}
