package com.wangzhixuan.controller.fa;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.fa.FangAnDingYi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.fa.FangAnDingYiVo;
import com.wangzhixuan.service.fa.IFangAnDingYiService;

@Controller()
@RequestMapping("/fangAnDingYi")
public class FangAnDingYiController extends BaseController {
	@Autowired
	private IFangAnDingYiService fangAnDingYiService;

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<List<FangAnDingYiVo>> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<List<FangAnDingYiVo>> result = new RespResult<List<FangAnDingYiVo>>();
		List<FangAnDingYiVo> selectDataGrid = fangAnDingYiService
				.selectDataGrid(map);
		result.getSuccess(selectDataGrid);
		return result;
	}

	/**
	 * @author: Leslie @Description: 根据方案类型查询正在执行方案列表 @Date 2018/1/22
	 *          11:43 @param: [map] @return
	 *          com.wangzhixuan.commons.result.RespResult<java.util.List<com.wangzhixuan.model.vo.fa.FangAnDingYiVo>> @throws
	 */
	@RequestMapping(value = "/queryStartFa")
	@ResponseBody
	public RespResult<List<FangAnDingYi>> listStartedFa(@RequestBody Map<String, Object> map) {
		RespResult<List<FangAnDingYi>> result = new RespResult<>();
		result.getSuccess(fangAnDingYiService.listStartedFa(map));
		return result;
	}

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		FangAnDingYiVo vo = BeanUtils.mapToBean(map, FangAnDingYiVo.class);
		fangAnDingYiService.saveOrUpdate(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/enableFangAn")
	@ResponseBody
	public RespResult<String> enableFangAn(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Object idObj = map.get("id");
		Long id = null;
		if (idObj == null) {
			throw new SysException("方案ID不能为空");
		}
		try {
			id = Long.parseLong(idObj.toString());
		} catch (Exception e) {
			throw new SysException("方案ID无效");
		}
		Object enableObj = map.get("enable");
		Boolean enable = null;
		if (enableObj == null) {
			throw new SysException("id不能为空");
		}
		enable = Boolean.parseBoolean(enableObj.toString());
		fangAnDingYiService.enableFangAn(id, enable);
		result.getSuccess("", (enable ? "启用" : "禁用") + "成功");
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Object idObj = map.get("id");
		Long id = null;
		if (idObj == null) {
			throw new SysException("id不能为空");
		}
		try {
			id = Long.parseLong(idObj.toString());
		} catch (Exception e) {
			throw new SysException("患者ID非法");
		}
		fangAnDingYiService.delete(id);
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/queryById")
	@ResponseBody
	public RespResult<FangAnDingYiVo> queryById(@RequestBody Map<String, Object> map) {
		RespResult<FangAnDingYiVo> result = new RespResult<>();
		FangAnDingYi fangAnDingYi = BeanUtils.mapToBean(map, FangAnDingYi.class);
		FangAnDingYiVo fangAnDingYiVo = fangAnDingYiService.queryById(fangAnDingYi.getId());
		result.getSuccess(fangAnDingYiVo);
		return result;
	}
}
