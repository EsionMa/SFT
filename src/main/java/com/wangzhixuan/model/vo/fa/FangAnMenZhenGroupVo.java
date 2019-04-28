package com.wangzhixuan.model.vo.fa;

import java.util.List;

import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.fa.FangAnMenZhenGroup;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.param.Validate;
import com.wangzhixuan.param.ValidateResult;

public class FangAnMenZhenGroupVo extends FangAnMenZhenGroup implements Validate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5609852014477152191L;
	private List<SysOrganization> depts;
	private List<Icd> mzzds;
	private List<Long> menZhenInfoIds;
	private List<SysDictionaries> regTypes;
	private List<SysDictionaries> regWays;


	public List<SysOrganization> getDepts() {
		return depts;
	}


	public void setDepts(List<SysOrganization> depts) {
		this.depts = depts;
	}


	public List<Icd> getMzzds() {
		return mzzds;
	}


	public void setMzzds(List<Icd> mzzds) {
		this.mzzds = mzzds;
	}


	public List<Long> getMenZhenInfoIds() {
		return menZhenInfoIds;
	}


	public void setMenZhenInfoIds(List<Long> menZhenInfoIds) {
		this.menZhenInfoIds = menZhenInfoIds;
	}


	public List<SysDictionaries> getRegTypes() {
		return regTypes;
	}


	public void setRegTypes(List<SysDictionaries> regTypes) {
		this.regTypes = regTypes;
	}


	public List<SysDictionaries> getRegWays() {
		return regWays;
	}


	public void setRegWays(List<SysDictionaries> regWays) {
		this.regWays = regWays;
	}


	@Override
	public ValidateResult validate() {
		ValidateResult result = new ValidateResult();
		result.setValidate(false);
		return result;
	}

}
