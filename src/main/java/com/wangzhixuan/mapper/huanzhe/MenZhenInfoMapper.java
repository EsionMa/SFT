/**
 * 2017-12-18 18:57:19
 */
package com.wangzhixuan.mapper.huanzhe;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;

/**
 * @author Esion
 *
 */
public interface MenZhenInfoMapper extends BaseMapper<MenZhenInfo> {
	List<MenZhenInfo> findByCondition(Pagination page, Map<String, Object> params);

	List<MenZhenInfo> findByGroup(Pagination page, FangAnMenZhenGroupVo zyGroup);
}
