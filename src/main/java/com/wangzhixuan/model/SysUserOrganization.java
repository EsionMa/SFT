package com.wangzhixuan.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *
 * 用户角色
 *
 */
@TableName("sys_user_organization")
public class SysUserOrganization implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 786911679352807721L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 用户id */
	@TableField(value = "user_id")
	private Long userId;

	/** 部门id */
	@TableField(value = "organization_id")
	private Long organizationId;

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
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            要设置的 userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return organizationId
	 */
	public Long getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId
	 *            要设置的 organizationId
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
