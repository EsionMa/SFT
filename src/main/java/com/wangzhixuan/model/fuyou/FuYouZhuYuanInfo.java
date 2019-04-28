package com.wangzhixuan.model.fuyou;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@TableName("hzsf_fy_zy_info")
public class FuYouZhuYuanInfo implements Serializable {
    @TableField("exist = false")
    private static final long serialVersionUID = -5350885253284391804L;
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("zy_id")
    private Long zyId;
    @TableField("fy_verify_time")
    private Date fyVerifyTime;//确认怀孕日期
    @TableField("fy_week")
    private Long fyWeek;//周
    @TableField("fy_day")
    private Long fyDay;//天
    private Date periodTime;//孕前最后一次月经日期
    private Long cure;//是否在本院治疗：1 是，2否
    @TableField("fy_status")
    private Long fyStatus;//状态：1 终止分娩 2 已分娩 3 未分娩
    @TableField("fy_end_time")
    private Date fyEndTime;//分娩或终止分娩时间
    private Long method;//分娩方式
    private String comment;//备注


    public Long getFyStatus() {
        return fyStatus;
    }
    public void setFyStatus(Long fyStatus) {
        this.fyStatus = fyStatus;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZyId() {
        return zyId;
    }

    public void setZyId(Long zyId) {
        this.zyId = zyId;
    }

    public Date getFyVerifyTime() {
        return fyVerifyTime;
    }

    public void setFyVerifyTime(Date fyVerifyTime) {
        this.fyVerifyTime = fyVerifyTime;
    }

    public Long getFyWeek() {
        return fyWeek;
    }

    public void setFyWeek(Long fyWeek) {
        this.fyWeek = fyWeek;
    }

    public Long getFyDay() {
        return fyDay;
    }

    public void setFyDay(Long fyDay) {
        this.fyDay = fyDay;
    }

    public Date getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(Date periodTime) {
        this.periodTime = periodTime;
    }

    public Long getCure() {
        return cure;
    }

    public void setCure(Long cure) {
        this.cure = cure;
    }

    public Date getFyEndTime() {
        return fyEndTime;
    }

    public void setFyEndTime(Date fyEndTime) {
        this.fyEndTime = fyEndTime;
    }

    public Long getMethod() {
        return method;
    }

    public void setMethod(Long method) {
        this.method = method;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
