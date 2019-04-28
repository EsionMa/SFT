package com.wangzhixuan.service.fuyou.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.fuyou.FuYouHuanZheInfoMapper;
import com.wangzhixuan.model.fuyou.FuYouHuanZheInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouHuanZheInfoService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/
 */
@Service
public class FuYouHuanZheInfoServiceImpl extends ServiceImpl<FuYouHuanZheInfoMapper, FuYouHuanZheInfo>
		implements IFuYouHuanZheInfoService {

	@Override
	public void saveOrUpdate(FuYouHuanZheInfoVo vo) throws SysException {
		if (vo.getId() == null) {
			insert(vo);
		} else {
			updateById(vo);
		}
	}

	@Override
	public PageInfo selectedData(FuYouHuanZheInfoVo vo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		EntityWrapper<FuYouHuanZheInfo> wrapper = new EntityWrapper<>();
		if (vo.getHzId() != null) {
			wrapper.where("hz_id={0}", vo.getHzId());
		}
		if (vo.getFyExpectedTime() != null) {
			wrapper.where("fy_expected_time={0}", vo.getFyExpectedTime());
		}
		selectMapsPage(page, wrapper);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}



	@Override
	public void deleteByHzId(Long hzId) throws SysException {
		EntityWrapper<FuYouHuanZheInfo> wrapper = new EntityWrapper<>();
		wrapper.where("hz_id={0}", hzId);
		delete(wrapper);
	}

	@Override
	public List<FuYouHuanZheInfoVo> selectByHzId(Long hzId) throws SysException {
		List<FuYouHuanZheInfoVo> vos = null;
		EntityWrapper<FuYouHuanZheInfo> wrapper = new EntityWrapper<>();
		wrapper.where("hz_id={0}", hzId);
		List<FuYouHuanZheInfo> fuYouHuanZheInfos = selectList(wrapper);
		if (fuYouHuanZheInfos != null && fuYouHuanZheInfos.size() > 0) {
			vos = new ArrayList<>();
			for (FuYouHuanZheInfo fuYouHuanZheInfo : fuYouHuanZheInfos) {
				vos.add(BeanUtils.copy(fuYouHuanZheInfo, FuYouHuanZheInfoVo.class));
			}
		}
		return vos;
	}

}
