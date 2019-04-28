package com.wangzhixuan.service.task;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.model.task.StaticsTask;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:16:10
 * @Date:2017/12/25 Description:
 */
public interface IStaticsTask extends IService<StaticsTask>{
    List<StaticsTask> getTasks(Integer status);

    List<StaticsTask> getByTime(Map<String, Object> map);
}
