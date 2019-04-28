package com.wangzhixuan.service.fa.imp;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.fa.FangAnZhuYuanGroupMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.fa.FangAnZhuYuanGroup;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.service.fa.IFangAnZhuYuanGroupService;

@Service
public class FangAnZhuYuanGroupServiceImpl extends ServiceImpl<FangAnZhuYuanGroupMapper, FangAnZhuYuanGroup>
		implements IFangAnZhuYuanGroupService {
	@Autowired
	private FangAnZhuYuanGroupMapper fangAnZhuYuanGroupMapper;

	@Override
	public void saveOrUpdate(FangAnZhuYuanGroupVo vo) throws SysException {
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
			// throw new SysException("出院科室不能为空");
		}
		List<Icd> cyzds = vo.getCyzds();
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
			vo.setZdCodes(zdSb.toString());
		} else {
			vo.setZdCodes(null);
			// throw new SysException("出院诊断不能为空");
		}
		if (StringUtils.isBlank(vo.getSxsjlx())) {
			throw new SysException("筛选时间类型不能为空");
		}
		if (vo.getZxsxsj() == null) {
			throw new SysException("开始时间不能为空");
		}
		if (vo.getId() == null) {
			insert(vo);
		} else {
			updateAllColumnById(vo);
		}
	}

	@Override
	public FangAnZhuYuanGroupVo findByFangAnId(Long faId) throws SysException {
		FangAnZhuYuanGroup group = new FangAnZhuYuanGroup();
		group.setFaId(faId);
		group = fangAnZhuYuanGroupMapper.selectOne(group);
		if (group == null) {
			return null;
		}
		FangAnZhuYuanGroupVo vo = new FangAnZhuYuanGroupVo();
		BeanUtils.copy(group, vo);
		return vo;
	}

}
