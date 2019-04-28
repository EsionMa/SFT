package com.wangzhixuan.model.vo.fa;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.fa.FangAnZhuYuanGroup;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.param.Validate;
import com.wangzhixuan.param.ValidateResult;

public class FangAnZhuYuanGroupVo extends FangAnZhuYuanGroup implements Validate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4734381626251488326L;
	private List<SysOrganization> depts;
	private List<Icd> cyzds;
	private List<Long> zhuYuanInfoIds;

	public List<SysOrganization> getDepts() {
		return depts;
	}

	public void setDepts(List<SysOrganization> depts) {
		this.depts = depts;
	}

	public List<Icd> getCyzds() {
		return cyzds;
	}

	public void setCyzds(List<Icd> cyzds) {
		this.cyzds = cyzds;
	}

	public List<Long> getZhuYuanInfoIds() {
		return zhuYuanInfoIds;
	}

	public void setZhuYuanInfoIds(List<Long> zhuYuanInfoIds) {
		this.zhuYuanInfoIds = zhuYuanInfoIds;
	}

	@Override
	public ValidateResult validate() {
		ValidateResult result = new ValidateResult();
		result.setValidate(true);
		if (StringUtils.isBlank(getSxsjlx())) {
			result.setValidate(false);
			result.setMsg("住院患者来源筛选时间类型不能为空");
		}
		return result;
	}

}
