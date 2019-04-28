package com.wangzhixuan.service.sqlserver.impl;

import java.util.ArrayList;
import java.util.List;

import com.wangzhixuan.mapper.SysDictionariesMapper;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.mapper.huanzhe.HuanZheContractMapper;
import com.wangzhixuan.mapper.huanzhe.HuanZheInfoMapper;
import com.wangzhixuan.mapper.huanzhe.ZhuYuanInfoMapper;
import com.wangzhixuan.mapper.sqlserver.MysqlMapper;
import com.wangzhixuan.model.vo.fa.HuanZhePiPeiBo;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.fa.IFangAnDingYiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.service.sqlserver.IMySqlService;

/**
 * Created by Leslie on 2017/9/1. TIME:9:48 Date:2017/9/1. Description:
 */
@Service
public class MySqlServiceImpl implements IMySqlService {
	@Autowired
	private MysqlMapper mysqlMapper;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private SysOrganizationMapper orgMapper;
	@Autowired
	private SysDictionariesMapper dicMapper;
	@Autowired
	private HuanZheInfoMapper hzMapper;
	@Autowired
	private ZhuYuanInfoMapper zyMapper;
	@Autowired
	private HuanZheContractMapper contractMapper;
	@Autowired
	private IFangAnDingYiService fangAnDingYiService;

	/**
	 * 插入科室信息
	 *
	 * @param orgs
	 */
	@Override
	public void insertSysOrgs(List<SysOrganization> orgs) throws SysException {
		for (SysOrganization item : orgs) {
			if (StringUtils.isNotBlank(item.getOutPid())) {
				// 设置我方数据库部门Pid
				SysOrganization sysOrganization = mysqlMapper.selectOrg(item.getOutPid());
				if (sysOrganization != null) {
					item.setPid(sysOrganization.getId());
				}
			}
			// if ("1".equals(item.getType())){
			// item.setType("3");
			// }
			// 医院部门
			item.setType("2");
			item.setIcon("fi-folder");
			orgMapper.insert(item);
		}

	}

    /**
     * 插入字典表
     * @param dics
     * @param user
     * @throws SysException
     */
	@Override
	public void insertSysDics(List<SysDictionaries> dics, ShiroUser user) throws SysException {
		// TODO
		for (SysDictionaries item : dics) {
			// 省
			if (item.getName().endsWith("自治区") || item.getName().endsWith("省")) {
				item.setParentCode("C_CHINA");
			}
			// 市
			if (item.getName().endsWith("州") || item.getName().endsWith("市")) {
				item.setParentCode("C_CHINA");
			}
			// 区县

			// 设置创建人ID
			if (user != null) {
				item.setCreateUserId(user.getId());
				// item.setUpdateUserId(user.getId());
			} else {
				item.setCreateUserId(1L);
				// item.setUpdateUserId(1L);
			}
			dicMapper.insert(item);
		}
	}

	/**
	 * 插入医生护士信息
	 *
	 * @param users
	 */
	@Override
	public void insertUsers(List<SysUser> users) throws SysException {
		for (SysUser item : users) {
			// 0.管理员 1.其他人员2.医生3.护士
			// if (StringUtils.isNotBlank(item.getOutUserType())) {
			// if ("2".equals(item.getOutUserType())) {
			// item.setUserType(2);
			// } else if ("1".equals(item.getOutUserType())) {
			// item.setUserType(3);
			// } else {
			// item.setUserType(1);
			// }
			// } else {
			// item.setUserType(1);
			// }

			// 出生日期转换
			// if (StringUtils.isNotBlank(item.getBirthday())) {
			// Date birthday = DateUtils.parse(item.getBirthday());
			// try {
			// Integer age = DateUtils.getAge(birthday);
			// item.setAge(age);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			// 性别转换
			/*if (StringUtils.isNotBlank(item.getOutSex())) {
				if ("女".equals(item.getOutSex())) {
					item.setSex(0);
				} else if ("男".equals(item.getOutSex())) {
					item.setSex(1);
				} else {
					item.setSex(2);
				}
			} else {
				item.setSex(2);
			}*/
			// 根据对方数据库的outId,查询我方数据库的部门pId
//			if (StringUtils.isNotBlank(item.getOutOrgId())) {
//				SysOrganization sysOrganization = mysqlMapper.selectOrg(item.getOutOrgId());
//				if (sysOrganization != null) {
//					item.setOrganizationId(sysOrganization.getId());
//				}
//			}
			userService.insertUser(item);
		}
	}

	/**
	 * 添加患者
	 *
	 * @param infos
	 * @param user
	 */
	@Override
	public void insertHuanzhe(List<HuanZheInfo> infos, ShiroUser user) throws SysException {
		for (HuanZheInfo item : infos) {
			// 性别转换
			if (StringUtils.isNotBlank(item.getSex())) {
				if ("女".equals(item.getSex())) {
					item.setSex("0");
				} else if ("男".equals(item.getSex())) {
					item.setSex("1");
				} else {
					item.setSex("2");
				}
			} else {
				item.setSex("2");
			}
			// 婚姻状况转换
			if (StringUtils.isNotBlank(item.getMarriage())) {
				if (item.getMarriage().indexOf("未") != -1) {
					item.setMarriage("1");
				} else if (item.getMarriage().indexOf("已") != -1) {
					item.setMarriage("2");
				} else if (item.getMarriage().indexOf("离") != -1) {
					item.setMarriage("3");
				} else if (item.getMarriage().indexOf("丧") != -1) {
					item.setMarriage("4");
				} else {
					item.setMarriage("9");
				}
			} else {
				item.setMarriage("9");
			}
			// 身份证号空格处理
			if (StringUtils.isNotBlank(item.getIdcardno())) {
				item.setIdcardno(item.getIdcardno().trim());
			}
			// 设置创建人ID
			if (user != null) {
				item.setCreateUserId(user.getId());
				// item.setUpdateUserId(user.getId());
			} else {
				item.setCreateUserId(1L);
				// item.setUpdateUserId(1L);
			}
			hzMapper.insert(item);
		}
	}

