package com.wangzhixuan.controller.sms;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.sms.SmsTemplateVo;
import com.wangzhixuan.service.sms.ISmsTemplateService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12 0012.
 */
@Controller
@RequestMapping("sms/template")
public class SmsTemplateController extends BaseController {

	@Autowired
	private ISmsTemplateService smsTemplateService;

	/**
	 * 添加、
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RespResult<String> addTemplate(@RequestBody SmsTemplateVo vo) {
		RespResult<String> result = new RespResult<>();
		ShiroUser user = getShiroUser();
		smsTemplateService.save(vo, user);
		result.getSuccess("", "新模板添加成功");
		return result;
	}

	/**
	 * 修改模板
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public RespResult<String> update(@RequestBody SmsTemplateVo vo) {
		RespResult<String> result = new RespResult<>();
		ShiroUser user = getShiroUser();
		smsTemplateService.update(vo, user);
		result.getSuccess("", "模板修改成功");
		return result;
	}

	/**
	 * 查询，
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	public RespResult<PageInfo> getDataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		SmsTemplateVo vo = BeanUtils.mapToBean(map, SmsTemplateVo.class);
		PageInfo pageInfo = smsTemplateService.selectDataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 获取模板ID
	 * 
	 * @param map
	 * @return 返会对象
	 */
	@ResponseBody
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public RespResult<SmsTemplateVo> findById(@RequestBody Map<String, Object> map) {
		RespResult<SmsTemplateVo> result = new RespResult<>();
		SmsTemplateVo vo = BeanUtils.mapToBean(map, SmsTemplateVo.class);
		if (vo.getId() != null) {
			SmsTemplateVo templateId = smsTemplateService.findById(vo.getId());
			result.getSuccess(templateId);
		} else {
			result.getFail(ErrorCode.ReqIsNull);
		}
		return result;
	}

	/**
	 * 删除模板
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delMsgs", method = RequestMethod.POST)
	public RespResult<String> deleteMsgs(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		SmsTemplateVo vo = BeanUtils.mapToBean(map, SmsTemplateVo.class);
		if (vo == null) {
			result.getFail("该模板不存在");
		}
		smsTemplateService.deleteMsgs(vo.getId());
		result.getSuccess("删除成功");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/findByType", method = RequestMethod.POST)
	public RespResult<List<SmsTemplateVo>> findByType(@RequestBody Map<String, Object> map) {
		RespResult<List<SmsTemplateVo>> rsult = new RespResult<>();
		SmsTemplateVo vo = BeanUtils.mapToBean(map, SmsTemplateVo.class);
		rsult.getSuccess(smsTemplateService.selectTempByType(vo));
		return rsult;
	}
}
