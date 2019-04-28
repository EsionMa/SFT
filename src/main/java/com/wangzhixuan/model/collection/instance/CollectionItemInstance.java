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
 * @TIME:15:33
 * @Date:2018/3/1 Description:采集项实例
 */
@TableName(value = "hzsf_collection_item_sl")
public class CollectionItemInstance implements Serializable {
	private static final long serialVersionUID = 8418347234957972767L;
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 填写内容 */
	@TableField(value = "fill_content")
	private String fill_content;
	@TableField(value = "choose")
	private String choose;
	/** 同Type类的名字。懒得查表了 */
	@TableField(value = "name")
	private String name;
	/** 表格实例id */
	@TableField(value = "table_sl_id")
	private Long tableInstanceId;
	/** 所属量表实例Id */
	@TableField(value = "special_id")
	private Long followInstanceId;
	@TableField(value = "hz_id")
	private Long hzId;
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(value = "create_user_name")
	private String createUserName;
	/** 实例来源type的id */
	@TableField(value = "type")
	private Long typeId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFill_content() {
		return fill_content;
	}
	public void setFill_content(String fill_content) {
		this.fill_content = fill_content;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTableInstanceId() {
		return tableInstanceId;
	}
	public void setTableInstanceId(Long tableInstanceId) {
		this.tableInstanceId = tableInstanceId;
	}
	public Long getFollowInstanceId() {
		return followInstanceId;
	}
	public void setFollowInstanceId(Long followInstanceId) {
		this.followInstanceId = followInstanceId;
	}
	public Long getHzId() {
		return hzId;
	}
	public void setHzId(Long hzId) {
		this.hzId = hzId;
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
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

}
