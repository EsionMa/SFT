/**
 *
 */
package com.wangzhixuan.commons.shiro;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysRole;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
public class ShiroUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8899328153788724832L;
	private Long id;
	private final String loginName;
	private String name;
	private Set<String> urlSet;
	private Set<String> roles;
	private SysRole role;
	private List<SysOrganization> depts;
	private Boolean grDataAuth;
	private Boolean bmDataAuth;
	private Boolean qyDataAuth;

	public ShiroUser(Long id, String loginName, String name, SysRole role) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.role = role;
	}

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

	public Set<String> getUrlSet() {
		return urlSet;
	}

	public void setUrlSet(Set<String> urlSet) {
		this.urlSet = urlSet;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public List<SysOrganization> getDepts() {
		return depts;
	}

	public void setDepts(List<SysOrganization> depts) {
		this.depts = depts;
	}

	public Boolean getGrDataAuth() {
		return grDataAuth;
	}

	public void setGrDataAuth(Boolean grDataAuth) {
		this.grDataAuth = grDataAuth;
	}

	public Boolean getBmDataAuth() {
		return bmDataAuth;
	}

	public void setBmDataAuth(Boolean bmDataAuth) {
		this.bmDataAuth = bmDataAuth;
	}

	public Boolean getQyDataAuth() {
		return qyDataAuth;
	}

	public void setQyDataAuth(Boolean qyDataAuth) {
		this.qyDataAuth = qyDataAuth;
	}

	public String getLoginName() {
		return loginName;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}
}