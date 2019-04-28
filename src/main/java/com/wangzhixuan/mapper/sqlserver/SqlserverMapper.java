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

/**
 * @author Esion
 *
 */
public interface SqlserverMapper {
	/**
	 * 查询所有部门信息
	 * 
	 * @return
	 */
	List<SysOrganization> selectAllDepts(Map<String, Object> map);

	/**
	 * 查询所有字典信息
	 * 
	 * @return
	 */
	List<SysDictionaries> selectAllDics(Map<String, Object> map);

	/**
	 * 查询所有医护人员信息
	 * 
	 * @return
	 */
	List<SysUser> selectAllStaff(Map<String, Object> map);

	/**
	 * 查询所有患者信息
	 * 
	 * @return
	 */
	List<HuanZheInfo> selectAllPatients(Map<String, Object> map);

	/**
	 * 查询所有住院信息
	 * 
	 * @return
	 */
	List<ZhuYuanInfo> selectAllHos(Map<String, Object> map);

	/**
	 * 查询患者联系人
	 * 
	 * @param map
	 * @return
	 */

	List<HuanZheContract> selectContact(Map<String, Object> map);
}
