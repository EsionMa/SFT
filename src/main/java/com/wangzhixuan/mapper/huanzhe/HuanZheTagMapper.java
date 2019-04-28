package com.wangzhixuan.mapper.huanzhe;

import java.util.List;

import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.huanzhe.HuanZheTag;

public interface HuanZheTagMapper extends BaseMapper<HuanZheTag>{
	List<HuanZheTag> listByHzId(@Param("hzId")Long hzId);
	void deleteByHzId(@Param("hzId")Long hzId);

	List<HuanZheInfo> findByCode(@Param("code") String code);

	List<Long> findIdsByCode(@Param("tagsName") String tagsName);
}	
