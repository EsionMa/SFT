/**
 * 2017-12-18 18:55:55
 */
package com.wangzhixuan.service.huanzhe.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wangzhixuan.mapper.fuyou.FuYouMenZhenInfoMapper;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouMenZhenInfoService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.mapper.huanzhe.MenZhenInfoMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;
import com.wangzhixuan.model.vo.huanzhe.MenZhenInfoVo;
import com.wangzhixuan.service.ISysDictionariesService;
import com.wangzhixuan.service.ISysOrganizationService;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;
import com.wangzhixuan.service.huanzhe.IMenZhenInfoService;
import com.wangzhixuan.service.icd.IIcdService;

/**
 * @author Esion
 *
 */
@Service
public class MenZhenInfoServiceImpl extends ServiceImpl<MenZhenInfoMapper, MenZhenInfo> implements IMenZhenInfoService {

	private static final Logger log = LoggerFactory.getLogger(HuanZheInfoServiceImpl.class);
	@Autowired
	private IHuanZheInfoService huanZheInfoService;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private ISysOrganizationService organizationService;
	@Autowired
	private MenZhenInfoMapper menZhenInfoMapper;
	@Autowired
	private ISysDictionariesService dictionariesService;
	@Autowired
	private IIcdService icdService;
	@Autowired
	private IFuYouMenZhenInfoService fuYouMenZhenInfoService;
	@Autowired
	private FuYouMenZhenInfoMapper fuYouMenZhenInfoMapper;

	/**
	 *
	 * @param vo
	 * @param user
	 * @return
	 */
	@Override
	public MenZhenInfoVo saveOrUpdate(MenZhenInfoVo vo, ShiroUser user) {
		HuanZheInfo huanzheInfo = huanZheInfoService.selectByHzNoAndHzId(vo.getHzNo(), vo.getHzId());
		if (huanzheInfo == null) {
			throw new SysException("不存在患者号[患者号]为" + vo.getHzNo() + "的患者");
		}
		vo.setHzName(huanzheInfo.getName());
		vo.setHzId(huanzheInfo.getId());
		// 设置年龄
		if (vo.getAge() != null && huanzheInfo.getBirthday() != null) {
			try {
				vo.setAge(DateUtils.getAge(huanzheInfo.getBirthday()));
			} catch (Exception e) {
				log.warn("患者年龄计算失败", e);
			}
		}
		vo.setSex(huanzheInfo.getSex());
		// 添加门诊医生姓名
		if (StringUtils.isBlank(vo.getDoctorName())) {
			if (vo.getDoctorId() != null) {
				UserVo doctor = userService.selectVoById(vo.getDoctorId());
				if (doctor != null) {
					vo.setDoctorName(doctor.getName());
				}
			}
		}
		/*
		 * //设置就诊医生姓名 if (StringUtils.isBlank(vo.getDisDoctorName())){ if
		 * (StringUtils.isNotBlank(vo.getDisDoctorId())){ UserVo doctor=
		 * userService.selectVoById(Long.parseLong(vo.getDisDoctorId())); if
		 * (doctor!=null){ vo.setDisDoctorName(doctor.getName()); } } }
		 */

		// 添加门诊科室名
		if (StringUtils.isBlank(vo.getMzDeptName())) {
			if (vo.getMzDeptId() != null) {
				SysOrganization organization = organizationService.selectById(vo.getMzDeptId());
				if (organization != null) {
					vo.setMzDeptName(organization.getName());
				}
			}
		}
		// 添加就诊科室名
		/*
		 * if (StringUtils.isBlank(vo.getDisDeptName())){ if
		 * (StringUtils.isNotBlank(vo.getDisDeptId())){ SysOrganization
		 * organization=organizationService.selectById(Long.parseLong(vo.
		 * getDisDeptId())); if (organization!=null){
		 * vo.setDisDeptName(organization.getName()); } } }
		 */
		if (vo.getId() == null) {
			vo.setCreateUserId(user.getId());
			vo.setCreateTime(new Date());
			insert(vo);
		} else {
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			log.debug("删除妇幼信息");
			fuYouMenZhenInfoService.deleteByHzId(vo.getId());
			updateById(vo);
		}
		List<FuYouMenZhenInfoVo> fuYouMenZhenInfoVos = vo.getPregnant();
		if (fuYouMenZhenInfoVos != null && fuYouMenZhenInfoVos.size() > 0) {
			log.debug("保存{}个妇幼信息", fuYouMenZhenInfoVos.size());
			// 保存患者妇幼信息
			for (FuYouMenZhenInfoVo fuYouMenZhenInfoVo : fuYouMenZhenInfoVos) {
				fuYouMenZhenInfoVo.setMzId(vo.getId());
				fuYouMenZhenInfoService.insert(fuYouMenZhenInfoVo);
			}
		}
		return vo;
	}

