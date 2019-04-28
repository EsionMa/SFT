/**
 * 2017-08-08 11:59:51
 */
package com.wangzhixuan.model.sms;

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
@TableName("hzsf_sms_send")
public class SmsSend implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -5241499973963232629L;
	/** 主鍵 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 接收者号码 */
	@TableField("to_number")
	private String toNumber;
	/** 接收者 */
	@TableField("rcver_name")
	private String rcverName;
	/** 短信内容 */
	private String content;
	/** 状态 （1.成功 0.失败） */
	private Integer status;
	/** 发送时间 */
	@TableField("send_time")
	private Date sendTime;
	/** 操作员姓名 */
	@TableField("user_name")
	private String userName;
	/** 操作员id */
	@TableField("user_id")
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	public String getRcverName() {
		return rcverName;
	}

	public void setRcverName(String rcverName) {
		this.rcverName = rcverName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
