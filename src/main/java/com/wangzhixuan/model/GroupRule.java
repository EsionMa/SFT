package com.wangzhixuan.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_hz_group_rule")
public class GroupRule implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -2261417890760477799L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String hzly;// 患者来源 1门诊，2住院...0 不限
	private String jzks;// 就诊科室，一对多。组织表id，用'',''分开
	private String bz;// 病种，暂时不做
	private String zd;// 诊断，暂时不做
	private String hzxb;// 患者性别，0女1男2所有
	private Integer zxhznl;// 最小患者年龄，大于等于0的整数，为空则为0
	private Integer zdhznl;// 最大患者年龄,最大默认254
	private Integer sxsjlx;// 1就诊开始时间，2就诊结束时间
	private Date zxsxsj;// 最小筛选时间，空为无限制
	private Date zdsxsj;// 最大筛选时间,空为无限制
	private String rule_group;// 规则所属组1常规患者组，1专科患者组，2妇科患者组，3慢性别患者组
	private String statue;// 状态 1启用0禁用
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHzly() {
		return hzly;
	}

	public void setHzly(String hzly) {
		this.hzly = hzly;
	}

	public String getJzks() {
		return jzks;
	}

	public void setJzks(String jzks) {
		this.jzks = jzks;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getHzxb() {
		return hzxb;
	}

	public void setHzxb(String hzxb) {
		this.hzxb = hzxb;
	}

	public Integer getZxhznl() {
		return zxhznl;
	}

	public void setZxhznl(Integer zxhznl) {
		this.zxhznl = zxhznl;
	}

	public Integer getZdhznl() {
		return zdhznl;
	}

	public void setZdhznl(Integer zdhznl) {
		this.zdhznl = zdhznl;
	}

	public Integer getSxsjlx() {
		return sxsjlx;
	}

	public void setSxsjlx(Integer sxsjlx) {
		this.sxsjlx = sxsjlx;
	}

	public Date getZxsxsj() {
		return zxsxsj;
	}

	public void setZxsxsj(Date zxsxsj) {
		this.zxsxsj = zxsxsj;
	}

	public Date getZdsxsj() {
		return zdsxsj;
	}

	public void setZdsxsj(Date zdsxsj) {
		this.zdsxsj = zdsxsj;
	}

	public String getRule_group() {
		return rule_group;
	}

	public void setRule_group(String rule_group) {
		this.rule_group = rule_group;
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

}
