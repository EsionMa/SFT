package com.wangzhixuan.model.fa;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_fa_mz_group")
public class FangAnMenZhenGroup implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -2307478357475195875L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "fa_id")
	private Long faId;// 方案id
	private String sex;// 性别0女 1 男 2 未知
	@TableField(value = "age_min")
	private Integer ageMin;// 最小年龄
	@TableField(value = "age_max")
	private Integer ageMax;// 最大年龄
	@TableField(value = "dept_ids")
	private String deptIds;// 科室id（多个科室逗号隔开）
	@TableField(value = "disease_codes")
	private String diseaseCodes;// 疾病 （多个疾病逗号隔开）
	@TableField(value = "mz_count_min")
	private Integer mzCountMin;// 门诊最少次数
	@TableField(value = "mz_count_max")
	private Integer mzCountMax;// 门诊最大次数
	@TableField(value = "regis_types")
	private String regisTypes;// 挂号类型
	@TableField(value = "regis_ways")
	private String regisWays;// 挂号方式
	@TableField(value = "date_min")
	private Date dateMin;// 最小筛选时间
	@TableField(value = "date_max")
	private Date dateMax;// 最大筛选时间
	private Integer status;// 状态 0.禁用 1.启用
	@TableField(value = "create_user_id")
	private Long createUserId;// 创建人id
	@TableField(value = "create_time")
	private Date createTime;
	@TableField(value = "update_user_id")
	private Long updateUserId;
	@TableField(value = "update_time")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFaId() {
		return faId;
	}

	public void setFaId(Long faId) {
		this.faId = faId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDiseaseCodes() {
		return diseaseCodes;
	}

	public void setDiseaseCodes(String diseaseCodes) {
		this.diseaseCodes = diseaseCodes;
	}

	public Integer getMzCountMin() {
		return mzCountMin;
	}

	public void setMzCountMin(Integer mzCountMin) {
		this.mzCountMin = mzCountMin;
	}

	public Integer getMzCountMax() {
		return mzCountMax;
	}

	public void setMzCountMax(Integer mzCountMax) {
		this.mzCountMax = mzCountMax;
	}

	public String getRegisTypes() {
		return regisTypes;
	}

	public void setRegisTypes(String regisTypes) {
		this.regisTypes = regisTypes;
	}

	public String getRegisWays() {
		return regisWays;
	}

	public void setRegisWays(String regisWays) {
		this.regisWays = regisWays;
	}

	public Date getDateMin() {
		return dateMin;
	}

	public void setDateMin(Date dateMin) {
		this.dateMin = dateMin;
	}

	public Date getDateMax() {
		return dateMax;
	}

	public void setDateMax(Date dateMax) {
		this.dateMax = dateMax;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
