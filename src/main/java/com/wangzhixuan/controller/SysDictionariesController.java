package com.wangzhixuan.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.service.ISysDictionariesService;

/**
 * 字典表管理
 * 
 * @author 37355
 *
 */
@Controller
@RequestMapping("/sysDictionaries")
public class SysDictionariesController extends BaseController {

	@Autowired
	private ISysDictionariesService sysDictionariesService;

	/**
	 * 部主页
	 *
	 * @return
	 */
	@GetMapping(value = "/manager")
	public String manager() {
		return "admin/sysDictionaries/sysDictionaries";
	}

	/**
	 * 添加部门页
	 *
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(Model model, String id) {
		SysDictionaries sysDictionaries=new SysDictionaries();
		if (StringUtils.isNotBlank(id)) {
			sysDictionaries = sysDictionariesService.selectById(id);
		}
		model.addAttribute("sysDictionaries", sysDictionaries);
		return "admin/sysDictionaries/sysDictionariesAdd";
	}
	@ResponseBody
	@RequestMapping(value="/listByParentCode")
	public Object listByParentCode(String parentCode){
		return sysDictionariesService.listByParentCode(parentCode);
	}
	@ResponseBody
	@RequestMapping(value="/queryByParentCode")
	public RespResult<ArrayList<SysDictionaries>> queryByParentCode(@RequestBody Map<String, Object> map){
		Object parentCodeObj = map.get("parentCode");
		if(parentCodeObj==null){
			throw new SysException(ErrorCode.ReqParentCodeIsNull);
		}
		RespResult<ArrayList<SysDictionaries>>  result=new RespResult<ArrayList<SysDictionaries>>();
		result.getSuccess((ArrayList)sysDictionariesService.listByParentCode(parentCodeObj.toString()));
		return result;
	}
	/**
	 * 列表
	 *
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public Object treeGrid() {
		return sysDictionariesService.selectTreeGrid();
	}
	
	/**
	 * 添加
	 *
	 * @param dictionaries
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@Valid SysDictionaries sysDictionaries, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		ShiroUser shiroUser = getShiroUser();
		if(sysDictionaries.getId()==null){
			sysDictionaries.setCreateUserId(shiroUser.getId());
			sysDictionaries.setCreateTime(new Date());
			sysDictionariesService.save(sysDictionaries);
			return renderSuccess("添加成功！");
		}else{
			sysDictionaries.setUpdateTime(new Date());
			sysDictionaries.setUpdateUserId(shiroUser.getId());
			sysDictionariesService.updateById(sysDictionaries);
			return renderSuccess("修改成功");
		}
	}

	/**
	 * 编辑页
	 *
	 * @param request
	 * @param id
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, String id) {
		SysDictionaries sysDictionaries = sysDictionariesService.selectById(id);
		model.addAttribute("sysDictionaries", sysDictionaries);
		return "admin/sysDictionaries/sysDictionariesAdd";
	}

	/**
	 * 编辑部门
	 *
	 * @param sysDictionaries
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid SysDictionaries sysDictionaries, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		sysDictionariesService.updateById(sysDictionaries);
		return renderSuccess("编辑成功！");
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		Long dId=Long.parseLong(id);
		sysDictionariesService.deleteById(dId);
		return renderSuccess("删除成功！");
	}
}