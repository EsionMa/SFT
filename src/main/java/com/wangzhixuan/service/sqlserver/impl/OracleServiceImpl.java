/**
 * 2017-08-29 23:36:31
 */
package com.wangzhixuan.service.sqlserver.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.commons.utils.datasource.CustomDataSource;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.service.sqlserver.IOracleService;
import com.wangzhixuan.mapper.sqlserver.OracleMapper;

/**
 * @author Esion
 *
 */
@Service
public class OracleServiceImpl implements IOracleService {
	@Autowired
	private OracleMapper oracleMapper;

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<SysOrganization> getAllDepts(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		if (boStTime != null) {
			map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		}
		if (boEdTime != null) {
			map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		}
		List<SysOrganization> list = oracleMapper.selectAllDepts(map);
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<SysDictionaries> getAllDics(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		if (boStTime != null) {
			map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		}
		if (boEdTime != null) {
			map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		}
		List<SysDictionaries> list = oracleMapper.selectAllDics(map);
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<SysUser> getAllStaff(Date boStTime, Date boEdTime) throws SysException {
		Map<String, Object> map = new HashMap<>();
		if (boStTime != null) {
			map.put("boStTime", DateUtils.yyyyMMddHHmmssFormat.format(boStTime));
		}
		if (boEdTime != null) {
			map.put("boEdTime", DateUtils.yyyyMMddHHmmssFormat.format(boEdTime));
		}
		List<SysUser> list = oracleMapper.selectAllStaff(map);
		return list;
	}

	/**
	 * @Author: Leslie
	 * @Description:病人导入成功
	 * @Date 2017/8/31 20:05
	 */
	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<HuanZheInfo> getAllPatients(PatientQuery patientQuery) throws SysException {
		Map<String, Object> map = new HashMap<>();
		if (patientQuery.getBoStTime()!=null) {
			map.put("boStTime", DateUtils.format(patientQuery.getBoStTime(),DateUtils.yyyyMMddHHmmss));
		}
		if (patientQuery.getBoEdTime()!=null) {
			map.put("boEdTime", DateUtils.format(patientQuery.getBoEdTime(),DateUtils.yyyyMMddHHmmss));
		}
		if (patientQuery.getZyNo()!=null){
			map.put("zyNo",patientQuery.getZyNo());
		}
		if (map.size()==0){
			throw new SysException("请至少传入一个参数");
		}
		List<HuanZheInfo> list = oracleMapper.selectAllPatients(map);
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<ZhuYuanInfo> getAllHos(PatientQuery patientQuery) throws SysException {
		Map<String, Object> map = new HashMap<>();
		if (patientQuery.getBoStTime()!=null) {
			map.put("boStTime", DateUtils.format(patientQuery.getBoStTime(),DateUtils.yyyyMMddHHmmss));
		}
		if (patientQuery.getBoEdTime()!=null) {
			map.put("boEdTime", DateUtils.format(patientQuery.getBoEdTime(),DateUtils.yyyyMMddHHmmss));
		}
		if (StringUtils.isNotBlank(patientQuery.getZyNo())){
			map.put("zyNo",patientQuery.getZyNo());
		}
		if (map.size()==0){
			throw new SysException("请至少传入一个参数");
		}
		List<ZhuYuanInfo> list = oracleMapper.selectAllHos(map);
		return list;
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<HuanZheContract> getAllContract(PatientQuery patientQuery) {
		Map<String, Object> map = new HashMap<>();
		if (patientQuery.getBoStTime()!=null) {
			map.put("boStTime", DateUtils.format(patientQuery.getBoStTime(),DateUtils.yyyyMMddHHmmss));
		}
		if (patientQuery.getBoEdTime()!=null) {
			map.put("boEdTime", DateUtils.format(patientQuery.getBoEdTime(),DateUtils.yyyyMMddHHmmss));
		}
		if (patientQuery.getZyNo()!=null){
			map.put("zyNo",patientQuery.getZyNo());
		}
		if (map.size()==0){
			throw new SysException("请至少传入一个参数");
		}
		List<HuanZheContract> list = oracleMapper.selectContarct(map);
		return list;
	}
	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public Integer count(PatientQuery query) {
		Map<String, Object> map = new HashMap<>();
		if (query.getBoEdTime()==null){
			throw new SysException("结束时间为空");
		}
		if (query.getBoStTime()==null){
			throw new SysException("开始时间为空");
		}
		return oracleMapper.count(query);
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<HuanZheInfo> getNotFoundPatient(PatientQuery query) {
		if (query.getBoEdTime()==null){
			throw new SysException("结束时间为空");
		}
		if (query.getBoStTime()==null){
			throw new SysException("开始时间为空");
		}
		if (query.getIds()==null){
			throw  new SysException("ids为空");
		}
		return  oracleMapper.getNotFoundPatient(query);
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<ZhuYuanInfo> getNotFoundHos(PatientQuery query) {
		if (query.getBoEdTime()==null){
			throw new SysException("结束时间为空");
		}
		if (query.getBoStTime()==null){
			throw new SysException("开始时间为空");
		}
		if (query.getIds()==null){
			throw  new SysException("ids为空");
		}
		return oracleMapper.getNotFoundHos(query);
	}

	@Override
	@CustomDataSource(CustomDataSource.oracle)
	public List<HuanZheContract> getNotFoundContracts(PatientQuery query) {
		if (query.getBoEdTime()==null){
			throw new SysException("结束时间为空");
		}
		if (query.getBoStTime()==null){
			throw new SysException("开始时间为空");
		}
		if (query.getIds()==null){
			throw  new SysException("ids为空");
		}
		return oracleMapper.getNotFoundContrac(query);
	}

}
