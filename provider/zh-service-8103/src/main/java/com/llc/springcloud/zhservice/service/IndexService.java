package com.llc.springcloud.zhservice.service;

import com.llc.springcloud.zhservice.dto.StoryDto;
import com.llc.springcloud.zhservice.entity.Story;
import com.llc.springcloud.zhservice.entity.StoryDetail;

import java.util.Date;
import java.util.List;

public interface IndexService {
	List<Story> getLatest();
	StoryDetail getStoryDetail(Integer id);
	List<Story> getBefore(String date);
}
