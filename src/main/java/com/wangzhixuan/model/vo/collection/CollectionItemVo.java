package com.wangzhixuan.model.vo.collection;

import java.util.List;

import com.wangzhixuan.model.collection.CollectionItem;
import com.wangzhixuan.model.collection.CollectionItemOption;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:55
 * @Date:2018/3/1 Description:
 */
public class CollectionItemVo extends CollectionItem {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6269799368515117424L;
	
	private Integer page = 1;
	private Integer rows = 10;
	private String parentCode;
	private Integer showItem;
	private List<CollectionItemOption> options;
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
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public Integer getShowItem() {
		return showItem;
	}
	public void setShowItem(Integer showItem) {
		this.showItem = showItem;
	}
	public List<CollectionItemOption> getOptions() {
		return options;
	}
	public void setOptions(List<CollectionItemOption> options) {
		this.options = options;
	}


}
