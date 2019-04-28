package com.wangzhixuan.model.vo.zhishiku;

import java.util.List;

import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;

public class ZhiShiKuNodeVo extends ZhiShiKuNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398739944831397647L;
	private List<ZhiShiKuNode> children;

	private String isParent;

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public List<ZhiShiKuNode> getChildren() {
		return children;
	}
	public void setChildren(List<ZhiShiKuNode> children) {
		this.children = children;
	}


}
