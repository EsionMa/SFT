package com.wangzhixuan.model.pj;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_pj")
public class PingJia implements Serializable {
	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 6534828052285663419L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("bpj_name")
	private String bpjName;// 被评价人姓名
	@TableField("bpj_dept_name")
	private String bpjDeptName;// 被评价科室姓名
	@TableField("bpj_dept_id")
	private Long bpjDeptId;// 被评价科室ID
	private String kspj;// 科室评价/1.医德医风2.住院环境3.服务态度4.其他
	private String pjnr;// 评价内容/
	private String pjlx;// 评价类型1投诉2建议3表扬
	private String pjly;// 评价来源1住院随访评价2门诊随访评价
	@TableField("pjly_ext")
	private String pjlyExt;// 其他扩充存储评价来源扩充字段
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	@TableField(value = "fa_type")
	private Integer faType;
	@TableField(value = "hz_id")
	private Long hzId;
	@TableField(value = "zy_no")
	private String zyNo;
	@TableField(value = "phone_no")
	private String phoneNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBpjName() {
		return bpjName;
	}

	public void setBpjName(String bpjName) {
		this.bpjName = bpjName;
	}

	public String getBpjDeptName() {
		return bpjDeptName;
	}

	public void setBpjDeptName(String bpjDeptName) {
		this.bpjDeptName = bpjDeptName;
	}

	public Long getBpjDeptId() {
		return bpjDeptId;
	}

	public void setBpjDeptId(Long bpjDeptId) {
		this.bpjDeptId = bpjDeptId;
	}

	public String getKspj() {
		return kspj;
	}

	public void setKspj(String kspj) {
		this.kspj = kspj;
	}

	public String getPjnr() {
		return pjnr;
	}

	public void setPjnr(String pjnr) {
		this.pjnr = pjnr;
	}

	public String getPjlx() {
		return pjlx;
	}

	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}

	public String getPjly() {
		return pjly;
	}

	public void setPjly(String pjly) {
		this.pjly = pjly;
	}

	public String getPjlyExt() {
		return pjlyExt;
	}

	public void setPjlyExt(String pjlyExt) {
		this.pjlyExt = pjlyExt;
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

	@Override
	public String toString() {
		return "PingJia [id=" + id + ", bpjName=" + bpjName + ", bpjDeptName=" + bpjDeptName + ", bpjDeptId="
				+ bpjDeptId + ", kspj=" + kspj + ", pjnr=" + pjnr + ", pjlx=" + pjlx + ", pjly=" + pjly + ", pjlyExt="
				+ pjlyExt + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUserId="
				+ createUserId + ", updateUserId=" + updateUserId + "]";
	}


	public void setFaType(Integer faType) {
		this.faType = faType;
	}

	public Integer getFaType() {
		return faType;
	}

	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}

	public Long getHzId() {
		return hzId;
	}

	public void setZyNo(String zyNo) {
		this.zyNo = zyNo;
	}

	public String getZyNo() {
		return zyNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}
}
