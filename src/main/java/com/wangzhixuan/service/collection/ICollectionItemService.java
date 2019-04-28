package com.wangzhixuan.service.collection;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.collection.CollectionItem;
import com.wangzhixuan.model.vo.collection.CollectionItemVo;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:37
 * @Date:2018/3/1 Description:构成小型题目的Service
 */
public interface ICollectionItemService extends IService<CollectionItem> {
	/**
	 * 分页+搜索
	 * 
	 * @param vo
	 * @return
	 */
	PageInfo selectDataGrid(CollectionItemVo vo);

	/**
	 * 保存或更新
	 * 
	 * @param specialTypeVo
	 * @param user
	 * @return
	 */
	String addOrUpdate(CollectionItemVo specialTypeVo, ShiroUser user);

	/**
	 * 获取单个采集项详情
	 * 
	 * @param vo
	 * @return
	 */
	CollectionItemVo getDetail(CollectionItemVo vo);

	/**
	 * 刪除单个采集项（逻辑删除）
	 * 
	 * @param vo
	 * @param user
	 */
	void delete(CollectionItemVo vo, ShiroUser user);

}
