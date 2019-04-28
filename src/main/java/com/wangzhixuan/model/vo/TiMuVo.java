package com.wangzhixuan.model.vo;

import java.util.List;

import com.wangzhixuan.model.wenjuan.TiMu;
import com.wangzhixuan.model.wenjuan.TimuXuanXiang;

public class TiMuVo extends TiMu {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3796533877431690855L;
	private String showItem;//是否显示题目选项 1显示 0不显示
	private Integer page=1;
	private Integer rows=10;
	private Integer seq;
	private String dtnr;
	private List<TimuXuanXiang> tmxxs;

	public String getDtnr() {
		return dtnr;
	}

	public void setDtnr(String dtnr) {
		this.dtnr = dtnr;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public List<TimuXuanXiang> getTmxxs() {
		return tmxxs;
	}

	public void setTmxxs(List<TimuXuanXiang> tmxxs) {
		this.tmxxs = tmxxs;
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

	public String getShowItem() {
		return showItem;
	}

	public void setShowItem(String showItem) {
		this.showItem = showItem;
	}
	
}
