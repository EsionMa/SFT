package com.wangzhixuan.mapper.collection;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.collection.CollectionItemOption;
import com.wangzhixuan.model.vo.collection.CollectionItemOptionVo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Leslie on 2018/3/1.
 *
 * @author: Leslie
 * @TIME:16:04
 * @Date:2018/3/1 Description:
 */
public interface CollectionItemOptionMapper extends BaseMapper<CollectionItemOption> {
	List<CollectionItemOptionVo> selectData(Pagination page, @Param("vo") CollectionItemOptionVo vo);

	/**
	 * @author: Leslie
	 * @Date 2018/3/14 16:15
	 * @param: [id]
	 * @return java.util.List<com.wangzhixuan.model.vo.CheckedVo>
	 * @throws @Description:
	 *             通过采集项id 得到选项
	 */
	List<CollectionItemOption> getCheckedsById(@Param("id") Long id);
}
