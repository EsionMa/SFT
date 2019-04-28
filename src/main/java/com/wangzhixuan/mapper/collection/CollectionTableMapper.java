package com.wangzhixuan.mapper.collection;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.collection.CollectionTable;
import com.wangzhixuan.model.vo.collection.CollectionTableVo;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:03
 * @Date:2018/3/1 Description:
 */
public interface CollectionTableMapper extends BaseMapper<CollectionTable> {
	/**
	 * 搜索+分页
	 * 
	 * @param page
	 * @param vo
	 * @return
	 */
	List<Map<String, Object>> selectPage(Pagination page, CollectionTableVo vo);

	void addItem(@Param("ids") List<Long> ids, @Param("id") Long id);

	/**
	 * @author: Leslie
	 * @Date 2018/3/16 11:01
	 * @param: [tableId]
	 * @return java.util.List<java.lang.Long>
	 * @throws @Description:
	 *             通采集单id获取一堆采集项id
	 */
	List<Long> getTypeIdsByTableId(@Param("tableId") Long tableId);
}
