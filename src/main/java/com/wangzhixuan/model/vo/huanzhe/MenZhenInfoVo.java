package com.wangzhixuan.model.vo.huanzhe;

import java.util.Date;
import java.util.List;

import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;

public class MenZhenInfoVo extends MenZhenInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 38818712650239141L;
	private Integer page = 1;
	private Integer rows = 20;
	private Date staDate;
	private Date endDate;
	private String regisTypeName;
	private String regisWayName;
	private List<FuYouMenZhenInfoVo> pregnant;
	private Integer ageMin;
	private Integer ageMax;

	/**
	 * @return page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page 要设置的 page
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return rows
	 */
	public Integer getRows() {
		return rows;
	}
	/**
	 * @param rows 要设置的 rows
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	/**
	 * @return staDate
	 */
	public Date getStaDate() {
		return staDate;
	}
	/**
	 * @param staDate 要设置的 staDate
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
	 * @param endDate 要设置的 endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return regisTypeName
	 */
	public String getRegisTypeName() {
		return regisTypeName;
	}
	/**
	 * @param regisTypeName 要设置的 regisTypeName
	 */
	public void setRegisTypeName(String regisTypeName) {
		this.regisTypeName = regisTypeName;
	}
	/**
	 * @return regisWayName
	 */
	public String getRegisWayName() {
		return regisWayName;
	}
	/**
	 * @param regisWayName 要设置的 regisWayName
	 */
	public void setRegisWayName(String regisWayName) {
		this.regisWayName = regisWayName;
	}

	public List<FuYouMenZhenInfoVo> getPregnant() {
		return pregnant;
	}

	public void setPregnant(List<FuYouMenZhenInfoVo> pregnant) {
		this.pregnant = pregnant;
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
}
