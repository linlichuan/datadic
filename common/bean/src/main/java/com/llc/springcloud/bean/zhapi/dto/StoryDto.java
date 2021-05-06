package com.llc.springcloud.bean.zhapi.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class StoryDto implements Serializable {
	private Integer id;
	private String imageHue;
	private String url;
	private String hint;
	private String gaPrefix;
	private Integer type;
	private Integer storyId;
	private Date createDate;
	private String images;
	private String title;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setImageHue(String imageHue) {
		this.imageHue = imageHue;
	}
	
	public String getImageHue() {
		return this.imageHue;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String getHint() {
		return this.hint;
	}
	
	public void setGaPrefix(String gaPrefix) {
		this.gaPrefix = gaPrefix;
	}
	
	public String getGaPrefix() {
		return this.gaPrefix;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}
	
	public Integer getStoryId() {
		return this.storyId;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setImages(String images) {
		this.images = images;
	}
	
	public String getImages() {
		return this.images;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
}
