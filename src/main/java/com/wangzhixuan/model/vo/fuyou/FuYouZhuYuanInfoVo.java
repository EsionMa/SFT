package com.wangzhixuan.model.vo.fuyou;

import com.wangzhixuan.model.fuyou.FuYouZhuYuanInfo;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public class FuYouZhuYuanInfoVo extends FuYouZhuYuanInfo {
    private static final long serialVersionUID = -6638356063632604461L;
    private Integer page = 1;
    private Integer rows = 20;

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
