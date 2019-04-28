package com.wangzhixuan.model.vo;

import java.util.Date;
import java.util.List;
import com.wangzhixuan.model.vo.pj.PingJiaVo;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;

public class WenJuanShiLiVo extends WenJuanShiLi {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4053024523042465190L;
	private List<PingJiaVo> pingJia;
	private Integer page = 1;
	private Integer rows = 10;
	private String actual;// actual：有值，则为实际随访时间
	private Date startDate;// 开始时间
	private Date endDate;// 结束时间
	private Date ydDate;// 预定时间
	private String zxname;//执行人
	private List<WenJuanVo> wenJuans;

	public String getZxname() {
		return zxname;
	}

	public void setZxname(String zxname) {
		this.zxname = zxname;
	}

	public List<PingJiaVo> getPingJia() {
		return pingJia;
	}

	public void setPingJia(List<PingJiaVo> pingJia) {
		this.pingJia = pingJia;
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

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getYdDate() {
		return ydDate;
	}

	public void setYdDate(Date ydDate) {
		this.ydDate = ydDate;
	}

	public List<WenJuanVo> getWenJuans() {
		return wenJuans;
	}

	public void setWenJuans(List<WenJuanVo> wenJuans) {
		this.wenJuans = wenJuans;
	}

}
