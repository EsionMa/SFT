package com.wangzhixuan.mapper.scale;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.scale.ScaleTable;
import com.wangzhixuan.model.vo.scale.ScaleTableVo;

/**
 * 
 * @author Esion
 *
 */
public interface ScaleTableMapper extends BaseMapper<ScaleTable> {
	/**
	 * 搜索+分页
	 * 
	 * @param page
	 * @param vo
	 * @return
	 */
	List<Map<String, Object>> selectPage(Pagination page, ScaleTableVo vo);
}
