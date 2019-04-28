package com.wangzhixuan.model.vo.zhishiku;

import com.wangzhixuan.model.zhishiku.ZhiShiKu;

public class ZhiShiKuVo extends ZhiShiKu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9051854724687759626L;
	/** 当前页 */
	private Integer page = 1;
	/** 显示条数 */
	private Integer rows = 10;

	private String diseaseName;

	private Long deptId;


	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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

}
