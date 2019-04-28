package com.wangzhixuan.model.phone;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2017/8/8. TIME:9:45 Date:2017/8/8. Description:
 */
@TableName("hzsf_phone")
public class Phone implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 6056927515078978292L;
	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("user_id")
	private Long userId;// 操作人id
	@TableField("hz_id")
	private Long hzId;// 患者id
	@TableField("phone_num")
	private String phoneNum;// 患者电话号码
	@TableField("file")
	private String fileLoad;// 录音文件路径
	@TableField("end_time")
	private Date endTime;// 电话结束时间
	@TableField("create_time")
	private Date creTime;// 电话开始时间
	@TableField("sf_id")
	private Long sfId;// 随访对应Id
	@TableField("sf_type")
	private Long sfType;// 对应的业务逻辑，默认为1，随访执行
	@TableField("checked")
	private Integer checked;
	@TableField("type")
	private Integer type;// 电话类型：0.去电1.来电 default:0
	@TableField("hold")
	private Integer hold;// 接通类型：0.未接通1.已接通 default:0

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getHzId() {
		return hzId;
	}

	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFileLoad() {
		return fileLoad;
	}

	public void setFileLoad(String fileLoad) {
		this.fileLoad = fileLoad;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public Long getSfId() {
		return sfId;
	}

	public void setSfId(Long sfId) {
		this.sfId = sfId;
	}

	public Long getSfType() {
		return sfType;
	}

	public void setSfType(Long sfType) {
		this.sfType = sfType;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHold() {
		return hold;
	}

	public void setHold(Integer hold) {
		this.hold = hold;
	}

}
