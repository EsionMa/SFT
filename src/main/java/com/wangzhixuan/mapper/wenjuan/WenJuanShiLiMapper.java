package com.wangzhixuan.mapper.wenjuan;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WenJuanShiLiMapper extends BaseMapper<WenJuanShiLi> {
	Long save(WenJuanShiLiVo vo);

	Integer disableAllWeiDiaoCha(@Param("faId") Long faId);

	List<Map<String, Object>> findByCondition(Pagination pagination, Map<String, Object> map);

	List<Long> queryItemIdByExeUserId(@Param("exeUserId") Long exeUserId);

	/**
	 * 离院时间统计
	 * 
	 * @param map
	 * @return
	 */
	Integer leaveCount(Map<String, Object> map);

	/**
	 * 随访状态统计
	 * 
	 * @param map
	 * @return
	 */
	Integer sfCount(Map<String, Object> map);

	/**
	 * 所有随访
	 * 
	 * @param map
	 * @return
	 */
	Integer allCount(Map<String, Object> map);

	/**
	 * 用户随访状态统计
	 *
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> userSfCount(Map<String, Object> map);

	/**
	 * 用户所有随访
	 *
	 * @param map
	 * @return
	 */
	Integer userAllCount(Map<String, Object> map);

	/**
	 * 采用group by 进行数量查询
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> statusCount(Map<String, Object> map);

	// 今日将要随访的人数
	Integer plan(Map<String, Object> map);

	Integer already(Map<String, Object> map);

	/**
	 * @author: Leslie
	 * @Date 2018/2/27 14:41
	 * @param: [map]
	 * @return java.lang.Integer
	 * @throws @Description:根据计划时间算出用户需要进行的随访
	 */
	Integer homeCount(Map<String, Object> map);

	/**
	 * 根据暂停时间查询一条问卷实例
	 * 
	 * @param map
	 * @return
	 */
	WenJuanShiLi selectOneByPauseTime(@Param("faType") Integer faType, @Param("pauseTime") Date pauseTime);
   /**
	*@author: Leslie
	*@Date 2018/3/28 15:41
	*@param: [id]
	*@return void
	*@throws
	*@Description: 根据方案id删除问卷实例
	*/
	void deleteAllShiLi(@Param("id") Long id);
}
