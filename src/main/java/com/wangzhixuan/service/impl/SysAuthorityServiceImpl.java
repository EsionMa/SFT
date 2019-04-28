package com.wangzhixuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.PasswordHash;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.SysAuthorityMapper;
import com.wangzhixuan.model.SysAuthority;
import com.wangzhixuan.service.ISysAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Leslie on 2017/12/5.
 *
 * @author: Leslie
 * @TIME:14:05
 * @Date:2017/12/5 Description:
 */
@Service
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority>
		implements ISysAuthorityService {
	@Autowired
	private SysAuthorityMapper sysAuthorityMapper;
	@Autowired
	private PasswordHash passwordHash;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 查询所有ip地址
	 * 
	 * @param pageInfo
	 */
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<SysAuthority> page = new Page<SysAuthority>(pageInfo.getNowpage(), pageInfo.getSize());
		EntityWrapper<SysAuthority> wrapper = new EntityWrapper<SysAuthority>();
		wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
		selectPage(page, wrapper);
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void insertWithSalt(SysAuthority sysAuthority, ShiroUser user) {
		if (sysAuthority == null) {
			throw new SysException("填写要求不合格");
		}
		if (user == null) {
			throw new SysException("无法获取创建人");
		}
		String salt = StringUtils.getUUId();
		sysAuthority.setSalt(salt);
		String ip = sysAuthority.getIpAddress();
		sysAuthority.setIpPassword(passwordHash.toHex(ip, salt));
		Date time = sysAuthority.getTime();
		sysAuthority.setTimePassword(passwordHash.toHex(DateUtils.format(time, "yyyy-MM-dd HH:mm:ss"), salt));
		sysAuthority.setUserName(user.getName());
		sysAuthority.setCreateUserId(user.getId());
		sysAuthority.setCreateTime(new Date());
		insert(sysAuthority);
	}

	@Override
	public void updateIpById(SysAuthority sysAuthority, ShiroUser user) {
		if (sysAuthority.getId() == null) {
			throw new SysException("id为空");
		}
		if (user == null) {
			throw new SysException("用户为空");
		}
		// uuid方法生成盐
		String salt = StringUtils.getUUId();
		sysAuthority.setSalt(salt);
		// 加入盐值进行ip加密
		sysAuthority.setIpPassword(passwordHash.toHex(sysAuthority.getIpAddress(), salt));
		// 加入盐值进行时间字符串加密
		sysAuthority.setTimePassword(
				passwordHash.toHex(DateUtils.format(sysAuthority.getTime(), "yyyy-MM-dd HH:mm:ss"), salt));
		sysAuthority.setUserName(user.getName());
		sysAuthority.setUpdateUserId(user.getId());
		sysAuthority.setUpdateTime(new Date());
		updateById(sysAuthority);
	}

	/**
	 * 通过登录的ip进行判断
	 * 
	 * @param ip
	 * @return
	 */
	@Override
	public boolean ipAndTimeIsCorrect(String ip) {
		SysAuthority authority = new SysAuthority();
		logger.info("ip地址为{}", ip);
		authority.setIpAddress(ip);
		SysAuthority sysAuthority = sysAuthorityMapper.selectOne(authority);
		if (sysAuthority == null) {
			logger.warn("此ip未经授权{}", ip);
			return false;
		} else {
			String salt = sysAuthority.getSalt();
			String ipPassword = sysAuthority.getIpPassword();
			String timePassword = sysAuthority.getTimePassword();
			Date time = sysAuthority.getTime();
			if (ipPassword.equals(passwordHash.toHex(ip, salt))) {
				if (timePassword.equals(passwordHash.toHex(DateUtils.format(time, "yyyy-MM-dd HH:mm:ss"), salt))) {
					if (DateUtils.compareTo(time, new Date()) > 0) {
						return true;
					} else {
						logger.warn("授权时间过期{}", time);
					}
				} else {
					logger.warn("非法修改授权时间{}", time);
				}
			} else {
				logger.warn("数据库非法修改ip地址{}", ip);
			}
		}
		return false;
	}

	@Override
	public boolean ipIsExsit(String ipAddress) {
		SysAuthority authority = new SysAuthority();
		authority.setIpAddress(ipAddress);
		SysAuthority sysAuthority = sysAuthorityMapper.selectOne(authority);
		if (sysAuthority != null) {
			return true;
		} else {
			return false;
		}
	}
}
