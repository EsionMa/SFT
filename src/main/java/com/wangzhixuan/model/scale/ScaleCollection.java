package com.wangzhixuan.model.scale;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Esion
 * 
 * 
 */
@TableName(value = "hzsf_scale_collection")
public class ScaleCollection implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -8853412145556733454L;

	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "scale_id")
	private Long scaleId;
	@TableField(value = "collection_id")
	private Long collectionId;
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(value = "create_user_name")
	private String createUserName;
	@TableField(value = "update_time")
	private Date updateTime;
	@TableField(value = "update_user_name")
	private String updateUserName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScaleId() {
		return scaleId;
	}

	public void setScaleId(Long scaleId) {
		this.scaleId = scaleId;
	}

	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

}
