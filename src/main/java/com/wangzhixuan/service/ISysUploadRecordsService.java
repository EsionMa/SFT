package com.wangzhixuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.SysUploadRecords;
import com.wangzhixuan.model.vo.SysUploadRecordsVo;

/**
 *  SysUploadRecords 表服务层
 * Created by Administrator on 2018/3/26 0026.
 */
public interface ISysUploadRecordsService extends IService<SysUploadRecords>{
    /** 所有记录*/
    void selectDataGrid(PageInfo pageInfo,SysUploadRecordsVo vo);
}
