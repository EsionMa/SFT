package com.wangzhixuan.model.vo.collection;

import com.wangzhixuan.model.collection.CollectionItemOption;

/**
 * Created by Leslie on 2018/3/2.
 *
 * @author: Leslie
 * @TIME:15:38
 * @Date:2018/3/2 Description:
 */
public class CollectionItemOptionVo extends CollectionItemOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5101143948639731560L;

	private Integer page = 1;
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
