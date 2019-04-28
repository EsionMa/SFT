/**
 * 2018-03-21 19:11:42
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
import com.wangzhixuan.model.vo.collection.CollectionTableVo;
import com.wangzhixuan.service.collection.ICollectionlTableService;

/**
 * @author Esion
 *
 */
@RequestMapping(value = "/collection/table")
@Controller
public class CollectionTableController extends BaseController {
	@Autowired
	private ICollectionlTableService collectionlTableService;

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
		CollectionTableVo vo = BeanUtils.mapToBean(map, CollectionTableVo.class);
		PageInfo pageInfo = collectionlTableService.selectDataGrid(vo);
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
		CollectionTableVo vo = BeanUtils.mapToBean(map, CollectionTableVo.class);
		String msg = collectionlTableService.addOrUpdate(vo, getShiroUser());
		result.getSuccess("", msg);
		return result;
	}

	/**
	 * 单个采集表详情
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public RespResult<CollectionTableVo> queryDetailById(@RequestBody Map<String, Object> map) {
		RespResult<CollectionTableVo> result = new RespResult<>();
		CollectionTableVo vo = BeanUtils.mapToBean(map, CollectionTableVo.class);
		vo = collectionlTableService.getDetail(vo);
		result.getSuccess(vo);
		return result;
	}

	/**
	 * 刪除单个采集表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespResult<String> delete(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		// 禁止使用物理删除
		CollectionTableVo vo = BeanUtils.mapToBean(map, CollectionTableVo.class);
		collectionlTableService.delete(vo, getShiroUser());
		result.getSuccess(null, "刪除成功");
		return result;
	}
}
