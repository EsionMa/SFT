package com.wangzhixuan.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.PasswordHash;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.mapper.SysUserOrganizationMapper;
import com.wangzhixuan.mapper.SysUserRoleMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.SysUserOrganization;
import com.wangzhixuan.model.SysUserRole;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.ISysRoleService;
import com.wangzhixuan.service.ISysUserRoleService;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.util.HanYuUtil;

/**
 * User 表数据服务层接口实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private ISysRoleService roleService;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserOrganizationMapper userOrganizationMapper;

	@Override
	public List<SysUser> selectByLoginName(UserVo userVo) {
		SysUser user = new SysUser();
		user.setLoginName(userVo.getLoginName());
		EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>(user);
		if (null != userVo.getId()) {
			wrapper.where("id != {0}", userVo.getId());
		}
		return this.selectList(wrapper);
	}

	@Override
	public void insertByVo(UserVo userVo) {
		SysUser user = BeanUtils.copy(userVo, SysUser.class);
		user.setCreateTime(new Date());
		this.insert(user);

		Long id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		SysUserRole userRole = new SysUserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public UserVo selectVoById(Long id) {
		UserVo userVo = userMapper.selectUserVoById(id);
		if (userVo != null) {
			List<Long> roleIdList = new ArrayList<>();
			String[] roleIdArr = userVo.getRoleIds().split(",");
			for (String roleId : roleIdArr) {
				roleIdList.add(Long.parseLong(roleId));
			}
			userVo.setRoleIdList(roleIdList);
			List<Long> organizationIdList = new ArrayList<>();
			//用户角色列表,预防空指针异常
			if (StringUtils.isNotBlank(userVo.getOrganizationIds())){
				String[] organizationIdArr = userVo.getOrganizationIds().split(",");
				for (String organizationId : organizationIdArr) {
					organizationIdList.add(Long.parseLong(organizationId));
				}
				userVo.setOrganizationIdList(organizationIdList);
			}
		}
		return userVo;
	}

	@Override
	@Cacheable(value = "userCache",key = "#id")
	public SysUser selectUserById(Serializable id) {
		if (id==null){
			throw new SysException("用户id不能为空");
		}
		return userMapper.selectById(id);
	}

	@Override
	public void updateByVo(UserVo userVo) {
		SysUser user = BeanUtils.copy(userVo, SysUser.class);
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		}
		this.updateById(user);
		Long userId = userVo.getId();
		// 更新用户部门（先删除后新增）
		EntityWrapper<SysUserOrganization> wrapper = new EntityWrapper<>();
		wrapper.where("user_id={0}", userId);
		List<SysUserOrganization> userOrganizations = userOrganizationMapper.selectList(wrapper);
		if (userOrganizations != null && !userOrganizations.isEmpty()) {
			for (SysUserOrganization userOrganization : userOrganizations) {
				userOrganizationMapper.deleteById(userOrganization.getId());
			}
		}
		String[] organizations = userVo.getOrganizationIds().split(",");
		SysUserOrganization userOrganization = new SysUserOrganization();
		for (String string : organizations) {
			userOrganization.setUserId(userId);
			userOrganization.setOrganizationId(Long.valueOf(string));
			userOrganizationMapper.insert(userOrganization);
		}
		// 更新用户角色（先删除后新增）
		List<SysUserRole> userRoles = userRoleMapper.selectByUserId(userId);
		if (userRoles != null && !userRoles.isEmpty()) {
			for (SysUserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}
		String[] roles = userVo.getRoleIds().split(",");
		SysUserRole userRole = new SysUserRole();
		for (String string : roles) {
			userRole.setUserId(userId);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public void updatePwdByUserId(Long userId, String md5Hex) {
		SysUser user = new SysUser();
		user.setId(userId);
		user.setPassword(md5Hex);
		this.updateById(user);
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		if (StringUtils.isNotBlank(pageInfo.getSort())) {
			page.setOrderByField(pageInfo.getSort());
		}
		if (StringUtils.isNotBlank(pageInfo.getOrder())) {
			page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		}
		List<Map<String, Object>> userMapList = userMapper.selectUserPage(page, pageInfo.getCondition());
		pageInfo.setRows(userMapList);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void deleteUserById(Long id) {
		this.deleteById(id);
		userRoleMapper.deleteByUserId(id);
	}

	@Override
	public boolean hasExeZhixingQx(Long userId) {
		SysUser sysUser = selectById(userId);
		if (sysUser == null) {
			throw new SysException("不存在的执行用户");
		}
		Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(userId);
		Set<String> urls = resourceMap.get("urls");
		if (urls == null || !urls.contains("suifangfanganzhixing")) {
			throw new SysException("该用户没有随访执行权限");
		}
		return true;
	}

	@Override
	public List<SysUser> queryZxrList(Long deptId, Integer userType) {
		List<SysUser> users = userMapper.hasResourceUrlUser("suifangfanganzhixing", deptId, userType, null);
		return users;
	}

	/**
	 * @Author: Leslie
	 * @Description:根据部门id查询人员
	 * @Date 2017/8/23 16:26
	 */
	@Override
	public List<SysUser> getUsers(UserVo vo) {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
		if (vo.getUserType() == null) {
			throw new SysException("人员类型不能为空");
		}
		 if (vo.getOrganizationId() == null) {
			 throw new SysException("科室ID不能为空");
		 }

		return userOrganizationMapper.selected(vo.getOrganizationId(),vo.getUserType());
	}

	@Override
	public void insertUser(SysUser sysUser) throws SysException {
		String loginName = getDifferentLoginName(HanYuUtil.getPinYin(sysUser.getName()));
		sysUser.setLoginName(loginName);
		String salt = StringUtils.getUUId();
		sysUser.setSalt(salt);
		String pwd = passwordHash.toHex("123", salt);
		sysUser.setPassword(pwd);
		insert(sysUser);
		Long userId = sysUser.getId();
		// 设定默认用户角色-随访坐席
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(userId);
		sysUserRole.setRoleId(Long.parseLong("7"));
		sysUserRoleService.insert(sysUserRole);

	}

	@Override
	public List<SysOrganization> findDeptIdByUser(Long id) {
		List<SysOrganization> sysOrganizations = userMapper.findDeptByUser(id);
		if (sysOrganizations == null || sysOrganizations.size() == 0) {
			return null;
		} else {
			return sysOrganizations;
		}
	}

	/**
	 * 获取不同的登录名
	 * 
	 * @param loginName
	 * @return
	 */
	private String getDifferentLoginName(String loginName) {
		UserVo userVo = new UserVo();
		userVo.setLoginName(loginName);
		List<SysUser> list = selectByLoginName(userVo);
		if (list != null && !list.isEmpty()) {
			String newLoginName = loginName + (int) (Math.random() * 100);
			return getDifferentLoginName(newLoginName);
		} else {
			return loginName;
		}
	}

}