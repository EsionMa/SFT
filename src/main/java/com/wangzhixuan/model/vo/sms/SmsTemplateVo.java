package com.wangzhixuan.model.vo.sms;

import com.wangzhixuan.model.sms.SmsTemplate;

/**
 * Created by Administrator on 2017/12/13 0013.
 */
public class SmsTemplateVo extends SmsTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4359031220113676150L;

	/** 当前页 */
	private Integer page = 1;

	/** 显示条数 */
	private Integer rows = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
