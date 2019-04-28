package com.wangzhixuan.model.vo;

import com.wangzhixuan.model.wenjuan.WenJuan;

import java.util.Date;
import java.util.List;

public class WenJuanVo extends WenJuan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5505112915885857192L;
	private Integer rows;
	private Integer page;
	private List<TiMuVo> tiMus;
	private List<TiMuShiLiVo> tmsls;// 题目实例数
	private Long itemId;//已完成问卷的数据库id
	/**
	 * 问卷题总数
	 */
	private Integer wjCount;
	/**
	 * 问卷实例id
	 */
	private Long wjslId;
	/**
	 * 时间
	 */
	private Date dcDate;
	/**
	 * 科室
	 */
	private String deptName;
	/**
	 * 答题人
	 */
	private String answerName;
	/**
	 * 答题数目
	 */
	private Integer answerCount;
	/**
	 * 得分
	 */
	private Double grade;
	/**调查人
	 *
	 */
    private String userName;


	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getWjCount() {
		return wjCount;
	}

	public void setWjCount(Integer wjCount) {
		this.wjCount = wjCount;
	}

	public Long getWjslId() {
		return wjslId;
	}

	public void setWjslId(Long wjslId) {
		this.wjslId = wjslId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDcDate() {
		return dcDate;
	}

	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<TiMuVo> getTiMus() {
		return tiMus;
	}
	public void setTiMus(List<TiMuVo> tiMus) {
		this.tiMus = tiMus;
	}
	public List<TiMuShiLiVo> getTmsls() {
		return tmsls;
	}
	public void setTmsls(List<TiMuShiLiVo> tmsls) {
		this.tmsls = tmsls;
	}


}
