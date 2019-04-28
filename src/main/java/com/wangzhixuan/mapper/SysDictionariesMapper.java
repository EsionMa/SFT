package com.wangzhixuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysDictionaries;

/**
 *
 * Organization 表数据库控制层接口
 *
 */
public interface SysDictionariesMapper extends BaseMapper<SysDictionaries> {
	void save(SysDictionaries sysDictionaries);
	List<SysDictionaries> listByParentCode(@Param("parentCode")String parentCode);
	SysDictionaries listByCode(@Param("code")String code);
}