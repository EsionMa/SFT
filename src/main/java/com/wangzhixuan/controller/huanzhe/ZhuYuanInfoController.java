package com.wangzhixuan.controller.huanzhe;

import java.util.HashMap;
import java.util.Map;

import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;

@Controller()
@RequestMapping("/zhuyuanInfo")
public class ZhuYuanInfoController extends BaseController {
	@Autowired
	private IZhuYuanInfoService zhuyuanInfoService;

	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		ZhuYuanInfoVo vo = BeanUtils.mapToBean(map, ZhuYuanInfoVo.class);
		if (vo.getId() == null) {
			throw new SysException("删除ID不能为空");
		}
		zhuyuanInfoService.delete(vo.getId());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		ZhuYuanInfoVo vo = BeanUtils.mapToBean(map, ZhuYuanInfoVo.class);
		if (vo == null) {
			vo = new ZhuYuanInfoVo();
		}
		PageInfo pageInfo = zhuyuanInfoService.selectDataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		ZhuYuanInfoVo vo = BeanUtils.mapToBean(map, ZhuYuanInfoVo.class);
		zhuyuanInfoService.saveOrUpdate(vo,getShiroUser());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/queryDetailById")
	@ResponseBody
	public RespResult<ZhuYuanInfoVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<ZhuYuanInfoVo> result = new RespResult<>();
		ZhuYuanInfoVo vo = BeanUtils.mapToBean(map, ZhuYuanInfoVo.class);
		vo = zhuyuanInfoService.selectDetailById(vo);
		result.getSuccess(vo);
		return result;
	}

	/**
	 * @Author: wangjun
	 * @Description:住院信息模糊查询传入name,zy_no,plzd等字段
	 * @Date 2017/8/1 18:24
	 */
	@RequestMapping(value = "/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByCondition(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		ZhuYuanInfoVo vo = BeanUtils.mapToBean(map, ZhuYuanInfoVo.class);
		if (vo == null) {
			vo = new ZhuYuanInfoVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("hzName", vo.getHzName());
		condition.put("zy_no", vo.getZyNo());
		condition.put("hz_no", vo.getHzNo());
		condition.put("plzd", vo.getPlzd());
		condition.put("deptId",vo.getCyDeptId());
		pageInfo.setCondition(condition);
		zhuyuanInfoService.findByCondition(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}
}
