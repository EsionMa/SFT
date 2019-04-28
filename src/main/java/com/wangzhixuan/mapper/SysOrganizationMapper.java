package com.wangzhixuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysOrganization;

/**
 *
 * Organization 表数据库控制层接口
 *
 */
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {
	List<SysOrganization> queryByParentId(@Param("parentId") Long parentId, @Param("type") String type);
   /**
    *@author: Leslie
    *@Date 2018/3/23 18:05
    *@param: [type]
    *@return java.util.List<com.wangzhixuan.model.SysOrganization>
    *@throws
    *@Description: 根据科室类型获取科室
    */
    List<SysOrganization> selectByType(@Param("type") String type);
}