package com.wangzhixuan.model.vo.sms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wangzhixuan.model.SysUser;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
public class SmsUserVo extends SysUser {


    private static final long serialVersionUID = 4448911022213451656L;
    /** 当前页 */
    private Integer page = 1;

    /** 显示条数 */
    private  Integer rows = 5;
    @TableField(exist = false)
    private Long deptId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
