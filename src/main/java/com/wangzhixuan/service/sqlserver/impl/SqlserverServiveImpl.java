/**
 * 2017-08-29 23:36:31
 */
package com.wangzhixuan.service.sqlserver.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.huanzhe.HuanZheContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.commons.utils.datasource.CustomDataSource;
import com.wangzhixuan.mapper.sqlserver.SqlserverMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.service.sqlserver.ISqlserverServive;

/**
 * @author Esion
 *
 */
@Service
public class SqlserverServiveImpl implements ISqlserverServive {
	@Autowired
	private SqlserverMapper sqlserverMapper;

	/**
	 * @Author: Leslie
	 * @Description:部门表，测试通过， 数据库将create_rime 非Null取消
	 * @Date 2017/8/31 18:29
	 */
	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<SysOrganization> getAllDepts(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		/*
		 * map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		 * map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		 */
		List<SysOrganization> list = sqlserverMapper.selectAllDepts(map);
		return list;
	}

	/**
	 * @Author: Leslie
	 * @Description:字典表，测试通过 数据库将create_time 非Null取消
	 * @Date 2017/8/31 18:23
	 */
	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<SysDictionaries> getAllDics(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		/*
		 * map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		 * map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		 */
		List<SysDictionaries> list = sqlserverMapper.selectAllDics(map);
		return list;
	}

	/**
	 * @Author: Leslie
	 * @Description:user 医院科室相关表，目标数据库id为varchar，转换失败，部门id，目标部门为varcahr,为空时转换Integer失败
	 * @Date 2017/8/31 18:29
	 */
	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<SysUser> getAllStaff(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		/*
		 * map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		 * map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		 */
		List<SysUser> list = sqlserverMapper.selectAllStaff(map);
		return list;
	}

	/**
	 * @Author: Leslie
	 * @Description:病人导入成功
	 * @Date 2017/8/31 20:05
	 */
	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<HuanZheInfo> getAllPatients(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		List<HuanZheInfo> list = sqlserverMapper.selectAllPatients(map);
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<ZhuYuanInfo> getAllHos(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		List<ZhuYuanInfo> list = sqlserverMapper.selectAllHos(map);
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.sqlserver)
	public List<HuanZheContract> getAllContact(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		List<HuanZheContract> list = sqlserverMapper.selectContact(map);
		return list;

	}
}
