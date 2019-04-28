package com.wangzhixuan.model.fa;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_fa_zy_group")
public class FangAnZhuYuanGroup implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -840345809034984050L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String sex;// 0女1男2未知
	private Integer zxhznl;// 最小患者年龄，大于等于0的整数，为空则为0
	private Integer zdhznl;// 最大患者年龄,最大默认254
	private String sxsjlx;// 筛选时间类型 1入院时间 2出院时间
	private Date zxsxsj;// 最小筛选时间，空为无限制
	private Date zdsxsj;// 最大筛选时间,空为无限制
	private String statue;// 状态 1启用 0禁用
	@TableField("fa_id")
	private Long faId;// 方案ID
	@TableField("dept_ids")
	private String deptIds;// 科室用逗号隔开
	@TableField("zd_codes")
	private String zdCodes;// 诊断用逗号隔开
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getZxhznl() {
		return zxhznl;
	}

	public void setZxhznl(Integer zxhznl) {
		this.zxhznl = zxhznl;
	}

	public Integer getZdhznl() {
		return zdhznl;
	}

	public void setZdhznl(Integer zdhznl) {
		this.zdhznl = zdhznl;
	}

	public String getSxsjlx() {
		return sxsjlx;
	}

	public void setSxsjlx(String sxsjlx) {
		this.sxsjlx = sxsjlx;
	}

	public Date getZxsxsj() {
		return zxsxsj;
	}

	public void setZxsxsj(Date zxsxsj) {
		this.zxsxsj = zxsxsj;
	}

	public Date getZdsxsj() {
		return zdsxsj;
	}

	public void setZdsxsj(Date zdsxsj) {
		this.zdsxsj = zdsxsj;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public Long getFaId() {
		return faId;
	}

	public void setFaId(Long faId) {
		this.faId = faId;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getZdCodes() {
		return zdCodes;
	}

	public void setZdCodes(String zdCodes) {
		this.zdCodes = zdCodes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

}
