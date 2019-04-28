package com.wangzhixuan.model.wenjuan;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
/***
 *@author: Leslie
 *@Date 2018/1/29 12:06
 *@param:
 *@return
 *@throws
 *@Description:问卷表
 */
@TableName("hzsf_wj")
public class WenJuan implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 2363081487289036158L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String wjzt;// 问卷主题
	private String wjfl;// 问卷分类,字典表Code
	private Date jzrq;// 截止日期
	private String sfzdymb;// 否是自定义模板（1为是，0为否）
	private String dclx;// 调查类型（1为匿名调查，2为实名调查）
	private String syfw;// 使用范围（为个人私有，2为公开）
	private String wjsm;// 问卷说明
	private String status;// 状态 1正常 9删除
	@TableField("dept_id")
	private Long deptId;// 科室ID
	@TableField("zd_code")
	private String zdCode;// 诊断code
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	@TableField(value = "wj_score")
	private Double wenjuanScore; //问卷总分值


	public Double getWenjuanScore() {
		return wenjuanScore;
	}

	public void setWenjuanScore(Double wenjuanScore) {
		this.wenjuanScore = wenjuanScore;
	}

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
	 * @return wjzt
	 */
	public String getWjzt() {
		return wjzt;
	}

	/**
	 * @param wjzt
	 *            要设置的 wjzt
	 */
	public void setWjzt(String wjzt) {
		this.wjzt = wjzt;
	}

	/**
	 * @return wjfl
	 */
	public String getWjfl() {
		return wjfl;
	}

	/**
	 * @param wjfl
	 *            要设置的 wjfl
	 */
	public void setWjfl(String wjfl) {
		this.wjfl = wjfl;
	}

	/**
	 * @return jzrq
	 */
	public Date getJzrq() {
		return jzrq;
	}

	/**
	 * @param jzrq
	 *            要设置的 jzrq
	 */
	public void setJzrq(Date jzrq) {
		this.jzrq = jzrq;
	}

	/**
	 * @return sfzdymb
	 */
	public String getSfzdymb() {
		return sfzdymb;
	}

	/**
	 * @param sfzdymb
	 *            要设置的 sfzdymb
	 */
	public void setSfzdymb(String sfzdymb) {
		this.sfzdymb = sfzdymb;
	}

	/**
	 * @return dclx
	 */
	public String getDclx() {
		return dclx;
	}

	/**
	 * @param dclx
	 *            要设置的 dclx
	 */
	public void setDclx(String dclx) {
		this.dclx = dclx;
	}

	/**
	 * @return syfw
	 */
	public String getSyfw() {
		return syfw;
	}

	/**
	 * @param syfw
	 *            要设置的 syfw
	 */
	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}

	/**
	 * @return wjsm
	 */
	public String getWjsm() {
		return wjsm;
	}

	/**
	 * @param wjsm
	 *            要设置的 wjsm
	 */
	public void setWjsm(String wjsm) {
		this.wjsm = wjsm;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return deptId
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            要设置的 deptId
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return zdCode
	 */
	public String getZdCode() {
		return zdCode;
	}

	/**
	 * @param zdCode
	 *            要设置的 zdCode
	 */
	public void setZdCode(String zdCode) {
		this.zdCode = zdCode;
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

}
