package com.wangzhixuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysAuthority;

/**
 * Created by Leslie on 2017/12/5.
 *
 * @author: Leslie
 * @TIME:14:03
 * @Date:2017/12/5 Description:
 */
public interface ISysAuthorityService extends IService<SysAuthority>{
    void selectDataGrid(PageInfo pageInfo);

    void insertWithSalt(SysAuthority authority, ShiroUser user);

    void updateIpById(SysAuthority authority,ShiroUser user);


    boolean ipAndTimeIsCorrect(String ip);

    boolean ipIsExsit(String ipAddress);


}
