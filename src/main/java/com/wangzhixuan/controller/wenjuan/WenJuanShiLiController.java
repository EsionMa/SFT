package com.wangzhixuan.controller.wenjuan;

import java.util.HashMap;
import java.util.Map;
import com.wangzhixuan.model.vo.wj.ChouChaBo;
import com.wangzhixuan.model.vo.wj.SatisfactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;
import com.wangzhixuan.service.pj.IPingJiaService;
import com.wangzhixuan.service.wenjuan.IWenJuanShiLiService;

@Controller
@RequestMapping("/wenJuanShiLi")
public class WenJuanShiLiController extends BaseController {
	@Autowired
	private IWenJuanShiLiService wenJuanShiLiService;
	@Autowired
	private IPingJiaService pingJiaService;

	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		PageInfo pageInfo = new PageInfo(map);
		pageInfo = wenJuanShiLiService.selectDateGrid(pageInfo, getShiroUser());
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 按照计划执行的执行方式保存
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map) {
		WenJuanShiLiVo vo = BeanUtils.mapToBean(map, WenJuanShiLiVo.class);
		vo.setZxfs("1");
		RespResult<String> result = new RespResult<String>();
		wenJuanShiLiService.saveOrUpdate(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}

	/**
	 * 保存评价
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePj")
	@ResponseBody
	public RespResult<String> savePingJia(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		PingJiaVo pingJiaVo = BeanUtils.mapToBean(map, PingJiaVo.class);
		pingJiaService.saveOrUpdate(pingJiaVo, getShiroUser());
		return result;
	}

	/**
	 * 抽查随访
	 *
	 * @param map
	 * @return
	 */
	/*@RequestMapping(value = "/chouCha")
	@ResponseBody
	public RespResult<Map<String, Object>> chouCha(@RequestBody Map<String, Object> map) {
		RespResult<Map<String, Object>> result = new RespResult<>();
		ChouChaBo bo = BeanUtils.mapToBean(map, ChouChaBo.class);
		Integer integer = wenJuanShiLiService.chouCha(bo, getShiroUser());
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("successCount", integer);
		result.getSuccess(resultMap);
		return result;
	}
*/
	/**
	 * @Author: Leslie
	 * @Description:随访执行模糊查询
	 * @Date 2017/8/19 16:49
	 */
	@RequestMapping(value = "/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByCondition(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		WenJuanShiLiVo vo = BeanUtils.mapToBean(map, WenJuanShiLiVo.class);
		if (vo == null) {
			vo = new WenJuanShiLiVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("hzName", vo.getHzName());
		// condition.put("zyNo", vo.getZyNo());
		condition.put("status", vo.getStatus());
		condition.put("deptId", vo.getDeptId());
		condition.put("staTime", vo.getStartDate());
		condition.put("endTime", vo.getEndDate());
		// actual 有值，代表按照实际随访时间查询，无值，则是按照计划随访时间进行查询
		condition.put("actual", vo.getActual());
		pageInfo.setCondition(condition);
		wenJuanShiLiService.findByCondition(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 没有计划保存问卷调查（ATM）
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/zzjsave")
	@ResponseBody
	public RespResult<String> zzjsave(@RequestBody Map<String, Object> map) {
		WenJuanShiLiVo vo = BeanUtils.mapToBean(map, WenJuanShiLiVo.class);
		RespResult<String> result = new RespResult<String>();
		wenJuanShiLiService.saveOrUpdate(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}
     /**
	  *@author: Leslie
	  *@Date 2018/3/30 15:49
	  *@param: [map]
	  *@return com.wangzhixuan.commons.result.RespResult<com.wangzhixuan.model.vo.WenJuanShiLiVo>
	  *@throws
	  *@Description: 查询问卷实例相关信息
	  */
	@RequestMapping(value = "/queryDetailById")
	@ResponseBody
	public RespResult<WenJuanShiLiVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<WenJuanShiLiVo> result = new RespResult<>();
		WenJuanShiLiVo vo = BeanUtils.mapToBean(map, WenJuanShiLiVo.class);
		vo = wenJuanShiLiService.queryDetailById(vo.getId());
		result.getSuccess(vo);
		return result;
	}
	/**
	 *@author: Leslie
	 *@Date 2018/2/1 16:43
	 *@param: [map]
	 *@return com.wangzhixuan.commons.result.RespResult<com.wangzhixuan.model.vo.WenJuanShiLiVo>
	 *@throws
	 *@Description:得到随访内容，不加载
	 */
	@ResponseBody
	@RequestMapping(value = "/getSatisfaction")
	public RespResult<SatisfactionVo>  getSatisfaction(@RequestBody Map<String,Object> map){
		RespResult<SatisfactionVo> result = new RespResult<>();
		result.getSuccess(wenJuanShiLiService.getSatisfaction(map));
		return result;
	}
}
