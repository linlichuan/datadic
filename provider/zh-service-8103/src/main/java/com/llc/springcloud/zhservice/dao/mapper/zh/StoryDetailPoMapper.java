package com.llc.springcloud.zhservice.dao.mapper.zh;

import com.llc.springcloud.zhservice.dao.mapper.all.StoryDetailMapper;
import com.llc.springcloud.zhservice.entity.StoryDetail;
import org.apache.ibatis.annotations.Param;

public interface StoryDetailPoMapper extends StoryDetailMapper {
	
	StoryDetail getStoryDetail(@Param("detailId") Integer id);
	
}
