package com.wangzhixuan.mapper.huanzhe;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;

public interface ZhuYuanInfoMapper extends BaseMapper<ZhuYuanInfo> {

	/*
	 * List<ZhuYuanInfo> findByCondition(@Param("query") ZyConditionQuery
	 * query);
	 */

	List<ZhuYuanInfo> findByGroup(Pagination page, FangAnZhuYuanGroupVo zyGroup);

	List<ZhuYuanInfoVo> findByCondition(Pagination page, Map<String, Object> params);

	List<Long> insertZyInfoBatch(List<ZhuYuanInfo> zhuYuanInfos);
	Integer export(Map<String, Object> map);
}
