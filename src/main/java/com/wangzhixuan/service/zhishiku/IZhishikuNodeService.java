package com.wangzhixuan.service.zhishiku;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.vo.zhishiku.NodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Leslie on 2017/8/20.
 * TIME:15:12
 * Date:2017/8/20.
 * Description:
 */
public interface IZhishikuNodeService extends IService<ZhiShiKuNode>{


    /**
     *
     * @param parentId
     * @return
     */
    List<ZhiShiKuNode> getParentNode(Long parentId);
    /**
     * 根据parentId查询知识库树，当parentId为空时为所有节点
     *
     * @param id
     * @return
     */
    List<ZhiShiKuNodeVo> nodeTree(Long id);
    /**
     *@Author: Leslie
     *@Description:添加知识库节点
     *@Date 2017/8/20 15:45
     */
    void  saveNode(ZhiShiKuNode vo, ShiroUser user);
    /**
     *@Author: Leslie
     *@Description:删除识库节点
     *@Date 2017/8/20 15:46
     */
    void  deleteNode(Long nodeId);

    /**
     * 根据父id得到病种，以及相应的icd
     * @param map
     * @return
     */
    List<ZhiShiKuNode> getDisByDeptId(Map<String, Object> map);


    /**
     *@Author: Leslie
     *@Description:为知识库的节点添加icd说明
     *@Date 2018/1/9 18:50
     *@Param:
     */
    NodeVo addIcdFromNode(ZhiShiKuNode zhiShiKu);

    /**
     * 封装一个NodeVO，包含list<icd> 和知识库List<zhishikuVo>
     * @param pageInfo
     * @param vo
     * @return
     */
    NodeVo nodeQueryIcd(PageInfo pageInfo, ZhiShiKuVo vo);

    /**
     * 修改或增加节点
     * @param vo
     */
    void saveOrUpdateNode(NodeVo vo,ShiroUser user);

   /**
    *@author: Leslie
    *@Date 2018/2/5 15:23
    *@param: [map]
    *@return java.util.List<com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNode>
    *@throws
    *@Description: 通过一个list的icd 编码，得到set的病种节点
    */
    List<ZhiShiKuNode> getDisByIcd(Map<String, Object> map);
}
