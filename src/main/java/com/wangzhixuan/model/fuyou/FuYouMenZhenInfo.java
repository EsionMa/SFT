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
@TableName("hzsf_fy_mz_info")
public class FuYouMenZhenInfo implements Serializable{
    @TableField("exist = false")
    private static final long serialVersionUID = -6856503957468958791L;
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("mz_id")
    private Long mzId;//门诊ID
    @TableField("fy_yun")
    private Long fyYun;//孕
    @TableField("fy_chan")
    private Long fyChan;//产
    @TableField("fy_site")
    private String fySite;//胎位
    @TableField("fy_live")
    private Long fyLive;//胎儿是否存活 1 是，2否
    @TableField("fy_period")
    private Long fyPeriod;//孕周
    @TableField("fy_days")
    private Long fyDays;//孕天
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMzId() {
        return mzId;
    }

    public void setMzId(Long mzId) {
        this.mzId = mzId;
    }

    public Long getFyYun() {
        return fyYun;
    }

    public void setFyYun(Long fyYun) {
        this.fyYun = fyYun;
    }

    public Long getFyChan() {
        return fyChan;
    }

    public void setFyChan(Long fyChan) {
        this.fyChan = fyChan;
    }

    public String getFySite() {
        return fySite;
    }

    public void setFySite(String fySite) {
        this.fySite = fySite;
    }

    public Long getFyLive() {
        return fyLive;
    }

    public void setFyLive(Long fyLive) {
        this.fyLive = fyLive;
    }

    public Long getFyPeriod() {
        return fyPeriod;
    }

    public void setFyPeriod(Long fyPeriod) {
        this.fyPeriod = fyPeriod;
    }

    public Long getFyDays() {
        return fyDays;
    }

    public void setFyDays(Long fyDays) {
        this.fyDays = fyDays;
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

    public Long getFyStatus() {
        return fyStatus;
    }

    public void setFyStatus(Long fyStatus) {
        this.fyStatus = fyStatus;
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
