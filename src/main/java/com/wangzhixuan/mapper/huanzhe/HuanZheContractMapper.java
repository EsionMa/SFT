package com.wangzhixuan.mapper.huanzhe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.vo.huanzhe.HuanZheContractVo;

public interface HuanZheContractMapper extends BaseMapper<HuanZheContract>{
	List<Map<String,Object>> selectPage(Pagination page, Map<String, Object> params);
	void deleteByHzId(Long hzId);
	List<HuanZheContractVo> selectByHzId(@Param("hzId")Long hzId);
}	
