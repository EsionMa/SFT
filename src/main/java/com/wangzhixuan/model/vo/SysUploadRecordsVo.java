package com.wangzhixuan.model.vo;

import com.wangzhixuan.model.SysUploadRecords;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/28 0028.
 */
public class SysUploadRecordsVo extends SysUploadRecords {

    private static final long serialVersionUID = 1095118586509362413L;

    private Date staDate;
    private Date endDate;

    public Date getStaDate() {
        return staDate;
    }

    public void setStaDate(Date staDate) {
        this.staDate = staDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
