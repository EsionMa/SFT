package com.wangzhixuan.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/23 0023.
 */
@TableName("textio")
public class Excel implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = -8240920961126000454L;
    /** 主键id */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 用户名*/
    private String name;
    /** 年龄*/
    private Long age;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
