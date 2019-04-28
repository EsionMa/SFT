package com.wangzhixuan.service.statics;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.mapper.statics.OutStaticsMapper;
import com.wangzhixuan.model.statics.OutStatics;
import com.wangzhixuan.model.vo.statics.OutStaticsVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:15:58
 * @Date:2017/12/25 Description:
 */
public interface IOutStaticsService  extends IService<OutStatics>{

    /**
     * 保存
     */
    void save(Map<String, Object> map, Date date);


    List<OutStatics> get(Map<String, Object> map);

    void exeTj();
}
