package com.wangzhixuan.mapper.huanzhe;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.wangzhixuan.model.vo.huanzhe.HuanZheInfoVo;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;

public interface HuanZheInfoMapper extends BaseMapper<HuanZheInfo> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);

	/**
	 *  通过 hzno 和hzid 查询患者信息
	 * @param hzNo
	 * @param hzId
	 * @return
	 */
	HuanZheInfo selectByhzNoAndhzId(@Param("hzNo") String hzNo, @Param("hzId") Long hzId);

	/**
	 * @Author: wangjun
	 * @Description:患者 模糊查询
	 * @Date 2017/7/31 13:29
	 */
	List<HuanZheInfoVo> findByCondition(Map<String, Object> params);

	Map<String, Object> findByNum(@Param("mobilephone") String mobilephone);
  /**
   *@author: Leslie
   *@Date 2018/2/27 18:12
   *@param: [map]
   *@return java.lang.Integer
   *@throws
   *@Description:得到患者生日人数
   */
  Integer getBirthCount(Map<String, Object> map);
  /**
   *@author: Leslie
   *@Date 2018/2/27 18:35
   *@param: [page, map]
   *@return java.util.List<com.wangzhixuan.model.huanzhe.HuanZheInfo>
   *@throws
   *@Description:得到分页的当前生日患者
   */

	List<Map<String,Object>> findByQuery(Pagination page, HuanZheInfoVo vo);
}
