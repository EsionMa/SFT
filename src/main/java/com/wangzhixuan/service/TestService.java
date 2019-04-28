package com.wangzhixuan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.SysUser;

/**
 * Created by Leslie on 2017/8/28. TIME:12:05 Date:2017/8/28. Description:
 */
public interface TestService {

	SysUser selectById(Serializable id);

	/**
	 * @Author: Leslie
	 * @Description:得到所有门诊病人信息
	 * @Date 2017/8/28 13:43
	 */
	List<Map<String, Object>> getAllPatients();

	/**
	 * @Author: Leslie
	 * @Description:得到所有住院记录
	 * @Date 13:42
	 */
	List<Map<String, Object>> getAllHospitalization();

	/**
	 * @Author: Leslie
	 * @Description:得到医院所有部门信息
	 * @Date 2017/8/29 17:33
	 */
	List<Map<String, Object>> getAllDept();
}
