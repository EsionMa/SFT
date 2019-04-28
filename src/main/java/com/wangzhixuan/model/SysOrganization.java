package com.wangzhixuan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * 组织机构
 *
 */
@TableName("sys_organization")
public class SysOrganization implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 4929146660905533536L;
	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;
	/** 组织名 */
	@NotBlank
	private String name;
	/** 地址 */
	private String address;
	/** 编号 */
	@NotBlank
	private String code;
	/** 图标 */
	@JsonProperty("iconCls")
	private String icon;
	/** 父级主键 */
	private Long pid;
	/** 排序 */
	private Integer seq;
	private String type;// 类型，1权限部门管理人员 2医院部门
	/** 引入对方数据库id */
	@TableField(value = "out_id")
	private String outId;
	/** 暂存对方数据库pid */
	@TableField(exist = false)
	private String outPid;
	/** 创建时间 */
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(exist = false)
	private List<SysOrganization> children;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getOutPid() {
		return outPid;
	}

	public void setOutPid(String outPid) {
		this.outPid = outPid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<SysOrganization> getChildren() {
		return children;
	}

	public void setChildren(List<SysOrganization> children) {
		this.children = children;
	}

}
