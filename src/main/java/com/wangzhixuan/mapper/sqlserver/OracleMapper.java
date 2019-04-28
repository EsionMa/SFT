/**
 * 2017-08-29 23:40:52
 */
package com.wangzhixuan.mapper.sqlserver;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;

/**
 * @author Esion
 *
 */
public interface OracleMapper {
	/**
	 * 查询所有部门信息
	 *
	 * @param map
	 * @return
	 */
	List<SysOrganization> selectAllDepts(Map<String, Object> map);

	/**
	 * 查询所有字典信息
	 *
	 * @param map
	 * @return
	 */
	List<SysDictionaries> selectAllDics(Map<String, Object> map);

	/**
	 * 查询所有医护人员信息
	 *
	 * @param map
	 * @return
	 */
	List<SysUser> selectAllStaff(Map<String, Object> map);

	/**
	 * 查询所有患者信息
	 *
	 * @param map
	 * @return
	 */
	List<HuanZheInfo> selectAllPatients(Map<String, Object> map);

	/**
	 * 查询所有住院信息
	 *
	 * @param map
	 * @return
	 */
	List<ZhuYuanInfo> selectAllHos(Map<String, Object> map);

	/**
	 * 查询所有住院患者联系人信息
	 *
	 * @param map
	 * @return
	 */

	List<HuanZheContract> selectContarct(Map<String, Object> map);

	Integer count(PatientQuery query);

	List<HuanZheInfo> getNotFoundPatient(PatientQuery query);

	List<ZhuYuanInfo> getNotFoundHos(PatientQuery query);

	List<HuanZheContract> getNotFoundContrac(PatientQuery query);
}