	@Override
	public PageInfo dataGrid(MenZhenInfoVo vo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(vo.getPage(), vo.getRows());
		EntityWrapper<MenZhenInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(vo.getHzName())) {
			wrapper.where("hz_name={0}", vo.getHzName());
		}
		if (StringUtils.isNotBlank(vo.getMzNo())) {
			wrapper.where("mz_no={0}", vo.getMzNo());
		}
		if (vo.getMzDeptId() != null) {
			wrapper.where("mz_dept_id={0}", vo.getMzDeptId());
		}
		if (StringUtils.isNotBlank(vo.getDiseaseCode())) {
			wrapper.where("disease_code={0}", vo.getDiseaseCode());
		}
		if(StringUtils.isNotBlank(vo.getSex())){
			wrapper.where("sex={0}",vo.getSex());
		}
		if(StringUtils.isNotBlank(vo.getDoctorName())){
			wrapper.where("doctor_name={0}",vo.getDoctorName());
		}
		if(StringUtils.isNotBlank(vo.getRegisType())){
			wrapper.where("regis_type={0}",vo.getRegisType());
		}
		if(StringUtils.isNotBlank(vo.getRegisWay())){
			wrapper.where("regis_way={0}",vo.getRegisWay());
		}
		if(vo.getAge() == null){
			if(vo.getAgeMin() != null){
				wrapper.where("age>={0}",vo.getAgeMin());
			}
			if(vo.getAgeMax() != null){
				wrapper.where("age<={0}",vo.getAgeMax());
			}
		}
		if (vo.getStaDate() != null) {
			wrapper.where("mz_time>={0}", vo.getStaDate());
		}
		if (vo.getEndDate() != null) {
			wrapper.where("mz_time<={0}", vo.getEndDate());
		}
		wrapper.orderBy("mz_time", false);
		Page<Map<String, Object>> mapPage = selectMapsPage(page, wrapper);
		List<Map<String, Object>> list = mapPage.getRecords();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(list);
		pageInfo.setTotal(mapPage.getTotal());
		return pageInfo;
	}

	@Override
	public MenZhenInfoVo selectDetailById(MenZhenInfoVo vo) {
		if (vo.getId() == null) {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
		MenZhenInfo menZhenInfo = selectById(vo.getId());
		if (menZhenInfo.getRegisWay() != null) {
			SysDictionaries way = dictionariesService.getByCode(menZhenInfo.getRegisWay());
			vo.setRegisWayName(way == null ? null : way.getName());
		}
		if (menZhenInfo.getRegisType() != null) {
			SysDictionaries type = dictionariesService.getByCode(menZhenInfo.getRegisType());
			vo.setRegisTypeName(type == null ? null : type.getName());
		}
		if (StringUtils.isNotBlank(menZhenInfo.getDiagnose())) {
			Icd icd = icdService.selectById(menZhenInfo.getDiagnose());
			menZhenInfo.setDiagnose(icd.getName());
		}
		if (StringUtils.isNotBlank(menZhenInfo.getDiagnose1())) {
			Icd icd = icdService.selectById(menZhenInfo.getDiagnose1());
			menZhenInfo.setDiagnose1(icd.getName());
		}
		if (StringUtils.isNotBlank(menZhenInfo.getDiagnose2())) {
			Icd icd = icdService.selectById(menZhenInfo.getDiagnose2());
			menZhenInfo.setDiagnose2(icd.getName());
		}
		BeanUtils.copy(menZhenInfo, vo);
		List<FuYouMenZhenInfoVo> fuYouMenZhenInfoVos = fuYouMenZhenInfoService.selectByHzId(vo.getId());
		vo.setPregnant(fuYouMenZhenInfoVos);
		return vo;
	}

	@Override
	public void delete(Long id) throws SysException {
		if (id != null) {
			deleteById(id);
		}
	}

	@Override
	public PageInfo findByCondition(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setAsc(false);
		if (pageInfo.getCondition() != null) {
			List<MenZhenInfo> list = menZhenInfoMapper.findByCondition(page, pageInfo.getCondition());
			pageInfo.setRows(list);
			pageInfo.setTotal(page.getTotal());
			return pageInfo;
		} else {
			throw new SysException("无条件！！");
		}
	}

	/**
	 * @Author: Leslie
	 * @Description:添加问卷实例时所需要的
	 * @Date 2017/10/30 14:37
	 * @Param:
	 */
	@Override
	public PageInfo findByGroup(FangAnMenZhenGroupVo group, Integer nowPage, Integer pageSize) throws SysException {
		Page<MenZhenInfo> page = new Page<>(nowPage, pageSize);
		// 性别(全部)
		if ("2".equals(group.getSex())) {
			group.setSex(null);
		}
		// List<MenZhenInfo> MenZhenInfos =
		// menZhenInfoMapper.findByGroup(menZhenInfoPage, group);
		EntityWrapper<MenZhenInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(group.getSex())) {
			wrapper.where("sex={0}", group.getSex());
		}
		if (group.getAgeMin() != null) {
			wrapper.where("age>={0}", group.getAgeMin());
		}
		if (group.getAgeMax() != null) {
			wrapper.where("age<={0}", group.getAgeMax());
		}
		if (group.getMzCountMin() != null) {
			wrapper.where("mz_count>={0}", group.getMzCountMin());
		}
		if (group.getMzCountMax() != null) {
			wrapper.where("mz_count<={0}", group.getMzCountMax());
		}
		if (group.getDateMin() != null) {
			wrapper.where("mz_time>={0}", group.getDateMin());
		}
		if (group.getDateMax() != null) {
			wrapper.where("mz_time<={0}", group.getDateMax());
		}
		// 科室
		if (StringUtils.isNotBlank(group.getDeptIds())) {
			wrapper.in("mz_dept_id", group.getDeptIds());
		}
		// 诊断
		if (StringUtils.isNotBlank(group.getDiseaseCodes())) {
			wrapper.in("diagnose", group.getDiseaseCodes());
		}
		if (StringUtils.isNotBlank(group.getRegisTypes())) {
			wrapper.in("regis_type", group.getRegisTypes());
		}
		if (StringUtils.isNotBlank(group.getRegisWays())) {
			wrapper.in("regis_way", group.getRegisWays());
		}
		if (group.getMenZhenInfoIds() != null && group.getMenZhenInfoIds().size() > 0) {
			wrapper.in("id", group.getMenZhenInfoIds());
		}
		selectPage(page, wrapper);
		List<MenZhenInfo> MenZhenInfos = page.getRecords();
		/*
		 * for (ZhuYuanInfo item : zhuYuanInfos) { if (item.getCyDeptId() !=
		 * null) { SysOrganization sys =
		 * sysOrganizationMapper.selectById(item.getCyDeptId()); if (sys !=
		 * null) { item.setCyDeptName(sys.getName()); } } if (item.getRyDeptId()
		 * != null) { SysOrganization sys =
		 * sysOrganizationMapper.selectById(item.getRyDeptId()); if (sys !=
		 * null) { item.setRyDeptName(sys.getName()); } } }
		 */
		PageInfo pageInfo = new PageInfo();
		pageInfo.setNowpage(nowPage);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(MenZhenInfos.size());
		pageInfo.setRows(MenZhenInfos);
		return pageInfo;
	}

    @Override
    public Map<String, Object> getByTimeAndId(Map<String, Object> map) {
		Wrapper<MenZhenInfo> wrapper=new EntityWrapper<>();
		wrapper.where("hz_id={0}",map.get("hzId"));
		wrapper.where("mz_time={0}",map.get("otherTime"));
		wrapper.orderBy("mz_time",false);
		List<Map<String,Object>> maps=selectMaps(wrapper);
        return maps==null?null:maps.get(0);
    }
}
