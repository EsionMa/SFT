package com.wangzhixuan.service.wenjuan.impl;

import java.text.DecimalFormat;
import java.util.*;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.SysUserOrganization;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.phone.Phone;
import com.wangzhixuan.model.vo.huanzhe.HuanZheInfoVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;
import com.wangzhixuan.model.vo.wj.PageSatisFaction;
import com.wangzhixuan.model.vo.wj.SatisfactionVo;
import com.wangzhixuan.model.wenjuan.*;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;
import com.wangzhixuan.service.huanzhe.IMenZhenInfoService;
import com.wangzhixuan.service.phone.IPhoneService;
import com.wangzhixuan.service.pj.IPingJiaService;
import com.wangzhixuan.service.wenjuan.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.commons.utils.SuiFangUtils;
import com.wangzhixuan.mapper.fa.FangAnDingYiItemMapper;
import com.wangzhixuan.mapper.fa.FangAnDingYiMapper;
import com.wangzhixuan.mapper.huanzhe.HuanZheTagMapper;
import com.wangzhixuan.mapper.wenjuan.WenJuanItemMapper;
import com.wangzhixuan.mapper.wenjuan.WenJuanMapper;
import com.wangzhixuan.mapper.wenjuan.WenJuanShiLiMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.fa.FangAnDingYi;
import com.wangzhixuan.model.huanzhe.HuanZheTag;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.TiMuShiLiVo;
import com.wangzhixuan.model.vo.TiMuVo;
import com.wangzhixuan.model.vo.WenJuanShiLiDataGridQuery;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.vo.fa.FangAnDingYiItemVo;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.service.ISysUserOrganizationService;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.fa.IFangAnMenZhenGroupService;
import com.wangzhixuan.service.fa.IFangAnZhuYuanGroupService;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import com.wangzhixuan.service.icd.IIcdService;

