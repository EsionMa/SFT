/**
 * 2017-08-08 11:50:46
 */
package com.wangzhixuan.controller.sms;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.sms.SmsRcvVo;
import com.wangzhixuan.service.sms.ISmsRcvService;

/**
 * @author Esion
 *
 */
@Controller
@RequestMapping("/sms/rcv")
public class SmsRcvController extends BaseController {
	@Autowired
	private ISmsRcvService smsRcvService;

	/**
	 * 查询（分页）（接收）
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	public RespResult<PageInfo> getDataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		SmsRcvVo vo = BeanUtils.mapToBean(map, SmsRcvVo.class);
		PageInfo pageInfo =	smsRcvService.selectDataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 删除（可批量）（接收）
	 * 
	 * @param map
	 * @return
	 */
	/**
	 * @Author: Leslie
	 * @Description:单个删除
	 * @Date 2017/8/17 13:55
	 */
	@ResponseBody
	@RequestMapping(value = "/delMsgs", method = RequestMethod.POST)
	public RespResult<String> deleteMsgs(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		SmsRcvVo vo = BeanUtils.mapToBean(map, SmsRcvVo.class);
		if (vo == null) {
			vo = new SmsRcvVo();
		}
		smsRcvService.deleteMsgs(vo.getId());
		result.getSuccess("");
		return result;
	}

}
