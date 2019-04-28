package com.wangzhixuan.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wangzhixuan.commons.utils.datasource.CustomDataSource;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.mapper.test.TestMapper;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.service.TestService;

/**
 * @Author: Leslie
 * @Description:此类针对成都恒博数据库
 * @Date 2017/8/29 17:41
 */
@Service
public class TestServiceImp implements TestService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	TestMapper testMapper;

	@Cacheable(value = "hour", key = "#id")
	public SysUser selectById(Serializable id) {
		return userMapper.selectById(id);
	}

	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<Map<String, Object>> getAllPatients() {
		List<Map<String, Object>> list = testMapper.select();
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<Map<String, Object>> getAllHospitalization() {
		List<Map<String, Object>> list = testMapper.getAllHos();
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<Map<String, Object>> getAllDept() {
		List<Map<String, Object>> list = testMapper.getAllDept();
		return list;
	}
}
