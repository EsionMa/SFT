package com.wangzhixuan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.model.vo.GroupRuleVo;
import com.wangzhixuan.service.IGroupRuleService;
@Controller
@RequestMapping("group")
public class GroupController extends BaseController{
	private IGroupRuleService groupRoleService;
	
	@RequestMapping(value="/addGroup")
	@ResponseBody
	public RespResult<String> queryDetailById(@RequestBody GroupRuleVo vo){
		RespResult<String> result=new RespResult<String>();
		groupRoleService.addGroup(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}
	@RequestMapping(value="/updateStatue")
	@ResponseBody
	public RespResult<String> updateStatue(@RequestBody GroupRuleVo vo){
		RespResult<String> result=new RespResult<String>();
		groupRoleService.updateStatue(vo.getId(), vo.getStatue(), getShiroUser());
		result.getSuccess("");
		return result;
	}
	public RespResult<Map<String,Object>> dataGrid(@RequestBody GroupRuleVo vo){
		RespResult<Map<String,Object>> result=new RespResult<Map<String,Object>>();
		Map<String, Object> data=new HashMap<String, Object>();
		result.getSuccess(data);
		return result;
	}
}
