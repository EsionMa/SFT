package com.wangzhixuan.model.vo.admin;

/**
 * Created by Leslie on 2018/1/31.
 *
 * @author: Leslie
 * @TIME:17:40
 * @Date:2018/1/31 Description: 专门用来模糊查询
 */
public class CheckedQuery {
    private Integer page = 1;
    private Integer rows = 25;
    private String hzName;
    private String dcUserName;
    private String staDate;
    private String endDate;
    private Long faId;
    private Integer faType;
    private Long deptId;
    private Long exeUserId;
    private String timeType;
    private String status;

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

    public String getHzName() {
        return hzName;
    }

    public void setHzName(String hzName) {
        this.hzName = hzName;
    }

    public String getDcUserName() {
        return dcUserName;
    }

    public void setDcUserName(String dcUserName) {
        this.dcUserName = dcUserName;
    }

    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getFaId() {
        return faId;
    }

    public void setFaId(Long faId) {
        this.faId = faId;
    }

    public Integer getFaType() {
        return faType;
    }

    public void setFaType(Integer faType) {
        this.faType = faType;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getExeUserId() {
        return exeUserId;
    }

    public void setExeUserId(Long exeUserId) {
        this.exeUserId = exeUserId;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
