package com.wangzhixuan.model.statics;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:15:16
 * @Date:2017/12/25 Description:将随访类型变为可以维护后，如何将跑批也变为可以维护的
 */
@TableName("hzsf_cy_statics")
public class OutStatics implements Serializable{
    private static final long serialVersionUID = -7388646896979740817L;
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 离院人数
     */
    @TableField(value = "leave_count")
    private Integer leaveCount;
    /**
     * 所有随访数
     */
    @TableField(value = "all_visit_count")
    private Integer allVisitCount;
    /**
     * 随访率
     */
    @TableField(value = "all_visit_percent")
    private String allVisitPercent;
    /**
     * 成功随访率
     */
    @TableField(value = "success_visit_percent")
    private String successPercent;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 科室id
     */
    @TableField(value = "dept_id")
    private Long deptId;
    /**
     * 科室名
     */
    @TableField(value = "dept_name")
    private String deptName;
    /**
     * 出院时间
     */
    @TableField(value = "cytime")
    private Date cyTime;
    /**
     * 待随访数+百分比
     */
    @TableField(value = "wait_visit_count")
    private String waitVisit;
    /**
     * 已随访+百分比
     */
    @TableField(value = "already_visit_count")
    private String alreadyVisit;
    /**
     * 死亡患者+百分比
     */
    @TableField(value = "dead_count")
    private String deadCount;
    /**
     * 预约随访+百分比
     */
    @TableField(value = "appointment_count")
    private String appointmentVisit;
    /**
     * 空号错号+百分比
     */
    @TableField(value = "error_num_count")
    private String errorNum;
    /**
     * 无人接听+百分比
     */
    @TableField(value = "no_response_count")
    private String noResponse;
    /**
     * 拒绝随访+百分比
     */
    @TableField(value = "refuse_count")
    private String refuseCount;
    @TableField(value = "other_count")
    private String otherCount;

    public String getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(String otherCount) {
        this.otherCount = otherCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Integer leaveCount) {
        this.leaveCount = leaveCount;
    }

    public Integer getAllVisitCount() {
        return allVisitCount;
    }

    public void setAllVisitCount(Integer allVisitCount) {
        this.allVisitCount = allVisitCount;
    }

    public String getAllVisitPercent() {
        return allVisitPercent;
    }

    public void setAllVisitPercent(String allVisitPercent) {
        this.allVisitPercent = allVisitPercent;
    }

    public String getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(String successPercent) {
        this.successPercent = successPercent;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getCyTime() {
        return cyTime;
    }

    public void setCyTime(Date cyTime) {
        this.cyTime = cyTime;
    }

    public String getWaitVisit() {
        return waitVisit;
    }

    public void setWaitVisit(String waitVisit) {
        this.waitVisit = waitVisit;
    }

    public String getAlreadyVisit() {
        return alreadyVisit;
    }

    public void setAlreadyVisit(String alreadyVisit) {
        this.alreadyVisit = alreadyVisit;
    }

    public String getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(String deadCount) {
        this.deadCount = deadCount;
    }

    public String getAppointmentVisit() {
        return appointmentVisit;
    }

    public void setAppointmentVisit(String appointmentVisit) {
        this.appointmentVisit = appointmentVisit;
    }

    public String getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(String errorNum) {
        this.errorNum = errorNum;
    }

    public String getNoResponse() {
        return noResponse;
    }

    public void setNoResponse(String noResponse) {
        this.noResponse = noResponse;
    }

    public String getRefuseCount() {
        return refuseCount;
    }

    public void setRefuseCount(String refuseCount) {
        this.refuseCount = refuseCount;
    }
}
