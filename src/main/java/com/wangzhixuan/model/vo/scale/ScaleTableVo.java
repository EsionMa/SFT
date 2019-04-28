package com.wangzhixuan.model.vo.scale;

import java.util.List;

import com.wangzhixuan.model.scale.ScaleTable;
import com.wangzhixuan.model.vo.collection.CollectionTableVo;

/**
 * Created by Leslie on 2018/3/2.
 *
 * @author: Leslie
 * @TIME:10:18
 * @Date:2018/3/2 Description:
 */
public class ScaleTableVo extends ScaleTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 509481485672847104L;
	
	private Integer page = 1;
	private Integer rows = 10;
	private String parentCode;
	private List<CollectionTableVo> tableVos;

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

	public List<CollectionTableVo> getTableVos() {
		return tableVos;
	}

	public void setTableVos(List<CollectionTableVo> tableVos) {
		this.tableVos = tableVos;
	}

}
