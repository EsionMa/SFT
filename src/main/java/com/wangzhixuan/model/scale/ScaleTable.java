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
@TableName(value = "hzsf_scale_table")
public class ScaleTable implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 3741020068977053417L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 状态 1启用 9禁用 */
	@TableField(value = "status")
	private Integer status;
	@TableField(value = "name")
	private String name;
	@TableField(value = "type")
	private String type;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
