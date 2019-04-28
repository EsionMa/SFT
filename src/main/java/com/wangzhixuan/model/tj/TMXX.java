package com.wangzhixuan.model.tj;

import com.wangzhixuan.model.wenjuan.TimuXuanXiang;

import java.util.List;
import java.util.Map;

public class TMXX  extends TimuXuanXiang{

	private Integer checked;// 选项所填的人数
	private String checkedPercent; //选项所占比例





	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getCheckedPercent() {
		return checkedPercent;
	}

	public void setCheckedPercent(String checkedPercent) {
		this.checkedPercent = checkedPercent;
	}


}