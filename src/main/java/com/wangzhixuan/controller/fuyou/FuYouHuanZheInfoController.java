package com.wangzhixuan.controller.fuyou;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouHuanZheInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@Controller
@RequestMapping("/fuyouhuanzhe")
public class FuYouHuanZheInfoController extends BaseController {

	@Autowired
	private IFuYouHuanZheInfoService fuYouInfoService;

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		String expect = map.get("hyExpected").toString();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date expected = format.parse(expect);
			map.put("expected", expected);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		FuYouHuanZheInfoVo vo = BeanUtils.mapToBean(map, FuYouHuanZheInfoVo.class);
		fuYouInfoService.saveOrUpdate(vo);
		result.getSuccess("", "操作成功");
		return result;
	}
    @RequestMapping(value = "/selectedData")
    @ResponseBody
	public RespResult<String> selectedData(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		FuYouHuanZheInfoVo vo = BeanUtils.mapToBean(map, FuYouHuanZheInfoVo.class);
		if (vo.getHzId() == null) {
			throw new SysException("患者id不能为空");
		}
		fuYouInfoService.selectedData(vo);
		result.getSuccess("", "");
		return result;
	}
}
