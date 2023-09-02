<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!--
I${className}Dao mybatis

@author ${ftl_author}
@date ${ftl_now}
@version ${ftl_version}
-->
<mapper namespace="${ftl_dao_package}.I${className}Dao" >
    <resultMap id="${className}Map" type="${ftl_model_package}.${className}">
        <#list classFields as v>
        <result property="${v.field}" column="${v.columnName}"/>
            <#if v.isPrimaryKey=true>
                <#assign tableKeyName=v.columnName>
                <#assign beanKeyName=v.field>
                <#assign beanKeyType=v.type>
            </#if>
        </#list>
    </resultMap>

    <insert id="insert" parameterType="${ftl_model_package}.${className}">
        <#if ftl_dbType="MYSQL">
            <selectKey keyProperty="${beanKeyName}" resultType="${beanKeyType}" order="AFTER">
                SELECT LAST_INSERT_ID() AS ${beanKeyName}
            </selectKey>
        </#if>
        INSERT INTO ${tableName} (
        <trim suffixOverrides=",">
            <#list classFields as v>
                <#if v.isNullable=true>
                <if test="${v.field} != null">${v.columnName},</if>
                <#else>
                ${v.columnName},
                </#if>
            </#list>
        </trim>
        )VALUES(
        <trim suffixOverrides=",">
            <#list classFields as v>
                <#if v.isNullable=true>
                    <if test="${v.field} != null"> ${"#{"+v.field+"}"},</if>
                <#else>
                    ${"#{"+v.field+"}"},
                </#if>
            </#list>
        </trim>
        )
    </insert>

    <update id="updateByPk" parameterType="${ftl_model_package}.${className}">
        update ${tableName}
        <trim prefix="SET" suffixOverrides=",">
        <#list classFields as v>
            <#if beanKeyName != v.field>
                <if test="${v.field} != null">${v.columnName} = ${"#{"+v.field+"}"},</if>
            </#if>
        </#list>
        </trim>
        where ${tableKeyName} = ${"#{"+beanKeyName+"}"}
    </update>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
        delete from ${tableName} where ${tableKeyName} = ${"#"}{key}
    </delete>

    <select id="getOneByPrimaryKey" parameterType="java.lang.Long" resultMap="${className}Map">
        select * from ${tableName} where ${tableKeyName} = ${"#"}{key}
    </select>

    <select id="queryListByKeys" resultMap="${className}Map">
        <if test="keys!=null and keys.size>0">
            SELECT * FROM ${tableName} WHERE ${tableKeyName} IN
            <foreach item="id" index="index" collection="keys" open="(" separator="," close=")">
                ${"#"}{id}
            </foreach>
        </if>
    </select>

    <delete id="deleteByKeys">
        <if test="keys!=null and keys.size>0">
            DELETE FROM ${tableName} WHERE ${tableKeyName} IN
            <foreach item="id" index="index" collection="keys" open="(" separator="," close=")">
                ${"#"}{id}
            </foreach>
        </if>
    </delete>

    <insert id="insertBatch">
        <if test="list!=null and list.size>0">
            INSERT INTO ${tableName}
            <trim suffixOverrides="," prefix="(" suffix=") VALUES">
            <#list classFields as v>
                ${v.columnName},
            </#list>
            </trim>
        <foreach collection="list" item="v" index="index" separator=",">
            <trim suffixOverrides="," prefix="(" suffix=")">
                <#list classFields as v>
                ${"#"}{v.${v.field}},
                </#list>
            </trim>
        </foreach>
        </if>
    </insert>

    <select id="getAllItemsByQueryObject" resultMap="${className}Map">
        select * from ${tableName}
        <trim prefix="where" prefixOverrides="AND|OR">
        <#list classFields as v>
            <if test="qo.${v.field} != null">AND ${v.columnName} = ${"#"}{qo.${v.field}}</if>
        </#list>
        </trim>
        <if test="qo.orderBy != null">order by ${"$"}{qo.orderBy}</if>
    </select>
</mapper>