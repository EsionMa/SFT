package com.wangzhixuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.mapper.SysUploadRecordsMapper;
import com.wangzhixuan.model.SysUploadRecords;
import com.wangzhixuan.model.vo.SysUploadRecordsVo;
import com.wangzhixuan.service.ISysUploadRecordsService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
@Service
public class SysUploadRecordsServiceImpl extends ServiceImpl<SysUploadRecordsMapper, SysUploadRecords> implements ISysUploadRecordsService {
    @Override
    public void selectDataGrid(PageInfo pageInfo, SysUploadRecordsVo vo) {
        Page<SysUploadRecords> page = new Page<SysUploadRecords>(pageInfo.getNowpage(), pageInfo.getSize());
        EntityWrapper<SysUploadRecords> wrapper = new EntityWrapper<SysUploadRecords>();
        if (vo.getStaDate() != null) {
            wrapper.where("uploadTime>={0}", vo.getStaDate());
        }
        if (vo.getEndDate() != null) {
            wrapper.where("uploadTime<={0}", vo.getEndDate());
        }
        wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
        selectPage(page, wrapper);
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
    }
}
