package com.wangzhixuan.mapper.collection;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.collection.CollectionTableItem;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:03
 * @Date:2018/3/1 Description:
 */
public interface CollectionTableItemMapper extends BaseMapper<CollectionTableItem> {

	/**
	 * 根据采集表ID获取采集项
	 * 
	 * @param tableId
	 * @return
	 */
	List<CollectionTableItem> selectItemsByTableId(@Param("tableId") Long tableId);
}
