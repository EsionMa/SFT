package com.wangzhixuan.mapper.wenjuan;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.wenjuan.TimuXuanXiang;

public interface TiMuXuanXiangMapper extends BaseMapper<TimuXuanXiang> {
	Long save(TimuXuanXiang tmxx);

	Integer deleteByTimuIdNotIn(@Param("tmId") Long tmId, @Param("xxIds") List<Long> xxIds);
    /**
	 *@author: Leslie
	 *@Description: 得到题目选项
	 *@Date 2018/1/19 16:32
	 *@param: [tmId]
	 *@return java.util.List<com.wangzhixuan.model.wenjuan.TimuXuanXiang>
	 *@throws
	 */
	List<TimuXuanXiang> listByTiMuId(@Param("tmId") Long tmId);
   /***
	*@author: Leslie
	*@Date 2018/1/26 18:04
	*@param: [xxId]
	*@return java.lang.Integer
	*@throws
	*@Description:根据选项id得到其所占比重
	*/
   Double selectPercent(@Param("xxId") Long xxId);
    /**
	 *@author: Leslie
	 *@Date 2018/3/20 15:06
	 *@param: [id]
	 *@return void
	 *@throws
	 *@Description: 根据题目id删除题目选项
	 */
	void deleteTmxxByTiMuId(@Param("id") Long id);
}
