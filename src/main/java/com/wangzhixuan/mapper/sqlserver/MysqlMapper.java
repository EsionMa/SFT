package com.wangzhixuan.mapper.sqlserver;

import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Leslie on 2017/9/1. TIME:9:28 Date:2017/9/1. Description:查询我方所用数据
 */
public interface MysqlMapper {

	/**
	 * @Author: Leslie
	 * @Description:根据对方数据库患者id查询我方数据库患者信息
	 * @Date 2017/9/1 15:57
	 */
	HuanZheInfo selectPatient(@Param("outId") String outId);

	/**
	 * @Author: Leslie
	 * @Description:根据对方数据库部门id查询我方数据库部门信息
	 * @Date 2017/9/2 10:10
	 */
	SysOrganization selectOrg(@Param("outId") String outId);

	/**
	 * @Author: Leslie
	 * @Description:根据对方数据库用户id查询我方数据库用户信息
	 * @Date 2017/9/2 11:09
	 */
	SysUser selectUser(@Param("outId") String outId);


	Integer count(PatientQuery query);

	List<String> findZyNos(PatientQuery query);
   /**
	*@author: Leslie
	*@Date 2018/1/23 10:08
	*@param: [query]
	*@return int
	*@throws
	* @Description: 查询我方数据库是否存在该患者
	*/
   Integer existHos(PatientQuery query);
}
