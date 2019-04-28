package com.wangzhixuan.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangzhixuan.commons.utils.JsonUtils;
import com.wangzhixuan.model.SysUser;

/**
 * @description：UserVo
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
public class UserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -213352279104999622L;
	private Integer page = 1;
	private Integer rows = 15;
	private Long id;
	@NotBlank
	private String loginName;
	private String name;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt; // 密码加密盐
	private Integer sex;
	private Integer age;
	private Integer userType;
	private Integer status;
	private Date createTime;
	private String phone;
	private List<Long> roleIdList;
	private List<Long> organizationIdList;
	private Long organizationId;
	private String organizationName;
	private String roleIds;
	private String organizationIds;
	private Date createdateStart;
	private Date createdateEnd;
	private String token;
	private Date tokenTime;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<Long> getOrganizationIdList() {
		return organizationIdList;
	}

	public void setOrganizationIdList(List<Long> organizationIdList) {
		this.organizationIdList = organizationIdList;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getOrganizationIds() {
		return organizationIds;
	}

	public void setOrganizationIds(String organizationIds) {
		this.organizationIds = organizationIds;
	}

	public Date getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(Date createdateStart) {
		this.createdateStart = createdateStart;
	}

	public Date getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(Date createdateEnd) {
		this.createdateEnd = createdateEnd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(Date tokenTime) {
		this.tokenTime = tokenTime;
	}

	/**
	 * 比较vo和数据库中的用户是否同一个user，采用id比较
	 * 
	 * @param user
	 *            用户
	 * @return 是否同一个人
	 */
	public boolean equalsUser(SysUser user) {
		if (user == null) {
			return false;
		}
		Long userId = user.getId();
		if (id == null || userId == null) {
			return false;
		}
		return id.equals(userId);
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}