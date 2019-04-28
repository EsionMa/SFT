package com.wangzhixuan.service.impl;

import java.util.List;

import com.wangzhixuan.commons.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.mapper.SysDictionariesMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.service.ISysDictionariesService;

@Service
public class SysDictionariesServiceImp extends ServiceImpl<SysDictionariesMapper, SysDictionaries> implements ISysDictionariesService {
	@Autowired
	private SysDictionariesMapper sysDictionariesMapper;
	@Override
	public List<SysDictionaries> selectTreeGrid() {
		EntityWrapper<SysDictionaries> wrapper = new EntityWrapper<SysDictionaries>();
		wrapper.orderBy("create_time desc");
		return sysDictionariesMapper.selectList(wrapper);
	}

	@Override
	public List<SysDictionaries> listByParentCode(String parentCode) {
		return sysDictionariesMapper.listByParentCode(parentCode);
	}

	/**
	 *@author: Leslie
	 *@Date 2018/3/19 11:31
	 *@param: [code]
	 *@return com.wangzhixuan.model.SysDictionaries
	 *@throws
	 *@Description: 将字典进行缓存
	 */
	@Override
	@Cacheable(value = "dicCache",key = "#code")
	public SysDictionaries getByCode(String code) {
		if (StringUtils.isBlank(code)){
			throw new SysException("编码不能为空");
		}
		SysDictionaries sysDictionaries = sysDictionariesMapper.listByCode(code);
		return sysDictionaries;
	}

	@Override
	@CachePut(value = "dicCache",key = "#p0.code")
	public SysDictionaries save(SysDictionaries sysDictionaries) throws SysException {
		sysDictionariesMapper.save(sysDictionaries);
		return sysDictionaries;
	}

}
