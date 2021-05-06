package com.llc.springcloud.onlinecharts.vrtest.util;

import java.io.Serializable;

/**
 * VR响应数据分页信息
 */
@SuppressWarnings("serial")
public class VrPageInfoPojo implements Serializable {
	
	private Integer cur_page; // 当前页
	private Integer next_page; // 下一页
	private Integer count_page; // 总页码
	
	
	public VrPageInfoPojo() {
		super();
	}
	
	
	private void init() {
		cur_page = cur_page == null || cur_page < 0 ? 0 : cur_page;
		next_page = next_page == null || next_page < 0 ? 0 : next_page;
		count_page = count_page == null || count_page < 0 ? 0 : count_page;
	}
	
	public boolean hasNextPage() {
		init();
		return cur_page.compareTo(count_page) < 0 || cur_page.compareTo(next_page) < next_page;
	}
	
	public Integer getCur_page() {
		return cur_page;
	}
	public void setCur_page(Integer cur_page) {
		this.cur_page = cur_page;
	}
	public Integer getNext_page() {
		return next_page;
	}
	public void setNext_page(Integer next_page) {
		this.next_page = next_page;
	}
	public Integer getCount_page() {
		return count_page;
	}
	public void setCount_page(Integer count_page) {
		this.count_page = count_page;
	}
	
	
}
