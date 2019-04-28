package com.wangzhixuan.mapper.fa;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.fa.FangAnDingYiItem;
import com.wangzhixuan.model.vo.fa.FangAnDingYiItemVo;

public interface FangAnDingYiItemMapper extends BaseMapper<FangAnDingYiItem> {
	/**
	 * 根据方案ID查询方案 item
	 * 
	 * @param faId
	 * @return
	 */
	List<FangAnDingYiItemVo> findByFangAnId(@Param("faId") Long faId);

	/**
	 * 刪除之前的方案item
	 * @param faId
	 * @param ids
	 */
	void deleteByFaIdAndIdNotIn(@Param("faId") Long faId, @Param("ids") List<Long> ids);

	/**
	 * 添加方案执行人
	 * 
	 * @param itemId
	 * @param exeUserId
	 * @return
	 */
	Long insertExeUsers(@Param("itemId") Long itemId, @Param("exeUserId") Long exeUserId);

	/**
	 * 查询方案item中的执行人
	 * 
	 * @param itemId
	 * @return
	 */
	List<Map<String, Object>> selectExeUsers(@Param("itemId") Long itemId);

	/**
	 * 根据方案itemId删除执行人
	 * 
	 * @param itemId
	 */
	void deleteExeUserByItemId(@Param("itemId") Long itemId);

	/**
	 * 添加方案问卷
	 * 
	 * @param itemId
	 * @param wjId
	 * @return
	 */
	Long insertWenJuan(@Param("itemId") Long itemId, @Param("wjId") Long wjId);

	/**
	 * 根据方案itemId删除问卷
	 * 
	 * @param itemId
	 */
	void deleteWenJuanByItemId(@Param("itemId") Long itemId);

	/**
	 * 更新方案item中的问卷
	 * 
	 * @param wjId
	 * @param beforeId
	 */
	void updateWenJuanByItemId(@Param("wjId") Long wjId, @Param("beforeId") Long beforeId);

	/**
	 * 查询方案item中的问卷
	 * 
	 * @param itemId
	 * @return
	 */
	List<Map<String, Object>> selectWenJuans(@Param("itemId") Long itemId);
}
