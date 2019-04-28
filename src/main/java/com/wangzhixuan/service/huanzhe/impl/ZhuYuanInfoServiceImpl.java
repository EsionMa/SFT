package com.wangzhixuan.service.huanzhe.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.mapper.fuyou.FuYouZhuYuanInfoMapper;
import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouZhuYuanInfoService;
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
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.mapper.huanzhe.ZhuYuanInfoMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import com.wangzhixuan.service.huanzhe.query.ZyConditionQuery;
import com.wangzhixuan.service.icd.IIcdService;

@Service
public class ZhuYuanInfoServiceImpl extends ServiceImpl<ZhuYuanInfoMapper, ZhuYuanInfo> implements IZhuYuanInfoService {
	private static final Logger log = LoggerFactory.getLogger(ZhuYuanInfoServiceImpl.class);
	@Autowired
	private ZhuYuanInfoMapper zhuYuanInfoMapper;
	@Autowired
	private IHuanZheInfoService huanZheInfoService;
	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private IIcdService icdService;
	@Autowired
	private IFuYouZhuYuanInfoService fuYouZhuYuanInfoService;
	@Autowired
	private FuYouZhuYuanInfoMapper fuYouZhuYuanInfoMapper;

	@Override
	public PageInfo selectDataGrid(ZhuYuanInfoVo vo) throws SysException {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(vo.getPage(), vo.getRows());
		EntityWrapper<ZhuYuanInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(vo.getHzName())) {
			wrapper.where("hz_name={0}", vo.getHzName());
		}
		if (StringUtils.isNotBlank(vo.getZyNo())) {
			wrapper.where("zy_no={0}", vo.getZyNo());
		}
		if (StringUtils.isNotBlank(vo.getHzNo())) {
			wrapper.where("hz_no={0}", vo.getHzNo());
		}
		if (StringUtils.isNotBlank(vo.getCyzd())) {
			wrapper.where("cyzd={0}", vo.getCyzd());
		}
		if (vo.getCyDeptId() != null) {
			wrapper.where("cy_dept_id={0}", vo.getCyDeptId());
		}
			if (vo.getRyDeptId() != null) {
			wrapper.where("ry_dept_id={0}", vo.getRyDeptId());
		}
		if (StringUtils.isNotBlank(vo.getSex())){
			wrapper.where("sex={0}",vo.getSex());
		}
		if(StringUtils.isNotBlank(vo.getGcDoctorName())){
			wrapper.where("gc_doctor_name={0}",vo.getGcDoctorName());
		}
		if(StringUtils.isNotBlank(vo.getGcHsName())){
			wrapper.where("gc_hs_name={0}",vo.getGcHsName());
		}
		if(StringUtils.isNotBlank(vo.getCyqk())){
			wrapper.where("cyqk={0}",vo.getCyqk());
		}
		if(StringUtils.isNotBlank(vo.getCwno())){
			wrapper.where("cwno={0}",vo.getCwno());
		}
		if(vo.getZycount() != null){
			wrapper.where("zycount={0}",vo.getZycount());
		}
		if(vo.getAge() == null){
			if(vo.getAgeMin() != null){
				wrapper.where("age>={0}",vo.getAgeMin());
			}
			if(vo.getAgeMax() != null){
				wrapper.where("age<={0}",vo.getAgeMax());
			}
		}
		if ("1".equals(vo.getTimeType())) {
			// 入院时间
			if (vo.getStaDate() != null) {
				wrapper.where("rytime>={0}", vo.getStaDate());
			}
			if (vo.getEndDate() != null) {
				wrapper.where("rytime<={0}", vo.getEndDate());
			}
			wrapper.orderBy("rytime", false);
		} else if ("2".equals(vo.getTimeType())) {
			// 出院时间
			if (vo.getStaDate() != null) {
				wrapper.where("cytime>={0}", vo.getStaDate());
			}
			if (vo.getEndDate() != null) {
				wrapper.where("cytime<={0}", vo.getEndDate());
			}
			wrapper.orderBy("cytime", false);
		}
		Page<Map<String, Object>> mapPage = selectMapsPage(page, wrapper);
		List<Map<String, Object>> list = mapPage.getRecords();
		for (Map<String, Object> map : list) {
			if (map.get("cyzd") != null) {
				Icd icd = icdService.selectById(map.get("cyzd").toString());
				map.put("cyzd", icd == null ? null : icd.getName());
			}
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(list);
		pageInfo.setTotal(mapPage.getTotal());
		return pageInfo;
	}



