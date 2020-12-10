package com.llc.springcloud.zhservice.constant;

import java.util.Objects;

public enum StoryTypeEnum {
	
	STORY(1, "普通文章"),
	TOP_STORY(2, "热门文章");
	
	private int code;
	private String desc;
	
	StoryTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isTopStory(Integer type) {
		if (type == null) {
			return false;
		}
		return Objects.equals(type, TOP_STORY.code);
	}
	
	public static boolean isStory(Integer type) {
		if (type == null) {
			return false;
		}
		return Objects.equals(type, STORY.code);
	}
}
