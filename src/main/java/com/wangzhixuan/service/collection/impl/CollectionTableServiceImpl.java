package com.wangzhixuan.service.collection.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.collection.CollectionTableItemMapper;
import com.wangzhixuan.mapper.collection.CollectionTableMapper;
import com.wangzhixuan.model.collection.CollectionTable;
import com.wangzhixuan.model.collection.CollectionTableItem;
import com.wangzhixuan.model.vo.collection.CollectionItemVo;
import com.wangzhixuan.model.vo.collection.CollectionTableVo;
import com.wangzhixuan.service.collection.ICollectionItemService;
import com.wangzhixuan.service.collection.ICollectionlTableService;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:45
 * @Date:2018/3/1 Description:
 */
@Service
public class CollectionTableServiceImpl extends ServiceImpl<CollectionTableMapper, CollectionTable>
		implements ICollectionlTableService {
	@Autowired
	private ICollectionItemService collectionItemService;
	@Autowired
	private CollectionTableMapper tableMapper;
	@Autowired
	private CollectionTableItemMapper tableItemMapper;

	@Override
	public PageInfo selectDataGrid(CollectionTableVo vo) {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		vo.setStatus(1);
		List<Map<String, Object>> list = tableMapper.selectPage(page, vo);
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public String addOrUpdate(CollectionTableVo vo, ShiroUser user) {
		if (StringUtils.isBlank(vo.getName())) {
			throw new SysException("采集表名不能为空");
		}
		if (vo.getType() == null) {
			throw new SysException("采集表类型不能为空");
		}
		// 采集项
		List<CollectionItemVo> itemVos = vo.getItemVos();
		if (itemVos == null || itemVos.size() < 1) {
			throw new SysException("请至少添加一个采集项");
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
			// 将原来采集表状态改为9（逻辑删除）
			vo.setStatus(9);
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			updateById(vo);
			// 添加新的采集表
			vo.setId(null);
			vo.setStatus(1);
			vo.setCreateTime(new Date());
			vo.setCreateUserName(user.getName());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			insert(vo);
			str = "修改成功";
		}
		// 添加采集项（采集表-采集项中间表，只增不减）
		for (int i = 0; i < itemVos.size(); i++) {
			CollectionItemVo itemVo = itemVos.get(i);
			CollectionTableItem tableItem = new CollectionTableItem();
			tableItem.setTableId(vo.getId());
			tableItem.setItemId(itemVo.getId());
			tableItemMapper.insert(tableItem);
		}
		return str;
	}

	@Override
	public CollectionTableVo getDetail(CollectionTableVo vo) {
		if (vo == null || vo.getId() == null) {
			throw new SysException("该采集表不存在");
		}
		Long tableId = vo.getId();
		// 单个采集表详情
		CollectionTable table = selectById(tableId);
		vo = BeanUtils.copy(table, CollectionTableVo.class);
		List<CollectionItemVo> itemVos = null;
		// 单个采集表包含的多个采集项
		List<CollectionTableItem> tableItems = tableItemMapper.selectItemsByTableId(tableId);
		if (tableItems != null && tableItems.size() > 0) {
			itemVos = new ArrayList<>();
			for (CollectionTableItem tableItem : tableItems) {
				CollectionItemVo itemVo = new CollectionItemVo();
				itemVo.setId(tableItem.getItemId());
				// 单个采集项详情
				itemVo = collectionItemService.getDetail(itemVo);
				if (itemVo != null) {
					itemVos.add(itemVo);
				}
			}
		}
		vo.setItemVos(itemVos);
		return vo;
	}

	@Override
	public void delete(CollectionTableVo vo, ShiroUser user) {
		if (vo == null || vo.getId() == null) {
			throw new SysException("该采集表不存在");
		}
		vo.setStatus(9);
		vo.setUpdateTime(new Date());
		vo.setUpdateUserName(user.getName());
		updateById(vo);
	}

}
