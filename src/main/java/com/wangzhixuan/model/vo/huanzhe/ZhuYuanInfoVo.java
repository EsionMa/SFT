package com.wangzhixuan.model.vo.huanzhe;

import java.util.Date;
import java.util.List;

import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;

public class ZhuYuanInfoVo extends ZhuYuanInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7453734802355603225L;
	private Integer page = 1;
	private Integer rows = 10;
	private Date staDate;
	private Date endDate;
	private String timeType;
	private Integer ageMin;
	private Integer ageMax;


	private List<FuYouZhuYuanInfoVo> pregnant;

	public String getZyDay() {
		Date cytime2 = getCytime();
		Date rytime2 = getRytime();
		if (cytime2 == null || rytime2 == null) {
			return "";
		}
		return "" + Math.abs(DateUtils.getDiffDays(rytime2,cytime2));
	}

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
	 * @return timeType
	 */
	public String getTimeType() {
		return timeType;
	}

	/**
	 * @param timeType 要设置的 timeType
	 */
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public List<FuYouZhuYuanInfoVo> getPregnant() {
		return pregnant;
	}

	public void setPregnant(List<FuYouZhuYuanInfoVo> pregnant) {
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
