package com.wangzhixuan.model.wenjuan;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_wj_tmxx")
public class TimuXuanXiang implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 5462848329115024468L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String xxnr;// 选项内容
	private Float fz;// 分值
	private String sfkt;// 是否可填 0否 1是
	private String stnr;// 所填内容
	@TableField("tm_id")
	private Long tmId;// 题目ID
	private Integer seq;// 排序
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	@TableField(exist = false)
	private String sfxz;// 是否选中 Y/N

	public String getSfxz() {
		return sfxz;
	}

	public void setSfxz(String sfxz) {
		this.sfxz = sfxz;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getTmId() {
		return tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}

	public Float getFz() {
		return fz;
	}

	public void setFz(Float fz) {
		this.fz = fz;
	}

	public String getSfkt() {
		return sfkt;
	}

	public void setSfkt(String sfkt) {
		this.sfkt = sfkt;
	}

	public String getStnr() {
		return stnr;
	}

	public void setStnr(String stnr) {
		this.stnr = stnr;
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

}
