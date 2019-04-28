package com.wangzhixuan.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.SysUserOrganization;
import com.wangzhixuan.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.List;

/**
 *
 * SysUserRole 表数据库控制层接口
 *
 */
public interface SysUserOrganizationMapper extends BaseMapper<SysUserOrganization> {



    List<SysUser> selected(@Param("organizationId") Long organizationId, @Param("userType") Integer userType);
}