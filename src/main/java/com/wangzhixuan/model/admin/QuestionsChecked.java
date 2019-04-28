package com.wangzhixuan.model.admin;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2018/1/29.
 *
 * @author: Leslie
 * @TIME:16:21
 * @Date:2018/1/29 Description:抽查实例
 */
@TableName(value = "hzsf_checked_sl")
public class QuestionsChecked implements Serializable{
    private static final long serialVersionUID = 7727584381079197502L;
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 问卷实例Id
     */
    @TableField(value = "wjsl_id")
    private Long wjslId;
    /**
     * 患者姓名
     */
    @TableField(value = "hz_name")
    private String hzName;
    /**
     * 调查时间
     */
    @TableField(value = "dc_time")
    private Date dcTime;
    /**
     * 调查人
     */
    @TableField(value = "dc_user_id")
    private Long dcUserId;
    /**
     * 调查人名
     */
    @TableField(value = "dc_user_name")
    private  String dcUserName;
    /**
     * 抽查问卷id
     */
    @TableField(value = "wj_id")
    private Long wjId;
    /**
     * 问卷名
     */
    @TableField(value = "wj_name")
    private String wjName;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 创建人id
     */
    @TableField(value = "create_user_id")
    private Long createUserId;
    /**
     * 修改人id
     */
    @TableField(value = "update_user_id")
    private Long updateUserId;
    /**
     * 出院时间
     */
    @TableField(value = "other_time")
    private Date otherTime;
    /**
     * 就诊科室id
     */
    @TableField(value = "dept_id")
    private Long deptId;
    /**
     * 就诊科室名
     */
    @TableField(value = "dept_name")
    private String deptName;
    /**
     * 患者id
     */
    @TableField(value = "hz_id")
    private Long hzId;
    /**
     * 实例方案id
     * @return
     */
    @TableField(value = "fa_id")
    private Long faId;
    /**
     * 抽查状态
     */
    private String status;
    /**
     * 方案类型
     */
    @TableField(value = "fa_type")
    private Integer faType;
    /**
     * 抽查人
     */
    @TableField(value = "checked_user_name")
    private String checkedName;
    /**
     * 就诊医生名
     */
    @TableField(value = "doctor_name")
    private String doctorName;
    /**
     * 患者来源
     */
    @TableField(value = "hzly")
    private Integer hzly;
    /**
     * 抽查所做的问卷id
     */
    @TableField("item_id")
    private Long itemId;
    /**
     * 抽查内容
     */
    @TableField(value = "content")
    private String content;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHzly() {
        return hzly;
    }

    public void setHzly(Integer hzly) {
        this.hzly = hzly;
    }

    public String getCheckedName() {
        return checkedName;
    }

    public void setCheckedName(String checkedName) {
        this.checkedName = checkedName;
    }

    public Integer getFaType() {
        return faType;
    }

    public void setFaType(Integer faType) {
        this.faType = faType;
    }

    public String getDcUserName() {
        return dcUserName;
    }

    public void setDcUserName(String dcUserName) {
        this.dcUserName = dcUserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFaId() {
        return faId;
    }

    public void setFaId(Long faId) {
        this.faId = faId;
    }

    public Long getHzId() {
        return hzId;
    }

    public void setHzId(Long hzId) {
        this.hzId = hzId;
    }

    public String getWjName() {
        return wjName;
    }

    public void setWjName(String wjName) {
        this.wjName = wjName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWjslId() {
        return wjslId;
    }

    public void setWjslId(Long wjslId) {
        this.wjslId = wjslId;
    }

    public String getHzName() {
        return hzName;
    }

    public void setHzName(String hzName) {
        this.hzName = hzName;
    }

    public Date getDcTime() {
        return dcTime;
    }

    public void setDcTime(Date dcTime) {
        this.dcTime = dcTime;
    }

    public Long getDcUserId() {
        return dcUserId;
    }

    public void setDcUserId(Long dcUserId) {
        this.dcUserId = dcUserId;
    }

    public Long getWjId() {
        return wjId;
    }

    public void setWjId(Long wjId) {
        this.wjId = wjId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getOtherTime() {
        return otherTime;
    }

    public void setOtherTime(Date otherTime) {
        this.otherTime = otherTime;
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

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
