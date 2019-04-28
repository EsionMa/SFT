package com.wangzhixuan.service.tj;

import java.util.Date;

public class WenJuanTongJiBo {
    private Long wjId;
    private String dateType;//1调查时间，2出院时间
    private Date dateSt;
    private Date dateEd;
    private Long deptId;
    private Long DoctorId;

    public Long getWjId() {
        return wjId;
    }

    public void setWjId(Long wjId) {
        this.wjId = wjId;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Date getDateSt() {
        return dateSt;
    }

    public void setDateSt(Date dateSt) {
        this.dateSt = dateSt;
    }

    public Date getDateEd() {
        return dateEd;
    }

    public void setDateEd(Date dateEd) {
        this.dateEd = dateEd;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Long doctorId) {
        DoctorId = doctorId;
    }
}
