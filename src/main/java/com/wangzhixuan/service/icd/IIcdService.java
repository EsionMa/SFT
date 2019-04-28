package com.wangzhixuan.service.icd;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.icd.IcdVo;

import java.io.Serializable;
import java.util.List;

public interface IIcdService extends IService<Icd> {
	/**
	 * ICD列表
	 * 
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	List<IcdVo> getIcdList(IcdVo vo) throws SysException;

	/**
	 * 通过知识库nodeId 得到 icd
	 * @param nodeId
	 * @return
	 */
    List<Icd> getIcdListByNode(Long nodeId);

    void addNodeAndIcd(List<Icd> icds, Long id);

	void deleteNodeAndIcd(Long id);

	Icd selectById(Serializable id);
}
