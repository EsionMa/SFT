package com.wangzhixuan.model.icd;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_icd_10")
public class Icd implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 8300813398450988118L;
	/** 编码 */
	@TableId
	private String code;
	/** 中文名称 */
	private String name;
	/** 简拼码 */
	private String jpm;

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            要设置的 code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return jpm
	 */
	public String getJpm() {
		return jpm;
	}

	/**
	 * @param jpm
	 *            要设置的 jpm
	 */
	public void setJpm(String jpm) {
		this.jpm = jpm;
	}

}
