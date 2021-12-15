package com.llc.springcloud.codegen.tpl.tpldto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@SuppressWarnings("serial")
public class GenConfigDto implements Serializable {
	
	public GenConfigDto() {
		super();
		this.jdbcConnection = new JdbcConnection();
		this.javaModelGenerator = new JavaModelGenerator();
		this.sqlMapGenerator = new SqlMapGenerator();
		this.javaClientGenerator = new JavaClientGenerator();
		this.tableList = new ArrayList<>();
	}
	
	private JdbcConnection jdbcConnection;
	private JavaModelGenerator javaModelGenerator;
	private SqlMapGenerator sqlMapGenerator;
	private JavaClientGenerator javaClientGenerator;
	private List<Table> tableList;
	
	@Data
	@AllArgsConstructor
	public static class JdbcConnection {
		private String connectionURL;
		private String userId;
		private String password;
		
		public JdbcConnection() {
			super();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class JavaModelGenerator {
		private String targetPackage = "";
		private String targetProject = "";
		
		public JavaModelGenerator() {
			super();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class SqlMapGenerator {
		String targetPackage = "";
		String targetProject = "";
		
		public SqlMapGenerator() {
			super();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class JavaClientGenerator {
		String targetPackage = "";
		String targetProject = "";
		String type = "";
		
		public JavaClientGenerator() {
			super();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Table {
		String schema = "";
		String tableName = "";
		String domainObjectName = "";
		
		public Table() {
			super();
		}
	}
	
}
