package com.wangzhixuan.model.vo.collection;

import java.util.List;
import com.wangzhixuan.model.collection.CollectionTable;
import com.wangzhixuan.model.vo.collection.instance.CollectionItemInstanceVo;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:55
 * @Date:2018/3/1 Description:
 */
public class CollectionTableVo extends CollectionTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2461107200780109670L;

	private Integer page = 1;
	private Integer rows = 10;
	private String parentCode;
	/** 采集项数 */
	private List<CollectionItemVo> itemVos;
	/** 采集项实例数 */
	private List<CollectionItemInstanceVo> itemSlVos;

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

	public List<CollectionItemVo> getItemVos() {
		return itemVos;
	}

	public void setItemVos(List<CollectionItemVo> itemVos) {
		this.itemVos = itemVos;
	}

	public List<CollectionItemInstanceVo> getItemSlVos() {
		return itemSlVos;
	}

	public void setItemSlVos(List<CollectionItemInstanceVo> itemSlVos) {
		this.itemSlVos = itemSlVos;
	}

}
