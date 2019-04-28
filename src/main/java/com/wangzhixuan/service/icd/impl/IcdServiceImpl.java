package com.wangzhixuan.service.icd.impl;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.icd.IcdMapper;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.vo.icd.IcdVo;
import com.wangzhixuan.service.icd.IIcdService;

@Service
public class IcdServiceImpl extends ServiceImpl<IcdMapper, Icd> implements IIcdService {
	@Autowired
	private IcdMapper icdMapper;
	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * com.wangzhixuan.service.icd.IIcdService#getIcdList(com.wangzhixuan.model.
	 * vo.icd.IcdVo)
	 */
	@Override
	public List<IcdVo> getIcdList(IcdVo vo) throws SysException {
		List<IcdVo> icdVos = new ArrayList<>();
		EntityWrapper<Icd> wrapper = new EntityWrapper<>();
		List<Icd> icds = selectList(wrapper);
		for (Icd icd : icds) {
			IcdVo icdVo = BeanUtils.copy(icd, IcdVo.class);
			icdVos.add(icdVo);
		}
		return icdVos;
	}

    @Override
    public List<Icd> getIcdListByNode(Long nodeId) {
		if (nodeId==null){
			throw new SysException("nodeId节点为空");
		}
        return icdMapper.getIcdListByNode(nodeId);
    }

    @Override
    public void addNodeAndIcd(List<Icd> icds, Long id) {
        if (id==null){
        	throw new SysException("nodeId节点为空");
		}
		if (icds==null||icds.size()<1){
        	throw new SysException("icd编码为空");
		}
		Map<String,Object> map=new HashedMap();
		map.put("nodeId",id);
		for (Icd item:icds){
			map.put("code",item.getCode());
			icdMapper.insertNodeAndCode(map);
		}

    }
    //删除某个节点的icd
	@Override
	public void deleteNodeAndIcd(Long id) {
		if (id==null){
			throw new SysException("节点nodeId为空");
		}
		icdMapper.deleteNodeAndIcd(id);
	}

	@Override
	@Cacheable(value = "icdCache",key = "#id")
	public Icd selectById(Serializable id) {
		if (id==null){
			throw new SysException("编码不能为空");
		}
		return icdMapper.selectById(id);
	}

}
