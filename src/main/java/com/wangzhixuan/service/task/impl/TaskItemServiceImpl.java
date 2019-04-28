package com.wangzhixuan.service.task.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.task.TaskItemMapper;
import com.wangzhixuan.model.task.TaskItem;
import com.wangzhixuan.service.task.ITaskItemService;

@Service
public class TaskItemServiceImpl extends ServiceImpl<TaskItemMapper, TaskItem> implements ITaskItemService {
	@Override
	public TaskItem getLastTaskItem(String type, String status) throws SysException {
		EntityWrapper<TaskItem> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(type)) {
			wrapper.where("type={0}", type);
		}
		if (StringUtils.isNotBlank(status)) {
			wrapper.where("status={0}", status);
		}
		wrapper.orderBy("bo_st_time", false);
		List<TaskItem> tasks = selectList(wrapper);
		if (tasks == null || tasks.size() < 1) {
			return null;
		}
		return tasks.get(0);
	}

}
