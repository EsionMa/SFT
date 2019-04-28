package com.wangzhixuan.service.wenjuan.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wangzhixuan.mapper.wenjuan.WenJuanItemMapper;
import com.wangzhixuan.model.wenjuan.WenJuanItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.wenjuan.TiMuMapper;
import com.wangzhixuan.mapper.wenjuan.TiMuXuanXiangMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.vo.TiMuVo;
import com.wangzhixuan.model.wenjuan.TiMu;
import com.wangzhixuan.model.wenjuan.TimuXuanXiang;
import com.wangzhixuan.service.ISysDictionariesService;
import com.wangzhixuan.service.wenjuan.ITiMuService;

@Service
public class TiMuServiceImpl extends ServiceImpl<TiMuMapper, TiMu> implements ITiMuService {
	@Autowired
	private TiMuMapper tiMuMapper;
	@Autowired
	private TiMuXuanXiangMapper tiMuXuanXiangMapper;
	@Autowired
	private ISysDictionariesService sysDictionariesService;
	@Autowired
	private WenJuanItemMapper wenJuanItemMapper;

	@Override
	public PageInfo selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		List<Map<String, Object>> list = tiMuMapper.selectPage(page, pageInfo.getCondition());
		if (pageInfo.getCondition().get("showItem") != null && "1".equals(pageInfo.getCondition().get("showItem"))
				&& list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> tiMuMap = list.get(i);
				Long id = (Long) tiMuMap.get("id");
				List<TimuXuanXiang> timuXuanXiangs = tiMuXuanXiangMapper.listByTiMuId(id);
				tiMuMap.put("tmxxs", timuXuanXiangs);
			}
		}
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public void saveOrUpdate(TiMuVo vo, ShiroUser user) throws SysException {
		if (vo == null) {
			throw new SysException(ErrorCode.ReqIsNull);
		}
		// 第一步先保存选项，第二步保存题目
		List<TimuXuanXiang> tmxxs = vo.getTmxxs();
		// 是否需要题目选项,不为填空题则需要
		if (StringUtils.isBlank(vo.getTxxz())) {
			throw new SysException("需要选择题型");
		}
		boolean tmxxsFlag = (!"3".equals(vo.getTxxz()) && (tmxxs == null || tmxxs.size() < 1));
		// 校验
		if (tmxxsFlag) {
			throw new SysException(ErrorCode.ReqTMXXIsNull);
		}
		if (StringUtils.isBlank(vo.getBt())) {
			throw new SysException(ErrorCode.ReqTMBTIsNull);
		}
		if (StringUtils.isBlank(vo.getTmfl())) {
			throw new SysException(ErrorCode.ReqTMFLIsNull);
		}
		SysDictionaries sysDictionaries = sysDictionariesService.getByCode(vo.getTmfl());
		if (sysDictionaries == null) {
			throw new SysException(ErrorCode.ReqTMFLDisable);
		}
		//删除选项
        tiMuXuanXiangMapper.deleteTmxxByTiMuId(vo.getId());
		// Long tmId = null;
        vo.setUpdateUserId(user.getId());
        vo.setUpdateTime(new Date());
        vo.setStatus("1");
		if (vo.getId() == null) {
			// 保存
            //时间进行一致性，避免创建时间比修改时间大
			vo.setCreateTime(vo.getUpdateTime());
			vo.setCreateUserId(user.getId());
			tiMuMapper.insert(vo);
		} else {
			//修改
			tiMuMapper.updateById(vo);
		}
		// 非填空题时,操作题目选项
		if (!"3".equals(vo.getTxxz())) {
			for (int i = 0; i < tmxxs.size(); i++) {
				TimuXuanXiang tmxx = tmxxs.get(i);
				tmxx.setSeq(i + 1);
				tmxx.setId(null);
				tmxx.setUpdateTime(new Date());
				tmxx.setCreateTime(new Date());
				tmxx.setCreateUserId(user.getId());
				tmxx.setUpdateUserId(user.getId());
				tmxx.setTmId(vo.getId());
				tiMuXuanXiangMapper.insert(tmxx);
			}
		}
	}

	@Override
	public void delete(TiMuVo vo, ShiroUser user) throws SysException {
		if (vo == null || vo.getId() == null) {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
		tiMuMapper.deleteById(vo.getId());
		tiMuXuanXiangMapper.deleteTmxxByTiMuId(vo.getId());
	}

	@Override
	public TiMuVo queryDetailById(TiMuVo vo) throws SysException {
		EntityWrapper<TiMu> wrapper = new EntityWrapper<>();
		wrapper.where("id={0}", vo.getId());
		if (StringUtils.isNotBlank(vo.getStatus())) {
			wrapper.where("status={0}", vo.getStatus());
		}
		TiMu tiMu = selectOne(wrapper);
		if (tiMu != null) {
			BeanUtils.copy(tiMu, vo);
		} else {
			return null;
		}
		List<TimuXuanXiang> tmxxs = tiMuXuanXiangMapper.listByTiMuId(vo.getId());
		vo.setTmxxs(tmxxs);
		return vo;
	}

	@Override
	public List<TiMu> selectByWjId(Long wjId) throws SysException {
		EntityWrapper<WenJuanItem> wrapper1 = new EntityWrapper<>();
		wrapper1.where("wj_id={0}", wjId);
		wrapper1.where("tm_id is not null");
		List<WenJuanItem> wenJuanItems = wenJuanItemMapper.selectList(wrapper1);
		List<Long> ids = new ArrayList<>();
		for (int i = 0; i < wenJuanItems.size(); i++) {
			Long tmId = wenJuanItems.get(i).getTmId();
			ids.add(tmId);
		}
		List<TiMu> tiMus = selectBatchIds(ids);
		if (tiMus == null || tiMus.size() < 1) {
			return null;
		}
		return tiMus;
	}

	@Override
	public List<TiMu> listChoseByWjId(Long wjId) throws SysException {
		return tiMuMapper.listChoseByWjId(wjId);
	}

	@Override
	public PageInfo findByCondition(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		if (pageInfo.getCondition() != null) {
			List<Map<String, Object>> list = tiMuMapper.findByCondition(page, pageInfo.getCondition());
			if (pageInfo.getCondition().get("showItem") != null && "1".equals(pageInfo.getCondition().get("showItem"))
					&& list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> tiMuMap = list.get(i);
					Long id = (Long) tiMuMap.get("id");
					List<TimuXuanXiang> timuXuanXiangs = null;
					timuXuanXiangs = tiMuXuanXiangMapper.listByTiMuId(id);
					tiMuMap.put("tmxxs", timuXuanXiangs);
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
    public Integer countByWj(Long wjId) {
        if (wjId==null){
        	return 0;
		}
		return tiMuMapper.tmCountByWj(wjId);
    }

    @Override
    public Double selectScoreById(Long tmId) {
		Double score=tiMuMapper.selectScore(tmId);
		if (tmId==null||score==null){
			return 0D;
		}
        return score;
    }


}
