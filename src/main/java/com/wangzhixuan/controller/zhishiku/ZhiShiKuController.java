package com.wangzhixuan.controller.zhishiku;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wangzhixuan.model.vo.zhishiku.NodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.BatchIds;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;
import com.wangzhixuan.service.zhishiku.IZhiShiKuService;
import com.wangzhixuan.service.zhishiku.IZhishikuNodeService;

@Controller()
@RequestMapping("/zhiShiKu")
public class ZhiShiKuController extends BaseController {
	@Autowired
	private IZhiShiKuService zhiShiKuService;
	@Autowired
	private IZhishikuNodeService nodeService;

	@RequestMapping(value = "/nodeTree")
	@ResponseBody
	public RespResult<List<ZhiShiKuNodeVo>> nodeTree(@RequestBody Map<String, Object> map) {
		RespResult<List<ZhiShiKuNodeVo>> result = new RespResult<>();
		ZhiShiKuNodeVo vo = BeanUtils.mapToBean(map, ZhiShiKuNodeVo.class);
		if (vo==null){
			vo=new ZhiShiKuNodeVo();
		}
		List<ZhiShiKuNodeVo> nodeTree = nodeService.nodeTree(vo.getParentId());
		result.getSuccess(nodeTree);
		return result;
	}

	/**
	 * 获取知识库父节点
	 * @return
	 */
	 @RequestMapping("/getParentNode")
	 @ResponseBody
	 public  RespResult<List<ZhiShiKuNode>> getParentNode(){
			RespResult<List<ZhiShiKuNode>> result=new RespResult<>();
			result.getSuccess(nodeService.getParentNode(null));
			return  result;
	 }

	/**
	 * （分页+搜索）
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryByNodeId/dataGrid")
	@ResponseBody
	public RespResult<PageInfo> queryByNodeId4DataGrid(@RequestBody Map<String, Object> map) {
		RespResult<PageInfo> result = new RespResult<>();
		ZhiShiKuVo vo = BeanUtils.mapToBean(map, ZhiShiKuVo.class);
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		zhiShiKuService.queryByNodeId4DataGrid(pageInfo, vo);
		result.getSuccess(pageInfo);
		return result;
	}



	/**
	 * @Author: Leslie
	 * @Description:添加知识库
	 * @Date 2017/8/16 13:45
	 */
	@ResponseBody
	@RequestMapping(value = "/addZhishiku")
	public RespResult<String> addOrUpdate(@RequestBody ZhiShiKuVo vo) {
		RespResult<String> result = new RespResult<String>();
		ShiroUser user = getShiroUser();
		zhiShiKuService.save(vo, user);
		result.getSuccess("", "添加成功");
		return result;
	}

	/**
	 * @Author: Leslie
	 * @Description:
	 * @Date 2017/8/16 14:14
	 */
	@ResponseBody
	@RequestMapping(value = "/updateZhishiku")
	public RespResult<String> update(@RequestBody ZhiShiKuVo vo) {
		RespResult<String> result = new RespResult<>();
		ShiroUser user = getShiroUser();
		zhiShiKuService.update(vo, user);
		result.getSuccess("", "修改成功");
		return result;
	}

	/**
	 * @Author: Leslie
	 * @Description:删除知识库(可批量)
	 * @Date 2017/8/16 14:42
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteZhishiku")
	public RespResult<String> deleteZhi(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		zhiShiKuService.deleteZhi(map);
		result.getSuccess("", "删除成功");
		return result;
	}






    @RequestMapping(value = "/getDisByDeptId")
	@ResponseBody
	public RespResult<List<ZhiShiKuNode>> getDisByDeptId(@RequestBody Map<String,Object> map){
		RespResult<List<ZhiShiKuNode>> result=new RespResult<>();
		List<ZhiShiKuNode> nodes= nodeService.getDisByDeptId(map);
		result.getSuccess(nodes);
		return result;
	}
	@RequestMapping(value = "/queryByNodeId/nodeQueryIcd")
	@ResponseBody
	public RespResult<NodeVo> nodeQueryIcd(@RequestBody Map<String,Object> map){
		RespResult<NodeVo> result=new RespResult<>();
		ZhiShiKuVo vo = BeanUtils.mapToBean(map, ZhiShiKuVo.class);
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		NodeVo nodeVo=nodeService.nodeQueryIcd(pageInfo,vo);
		result.getSuccess(nodeVo);
		return result;
	}
	@RequestMapping(value = "/saveOrUpdateNode")
	@ResponseBody
	public RespResult<String> saveOrUpdateNode(@RequestBody Map<String,Object> map){
		RespResult<String> result=new RespResult<>();
		NodeVo vo=BeanUtils.mapToBean(map,NodeVo.class);
		nodeService.saveOrUpdateNode(vo,getShiroUser());
		result.getSuccess("","操作成功");
		return result;
	}
	/**
	 *@author: Leslie
	 *@Date 2018/2/5 15:22
	 *@param: [map]
	 *@return com.wangzhixuan.commons.result.RespResult<java.util.List<com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNode>>
	 *@throws
	 *@Description: 通过患者的icd编码得到与其相关的病种编码
	 */
	@ResponseBody
	@RequestMapping(value="/getDisByIcd")
	public RespResult<List<ZhiShiKuNode>> nodeByIcd(@RequestBody Map<String,Object> map){
		RespResult<List<ZhiShiKuNode>> result=new RespResult<>();
		result.getSuccess(nodeService.getDisByIcd(map));
		return result;
	}
}
