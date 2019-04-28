package com.wangzhixuan.mapper.collection;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.collection.CollectionItem;
import com.wangzhixuan.model.vo.collection.CollectionItemVo;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:03
 * @Date:2018/3/1 Description:
 */
public interface CollectionItemMapper extends BaseMapper<CollectionItem> {
	/**
	 * 搜索+分页
	 * 
	 * @param page
	 * @param vo
	 * @return
	 */
	List<Map<String, Object>> selectPage(Pagination page, CollectionItemVo vo);

	void addItem(@Param("id") Long id, @Param("list") List<Long> list);

}
