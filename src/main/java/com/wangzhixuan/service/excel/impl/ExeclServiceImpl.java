package com.wangzhixuan.service.excel.impl;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.SysUploadRecords;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.service.ISysUploadRecordsService;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;
import com.wangzhixuan.service.huanzhe.IMenZhenInfoService;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import com.wangzhixuan.util.JxlExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23 0023.
 */
@Service
public class ExeclServiceImpl {
    @Autowired
    private IHuanZheInfoService huanZheInfoService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IZhuYuanInfoService zhuYuanInfoService;
    @Autowired
    private IMenZhenInfoService menZhenInfoService;
    @Autowired
    private ISysUploadRecordsService uploadRecordsService;

    /**
     * excel上传到数据库实现类
     *
     * @param file 上传文件路径
     * @param id   对应表信息
     */
    public void insertData(MultipartFile file, Long id, ShiroUser user) {
        try {
            if (id == 1) {

                List<SysUser> users = JxlExcelUtil.readExcel(file.getInputStream(), SysUser.class);
                for (SysUser sysUser : users) {
                    if (StringUtils.isBlank(sysUser.getLoginName()) && StringUtils.isBlank(sysUser.getName()) ) {
                        throw new SysException("字段不匹配，请确认！");
                    } else {
                        SysUser userObj = userService.selectById(sysUser.getLoginName());
                        //可以将loginName赋值给outId 就是医护人员的工号，工号是唯一的。
                        sysUser.setOutId(sysUser.getLoginName());
                        sysUser.setCreateTime(new Date());
                        //对象为 null 就插入数据库，反之跳过继续执行循环。
                        if (userObj == null) {
                            userService.insertUser(sysUser);// TODO ?loginName
                        } else {
                            continue;
                        }
                    }
                }
            }
            if (id == 2) {
                List<HuanZheInfo> excel = JxlExcelUtil.readExcel(file.getInputStream(), HuanZheInfo.class);
                for (HuanZheInfo huanZheInfo : excel) {
                    if (StringUtils.isBlank(huanZheInfo.getHzno())) {
                        throw new SysException("字段不匹配，请确认！");
                    } else {
                        //将住院号拿到在数据库查询是否存在对象信息
                        HuanZheInfo huanZhe = huanZheInfoService.selectByHzNo(huanZheInfo.getHzno());
                        //对象为 null 就插入数据库，反之跳过继续执行循环。
                        if (huanZhe == null) {
                            huanZheInfoService.insert(huanZheInfo);
                        } else {
                            continue;
                        }
                    }
                }
            }
            if (id == 3) {
                List<ZhuYuanInfo> excel = JxlExcelUtil.readExcel(file.getInputStream(), ZhuYuanInfo.class);
                for (ZhuYuanInfo zhuYuanInfo : excel) {
                    if (StringUtils.isBlank(zhuYuanInfo.getZyNo())) {
                        throw new SysException("字段不匹配，请确认！");
                    } else {
                        ZhuYuanInfo zhuYuan = zhuYuanInfoService.selectById(zhuYuanInfo.getZyNo());
                        if (zhuYuan == null) {
                            zhuYuanInfoService.insert(zhuYuanInfo);
                        } else {
                            continue;
                        }
                    }
                }
            }
            if (id == 4) {
                List<MenZhenInfo> excel = JxlExcelUtil.readExcel(file.getInputStream(), MenZhenInfo.class);
                for (MenZhenInfo menZhenInfo : excel) {
                    if (StringUtils.isBlank(menZhenInfo.getMzNo())) {
                        throw new SysException("字段不匹配，请确认！");
                    } else {
                        MenZhenInfo menzhen = menZhenInfoService.selectById(menZhenInfo.getMzNo());
                        if (menzhen == null) {
                            menZhenInfoService.insert(menZhenInfo);
                        }else {
                            continue;
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new SysException("上传目标错误！");
        }
        //上传数据的同时做记录
        SysUploadRecords records = new SysUploadRecords();
        records.setUserName(user.getName());
        records.setFileName(file.getOriginalFilename());
        records.setUploadTime(new Date());
        //未做解析，直接给定为解析失败
        records.setStatus("0");
        uploadRecordsService.insert(records);
    }
}
