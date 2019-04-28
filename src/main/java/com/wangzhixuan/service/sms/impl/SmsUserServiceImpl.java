package com.wangzhixuan.service.sms.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.sms.SmsUserMapper;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.sms.SmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
/*
 * 查找通讯录
 */
@Service
public class SmsUserServiceImpl extends ServiceImpl<SmsUserMapper, SysUser> implements SmsUserService {
	@Autowired
	private ISysUserService userService;

	// 职工通讯录
	@Override
	public PageInfo selectUser(UserVo vo, ShiroUser shiroUser) throws SysException {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		page.setAsc(false);
		// List<Map<String, Object>> list = smsUserMapper.selectUserPage(page,
		// pageInfo.getCondition());
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows(), null, null);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(vo.getLoginName())) {
			condition.put("loginName", vo.getLoginName());
		}
		if (StringUtils.isNotBlank(vo.getName())) {
			condition.put("name", vo.getName());
		}
		if (vo.getOrganizationId() != null) {
			condition.put("organizationId", vo.getOrganizationId());
		}
		if (vo.getCreatedateStart() != null) {
			condition.put("startTime", vo.getCreatedateStart());
		}
		if (vo.getCreatedateEnd() != null) {
			condition.put("endTime", vo.getCreatedateEnd());
		}
		// shiro中缓存的用户最高角色
		SysRole role = shiroUser.getRole();
		condition.put("seq", role.getSeq());
		pageInfo.setCondition(condition);
		userService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	@Override
	public UserVo findById(Long id) throws SysException {
		if(id != null) {
			SysUser sysUser = selectById(id);
			UserVo vo = BeanUtils.copy(sysUser, UserVo.class);
			return vo;
		}else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}
}
