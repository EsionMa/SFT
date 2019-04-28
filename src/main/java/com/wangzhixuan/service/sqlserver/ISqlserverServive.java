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

/**
 * @author Esion
 *
 */
public interface ISqlserverServive {
	/**
	 * @Author: Leslie
	 * @Description:得到医院所有部门信息
	 * @Date 2017/8/29 17:33
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
	 * @return
	 * @throws SysException
	 */
	List<SysUser> getAllStaff(Date boStTime, Date boEdTime) throws SysException;

	/**
	 * @Author: Leslie
	 * @Description:得到所有门诊病人信息
	 * @Date 2017/8/28 13:43
	 */
	List<HuanZheInfo> getAllPatients(Date boStTime, Date boEdTime) throws SysException;

	/**
	 * @Author: Leslie
	 * @Description:得到所有住院记录
	 * @Date 13:42
	 */
	List<ZhuYuanInfo> getAllHos(Date boStTime, Date boEdTime) throws SysException;

	/**
	 * @Author: Leslie
	 * @Description:得到患者联系人
	 * @Date 2017/9/2 9:58
	 */
	List<HuanZheContract> getAllContact(Date boStTime, Date boEdTime) throws SysException;
}
