package com.wangzhixuan.model.huanzhe;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_hz_info")
public class HuanZheInfo implements Serializable {
	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -6379128525002687844L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String hzno;// 患者号
	private String name;// 姓名
	private String photo;// 照片路径相对路径
	private String isaliave;// 是否健在 1健在2死亡
	private Date deadtime;// 死亡时间
	private String deadcause;// 死亡原因
	private String sex;// 0女1男2未知
	private String ybtype;// 医保类型
	private String ybno;// 医保号码
	private Date birthday;// 出生年月
	private String marriage;// 婚姻状况1未婚2已婚3离异4丧偶9未知
	private String fixphone;// 固定电话
	private String mobilephone;// 移动电话
	private String ipphone;// IP电话
	private String qqno;// QQ号码
	private String email;// 邮件
	private String zlcard;// 诊疗卡号
	@TableField("card_type")
	private String cardType;// 证件类型
	private String idcardno;// 身份证
	private String profession;// 职业
	private String nation;// 名族
	@TableField("nation_ref")
	private String natinonRef;
	private String country;// 国家
	@TableField("country_ref")
	private String countryRef;
	private String province;// 省份
	@TableField("province_ref")
	private String provinceRef;
	private String city;// 城市
	@TableField("city_ref")
	private String cityRef;//
	private String company;// 公司
	private String nativeplace;// 籍贯
	private String nativeaddr;// 户口地址
	private String addr;// 居住地址
	private String acceptsf;// 是否接受随访 1 接受2不接受
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	@TableField("sf_flag")
	private String sfFlag;// 是否失访 1失访 2没失访
	@TableField("sf_date")
	private String sfDate;// 失访时间
	private String hzly;// 患者来源
	private String remark;// 备注
	@TableField("out_id")
	private String outId;// 对接患者id

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getHzly() {
		return hzly;
	}

	public void setHzly(String hzly) {
		this.hzly = hzly;
	}

	public String getSfFlag() {
		return sfFlag;
	}

	public void setSfFlag(String sfFlag) {
		this.sfFlag = sfFlag;
	}

	public String getSfDate() {
		return sfDate;
	}

	public void setSfDate(String sfDate) {
		this.sfDate = sfDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHzno() {
		return hzno;
	}

	public void setHzno(String hzno) {
		this.hzno = hzno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIsaliave() {
		return isaliave;
	}

	public void setIsaliave(String isaliave) {
		this.isaliave = isaliave;
	}

	public Date getDeadtime() {
		return deadtime;
	}

	public void setDeadtime(Date deadtime) {
		this.deadtime = deadtime;
	}

	public String getDeadcause() {
		return deadcause;
	}

	public void setDeadcause(String deadcause) {
		this.deadcause = deadcause;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getYbtype() {
		return ybtype;
	}

	public void setYbtype(String ybtype) {
		this.ybtype = ybtype;
	}

	public String getYbno() {
		return ybno;
	}

	public void setYbno(String ybno) {
		this.ybno = ybno;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getFixphone() {
		return fixphone;
	}

	public void setFixphone(String fixphone) {
		this.fixphone = fixphone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getIpphone() {
		return ipphone;
	}

	public void setIpphone(String ipphone) {
		this.ipphone = ipphone;
	}

	public String getQqno() {
		return qqno;
	}

	public void setQqno(String qqno) {
		this.qqno = qqno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZlcard() {
		return zlcard;
	}

	public void setZlcard(String zlcard) {
		this.zlcard = zlcard;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNatinonRef() {
		return natinonRef;
	}

	public void setNatinonRef(String natinonRef) {
		this.natinonRef = natinonRef;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryRef() {
		return countryRef;
	}

	public void setCountryRef(String countryRef) {
		this.countryRef = countryRef;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceRef() {
		return provinceRef;
	}

	public void setProvinceRef(String provinceRef) {
		this.provinceRef = provinceRef;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityRef() {
		return cityRef;
	}

	public void setCityRef(String cityRef) {
		this.cityRef = cityRef;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getNativeaddr() {
		return nativeaddr;
	}

	public void setNativeaddr(String nativeaddr) {
		this.nativeaddr = nativeaddr;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAcceptsf() {
		return acceptsf;
	}

	public void setAcceptsf(String acceptsf) {
		this.acceptsf = acceptsf;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
