package com.springboot.test.pojo;

import java.io.Serializable;
import java.util.Date;

/**
* model Student
*
* @author sijie
* @date 2023/09/02 13:21
* @version 0.0.1
*/
public class Student extends AbsPojoSet<Student> implements Serializable {
    private Integer id;
    private String name;
    private __UNKNOWN__ age;

    public Student setId(Integer id){
        this.id = id;
        return this;
    }
    public Integer getId(){
        return this.id;
    }

    public Student setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }

    public Student setAge(__UNKNOWN__ age){
        this.age = age;
        return this;
    }
    public __UNKNOWN__ getAge(){
        return this.age;
    }



    @Override
    public void setPk(Long pk){
        this.id = pk;
    }

    @Override
    public Long getPk() {
        return id;
    }
}