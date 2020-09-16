package com.llc.springcloud.apiservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("表连接信息")
public class TableConnectMsgDto {

	@ApiModelProperty("连接数据库的url")
	private String url;
	@ApiModelProperty("用户名")
	private String userName;
	@ApiModelProperty("密码")
	private String password;
	@ApiModelProperty("使用的数据库")
	private String database;

	public TableConnectMsgDto() {
		super();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDatabase() {
		return this.database;
	}
}
