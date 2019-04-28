package com.wangzhixuan.controller.tj;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.statics.OutStatics;
import com.wangzhixuan.model.tj.WenJuanDaAnTongjiVo;
import com.wangzhixuan.model.vo.wj.GongZuoTongJiVo;
import com.wangzhixuan.model.vo.wj.SuiFangTongJiVo;
import com.wangzhixuan.service.statics.IOutStaticsService;
import com.wangzhixuan.service.tj.ITongjiService;
import com.wangzhixuan.service.tj.WenJuanTongJiBo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tj")
public class TongJiController extends BaseController {
	@Autowired
	private ITongjiService tongJiService;
	@Autowired
	private IOutStaticsService staticsService;
    /**
     *@Author: Leslie
     *@Description:问卷统计
     *@Date 2018/1/19 11:25
     *@Param:
     */
	@RequestMapping(value = "/wenJuanDaAnTongJi")
	@ResponseBody
	public RespResult<WenJuanDaAnTongjiVo> wenJuanDaAnTongJi(@RequestBody Map<String, Object> map) {
		RespResult<WenJuanDaAnTongjiVo> result = new RespResult<>();
		WenJuanDaAnTongjiVo wenJuanDaAnTongJi = tongJiService.wenJuanDaAnTongJi(map);
		result.getSuccess(wenJuanDaAnTongJi);
		return result;
	}

	/**
	 * 随访统计
	 * 
	 * @param map
	 * @return
	 */
	//TODO  根据登录用户权限进行判定
	@RequestMapping(value = "/wenJuanShiLiTongJI")
	@ResponseBody
	public RespResult<SuiFangTongJiVo> ShiLiTongJi(@RequestBody Map<String, Object> map) {
		RespResult<SuiFangTongJiVo> result = new RespResult<>();
		result.getSuccess(tongJiService.sfCount(map));
		return result;
	}

	/**
	 * if(admin){
	 *     all
	 * }else{
	 *     if(chankanquanyuanshuju){
	 *         dept
	 *     }else{
	 *         my
	 *     }
	 * }
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/workCount")
	@ResponseBody
	public RespResult<List<GongZuoTongJiVo>> workCount(@RequestBody Map<String, Object> map) {
		RespResult<List<GongZuoTongJiVo>> result = new RespResult<>();
		result.getSuccess(tongJiService.workCount(map, getShiroUser()));
		return result;
	}
}
