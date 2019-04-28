/**
 * 2017-11-22 11:40:52
 */
package com.wangzhixuan.controller.icd;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.icd.IcdVo;
import com.wangzhixuan.service.icd.IIcdService;

/**
 * @author Esion
 *
 */
@Controller
@RequestMapping("/icd")
public class IcdController extends BaseController {
	@Autowired
	private IIcdService icdService;

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<List<IcdVo>> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<List<IcdVo>> result = new RespResult<>();
		IcdVo vo = BeanUtils.mapToBean(map, IcdVo.class);
		List<IcdVo> icdVos = icdService.getIcdList(vo);
		result.getSuccess(icdVos);
		return result;
	}
}
