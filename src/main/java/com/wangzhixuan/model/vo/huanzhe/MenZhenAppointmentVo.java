package com.wangzhixuan.model.vo.huanzhe;

import com.wangzhixuan.model.huanzhe.MenZhenAppointment;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class MenZhenAppointmentVo extends MenZhenAppointment{

    private static final long serialVersionUID = 4342426679473594757L;

    private Integer page = 1;
    private Integer rows;
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
