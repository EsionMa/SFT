package com.wangzhixuan.mapper.statics;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.statics.OutStatics;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:15:59
 * @Date:2017/12/25 Description:
 */
public interface OutStaticsMapper extends BaseMapper<OutStatics>{
    /**
     * @param map
     * @return
     */
    List<OutStatics> getByCondition(Map<String, Object> map);
}