	/**
	 *
	 * @param infos
	 * @param user
	 * @return
	 */
	@Override
	public void insertZhuyuans(List<ZhuYuanInfo> infos, ShiroUser user) throws SysException {
		List<Long> ids = new ArrayList<>();
		for (ZhuYuanInfo item : infos) {
			// 患者转换为我方数据库患者name和id
			if (StringUtils.isNotBlank(item.getHzNo())) {
				HuanZheInfo huanZheInfo = mysqlMapper.selectPatient(item.getHzNo());
				if (huanZheInfo != null) {
					item.setHzId(huanZheInfo.getId());
				}
			}
			// 入院科室转换为我方数据库科室id,name
			if (StringUtils.isNotBlank(item.getOutRyDeptId())) {
				SysOrganization sysOrganization = mysqlMapper.selectOrg(item.getOutRyDeptId());
				if (sysOrganization != null) {
					// item.setRyDeptName(sysOrganization.getName());
					item.setRyDeptId(sysOrganization.getId());
				}
			}
			// 出院科室转换为我方数据库科室id,name
			if (StringUtils.isNotBlank(item.getOutCyDeptId())) {
				SysOrganization sysOrganization = mysqlMapper.selectOrg(item.getOutCyDeptId());
				if (sysOrganization != null) {
					// item.setCyDeptName(sysOrganization.getName());
					item.setCyDeptId(sysOrganization.getId());
				}
			}
			// 设置管床医生id
			if (StringUtils.isNotBlank(item.getOutGcDoctorId())) {
				SysUser sysUser = mysqlMapper.selectUser(item.getOutGcDoctorId());
				if (sysUser != null) {
					item.setGcDoctorId(sysUser.getId());
					// item.setGcDoctorName(sysUser.getName());
				}
			}
			// 设置主治医生id
			if (StringUtils.isNotBlank(item.getOutZzDoctorId())) {
				SysUser sysUser = mysqlMapper.selectUser(item.getOutZzDoctorId());
				if (sysUser != null) {
					item.setZzDoctorId(sysUser.getId());
					// item.setGcDoctorName(sysUser.getName());
				}
			}
			// 设置管床护士ID
			if (StringUtils.isNotBlank(item.getOutGcHsId())) {
				SysUser sysUser = mysqlMapper.selectUser(item.getOutGcHsId());
				if (sysUser != null) {
					item.setGcHsId(sysUser.getId());
					// item.setGcHsName(sysUser.getName());
				}

			}
			// 设置病区
			// if (StringUtils.isNotBlank(item.getBq())) {
			// SysOrganization bq =
			// mysqlMapper.selectOrg(Long.parseLong(item.getBq()));
			// if (bq != null) {
			// item.setBq(bq.getName());
			// }
			// }
			// 设置创建人ID
			if (user != null) {
				item.setCreateUserId(user.getId());
				// item.setUpdateUserId(user.getId());
			} else {
				item.setCreateUserId(1L);
				item.setUpdateUserId(1L);
			}
			// 设置创建时间
			// item.setUpdateTime(new Date());
			zyMapper.insert(item);
			ids.add(item.getId());
		}
		// 住院患者加入到方案中
		HuanZhePiPeiBo bo = new HuanZhePiPeiBo();
		bo.setZhuYuanInfoIds(ids);
		fangAnDingYiService.taskHuanZhePiPeiFangan(bo);
	}

    /**
     * 添加患者联系人
     * @param contacts
     * @param user
     * @throws SysException
     */
	@Override
	public void insertConTracs(List<HuanZheContract> contacts, ShiroUser user) throws SysException {
		for (HuanZheContract item : contacts) {
			if (item.getOutId() != null) {
				HuanZheInfo info = mysqlMapper.selectPatient(item.getOutId());
				if (info != null) {
					item.setHzId(info.getId());
				}
			}
			if (user != null) {
				item.setCreateUserId(user.getId());
			} else {
				item.setCreateUserId(1L);
			}
			contractMapper.insert(item);
		}

	}
    /***
	 *@author: Leslie
	 *@Date 2018/1/23 15:25
	 *@param: [contracts, zhuYuanInfos, huanZheInfos]
	 *@return void
	 *@throws
	 *@Description:存取还是一起存
	 */
	@Override
	public void insertData(List<HuanZheContract> contracts, List<ZhuYuanInfo> zhuYuanInfos, List<HuanZheInfo> huanZheInfos) throws SysException {
		insertHuanzhe(huanZheInfos,null);
		insertConTracs(contracts,null);
		insertZhuyuans(zhuYuanInfos,null);
	}

	@Override
	public Integer count(PatientQuery query) {
		if (query.getBoEdTime()==null){
			throw new SysException("结束时间为空");
		}
		if (query.getBoStTime()==null){
			throw new SysException("开始时间为空");
		}
		return mysqlMapper.count(query);
	}

	@Override
	public List<String> findZyNos(PatientQuery query) {
		if (query.getBoEdTime()==null){
			throw new SysException("结束时间为空");
		}
		if (query.getBoStTime()==null){
			throw new SysException("开始时间为空");
		}
		return mysqlMapper.findZyNos(query);
	}

    @Override
    public int existHos(PatientQuery query) {
		if (query.getZyNo()==null){
			throw new SysException("请输入住院号");
		}
		return mysqlMapper.existHos(query);
    }


}
