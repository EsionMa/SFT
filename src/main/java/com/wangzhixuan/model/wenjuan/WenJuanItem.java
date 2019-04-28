package com.wangzhixuan.model.wenjuan;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("hzsf_wj_item")
public class WenJuanItem implements Serializable{
	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 6920972020223818694L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("wj_id")
	private Long wjId;
	@TableField("tm_id")
	private Long tmId;
	private Integer seq;
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	
	public WenJuanItem(){
		
	}
	public WenJuanItem(Long wjId,Long tmId){
		this.wjId=wjId;
		this.tmId=tmId;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWjId() {
		return wjId;
	}
	public void setWjId(Long wjId) {
		this.wjId = wjId;
	}
	public Long getTmId() {
		return tmId;
	}
	public void setTmId(Long tmId) {
		this.tmId = tmId;
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
	
	
}
