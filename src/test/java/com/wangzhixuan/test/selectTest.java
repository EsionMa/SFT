package com.wangzhixuan.test;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.mapper.SysUserOrganizationMapper;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.SysUserOrganization;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.impl.SysUserServiceImpl;
import com.wangzhixuan.test.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class selectTest extends BaseTest{
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysUserOrganizationMapper userOrganizationMapper;
   /* @Test
    public void select()throws SysException{
//        SysUser sysUser = new SysUser();
//        sysUser.setUserType(2);
//        SysUserOrganization sysUserOrganization = new SysUserOrganization();
//        sysUserOrganization.setOrganizationId(2L);

        UserVo vo = new UserVo();
        vo.setOrganizationId(2L);
        vo.setUserType(2);

        userService.getUsers(vo);



    }*/
}