	@Override
	public ZhuYuanInfoVo saveOrUpdate(ZhuYuanInfoVo vo, ShiroUser user)
			throws SysException {
		HuanZheInfo huanzheInfo = huanZheInfoService.selectByHzNoAndHzId(vo.getHzNo(), vo.getHzId());
		if (huanzheInfo == null) {
			throw new SysException("不存在患者号[hzNO]为" + vo.getHzNo() + "的患者");
		}
		vo.setHzName(huanzheInfo.getName());
		vo.setHzId(huanzheInfo.getId());
		if (vo.getAge() != null && huanzheInfo.getBirthday() != null) {
			try {
				DateUtils.getAge(huanzheInfo.getBirthday());
			} catch (Exception e) {
				log.warn("患者年龄计算失败", e);
			}
		}
		// 添加住院医生名
		if (vo.getZyDoctorId() != null) {
			vo.setZyDoctorName(userMapper.selectById(vo.getZyDoctorId()).getName());
		}
		// // 添加主治医生名
		// if (vo.getZzDoctorId() != null) {
		// vo.setZzDoctorName(userMapper.selectById(vo.getZzDoctorId()).getName());
		// }
		// 添加主任医生
		if (vo.getZrDoctorId() != null) {
			vo.setZrDoctorName(userMapper.selectById(vo.getZrDoctorId()).getName());
		}
		// 添加管床护士
		if (vo.getGcHsId() != null) {
			vo.setGcHsName(userMapper.selectById(vo.getGcHsId()).getName());
		}
		// 添加住院科室
		if (vo.getCyDeptId() != null) {
			SysOrganization sysOrganization = sysOrganizationMapper.selectById(vo.getCyDeptId());
			if (sysOrganization != null) {
				vo.setCyDeptName(sysOrganization.getName());
			}
		}
		if (vo.getRyDeptId() != null) {
			SysOrganization sysOrganization = sysOrganizationMapper.selectById(vo.getRyDeptId());
			if (sysOrganization != null) {
				vo.setRyDeptName(sysOrganization.getName());
			}
		}
		vo.setUpdateUserId(user.getId());
		vo.setUpdateTime(new Date());
		if (vo.getId() == null) {
			vo.setCreateUserId(user.getId());
			vo.setCreateTime(new Date());

			insert(vo);
		} else {
			log.debug("删除妇幼信息");
			fuYouZhuYuanInfoService.deleteByHzId(vo.getId());
			updateById(vo);
		}
		List<FuYouZhuYuanInfoVo> fuYouZhuYuanInfoVos = vo.getPregnant();
		if (fuYouZhuYuanInfoVos != null && fuYouZhuYuanInfoVos.size() > 0) {
			log.debug("保存{}个妇幼信息", fuYouZhuYuanInfoVos.size());
			// 保存患者妇幼信息
			for (FuYouZhuYuanInfoVo fuYouZhuYuanInfoVo : fuYouZhuYuanInfoVos) {
				fuYouZhuYuanInfoVo.setZyId(vo.getId());
				fuYouZhuYuanInfoService.insert(fuYouZhuYuanInfoVo);
			}
		}
		return vo;
	}

