package com.wangzhixuan.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysResource;

/**
 *
 * SysResource 表数据服务层接口
 *
 */
public interface ISysResourceService extends IService<SysResource> {
	/**
	 * 资源列表（未使用）
	 * 
	 * @param shiroUser
	 * @return
	 */
	List<SysResource> selectAll(ShiroUser shiroUser);

	/**
	 * 查询所有菜单（未使用）
	 * 
	 * @param shiroUser
	 * @return
	 */
	List<Tree> selectAllMenu(ShiroUser shiroUser);

	/**
	 * （未使用）
	 * 
	 * @param shiroUser
	 * @return
	 */
	List<Tree> selectAllTree(ShiroUser shiroUser);

	/**
	 * 菜单树（左侧导航栏,上级资源,授权）
	 * 
	 * @param shiroUser
	 * @return
	 */
	List<Tree> selectTree(ShiroUser shiroUser);

}