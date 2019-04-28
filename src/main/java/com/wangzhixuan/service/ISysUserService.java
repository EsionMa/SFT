package com.wangzhixuan.service;

import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface ISysUserService extends IService<SysUser> {
    /**
	 *@author: Leslie
	 *@Date 2018/3/28 17:31
	 *@param: [userVo]
	 *@return java.util.List<com.wangzhixuan.model.SysUser>
	 *@throws
	 *@Description: 查找是拥有该登录名的用户
	 */
	List<SysUser> selectByLoginName(UserVo userVo);
    /**
	 *@author: Leslie
	 *@Date 2018/3/28 17:31
	 *@param: [userVo]
	 *@return void
	 *@throws
	 *@Description: 添加用户
	 */
	void insertByVo(UserVo userVo);
    /**
	 *@author: Leslie
	 *@Date 2018/3/28 17:31
	 *@param: [id]
	 *@return com.wangzhixuan.model.vo.UserVo
	 *@throws
	 *@Description: 根据用户id进行1查询
	 */
	UserVo selectVoById(Long id);
	/**
	 *@author: Leslie
	 *@Date 2018/3/28 17:33
	 *@param: [id]
	 *@return com.wangzhixuan.model.SysUser
	 *@throws
	 *@Description: 虽然有继承，为了缓存
	 */
	SysUser selectUserById(Serializable id);

	void updateByVo(UserVo userVo);

	void updatePwdByUserId(Long userId, String md5Hex);

	void selectDataGrid(PageInfo pageInfo);

	void deleteUserById(Long id);

	boolean hasExeZhixingQx(Long userId);

	List<SysUser> queryZxrList(Long deptId, Integer userType);

	/**
	 * @Author: Leslie
	 * @Description:根据部门id查询人员
	 * @Date 2017/8/23 16:26
	 */
	List<SysUser> getUsers(UserVo vo);

	/**
	 * 自动导入添加用户
	 * 
	 * @param users
	 * @throws SysException
	 */
	void insertUser(SysUser users) throws SysException;

	List<SysOrganization> findDeptIdByUser(Long id);
}