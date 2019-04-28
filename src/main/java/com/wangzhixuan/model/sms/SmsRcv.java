/**
 * 2017-08-09 17:37:57
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
@TableName("hzsf_sms_rcv")
public class SmsRcv implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7484411220684296300L;
	/** 主鍵 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 发送者号码 */
	@TableField("from_number")
	private String fromNumber;
	/** 短信内容 */
	private String content;
	/** 接收时间 */
	@TableField("rcv_time")
	private Date rcvTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRcvTime() {
		return rcvTime;
	}

	public void setRcvTime(Date rcvTime) {
		this.rcvTime = rcvTime;
	}

}
