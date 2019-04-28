package com.wangzhixuan.controller.wenjuan;

import java.util.HashMap;
import java.util.Map;

import com.wangzhixuan.model.tj.WenJuanDaAnTongjiVo;
import com.wangzhixuan.model.wenjuan.WenJuanAnswer;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.service.wenjuan.IWenJuanService;

@Controller()
@RequestMapping("/wenJuan")
public class WenJuanController extends BaseController {
	@Autowired
	private IWenJuanService wenJuanService;
	public WenJuanVo wenJuanVo;

	/**
	 * 添加修改
	 *
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate")
	@ResponseBody
	public RespResult<String> addOrUpdate(@RequestBody WenJuanVo vo) {
		RespResult<String> result = new RespResult<String>();
		ShiroUser user = getShiroUser();
		wenJuanService.saveOrUpdate(vo, user);
		result.getSuccess();
		return result;
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody WenJuanVo vo) {
		RespResult<PageInfo> result = new RespResult<>();
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("wjfl", vo.getWjfl());
		condition.put("wjzt", vo.getWjzt());
		condition.put("deptId", vo.getDeptId());
		condition.put("zdCode", vo.getZdCode());
		pageInfo.setCondition(condition);
		wenJuanService.selectDateGrid(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Object idObj = map.get("id");
		if (idObj == null) {
			result.getFail(ErrorCode.ReqIdIsNull);
		}
		WenJuanVo vo = new WenJuanVo();
		vo.setId(Long.parseLong(idObj.toString()));
		wenJuanService.delete(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/queryDetailById")
	@ResponseBody
	public RespResult<WenJuanVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<WenJuanVo> result = new RespResult<>();
		WenJuanVo vo = BeanUtils.mapToBean(map, WenJuanVo.class);
		result.getSuccess(wenJuanService.queryDetail(vo));
		return result;
	}

	/**
	 * @Author: Leslie
	 * @Description:问卷主题搜索
	 * @Date 2017/8/16 12:00
	 */
	@RequestMapping("/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByWjzt(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		WenJuanVo vo = BeanUtils.mapToBean(map, WenJuanVo.class);
		if (vo == null) {
			vo = new WenJuanVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("wjzt", vo.getWjzt());
		pageInfo.setCondition(condition);
		wenJuanService.findByWjzt(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}
	/***
	 *@author: Leslie
	 *@Date 2018/1/23 17:45
	 *@param: [map]
	 *@return com.wangzhixuan.commons.result.RespResult
	 *@throws
	 *@Description:问卷具体详情，哪些人回答了该问卷，问卷内容等
	 */
	@RequestMapping("/listWjAnswers")
	@ResponseBody
	public RespResult<PageInfo> listAnswers(@RequestBody Map<String,Object> map){
		RespResult<PageInfo> result = new RespResult<>();
		WenJuanVo vo = BeanUtils.mapToBean(map, WenJuanVo.class);
		if (vo == null) {
			vo = new WenJuanVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String,Object> condition=new HashedMap();
		condition.put("id",vo.getId());
		pageInfo.setCondition(condition);
		wenJuanService.listAnswerOfWenJuan(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}

  /* @RequestMapping("/answerDetail")
   @ResponseBody
   public RespResult<WenJuanVo> answerDetail(@RequestBody Map<String,Object> map){
		RespResult<WenJuanVo> result=new RespResult<>();
		 WenJuanVo vo=BeanUtils.mapToBean(map,WenJuanVo.class);
		 if (vo==null){
		 	vo=new WenJuanVo();
		 }
		result.getSuccess(wenJuanService.answerDetail(vo));
		return result;
   }*/

	public RespResult<String> writeWj() {
		RespResult<String> result = new RespResult<>();
		return result;
	}
}
