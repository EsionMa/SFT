package com.wangzhixuan.mapper.wenjuan;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.wenjuan.WenJuan;

public interface WenJuanMapper extends BaseMapper<WenJuan> {
	/**
	 * 查询问卷（分页）
	 * 
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);

	/**
	 *
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findByWjzt(Pagination page, Map<String, Object> params);

}
