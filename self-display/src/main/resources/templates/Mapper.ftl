<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package_name}.mapper.${package_name_suffix}.${table_name}DAO">

    <resultMap id="${table_name}Map" type="${table_name}">
    	<#if model_column?exists>
    		<#list model_column as model>
    			<result property="${model.changeColumnName}" column="${model.columnName}"/>
    		</#list>
    	</#if>
    </resultMap>

    <sql id="findDtoSql">
        select * from (
        select * from  ${table_name_small} temp
        ) t
    </sql>

    <select id="select" parameterType="String" resultMap="${table_name}Map">
        select * from  ${table_name_small} t where  t.id = #{id}
    </select>

    <select id="list" parameterType="map" resultMap="${table_name}Map">
        select * from  ${table_name_small} t
    </select>
	
	<insert id="insert" parameterType="user">
	
	</insert>
</mapper>