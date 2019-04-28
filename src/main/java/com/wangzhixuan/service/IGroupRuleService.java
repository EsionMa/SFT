package com.wangzhixuan.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.GroupRule;
import com.wangzhixuan.model.vo.GroupRuleVo;

public interface IGroupRuleService extends IService<GroupRule>{
	void addGroup(GroupRuleVo vo,ShiroUser user)throws SysException;
	void updateStatue(Long id,String statue,ShiroUser user)throws SysException;
	Map<String, Object> dataGrid()throws SysException;
	
}
