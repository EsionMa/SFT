package com.wangzhixuan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wangzhixuan.commons.utils.StringUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.mapper.SysOrganizationMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.service.ISysOrganizationService;

/**
 *
 * Organization 表数据服务层接口实现类
 *
 */
@Service
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization>
		implements ISysOrganizationService {

	@Autowired
	private SysOrganizationMapper organizationMapper;
	@Autowired
	private CacheManager cacheManager;

	@Override
	public List<Tree> selectTree() {
		List<SysOrganization> organizationList = selectTreeGrid();
		List<Tree> trees = new ArrayList<Tree>();
		if (organizationList != null) {
			for (SysOrganization organization : organizationList) {
				Tree tree = new Tree();
				tree.setId(organization.getId());
				tree.setText(organization.getName());
				tree.setIconCls(organization.getIcon());
				tree.setPid(organization.getPid());
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<SysOrganization> selectTreeGrid() {
		EntityWrapper<SysOrganization> wrapper = new EntityWrapper<SysOrganization>();
		wrapper.orderBy("seq");
		return organizationMapper.selectList(wrapper);
	}

	@Override
	public List<SysOrganization> getDepts(Long parentId, String type) throws SysException {
		List<SysOrganization> sysOrganizations = organizationMapper.queryByParentId(parentId, type);
		if (sysOrganizations != null && sysOrganizations.size() > 0) {
			for (int i = 0; i < sysOrganizations.size(); i++) {
				SysOrganization sysOrganization = sysOrganizations.get(i);
				sysOrganization.setChildren(getDepts(sysOrganization.getId(), type));
			}
		} else {
			return null;
		}
		return sysOrganizations;
	}

    @Override
    public List<SysOrganization> getAllDepts() {
		EntityWrapper<SysOrganization> wrapper=new EntityWrapper<>();
		wrapper.where("pid is Null");
        return selectList(wrapper);
    }

    @Override
    public List<SysOrganization> getDeparts(Map<String, Object> map) {
		if (map==null||map.get("type")==null||"".equals(map.get("type"))){
			throw new SysException("科室类型不能为空");
		}
		String type=map.get("type").toString();
        return getDeptsByType(type);
    }

	@Override
	@Cacheable(value = "departsCache",key ="#type")
	public List<SysOrganization> getDeptsByType(String type) {
		if (StringUtils.isBlank(type)){
			throw new SysException("科室类型不能为空");
		}
		return organizationMapper.selectByType(type);
	}

	@Override
	public void addDept(SysOrganization organization) {
		insert(organization);
		Cache cache=cacheManager.getCache("departsCache");
		if (cache!=null&&cache.get(organization.getType())!=null){
			cache.flush();
		}
	}

}