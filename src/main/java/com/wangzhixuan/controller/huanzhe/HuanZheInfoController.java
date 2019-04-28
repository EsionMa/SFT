package com.wangzhixuan.controller.huanzhe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.wangzhixuan.model.vo.huanzhe.HuanZheInfoVo;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;

@Controller()
@RequestMapping("/huanzheInfo")
public class HuanZheInfoController extends BaseController {
	@Autowired
	private IHuanZheInfoService huanZheInfoService;

	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		HuanZheInfoVo vo = BeanUtils.mapToBean(map, HuanZheInfoVo.class);
		if (vo.getId() == null) {
			throw new SysException("删除ID不能为空");
		}
		huanZheInfoService.delete(vo.getId());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		HuanZheInfoVo vo = BeanUtils.mapToBean(map, HuanZheInfoVo.class);
		if (vo == null) {
			vo = new HuanZheInfoVo();
		}
		PageInfo pageInfo = huanZheInfoService.selectDataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		HuanZheInfoVo vo = BeanUtils.mapToBean(map, HuanZheInfoVo.class);
		huanZheInfoService.saveOrUpdate(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/queryDetailById")
	@ResponseBody
	public RespResult<HuanZheInfoVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<HuanZheInfoVo> result = new RespResult<>();
		HuanZheInfoVo vo = BeanUtils.mapToBean(map, HuanZheInfoVo.class);
		vo = huanZheInfoService.selectDetailById(vo);
		result.getSuccess(vo);
		return result;
	}

	@RequestMapping(value = "/addTag")
	@ResponseBody
	public RespResult<String> addTag(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Object hzIdObj = map.get("hzId");
		Long hzId = null;
		if (hzIdObj == null) {
			throw new SysException("患者不存在Null");
		}
		try {
			hzId = Long.parseLong(hzIdObj.toString());
		} catch (Exception e) {
			throw new SysException("患者ID非法");
		}
		Object tagCodesObj = map.get("tagCodes");
		List<String> tagCodes = null;
		if (tagCodesObj != null) {
			tagCodes = (List) tagCodesObj;
		}
		huanZheInfoService.addTag(hzId, tagCodes, getShiroUser());
		result.getSuccess("");
		return result;
	}

	@RequestMapping(value = "/queryHzContractMap")
	@ResponseBody
	public RespResult<List<Map<String, String>>> queryHzContractMap(@RequestBody Map<String, Object> map) {
		RespResult<List<Map<String, String>>> result = new RespResult<>();
		Object hzIdObj = map.get("hzId");
		Long hzId = null;
		if (hzIdObj == null) {
			throw new SysException("患者不存在Null");
		}
		try {
			hzId = Long.parseLong(hzIdObj.toString());
		} catch (Exception e) {
			throw new SysException("患者ID非法");
		}
		List<Map<String, String>> hzContractMap = huanZheInfoService.queryHzContractMap(null, hzId);
		result.getSuccess(hzContractMap);
		return result;
	}

	/**
	 * @Author: wangjun
	 * @Description:传入 hzno,phone,tagsName等字段模糊查询
	 * @Date 2017/8/1 18:22
	 *
	 *       此接口未使用
	 */
	@RequestMapping(value = "/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByCondition(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		HuanZheInfoVo vo = BeanUtils.mapToBean(map, HuanZheInfoVo.class);
		if (vo == null) {
			vo = new HuanZheInfoVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("name", vo.getName());
		condition.put("hzno", vo.getHzno());
		condition.put("mobilePhone", vo.getMobilephone());
		condition.put("tagsName", vo.getTagsName());
		/* condition.put("type",vo.set) */
		pageInfo.setCondition(condition);
		huanZheInfoService.findByCondition(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}

	/***
	 * @author: Leslie
	 * @Date 2018/2/1 15:18
	 * @param: [map]
	 * @return com.wangzhixuan.commons.result.RespResult<java.util.Map<java.lang.String,java.lang.Object>>
	 * @throws @Description:根据患者来源返回住院信息或者是门诊信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getHosOrOut")
	public RespResult<Map<String, Object>> getHosOrOut(@RequestBody Map<String, Object> map) {
		RespResult<Map<String, Object>> result = new RespResult<>();
		result.getSuccess(huanZheInfoService.getHosOrOut(map));
		return result;
	}

	/**
	 * @author: Leslie
	 * @Date 2018/2/27 18:09
	 * @param: []
	 * @return com.wangzhixuan.commons.result.RespResult<java.lang.Integer>
	 * @throws @Description:根据日期得到患者生日人数
	 */
	@ResponseBody
	@RequestMapping(value = "/getBirthCount")
	public RespResult<Integer> getBirthCount(@RequestBody Map<String, Object> map) {
		RespResult<Integer> result = new RespResult<>();
		result.getSuccess(huanZheInfoService.getBirthCount(map));
		return result;
	}
}
