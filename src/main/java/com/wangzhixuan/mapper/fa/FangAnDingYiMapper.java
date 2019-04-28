package com.wangzhixuan.mapper.fa;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.fa.FangAnDingYi;
import org.apache.ibatis.annotations.Param;

public interface FangAnDingYiMapper extends BaseMapper<FangAnDingYi> {
    String findDeptIdsByFaId(@Param("faId") Long faId);
    // List<FangAnDingYi> findAll();
}
