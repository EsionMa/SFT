package com.wangzhixuan.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2017/12/5.
 *
 * @author: Leslie
 * @TIME:11:49
 * @Date:2017/12/5 Description:限制ip 时间的SysAuthority 系统权限类
 */
@TableName("sys_authority")
public class SysAuthority implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 3706643446635589949L;
	/** id,主键自增 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/** ip */
	@NotBlank(message = "不为空")
	@TableField(value = "ip_address")
	private String ipAddress;
	@TableField(value = "ip_password")
	private String ipPassword;
	/** 过期时间 */
	private Date time;
	@TableField(value = "time_password")
	private String timePassword;
	/** 盐 */
	private String salt;
	private String remark;
	@TableField(value = "user_name")
	private String userName;
	@TableField(value = "create_user_id")
	private Long createUserId;
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(value = "update_user_id")
	private Long updateUserId;
	@TableField(value = "update_time")
	private Date updateTime;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            要设置的 ipAddress
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return ipPassword
	 */
	public String getIpPassword() {
		return ipPassword;
	}

	/**
	 * @param ipPassword
	 *            要设置的 ipPassword
	 */
	public void setIpPassword(String ipPassword) {
		this.ipPassword = ipPassword;
	}

	/**
	 * @return time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            要设置的 time
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return timePassword
	 */
	public String getTimePassword() {
		return timePassword;
	}

	/**
	 * @param timePassword
	 *            要设置的 timePassword
	 */
	public void setTimePassword(String timePassword) {
		this.timePassword = timePassword;
	}

	/**
	 * @return salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            要设置的 salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            要设置的 createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return updateUserId
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId
	 *            要设置的 updateUserId
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            要设置的 updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
