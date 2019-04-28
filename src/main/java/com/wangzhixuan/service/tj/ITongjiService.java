package com.wangzhixuan.service.tj;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.tj.WenJuanDaAnTongjiVo;
import com.wangzhixuan.model.vo.wj.GongZuoTongJiVo;
import com.wangzhixuan.model.vo.wj.SuiFangTongJiVo;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Map;

public interface ITongjiService {
	 /**
      * 问卷统计
	  *@author: Leslie
	  *@Description:
	  *@Date 2018/1/19 11:54
	  *@Param:[map]
	  *@return com.wangzhixuan.model.tj.WenJuanDaAnTongjiVo
	  */
	WenJuanDaAnTongjiVo wenJuanDaAnTongJi(Map<String,Object> map) throws SysException;

	/**
	 *@author: Leslie
	 *@Description:随访统计
	 *@Date 2018/1/19 11:54
	 *@Param:[map]
	 *@return java.util.List<com.wangzhixuan.model.vo.wj.SuiFangTongJiVo>
	 */
	SuiFangTongJiVo sfCount(Map<String,Object> map)throws SysException;



	/**
	 *@author: Leslie
	 *@Description:工作统计
	 *@Date 2018/1/19 11:54
	 *@Param:[map, user]
	 *@return java.util.List<com.wangzhixuan.model.vo.wj.GongZuoTongJiVo>
	 */
	List<GongZuoTongJiVo> workCount(Map<String,Object> map, ShiroUser user ) throws SysException;
}
