package com.wangzhixuan.model.vo.fa;

import com.wangzhixuan.model.fa.FangAnDingYiItem;
import java.util.List;
import java.util.Map;

public class FangAnDingYiItemVo extends FangAnDingYiItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077671940352629313L;
	private List<Map<String, Object>> exeUsers;
	private List<Map<String, Object>> wenJuans;

	public List<Map<String, Object>> getExeUsers() {
		return exeUsers;
	}

	public void setExeUsers(List<Map<String, Object>> exeUsers) {
		this.exeUsers = exeUsers;
	}

	public List<Map<String, Object>> getWenJuans() {
		return wenJuans;
	}

	public void setWenJuans(List<Map<String, Object>> wenJuans) {
		this.wenJuans = wenJuans;
	}

}
