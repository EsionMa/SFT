package com.wangzhixuan.service.collection;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.collection.CollectionTable;
import com.wangzhixuan.model.vo.collection.CollectionTableVo;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:44
 * @Date:2018/3/1 Description:构成表格的Service
 */
public interface ICollectionlTableService extends IService<CollectionTable> {
	/**
	 * 分页+搜索（showItem是否显示选项 1是0否）
	 * 
	 * @param vo
	 * @return
	 */
	PageInfo selectDataGrid(CollectionTableVo vo);

	/**
	 * @author: Leslie
	 * @Date 2018/3/2 10:50
	 * @param: [specialTableVo,
	 *             user]
	 * @return void
	 * @throws @Description:新增或者修改表格
	 */
	String addOrUpdate(CollectionTableVo vo, ShiroUser user);

	/**
	 * 获取单个采集表详情
	 * 
	 * @param vo
	 * @return
	 */
	CollectionTableVo getDetail(CollectionTableVo vo);

	/**
	 * 刪除单个采集表（逻辑删除）
	 * 
	 * @param vo
	 * @param user
	 */
	void delete(CollectionTableVo vo, ShiroUser user);
}
