package com.wangzhixuan.service.fa;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.fa.FangAnDingYi;
import com.wangzhixuan.model.vo.fa.FangAnDingYiVo;
import com.wangzhixuan.model.vo.fa.HuanZhePiPeiBo;

public interface IFangAnDingYiService extends IService<FangAnDingYi> {
	/**
	 *  保存或更新
	 * @param vo
	 * @param user
	 * @throws SysException
	 */
	void saveOrUpdate(FangAnDingYiVo vo, ShiroUser user) throws SysException;
    /**
	 *@author: Leslie
	 *@Date 2018/3/21 17:51
	 *@param: [faType]
	 *@return java.util.List<com.wangzhixuan.model.vo.fa.FangAnDingYiVo>
	 *@throws
	 *@Description: 方案查询
	 */
	List<FangAnDingYiVo> selectDataGrid(Map<String,Object> map) throws SysException;

	/**
	 * 启用方案
	 *
	 * @param enable
	 * @throws SysException
	 */
	void enableFangAn(Long id, boolean enable) throws SysException;

	void delete(Long id) throws SysException;
    /**
	 *@author: Leslie
	 *@Date 2018/3/21 18:53
	 *@param: [id]
	 *@return com.wangzhixuan.model.vo.fa.FangAnDingYiVo
	 *@throws
	 *@Description:  获取问卷详情
	 */
	FangAnDingYiVo queryById(Long id) throws SysException;

	void taskHuanZhePiPeiFangan(HuanZhePiPeiBo bo) throws SysException;
	/**
	 *@author: Leslie
	 *@Description: 根据方案类型获得正在执行方案
	 *@Date 2018/1/22 11:46
	 *@param: [map]
	 *@return java.util.List<com.wangzhixuan.model.vo.fa.FangAnDingYiVo>
	 *@throws
	 */

    List<FangAnDingYi> listStartedFa(Map<String, Object> map);
   /**
	*@author: Leslie
	*@Description: 根据方案id查询执行该方案的科室
	*@Date 2018/1/22 15:27
	*@param: [faId]
	*@return java.util.List<com.wangzhixuan.model.SysOrganization>
	*@throws
	*/
	List<SysOrganization> listOrgsByFaId(Long faId);
}
