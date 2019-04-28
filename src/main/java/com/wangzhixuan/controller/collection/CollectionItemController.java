/**
 * 2018-03-20 16:03:33
 */
package com.wangzhixuan.controller.collection;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.collection.CollectionItemVo;
import com.wangzhixuan.service.collection.ICollectionItemService;

/**
 * @author Esion
 *
 */
@RequestMapping(value = "/collection/item")
@Controller
public class CollectionItemController extends BaseController {
	@Autowired
	private ICollectionItemService collectionItemService;

	/**
	 * 分页+搜索
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> dataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		CollectionItemVo vo = BeanUtils.mapToBean(map, CollectionItemVo.class);
		PageInfo pageInfo = collectionItemService.selectDataGrid(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 添加或更新
	 *
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate")
	@ResponseBody
	public RespResult<String> addOrUpdate(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<String>();
		// tiMuService.delete(vo.getId());
		// vo.setId(null);
		CollectionItemVo vo = BeanUtils.mapToBean(map, CollectionItemVo.class);
		String msg = collectionItemService.addOrUpdate(vo, getShiroUser());
		result.getSuccess("", msg);
		return result;
	}

	/**
	 * 单个采集项详情
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public RespResult<CollectionItemVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<CollectionItemVo> result = new RespResult<>();
		CollectionItemVo vo = BeanUtils.mapToBean(map, CollectionItemVo.class);
		collectionItemService.getDetail(vo);
		result.getSuccess(vo);
		return result;
	}

	/**
	 * 刪除单个采集项
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		// 禁止使用物理删除
		CollectionItemVo vo = BeanUtils.mapToBean(map, CollectionItemVo.class);
		collectionItemService.delete(vo, getShiroUser());
		result.getSuccess(null, "刪除成功");
		return result;
	}

}
