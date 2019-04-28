package com.wangzhixuan.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User 表数据库控制层接口
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	/**
	 * 查询用户详细
	 * 
	 * @param id
	 * @return
	 */
	UserVo selectUserVoById(@Param("id") Long id);

	/**
	 * 用户列表（分页）
	 * 
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectUserPage(Pagination page, Map<String, Object> params);

	List<SysUser> hasResourceUrlUser(@Param("resourceUrl") String resourceUrl, @Param("deptId") Long deptId,
			@Param("userType") Integer userType, @Param("userId") Long userId);

	List<SysOrganization> findDeptByUser(@Param("id") Long id);
}