package com.wangzhixuan.model.vo.pj;

import java.util.List;

public class EvaluationVo {
	/** 科室 */
	private String deptName;
	/** 评价  前端需要的是评价人，住院号，手机号*/
	private List<PingJiaVo> evaContentList;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<PingJiaVo> getEvaContentList() {
		return evaContentList;
	}

	public void setEvaContentList(List<PingJiaVo> evaContentList) {
		this.evaContentList = evaContentList;
	}

}
