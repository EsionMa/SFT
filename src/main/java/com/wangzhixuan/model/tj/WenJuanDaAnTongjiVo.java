package com.wangzhixuan.model.tj;

import com.wangzhixuan.model.wenjuan.WenJuan;

import java.io.Serializable;
import java.util.List;
/**
 *@author: Leslie
 *@Description:问卷统计vo，只统计有效答题，答卷做过选择的
 *@Date 2018/1/19 11:25
 *@Param:
 */
public class WenJuanDaAnTongjiVo extends WenJuan implements Serializable {
	private static final long serialVersionUID = -7441859202119230936L;
	private List<TM> tms;

	public List<TM> getTms() {
		return tms;
	}

	public void setTms(List<TM> tms) {
		this.tms = tms;
	}
}
