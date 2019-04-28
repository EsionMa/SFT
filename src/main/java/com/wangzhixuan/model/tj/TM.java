package com.wangzhixuan.model.tj;

import com.wangzhixuan.model.wenjuan.TiMu;

import java.util.List;

public class TM extends TiMu{

	private Integer count;// 题目有效答题数量

	private List<TMXX> xxs;// 选项


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<TMXX> getXxs() {
		return xxs;
	}

	public void setXxs(List<TMXX> xxs) {
		this.xxs = xxs;
	}



}
