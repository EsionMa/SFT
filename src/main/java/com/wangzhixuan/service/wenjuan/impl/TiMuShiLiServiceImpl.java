package com.wangzhixuan.service.wenjuan.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.wenjuan.TiMuShiLiMapper;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.TiMuShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.TiMu;
import com.wangzhixuan.model.wenjuan.TiMuShiLi;
import com.wangzhixuan.service.tj.WenJuanTongJiBo;
import com.wangzhixuan.service.wenjuan.ITiMuService;
import com.wangzhixuan.service.wenjuan.ITiMuShiLiService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TiMuShiLiServiceImpl extends ServiceImpl<TiMuShiLiMapper, TiMuShiLi> implements ITiMuShiLiService {
	private static final Logger log = LoggerFactory.getLogger(TiMuShiLiServiceImpl.class);
	@Autowired
	private TiMuShiLiMapper tiMuShiLiMapper;
	@Autowired
	private ITiMuService tiMuService;

	@Override
	public void saveOrUpdate(TiMuShiLiVo vo, ShiroUser user) throws SysException {
		if (vo.getTmId() == null) {
			throw new SysException("题目ID不能为空");
		}
		TiMu tiMu = tiMuService.selectById(vo.getTmId());
		if (tiMu == null) {
			throw new SysException("不存在的题目id为" + vo.getTmId() + "的题目，请联系管理员");
		}
		String txxz = tiMu.getTxxz();// 题型选择（1单选，2多选，3填空）
		if (StringUtils.isBlank(txxz)) {
			throw new SysException("未知题型，请联系管理员");
		}
		if (("1".equals(tiMu.getSfbt()) && "1".equals(txxz) || "2".equals(txxz))) {
			if (vo.getXxId() == null && StringUtils.isBlank(vo.getXxIds())) {
				throw new SysException("题目ID为" + tiMu.getId() + "题目标题为" + tiMu.getBt() + "的题目为必填题目，请确认后提交");
			}
		}
		if ("1".equals(tiMu.getSfbt()) && "3".equals(txxz)) {
			if (StringUtils.isBlank(vo.getDtnr())) {
				throw new SysException(tiMu.getBt() + "为必填题目，请确认后提交");
			}
		}
		TiMuShiLi tiMuShiLi = BeanUtils.copy(vo, TiMuShiLi.class);
		Date curDate = new Date();
		tiMuShiLi.setTmBt(tiMu.getBt());
		tiMuShiLi.setUpdateTime(curDate);
		tiMuShiLi.setTmFl(tiMu.getTmfl());
		tiMuShiLi.setTxxz(tiMu.getTxxz());
		if (StringUtils.isNotBlank(vo.getXxIds())) {
			String xxIds = vo.getXxIds();
			String[] xxIdsSplit = xxIds.split(",");
			for (int i = 0; i < xxIdsSplit.length; i++) {
				Long xxidLong = com.wangzhixuan.commons.utils.StringUtils.getLong(xxIdsSplit[i]);
				if (xxidLong != null) {
					tiMuShiLi.setXxId(xxidLong);
				}
				tiMuShiLi.setCreateTime(curDate);
				if (user != null) {
					tiMuShiLi.setCreateUserId(user.getId());
				}
				tiMuShiLiMapper.insert(tiMuShiLi);
			}
		} else {
			tiMuShiLi.setCreateTime(curDate);
			if (user != null) {
				tiMuShiLi.setCreateUserId(user.getId());
			}
			tiMuShiLiMapper.insert(tiMuShiLi);
		}
	}

	@Override
	public void saveOrUpdate(List<TiMuShiLiVo> vo, Long wjslId, Long wjId, Date time,Long deptId,Long doctorId, ShiroUser user,
			Long itemId) throws SysException {
		if (vo == null || vo.size() < 1) {
			return;
		}
		for (int i = 0; i < vo.size(); i++) {
			TiMuShiLiVo tiMuShiLiVo = vo.get(i);
			if (tiMuShiLiVo == null) {
				continue;
			}
			tiMuShiLiVo.setWenjuanItemId(itemId);
			tiMuShiLiVo.setWjSlId(wjslId);
			tiMuShiLiVo.setWjId(wjId);
			//出院时间，科室id，医生id，根据门诊和住院信息的不同进行相应保存
			tiMuShiLiVo.setCyTime(time);
			tiMuShiLiVo.setDeptId(deptId);
			tiMuShiLiVo.setDoctorId(doctorId);
			saveOrUpdate(tiMuShiLiVo, user);
		}
	}

	@Override
	public TiMuShiLiVo queryByWjslId(Long wjslId, Long itemId, Long tmId, Long xxId) throws SysException {
		EntityWrapper<TiMuShiLi> wrapper = new EntityWrapper<>();
		if (wjslId != null) {
			wrapper.where("wj_sl_id={0}", wjslId);
		} else {
			wrapper.where("wenjuan_item_id={0}", itemId);
		}

		wrapper.where("tm_id={0}", tmId);
		if (xxId != null) {
			wrapper.where("xx_id={0}", xxId);
		}

		List<TiMuShiLi> tiMuShiLis = selectList(wrapper);
		if (tiMuShiLis == null || tiMuShiLis.size() < 1) {
			return null;
		}
		TiMuShiLiVo vo = BeanUtils.copy(tiMuShiLis.get(0), TiMuShiLiVo.class);
		vo.setXxIds(vo.getXxId() == null ? "" : vo.getXxId().toString());
		if (tiMuShiLis.size() == 1) {
			return vo;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tiMuShiLis.size(); i++) {
			TiMuShiLi tiMuShiLi = tiMuShiLis.get(i);
			if (tiMuShiLi.getXxId() != null) {
				sb.append(tiMuShiLi.getXxId()).append(",");
			}
		}
		sb.setLength(sb.length() > 0 ? sb.length() - 1 : 0);
		vo.setXxIds(sb.toString());
		return vo;
	}

	@Override
	public boolean deleteBySlId(Long slId) throws SysException {
		EntityWrapper<TiMuShiLi> wrapper = new EntityWrapper<>();
		wrapper.where("wj_sl_id={0}", slId);
		return delete(wrapper);
	}

	@Override
	public int countWjIdAndTmId(Long wjId, Long id) throws SysException {
		EntityWrapper<TiMuShiLi> wrapper = new EntityWrapper<>();
		wrapper.where("wj_id={0}", wjId);
		if (id != null) {
			wrapper.where("wenjuan_item_id={0}", id);
		}
		return selectCount(wrapper);
	}

	@Override
	public int countWjIdAndTmIdAndXxId(Long wjId, Long tmId, Long xxId, WenJuanTongJiBo bo) throws SysException {
		EntityWrapper<TiMuShiLi> wrapper = new EntityWrapper<>();
		wrapper.where("wj_id={0}", wjId);
		wrapper.where("tm_id={0}", tmId);
		wrapper.where("xx_id={0}", xxId);
		String timeType = bo.getDateType();
		Date b = bo.getDateSt();
		Date e = bo.getDateEd();
		if (StringUtils.isNotBlank(timeType)) {
			String type = "update_time";
			if ("2".equals(timeType)) {
				type = "cy_time";
			}
			if (b != null) {
				wrapper.where(type + ">={0}", b);
			}
			if (e != null) {
				wrapper.where(type + "<={0}", e);
			}
		}
		if (bo.getDeptId() != null) {
			wrapper.where("doctor_id={0}", bo.getDoctorId());
		}
		if (bo.getDeptId() != null) {
			wrapper.where("dept_id={0}", bo.getDeptId());
		}
		return selectCount(wrapper);
	}

	@Override
	public List<TiMuShiLi> queryByWjIdAndTmId(Long wjId, Long tmId, WenJuanTongJiBo bo) throws SysException {
		EntityWrapper<TiMuShiLi> wrapper = new EntityWrapper<>();
		wrapper.where("wj_id={0}", wjId);
		wrapper.where("tm_id={0}", tmId);
		String timeType = bo.getDateType();
		Date b = bo.getDateSt();
		Date e = bo.getDateEd();
		if (StringUtils.isNotBlank(timeType)) {
			String type = "update_time";
			if ("2".equals(timeType)) {
				type = "cy_time";
			}
			if (b != null) {
				wrapper.where(type + ">={0}", b);
			}
			if (e != null) {
				wrapper.where(type + "<={0}", e);
			}
		}
		if (bo.getDeptId() != null) {
			wrapper.where("doctor_id={0}", bo.getDoctorId());
		}
		if (bo.getDeptId() != null) {
			wrapper.where("dept_id={0}", bo.getDeptId());
		}
		return selectList(wrapper);
	}

	/**
	 * @author: Leslie @Description: @Date 2018/1/19 14:48 @param: [map] @return
	 * void @throws
	 */
	@Override
	public Integer countByCondition(Map<String, Object> map) {
		return tiMuShiLiMapper.countByCondition(map);
	}

	@Override
	public List<WenJuanVo> queryWjByWjId(Page<Map<String, Object>> page, Long wjId) {
		return tiMuShiLiMapper.queryWjByWjId(page, wjId);
	}
}