@Service
public class WenJuanShiLiServiceImpl extends ServiceImpl<WenJuanShiLiMapper, WenJuanShiLi>
		implements IWenJuanShiLiService {
	private static final Logger logger = LoggerFactory.getLogger(WenJuanShiLiServiceImpl.class);
	@Autowired
	private WenJuanShiLiMapper wenJuanShiLiMapper;
	@Autowired
	private WenJuanMapper wenJuanMapper;
	@Autowired
	private FangAnDingYiMapper fangAnDingYiMapper;
	@Autowired
	private FangAnDingYiItemMapper fangAnDingYiItemMapper;
	@Autowired
	private HuanZheTagMapper huanZheTagMapper;
	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;
	@Autowired
	private WenJuanItemMapper wenJuanItemMapper;
	@Autowired
	private IFangAnZhuYuanGroupService fangAnZhuYuanGroupService;
	@Autowired
	private IFangAnMenZhenGroupService fangAnMenZhenGroupService;
	@Autowired
	private IZhuYuanInfoService zhuYuanInfoService;
	@Autowired
	private IMenZhenInfoService menZhenInfoService;
	@Autowired
	private IWenJuanService wenJuanService;
	@Autowired
	private ITiMuService tiMuService;
	@Autowired
	private ITiMuShiLiService tiMuShiLiService;
	@Autowired
	private IPingJiaService pingJiaService;
	@Autowired
	private IPhoneService phoneService;
	@Autowired
	private IHuanZheInfoService huanZheInfoService;
	@Autowired
	private IIcdService icdService;
	@Autowired
	private ISysUserOrganizationService userOrganizationService;
	@Autowired
	private IWenJuanShiLiItemService wenJuanShiLiItemService;
	@Autowired
	private ITiMuXuanXiangService xuanXiangService;
	@Autowired
	private ISysUserService userService;

	private static final DecimalFormat DOUBLE_DF = new DecimalFormat("#.00");

	@Override
	public void saveOrUpdate(WenJuanShiLiVo vo, ShiroUser shiroUser) {
		logger.debug("执行问卷，保存问卷结果");
		if (StringUtils.isBlank(vo.getZxfs())) {
			throw new SysException("执行方式不能为空");
		}
		if ("4".equals(vo.getStatus())) {// 预约时间
			saveOrUpdateByLater(vo, shiroUser);
		}
		if ("1".equals(vo.getZxfs())) {// 按照计划执行随访
			saveOrUpdateByJh(vo, shiroUser);
			return;
		}
		if ("2".equals(vo.getZxfs())) {// 无计划的执行随访
			saveOrUpdateNoJh(vo, shiroUser);
		}
	}

	private void saveOrUpdateByLater(WenJuanShiLiVo vo, ShiroUser shiroUser) {
		WenJuanShiLi sl = wenJuanShiLiMapper.selectById(vo.getId());
		if (sl == null) {
			throw new SysException("不存在的问卷，请确认");
		}
		if (vo.getYdDate() == null) {
			throw new SysException("预计时间不能为空");
		}
		Date today = DateUtils.getDayBegin(new Date());
		if (vo.getYdDate().getTime() < today.getTime()) {
			throw new SysException("预定时间不能小于今天");
		}
		sl.setJhsfDate(vo.getYdDate());
		sl.setUpdateTime(new Date());
		sl.setUpdateUserId(shiroUser.getId());
		wenJuanShiLiMapper.updateById(sl);
	}
    /**
	 *@author: Leslie
	 *@Date 2018/3/30 17:04
	 *@param: [vo, shiroUser]
	 *@return void
	 *@throws
	 *@Description: 按计划执行随访问卷？
	 */
	private void saveOrUpdateByJh(WenJuanShiLiVo vo, ShiroUser shiroUser) {
		Date currentDate = new Date();
		if (vo.getId() == null) {
			throw new SysException("不存在的问卷计划执行ID");
		}
		WenJuanShiLi wenJuanShiLi = selectById(vo.getId());
		if (wenJuanShiLi == null) {
			throw new SysException("不存在的问卷计划执行");
		}
		//获取住院信息？
		Long hzLsId = wenJuanShiLi.getHzLsId();
		ZhuYuanInfo zhuYuanInfo = null;
		MenZhenInfo menZhenInfo=null;
		Long deptId=null;
		Date time=null;
		Long doctorId=null;
		//根据患者来源添加住院数据或者是门诊数据
		if (hzLsId != null) {
			if (vo.getHzly()!=null&&vo.getHzly()==1){
				zhuYuanInfo = zhuYuanInfoService.selectById(hzLsId);
				deptId=zhuYuanInfo.getCyDeptId();
				time=zhuYuanInfo.getCytime();
				doctorId=zhuYuanInfo.getZzDoctorId();
			}else if (vo.getHzly()==2){
				menZhenInfo=menZhenInfoService.selectById(hzLsId);
				deptId=menZhenInfo.getMzDeptId();
				doctorId=menZhenInfo.getDoctorId();
				time=menZhenInfo.getMzTime();
			}else {
				throw new SysException("未知的随访来源");
			}
		}

		String status = vo.getStatus();
		if (StringUtils.isBlank(status)) {
			throw new SysException("调查结果状态不能为空");
		}
		wenJuanShiLi.setUpdateUserId(shiroUser == null ? null : shiroUser.getId());
		wenJuanShiLi.setUpdateTime(currentDate);
		// 删除之前的题目实例（表：hzsf_tm_sl）
		tiMuShiLiService.deleteBySlId(vo.getId());
		List<WenJuanVo> wjList = vo.getWenJuans();
		for (WenJuanVo wenJuanVo : wjList) {
			Double wjScore = 0D;
			List<TiMuShiLiVo> tmsls = wenJuanVo.getTmsls();
			// 计算每道题的分数
			if (tmsls != null && tmsls.size() > 0) {
				for (TiMuShiLiVo timusl : tmsls) {
					if ("1".equals(timusl.getTxxz())) {
						Long tmId = timusl.getTmId();
						Long xxId = timusl.getXxId();
						Double tmScore = tiMuService.selectScoreById(tmId);
						Double scorePercent = xuanXiangService.selectPercent(xxId);
						timusl.setScore(tmScore * scorePercent);
						wjScore += (tmScore * scorePercent);
					}
				}
				// 问卷分数本可以设为String类型，忘了
				wenJuanVo.setGrade(Double.parseDouble(DOUBLE_DF.format(wjScore)));
				Long itemId = wenJuanShiLiItemService.wenJuanShiLiItemSave(wenJuanShiLi, wenJuanVo);
				//接口兼容性
				tiMuShiLiService.saveOrUpdate(tmsls, vo.getId(), wenJuanVo.getId(),time ,deptId,doctorId, shiroUser, itemId);
			}
		}
		wenJuanShiLi.setDcTime(currentDate);
		if ("1".equals(status)) {
			wenJuanShiLi.setStatus("2");
		} else {
			wenJuanShiLi.setStatus(status);
		}
		wenJuanShiLi.setHzMobilePhone(vo.getHzMobilePhone());
		wenJuanShiLi.setDcxj(vo.getDcxj());

		List<PingJiaVo> pingJias = vo.getPingJia();
		List<Long> ids = new ArrayList<>();
		for (int i = 0; i < pingJias.size(); i++) {
			ids.add(pingJias.get(i).getId());
		}
		//删除之前的评价？
		pingJiaService.deleteByByPjLyAndExtId(null, "" + wenJuanShiLi.getId(), ids);
		if (pingJias != null && pingJias.size() > 0) {
			for (int i = 0; i < pingJias.size(); i++) {
				PingJiaVo pingJia = pingJias.get(i);
				pingJia.setPjlyExt(vo.getId().toString());
				// 评价来源（住院随访）
				pingJia.setFaType(vo.getFaType());
				// 添加患者id
				pingJia.setHzId(wenJuanShiLi.getHzId());
				// 添加住院号
				if (zhuYuanInfo != null) {
					pingJia.setZyNo(zhuYuanInfo.getZyNo());
				} else {
					logger.debug("没有查询到住院信息");
				}
				// 添加电话号码
				pingJia.setPhoneNo(vo.getHzMobilePhone());
				pingJia.setPjly("1");
				pingJiaService.saveOrUpdate(pingJia, shiroUser);
			}
		}
		// 生成中间表需要的相关信息

		// 随访类型
		wenJuanShiLi.setFaType(vo.getFaType());
		updateById(wenJuanShiLi);
	}

	/**
	 * 无计划随访问卷执行保存，即大厅进行的评价机进行随访
	 *
	 * @param vo
	 * @param shiroUser
	 */
	private void saveOrUpdateNoJh(WenJuanShiLiVo vo, ShiroUser shiroUser) {
		String ybNo = vo.getYbno();
		String zlcard = vo.getZlcard();
		if (StringUtils.isBlank(ybNo) && StringUtils.isBlank(zlcard)) {
			throw new SysException("医保号或者诊疗卡号至少填一个");
		}
		if (StringUtils.isBlank(vo.getHzMobilePhone())) {
			throw new SysException("手机号不能为空");
		}
		HuanZheInfoVo huanZheInfoVo = new HuanZheInfoVo();
		huanZheInfoVo.setMobilephone(vo.getHzMobilePhone());
		huanZheInfoVo.setYbno(vo.getYbno());
		huanZheInfoVo.setZlcard(vo.getZlcard());
		HuanZheInfo huanZheInfo = huanZheInfoService.selectByYbNoOrPhone(huanZheInfoVo);

		Date currentDate = new Date();
		WenJuanShiLi wenJuanShiLi = new WenJuanShiLi();
		List<WenJuanVo> wjList = vo.getWenJuans();
		for (WenJuanVo wenJuanVo : wjList) {
			List<TiMuShiLiVo> tmsls = wenJuanVo.getTmsls();// TODO bug未測試
			wenJuanShiLi.setZlcard(zlcard);
			wenJuanShiLi.setYbno(ybNo);
			wenJuanShiLi.setZxfs(vo.getZxfs());
			wenJuanShiLi.setSfType("8");// 触摸屏
			wenJuanShiLi.setUpdateTime(currentDate);
			wenJuanShiLi.setDcTime(currentDate);
			wenJuanShiLi.setStatus("2");
			if (vo.getWjId() != null) {
				wenJuanShiLi.setWjId(vo.getWjId());
				WenJuan wenJuan = wenJuanMapper.selectById(vo.getWjId());
				if (wenJuan != null) {
					wenJuanShiLi.setWjzt(wenJuan.getWjzt());
				}
			}
			wenJuanShiLi.setHzMobilePhone(vo.getHzMobilePhone());
			wenJuanShiLi.setDcxj(vo.getDcxj());
			wenJuanShiLi.setCreateUserId(shiroUser == null ? null : shiroUser.getId());
			wenJuanShiLi.setCreateTime(currentDate);
			wenJuanShiLi.setUpdateUserId(shiroUser == null ? null : shiroUser.getId());
			wenJuanShiLi.setUpdateTime(currentDate);
			if (huanZheInfo != null) {
				wenJuanShiLi.setHzId(huanZheInfo.getId());
				wenJuanShiLi.setHzName(huanZheInfo.getName());
			}
			insert(wenJuanShiLi);
			ZhuYuanInfo zhuYuanInfo = new ZhuYuanInfo();
			zhuYuanInfo.setCyDeptId(vo.getDeptId());
			// 计算每道题的分数
			Double wjScore = 0D;
			// 若一道题都没有做，就是没有做
			if (tmsls != null && tmsls.size() > 0) {
				for (TiMuShiLiVo timusl : tmsls) {
					if ("1".equals(timusl.getTxxz())) {
						Long tmId = timusl.getTmId();
						Long xxId = timusl.getXxId();
						Double tmScore = tiMuService.selectScoreById(tmId);
						Double scorePercent = xuanXiangService.selectPercent(xxId);
						timusl.setScore(tmScore * scorePercent);
						wjScore += tmScore * scorePercent;
					}
				}
				// 问卷分数本可以设为String类型，忘了
				wenJuanVo.setGrade(Double.parseDouble(DOUBLE_DF.format(wjScore)));
				Long itemId = wenJuanShiLiItemService.wenJuanShiLiItemSave(wenJuanShiLi, wenJuanVo);
				tiMuShiLiService.saveOrUpdate(tmsls, vo.getId(), wenJuanVo.getId(), null,vo.getDeptId(),null, shiroUser, itemId);
			}
		}
	}

	@Override
	public void creteShiLiByFangAn(Long faId, FangAnDingYiItemVo fangAnDingYiItemVo, List<Long> infos) {
		FangAnDingYi fangAnDingYi = fangAnDingYiMapper.selectById(faId);
		if (fangAnDingYi == null) {
			logger.warn("创建方案实例警告，不存在的实例，方案ID：{}", faId);
			return;
		}
		// 第一步，选择患者
		if (fangAnDingYi.getHzly() == 1) {
			// 住院方案
			logger.debug("方案生成调查问卷实例，方案ID为{},方案名称为{}", faId, fangAnDingYi.getName());
			FangAnZhuYuanGroupVo fangAnZhuYuanGroupVo = fangAnZhuYuanGroupService.findByFangAnId(faId);
			if (fangAnZhuYuanGroupVo == null) {
				logger.debug("方案{},{}不存在住院用户群组，不会生成 问卷实例", faId, fangAnDingYi.getName());
				return;
			}
			if (infos == null || infos.size() < 1) {
				infos = null;
			}
			fangAnZhuYuanGroupVo.setZhuYuanInfoIds(infos);
			// 创建4000条随访执行实例
			creteShiLiByZyGroup(fangAnDingYi, fangAnZhuYuanGroupVo, fangAnDingYiItemVo, 1, 5000);
		} else if (fangAnDingYi.getHzly() == 2) {
			// 门诊方案
			logger.debug("方案生成调查问卷实例，方案ID为{},方案名称为{}", faId, fangAnDingYi.getName());
			FangAnMenZhenGroupVo fangAnMenZhenGroupVo = fangAnMenZhenGroupService.findByFangAnId(faId);
			if (fangAnMenZhenGroupVo == null) {
				logger.debug("方案{},{}不存在住院用户群组，不会生成 问卷实例", faId, fangAnDingYi.getName());
				return;
			}
			List<FangAnDingYiItemVo> fangAnDingYiItemVos = fangAnDingYiItemMapper.findByFangAnId(faId);
			if (fangAnDingYiItemVos == null || fangAnDingYiItemVos.size() < 1) {
				logger.debug("方案{},{}不存在方案内容，不会生成问卷实例", faId, fangAnDingYi.getName());
				return;
			}
			if (infos == null || infos.size() < 1) {
				infos = null;
			}
			fangAnMenZhenGroupVo.setMenZhenInfoIds(infos);
			// 创建4000条随访执行实例
			creteShiLiByMzGroup(fangAnDingYi, fangAnMenZhenGroupVo, fangAnDingYiItemVo, 1, 5000);
		} else {
			logger.debug("未知方案类型");
			throw new SysException("未知方案类型");
		}
		// 第二步，获取执行内容
	}

	/**
	 * 根据住院群组条件创建问卷实例 (预留多线程方案)
	 * 
	 * @param fangAnDingYi
	 * @param group
	 * @param fangAnDingYiItemVo
	 * @param nowPage
	 * @param pageSize
	 * @throws SysException
	 */
	@SuppressWarnings("unchecked")
	private void creteShiLiByZyGroup(FangAnDingYi fangAnDingYi, FangAnZhuYuanGroupVo group,
			FangAnDingYiItemVo fangAnDingYiItemVo, Integer nowPage, Integer pageSize) throws SysException {
		// 符合群组条件的住院患者
		PageInfo pageZhuYuanInfo = zhuYuanInfoService.findByGroup(group, nowPage, pageSize);
		int zyCount = 0;
		List<ZhuYuanInfo> zhuYuanInfos = null;
		if (pageZhuYuanInfo != null) {
			zhuYuanInfos = pageZhuYuanInfo.getRows();
			if (zhuYuanInfos != null) {
				zyCount = zhuYuanInfos.size();
			}
		}
		logger.debug("根据住院来源生成实例第{}次运行，每页{}条数据", nowPage, zyCount);
		if (zyCount < 1) {
			logger.debug("没有获取到符合条件的住院患者，本次不生成问卷实例数据");
		}
		//问卷列表放错地方，其何里面内存循环无关系
		List<Map<String, Object>> wjList = fangAnDingYiItemMapper.selectWenJuans(fangAnDingYiItemVo.getId());
		for (int j = 0; j < zyCount; j++) {
			ZhuYuanInfo zhuYuanInfo = zhuYuanInfos.get(j);
			WenJuanShiLi wenJuanShiLi = new WenJuanShiLi();
			if (wjList.size() < 1) {
				logger.warn("方案{}的内容{}没有问卷ID", fangAnDingYi.getName(), j + 1);
				return;
			}
			StringBuilder wjNames = new StringBuilder();
			for (Map<String, Object> map : wjList) {
				wjNames.append(map.get("wjzt").toString()).append(",");
			}
			if (wjNames.length() > 0) {
				wjNames.setLength(wjNames.length() - 1);

			}
			wenJuanShiLi.setFaId(fangAnDingYi.getId());
			wenJuanShiLi.setFaItemId(fangAnDingYiItemVo.getId());
			// wenJuanShiLi.setWjId(item.getWjId());//TODO 问卷ID（多个）
			wenJuanShiLi.setHzId(zhuYuanInfo.getHzId());
			// 患者流水ID=住院ID
			wenJuanShiLi.setHzLsId(zhuYuanInfo.getId());
			wenJuanShiLi.setHzName(zhuYuanInfo.getHzName());
			// （管床医生为主）//TODO
			wenJuanShiLi.setDoctorId(zhuYuanInfo.getGcDoctorId());
			wenJuanShiLi.setDoctorName(zhuYuanInfo.getZzDoctorName());
			wenJuanShiLi.setHsId(zhuYuanInfo.getGcHsId());
			wenJuanShiLi.setHsName(zhuYuanInfo.getGcHsName());
			if ("1".equals(group.getSxsjlx())) {
				// 1 入院时间
				wenJuanShiLi.setDeptId(zhuYuanInfo.getRyDeptId());
				wenJuanShiLi.setDeptName(zhuYuanInfo.getRyDeptName());
				wenJuanShiLi.setOtherTime(zhuYuanInfo.getRytime());
			} else {
				// 2 出院时间
				wenJuanShiLi.setDeptId(zhuYuanInfo.getCyDeptId());
				wenJuanShiLi.setDeptName(zhuYuanInfo.getCyDeptName());
				wenJuanShiLi.setOtherTime(zhuYuanInfo.getCytime());
			}
			wenJuanShiLi.setWjzt(wjNames.toString());// 问卷主题（多个）
			wenJuanShiLi.setDcTime(null);// 执行随访时间|调查时间
			wenJuanShiLi.setSfType(fangAnDingYiItemVo.getSfType());
			// 方式：按计划执行
			wenJuanShiLi.setZxfs("1");
			wenJuanShiLi.setSfnm("0");// TODO（是否匿名尚不明确）
			wenJuanShiLi.setStatus("1");
			wenJuanShiLi.setCreateTime(new Date());
			// 随访类型
			wenJuanShiLi.setFaType(fangAnDingYi.getFaType());
			//患者来源
			wenJuanShiLi.setHzly(fangAnDingYi.getHzly());
			//工具类获取
			List<Date> jhsfDates = SuiFangUtils.getJhsfDate(fangAnDingYiItemVo, zhuYuanInfo.getCytime(),
					zhuYuanInfo.getRytime(), null);
			for (int k = 0; k < jhsfDates.size(); k++) {
				// 计划随访时间
				wenJuanShiLi.setJhsfDate(jhsfDates.get(k));
				wenJuanShiLiMapper.insert(wenJuanShiLi);
			}
		}
	}

	/**
	 * 根据门诊群组条件创建问卷实例 (预留多线程方案)
	 * 
	 * @param fangAnDingYi
	 * @param group
	 * @param
	 * @param nowPage
	 * @param pageSize
	 * @throws SysException
	 */
	@SuppressWarnings("unchecked")
	private void creteShiLiByMzGroup(FangAnDingYi fangAnDingYi, FangAnMenZhenGroupVo group,
			FangAnDingYiItemVo fangAnDingYiItemVo, Integer nowPage, Integer pageSize) throws SysException {
		// 符合群组条件的门诊患者
		PageInfo pageMenZhenInfo = menZhenInfoService.findByGroup(group, nowPage, pageSize);
		int mzCount = 0;
		List<MenZhenInfo> menZhenInfos = null;
		if (pageMenZhenInfo != null) {
			menZhenInfos = pageMenZhenInfo.getRows();
			if (menZhenInfos != null) {
				mzCount = menZhenInfos.size();
			}
		}
		logger.debug("根据门诊来源生成实例第{}次运行，每页{}条数据", nowPage, mzCount);
		if (mzCount < 1) {
			logger.debug("没有获取到符合条件的门诊患者，本次不生成问卷实例数据");
		}
		for (int j = 0; j < mzCount; j++) {
			MenZhenInfo menZhenInfo = menZhenInfos.get(j);
			WenJuanShiLi wenJuanShiLi = new WenJuanShiLi();
			List<Map<String, Object>> wjList = fangAnDingYiItemMapper.selectWenJuans(fangAnDingYiItemVo.getId());
			if (wjList.size() < 1) {
				logger.warn("方案{}的内容{}没有问卷ID", fangAnDingYi.getName(), j + 1);
				return;
			}
			StringBuilder wjNames = new StringBuilder();
			for (Map<String, Object> map : wjList) {
				WenJuan wenJuan = wenJuanService.selectById(map.get("id").toString());
				if (wenJuan == null) {
					logger.warn("方案{}的内容{}没有问卷", fangAnDingYi.getName(), j + 1);
					return;
				}
				wjNames.append(wenJuan.getWjzt()).append(",");
			}
			if (wjNames.length() > 0) {
				wjNames.setLength(wjNames.length() - 1);

			}
			wenJuanShiLi.setFaId(fangAnDingYi.getId());
			wenJuanShiLi.setFaItemId(fangAnDingYiItemVo.getId());
			// wenJuanShiLi.setWjId(item.getWjId());// TODO
			wenJuanShiLi.setHzId(menZhenInfo.getHzId());
			// 患者流水ID=门诊ID
			wenJuanShiLi.setHzLsId(menZhenInfo.getId());
			wenJuanShiLi.setHzName(menZhenInfo.getHzName());
			wenJuanShiLi.setDoctorId(menZhenInfo.getDoctorId());
			wenJuanShiLi.setDoctorName(menZhenInfo.getDoctorName());
			// wenJuanShiLi.setHsId(menZhenInfo.getGcHsId());
			// wenJuanShiLi.setHsName(menZhenInfo.getGcHsName());
			wenJuanShiLi.setDeptId(menZhenInfo.getMzDeptId());
			wenJuanShiLi.setDeptName(menZhenInfo.getMzDeptName());
			wenJuanShiLi.setOtherTime(menZhenInfo.getMzTime());
			wenJuanShiLi.setWjzt(wjNames.toString());// 问卷主题（多个）
			wenJuanShiLi.setDcTime(null);// 执行随访时间|调查时间
			wenJuanShiLi.setSfType(fangAnDingYiItemVo.getSfType());
			// 方式：按计划执行
			wenJuanShiLi.setZxfs("1");
			wenJuanShiLi.setSfnm("0");// TODO（是否匿名尚不明确）
			wenJuanShiLi.setStatus("1");
			wenJuanShiLi.setCreateTime(new Date());
			// 随访类型
			wenJuanShiLi.setFaType(fangAnDingYi.getFaType());
			//患者来源
			wenJuanShiLi.setHzly(fangAnDingYi.getHzly());
			List<Date> jhsfDates = SuiFangUtils.getJhsfDate(fangAnDingYiItemVo, null, null, menZhenInfo.getMzTime());
			for (int k = 0; k < jhsfDates.size(); k++) {
				// 计划随访时间
				wenJuanShiLi.setJhsfDate(jhsfDates.get(k));
				wenJuanShiLiMapper.insert(wenJuanShiLi);
			}
		}
	}

	@Override
	public void deleteShiLiByPauseTime(Integer faType, Date pauseTime) {
		// FangAnDingYi fangAnDingYi = fangAnDingYiMapper.selectById(faId);
		// if (fangAnDingYi == null) {
		// logger.warn("创建方案实例警告，不存在的实例，方案ID：{}", faId);
		// return;
		// }
		// logger.debug("方案删除调查问卷实例，方案ID为{},方案名称为{}", faId,
		// fangAnDingYi.getName());
		// Integer disableAllWeiDiaoCha =
		// wenJuanShiLiMapper.disableAllWeiDiaoCha(faId);
		EntityWrapper<WenJuanShiLi> wrapper = new EntityWrapper<>();
		wrapper.where("fa_type={0}", faType).where("jhsf_date>{0}", pauseTime);
		Integer disableWenJuanShiLi = wenJuanShiLiMapper.delete(wrapper);
		logger.debug("作废的问卷实例{}条数据", disableWenJuanShiLi);
	}
    /**
	 *@author: Leslie
	 *@Date 2018/3/30 14:38
	 *@param: [pageInfo, user]
	 *@return com.wangzhixuan.commons.result.PageInfo
	 *@throws
	 *@Description: 三张表的遍历，如果没有相关必需字段，建议直接删除
	 */
	@Override
	public PageInfo selectDateGrid(PageInfo pageInfo, ShiroUser user) throws SysException {
		// // shiro中缓存的权限
		// Set<String> urlSet = user.getUrlSet();
		// shiro中缓存的用户最高角色
		SysRole role = user.getRole();
		Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		EntityWrapper<WenJuanShiLi> wenJuanShiliWrapper = new EntityWrapper<>();
		Map<String, Object> condition = pageInfo.getCondition();
		WenJuanShiLiDataGridQuery query = BeanUtils.mapToBean(condition, WenJuanShiLiDataGridQuery.class);
		Long userId = user.getId();
		SysUser userInfo = userService.selectById(userId);
		// 是否有管理员及以上角色 ,Seq越小，权限越大
		if (role.getSeq() > 1) {
			// 当前用户拥有的方案内容
			List<Long> faItemList = wenJuanShiLiMapper.queryItemIdByExeUserId(userId);
			if (faItemList != null && faItemList.size() > 0) {
				// 是否有全院数据权限
				if (!user.getQyDataAuth()) {
					// 是否有部门数据权限
					// 是否有个人数据权限
					if (user.getBmDataAuth() || user.getGrDataAuth()) {
						StringBuilder bmSb = new StringBuilder();
						List<Object> bmParams = new ArrayList<>();
						int pCount = 0;
						EntityWrapper<SysUserOrganization> wrapper = new EntityWrapper<>();
						wrapper.where("user_id={0}", user.getId());
						//该用户所在的科室
						List<Long> deptIdList = new ArrayList<>();
						List<SysUserOrganization> userOrganizations = userOrganizationService.selectList(wrapper);
						for (SysUserOrganization sysUserOrganization : userOrganizations) {
							deptIdList.add(sysUserOrganization.getOrganizationId());
						}
						if (deptIdList != null && deptIdList.size() > 0) {
							bmSb.append("(");
							for (int i = 0; i < deptIdList.size(); i++) {
								bmSb.append("dept_id ={");
								bmSb.append(pCount++);
								bmSb.append("}");
								bmSb.append(" OR ");
							}
							bmSb.setLength(bmSb.length() - 3);
							bmSb.append(")");
							bmParams.addAll(deptIdList);
						}
						if (faItemList != null && faItemList.size() > 0) {
							bmSb.append("AND (");
							for (int i = 0; i < faItemList.size(); i++) {
								bmSb.append("fa_item_id ={");
								bmSb.append(pCount++);
								bmSb.append("}");
								bmSb.append(" OR ");
							}
							bmSb.setLength(bmSb.length() - 3);
							bmSb.append(")");
							bmParams.addAll(faItemList);
						}
						// 根据当前用户的部门和拥有的方案item查询问卷示例
						wenJuanShiliWrapper.where(bmSb.toString(), bmParams.toArray());
						// 是否查看个人数据
						if (user.getGrDataAuth()) {
							if (userInfo.getUserType() == 2) {
								// 医生
								wenJuanShiliWrapper.where("doctor_id={0}", userInfo.getId());
							} else if (userInfo.getUserType() == 3) {
								// 护士
								wenJuanShiliWrapper.where("hs_id={0}", userInfo.getId());
							}
						}
					} else {
						throw new SysException("当前用户没有查看数据的权限");
					}
				} else {
					// 随访中心查看数据（指定方案）
					int qCount = 0;
					StringBuilder qySb = new StringBuilder();
					List<Object> qyParams = new ArrayList<>();
					if (faItemList != null && faItemList.size() > 0) {
						qySb.append("(");
						for (int i = 0; i < faItemList.size(); i++) {
							qySb.append("fa_item_id={");
							qySb.append(qCount++);
							qySb.append("}");
							qySb.append(" OR ");
						}
						qySb.setLength(qySb.length() - 3);
						qySb.append(")");
						qyParams.addAll(faItemList);
					}
					// 根据当前用户的部门和拥有的方案item查询问卷示例
					wenJuanShiliWrapper.where(qySb.toString(), qyParams.toArray());
				}
			} else {
				throw new SysException("当前用户暂无要执行的任务");
			}
		}
		// 患者姓名查找
		if (StringUtils.isNotBlank(query.getHzName())) {
			wenJuanShiliWrapper.where("hz_name={0}", query.getHzName());
		}
		if (StringUtils.isNotBlank(query.getStatus())) {
			wenJuanShiliWrapper.where("status={0}", query.getStatus());
		} else {
			wenJuanShiliWrapper.where("status <> {0}", "9");
		}
		if (query.getFaId() != null) {
			wenJuanShiliWrapper.where("fa_id={0}", query.getFaId());
		}
		if (query.getDeptId() != null) {
			wenJuanShiliWrapper.where("dept_id = {0}", query.getDeptId());
		}
		if(query.getDoctorName()!=null){
			wenJuanShiliWrapper.where("doctor_name ={0}",query.getDoctorName());
		}
		if(StringUtils.isNotBlank(query.getHsName())){
			wenJuanShiliWrapper.where("hs_name ={0}",query.getHsName());
		}
		if (StringUtils.isNotBlank(query.getWjzt())){
			wenJuanShiliWrapper.where("wjzt ={0}",query.getWjzt());
		}
		if(query.getHzly() != null){
			wenJuanShiliWrapper.where("hzly ={0}",query.getHzly());
		}
		// 随访类型
		wenJuanShiliWrapper.where("fa_type={0}", query.getFaType());
		// 如果timeType为"actual",则视为按照实际随访时间查找，如果为其他，则视为按照计划随访时间查找
		if ("actual".equals(query.getTimeType())) {
			if (query.getStaDate() != null) {
				wenJuanShiliWrapper.where("dc_time >= {0}", query.getStaDate());
			}
			if (query.getEndDate() != null) {
				wenJuanShiliWrapper.where("dc_time <= {0}", query.getEndDate());
			}
			wenJuanShiliWrapper.orderBy("dc_time", false);

		} else if ("plan".equals(query.getTimeType())) {
			if (query.getStaDate() != null) {
				wenJuanShiliWrapper.where("jhsf_date >= {0}", query.getStaDate());
			}
			if (query.getEndDate() != null) {
				wenJuanShiliWrapper.where("jhsf_date <= {0}", query.getEndDate());
			}
			wenJuanShiliWrapper.orderBy("jhsf_date", false);
		} else {
			if (query.getStaDate() != null) {
				wenJuanShiliWrapper.where("other_time >= {0}", query.getStaDate());
			}
			if (query.getEndDate() != null) {
				wenJuanShiliWrapper.where("other_time <= {0}", query.getEndDate());
			}
			wenJuanShiliWrapper.orderBy("other_time", false);
		}

		// 查询问卷实例（分页）
		selectMapsPage(page, wenJuanShiliWrapper);
		//单表分页查询完成
		List<Map<String, Object>> records = page.getRecords();
		List<PageSatisFaction> result=new ArrayList<>();
		if (records != null && records.size() >= 1) {
			for (int i = 0; i < records.size(); i++) {
				Map<String, Object> record = records.get(i);
				PageSatisFaction faction=BeanUtils.mapToBean(record,PageSatisFaction.class);
				result.add(faction);
				// 住院ID或门诊ID
				Object hzLsId = record.get("hzLsId");
				if (hzLsId != null) {
					if (query.getFaType() == 1) {
						// 住院信息（部分字段）
						ZhuYuanInfo zhuYuanInfo = zhuYuanInfoService.selectById(Long.parseLong(hzLsId.toString()));
						if (zhuYuanInfo != null) {
							faction.setZhuYuanInfo(zhuYuanInfo);
							//record.put("zyNo", zhuYuanInfo.getZyNo());
							// 查询诊断名称
							if (StringUtils.isBlank(zhuYuanInfo.getCyzd())) {
								logger.error("患者{}[住院号:{}]没有出院诊断", zhuYuanInfo.getHzName(), zhuYuanInfo.getZyNo());
							} else {
								Icd icd = icdService.selectById(zhuYuanInfo.getCyzd());
								if (icd != null) {
									faction.setCyzd(icd.getName());
								}
							}
						}
					} else if (query.getFaType() == 2) {
						// 门诊信息（部分字段）
						MenZhenInfo menZhenInfo = menZhenInfoService.selectById(hzLsId.toString());
						if (menZhenInfo != null) {
							faction.setMenZhenInfo(menZhenInfo);
							// 查询诊断名称
							if (StringUtils.isBlank(menZhenInfo.getDiagnose())) {
								logger.error("患者{}[门诊号:{}]没有出院诊断", menZhenInfo.getHzName(), menZhenInfo.getMzNo());
							} else {
								Icd icd = icdService.selectById(menZhenInfo.getDiagnose());
								if (icd != null) {
									faction.setCyzd(icd.getName());
									//record.put("diagnose", icd.getName());
								}
							}
						}
					}
				}
				// // 随访状态
				// Object status = record.get("status");
				// if (status != null) {
				// SysDictionaries dictionaries =
				// dictionariesService.getByCode(status.toString());
				// if (dictionaries != null) {
				// record.put("status", dictionaries.getName());
				// }
				// }
				// 添加执行人
				if (record.get("updateUserId")!= null&&StringUtils.isNotBlank(record.get("updateUserId").toString())) {
					SysUser exeUser = userService.selectUserById(record.get("updateUserId").toString());
					if (exeUser != null) {
						faction.setZxname(exeUser.getName());
						//record.put("zxname", exeUser.getName());
					}
				}
				// 查询患者标记
				Object hzIdObj = record.get("hzId");
				List<HuanZheTag> huanZheTags = huanZheTagMapper.listByHzId(Long.parseLong(hzIdObj.toString()));
				StringBuilder huanzheTagsSb = new StringBuilder();
				if (huanZheTags != null && huanZheTags.size() > 0) {
					for (int j = 0; j < huanZheTags.size(); j++) {
						huanzheTagsSb.append(huanZheTags.get(j).getTagName()).append(",");
					}
					huanzheTagsSb.setLength(huanzheTagsSb.length() - 1);
				}
				faction.setTagsName(huanzheTagsSb.toString());
				//record.put("tagsName", huanzheTagsSb.toString());
				// 查询通话记录
				Object wjslIdObj = record.get("id");
				long wjslId = Long.parseLong(wjslIdObj.toString());
				Phone phone = new Phone();
				phone.setSfId(wjslId);
				// phone.setHold(1);
				// phone.setType(0);
				List<Phone> phones = phoneService.findByCondition(phone);
				if (phone != null && phones.size() > 0) {
					//record.put("audios", phones);
					faction.setAudios(phones);
				}
			}
		}
		pageInfo.setRows(result);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	/**
	 *@author: Leslie
	 *@Date 2018/3/30 14:40
	 *@param: [id]
	 *@return com.wangzhixuan.model.vo.WenJuanShiLiVo
	 *@throws
	 *@Description: 根据问卷实例进行查询
	 */
	@Override
	public WenJuanShiLiVo queryDetailById(Long id) throws SysException {
		if (id == null) {
			throw new SysException("查询执行方案问卷详情，执行方案ID不能为空");
		}
		WenJuanShiLiVo vo = new WenJuanShiLiVo();
		WenJuanShiLi wenJuanShiLi = selectById(id);
		if (wenJuanShiLi == null) {
			throw new SysException("问卷实例（问卷执行）不存在");
		}
		BeanUtils.copy(wenJuanShiLi, vo);
		// 评价来源"1"(出院随访)
		List<PingJiaVo> pingJias = pingJiaService.selectByPjLyAndExtId("1", "" + id);
		vo.setPingJia(pingJias);
		List<WenJuanVo> wjList = new ArrayList<>();
		// 问卷实例绑定的多个问卷
		List<Map<String, Object>> wjMaps = fangAnDingYiItemMapper.selectWenJuans(wenJuanShiLi.getFaItemId());
		for (Map<String, Object> map : wjMaps) {
			Long wjId = Long.parseLong(map.get("id").toString());
			String wjzt = map.get("wjzt").toString();
			WenJuanVo wenJuanVo = getWenJuanVo(wjId, id, wjzt, null);
			wjList.add(wenJuanVo);
		}
		vo.setWenJuans(wjList);
		return vo;
	}

	@Override
	public WenJuanVo getWenJuanVo(Long wjId, Long wjslId, String wjzt, Long itemId) {
		WenJuanVo wenJuanVo = new WenJuanVo();
		wenJuanVo.setId(wjId);
		wenJuanVo.setWjzt(wjzt);
		List<TiMuVo> tiMus = new ArrayList<>();
		// wenJuanItems 一大堆题目
		List<WenJuanItem> wenJuanItems = wenJuanItemMapper.queryByWenJuanId(wjId);
		if (wenJuanItems != null && wenJuanItems.size() > 0) {
			for (int i = 0; i < wenJuanItems.size(); i++) {
				// wenJuanItem 一个题目
				WenJuanItem wenJuanItem = wenJuanItems.get(i);
				TiMuVo timuVo = new TiMuVo();
				timuVo.setId(wenJuanItem.getTmId());
				// 状态为正常的题目
				timuVo.setStatus("1");
				// 包含选项的题目
				timuVo = tiMuService.queryDetailById(timuVo);
				if (timuVo == null) {
					logger.warn("问卷{}id为{}发现异常题目题目ID为{}", wjzt, wjId, wenJuanItem.getTmId());
					continue;
				}
				// 题目的排序
				timuVo.setSeq(wenJuanItem.getSeq());
				// 填空题
				if ("3".equals(timuVo.getTxxz())) {
					TiMuShiLiVo tiMuShiLiVo = tiMuShiLiService.queryByWjslId(wjslId, itemId, timuVo.getId(), null);
					if (tiMuShiLiVo != null) {
						timuVo.setDtnr(tiMuShiLiVo.getDtnr());
					}
				} else {
					// 选择题
					List<TimuXuanXiang> tmxxs = timuVo.getTmxxs();
					for (int j = 0; j < tmxxs.size(); j++) {
						TimuXuanXiang timuXuanXiang = tmxxs.get(j);
						TiMuShiLiVo tiMuShiLiVo = tiMuShiLiService.queryByWjslId(wjslId, itemId, wenJuanItem.getTmId(),
								timuXuanXiang.getId());
						if (tiMuShiLiVo != null) {
							timuXuanXiang.setSfxz("Y");
							timuXuanXiang.setStnr(tiMuShiLiVo.getDtnr());
						} else {
							timuXuanXiang.setSfxz("N");
						}
					}
				}
				tiMus.add(timuVo);
			}
		}
		wenJuanVo.setTiMus(tiMus);
		return wenJuanVo;
	}

	/**
	 * @Author: Leslie
	 * @Description:随访执行模糊查询
	 * @Date 2017/8/19 16:59
	 */
	@Override
	public PageInfo findByCondition(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		if (pageInfo.getCondition() != null) {
			List<Map<String, Object>> list = wenJuanShiLiMapper.findByCondition(page, pageInfo.getCondition());
			if (list != null && list.size() > 0) {
				for (Map<String, Object> item : list) {
					List<HuanZheTag> tags = huanZheTagMapper.listByHzId(Long.parseLong(item.get("hzId").toString()));
					// 设置tagsName
					if (tags != null && tags.size() > 0) {
						StringBuilder tagsName = new StringBuilder();
						for (HuanZheTag tag : tags) {
							tagsName.append(tag.getTagName());
						}
						item.put("tagsName", tagsName.toString());
					}
					// 设置患者住院信息
					if (item.get("updateUserId") != null) {
						item.put("zxname",
								userService.selectById(Long.parseLong(item.get("updateUserId").toString())).getName());
					} else {
						if (item.get("createUserId") != null) {
							item.put("zxname", userService
									.selectById(Long.parseLong(item.get("createUserId").toString())).getName());
						}
					}
					// 设置出院科室名
					if (item.get("cyDeptId") != null) {
						SysOrganization sys = sysOrganizationMapper
								.selectById(Long.parseLong(item.get("cyDeptId").toString()));
						if (sys != null) {
							item.put("cyDeptName", sys.getName());
						}
					}
					// 查询通话记录
					try {
						Phone phone = new Phone();
						long id = Long.parseLong(item.get("id").toString());
						phone.setSfId(id);
						phone.setHold(1);
						phone.setType(0);
						List<Phone> phones = phoneService.findByCondition(phone);
						item.put("audios", phones);
					} catch (Exception e) {
						logger.error("查询通话记录异常", e);
					}
				}
			}
			pageInfo.setRows(list);
			pageInfo.setTotal(page.getTotal());
			return pageInfo;
		} else {
			throw new SysException("未进行传值");
		}

	}

	@Override
	public RespResult<Integer> plan(Map<String, Object> map, ShiroUser user) {
		if (user == null || user.getId() == null) {
			throw new SysException("登录信息失效");
		}
		RespResult<Integer> result = new RespResult<>();
		map.put("doctorId", user.getId());
		map.put("staTime", DateUtils.getDayBegin());
		map.put("endTime", DateUtils.getDayEnd());
		result.getSuccess(wenJuanShiLiMapper.plan(map));
		return result;
	}

	@Override
	public RespResult<Integer> already(Map<String, Object> map, ShiroUser user) {
		if (user == null || user.getId() == null) {
			throw new SysException("登录信息失效");
		}
		RespResult<Integer> result = new RespResult<>();
		map.put("updateUserId", user.getId());
		map.put("staTime", DateUtils.getDayBegin());
		map.put("endTime", DateUtils.getDayEnd());
		result.getSuccess(wenJuanShiLiMapper.already(map));
		return result;
	}

	@Override
	public WenJuanShiLi selectOneAfterPauseByOtherTimeDesc(Integer faType, Date pauseTime) {
		WenJuanShiLi wenJuanShiLi = wenJuanShiLiMapper.selectOneByPauseTime(faType, pauseTime);
		return wenJuanShiLi;
	}

	@Override
	public SatisfactionVo getSatisfaction(Map<String, Object> map) {
		if (map == null || map.get("wjslId") == null || map.get("wjslId") == "") {
			throw new SysException("无问卷实例id");
		}
		Long wjslId = Long.parseLong(map.get("wjslId").toString());
		SatisfactionVo satisfactionVo = new SatisfactionVo();
		WenJuanShiLi wenJuanShiLi = selectById(wjslId);
		if (wenJuanShiLi != null) {
			BeanUtils.copyProperties(wenJuanShiLi, satisfactionVo);
			FangAnDingYi fangAnDingYi = fangAnDingYiMapper.selectById(wenJuanShiLi.getFaId());
			satisfactionVo.setFaName(fangAnDingYi == null ? "未知方案" : fangAnDingYi.getName());
		}
		List<WenJuanShiLiItem> wenJuanItems = wenJuanShiLiItemService.listWenjuans(wjslId);
		satisfactionVo.setItemVos(wenJuanItems);
		List<PingJiaVo> pingJias = pingJiaService.selectByPjLyAndExtId("1", wjslId.toString());
		satisfactionVo.setPingJias(pingJias);
		Phone phone = new Phone();
		phone.setSfId(wjslId);
		List<Phone> phones = phoneService.findByCondition(phone);
		satisfactionVo.setPhoneVos(phones);
		return satisfactionVo;
	}

	@Override
	public RespResult<Map<String, Object>> getCountByDate(Map<String, Object> map) {
		if (map == null) {
			throw new SysException("传值为空");
		}
		if (map.get("timeType") == null || map.get("timeType") == "") {
			throw new SysException("时间类型不为空");
		}
		if (map.get("type") == null || map.get("type") == "") {
			throw new SysException("随访类型不为空");
		}
		RespResult<Map<String, Object>> result = new RespResult<>();
		String timeType = map.get("timeType").toString();
		String type = map.get("type").toString();
		Date staTime;
		Date endTime;
		String state = "";
		/* 根据时间类型赋初值 */
		if ("week".equals(timeType)) {
			staTime = DateUtils.getBeginDayOfWeek();
			endTime = DateUtils.getEndDayOfWeek();
		} else if ("month".equals(timeType)) {
			staTime = DateUtils.getBeginDayOfMonth();
			endTime = DateUtils.getEndDayOfMonth();
		} else {
			staTime = DateUtils.getDayBegin();
			endTime = DateUtils.getDayEnd();
		}
		/* 根据类型赋值状态或者时间 */
		if ("already".equals(type)) {
			state = "2";
		} else if ("wait".equals(type)) {
			staTime = new Date();
			state = "1";
		} else if ("outOfDate".equals(type)) {
			state = "1";
			endTime = new Date();
		} else {
			state = "";
		}
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("staTime", staTime);
		conditionMap.put("endTime", endTime);
		conditionMap.put("state", state);
		Integer count = wenJuanShiLiMapper.homeCount(conditionMap);
		conditionMap.put("count", count);
		result.getSuccess(conditionMap);
		return result;
	}

	@Override
	public void deleteSHiLiByFaId(Long id) {
		if (id==null){
			throw new SysException("方案ID不能为空");
		}
		wenJuanShiLiMapper.deleteAllShiLi(id);
	}
}
