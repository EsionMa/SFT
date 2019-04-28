package com.wangzhixuan.service.wenjuan;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.vo.TiMuVo;
import com.wangzhixuan.model.wenjuan.TiMu;

public interface ITiMuService extends IService<TiMu> {
	/**
	 * 查询所有题目
	 * @param pageInfo
	 * @return
	 */
	PageInfo selectDataGrid(PageInfo pageInfo);

	/**
	 * 保存或更新题目
	 * @param vo
	 * @param user
	 * @throws SysException
	 */
	void saveOrUpdate(TiMuVo vo, ShiroUser user) throws SysException;

	/**
	 *  删除题目
	 * @param vo
	 * @param user
	 * @throws SysException
	 */
	void delete(TiMuVo vo, ShiroUser user) throws SysException;

	/**
	 * 查询题目详细 
	 * 
	 * @param vo (主键:id,状态:status)
	 * @return
	 * @throws SysException
	 */
	TiMuVo queryDetailById(TiMuVo vo) throws SysException;

	/**
	 *
	 * @param wjId
	 * @return
	 * @throws SysException
	 */
	List<TiMu> selectByWjId(Long wjId) throws SysException;

	/**
	 *@author: Leslie
	 *@Description: 根据问卷查询其选择题
	 *@Date 2018/1/19 15:53
	 *@param: [wjId]
	 *@return java.util.List<com.wangzhixuan.model.wenjuan.TiMu>
	 *@throws
	 */
	List<TiMu> listChoseByWjId(Long wjId) throws SysException;
	PageInfo findByCondition(PageInfo pageInfo);
    /***
	 *@author: Leslie
	 *@Date 2018/1/24 17:42
	 *@param: [wjId]
	 *@return void
	 *@throws
	 *@Description:根据问卷查询该问卷共多少道题
	 */
	Integer countByWj(Long wjId);
    /***
	 *@author: Leslie
	 *@Date 2018/1/26 17:46
	 *@param: [tmId]
	 *@return java.lang.Double
	 *@throws
	 *@Description:根据题目id获取该题分值
	 */
	Double selectScoreById(Long tmId);
}
