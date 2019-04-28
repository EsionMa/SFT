package com.wangzhixuan.service.task;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.model.task.TaskItem;

public interface ITaskItemService extends IService<TaskItem> {
	/**
	 * 获取最近一次任务情况
	 * 
	 * @param type 类型
	 * @param status 状态
	 * @return
	 * @throws SysException
	 */
	TaskItem getLastTaskItem(String type, String status) throws SysException;
}
