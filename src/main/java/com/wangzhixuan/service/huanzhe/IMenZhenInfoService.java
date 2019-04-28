/**
 * 2017-12-18 18:43:12
 */
package com.wangzhixuan.service.huanzhe;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.model.vo.huanzhe.MenZhenInfoVo;

import java.util.Map;

/**
 * @author Esion
 *
 */
public interface IMenZhenInfoService extends IService<MenZhenInfo> {
	/**
	 *  保存或更新
	 * @param vo
	 * @param user
	 * @return
	 */
	MenZhenInfoVo saveOrUpdate(MenZhenInfoVo vo, ShiroUser user);

	/**
	 *  查询门诊患者信息
	 * @param vo
	 * @return
	 */
	PageInfo dataGrid(MenZhenInfoVo vo);

	/**
	 *  根据id查询患者门诊信息
	 * @param vo
	 * @return
	 */
	MenZhenInfoVo selectDetailById(MenZhenInfoVo vo);

	/**
	 *  删除门诊信息
	 * @param id
	 * @throws SysException
	 */
	void delete(Long id) throws SysException;

	/**
	 * 条件查询
	 * @param pageInfo
	 * @return
	 * @throws SysException
	 */
	PageInfo findByCondition(PageInfo pageInfo) throws SysException;

	/**
	 * 根据群组条件筛选门诊患者（分页）
	 * 
	 * @param group
	 * @param nowPage
	 * @param pageSize
	 * @return
	 * @throws SysException
	 */
	PageInfo findByGroup(FangAnMenZhenGroupVo group, Integer nowPage, Integer pageSize) throws SysException;
   /**
	*@author: Leslie
	*@Date 2018/2/1 15:35
	*@param: [map]
	*@return java.util.Map<java.lang.String,java.lang.Object>
	*@throws
	*@Description:通过患者号或者门诊时间得到其门诊信息
	*/
   Map<String,Object> getByTimeAndId(Map<String, Object> map);
}
