package com.wangzhixuan.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wangzhixuan.commons.utils.JsonUtils;

/**
 *
 * 用户
 *
 */
@TableName("sys_user")
public class SysUser implements Serializable {


	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -2652173023204470408L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 登陆名 */
	@TableField(value = "login_name")
	private String loginName;

	/** 用户名 */
		private String name;

	/** 密码 */
	private String password;

	/** 密码加密盐 */
	private String salt;

	/** 性别 */
	private Integer sex;

	/** 年龄 */
	private Integer age;

	/** 手机号 */
	private String phone;
	/** 用户类别 */
	@TableField(value = "user_type")
	private Integer userType;// 0.管理员 2.医生 3.护士1.其他

	/** 医生，护士号 */
	@TableField(value = "out_id")
	private String outId;

	/** 用户状态 */
	private Integer status;

	/** 创建时间 */
	@TableField(value = "create_time")
	private Date createTime;

	/** 一个string类型的部门id */
	@TableField(exist = false)
	private String outOrgId;
	/** 一个String类型接收数据的type */
	@TableField(exist = false)
	private String outUserType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
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

	public String getOutOrgId() {
		return outOrgId;
	}

	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}

	public String getOutUserType() {
		return outUserType;
	}

	public void setOutUserType(String outUserType) {
		this.outUserType = outUserType;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
