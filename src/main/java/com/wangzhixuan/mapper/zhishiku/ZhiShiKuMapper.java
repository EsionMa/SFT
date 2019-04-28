package com.wangzhixuan.mapper.zhishiku;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKu;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;

public interface ZhiShiKuMapper extends BaseMapper<ZhiShiKu> {

	void updateZhishiku(ZhiShiKuVo zhiShiKuVo);

	/**
	 * @Author: Leslie
	 * @Description:通过父id查找子类
	 * @Date 2017/8/20 15:53
	 */
	List<ZhiShiKuNode> findByPId(@Param("parentId") Long parentId);

	Long findPIdByNodeId(@Param("id") Long id);

	Long findPIdByDeptId(@Param("deptId") Long deptId);


	/**
	 * 查询icd 返回知识库相关NodeIds
	 * @param map
	 * @return
	 */
	List<Long> queryByIcd(Map<String, Object> map);

	List<ZhiShiKuVo> queryByNodeId4DataGrid(Pagination page, Map<String, Object> map);
}
