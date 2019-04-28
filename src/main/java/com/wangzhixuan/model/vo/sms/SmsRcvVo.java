/**
 * 2017-08-09 17:42:17
 */
package com.wangzhixuan.model.vo.sms;

import com.wangzhixuan.model.sms.SmsRcv;

/**
 * @author Esion
 *
 */
public class SmsRcvVo extends SmsRcv {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265403235879563716L;
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
