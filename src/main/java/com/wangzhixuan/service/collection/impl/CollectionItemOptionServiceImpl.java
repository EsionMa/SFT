package com.wangzhixuan.service.collection.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.tools.doclint.Entity;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.collection.CollectionItemOptionMapper;
import com.wangzhixuan.model.collection.CollectionItemOption;
import com.wangzhixuan.model.vo.collection.CollectionItemOptionVo;
import com.wangzhixuan.service.collection.ICollectionItemOptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:43
 * @Date:2018/3/1 Description:
 */
@Service
public class CollectionItemOptionServiceImpl extends ServiceImpl<CollectionItemOptionMapper, CollectionItemOption>
		implements ICollectionItemOptionService {
	@Autowired
	private CollectionItemOptionMapper itemOptionMapper;

	@Override
	public void deleteByItemId(Long itemId) {
		EntityWrapper<CollectionItemOption> wrapper = new EntityWrapper<>();
		wrapper.where("item_id={0}", itemId);
		CollectionItemOption itemOption = new CollectionItemOption();
		itemOption.setStatus(9);
		update(itemOption, wrapper);
	}

	@Override
	public Long updateByStatus(CollectionItemOption specialCheck, ShiroUser user) {
		if (specialCheck == null) {
			throw new SysException("请输入正确的内容");
		}
		if (StringUtils.isBlank(specialCheck.getContent())) {
			throw new SysException("内容不能为空");
		}
		if (specialCheck.getType() == null) {
			throw new SysException("题型不能为空");
		}
		// id为空，新增
		if (specialCheck.getId() == null) {
			specialCheck.setCreateTime(new Date());
			specialCheck.setStatus(0);
			specialCheck.setCreateUserName(user.getName());
			insert(specialCheck);
			return specialCheck.getId();
		} else {
			// 先修改，在新增
			CollectionItemOption newCheck = BeanUtils.copy(specialCheck, CollectionItemOption.class);
			// 修改原来的选项
			specialCheck.setStatus(9);
			specialCheck.setUpdateTime(new Date());
			specialCheck.setUpdateUserName(user.getName());
			updateById(specialCheck);
			// 新增一个选项
			newCheck.setId(null);
			newCheck.setCreateTime(new Date());
			newCheck.setStatus(0);
			newCheck.setCreateUserName(user.getName());
			insert(newCheck);
			return newCheck.getId();
		}
	}

	@Override
	public void deleteChecked(CollectionItemOption specialCheck, ShiroUser user) {
		if (specialCheck == null || specialCheck.getId() == null) {
			throw new SysException("请选择要删除的选项");
		}
		specialCheck.setUpdateUserName(user.getName());
		specialCheck.setUpdateTime(new Date());
		specialCheck.setStatus(9);
		updateById(specialCheck);
	}

	@Override
	public PageInfo getCheckeds(CollectionItemOptionVo vo) {
		Page<CollectionItemOptionVo> page = new Page<>(vo.getRows(), vo.getPage());
		PageInfo pageInfo = new PageInfo();
		List<CollectionItemOptionVo> list = itemOptionMapper.selectData(page, vo);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setRows(list);
		return pageInfo;
	}

	// @Override
	// public CollectionItemOptionVo tree(CollectionItemOptionVo vo) {
	// if (vo.getpId() != null) {
	// CollectionItemOption check = checkMapper.selectById(vo.getpId());
	// CollectionItemOptionVo specialCheckVo = BeanUtils.copy(check,
	// CollectionItemOptionVo.class);
	// vo.setFather(tree(specialCheckVo));
	// }
	// return vo;
	//
	// }

	@Override
	public List<CollectionItemOption> selectOptionsByItemId(Long itemId) {
		if (itemId == null) {
			throw new SysException("采集项ID不能为空");
		}
		EntityWrapper<CollectionItemOption> wrapper = new EntityWrapper<>();
		wrapper.where("item_id={0}", itemId).where("status=1");
		return selectList(wrapper);
	}
}
