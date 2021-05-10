package com.llc.springcloud.zhservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.util.HttpUtil;
import com.llc.springcloud.util.ListUtil;
import com.llc.springcloud.util.StringUtil;
import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.zhservice.constant.ZHConstant;
import com.llc.springcloud.zhservice.dao.mapper.zh.StoryDetailPoMapper;
import com.llc.springcloud.zhservice.dao.mapper.zh.StoryPoMapper;
import com.llc.springcloud.zhservice.dao.mapper.zh.TopRecordPoMapper;
import com.llc.springcloud.zhservice.entity.Story;
import com.llc.springcloud.zhservice.entity.StoryDetail;
import com.llc.springcloud.zhservice.entity.TopRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IndexService {
	
	@Resource
	private StoryPoMapper storyPoMapper;
	@Resource
	private StoryDetailPoMapper storyDetailPoMapper;
	@Resource
	private TopRecordPoMapper topRecordPoMapper;
	
	public List<Story> getLatest() {
		String now = TimeUtil.dateToStr(new Date(), "yyyyMMdd");
		List<Story> result = storyPoMapper.getLatestList(now);
		if (result.isEmpty()) {
			result = getLatestFromRemote();
		}
		return result;
	}
	
	public StoryDetail getStoryDetail(Integer id) {
		StoryDetail storyDetail = storyDetailPoMapper.getStoryDetail(id);
		if (storyDetail == null) {
			String jsonStr = HttpUtil.get("https://news-at.zhihu.com/api/4/news/" + id);
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			storyDetail = saveStoryDetail(json, false);
		}
		return storyDetail;
	}
	
	public List<Story> getBefore(String date) {
		List<Story> result = storyPoMapper.getLatestList(date);
		if (result.isEmpty()) {
			String tomorrow = TimeUtil.dateToStr(TimeUtil.addDay(TimeUtil.strToDate(date, "yyyyMMdd"), 1), "yyyyMMdd");
			String jsonStr = HttpUtil.get("http://news-at.zhihu.com/api/4/news/before/" + tomorrow);
			if (StringUtil.isBlank(jsonStr)) {
				return result;
			}
			JSONObject json = (JSONObject)JSON.parse(jsonStr);
			result = parseStoryAndSave(json);
		}
		return result;
	}
	
	public List<Story> getLatestFromRemote() {
		String latest = HttpUtil.get("http://news-at.zhihu.com/api/4/news/latest");
		JSONObject json = (JSONObject)JSON.parse(latest);
		List<Story> storyList = parseStoryAndSave(json);
		Set<Integer> topStoryIds = storyList.stream().filter(Story::getIsTopStory).map(Story::getStoryId).collect(Collectors.toSet());
		if (!topStoryIds.isEmpty() && json.containsKey("date")) {
			String topDate = json.getString("date");
			TopRecord topRecord = topRecordPoMapper.getByDate(topDate);
			String[] storyIds = topRecord.getStoryIds().split(",");
			for (String storyId : storyIds) {
				topStoryIds.add(Integer.parseInt(storyId));
			}
			topRecord.setStoryIds(StringUtil.join(',', topStoryIds));
			topRecordPoMapper.updateByPrimaryKeySelective(topRecord);
		}
		return storyList;
	}
	
	private List<Story> parseStoryAndSave(JSONObject json) {
		List<Story> result = new ArrayList<>();
		String dt = json.getString("date");
		if (json.containsKey(ZHConstant.STORIES)) {
			JSONArray stories = json.getJSONArray(ZHConstant.STORIES);
			result.addAll(saveStory(stories, dt, false));
		}
		if (json.containsKey(ZHConstant.TOP_STORIES)) {
			JSONArray topStories = json.getJSONArray(ZHConstant.TOP_STORIES);
			result.addAll(saveStory(topStories, dt, true));
		}
		return result;
	}
	
	private List<Story> saveStory(JSONArray array, String dt, boolean isTopStory) {
		Story dto = null;
		Integer storyId = null;
		List<Story>	result = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			storyId = obj.getInteger("id");
			if (storyPoMapper.getByStoryId(storyId) != null) {
				continue;
			}
			dto = new Story();
			dto.setStoryId(storyId);
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
		return result;
	}
	
	private StoryDetail saveStoryDetail(JSONObject story, boolean isTopStory) {
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
		return storyDetail;
	}
	
	public void taskDetail() {
		getLatestFromRemote();
		Date now = new Date();
		String nowStr;
		for (int i = 1; ; i--) {
			nowStr = TimeUtil.dateToStr(TimeUtil.addDay(now, i), "yyyyMMdd");
			if (ListUtil.isEmpty(storyPoMapper.getLatestList(nowStr))) {
				getBefore(nowStr);
			} else {
				break;
			}
			if (i < -5) {
				break;
			}
		}
	}
	
}
