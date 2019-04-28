package com.wangzhixuan.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import com.wangzhixuan.service.sqlserver.IOracleService;
import com.wangzhixuan.service.statics.IOutStaticsService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.service.TestService;
import com.wangzhixuan.service.sqlserver.IMySqlService;

/**
 * @description：测试Controller
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	@Autowired
	TestService testService;
	@Autowired
	private IMySqlService mySqlService;
	@Autowired
	private IOracleService oracleServive;
	@Autowired
	private IOutStaticsService outStaticsService;

	/**
	 * 图标测试
	 *
	 * @RequiresRoles shiro 权限注解
	 *
	 * @return
	 */
	@RequiresRoles("test")
	@GetMapping("/dataGrid")
	public String dataGrid() {
		return "admin/test";
	}

	/**
	 * 下载测试
	 *
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/down")
	public ResponseEntity<byte[]> down() throws IOException {
		File file = new File("/Users/lcm/Desktop/个人小客车配置指标申请表.pdf");
		return download(file);
	}

	@RequestMapping("hello")
	@ResponseBody
	public List<Map<String, Object>> hello() {
		List<Map<String, Object>> list = new ArrayList<>();
		list = testService.getAllPatients();
		return list;
	}

	/**
	 * @Author: Leslie
	 * @Description:导入部门表，
	 * @Date 2017/8/31 18:25
	 */
	@RequestMapping("import/depts")
	@ResponseBody
	public RespResult<String> importDepts(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		/*
		 * Date boStTime = DateUtils.parse(map.get("boStTime").toString()); Date
		 * boEdTime = DateUtils.parse(map.get("boEdTime").toString());
		 */
		List<SysOrganization> sysOrganizations = oracleServive.getAllDepts(null, null);
		/* 在数据插入时，因为要在已插入中搜索id，所以只能选择单独插入，切勿批量插入 */
		mySqlService.insertSysOrgs(sysOrganizations);
		result.getSuccess(null, "导入数据成功");
		return result;
	}

	/**
	 * @Author: Leslie
	 * @Description:导入字典表
	 * @Date 2017/8/31 17:58
	 */
	@RequestMapping("import/dics")
	@ResponseBody
	public RespResult<String> importDics(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		/*
		 * Date boStTime = DateUtils.parse(map.get("boStTime").toString()); Date
		 * boEdTime = DateUtils.parse(map.get("boEdTime").toString());
		 */
		List<SysDictionaries> dictionaries = oracleServive.getAllDics(null, null);
		mySqlService.insertSysDics(dictionaries, getShiroUser());
		result.getSuccess(null, "导入数据成功");
		return result;
	}

	/**
	 * 导入医护人员
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping("import/users")
	@ResponseBody
	public RespResult<String> importStaff(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		/*
		 * Date boStTime = DateUtils.parse(map.get("boStTime").toString()); Date
		 * boEdTime = DateUtils.parse(map.get("boEdTime").toString());
		 */
		List<SysUser> sysUsers = oracleServive.getAllStaff(null, null);
		/* 由于插入带有查询问题，不能多个插入 */
		mySqlService.insertUsers(sysUsers);
		result.getSuccess(null, "导入数据成功");
		return result;
	}

	/**
	 * 导入患者信息
	 *
	 * @param map
	 * @return
	 */
	/*@RequestMapping("import/patients")
	@ResponseBody
	public RespResult<String> importPatients(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Date boStTime = DateUtils.parse(map.get("boStTime").toString());
		Date boEdTime = DateUtils.parse(map.get("boEdTime").toString());
		List<HuanZheInfo> huanZheInfos = oracleServive.getAllPatients(boStTime, boEdTime);
		mySqlService.insertHuanzhe(huanZheInfos, getShiroUser());
		result.getSuccess(null, "导入数据成功");
		return result;
	}*/

	/**
	 * @Author: Leslie
	 * @Description:导入住院信息
	 * @Date 2017/9/1 16:02
	 */
	/*@RequestMapping("import/hos")
	@ResponseBody
	public RespResult<String> importHos(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Date boStTime = DateUtils.parse(map.get("boStTime").toString());
		Date boEdTime = DateUtils.parse(map.get("boEdTime").toString());
		List<ZhuYuanInfo> zhuYuanInfos = oracleServive.getAllHos(boStTime, boEdTime);
		mySqlService.insertZhuyuans(zhuYuanInfos, getShiroUser());
		result.getSuccess(null, "导入数据成功");
		return result;
	}
*/
	/**
	 * @Author: Leslie
	 * @Description:获取患者联系人
	 * @Date 2017/9/2 10:08
	 */
	/*@RequestMapping("import/contacts")
	@ResponseBody
	public RespResult<String> importContrac(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		Date boStTime = DateUtils.parse(map.get("boStTime").toString());
		Date boEdTime = DateUtils.parse(map.get("boEdTime").toString());
		List<HuanZheContract> huanZheInfos = oracleServive.getAllContract(boStTime, boEdTime);
		mySqlService.insertConTracs(huanZheInfos, getShiroUser());
		result.getSuccess(null, "导入数据成功");
		return result;
	}*/



	/**
	 * 导入一段时间内所有丢失数据,此处应该添加事务相关，暂未找到
	 *  同理，每天跑批也得进行事务相关处理
	 * @param map
	 * @return
	 */
	@RequestMapping("import/all")
	@ResponseBody
	public RespResult<String> importAll(@RequestBody Map<String,Object> map){
		RespResult<String> result=new RespResult<>();
		PatientQuery query= BeanUtils.mapToBean(map, PatientQuery.class);
		Integer his=oracleServive.count(query);
		Integer suifang=mySqlService.count(query);
		if (!his.equals(suifang)){
			List<String> list=mySqlService.findZyNos(query);
			query.setIds(list);
			List<HuanZheInfo> huanZheInfos=oracleServive.getNotFoundPatient(query);;
			List<ZhuYuanInfo> zhuYuanInfos=oracleServive.getNotFoundHos(query);
			List<HuanZheContract> contracts=oracleServive.getNotFoundContracts(query);
			mySqlService.insertData(contracts,zhuYuanInfos,huanZheInfos);
		}
		result.getSuccess("导入成功");
		return result;
	}

	@RequestMapping("import/statics")
	@ResponseBody
	public RespResult<String> importStatics(@RequestBody Map<String,Object> map){
		RespResult<String> result=new RespResult<>();
		outStaticsService.exeTj();
		result.getSuccess("成功");
		return result;
	}
}
