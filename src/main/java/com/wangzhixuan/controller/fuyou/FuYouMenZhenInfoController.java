package com.wangzhixuan.controller.fuyou;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouMenZhenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@Controller
@RequestMapping(value = "/fuyoumenzhen")
public class FuYouMenZhenInfoController extends BaseController {

	@Autowired
	private IFuYouMenZhenInfoService fuYouInfoService;

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		/*
		 * String expect = map.get("hyExpected").toString(); DateFormat format =
		 * new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * try { Date expected = format.parse(expect);
		 * map.put("expected",expected); } catch (ParseException e) {
		 * e.printStackTrace(); }
		 */
		FuYouMenZhenInfoVo vo = BeanUtils.mapToBean(map, FuYouMenZhenInfoVo.class);
		fuYouInfoService.saveOrUpdate(vo);
		result.getSuccess("", "操作成功");
		return result;
	}

	public RespResult<String> selectedData(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		FuYouMenZhenInfoVo vo = BeanUtils.mapToBean(map, FuYouMenZhenInfoVo.class);
		if (vo.getMzId() == null) {
			throw new SysException("门诊id不能为空");
		}
		fuYouInfoService.selectedData(vo);
		result.getSuccess("", "");
		return result;
	}
}
