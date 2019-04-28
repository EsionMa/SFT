package com.wangzhixuan.model.sms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/12 0012.
 */
@TableName("hzsf_sms_template")
public class SmsTemplate implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 9152725554896335438L;
	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 短信标题 */
	private String title;
	/** 短信内容 */
	private String content;
	/** 短信分类 */
	private String type;
	/** 创建时间 */
	@TableField("create_time")
	private Date createTime;
	/** 创建者id */
	@TableField("create_user_id")
	private Long createUserId;
	/** 更新时间 */
	@TableField("update_time")
	private Date updateTime;
	/** 更新者id */
	@TableField("update_user_id")
	private Long updateUserId;

	@Override
	public String toString() {
		return "TemplateMessage{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\''
				+ ", type='" + type + '\'' + ", createTime=" + createTime + ", createUserId=" + createUserId
				+ ", updateTime=" + updateTime + ", updateUserId=" + updateUserId + '}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

}
