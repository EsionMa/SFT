package com.wangzhixuan.controller.wenjuan;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.vo.TiMuVo;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.wenjuan.TiMu;
import com.wangzhixuan.service.wenjuan.ITiMuService;
import com.wangzhixuan.service.wenjuan.IWenJuanShiLiService;

@Controller()
@RequestMapping("/wenJuan/tiMu")
public class TiMuController extends BaseController {

	@Autowired
	private ITiMuService tiMuService;
	@Autowired
	private IWenJuanShiLiService wenJuanShiLiService;

	@RequestMapping(value = "/queryDetailById")
	@ResponseBody
	public RespResult<TiMuVo> queryDetailById(@RequestBody TiMuVo vo) {
		RespResult<TiMuVo> result = new RespResult<>();
		vo = tiMuService.queryDetailById(vo);
		result.getSuccess(vo);
		return result;
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/fill")
	@ResponseBody
	public RespResult<String> fill(WenJuanShiLiVo vo) {
		RespResult<String> result = new RespResult<>();
		wenJuanShiLiService.saveOrUpdate(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody TiMuVo timu) {
		RespResult<PageInfo> result = new RespResult<>();
		PageInfo pageInfo = new PageInfo(timu.getPage(), timu.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("tmfl", timu.getTmfl());
		condition.put("showItem", timu.getShowItem());
		condition.put("bt", timu.getBt());
		condition.put("txxz", timu.getTxxz());
		pageInfo.setCondition(condition);
		tiMuService.selectDataGrid(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 添加或保存
	 *
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate")
	@ResponseBody
	public RespResult<String> addOrUpdate(@RequestBody TiMuVo vo) {
		RespResult<String> result = new RespResult<String>();
		ShiroUser user = getShiroUser();
		// tiMuService.delete(vo.getId());
		// vo.setId(null);
		tiMuService.saveOrUpdate(vo, user);
		result.getSuccess("", "保存成功");
		return result;
	}

	/**
	 * 编辑页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, String id) {
		TiMu tiMu = tiMuService.selectById(id);
		model.addAttribute("tiMu", tiMu);
		return "admin/wenJuan/tiMu/tiMuAdd";
	}

	/**
	 * 编辑部门
	 *
	 * @param tiMu
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid TiMu tiMu, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		tiMuService.updateById(tiMu);
		return renderSuccess("编辑成功！");
	}

	/**
	 * 删除
	 *
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody TiMuVo vo) {
		RespResult<String> result = new RespResult<>();
		// 禁止使用物理删除
		// tiMuService.deleteById(vo.getId());
		tiMuService.delete(vo, getShiroUser());
		result.getSuccess("");
		return result;
	}

	/**
	 * 部主页
	 *
	 * @return
	 */
	@GetMapping(value = "/manager")
	public String manager() {return "admin/wenJuan/tiMu/tiMu";
	}

	/**
	 * 添加页
	 *
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(Model model, String id) {
		TiMu tiMu = new TiMu();
		if (StringUtils.isNotBlank(id)) {
			tiMu = tiMuService.selectById(id);
		}
		model.addAttribute("tiMu", tiMu);
		return "admin/wenJuan/tiMu/tiMuAdd";
	}

	/**
	 * @Author: wangjun
	 * @Description:题目搜索
	 * @Date 2017/8/1 11:34
	 */
	@RequestMapping(value = "/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByCondition(@RequestBody TiMuVo timu) {
		RespResult<PageInfo> result = new RespResult<>();
		PageInfo pageInfo = new PageInfo(timu.getPage(), timu.getRows());
		Map<String, Object> condition = new HashMap<>();
		condition.put("tmfl", timu.getTmfl());
		condition.put("showItem", timu.getShowItem());
		condition.put("bt", timu.getBt());
		condition.put("txxz", timu.getTxxz());
		pageInfo.setCondition(condition);
		tiMuService.findByCondition(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}
}
