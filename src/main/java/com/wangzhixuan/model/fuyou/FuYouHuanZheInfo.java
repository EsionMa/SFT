package com.wangzhixuan.model.fuyou;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@TableName("hzsf_fy_hz_info")
public class FuYouHuanZheInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4901620831555494669L;

	@TableId(type = IdType.AUTO)
	private Long id;
	@TableField("hz_id")
	private Long hzId;//患者id
	@TableField("fy_week")
	private Integer fyWeek;//周
	@TableField("fy_day")
	private Integer fyDay;//天
	@TableField("fy_expected_time")
	private Date fyExpectedTime;//预产期
	@TableField("fy_status")
	private Long  fyStatus;//状态：1 终止分娩 2 已分娩 3 未分娩
	@TableField("fy_end_time")
	private Date fyEndTime;//分娩或终止分娩时间

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

	public Integer getFyWeek() {
		return fyWeek;
	}

	public void setFyWeek(Integer fyWeek) {
		this.fyWeek = fyWeek;
	}

	public Integer getFyDay() {
		return fyDay;
	}

	public void setFyDay(Integer fyDay) {
		this.fyDay = fyDay;
	}

	public Date getFyExpectedTime() {
		return fyExpectedTime;
	}

	public void setFyExpectedTime(Date fyExpectedTime) {
		this.fyExpectedTime = fyExpectedTime;
	}

	public Long getFyStatus() {
		return fyStatus;
	}

	public void setFyStatus(Long fyStatus) {
		this.fyStatus = fyStatus;
	}

	public Date getFyEndTime() {
		return fyEndTime;
	}

	public void setFyEndTime(Date fyEndTime) {
		this.fyEndTime = fyEndTime;
	}

}
