package com.wangzhixuan.service.sqlserver;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;

import java.util.List;

/**
 * Created by Leslie on 2017/9/1. TIME:9:47 Date:2017/9/1. Description:
 */
public interface IMySqlService {
	/**
	 * @Author: Leslie
	 * @Description:转换为我们自己需要的id
	 * @Date 2017/9/1 9:48
	 */
	void insertSysOrgs(List<SysOrganization> sys) throws SysException;

	/**
	 * @Author: Leslie
	 * @Description:将字典相关代表转换为我们自己的
	 * @Date 2017/9/1 14:39
	 */
	void insertSysDics(List<SysDictionaries> sysy, ShiroUser user) throws SysException;

	/**
	 * @Author: Leslie
	 * @Description:将用户相关转换为我们自己的
	 * @Date 2017/9/1 14:39
	 */
	void insertUsers(List<SysUser> users);

	/**
	 * @Author: Leslie
	 * @Description:添加患者信息相关
	 * @Date 2017/9/4 11:35
	 */
	void insertHuanzhe(List<HuanZheInfo> infos, ShiroUser user) throws SysException;

	/**
	 *
	 * @param info
	 * @param user
	 * @return
	 */
	void insertZhuyuans(List<ZhuYuanInfo> info, ShiroUser user) throws SysException;

	/**
	 * 患者联系人转换
	 *
	 * @param con
	 * @param user
	 * @return
	 */
	void insertConTracs(List<HuanZheContract> con, ShiroUser user) throws SysException;

   /***
	*@author: Leslie
	*@Date 2018/1/23 15:24
	*@param: [contracts, zhuYuanInfos, huanZheInfos]
	*@return void
	*@throws
	*@Description: 统一存储患者信息，住院信息，联系人信息
	*/
	void insertData(List<HuanZheContract> contracts,List<ZhuYuanInfo> zhuYuanInfos,List<HuanZheInfo> huanZheInfos)throws SysException;

	/**
	 * 我方数据库一段时间内人数
	 * @param query
	 * @return
	 */
	Integer count(PatientQuery query);

	/**
	 * 查找住院号
	 * @param query
	 * @return
	 */
	List<String> findZyNos(PatientQuery query);
  /**
   *@author: Leslie
   *@Date 2018/1/23 10:06
   *@param: [query]
   *@return java.lang.Boolean
   *@throws
   *@Description: 查询我方数据库是否存在该患者
   */
  int existHos(PatientQuery query);
}
