package com.wangzhixuan.mapper.zhishiku;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNodeVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ZhiShiKuNodeMapper extends BaseMapper<ZhiShiKuNode> {

    /*List<ZhiShiKuNodeVo> findNodeByDeptId(@Param("deptId") Long deptId);*/

    void deleteByNodeId(@Param("nodeId") Long nodeId);

    List<ZhiShiKuNode> findNodeByPId(@Param("nodeId") Long nodeId);
    /**
     *@author: Leslie
     *@Date 2018/2/5 16:05
     *@param: [codes]
     *@return java.util.List<java.lang.Long>
     *@throws
     *@Description:得到nodeId集合，通过诊断编码集合
     */
    List<Long> getNodesByCodes(@Param("codes") Set<String> codes);
}
