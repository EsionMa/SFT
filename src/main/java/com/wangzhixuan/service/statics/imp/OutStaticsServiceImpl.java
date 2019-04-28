package com.wangzhixuan.service.statics.imp;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.statics.OutStaticsMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.statics.OutStatics;
import com.wangzhixuan.model.task.StaticsTask;
import com.wangzhixuan.model.vo.wj.SuiFangTongJiVo;
import com.wangzhixuan.service.ISysOrganizationService;
import com.wangzhixuan.service.statics.IOutStaticsService;
import com.wangzhixuan.service.task.IStaticsTask;
import com.wangzhixuan.service.tj.ITongjiService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:16:01
 * @Date:2017/12/25 Description:统计对象实现层
 */
@Service
public class OutStaticsServiceImpl extends ServiceImpl<OutStaticsMapper, OutStatics> implements IOutStaticsService {
    @Autowired
    private OutStaticsMapper staticMapper;
    @Autowired
    private ITongjiService tongjiService;
    @Autowired
    private ISysOrganizationService organizationService;
    @Autowired
    private IStaticsTask staticTask;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void save(Map<String, Object> map, Date date) {
        SuiFangTongJiVo tongJiVos = tongjiService.sfCount(map);
        if (tongJiVos.getVos().size() > 1) {
            for (OutStatics item : tongJiVos.getVos()) {
                item.setUpdateTime(new Date());
                // 因为要跑批以前没有跑的，以每次跑批的筛选时间，下个月1号减去15天
                item.setCyTime(DateUtils.getFrontDay(date, 15));
                staticMapper.insert(item);
            }
        }
    }

    /**
     * 科室查询
     *
     * @param map
     * @return
     */
    @Override
    public List<OutStatics> get(Map<String, Object> map) {
        DateUtils.getEndTimeByStaTime(map);
        List<OutStatics> vos;
        if (map.get("deptId") == null || StringUtils.isBlank(map.get("deptId").toString())) {
            EntityWrapper<OutStatics> wrapper = new EntityWrapper<>();
            if (map.get("staTime") != null) {
                wrapper.where("cytime>={0}", map.get("staTime"));
            }
            if (map.get("endTime") != null) {
                wrapper.where("cytime<{0}", map.get("endTime"));
            }
            vos = selectList(wrapper);
            // // 查询总计
            // vos = staticMapper.getByCondition(map);
            // // 迭代科室
            // // TODO 此处迭代科室根据医院类型 type 的
            // List<SysOrganization> orgs = organizationService.getDepts(null,
            // "2");
            // for (SysOrganization item : orgs) {
            // map.put("deptId", item.getId());
            // List<OutStatics> leaveVos = staticMapper.getByCondition(map);
            // if (leaveVos.size() == 1) {
            // vos.add(leaveVos.get(0));
            // }
            // }
        } else {
            vos = staticMapper.getByCondition(map);
        }
        return vos;
    }

    @Override
    public void exeTj() {
        // 查询所有错误的任务
        List<StaticsTask> monthTask = staticTask.getTasks(3);
        logger.debug("错误的月跑批任务为{}", monthTask.size());
        // 出现未成功的任务后，循环执行所有错误任务
        exeErrorStaticsTask(monthTask);
        // 查询将要执行的查询任务，循环贷执行任务
        List<StaticsTask> exeTasks = staticTask.getTasks(0);
        exePlanStaticsTask(exeTasks);

        // 查询本月是否已经有任务创建，如果有，则不创建，无，则创建
        Map<String, Object> map = new HashMap<>();
        // 本月开始时间和结束时间
        map.put("staTime", DateUtils.getBeginDayOfMonth());
        map.put("endTime", DateUtils.getEndDayOfMonth());
        // 若未0，代表还未创建，那么创建当月计划
        if (staticTask.getByTime(map).size() == 0) {
            StaticsTask task = new StaticsTask();
            task.setStatus(0);
            task.setType(1);
            task.setCreateTime(new Date());
            // 创建当月计划,本月开始时间和结束时间
            task.setStaTime(DateUtils.getBeginDayOfMonth());
            task.setEndTime(DateUtils.getEndDayOfMonth());
            logger.debug("创建当月统计跑批任务");
            staticTask.insert(task);
        } else {
            logger.debug("已创建当月任务，无需重复创建");
        }
    }

    @SuppressWarnings(value = "unchecked")
    public void exePlanStaticsTask(List<StaticsTask> tasks) {
        if (tasks.size() != 0) {
            for (StaticsTask item : tasks) {
                // 得到筛选条件结束时间，因为是每月1日执行 和上月结束时间相差小于24小时，new
                // Date()和这个结束时间相差小于24个小时
                Date endTime = item.getEndTime();
                Date nowTime = new Date();
                int days = DateUtils.getDiffDays(endTime, nowTime);
                if (days > 0) {
                    logger.debug("与预计执行时间差为{}天", -days);
                    Map<String, Object> map = new HashedMap();
                    map.put("staTime", item.getStaTime());
                    map.put("endTime", item.getEndTime());
                    try {
                        item.setType(1);
                        item.setExeStaTime(new Date());
                        save(map, item.getEndTime());
                        item.setExeEndTime(new Date());
                        item.setStatus(2);
                        logger.debug("执行统计跑批任务");
                    } catch (RuntimeException e) {
                        item.setStatus(3);
                        logger.debug("执行计划任务id为{}出现错误 ，错误为{}", item.getId(), e);
                    } finally {
                        staticTask.updateById(item);
                        continue;
                    }

                } else {
                    logger.debug("离还要执行任务的时间为{}天", -days);
                }
            }
        }
    }

    /**
     * 执行错误跑批任务
     *
     * @param tasks
     */
    public void exeErrorStaticsTask(List<StaticsTask> tasks) {
        logger.debug("共有{}个错误跑批任务", tasks.size());
        if (tasks.size() != 0) {
            for (StaticsTask item : tasks) {
                Map<String, Object> map = new HashMap<>();
                map.put("staTime", item.getStaTime());
                map.put("endTime", item.getEndTime());
                try {
                    item.setExeStaTime(new Date());
                    item.setType(1);
                    save(map, item.getEndTime());
                    item.setExeEndTime(new Date());
                    item.setStatus(2);
                } catch (RuntimeException e) {
                    item.setStatus(3);
                    logger.debug("执行id为{}任务继续出现错误，异常信息{}", item.getId(), e);
                } finally {
                    staticTask.updateById(item);
                    continue;
                }

            }
        } else {
            logger.debug("无错误统计任务");
        }
    }
}
