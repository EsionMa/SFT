package com.wangzhixuan.model.wenjuan;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 问卷实例表
 *
 * @author LuoQiang
 * @date 2017年5月18日 下午5:34:09
 */
@TableName("hzsf_wj_sl")
public class WenJuanShiLi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8690985573127775358L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("fa_id")
	private Long faId;
	@TableField("fa_item_id")
	private Long faItemId;// 方案ItemId
	@TableField("wj_id")
	private Long wjId;// 问卷ID
	@TableField("hz_id")
	private Long hzId;// 患者ID
	@TableField("hz_ls_id")
	private Long hzLsId;// 患者流水ID（住院ID，门诊ID）
	@TableField("hz_name")
	private String hzName;// 患者姓名
	@TableField("doctor_id")
	private Long doctorId;// 医生ID
	@TableField("doctor_name")
	private String doctorName;// 医生姓名
	@TableField("hs_id")
	private Long hsId;// 护士ID
	@TableField("hs_name")
	private String hsName;// 护士姓名
	@TableField("dept_id")
	private Long deptId;// 科室ID
	@TableField("dept_name")
	private String deptName;// 科室名字
	@TableField("other_time")
	private Date otherTime;// 出院时间
	@TableField("hz_mobile_phone")
	private String hzMobilePhone;// 患者手机号
	@TableField("wjzt")
	private String wjzt;// 问卷主题
	@TableField("jhsf_date")
	private Date jhsfDate;// 计划随访日期
	@TableField("dc_time")
	private Date dcTime;// 调查时间
	@TableField("sf_type")
	private String sfType;// 随访类型|调查方式 1电话 2短信 8触摸屏 9 评价器
	private String dcxj;// 调查小结
	private String zxfs;// 执行方式 1 按照方案计划等执行随访 2无计划的
						// 自主随访，包括用户通过APP、PAD等移动设备，触摸屏，评价仪器等现场操作的方式为用户自主执行随访文件
	private String sfnm;// 是否匿名1是0不是
	private String ybno;// 医保号码
	private String zlcard;// 诊疗卡号
	private String status;// 问卷状态 0初始化 1未调查 2调查成功 3调查失败，4预约调查，9作废
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	@TableField("fa_type")
	private Integer faType;// 方案类型，1.出院，2门诊
	@TableField("hzly")
	private Integer hzly;// 患者来源

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

	public Long getFaItemId() {
		return faItemId;
	}

	public void setFaItemId(Long faItemId) {
		this.faItemId = faItemId;
	}

	public Long getWjId() {
		return wjId;
	}

	public void setWjId(Long wjId) {
		this.wjId = wjId;
	}

	public Long getHzId() {
		return hzId;
	}

	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}

	public Long getHzLsId() {
		return hzLsId;
	}

	public void setHzLsId(Long hzLsId) {
		this.hzLsId = hzLsId;
	}

	public String getHzName() {
		return hzName;
	}

	public void setHzName(String hzName) {
		this.hzName = hzName;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Long getHsId() {
		return hsId;
	}

	public void setHsId(Long hsId) {
		this.hsId = hsId;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getOtherTime() {
		return otherTime;
	}

	public void setOtherTime(Date otherTime) {
		this.otherTime = otherTime;
	}

	public String getHzMobilePhone() {
		return hzMobilePhone;
	}

	public void setHzMobilePhone(String hzMobilePhone) {
		this.hzMobilePhone = hzMobilePhone;
	}

	public String getWjzt() {
		return wjzt;
	}

	public void setWjzt(String wjzt) {
		this.wjzt = wjzt;
	}

	public Date getJhsfDate() {
		return jhsfDate;
	}

	public void setJhsfDate(Date jhsfDate) {
		this.jhsfDate = jhsfDate;
	}

	public Date getDcTime() {
		return dcTime;
	}

	public void setDcTime(Date dcTime) {
		this.dcTime = dcTime;
	}

	public String getSfType() {
		return sfType;
	}

	public void setSfType(String sfType) {
		this.sfType = sfType;
	}

	public String getDcxj() {
		return dcxj;
	}

	public void setDcxj(String dcxj) {
		this.dcxj = dcxj;
	}

	public String getZxfs() {
		return zxfs;
	}

	public void setZxfs(String zxfs) {
		this.zxfs = zxfs;
	}

	public String getSfnm() {
		return sfnm;
	}

	public void setSfnm(String sfnm) {
		this.sfnm = sfnm;
	}

	public String getYbno() {
		return ybno;
	}

	public void setYbno(String ybno) {
		this.ybno = ybno;
	}

	public String getZlcard() {
		return zlcard;
	}

	public void setZlcard(String zlcard) {
		this.zlcard = zlcard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getFaType() {
		return faType;
	}

	public void setFaType(Integer faType) {
		this.faType = faType;
	}

	public Integer getHzly() {
		return hzly;
	}

	public void setHzly(Integer hzly) {
		this.hzly = hzly;
	}

}
