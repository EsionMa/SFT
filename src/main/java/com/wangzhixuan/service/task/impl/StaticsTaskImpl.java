package com.wangzhixuan.service.task.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.mapper.task.StaticsTaskMapper;
import com.wangzhixuan.model.task.StaticsTask;
import com.wangzhixuan.service.task.IStaticsTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:16:12
 * @Date:2017/12/25 Description:统计跑批任务统计
 */
@Service
public class StaticsTaskImpl extends ServiceImpl<StaticsTaskMapper,StaticsTask> implements IStaticsTask {
    @Override
    public List<StaticsTask> getTasks(Integer status) {
        EntityWrapper<StaticsTask> wrapper = new EntityWrapper<>();
        if (status!=null) {
            wrapper.where("status={0}", status);
        }
        wrapper.orderBy("staTime", false);
        return selectList(wrapper);
    }

    @Override
    public List<StaticsTask> getByTime(Map<String, Object> map) {
        if (map.size()<1){
            throw  new SysException("传入条件不全");
        }
        EntityWrapper<StaticsTask> wrapper = new EntityWrapper<>();
        wrapper.where("create_time>={0}",map.get("staTime"));
        wrapper.where("create_time<={0}", DateUtils.getEndTimeByStaTime(map));
        return selectList(wrapper);
    }
}
