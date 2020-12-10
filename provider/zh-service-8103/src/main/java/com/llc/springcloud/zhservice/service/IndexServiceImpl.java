package com.llc.springcloud.zhservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.util.HttpUtil;
import com.llc.springcloud.util.StringUtil;
import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.zhservice.constant.ZHConstant;
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
	public List<Story> getLatest() {
		String now = TimeUtil.dateToStr(new Date(), "yyyyMMdd");
		List<Story> result = storyPoMapper.getLatestList(now);
		if (result.isEmpty() || result.size() < 10) {
			String jsonStr = HttpUtil.get("http://news-at.zhihu.com/api/4/news/latest");
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			parseStory(json, now, result);
		}
		return result;
	}
	
	@Override
	public StoryDetail getStoryDetail(Integer id) {
		StoryDetail storyDetail = storyDetailPoMapper.getStoryDetail(id);
		if (storyDetail == null) {
			String jsonStr = HttpUtil.get("https://news-at.zhihu.com/api/4/news/" + id);
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			saveStoryDetail(json, false);
		}
		return storyDetail;
	}
	
	@Override
	public List<Story> getBefore(String date) {
		List<Story> result = storyPoMapper.getLatestList(date);
		if (result.isEmpty()) {
			
			String jsonStr = HttpUtil.get("http://news-at.zhihu.com/api/4/news/before/" + date);
			if (StringUtil.isBlank(jsonStr)) {
				return result;
			}
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			parseStory(json, date, result);
		}
		return result;
	}
	
	public void parseStory(JSONObject json, String dt, List<Story> result) {
		if (json.containsKey(ZHConstant.STORIES)) {
			JSONArray stories = json.getJSONArray(ZHConstant.STORIES);
			saveStory(stories, dt, result, false);
		}
		if (json.containsKey(ZHConstant.TOP_STORIES)) {
			JSONArray topStories = json.getJSONArray(ZHConstant.TOP_STORIES);
			saveStory(topStories, dt, result, true);
		}
	}
	
	public void saveStory(JSONArray array, String dt, List<Story> result, boolean isTopStory) {
		Story dto = null;
		Integer storyId = null;
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			storyId = obj.getInteger("id");
			if (result.size() > 0) {
				Integer sid = storyId;
				if (result.stream().anyMatch(r -> Objects.equals(r.getStoryId(), sid))) {
					continue;
				}
			}
			dto = new Story();
			dto.setDate(dt);
			dto.setCreateDate(TimeUtil.strToDate(dt, "yyyyMMdd"));
			dto.setGaPrefix(obj.getString("ga_prefix"));
			dto.setHint(obj.getString("hint"));
			dto.setImageHue(obj.getString("image_hue"));
			dto.setTitle(obj.getString("title"));
			if (obj.containsKey("images")) {
				JSONArray images = obj.getJSONArray("images");
				String[] imgs = images.toArray(new String[0]);
				dto.setImages(StringUtil.join(',', imgs));
			}
			if (obj.containsKey("image")) {
				dto.setImages(StringUtil.join(',', obj.getString("image")));
			}
			dto.setStoryId(storyId);
			dto.setUrl(obj.getString("url"));
			dto.setType(obj.getInteger("type"));
			dto.setIsTopStory(isTopStory);
			result.add(dto);
			storyPoMapper.insertSelective(dto);
			if (storyDetailPoMapper.getStoryDetail(storyId) == null) {
				String jsonStr = HttpUtil.get("https://news-at.zhihu.com/api/4/news/" + storyId);
				JSONObject json = (JSONObject)JSON.parse(jsonStr);
				saveStoryDetail(json, isTopStory);
			}
		}
	}
	
	public void saveStoryDetail(JSONObject story, boolean isTopStory) {
		StoryDetail storyDetail = new StoryDetail();
		storyDetail.setBody(story.getString("body"));
		storyDetail.setImageHue(story.getString("image_hue"));
		storyDetail.setTitle(story.getString("title"));
		storyDetail.setDetailId(story.getInteger("id"));
		storyDetail.setType(story.getInteger("type"));
		storyDetail.setShareUrl(story.getString("shareUrl"));
		storyDetail.setImage(story.getString("image"));
		storyDetail.setImageSource(story.getString("imageSource"));
		storyDetail.setIsTopStory(isTopStory);
		storyDetailPoMapper.insertSelective(storyDetail);
	}
	
}
