package com.wangzhixuan.service.fa.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.fa.FangAnMenZhenGroupMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.fa.FangAnMenZhenGroup;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;
import com.wangzhixuan.service.fa.IFangAnMenZhenGroupService;

@Service
public class FangAnMenZhenGroupServiceImpl extends ServiceImpl<FangAnMenZhenGroupMapper, FangAnMenZhenGroup>
		implements IFangAnMenZhenGroupService {
	@Autowired
	private FangAnMenZhenGroupMapper fangAnMenZhenGroupMapper;

	@Override
	public void saveOrUpdate(FangAnMenZhenGroupVo vo) throws SysException {
		if (vo.getFaId() == null) {
			// TODO 理论上要校验方案ID是否存在
			throw new SysException("方案ID不能为空");
		}
		List<SysOrganization> depts = vo.getDepts();
		if (depts != null && depts.size() > 0) {
			StringBuilder deptSb = new StringBuilder();
			for (int i = 0; i < depts.size(); i++) {
				SysOrganization sysOrganization = depts.get(i);
				if (sysOrganization != null) {
					deptSb.append(sysOrganization.getId()).append(",");
				}
			}
			if (deptSb.length() > 0) {
				deptSb.setLength(deptSb.length() - 1);
			}
			vo.setDeptIds(deptSb.toString());
		} else {
			vo.setDeptIds(null);
			// throw new SysException("门诊科室不能为空");
		}
		List<Icd> cyzds = vo.getMzzds();
		if (cyzds != null && cyzds.size() > 0) {
			StringBuilder zdSb = new StringBuilder();
			for (int i = 0; i < cyzds.size(); i++) {
				Icd icd = cyzds.get(i);
				if (icd != null) {
					zdSb.append(icd.getCode()).append(",");
				}
			}
			if (zdSb.length() > 0) {
				zdSb.setLength(zdSb.length() - 1);
			}
			vo.setDiseaseCodes(zdSb.toString());
		} else {
			vo.setDiseaseCodes(null);
			// throw new SysException("门诊诊断不能为空");
		}
		List<SysDictionaries> regTypes = vo.getRegTypes();
		if (regTypes != null && regTypes.size() > 0) {
			StringBuilder rtSb = new StringBuilder();
			for (int i = 0; i < regTypes.size(); i++) {
				SysDictionaries sysDictionaries = regTypes.get(i);
				if (sysDictionaries != null) {
					rtSb.append(sysDictionaries.getCode()).append(",");
				}
			}
			if (rtSb.length() > 0) {
				rtSb.setLength(rtSb.length() - 1);
			}
			vo.setRegisTypes(rtSb.toString());
		} else {
			vo.setRegisTypes(null);
			// throw new SysException("挂号类型不能为空");
		}
		List<SysDictionaries> regWays = vo.getRegWays();
		if (regWays != null && regWays.size() > 0) {
			StringBuilder rwSb = new StringBuilder();
			for (int i = 0; i < regWays.size(); i++) {
				SysDictionaries sysDictionaries = regWays.get(i);
				if (sysDictionaries != null) {
					rwSb.append(sysDictionaries.getCode()).append(",");
				}
			}
			if (rwSb.length() > 0) {
				rwSb.setLength(rwSb.length() - 1);
			}
			vo.setRegisWays(rwSb.toString());
		} else {
			vo.setRegisWays(null);
			// throw new SysException("挂号方式不能为空");
		}
		if (vo.getDateMin() == null) {
			throw new SysException("开始时间不能为空");
		}
		if (vo.getId() == null) {
			insert(vo);
		} else {
			updateAllColumnById(vo);
		}
	}

	@Override
	public FangAnMenZhenGroupVo findByFangAnId(Long faId) throws SysException {
		FangAnMenZhenGroup group = new FangAnMenZhenGroup();
		group.setFaId(faId);
		group = fangAnMenZhenGroupMapper.selectOne(group);
		if (group == null) {
			return null;
		}
		FangAnMenZhenGroupVo vo = new FangAnMenZhenGroupVo();
		BeanUtils.copy(group, vo);
		// if (vo != null) {
		// // 出院科室
		// String deptIds = vo.getDeptIds();
		// if (StringUtils.isNotBlank(deptIds)) {
		// List<SysOrganization> deptList = new ArrayList<>();
		// vo.setDepts(deptList);
		// String[] _deptIds = deptIds.split(",");
		// if (_deptIds != null && _deptIds.length > 0) {
		// for (int j = 0; j < _deptIds.length; j++) {
		// SysOrganization selectById =
		// sysOrganizationMapper.selectById(_deptIds[j]);
		// if (selectById != null) {
		// deptList.add(selectById);
		// }
		// }
		// }
		// }
		// // 门诊诊断
		// String zdCodes = vo.getDiseaseCodes();
		// if (StringUtils.isNotBlank(zdCodes)) {
		// List<Icd> mzzdList = new ArrayList<>();
		// String[] _zdCodes = zdCodes.split(",");
		// if (_zdCodes != null && _zdCodes.length > 0) {
		// for (int i = 0; i < _zdCodes.length; i++) {
		// Icd selectById = icdMapper.selectById(_zdCodes[i]);
		// if (selectById != null) {
		// mzzdList.add(selectById);
		// }
		// }
		// }
		// vo.setMzzds(mzzdList);
		// }
		// }
		return vo;
	}

}
