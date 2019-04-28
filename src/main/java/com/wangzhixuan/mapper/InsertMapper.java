package com.wangzhixuan.mapper;

import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/8/28.
 * TIME:15:49
 * Date:2017/8/28.
 * Description:
 */
public interface InsertMapper {
    void insertHospitalization(List<ZhuYuanInfo> maps);
    void insertPatients(HuanZheInfo infos);
}
