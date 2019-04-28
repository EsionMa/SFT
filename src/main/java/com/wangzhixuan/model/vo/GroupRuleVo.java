package com.wangzhixuan.model.vo;

import java.util.List;

import com.wangzhixuan.model.GroupRule;

public class GroupRuleVo extends GroupRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4409022993145996957L;
	private List<String> hzlys;
	private List<Long> jzkss;
	
	public List<String> getHzlys() {
		return hzlys;
	}
	public void setHzlys(List<String> hzlys) {
		this.hzlys = hzlys;
	}
	public List<Long> getJzkss() {
		return jzkss;
	}
	public void setJzkss(List<Long> jzkss) {
		this.jzkss = jzkss;
	}
	
	

}
