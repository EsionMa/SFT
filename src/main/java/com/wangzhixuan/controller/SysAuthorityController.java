package com.wangzhixuan.controller;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.SysAuthority;
import com.wangzhixuan.model.vo.SysAuthorityVo;
import com.wangzhixuan.service.ISysAuthorityService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Leslie on 2017/12/5.
 *
 * @author: Leslie
 * @TIME:14:09
 * @Date:2017/12/5 Description: ip的修改，增加，删除 只能由system权限进行使用
 */
@Controller
@RequestMapping(value = "/ipconfig")
public class SysAuthorityController extends BaseController {
	@Autowired
	private ISysAuthorityService sysAuthorityService;

	/**
	 * 返回ip View
	 * 
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/ipconfig/ipconfig";
	}

	/**
	 * 返回排序的查询
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		sysAuthorityService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * View 跳转到添加页面
	 * 
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/ipconfig/ipAdd";
	}

	/**
	 * 编辑页跳转
	 * 
	 * @param model
	 * @param id
	 * @return
	 */

	@RequestMapping("/editPage")
	public String editPage(Model model, Long id) {
		SysAuthority sysAuthority = sysAuthorityService.selectById(id);
		SysAuthorityVo vo = new SysAuthorityVo();
		BeanUtils.copyProperties(sysAuthority, vo);
		model.addAttribute("authority", vo);
		return "admin/ipconfig/ipEdit";
	}

	/**
	 * 授权ip 时间
	 * 
	 * @param authority
	 * @param result
	 * @return
	 */
	@RequiresRoles("system")
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid SysAuthority authority, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		if (sysAuthorityService.ipIsExsit(authority.getIpAddress())) {
			return renderError("该ip已经注册");
		}
		sysAuthorityService.insertWithSalt(authority, getShiroUser());
		return renderSuccess("添加成功！");
	}

	/**
	 * 删除ip
	 * 
	 * @param id
	 * @return
	 */
	@RequiresRoles("system")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(Long id) {
		sysAuthorityService.deleteById(id);
		return renderSuccess("删除成功!");
	}

	/**
	 *
	 * @param vo
	 * @param result
	 * @return
	 */
	@RequiresRoles("system")
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid SysAuthorityVo vo, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		SysAuthority authority = new SysAuthority();
		BeanUtils.copy(vo, authority);
		sysAuthorityService.updateIpById(authority, getShiroUser());
		return renderSuccess("编辑成功！");
	}
}
