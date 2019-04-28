package com.wangzhixuan.model.scale.instance;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:15:48
 * @Date:2018/3/1 Description:采集单实例
 */
@TableName(value = "hzsf_scale_table_sl")
public class ScaleTableInstance implements Serializable {
	private static final long serialVersionUID = 102596629710337230L;
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 量表名字
	 */
	@TableField(value = "name")
	private String name;
	/**
	 * 量表原型id
	 */
	@TableField(value = "special_id")
	private Long followInstanceId;
	/**
	 * 轮次，第几次量表随访
	 */
	@TableField("turn")
	private Integer turn;
	/**
	 * 问卷实例id
	 */
	@TableField(value = "wjsl_id")
	private Long wjslId;
	@TableField(value = "create_time")
	private Date createTime;
	/**
	 * 统计所用
	 */
	@TableField(value = "hz_id")
	private Long hzId;
	@TableField(value = "hz_name")
	private String hzName;
	@TableField(value = "create_user_name")
	private String createUserName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFollowInstanceId() {
		return followInstanceId;
	}

	public void setFollowInstanceId(Long followInstanceId) {
		this.followInstanceId = followInstanceId;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	public Long getWjslId() {
		return wjslId;
	}

	public void setWjslId(Long wjslId) {
		this.wjslId = wjslId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getHzId() {
		return hzId;
	}

	public void setHzId(Long hzId) {
		this.hzId = hzId;
	}

	public String getHzName() {
		return hzName;
	}

	public void setHzName(String hzName) {
		this.hzName = hzName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
