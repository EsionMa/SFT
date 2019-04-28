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
@TableName(value = "hzsf_collection_item")
public class CollectionItem implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -4109945223251755207L;

	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "status")
	private Integer status;
	/** 名字： for example: 发病方式，入院诊断 */
	@TableField(value = "name")
	private String name;
	@TableField(value = "type")
	private String type;
	@TableField(value = "txxz")
	private Integer txxz;
	@TableField(value = "multi_fill")
	private Integer multiFill;
	/** 显示有无（1是 0否） */
	@TableField(value = "sub_option")
	private Integer subOption;
	/** 换行（1是 0否） */
	@TableField(value = "newline")
	private Integer newline;
	@TableField(value = "description")
	private String description;
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(value = "update_time")
	private Date updateTime;
	@TableField(value = "create_user_name")
	private String createUserName;
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

	public Integer getTxxz() {
		return txxz;
	}

	public void setTxxz(Integer txxz) {
		this.txxz = txxz;
	}

	public Integer getMultiFill() {
		return multiFill;
	}

	public void setMultiFill(Integer multiFill) {
		this.multiFill = multiFill;
	}

	public Integer getSubOption() {
		return subOption;
	}

	public void setSubOption(Integer subOption) {
		this.subOption = subOption;
	}

	public Integer getNewline() {
		return newline;
	}

	public void setNewline(Integer newline) {
		this.newline = newline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

}