	@Override
	public ZhuYuanInfoVo selectDetailById(ZhuYuanInfoVo vo) throws SysException {
		if (vo.getId() == null) {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
		// 前端地方去转吧
		ZhuYuanInfo zhuyuanInfo = selectById(vo.getId());
		/*
		 * if (StringUtils.isNotBlank(zhuyuanInfo.getPlzd())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getPlzd()); zhuyuanInfo.setPlzd(icd
		 * == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getRyzd())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getRyzd()); zhuyuanInfo.setRyzd(icd
		 * == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getRyzd1())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getRyzd1());
		 * zhuyuanInfo.setRyzd1(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getRyzd2())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getRyzd2());
		 * zhuyuanInfo.setRyzd2(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getRyzd3())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getRyzd3());
		 * zhuyuanInfo.setRyzd3(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getRyzd4())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getRyzd4());
		 * zhuyuanInfo.setRyzd4(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getCyzd())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getCyzd()); zhuyuanInfo.setCyzd(icd
		 * == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getCyzd1())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getCyzd1());
		 * zhuyuanInfo.setCyzd1(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getCyzd2())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getCyzd2());
		 * zhuyuanInfo.setCyzd2(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getCyzd3())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getCyzd3());
		 * zhuyuanInfo.setCyzd3(icd == null ? null : icd.getName()); } if
		 * (StringUtils.isNotBlank(zhuyuanInfo.getCyzd4())) { Icd icd =
		 * icdService.selectById(zhuyuanInfo.getCyzd4());
		 * zhuyuanInfo.setCyzd4(icd == null ? null : icd.getName()); }
		 */
		/*
		 * //添加出院，入院科室 if (vo.getRyDeptId() != null) { SysOrganization sys =
		 * sysOrganizationMapper.selectById(vo.getRyDeptId()); if (sys != null)
		 * { zhuyuanInfo.setRyDeptName(sys.getName()); } } if (vo.getCyDeptId()
		 * != null) { SysOrganization sys =
		 * sysOrganizationMapper.selectById(vo.getCyDeptId()); if (sys != null)
		 * { zhuyuanInfo.setCyDeptName(sys.getName()); } }
		 */
		BeanUtils.copy(zhuyuanInfo, vo);
		List<FuYouZhuYuanInfoVo> fuYouZhuYuanInfoVos = fuYouZhuYuanInfoService.selectByHzId(vo.getId());
		vo.setPregnant(fuYouZhuYuanInfoVos);
		return vo;
	}

	@Override
	public List<ZhuYuanInfoVo> findByCondition(ZyConditionQuery query) throws SysException {

		return null;
	}

	@Override
	public void delete(Long id) throws SysException {
		if (id != null) {
			deleteById(id);
		}
	}

	@Override
	public PageInfo findByGroup(FangAnZhuYuanGroupVo group, Integer nowPage, Integer pageSize) throws SysException {
		Page<ZhuYuanInfo> page = new Page<>(nowPage, pageSize);
		// 性别(全部)
		if ("2".equals(group.getSex())) {
			group.setSex(null);
		}
		// List<ZhuYuanInfo> zhuYuanInfos = zhuYuanInfoMapper.findByGroup(page,
		// group);
		EntityWrapper<ZhuYuanInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(group.getSex())) {
			wrapper.where("sex={0}", group.getSex());
		}
		if (group.getZxhznl() != null) {
			wrapper.where("age>={0}", group.getZxhznl());
		}
		if (group.getZdhznl() != null) {
			wrapper.where("age<={0}", group.getZdhznl());
		}
		if ("1".equals(group.getSxsjlx())) {
			// 入院时间
			if (group.getZxsxsj() != null) {
				wrapper.where("rytime>={0}", group.getZxsxsj());
			}
			if (group.getZdsxsj() != null) {
				wrapper.where("rytime<={0}", group.getZdsxsj());
			}
		} else if ("2".equals(group.getSxsjlx())) {
			// 出院时间
			if (group.getZxsxsj() != null) {
				wrapper.where("cytime>={0}", group.getZxsxsj());
			}
			if (group.getZdsxsj() != null) {
				wrapper.where("cytime<={0}", group.getZdsxsj());
			}
		}
		// 科室
		if (StringUtils.isNotBlank(group.getDeptIds())) {
			wrapper.in("cy_dept_id", group.getDeptIds());
		}
		// 诊断
		if (StringUtils.isNotBlank(group.getZdCodes())) {
			wrapper.in("cyzd", group.getZdCodes());
		}
		if (group.getZhuYuanInfoIds() != null && group.getZhuYuanInfoIds().size() > 0) {
			wrapper.in("id", group.getZhuYuanInfoIds());
		}
		selectPage(page, wrapper);
		List<ZhuYuanInfo> zhuYuanInfos = page.getRecords();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setNowpage(nowPage);
		pageInfo.setPagesize(pageSize);
		pageInfo.setTotal(zhuYuanInfos.size());
		pageInfo.setRows(zhuYuanInfos);
		return pageInfo;
	}

	/**
	 * @Author: wangjun
	 * @Description:住院信息条件查询
	 * @Date 2017/7/31 18:39
	 */
	@Override
	public PageInfo findByCondition(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		if (pageInfo.getCondition() != null) {
			List<ZhuYuanInfoVo> list = zhuYuanInfoMapper.findByCondition(page, pageInfo.getCondition());
			pageInfo.setRows(list);
			pageInfo.setTotal(list.size());
			return pageInfo;
		} else {
			throw new SysException("传入条件为空");
		}

	}

	@Override
	public List<Long> insertZyInfoBatch(List<ZhuYuanInfo> zhuYuanInfos) throws SysException {
		List<Long> zhuYuanInfoIds = zhuYuanInfoMapper.insertZyInfoBatch(zhuYuanInfos);
		return zhuYuanInfoIds;
	}

	@Override
	public RespResult<Integer> export() {
		RespResult<Integer> result = new RespResult<>();
		Map<String, Object> map = new HashMap<>();
		map.put("staTime", DateUtils.getDayBegin());
		map.put("endTime", new Date());
		result.getSuccess(zhuYuanInfoMapper.export(map));
		return result;
	}

	@Override
	public Map<String, Object> getByTimeAndId(Map<String, Object> map) {
		EntityWrapper<ZhuYuanInfo> wrapper = new EntityWrapper<>();
		wrapper.where("cytime={0}", map.get("otherTime"));
		wrapper.where("hz_id={0}", map.get("hzId"));
		wrapper.orderBy("cytime", false);
		List<Map<String, Object>> maps = selectMaps(wrapper);
		return maps == null ? null : maps.get(0);
	}
}
