package com.wangzhixuan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.PasswordHash;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.ISysUserService;

/**
 * @description：用户管理
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
	@Autowired
	private ISysUserService userService;
	@Autowired
	private PasswordHash passwordHash;

	private static  final Logger logger= LoggerFactory.getLogger(SysUserController.class);

	/**
	 * 用户管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/user/user";
	}

	/**
	 * 用户管理列表
	 *
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userVo.getLoginName())) {
			condition.put("loginName", userVo.getLoginName());
		}
		if (StringUtils.isNotBlank(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null) {
			condition.put("organizationId", userVo.getOrganizationId());
		}
		if (userVo.getCreatedateStart() != null) {
			condition.put("startTime", userVo.getCreatedateStart());
		}
		if (userVo.getCreatedateEnd() != null) {
			condition.put("endTime", userVo.getCreatedateEnd());
		}
		// shiro中缓存的用户最高角色
		SysRole role = getShiroUser().getRole();
		condition.put("seq", role.getSeq());
		pageInfo.setCondition(condition);
		userService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加用户页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/user/userAdd";
	}

	/**
	 * 添加用户
	 *
	 * @param userVo
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid UserVo userVo, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		// shiro中缓存的用户最高角色
		SysRole role = getShiroUser().getRole();
		// 管理人员及以上角色
		if (role.getSeq() > 2) {
			return renderError("当前账号无权添加用户");
		}
		if ("system".equals(userVo.getLoginName())) {
			return renderError("禁止添加system账号");
		}
		List<SysUser> list = userService.selectByLoginName(userVo);
		if (list != null && !list.isEmpty()) {
			return renderError("登录名已存在!");
		}
		String salt = StringUtils.getUUId();
		String pwd = passwordHash.toHex(userVo.getPassword(), salt);
		userVo.setSalt(salt);
		userVo.setPassword(pwd);
		userService.insertByVo(userVo);
		return renderSuccess("添加成功");
	}

	/**
	 * 编辑用户页
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		UserVo userVo = userService.selectVoById(id);
		model.addAttribute("organizationIds", userVo.getOrganizationIdList());
		model.addAttribute("roleIds", userVo.getRoleIdList());
		model.addAttribute("user", userVo);
		return "admin/user/userEdit";
	}

	/**
	 * 编辑用户
	 *
	 * @param userVo
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid UserVo userVo, BindingResult result) {
		if (result.hasErrors()) {
			return renderError(result);
		}
		// shiro中缓存的用户最高角色
		SysRole role = getShiroUser().getRole();
		if (role.getSeq() > 2) {
			return renderError("当前账号无权编辑用户");
		}
		List<SysUser> list = userService.selectByLoginName(userVo);
		if (list != null && !list.isEmpty()) {
			return renderError("登录名已存在!");
		}
		// 更新密码
		if (StringUtils.isNotBlank(userVo.getPassword())) {
			SysUser user = userService.selectById(userVo.getId());
			String salt = user.getSalt();
			String pwd = passwordHash.toHex(userVo.getPassword(), salt);
			userVo.setPassword(pwd);
			Subject subject=SecurityUtils.getSubject();
			ShiroUser shiroUser= (ShiroUser) subject.getPrincipals().getPrimaryPrincipal();
			/**
			 * 判断是否是当前登录用户修改自己的信息
			 */
			if (user.getId().equals(shiroUser.getId())){
				userService.updateByVo(userVo);
				subject.logout();
				renderSuccess("修改成功，已退出当前用户,请重新进行登录");
			}
		}
		userService.updateByVo(userVo);
		return renderSuccess("修改成功!");
	}

	/**
	 * 修改密码页
	 *
	 * @return
	 */
	@GetMapping("/editPwdPage")
	public String editPwdPage() {
		return "admin/user/userEditPwd";
	}

	/**
	 *@author: Leslie
	 *@Date 2018/4/8 10:23
	 *@param: [oldPwd, pwd]
	 *@return java.lang.Object
	 *@throws
	 *@Description: 当前用户修密码，并让其退出登录
	 */
	@PostMapping("/editUserPwd")
	@ResponseBody
	public Object editUserPwd(String oldPwd, String pwd) {
		SysUser user = userService.selectById(getUserId());
		String salt = user.getSalt();
		if (!user.getPassword().equals(passwordHash.toHex(oldPwd, salt))) {
			return renderError("老密码不正确!");
		}
		userService.updatePwdByUserId(getUserId(), passwordHash.toHex(pwd, salt));
		logger.info("用户{}修改密码",user.getName());
		/**
		 * 退出当前登录用户
		 */
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return renderSuccess("修改成功，已退出当前用户,请重新进行登录");
	}

	/**
	 * 删除用户
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		// shiro中缓存的用户最高角色
		SysRole role = getShiroUser().getRole();
		if (role.getSeq() > 2) {
			return renderError("当前账号无权删除用户");
		}
		Long currentUserId = getUserId();
		// java 数值类型常量池范围为-128到127 ==号只能判断小于该值的 @See LongCache
		if (currentUserId.equals(id)) {
			return renderError("不可以删除自己！");
		}
		userService.deleteUserById(id);
		return renderSuccess("删除成功！");
	}

	@RequestMapping(value = "/queryZxrList")
	@ResponseBody
	public RespResult<List<SysUser>> queryZxrList(@RequestBody Map<String, Object> map) {
		Object deptIdObj = map.get("deptId");
		if (deptIdObj == null) {
			throw new SysException("部门不能为空");
		}
		Long deptId = null;
		try {
			deptId = Long.parseLong(deptIdObj.toString());
		} catch (Exception e) {

		}
		if (deptId == null) {
			throw new SysException("部门不能为空");
		}
		Integer userType = null;
		if (map.get("userType") != null) {
			userType = Integer.parseInt(map.get("userType").toString());
		}
		RespResult<List<SysUser>> result = new RespResult<>();
		List<SysUser> sysUsers = userService.queryZxrList(deptId, userType);
		result.getSuccess(sysUsers);
		return result;

	}

	/**
	 * @Author: Leslie
	 * @Description:根据科室id查询人员
	 * @Date 2017/8/23 16:16
	 */
	@ResponseBody
	@RequestMapping("/getUser")
	public RespResult<List<SysUser>> findUsersByOrgId(@RequestBody Map<String, Object> map) {
		RespResult<List<SysUser>> result = new RespResult<>();
		UserVo userVo = BeanUtils.mapToBean(map, UserVo.class);
		if (userVo == null) {
			userVo = new UserVo();
		}
		List<SysUser> list = userService.getUsers(userVo);
		result.getSuccess(list);
		return result;
	}
}
