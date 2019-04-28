package com.wangzhixuan.model.vo.wj;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2017/8/25. TIME:14:16 Date:2017/8/25. Description:随访结果统计
 */
public class WenJuanShiLiBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5505131725314156639L;
	private Date staTime;// 查询开始时间段
	private Date endTime;// 查询结束时间段
	// 随访的各种状态
	private Integer planCount;// 预约随访
	private Integer successSf;// 成功人数
	private Integer waitSf;// 未随访人数
	private Integer allSf;// 所有随访
	private Integer noRespons;// 无人接听
	private Integer errorNum;// 空号错号
	private Integer refuseSf;// 拒接随访
	private Integer deadStatus;// 死亡状态
	private Integer sfCount;// 已随访数
	private Integer hzCount;// 被随访患者数
	private String sfPercent;// 随访率
	private String successPercent;// 成功随访率

	public Date getStaTime() {
		return staTime;
	}

	public void setStaTime(Date staTime) {
		this.staTime = staTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPlanCount() {
		return planCount;
	}

	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}

	public Integer getSuccessSf() {
		return successSf;
	}

	public void setSuccessSf(Integer successSf) {
		this.successSf = successSf;
	}

	public Integer getWaitSf() {
		return waitSf;
	}

	public void setWaitSf(Integer waitSf) {
		this.waitSf = waitSf;
	}

	public Integer getAllSf() {
		return allSf;
	}

	public void setAllSf(Integer allSf) {
		this.allSf = allSf;
	}

	public Integer getNoRespons() {
		return noRespons;
	}

	public void setNoRespons(Integer noRespons) {
		this.noRespons = noRespons;
	}

	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public Integer getRefuseSf() {
		return refuseSf;
	}

	public void setRefuseSf(Integer refuseSf) {
		this.refuseSf = refuseSf;
	}

	public Integer getDeadStatus() {
		return deadStatus;
	}

	public void setDeadStatus(Integer deadStatus) {
		this.deadStatus = deadStatus;
	}

	public Integer getSfCount() {
		return sfCount;
	}

	public void setSfCount(Integer sfCount) {
		this.sfCount = sfCount;
	}

	public Integer getHzCount() {
		return hzCount;
	}

	public void setHzCount(Integer hzCount) {
		this.hzCount = hzCount;
	}

	public String getSfPercent() {
		return sfPercent;
	}

	public void setSfPercent(String sfPercent) {
		this.sfPercent = sfPercent;
	}

	public String getSuccessPercent() {
		return successPercent;
	}

	public void setSuccessPercent(String successPercent) {
		this.successPercent = successPercent;
	}

}
