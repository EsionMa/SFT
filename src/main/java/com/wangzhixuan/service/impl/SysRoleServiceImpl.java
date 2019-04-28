package com.wangzhixuan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.SysRoleMapper;
import com.wangzhixuan.mapper.SysRoleResourceMapper;
import com.wangzhixuan.mapper.SysUserRoleMapper;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysRoleResource;
import com.wangzhixuan.service.ISysRoleService;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private SysRoleResourceMapper roleResourceMapper;

	public List<SysRole> selectAll(ShiroUser shiroUser) {
		// shiro中缓存的用户最高角色
		SysRole role = shiroUser.getRole();
		EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
		wrapper.where("seq>={0}", role.getSeq());
		wrapper.orderBy("seq");
		return roleMapper.selectList(wrapper);
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo, ShiroUser shiroUser) {
		// shiro中缓存的用户最高角色
		SysRole role = shiroUser.getRole();
		Page<SysRole> page = new Page<SysRole>(pageInfo.getNowpage(), pageInfo.getSize());
		EntityWrapper<SysRole> wrapper = new EntityWrapper<SysRole>();
		wrapper.where("seq>={0}", role.getSeq());
		wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
		selectPage(page, wrapper);
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public Object selectTree(ShiroUser shiroUser) {
		List<Tree> trees = new ArrayList<Tree>();
		List<SysRole> roles = this.selectAll(shiroUser);
		for (SysRole role : roles) {
			Tree tree = new Tree();
			tree.setId(role.getId());
			tree.setText(role.getName());

			trees.add(tree);
		}
		return trees;
	}

	@Override
	public void updateRoleResource(Long roleId, String resourceIds) {
		// 先删除后添加,有点爆力
		SysRoleResource roleResource = new SysRoleResource();
		roleResource.setRoleId(roleId);
		roleResourceMapper.delete(new EntityWrapper<SysRoleResource>(roleResource));

		String[] resourceIdArray = resourceIds.split(",");
		for (String resourceId : resourceIdArray) {
			roleResource = new SysRoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(Long.parseLong(resourceId));
			roleResourceMapper.insert(roleResource);
		}
	}

	@Override
	public List<Long> selectResourceIdListByRoleId(Long id) {
		return roleMapper.selectResourceIdListByRoleId(id);
	}

	@Override
	public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
		Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
		List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(userId);
		Set<String> urlSet = new HashSet<String>();
		Set<String> roles = new HashSet<String>();
		// 循环得到资源列表
		for (Long roleId : roleIdList) {
			List<Map<Long, String>> resourceList = roleMapper.selectResourceListByRoleId(roleId);
			if (resourceList != null) {
				for (Map<Long, String> map : resourceList) {
					if (StringUtils.isNotBlank(map.get("url"))) {
						urlSet.add(map.get("url"));
					}
				}
			}
			// 添加角色列表
			SysRole role = roleMapper.selectById(roleId);
			if (role != null) {
				roles.add(role.getName());
			}
		}
		resourceMap.put("urls", urlSet);
		resourceMap.put("roles", roles);
		return resourceMap;
	}

	@Override
	public SysRole selectHighestRoleByUserId(Long userId) {
		List<SysRole> roleList = roleMapper.selectRolesByUserId(userId);
		if (roleList != null && roleList.size() > 0) {
			return roleList.get(0);
		}
		return null;
	}
}