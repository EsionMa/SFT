package com.wangzhixuan.service.fa;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.model.fa.FangAnZhuYuanGroup;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;

public interface IFangAnZhuYuanGroupService extends IService<FangAnZhuYuanGroup> {
	void saveOrUpdate(FangAnZhuYuanGroupVo vo) throws SysException;

	/**
	 * 查询单个方案-住院群组条件详细
	 * 
	 * @param faId
	 * @return
	 * @throws SysException
	 */
	FangAnZhuYuanGroupVo findByFangAnId(Long faId) throws SysException;
}
