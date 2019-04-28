package com.wangzhixuan.service.zhishiku.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.zhishiku.ZhiShiKuMapper;
import com.wangzhixuan.mapper.zhishiku.ZhiShiKuNodeMapper;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.icd.IcdCodeList;
import com.wangzhixuan.model.vo.zhishiku.NodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKu;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;
import com.wangzhixuan.service.icd.IIcdService;
import com.wangzhixuan.service.zhishiku.IZhiShiKuService;
import com.wangzhixuan.service.zhishiku.IZhishikuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Leslie on 2017/8/20.
 * TIME:15:12
 * Date:2017/8/20.
 * Description:
 */
@Service
public class ZhishikuNodeServiceImp extends ServiceImpl<ZhiShiKuNodeMapper,ZhiShiKuNode> implements IZhishikuNodeService{
    @Autowired
    private ZhiShiKuNodeMapper nodeMapper;
    @Autowired
    private ZhiShiKuMapper zhiShiKuMapper;
    @Autowired
    private IIcdService icdService;
    @Autowired
    private IZhiShiKuService zhiShiKuService;


    @Override
    public List<ZhiShiKuNode> getParentNode(Long parentId) {
        EntityWrapper<ZhiShiKuNode> aMapper = new EntityWrapper<>();
        if (parentId == null) {
            aMapper.where("parent_id IS NULL");
        } else {
            aMapper.where("parent_id = {0}", parentId);
        }
        List<ZhiShiKuNode> zhiShiKuNodes = nodeMapper.selectList(aMapper);
        return zhiShiKuNodes;
    }

    /**
     * 放弃递归查询，太慢了
     * @param parentId
     * @return
     */
    @Override
    public List<ZhiShiKuNodeVo> nodeTree(Long parentId) {
        List<ZhiShiKuNodeVo> result=new ArrayList<>();
      List<ZhiShiKuNode> nodes=getParentNode(null);
      //父节点,自己手动创建迭代，此时只是两级迭代，递归会查询三次
      for (ZhiShiKuNode item:nodes){
          ZhiShiKuNodeVo vo=new ZhiShiKuNodeVo();
          BeanUtils.copyProperties(item, vo);
          vo.setIsParent("true");
          vo.setChildren(getParentNode(item.getId()));
          result.add(vo);
      }
        return result;
    }



    /**
     *@Author: Leslie
     *@Description:添加知识库节点的同时，还得添加知识库的icd
     *@Date 2018/1/10 10:10
     *@Param:
     */
    @Override
    public void saveNode(ZhiShiKuNode vo, ShiroUser user) throws SysException{
        if (vo.getText()!=null&&user!=null&&vo.getDeptId()!=null){
            vo.setCreateUserId(user.getId());
            vo.setCreateTime(new Date());
            insert(vo);
        }else {
            throw new SysException("存在空内容或用户验证过期");
        }
    }
    /**
     *@Author: Leslie
     *@Description:删除知识库节点
     *@Date 2017/8/20 15:48
     */
    @Override
    public void deleteNode(Long nodeId) throws SysException{
        if (nodeId!=null){
            List<ZhiShiKuNode> nodes=nodeMapper.findNodeByPId(nodeId);
            if (nodes==null||nodes.size()==0){
                EntityWrapper<ZhiShiKu> wrapper=new EntityWrapper<>();
                wrapper.where("node_id={0}",nodeId);
                List<ZhiShiKu> list=zhiShiKuMapper.selectList(wrapper);
                if (list.size()==0){
                    nodeMapper.deleteByNodeId(nodeId);
                    icdService.deleteNodeAndIcd(nodeId);
                }
                else {
                    throw new SysException("该病种存在知识库，无法删除");
                }
            }else {
                throw new SysException("该节点存在子节点，无法删除");
            }
        }else {
            throw new  SysException(ErrorCode.ReqIdIsNull);
        }
    }

    @Override
    public List<ZhiShiKuNode> getDisByDeptId(Map<String, Object> map) {
        if (map.get("parentId")==null||map.get("parentId")==""){
            throw new SysException("请传入科室id");
        }
        EntityWrapper<ZhiShiKuNode> wrapper=new EntityWrapper<>();
        wrapper.where("parent_id={0}",map.get("parentId"));
        return selectList(wrapper);
    }

    @Override
    public NodeVo addIcdFromNode(ZhiShiKuNode zhiShiKu) {
        if (zhiShiKu==null||zhiShiKu.getId()==null){
            throw new SysException("知识库节点为空");
        }
        NodeVo vo=new NodeVo();
        BeanUtils.copyProperties(zhiShiKu,vo);
        vo.setIcds(icdService.getIcdListByNode(zhiShiKu.getId()));
        return vo;
    }

    @Override
    public NodeVo nodeQueryIcd(PageInfo pageInfo, ZhiShiKuVo vo) {
        if (vo==null||vo.getNodeId()==null){
            throw new SysException("无相关节点信息");
        }
        //得到node节点,全是二级节点
        ZhiShiKuNode zhiShiKuNode=selectById(vo.getNodeId());
        //将节点转化为nodeVo,包含一堆icd
        NodeVo nodeVo=addIcdFromNode(zhiShiKuNode);
        //将下面的知识库放入到里面去
        nodeVo.setZhiShiKuVos(zhiShiKuService.queryByNodeId4DataGrid(pageInfo,vo));
        return nodeVo;
    }

    @Override
    public void saveOrUpdateNode(NodeVo vo,ShiroUser user) {
        if (user==null){
            throw new SysException("登录信息过期");
        }
        List<Icd> icds=vo.getIcds();
        if (vo.getId()==null){
            //增加
            vo.setCreateUserId(user.getId());
            vo.setUpdateUserId(user.getId());
            vo.setCreateTime(new Date());
            vo.setUpdateTime(new Date());
            nodeMapper.insert(vo);
        }else {
            vo.setUpdateTime(new Date());
            vo.setUpdateUserId(user.getId());
            nodeMapper.updateById(vo);
            icdService.deleteNodeAndIcd(vo.getId());
        }
        if (icds!=null&&icds.size()>0){
            icdService.addNodeAndIcd(icds,vo.getId());
        }
    }

    @Override
    public List<ZhiShiKuNode> getDisByIcd(Map<String, Object> map) {
        if (map==null||map.get("codes")==null){
            throw new SysException("传入icd编码为空");
        }
        IcdCodeList list= BeanUtils.mapToBean(map, IcdCodeList.class);
         if (list==null){
             list=new IcdCodeList();
         }
         if (list.getCodes()==null||list.getCodes()==null){
             throw new SysException("icd编码为空");
         }
         Set<String> codes=list.getCodes();
        List<Long> nodeVos = nodeMapper.getNodesByCodes(codes);
        List<ZhiShiKuNode> vos=new ArrayList<>();
          if (nodeVos!=null||nodeVos.size()>0){
              for (Long item:nodeVos){
                  ZhiShiKuNode nodeVo=selectById(item);
                  vos.add(nodeVo==null?null:nodeVo);
              }
          }
        return vos;
    }
}
