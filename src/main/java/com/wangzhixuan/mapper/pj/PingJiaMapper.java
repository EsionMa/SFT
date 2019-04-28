package com.wangzhixuan.mapper.pj;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.pj.PingJia;
import com.wangzhixuan.model.vo.EvaCountVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;

import java.util.List;
import java.util.Map;

public interface PingJiaMapper extends BaseMapper<PingJia> {
	/**
	 * 评价查询（分页）
	 * 
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findByCondition(Pagination page, Map<String, Object> map);

	/**
	 * 根据科室ID和时间查询
	 * 
	 * @param map
	 * @return
	 */
	List<PingJiaVo> findByDeptIdAndTime(Map<String,Object> map);
  /***
   *@author: Leslie
   *@Date 2018/1/31 10:47
   *@param: [map]
   *@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
   *@throws
   *@Description:评价类型查询
   */
	List<Map<String,Object>> findByPjlx(Map<String,Object> map);
   /**
	*@author: Leslie
	*@Date 2018/4/11 16:35
	*@param: [map]
	*@return java.util.List<com.wangzhixuan.model.vo.EvaCountVo>
	*@throws
	*@Description: 某段时间各个评价类型人数
	*/
   List<EvaCountVo> getEvaCount(Map<String, Object> map);
}
