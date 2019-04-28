package com.wangzhixuan.model.task;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_task_item")
public class TaskItem implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -8320715248009843823L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("task_id")
	private Long taskId;
	private String type;// 任务类型 1.科室信息 2.字典区域信息 3.医护人员信息 4.患者信息 5.住院信息
	private String status;// 当前任务状态 1成功2失败3部分失败
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

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
