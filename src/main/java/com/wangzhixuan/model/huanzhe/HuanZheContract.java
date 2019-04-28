package com.wangzhixuan.model.huanzhe;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("hzsf_hz_contact")
public class HuanZheContract implements Serializable {
    /**
     *
     */
    @TableField(exist = false)
    private static final long serialVersionUID = -61865299164993289L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("hz_id")
    private Long hzId;//
    private String gxtype;//患者关系类型（ta是患者的）1父母2子女3亲戚4朋友9其他
    private String sex;// 0女1男2未知
    @TableField("contract_hz_id")
    private String contractHzId;// 如果联系人在患者信息表中，则关联
    private String fixphone;// 固定电话

    private String mobilephone;// 移动电话
    private String addr;// 联系人地址
    private String acceptsf;// 是否接受随访
    @TableField("create_time")
    private Date createTime;// 创建时间
    @TableField("update_time")
    private Date updateTime;// 更新时间
    @TableField("create_user_id")
    private Long createUserId;// 创建用户Id
    @TableField("update_user_id")
    private Long updateUserId;// 更新用户Id
    @TableField("out_id")
    private String outId;

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHzId() {
        return hzId;
    }

    public void setHzId(Long hzId) {
        this.hzId = hzId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContractHzId() {
        return contractHzId;
    }

    public void setContractHzId(String contractHzId) {
        this.contractHzId = contractHzId;
    }

    public String getFixphone() {
        return fixphone;
    }

    public void setFixphone(String fixphone) {
        this.fixphone = fixphone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAcceptsf() {
        return acceptsf;
    }

    public void setAcceptsf(String acceptsf) {
        this.acceptsf = acceptsf;
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

    public String getGxtype() {
        return gxtype;
    }

    public void setGxtype(String gxtype) {
        this.gxtype = gxtype;
    }

}
