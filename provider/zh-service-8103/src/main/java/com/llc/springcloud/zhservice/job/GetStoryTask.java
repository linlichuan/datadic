package com.llc.springcloud.zhservice.job;

import com.llc.springcloud.zhservice.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class GetStoryTask {
	
	private static final Logger log = LoggerFactory.getLogger(GetStoryTask.class);
	
	@Resource
	IndexService indexService;
	
	@Scheduled(cron = "0 0 * * * ?")
	public void execute() {
		log.info("get story task start {}", System.nanoTime());
		indexService.getLatestFromRemote(null);
		log.info("get story task end {}", System.nanoTime());
	}
}
