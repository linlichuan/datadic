package com.llc.springcloud.codegen.tpl.tpldto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GenConfigDto implements Serializable {
	
	public GenConfigDto() {
		super();
		this.jdbcConnection = new jdbcConnection();
		this.javaModelGenerator = new javaModelGenerator();
		this.sqlMapGenerator = new sqlMapGenerator();
		this.javaClientGenerator = new javaClientGenerator();
		this.table = new table();
	}
	
	jdbcConnection jdbcConnection;
	javaModelGenerator javaModelGenerator;
	sqlMapGenerator sqlMapGenerator;
	javaClientGenerator javaClientGenerator;
	table table;
	
	public static class jdbcConnection {
		String connectionURL = "";
		String userId = "";
		String password = "";
		
		public jdbcConnection() {
			super();
		}
		
		public void setConnectionURL(String connectionURL) {
			this.connectionURL = connectionURL;
		}
		
		public String getConnectionURL() {
			return this.connectionURL;
		}
		
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
		public String getUserId() {
			return this.userId;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getPassword() {
			return this.password;
		}
	}
	
	public static class javaModelGenerator {
		String targetPackage = "";
		String targetProject = "";
		
		public javaModelGenerator() {
			super();
		}
		
		public void setTargetPackage(String targetPackage) {
			this.targetPackage = targetPackage;
		}
		
		public String getTargetPackage() {
			return this.targetPackage;
		}
		
		public void setTargetProject(String targetProject) {
			this.targetProject = targetProject;
		}
		
		public String getTargetProject() {
			return this.targetProject;
		}
	}
	
	public static class sqlMapGenerator {
		String targetPackage = "";
		String targetProject = "";
		
		public sqlMapGenerator() {
			super();
		}
		
		public void setTargetPackage(String targetPackage) {
			this.targetPackage = targetPackage;
		}
		
		public String getTargetPackage() {
			return this.targetPackage;
		}
		
		public void setTargetProject(String targetProject) {
			this.targetProject = targetProject;
		}
		
		public String getTargetProject() {
			return this.targetProject;
		}
	}
	
	public static class javaClientGenerator {
		String targetPackage = "";
		String targetProject = "";
		String type = "";
		
		public javaClientGenerator() {
			super();
		}
		
		public void setTargetPackage(String targetPackage) {
			this.targetPackage = targetPackage;
		}
		
		public String getTargetPackage() {
			return this.targetPackage;
		}
		
		public void setTargetProject(String targetProject) {
			this.targetProject = targetProject;
		}
		
		public String getTargetProject() {
			return this.targetProject;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public String getType() {
			return this.type;
		}
	}
	
	public static class table {
		String schema = "";
		String tableName = "";
		String domainObjectName = "";
		
		public table() {
			super();
		}
		
		public void setSchema(String schema) {
			this.schema = schema;
		}
		
		public String getSchema() {
			return this.schema;
		}
		
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		
		public String getTableName() {
			return this.tableName;
		}
		
		public void setDomainObjectName(String domainObjectName) {
			this.domainObjectName = domainObjectName;
		}
		
		public String getDomainObjectName() {
			return this.domainObjectName;
		}
	}
	
	public void setJdbcConnection(GenConfigDto.jdbcConnection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}
	
	public GenConfigDto.jdbcConnection getJdbcConnection() {
		return this.jdbcConnection;
	}
	
	public void setJavaModelGenerator(GenConfigDto.javaModelGenerator javaModelGenerator) {
		this.javaModelGenerator = javaModelGenerator;
	}
	
	public GenConfigDto.javaModelGenerator getJavaModelGenerator() {
		return this.javaModelGenerator;
	}
	
	public void setSqlMapGenerator(GenConfigDto.sqlMapGenerator sqlMapGenerator) {
		this.sqlMapGenerator = sqlMapGenerator;
	}
	
	public GenConfigDto.sqlMapGenerator getSqlMapGenerator() {
		return this.sqlMapGenerator;
	}
	
	public void setJavaClientGenerator(GenConfigDto.javaClientGenerator javaClientGenerator) {
		this.javaClientGenerator = javaClientGenerator;
	}
	
	public GenConfigDto.javaClientGenerator getJavaClientGenerator() {
		return this.javaClientGenerator;
	}
	
	public void setTable(GenConfigDto.table table) {
		this.table = table;
	}
	
	public GenConfigDto.table getTable() {
		return this.table;
	}
}
