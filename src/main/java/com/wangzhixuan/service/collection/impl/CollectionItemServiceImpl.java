package com.wangzhixuan.service.collection.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.collection.CollectionItemMapper;
import com.wangzhixuan.model.collection.CollectionItem;
import com.wangzhixuan.model.collection.CollectionItemOption;
import com.wangzhixuan.model.vo.collection.CollectionItemVo;
import com.wangzhixuan.service.collection.ICollectionItemOptionService;
import com.wangzhixuan.service.collection.ICollectionItemService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:38
 * @Date:2018/3/1 Description:
 */
@Service
public class CollectionItemServiceImpl extends ServiceImpl<CollectionItemMapper, CollectionItem>
		implements ICollectionItemService {
	@Autowired
	private ICollectionItemOptionService itemOptionService;
	@Autowired
	private CollectionItemMapper itemMapper;

	@Override
	public PageInfo selectDataGrid(CollectionItemVo vo) {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		vo.setStatus(1);
		List<Map<String, Object>> list = itemMapper.selectPage(page, vo);
		if (vo.getShowItem() != null && vo.getShowItem() == 1 && list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Long id = (Long) map.get("id");
				List<CollectionItemOption> itemOptions = itemOptionService.selectOptionsByItemId(id);
				map.put("options", itemOptions);
			}
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public String addOrUpdate(CollectionItemVo vo, ShiroUser user) {
		if (StringUtils.isBlank(vo.getName())) {
			throw new SysException("采集项名不能为空");
		}
		if (vo.getType() == null) {
			throw new SysException("采集项类型不能为空");
		}
		// 采集项-内容（选择或填空）
		List<CollectionItemOption> options = vo.getOptions();
		boolean optionFlag = options == null || options.size() < 1;
		if (optionFlag) {
			throw new SysException("请至少添加一个选项");
		}
		// 是否删除之前的采集项-内容
		// boolean isDelete = false;
		String str;
		// 新增
		if (vo.getId() == null) {
			vo.setStatus(1);
			vo.setCreateTime(new Date());
			vo.setCreateUserName(user.getName());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			insert(vo);
			str = "添加成功";
		} else {
			// 将原来采集项状态改为9（逻辑删除）
			vo.setStatus(9);
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			updateById(vo);
			// isDelete = true;
			// 添加新的采集项
			vo.setId(null);
			vo.setStatus(1);
			vo.setCreateTime(new Date());
			vo.setCreateUserName(user.getName());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserName(user.getName());
			insert(vo);
			str = "修改成功";
		}
		// 添加采集项-内容（选项只增不减）
		for (int i = 0; i < options.size(); i++) {
			CollectionItemOption itemOption = options.get(i);
			itemOption.setStatus(1);
			itemOption.setSeq(i + 1);
			itemOption.setCreateTime(new Date());
			itemOption.setCreateUserName(user.getName());
			itemOption.setUpdateTime(new Date());
			itemOption.setUpdateUserName(user.getName());
			itemOption.setItemId(vo.getId());
			itemOptionService.insert(itemOption);
		}
		return str;
	}

	@Override
	public CollectionItemVo getDetail(CollectionItemVo vo) {
		if (vo == null || vo.getId() == null) {
			throw new SysException("该采集项不存在");
		}
		EntityWrapper<CollectionItem> wrapper = new EntityWrapper<>();
		wrapper.where("id={0}", vo.getId());
		if (vo.getStatus() != null) {
			wrapper.where("status={0}", vo.getStatus());
		}
		CollectionItem collectionItem = selectOne(wrapper);
		if (collectionItem != null) {
			BeanUtils.copy(collectionItem, vo);
		} else {
			return null;
		}
		List<CollectionItemOption> options = itemOptionService.selectOptionsByItemId(vo.getId());
		vo.setOptions(options);
		return vo;
	}

	@Override
	public void delete(CollectionItemVo vo, ShiroUser user) {
		if (vo == null || vo.getId() == null) {
			throw new SysException("该采集项不存在");
		}
		vo.setStatus(9);
		vo.setUpdateTime(new Date());
		vo.setUpdateUserName(user.getName());
		// 逻辑删除采集项
		updateById(vo);
		// 逻辑删除采集项内容
		itemOptionService.deleteByItemId(vo.getId());
	}

}
