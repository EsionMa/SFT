package com.wangzhixuan.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysResource;
import com.wangzhixuan.service.ISysResourceService;

/**
 * @description：资源管理
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/resource")
public class SysResourceController extends BaseController {

	@Autowired
	private ISysResourceService resourceService;

	/**
	 * 菜单树（左侧导航栏,上级资源,授权）
	 *
	 * @return
	 */
	@PostMapping("/tree")
	@ResponseBody
	public Object tree() {
		ShiroUser shiroUser = getShiroUser();
		return resourceService.selectTree(shiroUser);
	}

	/**
	 * 菜单树
	 *
	 * @return
	 */
	@PostMapping("/menuTree")
	@ResponseBody
	public RespResult<List<Tree>> menuTree() {
		RespResult<List<Tree>> result = new RespResult<>();
		ShiroUser shiroUser = getShiroUser();
		List<Tree> trees = resourceService.selectTree(shiroUser);
		result.getSuccess(trees);
		return result;
	}

	/**
	 * 资源管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/resource/resource";
	}

	/**
	 * 资源管理列表
	 *
	 * @return
	 */
	@PostMapping("/treeGrid")
	@ResponseBody
	public Object treeGrid() {
		return resourceService.selectAll(getShiroUser());
	}

	/**
	 * 添加资源页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/resource/resourceAdd";
	}

	/**
	 * 添加资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@Valid SysResource resource, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		resource.setCreateTime(new Date());
		// 选择菜单时将openMode设置为null
		Integer type = resource.getResourceType();
		if (null != type && type == 0) {
			resource.setOpenMode(null);
		}
		resourceService.insert(resource);
		return renderSuccess("添加成功！");
	}

	/**
	 * 查询所有的菜单(未使用)
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public Object allMenu() {
		return resourceService.selectAllMenu(getShiroUser());
	}

	/**
	 * 查询所有的资源tree(未使用)
	 */
	@RequestMapping("/allTrees")
	@ResponseBody
	public Object allTree() {
		return resourceService.selectAllTree(getShiroUser());
	}

	/**
	 * 编辑资源页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, Long id) {
		SysResource resource = resourceService.selectById(id);
		model.addAttribute("resource", resource);
		return "admin/resource/resourceEdit";
	}

	/**
	 * 编辑资源
	 *
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid SysResource resource, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		resourceService.updateById(resource);
		return renderSuccess("编辑成功！");
	}

	/**
	 * 删除资源
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		resourceService.deleteById(id);
		return renderSuccess("删除成功！");
	}

}
