package com.wangzhixuan.model.wenjuan;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2018/1/26.
 *
 * @author: Leslie
 * @TIME:16:14
 * @Date:2018/1/26 Description: 问卷实例中间表
 */
@TableName(value = "hzsf_wjsl_item_wj")
public class WenJuanShiLiItem  implements Serializable{
    private static final long serialVersionUID = 5995562145764933162L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 问卷id
     */
    @TableField(value = "wj_id")
    private Long wjId;
    /**
     * 问卷实例id
     */
    @TableField(value = "wjsl_id")
    private Long wjslId;
    /**
     * 科室id
     */
    @TableField(value = "dept_id")
    private  Long deptId;
    /**
     * 科室名
     */
    @TableField(value = "dept_name")
    private String deptName;
    /**
     * 执行人姓名
     */
    @TableField(value = "exe_user_id")
    private  Long exeUserId;
    /**
     * 所得分数
     */
    private  Double score;
    /**
     * 患者名
     */
    @TableField(value = "hz_name")
    private String hzName;
    /**
     * 患者id
     */
    @TableField("hz_id")
    private Long hzId;
    /**
     * 问卷主题
     */
    private String wjzt;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "fa_type")
    private Integer faType;

    public Integer getFaType() {
        return faType;
    }

    public void setFaType(Integer faType) {
        this.faType = faType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWjId() {
        return wjId;
    }

    public void setWjId(Long wjId) {
        this.wjId = wjId;
    }

    public Long getWjslId() {
        return wjslId;
    }

    public void setWjslId(Long wjslId) {
        this.wjslId = wjslId;
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

    public Long getExeUserId() {
        return exeUserId;
    }

    public void setExeUserId(Long exeUserId) {
        this.exeUserId = exeUserId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getHzName() {
        return hzName;
    }

    public void setHzName(String hzName) {
        this.hzName = hzName;
    }

    public Long getHzId() {
        return hzId;
    }

    public void setHzId(Long hzId) {
        this.hzId = hzId;
    }

    public String getWjzt() {
        return wjzt;
    }

    public void setWjzt(String wjzt) {
        this.wjzt = wjzt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
