package com.wangzhixuan.model.wenjuan;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_wj_tm")
public class TiMu implements Serializable {
    /**
	 * 
	 */
	 @TableField(exist = false)
	private static final long serialVersionUID = -7797696204497910436L;
	@TableId(type = IdType.AUTO)
    private Long id;
    private String bt;// 标题
    private String tmfl;// 题目分类
    private String sfbt;// 是否必填（0否，1是）
    private String txxz;// 题型选择（1单选，2多选，3填空）
    private String dtms;// 答题描述
    private String dtxx;//答题选项
    private String sy;//私有
    private Float score;//
    private String status;//1正常 9 删除
    @TableField("ref_id")
    private Long refId;//引用ID
    @TableField("create_time")
    private Date createTime;// 创建时间
    @TableField("update_time")
    private Date updateTime;// 更新时间
    @TableField("create_user_id")
    private Long createUserId;// 创建用户Id
    @TableField("update_user_id")
    private Long updateUserId;// 更新用户Id

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getSy() {
        return sy;
    }

    public void setSy(String sy) {
        this.sy = sy;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getTmfl() {
        return tmfl;
    }

    public void setTmfl(String tmfl) {
        this.tmfl = tmfl;
    }

    public String getSfbt() {
        return sfbt;
    }

    public void setSfbt(String sfbt) {
        this.sfbt = sfbt;
    }

    public String getTxxz() {
        return txxz;
    }

    public void setTxxz(String txxz) {
        this.txxz = txxz;
    }

    public String getDtms() {
        return dtms;
    }

    public void setDtms(String dtms) {
        this.dtms = dtms;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getDtxx() {
        return dtxx;
    }

    public void setDtxx(String dtxx) {
        this.dtxx = dtxx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
