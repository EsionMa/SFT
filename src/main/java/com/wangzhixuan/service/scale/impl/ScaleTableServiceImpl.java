package com.wangzhixuan.service.scale.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.scale.ScaleCollectionMapper;
import com.wangzhixuan.mapper.scale.ScaleTableMapper;
import com.wangzhixuan.model.scale.ScaleCollection;
import com.wangzhixuan.model.scale.ScaleTable;
import com.wangzhixuan.model.vo.collection.CollectionTableVo;
import com.wangzhixuan.model.vo.scale.ScaleTableVo;
import com.wangzhixuan.service.collection.ICollectionlTableService;
import com.wangzhixuan.service.scale.IScaleTableService;

/**
 * 
 * @author Esion
 *
 */
@Service
public class ScaleTableServiceImpl extends ServiceImpl<ScaleTableMapper, ScaleTable> implements IScaleTableService {
	@Autowired
	private ScaleTableMapper scaleTableMapper;
	@Autowired
	private ScaleCollectionMapper scaleCollectionMapper;
	@Autowired
	private ICollectionlTableService collectionlTableService;

	@Override
	public PageInfo selectDataGrid(ScaleTableVo vo) {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		vo.setStatus(1);
		List<Map<String, Object>> list = scaleTableMapper.selectPage(page, vo);
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public String addOrUpdate(ScaleTableVo vo, ShiroUser user) {
		if (StringUtils.isBlank(vo.getName())) {
			throw new SysException("量表名不能为空");
		}
		if (vo.getType() == null) {
			throw new SysException("量表类型不能为空");
		}
		// 量表表
		List<CollectionTableVo> tableVos = vo.getTableVos();
		if (tableVos == null || tableVos.size() < 1) {
			throw new SysException("请至少添加一个量表");
		}
		String str = "";
		// 新增
		if (vo.getId() == null) {
			vo.setStatus(1);
			vo.setCreateUserName(user.getName());
			vo.setCreateTime(new Date());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			insert(vo);
			str = "添加成功";
		} else {
			// 将原来量表状态改为9（逻辑删除）
			vo.setStatus(9);
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			updateById(vo);
			// 添加新的量表
			vo.setId(null);
			vo.setStatus(1);
			vo.setCreateTime(new Date());
			vo.setCreateUserName(user.getName());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			insert(vo);
			str = "修改成功";
		}
		// 添加量表（量表-采集表中间表，只增不减）
		for (int i = 0; i < tableVos.size(); i++) {
			CollectionTableVo tableVo = tableVos.get(i);
			ScaleCollection scaleCollection = new ScaleCollection();
			scaleCollection.setScaleId(vo.getId());
			scaleCollection.setCollectionId(tableVo.getId());
			scaleCollectionMapper.insert(scaleCollection);
		}
		return str;
	}

	@Override
	public ScaleTableVo getDetail(ScaleTableVo vo) {
		if (vo == null || vo.getId() == null) {
			throw new SysException("该量表不存在");
		}
		Long scaleId = vo.getId();
		// 单个量表详情
		ScaleTable scaleTable = selectById(vo);
		vo = BeanUtils.copy(scaleTable, ScaleTableVo.class);
		List<CollectionTableVo> tableVos = null;
		// 单个量表包含的多个采集表
		List<ScaleCollection> scaleCollections = scaleCollectionMapper.selectColectionsByScaleId(scaleId);
		if (scaleCollections != null && scaleCollections.size() > 0) {
			tableVos = new ArrayList<>();
			for (ScaleCollection scaleCollection : scaleCollections) {
				CollectionTableVo tableVo = new CollectionTableVo();
				tableVo.setId(scaleCollection.getCollectionId());
				// 单个采集表详情
				tableVo = collectionlTableService.getDetail(tableVo);
				if (tableVo != null) {
					tableVos.add(tableVo);
				}
			}
		}
		vo.setTableVos(tableVos);
		return vo;
	}

	@Override
	public void delete(ScaleTableVo vo, ShiroUser user) {
		if (vo == null || vo.getId() == null) {
			throw new SysException("该采集表不存在");
		}
		vo.setStatus(9);
		vo.setUpdateTime(new Date());
		vo.setUpdateUserName(user.getName());
		updateById(vo);
	}

}
