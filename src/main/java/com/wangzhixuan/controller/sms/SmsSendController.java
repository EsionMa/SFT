/**
 * 2017-08-08 11:50:46
 */
package com.wangzhixuan.controller.sms;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;
import com.wangzhixuan.service.sms.ISmsPatientService;
import com.wangzhixuan.service.sms.SmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.sms.SmsSendVo;
import com.wangzhixuan.service.sms.ISmsSendService;

/**
 * @author Esion
 *
 */
@Controller
@RequestMapping("/sms/send")
public class SmsSendController extends BaseController {
	@Autowired
	private ISmsSendService smsSendService;
	@Autowired
	private SmsUserService smsUserService;
	@Autowired
	private ISmsPatientService smsPatientService;

	@RequestMapping(value = "/startModem", method = RequestMethod.POST)
	@ResponseBody
	public RespResult<String> startService() {
		RespResult<String> result = new RespResult<>();
		try {
			smsSendService.startService();
			result.getSuccess("", "检测到短信猫设备并开启服务");
		} catch (Exception e) {
			result.getFail(e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/sendMsg")
	public RespResult<String> sendMessage(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		SmsSendVo vo = BeanUtils.mapToBean(map, SmsSendVo.class);
		ShiroUser shiroUser = getShiroUser();
		vo.setUserId(shiroUser.getId());
		vo.setUserName(shiroUser.getName());
		List<UserVo> userList = vo.getUserList();
		for (UserVo userVo : userList) {
			vo.setRcverName(userVo.getName());
			vo.setToNumber(userVo.getPhone());
			try {
				if (vo.getTimeType() == 1) {
					smsSendService.sendInstantMsg(vo);
				} else if (vo.getTimeType() == 2) {
					smsSendService.generatePlannedMsg(vo, null, null);
				}
				result.getSuccess("", "短信发送成功");
			} catch (Exception e) {
				result.getFail(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 查询（分页）
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	public RespResult<PageInfo> getDataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		SmsSendVo vo = BeanUtils.mapToBean(map, SmsSendVo.class);
		PageInfo pageInfo = smsSendService.selectDataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 删除（可批量）
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
		SmsSendVo vo = BeanUtils.mapToBean(map, SmsSendVo.class);
		if (vo == null) {
			vo = new SmsSendVo();
		}
		smsSendService.deleteMsgs(vo.getId());
		result.getSuccess("刪除成功");
		return result;
	}

	/**
	 * 查找职工通讯录
	 */
	@ResponseBody
	@RequestMapping(value = "/selectUser", method = RequestMethod.POST)
	public RespResult<PageInfo> selectUser(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		UserVo userVo = BeanUtils.mapToBean(map, UserVo.class);
		PageInfo pageInfo = smsUserService.selectUser(userVo, getShiroUser());
		result.getSuccess(pageInfo);
		return result;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public RespResult<UserVo> findById(@RequestBody Map<String, Object> map) {
		RespResult<UserVo> result = new RespResult<>();
		UserVo vo = BeanUtils.mapToBean(map, UserVo.class);
		if (vo.getId() != null) {
			UserVo userId = smsUserService.findById(vo.getId());
			result.getSuccess(userId);
		} else {
			result.getFail(ErrorCode.ReqIsNull);
		}
		return result;
	}




	/**
	 * 查询患者通讯录，
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectPatient", method = RequestMethod.POST)
	public RespResult<PageInfo> selectPatients(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		ZhuYuanInfoVo vo = BeanUtils.mapToBean(map, ZhuYuanInfoVo.class);
		PageInfo pageInfo = smsPatientService.selectPatient(vo);
		result.getSuccess(pageInfo);
		return result;
	}

}
