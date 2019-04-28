package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysResource;
import com.wangzhixuan.model.SysRole;

/**
 *
 * Role 表数据库控制层接口
 *
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<Long> selectResourceIdListByRoleId(@Param("id") Long id);

	/**
	 * 多个角色拥有的权限
	 * 
	 * @param list
	 * @return
	 */
	List<SysResource> selectResourceListByRoleIdList(@Param("list") List<Long> list);

	/**
	 * 查询角色url集合
	 * 
	 * @param roleId
	 * @return
	 */
	List<Map<Long, String>> selectResourceListByRoleId(@Param("id") Long roleId);

	/**
	 * 根据userId查询角色列表（按seq顺序排列）
	 * 
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRolesByUserId(@Param("userId") Long userId);
}