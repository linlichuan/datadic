package com.llc.springcloud.zhservice.dao.mapper.all;

import com.llc.springcloud.zhservice.entity.StoryDetail;

public interface StoryDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    int insert(StoryDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    int insertSelective(StoryDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    StoryDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    int updateByPrimaryKeySelective(StoryDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(StoryDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table story_detail
     *
     * @mbggenerated Thu Dec 10 18:27:32 CST 2020
     */
    int updateByPrimaryKey(StoryDetail record);
}