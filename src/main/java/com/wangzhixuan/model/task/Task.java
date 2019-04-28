package com.wangzhixuan.model.task;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_task")
public class Task implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 523232221444569788L;
	@TableId(type = IdType.AUTO)
	private Long id;
	private String status;// 当前任务状态 0计划执行，1成功（完成），2失败，3进行中
	@TableField("exe_st_time")
	private Date exeStTime;// 执行开始时间
	@TableField("exe_ed_time")
	private Date exeEdTime;// 执行结束时间
	@TableField("bo_st_time")
	private Date boStTime;// 业务开始时间
	@TableField("bo_ed_time")
	private Date boEdTime;// 业务结束时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExeStTime() {
		return exeStTime;
	}

	public void setExeStTime(Date exeStTime) {
		this.exeStTime = exeStTime;
	}

	public Date getExeEdTime() {
		return exeEdTime;
	}

	public void setExeEdTime(Date exeEdTime) {
		this.exeEdTime = exeEdTime;
	}

	public Date getBoStTime() {
		return boStTime;
	}

	public void setBoStTime(Date boStTime) {
		this.boStTime = boStTime;
	}

	public Date getBoEdTime() {
		return boEdTime;
	}

	public void setBoEdTime(Date boEdTime) {
		this.boEdTime = boEdTime;
	}

}
