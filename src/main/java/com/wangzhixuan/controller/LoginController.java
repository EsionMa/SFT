package com.wangzhixuan.controller;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.service.ISysAuthorityService;
import com.wangzhixuan.service.ISysResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.csrf.CsrfToken;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.captcha.DreamCaptcha;
import com.wangzhixuan.commons.utils.StringUtils;

/**
 * @author LuoQiang
 * @ClassName: LoginController
 * @Description:
 * @date 2017年5月23日 上午9:50:18
 */
@Controller
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private DreamCaptcha dreamCaptcha;
	@Autowired
	private ISysResourceService sysResourceService;
	@Autowired
	ISysAuthorityService sysAuthorityService;

	@RequestMapping(value = "/api/login")
	@CsrfToken(remove = true)
	@ResponseBody
	public RespResult<Map<String, Object>> postLogin(HttpServletRequest request, @RequestBody Map<String, String> req)
			throws Exception {
		RespResult<Map<String, Object>> result = new RespResult<Map<String, Object>>();
		logger.info("API POST请求登录");
		if (req == null) {
			throw new SysException(ErrorCode.LoginUserIsNull);
		}
		String username = req.get("u");
		String password = req.get("p");
		// 改为全部抛出异常，避免ajax csrf token被刷新
		if (StringUtils.isBlank(username)) {
			throw new SysException(ErrorCode.LoginUserIsNull);
		}
		if (StringUtils.isBlank(password)) {
			throw new SysException(ErrorCode.LoginUserIsNull);
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// AppToken token=new AppToken(username, password, null);
		// 设置记住密码
		try {
			user.login(token);
			Serializable id = SecurityUtils.getSubject().getSession().getId();
			Map<String, Object> map = new HashMap<>();
			String tokenStr = null;
			if (id != null) {
				tokenStr = id.toString();
			}
			List<Tree> trees = sysResourceService.selectTree(getShiroUser());
			map.put("trees", trees);
			map.put("__token", tokenStr);
			map.put("shiroUser", getShiroUser());
			// 盒子授权
			map.put("useBox", sysAuthorityService.ipAndTimeIsCorrect(request.getRemoteAddr()));
			result.getSuccess(map);
			return result;
		} catch (UnknownAccountException e) {
			// throw new SysException(ErrorCode.LoginFail, e);
			throw new RuntimeException("账号不存在！", e);
		} catch (DisabledAccountException e) {
			// throw new SysException(ErrorCode.LoginFail, e);
			throw new RuntimeException("账号未启用！", e);
		} catch (IncorrectCredentialsException e) {
			// throw new SysException(ErrorCode.LoginFail, e);
			throw new RuntimeException("账号或密码错误！", e);
		} catch (Throwable e) {
			throw new SysException(e.getMessage(), e);
		}

	}

	/**
	 * 退出
	 *
	 * @return {Result}
	 */
	@PostMapping("/api/logout")
	@ResponseBody
	public RespResult<String> postLoginout() {
		RespResult<String> result = new RespResult<>();
		logger.info("登出");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		result.getSuccess("");
		return result;
	}

	/**
	 * 首页
	 *
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "redirect:/index";
	}

	/**
	 * 首页
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	/**
	 * GET 登录
	 *
	 * @return {String}
	 */
	@GetMapping("/login")
	@CsrfToken(create = true)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	/**
	 * POST 登录 shiro 写法
	 *
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return {Object}
	 */
	@PostMapping("/login")
	@ResponseBody
	public Object loginPost(HttpServletRequest request, HttpServletResponse response, String username, String password,
			String captcha, @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {
		logger.info("POST请求登录");
		// 改为全部抛出异常，避免ajax csrf token被刷新
		if (StringUtils.isBlank(username)) {
			throw new RuntimeException("用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			throw new RuntimeException("密码不能为空");
		}
		if (StringUtils.isBlank(captcha)) {
			throw new RuntimeException("验证码不能为空");
		}
		if (!dreamCaptcha.validate(request, response, captcha)) {
			throw new RuntimeException("验证码错误");
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 设置记住密码
		token.setRememberMe(1 == rememberMe);
		try {
			user.login(token);
			return renderSuccess();
		} catch (UnknownAccountException e) {
			throw new RuntimeException("账号不存在！", e);
		} catch (DisabledAccountException e) {
			throw new RuntimeException("账号未启用！", e);
		} catch (IncorrectCredentialsException e) {
			throw new RuntimeException("密码错误！", e);
		} catch (Throwable e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 未授权
	 *
	 * @return {String}
	 */
	@RequestMapping(value = "unauth")
	public void unauth(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("没有权限");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println("{}");
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 退出
	 *
	 * @return {Result}
	 */
	@PostMapping("/logout")
	@ResponseBody
	public Object logout() {
		logger.info("登出");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return renderSuccess();
	}
}
