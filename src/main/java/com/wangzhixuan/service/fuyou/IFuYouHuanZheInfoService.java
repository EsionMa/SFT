package com.wangzhixuan.service.fuyou;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.fuyou.FuYouHuanZheInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public interface IFuYouHuanZheInfoService extends IService<FuYouHuanZheInfo> {

	/**
	 * 保存或更新
	 * 
	 * @param vo
	 * @throws SysException
	 */
	void saveOrUpdate(FuYouHuanZheInfoVo vo) throws SysException;

	/**
	 * 数据分页
	 * 
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	PageInfo selectedData(FuYouHuanZheInfoVo vo) throws SysException;


	/**
	 * 根据患者ID删除
	 * 
	 * @param hzId
	 * @throws SysException
	 */
	void deleteByHzId(Long hzId) throws SysException;

	/**
	 * 根据患者ID查询
	 * 
	 * @param hzId
	 * @return
	 * @throws SysException
	 */
	List<FuYouHuanZheInfoVo> selectByHzId(Long hzId) throws SysException;

}
