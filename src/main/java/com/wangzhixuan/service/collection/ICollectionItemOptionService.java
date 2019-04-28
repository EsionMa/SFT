package com.wangzhixuan.service.collection;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.collection.CollectionItemOption;
import com.wangzhixuan.model.vo.collection.CollectionItemOptionVo;

import java.util.List;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:43
 * @Date:2018/3/1 Description:选项接口Service
 */
public interface ICollectionItemOptionService extends IService<CollectionItemOption> {
	/**
	 * 删除采集项中的内容（选择或填空）（逻辑）
	 * 
	 * @param itemId
	 */
	void deleteByItemId(Long itemId);

	/**
	 * @author: Leslie
	 * @Date 2018/3/2 10:21
	 * @param: [specialCheck,
	 *             user]
	 * @return void
	 * @throws @Description:单个选项的修改或者新增方法
	 */
	Long updateByStatus(CollectionItemOption specialCheck, ShiroUser user);

	/**
	 * @author: Leslie
	 * @Date 2018/3/2 10:48
	 * @param: [specialCheck,
	 *             user]
	 * @return void
	 * @throws @Description:逻辑删除选项
	 */
	void deleteChecked(CollectionItemOption specialCheck, ShiroUser user);

	/**
	 * @author: Leslie
	 * @Date 2018/3/2 15:44
	 * @param: [vo]
	 * @return com.wangzhixuan.commons.result.PageInfo
	 * @throws @Description:条件+分页
	 */
	PageInfo getCheckeds(CollectionItemOptionVo vo);

	/**
	 * 当要制作表格的时候才使用
	 * 
	 * @param vo
	 * @return
	 */
	// CollectionItemOptionVo tree(CollectionItemOptionVo vo);

	/**
	 * 通过采集项ID查询所有选项
	 * 
	 * @param itemId
	 * @return
	 */
	List<CollectionItemOption> selectOptionsByItemId(Long itemId);
}
