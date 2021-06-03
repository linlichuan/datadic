package com.llc.springcloud.bean.zhapi.entity;

import java.io.Serializable;
import java.util.Date;

public class Story implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.id
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.image_hue
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String imageHue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.url
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.title
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.hint
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String hint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.ga_prefix
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String gaPrefix;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.type
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.story_id
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private Integer storyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.create_date
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.date
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.is_top_story
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private Boolean isTopStory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column story.images
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private String images;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table story
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.id
     *
     * @return the value of story.id
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.id
     *
     * @param id the value for story.id
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.image_hue
     *
     * @return the value of story.image_hue
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getImageHue() {
        return imageHue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.image_hue
     *
     * @param imageHue the value for story.image_hue
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setImageHue(String imageHue) {
        this.imageHue = imageHue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.url
     *
     * @return the value of story.url
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.url
     *
     * @param url the value for story.url
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.title
     *
     * @return the value of story.title
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.title
     *
     * @param title the value for story.title
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.hint
     *
     * @return the value of story.hint
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getHint() {
        return hint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.hint
     *
     * @param hint the value for story.hint
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.ga_prefix
     *
     * @return the value of story.ga_prefix
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getGaPrefix() {
        return gaPrefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.ga_prefix
     *
     * @param gaPrefix the value for story.ga_prefix
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.type
     *
     * @return the value of story.type
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.type
     *
     * @param type the value for story.type
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.story_id
     *
     * @return the value of story.story_id
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public Integer getStoryId() {
        return storyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.story_id
     *
     * @param storyId the value for story.story_id
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.create_date
     *
     * @return the value of story.create_date
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.create_date
     *
     * @param createDate the value for story.create_date
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.date
     *
     * @return the value of story.date
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.date
     *
     * @param date the value for story.date
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.is_top_story
     *
     * @return the value of story.is_top_story
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public Boolean getIsTopStory() {
        return isTopStory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.is_top_story
     *
     * @param isTopStory the value for story.is_top_story
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setIsTopStory(Boolean isTopStory) {
        this.isTopStory = isTopStory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column story.images
     *
     * @return the value of story.images
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public String getImages() {
        return images;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column story.images
     *
     * @param images the value for story.images
     *
     * @mbggenerated Thu Dec 10 18:26:56 CST 2020
     */
    public void setImages(String images) {
        this.images = images;
    }
}