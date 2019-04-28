package com.wangzhixuan.service.fa.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.vo.fa.HuanZhePiPeiBo;
import com.wangzhixuan.service.ISysOrganizationService;
import com.wangzhixuan.service.ISysUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.mapper.fa.FangAnDingYiItemMapper;
import com.wangzhixuan.mapper.fa.FangAnDingYiMapper;
import com.wangzhixuan.model.fa.FangAnDingYi;
import com.wangzhixuan.model.vo.fa.FangAnDingYiItemVo;
import com.wangzhixuan.model.vo.fa.FangAnDingYiVo;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;
import com.wangzhixuan.service.fa.IFangAnDingYiService;
import com.wangzhixuan.service.fa.IFangAnMenZhenGroupService;
import com.wangzhixuan.service.fa.IFangAnZhuYuanGroupService;
import com.wangzhixuan.service.sms.ISmsSendService;
import com.wangzhixuan.service.wenjuan.IWenJuanShiLiService;

@Service
public class FangAnDingYiServiceImpl extends ServiceImpl<FangAnDingYiMapper, FangAnDingYi>
		implements IFangAnDingYiService {
	private static final Logger logger = LoggerFactory.getLogger(FangAnDingYiServiceImpl.class);
	@Autowired
	private FangAnDingYiMapper faAnDingYiMapper;
	@Autowired
	private FangAnDingYiItemMapper fangAnDingYiItemMapper;
	@Autowired
	private IFangAnZhuYuanGroupService fangAnZhuYuanGroupService;
	@Autowired
	private IFangAnMenZhenGroupService fangAnMenZhenGroupService;
	@Autowired
	private IWenJuanShiLiService wenJuanShiLiService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysOrganizationService organizationService;
	@Autowired
	private ISmsSendService smsSendService;

	@Override
	public void saveOrUpdate(FangAnDingYiVo vo, ShiroUser user) throws SysException {
		/*
		 * 方案
		 */
		if (StringUtils.isBlank(vo.getName())) {
			throw new SysException("方案名称不能为空");
		}
		if (vo.getId() == null) {
			// 新增
			vo.setStatue("0");//0：禁用，1：启用
			vo.setCreateTime(new Date());
			vo.setCreateUserId(user.getId());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			faAnDingYiMapper.insert(vo);
		} else {
			// 更新
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			//返回受影响行数
			Integer count = faAnDingYiMapper.updateById(vo);
			if (count < 1) {
				throw new SysException("方案不存在，不可更新");
			}
		}
		/*
		 * 方案item
		 */
		List<FangAnDingYiItemVo> items = vo.getItems();
		if (items == null || items.size() < 1) {
			throw new SysException("至少要有一条方案内容");
		}
		List<Long> faItemIds = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			FangAnDingYiItemVo fangAnDingYiItemVo = items.get(i);
			fangAnDingYiItemVo.setFaId(vo.getId());
			if (StringUtils.isBlank(fangAnDingYiItemVo.getZxsjLx())) {
				throw new SysException("方案内容" + (i + 1) + "执行时间类型不能为空");
			}
			if (fangAnDingYiItemVo.getGjtzx() == null) {
				throw new SysException("方案内容" + (i + 1) + "执行时间首次间隔天数不能为空");
			}
			// if (fangAnDingYiItemVo.getWjId() == null) {
			// throw new SysException("方案内容" + (i + 1) + "随访问卷不能为空");
			// }
			// 电话随访时选择发送时间和短信内容
			if ("2".equals(fangAnDingYiItemVo.getSfType())) {
				if (StringUtils.isBlank(fangAnDingYiItemVo.getSmsSendTime())) {
					throw new SysException("短信定时发送时间不能为空");
				}
				if (StringUtils.isBlank(fangAnDingYiItemVo.getSmsContent())) {
					throw new SysException("短信发送内容不能为空");
				}
			}
			if (fangAnDingYiItemVo.getId() == null) {
				// 添加（表：hzsf_fa_dy_item）
				fangAnDingYiItemVo.setCreateTime(new Date());
				fangAnDingYiItemVo.setCreateUserId(user.getId());
				fangAnDingYiItemVo.setUpdateTime(new Date());
				fangAnDingYiItemVo.setUpdateUserId(user.getId());
				fangAnDingYiItemMapper.insert(fangAnDingYiItemVo);
			} else {
				// 更新（表：hzsf_fa_dy_item）
				fangAnDingYiItemVo.setUpdateTime(new Date());
				fangAnDingYiItemVo.setUpdateUserId(user.getId());
				fangAnDingYiItemMapper.updateById(fangAnDingYiItemVo);
			}
			faItemIds.add(fangAnDingYiItemVo.getId());
			// 电话随访时选择问卷和执行人
			if ("1".equals(fangAnDingYiItemVo.getSfType())) {
				List<Map<String, Object>> wenJuans = fangAnDingYiItemVo.getWenJuans();
				logger.debug("wenJuans{}", JSONArray.fromObject(wenJuans).toString());
				if (wenJuans != null && wenJuans.size() > 0) {
					// 刪除之前的方案内容中问卷（表：hzsf_fa_dy_item_wj）//TODO 是否刪除问卷
					fangAnDingYiItemMapper.deleteWenJuanByItemId(fangAnDingYiItemVo.getId());
					for (int j = 0; j < wenJuans.size(); j++) {
						Map<String, Object> map22 = wenJuans.get(j);
						Object id = map22.get("id");
						logger.debug("一个问卷{}----{}", JSONObject.fromObject(map22).toString(), id);
						Long wjId = com.wangzhixuan.commons.utils.StringUtils.getLong(id);
						if (wjId == null) {
							throw new SysException("选择了一个空问卷");
						}
						// 插入新的方案内容中问卷（表：hzsf_fa_dy_item_wj）
						fangAnDingYiItemMapper.insertWenJuan(fangAnDingYiItemVo.getId(), wjId);
					}
				} else {
					throw new SysException("请选择随访问卷");
				}
				List<Map<String, Object>> exeUsers = fangAnDingYiItemVo.getExeUsers();
				logger.debug("exeUsers{}", JSONArray.fromObject(exeUsers).toString());
				if (exeUsers != null && exeUsers.size() > 0) {
					// 刪除之前的方案内容中执行人（表：fa_dy_item_exe）
					fangAnDingYiItemMapper.deleteExeUserByItemId(fangAnDingYiItemVo.getId());
					for (int k = 0; k < exeUsers.size(); k++) {
						Map<String, Object> map22 = exeUsers.get(k);
						Object id = map22.get("id");
						logger.debug("一个执行人{}----{}", JSONObject.fromObject(map22).toString(), id);
						Long exeUserId = com.wangzhixuan.commons.utils.StringUtils.getLong(id);
						if (exeUserId == null) {
							throw new SysException("选择了一个空执行人");
						}
						// 插入新的方案内容中执行人（表：fa_dy_item_exe）
						fangAnDingYiItemMapper.insertExeUsers(fangAnDingYiItemVo.getId(), exeUserId);
					}
				} else {
					throw new SysException("请选择执行人");
				}
			}
		}
		if (vo.getId() != null) {
			// 刪除之前的方案item（表：hzsf_fa_dy_item）
			fangAnDingYiItemMapper.deleteByFaIdAndIdNotIn(vo.getId(), faItemIds);
		}
		if (vo.getHzly() == 1) {
			// 住院群组条件
			FangAnZhuYuanGroupVo zygroup = vo.getZygroup();
			if (zygroup.validate().isValidate()) {
				zygroup.setFaId(vo.getId());
				fangAnZhuYuanGroupService.saveOrUpdate(zygroup);
			}
		} else if (vo.getHzly() == 2) {
			// 门诊群组条件
			FangAnMenZhenGroupVo mzgroup = vo.getMzgroup();
			mzgroup.setFaId(vo.getId());
			fangAnMenZhenGroupService.saveOrUpdate(mzgroup);
		} else {
			throw new SysException("未知患者来源");
		}

	}

	@Override
	public List<FangAnDingYiVo> selectDataGrid(Map<String,Object> map) throws SysException {
		if (map==null||map.get("faType")==null||StringUtils.isBlank(map.get("faType").toString())){
			throw new SysException("方案类型不能为空");
		}
		Integer faType=Integer.parseInt(map.get("faType").toString());
		EntityWrapper<FangAnDingYi> wrapper = new EntityWrapper<>();
		wrapper.where("fa_type={0}", faType).orderBy("update_time", false);
		List<FangAnDingYi> all = faAnDingYiMapper.selectList(wrapper);
		List<FangAnDingYiVo> result = new ArrayList<FangAnDingYiVo>();
		for (int i = 0; i < all.size(); i++) {
			FangAnDingYiVo vo = new FangAnDingYiVo();
			BeanUtils.copy(all.get(i), vo);
			//此方案一共有多少个item
			List<FangAnDingYiItemVo> fangAnDingYiItems = fangAnDingYiItemMapper.findByFangAnId(vo.getId());
			for (int j = 0; j < fangAnDingYiItems.size(); j++) {
				FangAnDingYiItemVo fangAnDingYiItemVo = fangAnDingYiItems.get(j);
				//设置问卷名
				if (fangAnDingYiItemVo != null) {
					List<Map<String, Object>> wjList = fangAnDingYiItemMapper
							.selectWenJuans(fangAnDingYiItemVo.getId());
					fangAnDingYiItemVo.setWenJuans(wjList);
				}
				// 总次数
				Long zts = fangAnDingYiItemVo.getZts();
				if (zts != null) {
					if (zts > 5) {
						zts = 5L;
					}
					Integer gjtzx = fangAnDingYiItemVo.getGjtzx();// 隔几天执行
					Integer zxjg = fangAnDingYiItemVo.getZxjg();// 执行间隔
					for (int k = 1; k < zts; k++) {
						gjtzx += zxjg;
						fangAnDingYiItemVo.setGjtzx(gjtzx);
						//大循环来自于fangAnDingYiItemVo ，add间接性增加了size
						/*fangAnDingYiItems.add(fangAnDingYiItemVo);*/
					}
				}
			}
			//分页条件，根本无须此字段
			vo.setUserName(sysUserService.selectById(vo.getCreateUserId()).getName());
			vo.setItems(fangAnDingYiItems);
			result.add(vo);
		}
		return result;
	}

	@Override
	public void enableFangAn(Long id, boolean enable) throws SysException {
		String functionStr = enable ? "启用" : "禁用";
		if (id == null) {
			throw new SysException(functionStr + "方案ID不能为空");
		}
		FangAnDingYi fangAnDingYi = selectById(id);
		if (fangAnDingYi == null) {
			throw new SysException(functionStr + "方案不能为空");
		}
		if (enable) {
			if ("1".equals(fangAnDingYi.getStatue())) {
				throw new SysException("方案已启用，无需再启用");
			}
		} else {
			if ("0".equals(fangAnDingYi.getStatue())) {
				throw new SysException("方案已禁用，无需再禁用");
			}
		}
		FangAnZhuYuanGroupVo zhuYuanGroupVo = null;
		FangAnMenZhenGroupVo menZhenGroupVo = null;
		if (fangAnDingYi.getHzly() == 1) {
			// 住院群组条件
			zhuYuanGroupVo = fangAnZhuYuanGroupService.findByFangAnId(id);
			if (zhuYuanGroupVo == null) {
				logger.debug("方案{},{}不存在住院患者群组，无需{}", id, fangAnDingYi.getName(), functionStr);
				return;
			}
		} else if (fangAnDingYi.getHzly() == 2) {
			// 门诊群组条件
			menZhenGroupVo = fangAnMenZhenGroupService.findByFangAnId(id);
			if (menZhenGroupVo == null) {
				logger.debug("方案{},{}不存在门诊患者群组，无需{}", id, fangAnDingYi.getName(), functionStr);
				return;
			}
		}
		// 方案内容item
		List<FangAnDingYiItemVo> fangAnDingYiItemVos = fangAnDingYiItemMapper.findByFangAnId(id);
		if (fangAnDingYiItemVos == null || fangAnDingYiItemVos.size() < 1) {
			logger.debug("方案{},{}不存在方案内容，无需{}", id, fangAnDingYi.getName(), functionStr);
			return;
		}
		/*
		 * 按照方案中的内容,删除计划任务和待发送短信(禁用)
		 */
		if ("1".equals(fangAnDingYi.getStatue()) && !enable) {
			// 暂停时间
			Date pauseTime = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd 00:00:00"));
			Date smsPauseTime = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			for (FangAnDingYiItemVo fangAnDingYiItemVo : fangAnDingYiItemVos) {
				if ("1".equals(fangAnDingYiItemVo.getSfType())) {
					/*
					 * （电话随访）启用创建方案实例
					 */
					// WenJuanShiLi beforeWjsl =
					// wenJuanShiLiService.selectOneByPauseTimeDesc(fangAnDingYi.getFaType(),
					// pauseTime);
					// // 暂停之前是否有问卷实例
					// if (beforeWjsl != null) {
					// //
					// 更新群组条件-最小筛选时间（取小于或等于今日计划问卷示例中，出院时间最晚的一条记录的出院时间，并为该时间加1天）
					// Date zxsxsj =
					// org.apache.commons.lang.time.DateUtils.addDays(pauseTime,
					// 1);
					// if (fangAnDingYi.getHzly() == 1) {
					// zhuYuanGroupVo.setZxsxsj(zxsxsj);
					// fangAnZhuYuanGroupService.updateById(zhuYuanGroupVo);
					// } else if (fangAnDingYi.getHzly() == 2) {
					// menZhenGroupVo.setDateMin(zxsxsj);
					// fangAnMenZhenGroupService.updateById(menZhenGroupVo);
					// }
					// } else {
					// // 暂停之后是否有问卷实例
					// WenJuanShiLi afterWjsl =
					// wenJuanShiLiService.selectOneByPauseTimeAsc(fangAnDingYi.getFaType(),
					// pauseTime);
					// if (afterWjsl != null) {
					// // 更新群组条件-最小筛选时间（取暂停问卷的出院时间）
					// if (fangAnDingYi.getHzly() == 1) {
					// zhuYuanGroupVo.setZxsxsj(afterWjsl.getOtherTime());
					// fangAnZhuYuanGroupService.updateById(zhuYuanGroupVo);
					// } else if (fangAnDingYi.getHzly() == 2) {
					// menZhenGroupVo.setDateMin(afterWjsl.getOtherTime());
					// fangAnMenZhenGroupService.updateById(menZhenGroupVo);
					// }
					// }
					// }
					// 暂停之后是否有问卷实例
					WenJuanShiLi afterWjsl = wenJuanShiLiService
							.selectOneAfterPauseByOtherTimeDesc(fangAnDingYi.getFaType(), pauseTime);
					if (afterWjsl != null) {
						// 更新群组条件-最小筛选时间（取暂停问卷的出院时间）
						if (fangAnDingYi.getHzly() == 1) {
							zhuYuanGroupVo.setZxsxsj(afterWjsl.getOtherTime());
							fangAnZhuYuanGroupService.updateById(zhuYuanGroupVo);
						} else if (fangAnDingYi.getHzly() == 2) {
							menZhenGroupVo.setDateMin(afterWjsl.getOtherTime());
							fangAnMenZhenGroupService.updateById(menZhenGroupVo);
						}
						// 删除暂停当天之后的计划任务
						wenJuanShiLiService.deleteShiLiByPauseTime(fangAnDingYi.getFaType(), pauseTime);
					}
				} else if ("2".equals(fangAnDingYiItemVo.getSfType())) {
					/*
					 * （短信随访）删除暂停当天之后的计划短信
					 */
					smsSendService.delPlannedSmsByPauseTime(smsPauseTime);
				}
			}
			fangAnDingYi.setStatue("0");
		} else if ("0".equals(fangAnDingYi.getStatue()) && enable) {
			/*
			 * 按照方案中的内容,创建任务和生成待发送短信(启用)
			 */
			for (FangAnDingYiItemVo fangAnDingYiItemVo : fangAnDingYiItemVos) {
				if ("1".equals(fangAnDingYiItemVo.getSfType())) {
					/*
					 * （电话随访）启用创建方案实例
					 */
					wenJuanShiLiService.creteShiLiByFangAn(id, fangAnDingYiItemVo, null);
				} else if ("2".equals(fangAnDingYiItemVo.getSfType())) {
					try {
						{
							/*
							 * （短信随访）生成计划短信
							 */
							smsSendService.generatePlannedMsg(null, fangAnDingYiItemVo, null);
						}
					} catch (Exception e) {
						throw new SysException(e.getMessage());
					}
				}
			}
			fangAnDingYi.setStatue("1");
		} else {
			return;
		}
		updateById(fangAnDingYi);
	}

	@Override
	public void delete(Long id) throws SysException {
		FangAnDingYi fangAnDingYi = selectById(id);
		if ("1".equals(fangAnDingYi.getStatue())) {
			throw new SysException("【" + fangAnDingYi.getName() + "】方案正在使用中，不可删除");
		}
		//删除方案，是否是要删除该方案的问卷实例，或者说是否要将该方案的所有数据相关删除
		//修改方案的状态
		fangAnDingYi.setStatue("9");
		updateById(fangAnDingYi);
		//删除所有的未做的问卷实例?
		wenJuanShiLiService.deleteSHiLiByFaId(id);
	}


	@Override
	public FangAnDingYiVo queryById(Long id) throws SysException {
		FangAnDingYi fangAnDingYi = selectById(id);
		FangAnDingYiVo vo = new FangAnDingYiVo();
		BeanUtils.copy(fangAnDingYi, vo);
		List<FangAnDingYiItemVo> fangAnDingYiItems = fangAnDingYiItemMapper.findByFangAnId(vo.getId());
		for (int j = 0; j < fangAnDingYiItems.size(); j++) {
			FangAnDingYiItemVo fangAnDingYiItemVo = fangAnDingYiItems.get(j);
			if (fangAnDingYiItemVo != null) {
				List<Map<String, Object>> wjList = fangAnDingYiItemMapper.selectWenJuans(fangAnDingYiItemVo.getId());
				fangAnDingYiItemVo.setWenJuans(wjList);
			}
			if (fangAnDingYiItemVo != null) {
				List<Map<String, Object>> maps = fangAnDingYiItemMapper.selectExeUsers(fangAnDingYiItemVo.getId());
				fangAnDingYiItemVo.setExeUsers(maps);
			}
		}
		vo.setItems(fangAnDingYiItems);
		if (vo.getHzly() == 1) {
			// 住院
			FangAnZhuYuanGroupVo fangAnZhuYuanGroupVo = fangAnZhuYuanGroupService.findByFangAnId(vo.getId());
			vo.setZygroup(fangAnZhuYuanGroupVo);
		} else if (vo.getHzly() == 2) {
			// 门诊
			FangAnMenZhenGroupVo fangAnMenZhenGroupVo = fangAnMenZhenGroupService.findByFangAnId(vo.getId());
			vo.setMzgroup(fangAnMenZhenGroupVo);
		} else {
			throw new SysException("患者来源未知");
		}
		return vo;
	}

	@Override
	public void taskHuanZhePiPeiFangan(HuanZhePiPeiBo bo) throws SysException {
		logger.debug("导入患者，匹配方案");
		EntityWrapper<FangAnDingYi> wrapper = new EntityWrapper<>();
		wrapper.where("statue={0}", "1");
		List<FangAnDingYi> fangAnDingYis = selectList(wrapper);
		if (fangAnDingYis == null || fangAnDingYis.size() < 1) {
			logger.debug("当前没有启动的方案，不进行匹配");
			return;
		}
		logger.debug("当前一共有{}个方案启用，进行匹配");
		for (int i = 0; i < fangAnDingYis.size(); i++) {
			FangAnDingYi fangAnDingYi = fangAnDingYis.get(i);
			Long faId = fangAnDingYi.getId();
			FangAnZhuYuanGroupVo zyGroup = fangAnZhuYuanGroupService.findByFangAnId(faId);
			if (zyGroup == null) {
				logger.debug("第{}个方案没有住院患者来源，不匹配", (i + 1));
				return;
			}
			// 方案内容item
			List<FangAnDingYiItemVo> fangAnDingYiItemVos = fangAnDingYiItemMapper.findByFangAnId(faId);
			if (fangAnDingYiItemVos == null || fangAnDingYiItemVos.size() < 1) {
				logger.debug("方案{},{}不存在方案内容，不会生成问卷实例", faId, fangAnDingYi.getName());
				return;
			}
			for (FangAnDingYiItemVo fangAnDingYiItemVo : fangAnDingYiItemVos) {
				if ("1".equals(fangAnDingYiItemVo.getSfType())) {
					// 电话随访 //TODO
					// 启用创建方案实例
					wenJuanShiLiService.creteShiLiByFangAn(fangAnDingYi.getId(), fangAnDingYiItemVo,
							bo.getZhuYuanInfoIds());
				} else if ("2".equals(fangAnDingYiItemVo.getSfType())) {
					// 短信随访 //TODO
					// 生成计划短信
					try {
						smsSendService.generatePlannedMsg(null, fangAnDingYiItemVo, bo.getZhuYuanInfoIds());
					} catch (Exception e) {
						logger.error("生成计划短信出错,报错：", e.getMessage());
					}

				}
			}
		}
	}

	@Override
	public List<FangAnDingYi> listStartedFa(Map<String, Object> map) {
		// 还是定义到vo里面
		if (map.get(FangAnDingYiVo.FATYPE) == null || map.get(FangAnDingYiVo.FATYPE) == "") {
			throw new SysException("方案类型为空");
		}
		String faType = map.get(FangAnDingYiVo.FATYPE).toString();
		Wrapper<FangAnDingYi> wrapper = new EntityWrapper<>();
		wrapper.where("fa_type={0}", faType);
		// 状态为1 启用 0 禁用
		wrapper.where("statue={0}", 1);
		return selectList(wrapper);
	}

	@Override
	public List<SysOrganization> listOrgsByFaId(Long faId) {
		if (faId == null) {
			throw new SysException("方案id为空");
		}
		List<SysOrganization> organizations = new ArrayList<>();
		String deptIds = faAnDingYiMapper.findDeptIdsByFaId(faId);
		// 对deptIds 判断
		if (StringUtils.isBlank(deptIds)) {
			organizations = organizationService.getDepts(null, "2");
			return organizations;
		} else {
			String[] ids = deptIds.split(",");
			if (ids != null && ids.length != 0) {
				for (String item : ids) {
					SysOrganization organization = organizationService.selectById(item);
					if (organization != null) {
						organizations.add(organization);
					}
				}
			}
			return organizations;
		}
	}

}
