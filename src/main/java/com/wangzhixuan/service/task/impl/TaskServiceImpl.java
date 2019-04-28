package com.wangzhixuan.service.task.impl;

import java.util.Date;
import java.util.List;

import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.task.TaskMapper;
import com.wangzhixuan.model.task.Task;
import com.wangzhixuan.service.task.ITaskService;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
	@Autowired
	private IZhuYuanInfoService zhuYuanInfoService;
	@Override
	public Task getLastTask(String status) throws SysException {
		EntityWrapper<Task> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(status)) {
			wrapper.where("status={0}", status);
		}
		wrapper.orderBy("bo_st_time", false);
		List<Task> tasks = selectList(wrapper);
		if (tasks == null || tasks.size() < 1) {
			return null;
		}
		return tasks.get(0);
	}

	@Override
	public Task getTask(Date boStTime) throws SysException {
		EntityWrapper<Task> wrapper = new EntityWrapper<>();
		wrapper.where("bo_st_time={0}", boStTime);
		Task task = selectOne(wrapper);
		return task;
	}
	/**
	 * 查询任务状态
	 * @return
	 */
	@Override
	public RespResult<String> export() {
		RespResult<String> result=new RespResult<>();
		EntityWrapper<Task> wrapper = new EntityWrapper<>();
		wrapper.where("bo_st_time>={0}", DateUtils.getBeginDayOfYesterday());
		wrapper.where("bo_ed_time<={0}",DateUtils.getDayBegin());
		List<Task> tasks=selectList(wrapper);
		if (tasks.size()==0){
			result.getSuccess("无昨日相关导出任务，请联系工程师进行检查");
		}else if (tasks.size()==1){
			if ("1".equals(tasks.get(0).getStatus())){
				result.getSuccess("正在进行导出相关任务");
			}else if ("2".equals(tasks.get(0).getStatus())){
				result.getSuccess("导入数据成功,昨日共计出院病人"+zhuYuanInfoService.export().getData()+"人");
			}else {
				result.getSuccess("执行任务失败，请联系工程师");
			}
		}else {
			result.getSuccess("创建了多个任务，请联系工程师");
		}
		return result;
	}

}
