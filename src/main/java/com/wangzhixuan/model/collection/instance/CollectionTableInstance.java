package com.wangzhixuan.model.collection.instance;

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
 * @TIME:15:40
 * @Date:2018/3/1 Description:采集表实例
 */
@TableName(value = "hzsf_collection_table_sl")
public class CollectionTableInstance implements Serializable {

	private static final long serialVersionUID = -1310095092749617488L;
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 表格名： 同 table 的表格名 生活史 吸烟史等
	 */
	@TableField(value = "name")
	private String name;
	@TableField(value = "table_type")
	private String tableType;
	/**
	 * 量表实例id
	 */
	@TableField(value = "special_sl_id")
	private Long followInstanceId;
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(value = "create_user_name")
	private String createUserName;
	/**
	 * 表格原型id
	 */
	@TableField(value = "table_id")
	private Long tableId;
	/**
	 * 预留方便统计字段
	 */
	@TableField(value = "hz_id")
	private Long hzId;

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

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public Long getFollowInstanceId() {
		return followInstanceId;
	}

	public void setFollowInstanceId(Long followInstanceId) {
		this.followInstanceId = followInstanceId;
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

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getHzId() {
		return hzId;
	}

	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}
}
