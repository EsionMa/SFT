/**
 * 2017-08-08 12:07:19
 */
package com.wangzhixuan.model.vo.sms;

import java.util.List;
import com.wangzhixuan.model.sms.SmsSend;
import com.wangzhixuan.model.vo.UserVo;

/**
 * @author Esion
 *
 */
public class SmsSendVo extends SmsSend {

	/**
	 * 
	 */
	private static final long serialVersionUID = 202883105486898482L;
	/** 当前页 */
	private Integer page = 1;
	/** 显示条数 */
	private Integer rows = 10;
	List<UserVo> userList;
	private Integer timeType;

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

	public List<UserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

}
