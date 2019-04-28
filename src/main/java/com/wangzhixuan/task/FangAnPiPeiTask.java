package com.wangzhixuan.task;

import java.util.Date;
import java.util.List;

import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import com.wangzhixuan.service.sqlserver.IMySqlService;
import com.wangzhixuan.service.sqlserver.IOracleService;
import com.wangzhixuan.service.statics.IOutStaticsService;
import com.wangzhixuan.service.task.IStaticsTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.task.Task;
import com.wangzhixuan.service.task.ITaskService;

/**
 * spring task 定时任务测试，适用于单系统 注意：不适合用于集群
 *
 * @author L.cm
 */
@Component
public class FangAnPiPeiTask {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IOracleService oracleServive;
	@Autowired
	private IMySqlService mySqlService;
	@Autowired
	private IOutStaticsService staticsService;
	@Autowired
	private IStaticsTask task;

	@Scheduled(cron = "0 30 0 * * ?")
	public void cronTest() {
		logger.debug("执行定时任务，获取用户数据，并且匹配用户");
		logger.debug("任务执行时间为{}", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 第一步，如果有计划执行任务，则先执行计划执行任务（昨天）
		// 今天
		Date thisDay = new Date();
		// 最近一次计划执行任务
		Task lastTask = taskService.getLastTask("0");
		// 判断是否存在计划任务
		if (lastTask != null) {
			// 执行昨天任务
			exeTask(lastTask);
		}

		// 创建今天任务
		Task thisTask = new Task();
		// 状态更新为未执行
		thisTask.setStatus("0");
		thisTask.setBoStTime(DateUtils.getDayBegin(thisDay));
		thisTask.setBoEdTime(DateUtils.getDayEnd(thisDay));
		taskService.insert(thisTask);
		logger.debug("日常数据拉取任务执行完毕时间为{}", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		logger.debug("开始执行月统计任务");
		staticsService.exeTj();

	}

	public void exeTask(Task task) {
		logger.debug("执行任务:{}", task);
		// 任务进行中
		task.setStatus("1");
		task.setExeStTime(new Date());
		// 状态更新为执行中
		taskService.updateById(task);
		PatientQuery patientQuery=new PatientQuery();
		patientQuery.setBoStTime(task.getBoStTime());
		patientQuery.setBoEdTime(task.getExeEdTime());
		// 获取所有患者信息，并保存
		List<HuanZheInfo> huanZheInfos = oracleServive.getAllPatients(patientQuery);
		if (huanZheInfos.size() > 0) {
			mySqlService.insertHuanzhe(huanZheInfos, null);
		}
		// 读取患者联系人
		List<HuanZheContract> contracts = oracleServive.getAllContract(patientQuery);
		mySqlService.insertConTracs(contracts, null);
		// 获取所有住院信息，并保存
		List<ZhuYuanInfo> zhuYuanInfos = oracleServive.getAllHos(patientQuery);
		if (zhuYuanInfos.size() > 0) {
			mySqlService.insertZhuyuans(zhuYuanInfos, null);
		}

		// 执行成功
		task.setStatus("2");
		task.setExeEdTime(new Date());
		// 状态更新为成功
		taskService.updateById(task);
		logger.debug("执行任务结束:{}", task);
	}

}
