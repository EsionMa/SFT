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
@TableName("hzsf_zy_info")
public class ZhuYuanInfo implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 1372569477821386352L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("hz_id")
	private Long hzId;// 患者id
	@TableField("hz_no")
	private String hzNo;// 患者号
	@TableField("hz_name")
	private String hzName;// 患者姓名
	@TableField("zy_no")
	private String zyNo;// 住院号
	private String sex;// 性别（0女 1男）
	private String phone;// 电话
	@TableField("gc_doctor_id") // 管床医生
	private Long gcDoctorId;
	@TableField("gc_doctor_name")
	private String gcDoctorName;// 管床医生名
	@TableField("gc_hs_id")
	private Long gcHsId;// 管床护士ID
	@TableField("gc_hs_name")
	private String gcHsName;// 管床护士名
	@TableField("zr_doctor_id")
	private Long zrDoctorId;// 主任医生id
	@TableField("zr_doctor_name")
	private String zrDoctorName;// 主任医生名
	@TableField("zz_doctor_id")
	private Long zzDoctorId;// 主治医生id
	@TableField("zz_doctor_name")
	private String zzDoctorName;// 主治医生名
	@TableField("zy_doctor_id")
	private Long zyDoctorId;// 住院医生id
	@TableField("zy_doctor_name")
	private String zyDoctorName;// 住院医生名
	private Integer age;// 年龄
	private Integer zycount;// 住院次数
	private String bq;// 病区
	private String cwno;// 床位号
	private Date rytime;// 入院时间
	@TableField("ry_dept_id")
	private Long ryDeptId;// 入院科室
	@TableField("ry_dept_name")
	private String ryDeptName;// 入院科室名
	private String rytype;// 入院类型
	private String ryqk;// 入院情况
	private String plzd;// 病理诊断
	private String ryzd;// 入院诊断
	private String ryzd1;//
	private String ryzd2;//
	private String ryzd3;//
	private String ryzd4;//
	private String cyzd;// 出院诊断
	private String cyzd1;// 出院诊断
	private String cyzd2;// 出院诊断
	private String cyzd3;// 出院诊断
	private String cyzd4;// 出院诊断
	private Date cytime;// 出院时间
	@TableField("cy_dept_id")
	private Long cyDeptId;// 出院科室
	@TableField("cy_dept_name")
	private String cyDeptName;// 出院科室
	private String cyqk;// 出院情况
	private String cyyz;// 出院医嘱
	private Date mztime;// 门诊时间
	private String mzno;// 门诊号
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	/** 暂存对方数据库outRyDeptId */
	@TableField(exist = false)
	private String outRyDeptId;
	/** 暂存对方数据库outCyDeptId */
	@TableField(exist = false)
	private String outCyDeptId;
	/** 暂存对方数据库outGcDoctorId */
	@TableField(exist = false)
	private String outGcDoctorId;
	/** 暂存对方数据库outZrDoctorId */
	@TableField(exist = false)
	private String outZrDoctorId;
	/** 暂存对方数据库outZzDoctorId */
	@TableField(exist = false)
	private String outZzDoctorId;
	/** 暂存对方数据库outZyDoctorId */
	@TableField(exist = false)
	private String outZyDoctorId;
	/** 暂存对方数据库outGcHsId */
	@TableField(exist = false)
	private String outGcHsId;

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

	public String getZyNo() {
		return zyNo;
	}

	public void setZyNo(String zyNo) {
		this.zyNo = zyNo;
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

	public Long getGcDoctorId() {
		return gcDoctorId;
	}

	public void setGcDoctorId(Long gcDoctorId) {
		this.gcDoctorId = gcDoctorId;
	}

	public String getGcDoctorName() {
		return gcDoctorName;
	}

	public void setGcDoctorName(String gcDoctorName) {
		this.gcDoctorName = gcDoctorName;
	}

	public Long getGcHsId() {
		return gcHsId;
	}

	public void setGcHsId(Long gcHsId) {
		this.gcHsId = gcHsId;
	}

	public String getGcHsName() {
		return gcHsName;
	}

	public void setGcHsName(String gcHsName) {
		this.gcHsName = gcHsName;
	}

	public Long getZrDoctorId() {
		return zrDoctorId;
	}

	public void setZrDoctorId(Long zrDoctorId) {
		this.zrDoctorId = zrDoctorId;
	}

	public String getZrDoctorName() {
		return zrDoctorName;
	}

	public void setZrDoctorName(String zrDoctorName) {
		this.zrDoctorName = zrDoctorName;
	}

	public Long getZzDoctorId() {
		return zzDoctorId;
	}

	public void setZzDoctorId(Long zzDoctorId) {
		this.zzDoctorId = zzDoctorId;
	}

	public String getZzDoctorName() {
		return zzDoctorName;
	}

	public void setZzDoctorName(String zzDoctorName) {
		this.zzDoctorName = zzDoctorName;
	}

	public Long getZyDoctorId() {
		return zyDoctorId;
	}

	public void setZyDoctorId(Long zyDoctorId) {
		this.zyDoctorId = zyDoctorId;
	}

	public String getZyDoctorName() {
		return zyDoctorName;
	}

	public void setZyDoctorName(String zyDoctorName) {
		this.zyDoctorName = zyDoctorName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getZycount() {
		return zycount;
	}

	public void setZycount(Integer zycount) {
		this.zycount = zycount;
	}

	public String getBq() {
		return bq;
	}

	public void setBq(String bq) {
		this.bq = bq;
	}

	public String getCwno() {
		return cwno;
	}

	public void setCwno(String cwno) {
		this.cwno = cwno;
	}

	public Date getRytime() {
		return rytime;
	}

	public void setRytime(Date rytime) {
		this.rytime = rytime;
	}

	public Long getRyDeptId() {
		return ryDeptId;
	}

	public void setRyDeptId(Long ryDeptId) {
		this.ryDeptId = ryDeptId;
	}

	public String getRyDeptName() {
		return ryDeptName;
	}

	public void setRyDeptName(String ryDeptName) {
		this.ryDeptName = ryDeptName;
	}

	public String getRytype() {
		return rytype;
	}

	public void setRytype(String rytype) {
		this.rytype = rytype;
	}

	public String getRyqk() {
		return ryqk;
	}

	public void setRyqk(String ryqk) {
		this.ryqk = ryqk;
	}

	public String getPlzd() {
		return plzd;
	}

	public void setPlzd(String plzd) {
		this.plzd = plzd;
	}

	public String getRyzd() {
		return ryzd;
	}

	public void setRyzd(String ryzd) {
		this.ryzd = ryzd;
	}

	public String getRyzd1() {
		return ryzd1;
	}

	public void setRyzd1(String ryzd1) {
		this.ryzd1 = ryzd1;
	}

	public String getRyzd2() {
		return ryzd2;
	}

	public void setRyzd2(String ryzd2) {
		this.ryzd2 = ryzd2;
	}

	public String getRyzd3() {
		return ryzd3;
	}

	public void setRyzd3(String ryzd3) {
		this.ryzd3 = ryzd3;
	}

	public String getRyzd4() {
		return ryzd4;
	}

	public void setRyzd4(String ryzd4) {
		this.ryzd4 = ryzd4;
	}

	public String getCyzd() {
		return cyzd;
	}

	public void setCyzd(String cyzd) {
		this.cyzd = cyzd;
	}

	public String getCyzd1() {
		return cyzd1;
	}

	public void setCyzd1(String cyzd1) {
		this.cyzd1 = cyzd1;
	}

	public String getCyzd2() {
		return cyzd2;
	}

	public void setCyzd2(String cyzd2) {
		this.cyzd2 = cyzd2;
	}

	public String getCyzd3() {
		return cyzd3;
	}

	public void setCyzd3(String cyzd3) {
		this.cyzd3 = cyzd3;
	}

	public String getCyzd4() {
		return cyzd4;
	}

	public void setCyzd4(String cyzd4) {
		this.cyzd4 = cyzd4;
	}

	public Date getCytime() {
		return cytime;
	}

	public void setCytime(Date cytime) {
		this.cytime = cytime;
	}

	public Long getCyDeptId() {
		return cyDeptId;
	}

	public void setCyDeptId(Long cyDeptId) {
		this.cyDeptId = cyDeptId;
	}

	public String getCyDeptName() {
		return cyDeptName;
	}

	public void setCyDeptName(String cyDeptName) {
		this.cyDeptName = cyDeptName;
	}

	public String getCyqk() {
		return cyqk;
	}

	public void setCyqk(String cyqk) {
		this.cyqk = cyqk;
	}

	public String getCyyz() {
		return cyyz;
	}

	public void setCyyz(String cyyz) {
		this.cyyz = cyyz;
	}

	public Date getMztime() {
		return mztime;
	}

	public void setMztime(Date mztime) {
		this.mztime = mztime;
	}

	public String getMzno() {
		return mzno;
	}

	public void setMzno(String mzno) {
		this.mzno = mzno;
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

	public String getOutRyDeptId() {
		return outRyDeptId;
	}

	public void setOutRyDeptId(String outRyDeptId) {
		this.outRyDeptId = outRyDeptId;
	}

	public String getOutCyDeptId() {
		return outCyDeptId;
	}

	public void setOutCyDeptId(String outCyDeptId) {
		this.outCyDeptId = outCyDeptId;
	}

	public String getOutGcDoctorId() {
		return outGcDoctorId;
	}

	public void setOutGcDoctorId(String outGcDoctorId) {
		this.outGcDoctorId = outGcDoctorId;
	}

	public String getOutZrDoctorId() {
		return outZrDoctorId;
	}

	public void setOutZrDoctorId(String outZrDoctorId) {
		this.outZrDoctorId = outZrDoctorId;
	}

	public String getOutZzDoctorId() {
		return outZzDoctorId;
	}

	public void setOutZzDoctorId(String outZzDoctorId) {
		this.outZzDoctorId = outZzDoctorId;
	}

	public String getOutZyDoctorId() {
		return outZyDoctorId;
	}

	public void setOutZyDoctorId(String outZyDoctorId) {
		this.outZyDoctorId = outZyDoctorId;
	}

	public String getOutGcHsId() {
		return outGcHsId;
	}

	public void setOutGcHsId(String outGcHsId) {
		this.outGcHsId = outGcHsId;
	}

}
