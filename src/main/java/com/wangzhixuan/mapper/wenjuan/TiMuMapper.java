package com.wangzhixuan.mapper.wenjuan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.wenjuan.TiMu;

public interface TiMuMapper extends BaseMapper<TiMu>{
	List<Map<String,Object>> selectPage(Pagination page, Map<String, Object> params);
	Long save(TiMu timu);
	/***
	 *@author: Leslie
	 *@Date 2018/1/24 17:45
	 *@param: [wjId]
	 *@return java.util.List<com.wangzhixuan.model.wenjuan.TiMu>
	 *@throws
	 *@Description:列出某个问卷的选择题
	 */
	List<TiMu> listChoseByWjId(@Param("wjId")Long wjId);
	//问卷模糊查询
	List<Map<String,Object>> findByCondition(Pagination page, Map<String, Object> params);
     /***
	  *@author: Leslie
	  *@Date 2018/1/24 17:43
	  *@param: [wjId]
	  *@return java.lang.Integer
	  *@throws
	  *@Description:某个问卷有多少题
	  */
	 Integer tmCountByWj(@Param("wjId") Long wjId);

    Double selectScore(@Param("tmId") Long tmId);
}
