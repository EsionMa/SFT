package com.wangzhixuan.service.task;

import java.util.Date;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.model.task.Task;

public interface ITaskService extends IService<Task> {
	/**
	 * 获取最近一次任务信息
	 * 
	 * @param status 状态
	 * @return
	 * @throws SysException
	 */
	Task getLastTask(String status) throws SysException;

	/**
	 * 获取失败任务信息
	 * 
	 * @param boStTime 业务开始时间
	 * @return
	 * @throws SysException
	 */
	Task getTask(Date boStTime) throws SysException;

	/**
	 * 前端专用跑批信息
	 * @return
	 */
	RespResult<String> export();
}
