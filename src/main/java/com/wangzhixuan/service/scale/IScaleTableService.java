package com.wangzhixuan.service.scale;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.scale.ScaleTable;
import com.wangzhixuan.model.vo.scale.ScaleTableVo;

/**
 * 
 * @author Esion
 *
 */
public interface IScaleTableService extends IService<ScaleTable> {
	/**
	 * 分页+搜索（showItem是否显示选项 1是0否）
	 * 
	 * @param vo
	 * @return
	 */
	PageInfo selectDataGrid(ScaleTableVo vo);

	/**
	 * @author: Leslie
	 * @Date 2018/3/2 10:50
	 * @param: [specialTableVo,
	 *             user]
	 * @return void
	 * @throws @Description:新增或者修改表格
	 */
	String addOrUpdate(ScaleTableVo vo, ShiroUser user);

	/**
	 * 获取单个采集表详情
	 * 
	 * @param vo
	 * @return
	 */
	ScaleTableVo getDetail(ScaleTableVo vo);

	/**
	 * 刪除单个采集表（逻辑删除）
	 * 
	 * @param vo
	 * @param user
	 */
	void delete(ScaleTableVo vo, ShiroUser user);
}
