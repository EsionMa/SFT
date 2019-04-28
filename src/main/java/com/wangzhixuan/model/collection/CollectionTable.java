package com.wangzhixuan.model.collection;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:15:10
 * @Date:2018/3/1 Description:
 */
@TableName(value = "hzsf_collection_table")
public class CollectionTable implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -1658251994978399617L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 表格名 for example:生存状态，后遗症，饮酒，吸烟 */
	@TableField(value = "name")
	private String name;
	/** 表格类型：for example:生存状态等：是属于个人信息，本次随访因脑卒中住院：应属于疾病相关 */
	@TableField(value = "type")
	private String type;
	@TableField(value = "status")
	private Integer status;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
