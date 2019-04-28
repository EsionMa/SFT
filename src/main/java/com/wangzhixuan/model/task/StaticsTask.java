package com.wangzhixuan.model.task;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2017/11/7.
 *
 * @author: Leslie
 * @TIME:11:49
 * @Date:2017/11/7 Description:
 */
@TableName(value = "hzsf_static_task")
public class StaticsTask implements Serializable{

    private static final long serialVersionUID = -907599882224911583L;
    @TableId(type = IdType.AUTO)
    private Long Id;
    //跑批类型 1.出院/门诊统计 2.工作统计 。。。。其他待添加
    @TableField(value = "type")
    private Integer type;
    //0.计划执行1.正在执行2.执行成功3.执行失败
    @TableField(value = "status")
    private Integer status;
    //执行开始时间
    @TableField(value = "exeStaTime")
    private Date exeStaTime;
    //执行结束时间
    @TableField(value = "exeEndTime")
    private Date exeEndTime;
    //筛选开始时间
    @TableField(value = "staTime")
    private Date staTime;
    //筛选结束时间
    @TableField(value = "endTime")
    private Date endTime;
    //任务创建时间
    @TableField(value = "create_time")
    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExeStaTime() {
        return exeStaTime;
    }

    public void setExeStaTime(Date exeStaTime) {
        this.exeStaTime = exeStaTime;
    }

    public Date getExeEndTime() {
        return exeEndTime;
    }

    public void setExeEndTime(Date exeEndTime) {
        this.exeEndTime = exeEndTime;
    }

    public Date getStaTime() {
        return staTime;
    }

    public void setStaTime(Date staTime) {
        this.staTime = staTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
