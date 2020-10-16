package com.llc.springcloud.zhservice.dao.mapper.zh;

import com.llc.springcloud.zhservice.dao.mapper.all.StoryMapper;
import com.llc.springcloud.zhservice.entity.Story;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface StoryPoMapper extends StoryMapper {
	
	List<Story> getLatestList(@Param("date") Date date);
	
}
