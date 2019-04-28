package com.wangzhixuan.controller.huanzhe;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.model.vo.huanzhe.MenZhenInfoVo;
import com.wangzhixuan.service.huanzhe.IMenZhenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leslie on 2017/10/24.
 *
 * @author: Leslie
 * @TIME:16:44
 * @Date:2017/10/24. Description:
 */
@Controller()
@RequestMapping(value = "/outpatient")
public class MenZhenInfoController extends BaseController {
	@Autowired
	private IMenZhenInfoService menZhenInfoService;

	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	public RespResult<String> addOutPatient(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		MenZhenInfoVo vo = BeanUtils.mapToBean(map, MenZhenInfoVo.class);
		if (vo == null) {
			vo = new MenZhenInfoVo();
		}
		menZhenInfoService.saveOrUpdate(vo,getShiroUser());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		MenZhenInfoVo vo = BeanUtils.mapToBean(map, MenZhenInfoVo.class);
		if (vo == null) {
			vo = new MenZhenInfoVo();
		}
		PageInfo pageInfo = menZhenInfoService.dataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	@RequestMapping(value = "/queryDetailById")
	@ResponseBody
	public RespResult<MenZhenInfoVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<MenZhenInfoVo> result = new RespResult<>();
		MenZhenInfoVo vo = BeanUtils.mapToBean(map, MenZhenInfoVo.class);
		vo = menZhenInfoService.selectDetailById(vo);
		result.getSuccess(vo);
		return result;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		MenZhenInfoVo vo = BeanUtils.mapToBean(map, MenZhenInfoVo.class);
		if (vo.getId() == null) {
			throw new SysException("删除ID不能为空");
		}
		menZhenInfoService.delete(vo.getId());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByCondition(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		MenZhenInfoVo vo = BeanUtils.mapToBean(map, MenZhenInfoVo.class);
		if (vo == null) {
			vo = new MenZhenInfoVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("hzName", vo.getHzName());
		condition.put("mzNo", vo.getMzNo());
		condition.put("mzDeptId", vo.getMzDeptId());
		pageInfo.setCondition(condition);
		menZhenInfoService.findByCondition(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}
}
