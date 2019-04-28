package com.wangzhixuan.service.zhishiku.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.service.ISysOrganizationService;
import com.wangzhixuan.service.zhishiku.IZhishikuNodeService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.zhishiku.ZhiShiKuMapper;
import com.wangzhixuan.mapper.zhishiku.ZhiShiKuNodeMapper;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKu;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;
import com.wangzhixuan.service.zhishiku.IZhiShiKuService;

@Service
public class ZhiShiKuServiceImpl extends ServiceImpl<ZhiShiKuMapper, ZhiShiKu> implements IZhiShiKuService {
	@Autowired
	private ZhiShiKuNodeMapper zhiShiKuNodeMapper;
	@Autowired
	private ZhiShiKuMapper zhiShiKuMapper;
	@Autowired
	private ISysOrganizationService organizationService;
	@Autowired
	private IZhishikuNodeService nodeService;







	/**
	 * 将deptId 一并传过来，就没事了
	 * @param pageInfo
	 * @param vo
	 * @return
	 */
	@Override
	public PageInfo queryByNodeId4DataGrid(PageInfo pageInfo, ZhiShiKuVo vo) {
		Map<String,Object> map=new HashedMap();
		if (vo.getNodeId() != null){
			map.put("nodeId",vo.getNodeId());
		}
		if (StringUtils.isNotBlank(vo.getType())){
			map.put("type",vo.getType());
		}
		if (StringUtils.isNotBlank(vo.getTitle())) {
			map.put("title",vo.getTitle());
		}
		if (StringUtils.isNotBlank(vo.getContent())) {
			map.put("content",vo.getContent());
		}
		if (StringUtils.isNotBlank(vo.getDiseaseName())){
			map.put("diseaseName",vo.getDiseaseName());
		}
		Page<ZhiShiKu> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		List<ZhiShiKuVo> list= zhiShiKuMapper.queryByNodeId4DataGrid(page,map);
		for (ZhiShiKuVo item:list){
			ZhiShiKuNode node=nodeService.selectById(item.getDeptId());
			item.setDeptId(node==null?null:node.getId());
		}
		pageInfo.setTotal(page.getTotal());
		pageInfo.setRows(list);
		return pageInfo;
	}

	@Override
	public List<ZhiShiKuVo> queryByNodeId(Long nodeId) {
		if (nodeId==null){
			throw new SysException("节点id为空");
		}
		List<ZhiShiKuVo> vos = new ArrayList<>();
		EntityWrapper<ZhiShiKu> wrapper = new EntityWrapper<>();
			wrapper.where("node_id={0}", nodeId);
		List<ZhiShiKu> zhiShiKus = selectList(wrapper);
		for (ZhiShiKu item : zhiShiKus) {
			ZhiShiKuVo zhiShiKuVo = BeanUtils.copy(item, ZhiShiKuVo.class);
			vos.add(zhiShiKuVo);
		}
		return vos;
	}

	/**
	 * @Author: Leslie
	 * @Description:根据部门id查询知识库
	 * @Date 2017/8/16 16:17 /*
	 *//*
		 * @Override public PageInfo findBydeptId(PageInfo pageInfo,
		 * ZhiShiKuNodeVo vo) { EntityWrapper<ZhiShiKuNode> aMapper = new
		 * EntityWrapper<>();
		 *
		 * return null; }
		 */

	/**
	 * @Author: Leslie
	 * @Description:删除
	 * @Date 2017/8/16 14:50
	 */
	@Override
	public void deleteZhi(Map<String,Object> map) {
		Long nodeId=null;
		Long knowId=null;
		if (map.get("nodeId")!=null&&map.get("nodeId")!=""){
           nodeId=Long.parseLong(map.get("nodeId").toString());
		}
		if (map.get("knowId")!=null&&map.get("knowId")!=""){
			knowId=Long.parseLong(map.get("knowId").toString());
		}
		if (nodeId==null&&knowId!=null){
			//删除知识库
			zhiShiKuMapper.deleteById(knowId);
		}else if (nodeId!=null&&knowId==null){
			//删除节点
			nodeService.deleteNode(nodeId);
		}else {
			throw new SysException("系统错误，暂时无法删除");
		}
	}



	/**
	 * @Author: Leslie
	 * @Description:修改知识库
	 * @Date 2017/8/16 14:09
	 */
	@Override
	public void update(ZhiShiKuVo vo, ShiroUser user) {
		if (user != null && vo != null && vo.getId() != null && !StringUtils.isBlank(vo.getContent()) && !StringUtils.isBlank(vo.getTitle())) {
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			zhiShiKuMapper.updateZhishiku(vo);
		} else {
			throw new SysException("修改发生错误，请检查录入数据");
		}
	}

	/**
	 * @Author: Leslie
	 * @Description:添加知识库
	 * @Date 2017/8/16 13:59
	 */
	@Override
	public void save(ZhiShiKuVo vo, ShiroUser user) throws SysException {
		EntityWrapper<ZhiShiKu> wrapper = new EntityWrapper<>();
		if (vo != null && user != null && vo.getNodeId() != null && !StringUtils.isBlank(vo.getContent()) && !StringUtils.isBlank(vo.getTitle())) {
			vo.setCreateUserId(user.getId());
			vo.setCreateTime(new Date());
		/*	ZhiShiKuNode node=zhiShiKuNodeMapper.selectById(vo.getNodeId());
			vo.setDiseaseName(node==null?null:node.getText());*/
			insert(vo);
		} else {
			throw new SysException("添加发生错误，请检查录入数据");
		}

	}

	/**
	 * @Author: Leslie
	 * @Description:根据nodeId查询parentId
	 * @Date 2017/8/21 10:38
	 */
	@Override
	public Long findPIdByNodeId(Long nodeId) {
		if (nodeId != null) {
			Long pId = zhiShiKuMapper.findPIdByNodeId(nodeId);
			return pId;
		} else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}

}
