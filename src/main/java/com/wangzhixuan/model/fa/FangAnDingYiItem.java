package com.wangzhixuan.model.fa;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_fa_dy_item")
public class FangAnDingYiItem implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 7845784506705834171L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("fa_id")
	private Long faId;
	@TableField("zxsj_lx")
	private String zxsjLx;// 执行时间类型，1当天，2离院时间，3返院时间，4自定义
	private Date zxsj;// 如果自定义时间，自定义具体时间
	private Integer gjtzx;// 隔几天执行
	private Integer zxjg;// 执行间隔（天）
	private Long zts;// 一共执行几次
	@TableField("sf_type")
	private String sfType;// 随访类型 1电话，2短信
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	@TableField("sms_content")
	private String smsContent;// 短信内容
	@TableField("sms_send_time")
	private String smsSendTime;// 短信发送时间（小时）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFaId() {
		return faId;
	}

	public void setFaId(Long faId) {
		this.faId = faId;
	}

	public String getZxsjLx() {
		return zxsjLx;
	}

	public void setZxsjLx(String zxsjLx) {
		this.zxsjLx = zxsjLx;
	}

	public Date getZxsj() {
		return zxsj;
	}

	public void setZxsj(Date zxsj) {
		this.zxsj = zxsj;
	}

	public Integer getGjtzx() {
		return gjtzx;
	}

	public void setGjtzx(Integer gjtzx) {
		this.gjtzx = gjtzx;
	}

	public Integer getZxjg() {
		return zxjg;
	}

	public void setZxjg(Integer zxjg) {
		this.zxjg = zxjg;
	}

	public Long getZts() {
		return zts;
	}

	public void setZts(Long zts) {
		this.zts = zts;
	}

	public String getSfType() {
		return sfType;
	}

	public void setSfType(String sfType) {
		this.sfType = sfType;
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

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsSendTime() {
		return smsSendTime;
	}

	public void setSmsSendTime(String smsSendTime) {
		this.smsSendTime = smsSendTime;
	}

}
