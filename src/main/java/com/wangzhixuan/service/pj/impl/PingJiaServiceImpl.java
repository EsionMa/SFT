package com.wangzhixuan.service.pj.impl;

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
import com.wangzhixuan.mapper.pj.PingJiaMapper;
import com.wangzhixuan.model.pj.PingJia;
import com.wangzhixuan.model.vo.EvaCountVo;
import com.wangzhixuan.model.vo.pj.EvaluationVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;
import com.wangzhixuan.service.fa.IFangAnDingYiService;
import com.wangzhixuan.service.pj.IPingJiaService;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PingJiaServiceImpl extends ServiceImpl<PingJiaMapper, PingJia> implements IPingJiaService {
	@Autowired
	private PingJiaMapper mapper;
	@Autowired
	private SysOrganizationMapper orgMapper;
	@Autowired
	private IFangAnDingYiService fangAnDingYiService;

	@Override
	public void deleteByByPjLyAndExtId(String pjly, String extId, List<Long> ids) {
		EntityWrapper<PingJia> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(pjly)) {
			wrapper.where("pjly={0}", pjly);
		}
		if (StringUtils.isNotBlank(extId)) {
			wrapper.where("pjly_ext={0}", extId);
		}
		if (ids != null && ids.size() > 0) {
			wrapper.notIn("id", ids);
		}
		delete(wrapper);
	}

	@Override
	public void saveOrUpdate(PingJiaVo vo, ShiroUser user) {
		if (vo.getId() == null) {
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			insert(vo);
		} else {
			vo.setCreateTime(new Date());
			vo.setCreateUserId(user.getId());
			updateById(vo);
		}
	}
    //
	@Override
	public List<PingJiaVo> selectByPjLyAndExtId(String pjly, String extId) {
		List<PingJiaVo> vos = new ArrayList<>();
		EntityWrapper<PingJia> wrapper = new EntityWrapper<>();
		wrapper.where("pjly={0} and pjly_ext={1} and pjnr !=''", pjly, extId);
		List<PingJia> pjList = selectList(wrapper);
		for (PingJia pingJia : pjList) {
			vos.add(BeanUtils.copy(pingJia, PingJiaVo.class));
		}
		return vos;
	}

	@Override
	public PageInfo findByCondition(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		if (pageInfo.getCondition() != null) {
			List<Map<String, Object>> list = mapper.findByCondition(page, pageInfo.getCondition());
			if (list.size() > 0) {
				// 添加科室名字
				for (Map<String, Object> item : list) {
					// 添加方案名字
					if (item.get("faId") != null) {
						//空指针
						item.put("faName",
								fangAnDingYiService.selectById(Long.parseLong(item.get("faId").toString())).getName());
					}
				}
			}
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(list);
		}
		return pageInfo;
	}



	@Override
	public Map<String, List<EvaluationVo>> print(Map<String, Object> map) throws SysException {
		DateUtils.getEndTimeByStaTime(map);
		Map<String, List<EvaluationVo>> result = new HashedMap();
		for (int i = 1; i < 4; i++) {
			map.put("pjlx", i);
			List<Map<String, Object>> depts = mapper.findByPjlx(map);
			List<EvaluationVo> evaluationVos = new ArrayList<>();
			if (i == 1) {
				// 投诉
				result.put("ts", evaluationVos);
			} else if (i == 2) {
				// 建议
				result.put("jy", evaluationVos);
			} else if (i == 3) {
				// 表扬
				result.put("by", evaluationVos);
			}
			for (Map<String, Object> item : depts) {
				EvaluationVo evaluationVo = new EvaluationVo();
				map.put("cyDeptId", item.get("cyDeptId"));
				evaluationVo.setDeptName(item.get("cyName").toString());
				List<PingJiaVo> pingJias = mapper.findByDeptIdAndTime(map);
				if (pingJias.size() != 0) {
					evaluationVo.setEvaContentList(pingJias);
				}
				evaluationVos.add(evaluationVo);
			}

		}
		return result;
	}

    @Override
    public List<EvaCountVo> getFeedBackCount(Map<String, Object> map) {
		if (map==null||map.get("staTime")==null){
			throw new SysException("开始时间不能为空");
		}
		map.put("endTime",DateUtils.getDayEnd(DateUtils.parse(map.get("staTime").toString())));
       List<EvaCountVo> vos=mapper.getEvaCount(map);
       return vos;
    }
}
