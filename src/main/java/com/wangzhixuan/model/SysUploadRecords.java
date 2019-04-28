package com.wangzhixuan.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 * @date 2018/3/26 0026
 */
@TableName("hzsf_upload_records")
public class SysUploadRecords implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 4944657226654539643L;

    /** 主键id */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 文件名*/
    private String fileName;
    /** 上传者*/
    private String userName;
    /** 上传时间*/
    private Date uploadTime;
    /** 上传数量*/
    private Integer amount;
    /** 成功数量*/
    private Integer succeed;
    /**解析状态 */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSucceed() {
        return succeed;
    }

    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
