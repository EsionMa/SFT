package com.wangzhixuan.service.wenjuan;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.vo.fa.FangAnDingYiItemVo;
import com.wangzhixuan.model.vo.wj.SatisfactionVo;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;

public interface IWenJuanShiLiService extends IService<WenJuanShiLi> {

	void saveOrUpdate(WenJuanShiLiVo vo, ShiroUser shiroUser);

	/**
	 * 根据方案、内容创建实例
	 * 
	 * @param faId
	 * @param fangAnDingYiItemVo
	 * @param infos
	 */
	void creteShiLiByFangAn(Long faId, FangAnDingYiItemVo fangAnDingYiItemVo, List<Long> infos);

	/**
	 * 删除暂停当天之后的计划任务
	 * 
	 * @param faType
	 * @param pauseTime
	 */
	void deleteShiLiByPauseTime(Integer faType, Date pauseTime);

	/**
	 *  查询当前用户的问卷实例
	 * @param pageInfo
	 * @param user
	 * @return
	 * @throws SysException
	 */
	PageInfo selectDateGrid(PageInfo pageInfo, ShiroUser user) throws SysException;

	WenJuanShiLiVo queryDetailById(Long id) throws SysException;

	PageInfo findByCondition(PageInfo pageInfo);

	/**
	 * 日统计计划随访
	 * 
	 * @return
	 */
	RespResult<Integer> plan(Map<String, Object> map, ShiroUser user);

	/**
	 * 日统计已随访
	 * 
	 * @return
	 */
	RespResult<Integer> already(Map<String, Object> map, ShiroUser user);

	/***
	 * @author: Leslie
	 * @Date 2018/1/24 10:33
	 * @param: [wjId,
	 *             wjslId, wjzt]
	 * @return com.wangzhixuan.model.vo.WenJuanVo
	 * @throws @Description:简单封装下问卷装题这种
	 */
	WenJuanVo getWenJuanVo(Long wjId, Long wjslId, String wjzt, Long itemId);

	/**
	 * 暂停后，根据otherTime倒序查询一条问卷实例
	 * 
	 * @param faType
	 * @param pauseTime
	 * @return
	 */
	WenJuanShiLi selectOneAfterPauseByOtherTimeDesc(Integer faType, Date pauseTime);

	/**
	 * @author: Leslie
	 * @Date 2018/2/1 16:59
	 * @param: [map]
	 * @return com.wangzhixuan.model.vo.wj.SatisfactionVo
	 * @throws @Description:获取随访细节
	 */
	SatisfactionVo getSatisfaction(Map<String, Object> map);

	/**
	 * @author: Leslie
	 * @Date 2018/2/27 14:21
	 * @param: [map]
	 * @return com.wangzhixuan.commons.result.RespResult<java.lang.Integer>
	 * @throws @Description:根据日期类型和随访类型查询数据
	 */
	RespResult<Map<String, Object>> getCountByDate(Map<String, Object> map);
    /**
	 *@author: Leslie
	 *@Date 2018/3/28 15:38
	 *@param: [id]
	 *@return void
	 *@throws
	 *@Description: 通过方案ID来删除问卷实例（未完成的问卷实例）
	 */
	void deleteSHiLiByFaId(Long id);
}
