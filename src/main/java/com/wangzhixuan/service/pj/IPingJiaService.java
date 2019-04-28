package com.wangzhixuan.service.pj;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.pj.PingJia;
import com.wangzhixuan.model.vo.EvaCountVo;
import com.wangzhixuan.model.vo.pj.EvaluationVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPingJiaService extends IService<PingJia> {
	void deleteByByPjLyAndExtId(String pjly, String extId, List<Long> ids);

	void saveOrUpdate(PingJiaVo vo, ShiroUser user);

	List<PingJiaVo> selectByPjLyAndExtId(String pjly, String extId);

	/**
	 * 查询评价(分页)
	 * 
	 * @param pageInfo
	 * @return
	 */
	PageInfo findByCondition(PageInfo pageInfo);


	/**
	 * @Author: Leslie
	 * @Description:打印专用接口
	 * @Date 2017/11/6 14:15
	 * @Param:
	 */
	Map<String, List<EvaluationVo>> print(Map<String, Object> map);
   /**
	*@author: Leslie
	*@Date 2018/4/11 16:16
	*@param: [map]
	*@return java.lang.Integer[]
	*@throws
	*@Description: 获取3种类型的字段
	*/
   List<EvaCountVo> getFeedBackCount(Map<String, Object> map);
}
