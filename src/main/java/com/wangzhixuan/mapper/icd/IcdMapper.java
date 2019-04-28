package com.wangzhixuan.mapper.icd;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.icd.Icd;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IcdMapper extends BaseMapper<Icd> {
    List<Icd> getIcdListByNode(@Param("nodeId") Long nodeId);

    void insertNodeAndCode(Map<String, Object> map);

    void deleteNodeAndIcd(@Param("nodeId") Long nodeId);
}
