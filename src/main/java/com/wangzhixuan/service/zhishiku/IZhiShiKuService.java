package com.wangzhixuan.service.zhishiku;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuNodeVo;
import com.wangzhixuan.model.vo.zhishiku.ZhiShiKuVo;
import com.wangzhixuan.model.zhishiku.ZhiShiKu;

public interface IZhiShiKuService extends IService<ZhiShiKu> {




	/**
	 * 根据nodeId查询知识库（分页+搜索）
	 *
	 * @param pageInfo
	 * @param vo
	 * @return
	 */
	PageInfo queryByNodeId4DataGrid(PageInfo pageInfo, ZhiShiKuVo vo);

	/**
	 * 根据知nodeId查询知识库
	 *
	 * @param nodeId
	 * @return
	 */
	List<ZhiShiKuVo> queryByNodeId(Long nodeId);

	/**
	 *  保存
	 * @param vo
	 * @param user
	 */
	void save(ZhiShiKuVo vo, ShiroUser user);

	/**
	 *  更新
	 * @param vo
	 * @param user
	 */
	void update(ZhiShiKuVo vo, ShiroUser user);

	/**
	 *  删除
	 * @param map
	 */
	void deleteZhi(Map<String,Object> map);
	/* PageInfo findBydeptId(PageInfo pageInfo,ZhiShiKuNodeVo vo); */

	Long findPIdByNodeId(Long nodeId);

}
