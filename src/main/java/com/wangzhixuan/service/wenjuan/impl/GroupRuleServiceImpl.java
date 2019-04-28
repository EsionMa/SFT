package com.wangzhixuan.service.wenjuan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.mapper.GroupRuleMapper;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.model.GroupRule;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.vo.GroupRuleVo;
import com.wangzhixuan.service.IGroupRuleService;
@Service
public class GroupRuleServiceImpl extends ServiceImpl<GroupRuleMapper, GroupRule> implements IGroupRuleService{
	@Autowired
	private SysOrganizationMapper organMapper;
	@Override
	public void addGroup(GroupRuleVo vo,ShiroUser user) throws SysException {
		List<Long> jzkss = vo.getJzkss();
		StringBuffer sb=new StringBuffer();
		List<String> hzlys = vo.getHzlys();
		if(hzlys==null || hzlys.size()<1){
			throw new SysException("请选择患者来源!");
		}
		for(int i=0;i<hzlys.size();i++){
			sb.append(hzlys.get(i)).append(",");
		}
		sb.setLength(sb.length()-1);
		vo.setHzly(sb.toString());
		vo.setHzlys(null);
		//如果有部门则保存部门信息
		if(jzkss!=null && jzkss.size()>0){
			sb.setLength(0);
			for(int i=0;i<jzkss.size();i++){
				SysOrganization dept = organMapper.selectById(jzkss.get(i));
				if(dept==null){
					throw new SysException("医院部门不存在");
				}
				sb.append(jzkss.get(i)).append(",");
			}
			sb.setLength(sb.length()-1);
			vo.setJzks(sb.toString());
			vo.setJzkss(null);
		}
		vo.setCreateTime(new Date());
		vo.setCreateUserId(user.getId());
		vo.setStatue("1");
		insert(vo);
	}
	@Override
	public void updateStatue(Long id, String statue, ShiroUser user) throws SysException {
		if(id==null){
			throw new SysException("群组不存在");
		}
		GroupRule groupRule = selectById(id);
		if(groupRule==null){
			throw new SysException("群组不存在");
		}
		groupRule.setStatue(statue);
		groupRule.setUpdateTime(new Date());
		groupRule.setUpdateUserId(user.getId());
		updateById(groupRule);
	}
	@Override
	public Map<String, Object> dataGrid() throws SysException {
		
		return null;
	}

}
