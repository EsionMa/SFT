package com.wangzhixuan.model.vo;

import java.util.Date;

public class WenJuanShiLiDataGridQuery {
	private Long exeUserId;
	private String status;
	private String timeType;// 要查询的时间类型
	private String hzName;// 患者姓名
	private Date staDate;
	private Date endDate;
	private Long wjId;
	private Long faId;
	private Long deptId;
	private Integer faType;
	private String doctorName;
	private String hsName;
	private String wjzt;
	private Integer hzly;

	/**
	 * @return exeUserId
	 */
	public Long getExeUserId() {
		return exeUserId;
	}

	/**
	 * @param exeUserId
	 *            要设置的 exeUserId
	 */
	public void setExeUserId(Long exeUserId) {
		this.exeUserId = exeUserId;
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
	 * @return timeType
	 */
	public String getTimeType() {
		return timeType;
	}

	/**
	 * @param timeType
	 *            要设置的 timeType
	 */
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	/**
	 * @return hzName
	 */
	public String getHzName() {
		return hzName;
	}

	/**
	 * @param hzName
	 *            要设置的 hzName
	 */
	public void setHzName(String hzName) {
		this.hzName = hzName;
	}

	/**
	 * @return staDate
	 */
	public Date getStaDate() {
		return staDate;
	}

	/**
	 * @param staDate
	 *            要设置的 staDate
	 */
	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	/**
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            要设置的 endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return wjId
	 */
	public Long getWjId() {
		return wjId;
	}

	/**
	 * @param wjId
	 *            要设置的 wjId
	 */
	public void setWjId(Long wjId) {
		this.wjId = wjId;
	}

	/**
	 * @return faId
	 */
	public Long getFaId() {
		return faId;
	}

	/**
	 * @param faId
	 *            要设置的 faId
	 */
	public void setFaId(Long faId) {
		this.faId = faId;
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
	 * @return faType
	 */
	public Integer getFaType() {
		return faType;
	}

	/**
	 * @param faType
	 *            要设置的 faType
	 */
	public void setFaType(Integer faType) {
		this.faType = faType;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHsName() {
		return hsName;
	}

	public void setHsName(String hsName) {
		this.hsName = hsName;
	}

	public String getWjzt() {
		return wjzt;
	}

	public void setWjzt(String wjzt) {
		this.wjzt = wjzt;
	}

	public Integer getHzly() {
		return hzly;
	}

	public void setHzly(Integer hzly) {
		this.hzly = hzly;
	}
}
