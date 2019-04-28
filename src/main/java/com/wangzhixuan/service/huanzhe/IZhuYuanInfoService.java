package com.wangzhixuan.service.huanzhe;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;
import com.wangzhixuan.service.huanzhe.query.ZyConditionQuery;

public interface IZhuYuanInfoService extends IService<ZhuYuanInfo> {
	/**
	 *  查询住院信息
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	PageInfo selectDataGrid(ZhuYuanInfoVo vo) throws SysException;

	/**
	 * 	新增或更新
	 * @param vo
	 * @param user
	 * @return
	 * @throws SysException
	 */
	ZhuYuanInfoVo saveOrUpdate(ZhuYuanInfoVo vo,  ShiroUser user) throws SysException;

	/**
	 *  根据患者id查询患者信息
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	ZhuYuanInfoVo selectDetailById(ZhuYuanInfoVo vo) throws SysException;

	/**
	 *
	 * @param query
	 * @return
	 * @throws SysException
	 *
	 *	未使用
	 */
	List<ZhuYuanInfoVo> findByCondition(ZyConditionQuery query) throws SysException;

	/**
	 * 删除住院信息
	 * @param id
	 * @throws SysException
	 */
	void delete(Long id) throws SysException;

	/**
	 * 根据群组条件筛选住院患者（分页）
	 * 
	 * @param group
	 * @param nowPage
	 * @param pageSize
	 * @return
	 * @throws SysException
	 */
	PageInfo findByGroup(FangAnZhuYuanGroupVo group, Integer nowPage, Integer pageSize) throws SysException;
    /**
	 *@author: Leslie
	 *@Date 2018/2/27 11:42
	 *@param: [pageInfo]
	 *@return com.wangzhixuan.commons.result.PageInfo
	 *@throws
	 *@Description:接口已废弃
	 */
	PageInfo findByCondition(PageInfo pageInfo) throws SysException;

	/**
	 * 批量插入住院信息，返回ID数组
	 * 
	 * @param zhuYuanInfos
	 * @return
	 * @throws SysException
	 */
	List<Long> insertZyInfoBatch(List<ZhuYuanInfo> zhuYuanInfos) throws SysException;


	/**
	 * 前端首页
	 * @return
	 */
	RespResult<Integer> export();
   /**
	*@author: Leslie
	*@Date 2018/2/1 15:27
	*@param: [map]
	*@return java.util.Map<java.lang.String,java.lang.Object>
	*@throws
	*@Description:根据出院时间和患者id进行查询其住院信息
	*/
   Map<String,Object> getByTimeAndId(Map<String, Object> map);
}
