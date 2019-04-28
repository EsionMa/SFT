package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysRole;

/**
 *
 * Role 表数据服务层接口
 *
 */
public interface ISysRoleService extends IService<SysRole> {

	void selectDataGrid(PageInfo pageInfo, ShiroUser shiroUser);

	Object selectTree(ShiroUser shiroUser);

	List<Long> selectResourceIdListByRoleId(Long id);

	void updateRoleResource(Long id, String resourceIds);

	/**
	 * 查詢用户角色和url集合
	 * 
	 * @param userId
	 * @return
	 */
	Map<String, Set<String>> selectResourceMapByUserId(Long userId);

	/**
	 * 查询用户最高角色
	 * 
	 * @param userId
	 * @return
	 */
	SysRole selectHighestRoleByUserId(Long userId);

}