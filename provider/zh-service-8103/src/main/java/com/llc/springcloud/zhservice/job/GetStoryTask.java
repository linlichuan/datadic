package com.llc.springcloud.zhservice.job;

import com.llc.springcloud.util.ListUtil;
import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.zhservice.dao.mapper.zh.StoryPoMapper;
import com.llc.springcloud.zhservice.entity.Story;
import com.llc.springcloud.zhservice.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class GetStoryTask {
	
	private static final Logger log = LoggerFactory.getLogger(GetStoryTask.class);
	
	@Resource
	private StoryPoMapper storyPoMapper;
	@Resource
	IndexService indexService;
	
	@Scheduled(cron = "0 0 * * * ?")
	public void execute() {
		log.info("get story task start {}", System.nanoTime());
		Date now = new Date();
		String nowStr = TimeUtil.dateToStr(now, "yyyyMMdd");
		while (true) {
			if (ListUtil.isEmpty(storyPoMapper.getLatestList(nowStr))) {
				indexService.getLatestFromRemote();
			} else {
				break;
			}
			nowStr = TimeUtil.dateToStr(TimeUtil.addDay(now, -1), "yyyyMMdd");
		}
		log.info("get story task end {}", System.nanoTime());
	}
}
