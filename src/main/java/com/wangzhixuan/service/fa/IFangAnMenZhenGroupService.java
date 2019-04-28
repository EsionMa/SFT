package com.wangzhixuan.service.fa;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.model.fa.FangAnMenZhenGroup;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;

public interface IFangAnMenZhenGroupService extends IService<FangAnMenZhenGroup> {
	void saveOrUpdate(FangAnMenZhenGroupVo vo) throws SysException;

	/**
	 * 查询单个方案-住院群组条件详细
	 * 
	 * @param faId
	 * @return
	 * @throws SysException
	 */
	FangAnMenZhenGroupVo findByFangAnId(Long faId) throws SysException;
}
