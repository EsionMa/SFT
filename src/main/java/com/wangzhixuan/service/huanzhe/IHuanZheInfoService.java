package com.wangzhixuan.service.huanzhe;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.vo.huanzhe.HuanZheInfoVo;

public interface IHuanZheInfoService extends IService<HuanZheInfo> {
	/**
	 *  查询患者信息
	 * @param vo
	 * @return
	 */
	PageInfo selectDataGrid(HuanZheInfoVo vo);

	/**
	 * 保存或更新
	 * 
	 * @param vo
	 * @param user
	 * @return
	 * @throws SysException
	 */
	HuanZheInfo saveOrUpdate(HuanZheInfoVo vo, ShiroUser user) throws SysException;

	/**
	 * 通过id查询患者信息
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	HuanZheInfoVo selectDetailById(HuanZheInfoVo vo) throws SysException;

	/**
	 * 通过患者号查询患者信息
	 * @param hzNo
	 * @return
	 * @throws SysException
	 */
	HuanZheInfo selectByHzNo(String hzNo) throws SysException;

	/**
	 *  查询患者号和患者ID是否存在
	 * @param hzNo
	 * @param hzId
	 * @return
	 * @throws SysException
	 */
	HuanZheInfo selectByHzNoAndHzId(String hzNo, Long hzId) throws SysException;

	/**
	 * 给患者添加标记
	 * @param hzId
	 * @param tagCodes
	 * @param user
	 * @throws SysException
	 */
	void addTag(Long hzId, List<String> tagCodes, ShiroUser user) throws SysException;

	/**
	 *  通过id删除患者
	 * @param id
	 * @throws SysException
	 */
	void delete(Long id) throws SysException;

	/**
	 * 	查询患者联系人电话
	 * @param hzNo
	 * @param hzId
	 * @return
	 * @throws SysException
	 */
	public List<Map<String, String>> queryHzContractMap(String hzNo, Long hzId) throws SysException;

	/**
	 *	查询患者联系人信息
	 * @param hzId
	 * @return
	 * @throws SysException
	 */
	public List<HuanZheContract> queryHzContract(Long hzId) throws SysException;

	/**
	 *
	 * @param pageInfo
	 * @return
	 *
	 * 接口未使用
	 */
	PageInfo findByCondition(PageInfo pageInfo);

	/**
	 *  通过电话号查出患者名和其id
	 * @param num
	 * @return
	 */
	Map<String, Object> findByphone(String num);

	/**
	 *  查询患者 医保、电话、证件
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	HuanZheInfo selectByYbNoOrPhone(HuanZheInfoVo vo) throws SysException;

	/**
	 * @author: Leslie
	 * @Date 2018/2/1 15:19
	 * @param: [map]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @throws @Description:返回患者的住院信息或者门诊信息等相关信息
	 */
	Map<String, Object> getHosOrOut(Map<String, Object> map);

	/**
	 * @author: Leslie
	 * @Date 2018/2/27 18:11
	 * @param: [map]
	 * @return java.lang.Integer
	 * @throws @Description:根据时间段得出患者生日
	 */
	Integer getBirthCount(Map<String, Object> map);


}
