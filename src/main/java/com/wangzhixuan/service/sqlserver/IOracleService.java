/**
 * 2017-08-29 23:31:12
 */
package com.wangzhixuan.service.sqlserver;

import java.util.Date;
import java.util.List;

import com.wangzhixuan.commons.SysException;
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
public interface IOracleService {
	/**
	 * 获取医院所有部门信息
	 *
	 * @param boStTime
	 * @param boEdTime
	 * @return
	 * @throws SysException
	 */
	List<SysOrganization> getAllDepts(Date boStTime, Date boEdTime) throws SysException;

	/**
	 * 获取所有字典信息
	 *
	 * @return
	 * @throws SysException
	 */
	List<SysDictionaries> getAllDics(Date boStTime, Date boEdTime) throws SysException;

	/**
	 * 获取所有医护人员信息
	 *
	 * @param boStTime
	 * @param boEdTime
	 * @return
	 * @throws SysException
	 */
	List<SysUser> getAllStaff(Date boStTime, Date boEdTime) throws SysException;

	/**
	 *
	 * @param patientQuery
	 * @return
	 * @throws SysException
	 */
	List<HuanZheInfo> getAllPatients(PatientQuery patientQuery) throws SysException;

	/**
	 *
	 * @param patientQuery
	 * @return
	 * @throws SysException
	 */
	List<ZhuYuanInfo> getAllHos(PatientQuery patientQuery) throws SysException;

	/**
	 *
	 * @param query
	 * @return
	 */
	List<HuanZheContract> getAllContract(PatientQuery query);

	/**
	 * 计算该月患者总数
	 * @param query
	 * @return
	 */
	Integer count(PatientQuery query);

	/**
	 * 不存在的患者
	 * @param query
	 * @return
	 */
	List<HuanZheInfo> getNotFoundPatient(PatientQuery query);

	/**
	 * 查找不存在的住院号
	 * @param query
	 * @return
	 */
	List<ZhuYuanInfo> getNotFoundHos(PatientQuery query);

	/**
	 * 查找不存在的联系人
	 * @param query
	 * @return
	 */
	List<HuanZheContract> getNotFoundContracts(PatientQuery query);
}
