<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>
	<context id="context1">
		<plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
		<jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="${jdbcConnection.connectionURL}" userId="${jdbcConnection.userId}" password="${jdbcConnection.password}" />
		<javaModelGenerator targetPackage="${javaModelGenerator.targetPackage}" targetProject="${javaModelGenerator.targetProject}" />
		<sqlMapGenerator targetPackage="${sqlMapGenerator.targetPackage}" targetProject="${sqlMapGenerator.targetProject}" />
		<javaClientGenerator targetPackage="${javaClientGenerator.targetPackage}" targetProject="${javaClientGenerator.targetProject}" type="${javaClientGenerator.type}" />
		
 		<table schema="${table.schema}"
 			tableName="${table.tableName}" domainObjectName="${table.domainObjectName}"
 			enableCountByExample="false" enableDeleteByExample="false"
 			enableSelectByExample="false" enableUpdateByExample="false">
 			<property name="useActualColumnNames" value="false" />
 			<generatedKey column="id" sqlStatement="MySql" identity="true" />
 		</table>

	</context>
</generatorConfiguration>
