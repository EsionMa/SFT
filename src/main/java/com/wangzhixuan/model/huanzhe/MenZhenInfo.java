/**
 * 2017-12-18 10:24:54
 */
package com.wangzhixuan.model.huanzhe;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author Esion
 *
 */
@TableName("hzsf_mz_info")
public class MenZhenInfo implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7670501287904033556L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField(value = "hz_id")
	private Long hzId;// 患者id
	@TableField(value = "hz_no")
	private String hzNo;// 患者号
	@TableField(value = "hz_name")
	private String hzName;// 患者姓名
	@TableField(value = "mz_no")
	private String mzNo;// 门诊号
	@TableField(value = "mz_time")
	private Date mzTime;// 门诊时间
	@TableField(value = "mz_dept_id")
	private Long mzDeptId;// 门诊科室id
	@TableField(value = "mz_dept_name")
	private String mzDeptName;// 门诊科室名
	@TableField(value = "mz_count")
	private Integer mzCount;// 门诊次数
	@TableField(value = "doctor_id")
	private Long doctorId;// 医生id
	@TableField(value = "doctor_name")
	private String doctorName;// 医生姓名
	@TableField(value = "disease_code")
	private String diseaseCode;// 疾病诊断码
	@TableField(value = "disease_name")
	private String diseaseName;// 疾病名称
	private Integer age;// 年龄
	private String sex;// 性别
	private String phone;// 电话
	private String diagnose;// 主要门诊诊断
	private String diagnose1;// 诊断1
	private String diagnose2;// 诊断2
	@TableField(value = "major_describe")
	private String majorDescribe;// 病情描述
	private String disposition;// 处置
	@TableField(value = "create_user_id")
	private Long createUserId;// 创建人id
	@TableField(value = "update_user_id")
	private Long updateUserId;// 修改人id
	@TableField(value = "create_time")
	private Date createTime;// 创建时间
	@TableField(value = "update_time")
	private Date updateTime;// 修改时间
	@TableField(value = "regis_type")
	private String regisType;// 挂号类型：1.普通2.专家 default:1 字典表
	@TableField(value = "regis_way")
	private String regisWay;// 挂号方式：1.现场2.电话3.网上 defaut:1 字典表
	@TableField(value = "health_check")
	private String healthCheck;// 体格检查
	@TableField(value = "ill_history")
	private String illHistory;// 既往病史
	@TableField(value = "assist_check")
	private String assitCheck;// 辅助检查
	@TableField(value = "ill_now")
	private String illNow;// 现病史

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHzId() {
		return hzId;
	}

	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}

	public String getHzNo() {
		return hzNo;
	}

	public void setHzNo(String hzNo) {
		this.hzNo = hzNo;
	}

	public String getHzName() {
		return hzName;
	}

	public void setHzName(String hzName) {
		this.hzName = hzName;
	}

	public String getMzNo() {
		return mzNo;
	}

	public void setMzNo(String mzNo) {
		this.mzNo = mzNo;
	}

	public Date getMzTime() {
		return mzTime;
	}

	public void setMzTime(Date mzTime) {
		this.mzTime = mzTime;
	}

	public Long getMzDeptId() {
		return mzDeptId;
	}

	public void setMzDeptId(Long mzDeptId) {
		this.mzDeptId = mzDeptId;
	}

	public String getMzDeptName() {
		return mzDeptName;
	}

	public void setMzDeptName(String mzDeptName) {
		this.mzDeptName = mzDeptName;
	}

	public Integer getMzCount() {
		return mzCount;
	}

	public void setMzCount(Integer mzCount) {
		this.mzCount = mzCount;
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

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getDiagnose1() {
		return diagnose1;
	}

	public void setDiagnose1(String diagnose1) {
		this.diagnose1 = diagnose1;
	}

	public String getDiagnose2() {
		return diagnose2;
	}

	public void setDiagnose2(String diagnose2) {
		this.diagnose2 = diagnose2;
	}

	public String getMajorDescribe() {
		return majorDescribe;
	}

	public void setMajorDescribe(String majorDescribe) {
		this.majorDescribe = majorDescribe;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
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

	public String getRegisType() {
		return regisType;
	}

	public void setRegisType(String regisType) {
		this.regisType = regisType;
	}

	public String getRegisWay() {
		return regisWay;
	}

	public void setRegisWay(String regisWay) {
		this.regisWay = regisWay;
	}

	public String getHealthCheck() {
		return healthCheck;
	}

	public void setHealthCheck(String healthCheck) {
		this.healthCheck = healthCheck;
	}

	public String getIllHistory() {
		return illHistory;
	}

	public void setIllHistory(String illHistory) {
		this.illHistory = illHistory;
	}

	public String getAssitCheck() {
		return assitCheck;
	}

	public void setAssitCheck(String assitCheck) {
		this.assitCheck = assitCheck;
	}

	public String getIllNow() {
		return illNow;
	}

	public void setIllNow(String illNow) {
		this.illNow = illNow;
	}

}
