/**
 * 2018-03-23 14:41:29
 */
package com.wangzhixuan.model.collection;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author Esion
 *
 */
@TableName(value = "hzsf_collection_table_item")
public class CollectionTableItem implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -4140418143920993354L;

	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "table_id")
	private Long tableId;
	@TableField(value = "item_id")
	private Long itemId;
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

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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
