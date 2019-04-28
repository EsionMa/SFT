package com.wangzhixuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysUserRole;

/**
 *
 * SysUserRole 表数据库控制层接口
 *
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	List<SysUserRole> selectByUserId(@Param("userId") Long userId);

	/**
	 * 查询用户拥有的角色ID列表
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select role_id AS roleId from sys_user_role where user_id = #{userId}")
	@ResultType(Long.class)
	List<Long> selectRoleIdListByUserId(@Param("userId") Long userId);

	@Delete("DELETE FROM sys_user_role WHERE user_id = #{userId}")
	int deleteByUserId(@Param("userId") Long userId);

}