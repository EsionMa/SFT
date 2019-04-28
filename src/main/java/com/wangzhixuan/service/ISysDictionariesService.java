package com.wangzhixuan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.model.SysDictionaries;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface ISysDictionariesService extends IService<SysDictionaries> {
	/**
	 *@author: Leslie
	 *@Date 2018/3/19 11:24
	 *@param: [sysDictionaries]
	 *@return void
	 *@throws
	 *@Description: 新增一个字典表
	 */
	SysDictionaries save(SysDictionaries sysDictionaries)throws SysException;
	/**
	 *@author: Leslie
	 *@Date 2018/3/19 11:24
	 *@param: []
	 *@return java.util.List<com.wangzhixuan.model.SysDictionaries>
	 *@throws
	 *@Description: 查询所有字典表
	 */
	List<SysDictionaries> selectTreeGrid();
	/**
	 *@author: Leslie
	 *@Date 2018/3/19 11:24
	 *@param: [parentCode]
	 *@return java.util.List<com.wangzhixuan.model.SysDictionaries>
	 *@throws
	 *@Description: 通过父code列出子code
	 */
	List<SysDictionaries> listByParentCode(String parentCode);
	/**
	 *@author: Leslie
	 *@Date 2018/3/19 11:25
	 *@param: [code]
	 *@return com.wangzhixuan.model.SysDictionaries
	 *@throws
	 *@Description: 通过code得到字典表
	 */
	SysDictionaries getByCode(String code);
}