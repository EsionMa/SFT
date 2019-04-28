package com.wangzhixuan.service.sms.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.sms.SmsTemplateMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.sms.SmsTemplate;
import com.wangzhixuan.model.vo.sms.SmsTemplateVo;
import com.wangzhixuan.service.ISysDictionariesService;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.sms.ISmsTemplateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13 0013.
 */
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements ISmsTemplateService {
	@Autowired
	private ISysDictionariesService sysDictionariesService;
	@Autowired
	private ISysUserService userService;

	/* 添加模块 */
	@Override
	public void save(SmsTemplateVo vo, ShiroUser user) throws SysException {
		if (vo.getId() == null) {
			vo.setCreateUserId(user.getId());
			vo.setCreateTime(new Date());
			insert(vo);
		} else {
			throw new SysException("添加发生错误，请检查录入数据");
		}
	}

	/**
	 * 更新模块
	 */
	@Override
	public void update(SmsTemplateVo vo, ShiroUser user) throws SysException {

		if (vo.getId() != null) {
			vo.setUpdateUserId(user.getId());
			vo.setUpdateTime(new Date());
			updateById(vo);
			// smsTemplateMapper.updateTemplate(vo);
		} else {
			throw new SysException("修改发生错误，请检查录入数据");
		}

	}

	/*
	 * 模糊查询
	 */
	@Override
	public PageInfo selectDataGrid(SmsTemplateVo vo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		EntityWrapper<SmsTemplate> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(vo.getType())) {
			wrapper.where("type={0}", vo.getType());
		}
		if (StringUtils.isNotBlank(vo.getTitle())) {
			wrapper.like("title", vo.getTitle());
		}
		if (StringUtils.isNotBlank(vo.getContent())) {
			wrapper.like("content", vo.getContent());
		}
 		selectMapsPage(page, wrapper);
		for (Map<String, Object> item : page.getRecords()) {
			SysDictionaries type = sysDictionariesService.getByCode(item.get("type").toString());
			item.put("typeName", type == null ? null : type.getName());
			if (item.containsKey("updateUserId")) {
				SysUser userName = userService.selectById(item.get("updateUserId").toString());
				item.put("updateUser", userName == null ? null : userName.getLoginName());
			}
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public void deleteMsgs(Long id) throws SysException {
		if (id != null) {
			deleteById(id);
		} else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}

	@Override
	public SmsTemplateVo findById(Long id) throws SysException {
		if (id != null) {
			SmsTemplate SmsTemplate = selectById(id);
			SmsTemplateVo vo = BeanUtils.copy(SmsTemplate, SmsTemplateVo.class);
			return vo;
		} else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}

	@Override
	public List<SmsTemplateVo> selectTempByType(SmsTemplateVo vo) {
		List<SmsTemplateVo> vos = new ArrayList<>();
		EntityWrapper<SmsTemplate> wrapper = new EntityWrapper<>();
		wrapper.where("type={0}", vo.getType());
		List<SmsTemplate> templateList = selectList(wrapper);
		for (SmsTemplate smsTemplate : templateList) {
			vos.add(BeanUtils.copy(smsTemplate, SmsTemplateVo.class));
		}
		return vos;

	}

}
