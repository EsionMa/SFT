package com.wangzhixuan.model.wenjuan;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_tm_sl")
public class TiMuShiLi implements Serializable {
	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 6153720607092287172L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("tm_id") // 题目ID
	private Long tmId;
	@TableField("xx_id") // 选项ID
	private Long xxId;
	@TableField("tm_bt") // 题目标题
	private String tmBt;
	@TableField("tm_fl") // 题目分类
	private String tmFl;
	private String txxz;// 题型选择（1单选，2多选，3填空）
	private String dtnr;// 答题内容
	@TableField("wj_sl_id")
	private Long wjSlId;// 问卷实例ID
	@TableField("wj_id")
	private Long wjId;// 问卷ID
	@TableField("cy_time")
	private Date cyTime;//出院时间
	@TableField("doctor_id")
	private Long doctorId;
	@TableField("dept_id")
	private Long deptId;
	@TableField("create_time")
	private Date createTime;// 创建时间
	@TableField("update_time")
	private Date updateTime;// 更新时间
	@TableField("create_user_id")
	private Long createUserId;// 创建用户Id
	@TableField("update_user_id")
	private Long updateUserId;// 更新用户Id
	/**
	 * 得分
	 */
	private Double score;
	/**
	 * 用于区分该题目究竟是哪个问卷而来
	 * 当同一患者，选择同一问卷进行抽查，多次抽查任务进行进入后，所有题目来自同一个问卷实例，同一问卷，造成无法区分
	 */
	@TableField("wenjuan_item_id")
	private Long wenjuanItemId;


	public Long getWenjuanItemId() {
		return wenjuanItemId;
	}

	public void setWenjuanItemId(Long wenjuanItemId) {
		this.wenjuanItemId = wenjuanItemId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getWjId() {
		return wjId;
	}

	public void setWjId(Long wjId) {
		this.wjId = wjId;
	}

	public Long getWjSlId() {
		return wjSlId;
	}

	public void setWjSlId(Long wjSlId) {
		this.wjSlId = wjSlId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTmId() {
		return tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	public Long getXxId() {
		return xxId;
	}

	public void setXxId(Long xxId) {
		this.xxId = xxId;
	}

	public String getTmBt() {
		return tmBt;
	}

	public void setTmBt(String tmBt) {
		this.tmBt = tmBt;
	}

	public String getTmFl() {
		return tmFl;
	}

	public void setTmFl(String tmFl) {
		this.tmFl = tmFl;
	}

	public String getTxxz() {
		return txxz;
	}

	public void setTxxz(String txxz) {
		this.txxz = txxz;
	}

	public String getDtnr() {
		return dtnr;
	}

	public void setDtnr(String dtnr) {
		this.dtnr = dtnr;
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

	public Date getCyTime() {
		return cyTime;
	}

	public void setCyTime(Date cyTime) {
		this.cyTime = cyTime;
	}
}
