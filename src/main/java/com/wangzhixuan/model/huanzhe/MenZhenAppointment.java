package com.wangzhixuan.model.huanzhe;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
@TableName("hzsf_mz_appointment")
public class MenZhenAppointment implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -7832717412461174389L;
    private Long id;//
    @TableField(value = "hz_name")
    private String hzName;
    private String sex;
    private Long age;
    private String phone;
    /*预约日期*/
    @TableField(value = "appointment_date")
    private Date appointDate;
    /*预约时间*/
    @TableField(value = "appointment_time")
    private String appointTime;
    /*科室id*/
    @TableField(value = "dept_id")
    private Long deptId;
    /*医生id*/
    @TableField(value = "doctor_id")
    private Long doctorId;
    /*应约状态*/
    private String status;
    /** 创建时间 */
    @TableField("create_time")
    private Date createTime;
    /** 创建者id */
    @TableField("create_user_id")
    private Long createUserId;
    /** 更新时间 */
    @TableField("update_time")
    private Date updateTime;
    /** 更新者id */
    @TableField("update_user_id")
    private Long updateUserId;
    /*备注*/
    private String remarks;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHzName() {
        return hzName;
    }

    public void setHzName(String hzName) {
        this.hzName = hzName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
