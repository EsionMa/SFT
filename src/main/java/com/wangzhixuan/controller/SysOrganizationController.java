package com.wangzhixuan.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.service.ISysOrganizationService;

/**
 * @description：部门管理
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/organization")
public class SysOrganizationController extends BaseController {

	@Autowired
	private ISysOrganizationService sysOrganizationService;

	@PostMapping(value = "/getDepts")
	@ResponseBody
	public RespResult<List<SysOrganization>> getDepts(@RequestBody Map<String, Object> vo) {
		RespResult<List<SysOrganization>> result = new RespResult<>();
		try {
			Object parentIdObj = vo.get("parentId");
			Object typeObj = vo.get("type");
			Long parentId = null;
			String type = null;
			if (parentIdObj != null) {
				String parentIdStr = parentIdObj.toString();
				if (!NumberUtils.isDigits(parentIdStr)) {
					throw new SysException("DEPT传入参数异常PrentID");
				}
			}
			if (typeObj != null) {
				type = typeObj.toString();
			}
			List<SysOrganization> depts = sysOrganizationService.getDepts(parentId, type);
			result.getSuccess(depts);
		} catch (Exception e) {
			if (e instanceof SysException) {
				throw e;
			}
			result.getFail("系统异常");
		}
		return result;
	}
	@RequestMapping(value = "/getDeparts")
	@ResponseBody
   public RespResult<List<SysOrganization>> getDeptsByType(@RequestBody Map<String,Object> map){
		RespResult<List<SysOrganization>> result=new RespResult<>();
		result.getSuccess(sysOrganizationService.getDeparts(map));
		return result;
   }
	/**
	 * 部门管理主页
	 *
	 * @return
	 */
	@GetMapping(value = "/manager")
	public String manager() {
		return "admin/organization/organization";
	}

	/**
	 * 部门资源树 ;
	 * 
	 * @return
	 */
	@PostMapping(value = "/treeGrid")
	@ResponseBody
	public Object treeGrid() {
		return sysOrganizationService.selectTreeGrid();
	}

	/**
	 * 部门资源树 ;
	 * 
	 * @return
	 */
	@PostMapping(value = "/tree")
	@ResponseBody
	public Object tree() {
		return sysOrganizationService.selectTree();
	}

	/**
	 * 添加部门页
	 *
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage() {
		return "admin/organization/organizationAdd";
	}

	/**
	 * 添加部门
	 *
	 * @param organization
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@Valid SysOrganization organization, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		organization.setCreateTime(new Date());
		sysOrganizationService.addDept(organization);
		return renderSuccess("添加成功！");
	}

	/**
	 * 编辑部门页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		SysOrganization organization = sysOrganizationService.selectById(id);
		model.addAttribute("organization", organization);
		return "admin/organization/organizationEdit";
	}

	/**
	 * 编辑部门
	 *
	 * @param organization
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid SysOrganization organization, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		sysOrganizationService.updateById(organization);
		return renderSuccess("编辑成功！");
	}

	/**
	 * 删除部门
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		sysOrganizationService.deleteById(id);
		return renderSuccess("删除成功！");
	}
}