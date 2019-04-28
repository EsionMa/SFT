package com.wangzhixuan.model.huanzhe;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("hzsf_hz_tag")
public class HuanZheTag implements Serializable {
	/**
		 * 
		 */
	@TableField(exist = false)
	private static final long serialVersionUID = -1851029321449002946L;
	@TableId(type=IdType.AUTO)
	private Long id;
	@TableField("hz_id")
	private Long hzId;
	@TableField("tag_code")
	private String tagCode;
	@TableField("tag_name")
	private String tagName;
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHzId() {
		return hzId;
	}
	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}
	public String getTagCode() {
		return tagCode;
	}
	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	
}
