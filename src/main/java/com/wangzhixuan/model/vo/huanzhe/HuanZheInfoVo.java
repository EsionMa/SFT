package com.wangzhixuan.model.vo.huanzhe;

import java.util.Date;
import java.util.List;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.HuanZheTag;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;

public class HuanZheInfoVo extends HuanZheInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6745618034711778797L;
	
	private Integer page = 1;
	private Integer rows = 10;
	private Date staDate;
	private Date endDate;
	private Date birthSta;
	private Date birthEnd;
	private List<HuanZheContractVo> contracts;
	private List<FuYouHuanZheInfoVo> pregnant;
	private List<ZhuYuanInfoVo> zhuyuanInfos;
	private List<MenZhenInfoVo> menzhenInfos;
	private String userName;// 创建人姓名
	private String ybtypeName;// 医保类型名
	private String cardName;// 证件类型名
	private String hzlyName;// 患者来源类型名
	private List<HuanZheTag> tags;
	private String tagsName;

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

	public Date getStaDate() {
		return staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBirthSta() {
		return birthSta;
	}

	public void setBirthSta(Date birthSta) {
		this.birthSta = birthSta;
	}

	public Date getBirthEnd() {
		return birthEnd;
	}

	public void setBirthEnd(Date birthEnd) {
		this.birthEnd = birthEnd;
	}

	public List<HuanZheContractVo> getContracts() {
		return contracts;
	}

	public void setContracts(List<HuanZheContractVo> contracts) {
		this.contracts = contracts;
	}

	public List<FuYouHuanZheInfoVo> getPregnant() {
		return pregnant;
	}

	public void setPregnant(List<FuYouHuanZheInfoVo> pregnant) {
		this.pregnant = pregnant;
	}

	public List<ZhuYuanInfoVo> getZhuyuanInfos() {
		return zhuyuanInfos;
	}

	public void setZhuyuanInfos(List<ZhuYuanInfoVo> zhuyuanInfos) {
		this.zhuyuanInfos = zhuyuanInfos;
	}

	public List<MenZhenInfoVo> getMenzhenInfos() {
		return menzhenInfos;
	}

	public void setMenzhenInfos(List<MenZhenInfoVo> menzhenInfos) {
		this.menzhenInfos = menzhenInfos;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getYbtypeName() {
		return ybtypeName;
	}

	public void setYbtypeName(String ybtypeName) {
		this.ybtypeName = ybtypeName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getHzlyName() {
		return hzlyName;
	}

	public void setHzlyName(String hzlyName) {
		this.hzlyName = hzlyName;
	}

	public List<HuanZheTag> getTags() {
		return tags;
	}

	public void setTags(List<HuanZheTag> tags) {
		this.tags = tags;
	}

	public String getTagsName() {
		return tagsName;
	}

	public void setTagsName(String tagsName) {
		this.tagsName = tagsName;
	}

}
