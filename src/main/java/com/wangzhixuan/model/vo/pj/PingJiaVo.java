package com.wangzhixuan.model.vo.pj;

import com.wangzhixuan.model.pj.PingJia;

import java.util.Date;

public class PingJiaVo extends PingJia{
	private Integer page=1;
	private Integer rows=10;
    private Long cyDeptId;//出院科室
	private String cyName;//出院科室名
	private String evaName;//被评价科室名
	private String hzName;//患者名
   private String phoneNum;//电话号码
	private Date staTime;//开始时间
	private Date endTime;//结束时间
	private Integer faId;//方案Id

	public Integer getFaId() {
		return faId;
	}

	public void setFaId(Integer faId) {
		this.faId = faId;
	}

	public Date getStaTime() {
		return staTime;
	}

	public void setStaTime(Date staTime) {
		this.staTime = staTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getCyDeptId() {
		return cyDeptId;
	}

	public void setCyDeptId(Long cyDeptId) {
		this.cyDeptId = cyDeptId;
	}

	public String getCyName() {
		return cyName;
	}

	public void setCyName(String cyName) {
		this.cyName = cyName;
	}

	public String getEvaName() {
		return evaName;
	}

	public void setEvaName(String evaName) {
		this.evaName = evaName;
	}

	public String getHzName() {
		return hzName;
	}

	public void setHzName(String hzName) {
		this.hzName = hzName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * 
	 */

	private static final long serialVersionUID = 5786765902666440769L;

}
