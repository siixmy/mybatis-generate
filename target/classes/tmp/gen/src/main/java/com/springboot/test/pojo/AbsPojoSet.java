package com.springboot.test.pojo;

/**
* pojo基础信息装填
*
* @author sijie
* @date 2023/09/02 13:21
* @version 0.0.1
*/
public abstract class AbsPojoSet<T> {

    //排序字段
    private String orderBy;

    public abstract void setPk(Long pk) ;

    public abstract Long getPk();

    public String getOrderBy() {
    return orderBy;
    }

    public T setOrderBy(String orderBy) {
    this.orderBy = orderBy;
    return (T)this;
    }
    }
