package com.wangzhixuan.mapper.scale;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.scale.ScaleCollection;

/**
 * 
 * @author Esion
 *
 */
public interface ScaleCollectionMapper extends BaseMapper<ScaleCollection> {

	/**
	 * 根据量表ID获取采集表
	 * 
	 * @param scaleId
	 * @return
	 */
	List<ScaleCollection> selectColectionsByScaleId(@Param("scaleId") Long scaleId);
}
