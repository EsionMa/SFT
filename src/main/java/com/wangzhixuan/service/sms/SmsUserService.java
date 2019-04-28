package com.wangzhixuan.service.sms;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
public interface SmsUserService extends IService<SysUser> {
	/**
	 * 院内职工列表
	 * 
	 * @param vo
	 * @param shiroUser
	 * @return
	 * @throws SysException
	 */
	PageInfo selectUser(UserVo vo, ShiroUser shiroUser) throws SysException;

	/**
	 *  查询用户
	 * @param id
	 * @return
	 * @throws SysException
	 */
	UserVo findById(Long id) throws SysException;

}
