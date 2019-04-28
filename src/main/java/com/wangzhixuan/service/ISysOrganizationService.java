package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.model.SysOrganization;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface ISysOrganizationService extends IService<SysOrganization> {

    List<Tree> selectTree();

    List<SysOrganization> selectTreeGrid();
    List<SysOrganization> getDepts(Long parentId,String type)throws SysException;

    List<SysOrganization> getAllDepts();
    /**
     *@author: Leslie
     *@Date 2018/3/23 18:01
     *@param: [map]
     *@return java.util.List<com.wangzhixuan.model.SysOrganization>
     *@throws
     *@Description: 根据科室类型获取对应科室
     */
    List<SysOrganization> getDeparts(Map<String, Object> map);


    List<SysOrganization> getDeptsByType(String type);
    /**
     *@author: Leslie
     *@Date 2018/3/23 18:28
     *@param: [organization]
     *@return void
     *@throws
     *@Description: 增加科室
     */
    void addDept(SysOrganization organization);
}