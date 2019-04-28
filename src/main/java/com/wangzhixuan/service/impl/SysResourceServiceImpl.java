package com.wangzhixuan.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.wangzhixuan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysResource;
import com.wangzhixuan.service.ISysResourceService;

/**
 *
 * SysResource 表数据服务层接口实现类
 *
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {
	private static final int RESOURCE_MENU = 0; // 菜单
	private static final int RESOURCE_BTN = 1; // 按钮

	@Autowired
	private SysResourceMapper resourceMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysRoleResourceMapper roleResourceMapper;

	@Override
	public List<SysResource> selectAll(ShiroUser shiroUser) {
		// shiro中缓存的用户角色
		Set<String> roles = shiroUser.getRoles();
		EntityWrapper<SysResource> wrapper = new EntityWrapper<SysResource>();
		wrapper.orderBy("seq");
		if (!roles.contains("system")) {
			wrapper.where("url!='/ipconfig/manager'");
			wrapper.where("url!='/ipconfig/add'");
			wrapper.where("url!='/ipconfig/edit'");
			wrapper.where("url!='/ipconfig/delete'");
		}
		return resourceMapper.selectList(wrapper);
	}

	private List<SysResource> selectByType(String role) {
		EntityWrapper<SysResource> wrapper = new EntityWrapper<SysResource>();
		SysResource resource = new SysResource();
		wrapper.setEntity(resource);
		// wrapper.where("resource_type = {0}", type);
		if (!"system".equals(role)) {
			wrapper.where("url!='/ipconfig/manager'");
			wrapper.where("url!='/ipconfig/add'");
			wrapper.where("url!='/ipconfig/edit'");
			wrapper.where("url!='/ipconfig/delete'");
		}
		wrapper.orderBy("seq");
		return resourceMapper.selectList(wrapper);
	}

	@Override
	public List<Tree> selectAllMenu(ShiroUser shiroUser) {
		// shiro中缓存的用户角色
		Set<String> roles = shiroUser.getRoles();
		List<Tree> trees = new ArrayList<Tree>();
		// 查询所有菜单
		List<SysResource> resources = new ArrayList<>();
		// if (roles.contains("system")) {
		// // 系统管理员
		// resources = this.selectByType(RESOURCE_MENU, "system");
		// } else {
		// resources = this.selectByType(RESOURCE_MENU, null);
		// }
		// if (resources == null) {
		// return trees;
		// }
		for (SysResource resource : resources) {
			Tree tree = new Tree();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setState(resource.getOpened());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<Tree> selectAllTree(ShiroUser shiroUser) {
		// 获取所有的资源 tree形式，展示
		List<Tree> trees = new ArrayList<Tree>();
		List<SysResource> resources = this.selectAll(shiroUser);
		if (resources == null) {
			return trees;
		}
		for (SysResource resource : resources) {
			Tree tree = new Tree();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setState(resource.getOpened());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<Tree> selectTree(ShiroUser shiroUser) {
		List<Tree> trees = new ArrayList<Tree>();
		// shiro中缓存的用户角色
		Set<String> roles = shiroUser.getRoles();
		if (roles == null || roles.size() < 1) {
			return trees;
		}
		if (roles.contains("system")) {
			// 系统管理员
			List<SysResource> resourceList = this.selectByType("system");
			// List<SysResource> resourceList = this.selectByType(RESOURCE_MENU,
			// "system");
			// if (resourceList == null) {
			// resourceList = new ArrayList<>();
			// }
			// List<SysResource> btnResources = this.selectByType(RESOURCE_BTN,
			// "system");
			// if (btnResources != null && btnResources.size() > 0) {
			// resourceList.addAll(btnResources);
			// }
			if (resourceList == null || resourceList.size() < 1) {
				return trees;
			}
			for (SysResource resource : resourceList) {
				Tree tree = new Tree();
				tree.setResourceType(resource.getResourceType());
				tree.setId(resource.getId());
				tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());
				trees.add(tree);
			}
		} else if (roles.contains("admin")) {
			// 管理员
			List<SysResource> resourceList = this.selectByType("admin");
			// List<SysResource> resourceList = this.selectByType(RESOURCE_MENU,
			// "admin");
			// if (resourceList == null) {
			// resourceList = new ArrayList<>();
			// }
			// List<SysResource> btnResources = this.selectByType(RESOURCE_BTN,
			// "admin");
			// if (btnResources != null && btnResources.size() > 0) {
			// resourceList.addAll(btnResources);
			// }
			if (resourceList == null || resourceList.size() < 1) {
				return trees;
			}
			for (SysResource resource : resourceList) {
				Tree tree = new Tree();
				tree.setResourceType(resource.getResourceType());
				tree.setId(resource.getId());
				tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());
				trees.add(tree);
			}
		} else {
			// 普通用户
			List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
			if (roleIdList == null || roleIdList.size() < 1) {
				return trees;
			}
			List<SysResource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
			if (resourceLists == null || resourceLists.size() < 1) {
				return trees;
			}
			for (SysResource resource : resourceLists) {
				Tree tree = new Tree();
				tree.setResourceType(resource.getResourceType());
				tree.setId(resource.getId());
				tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public boolean deleteById(Serializable resourceId) {
		roleResourceMapper.deleteByResourceId(resourceId);
		return super.deleteById(resourceId);
	}

}