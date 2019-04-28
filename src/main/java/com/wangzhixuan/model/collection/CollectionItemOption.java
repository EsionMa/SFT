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
 * @TIME:15:23
 * @Date:2018/3/1 Description:
 */
@TableName(value = "hzsf_collection_item_option")
public class CollectionItemOption implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 6331213421135245796L;

	@TableId(type = IdType.AUTO)
	private Long id;
	/** 采集项 */
	@TableField(value = "item_id")
	private Long itemId;
	/** 类型： 1 选择 2填空 */
	@TableField(value = "type")
	private Integer type;
	/** 内容 如果是填空，那么就是前面那一截 */
	@TableField(value = "content")
	private String content;
	/** 是否可填 */
	@TableField(value = "is_fill")
	private Integer isFill;
	/** 单位 */
	@TableField(value = "unit")
	private String unit;
	@TableField(value = "score")
	private Float score;
	/** 排序 */
	@TableField(value = "seq")
	private Integer seq;
	/** 状态 0 启用 9禁用 */
	@TableField(value = "status")
	private Integer status;
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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsFill() {
		return isFill;
	}

	public void setIsFill(Integer isFill) {
		this.isFill = isFill;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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
