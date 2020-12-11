package com.llc.springcloud.zhservice.dao.mapper.zh;

import com.llc.springcloud.zhservice.dao.mapper.all.TopRecordMapper;
import com.llc.springcloud.zhservice.entity.TopRecord;
import org.apache.ibatis.annotations.Param;

public interface TopRecordPoMapper extends TopRecordMapper {
	
	TopRecord getByDate(@Param("date") String date);
	
}
