package com.wangzhixuan.model.vo.fa;

import java.util.List;

import com.wangzhixuan.model.fa.FangAnDingYi;

public class FangAnDingYiVo extends FangAnDingYi {

	/**
	 * 
	 */
	public static final String FATYPE="faType";
	private static final long serialVersionUID = -8069694658981520584L;
	private List<FangAnDingYiItemVo> items;
	private FangAnZhuYuanGroupVo zygroup;
	private FangAnMenZhenGroupVo mzgroup;
	private String userName;// 方案定义人

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<FangAnDingYiItemVo> getItems() {
		return items;
	}

	public void setItems(List<FangAnDingYiItemVo> items) {
		this.items = items;
	}

	public FangAnZhuYuanGroupVo getZygroup() {
		if (this.zygroup == null) {
			this.zygroup = new FangAnZhuYuanGroupVo();
		}
		return zygroup;
	}

	public void setZygroup(FangAnZhuYuanGroupVo zygroup) {
		this.zygroup = zygroup;
	}

	public FangAnMenZhenGroupVo getMzgroup() {
		if (this.mzgroup == null) {
			this.mzgroup = new FangAnMenZhenGroupVo();
		}
		return mzgroup;
	}

	public void setMzgroup(FangAnMenZhenGroupVo mzgroup) {
		this.mzgroup = mzgroup;
	}

}
