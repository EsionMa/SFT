package com.wangzhixuan.service.sms;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.sms.SmsTemplate;
import com.wangzhixuan.model.vo.sms.SmsTemplateVo;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13 0013.
 */
public interface ISmsTemplateService extends IService<SmsTemplate> {

	/**
	 * 保存
	 * @param vo
	 * @param user
	 * @throws SysException
	 */
	void save(SmsTemplateVo vo, ShiroUser user) throws SysException;

	/**
	 * 修改
	 * @param vo
	 * @param user
	 * @throws SysException
	 */
	void update(SmsTemplateVo vo, ShiroUser user) throws SysException;

	/**
	 * 查询（分页）
	 *
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	PageInfo selectDataGrid(SmsTemplateVo vo) throws SysException;

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws SysException
	 */
	void deleteMsgs(Long id) throws SysException;

	/**
	 * 通过id查询模板信息
	 * 
	 * @param id
	 * @return
	 * @throws SysException
	 */
	SmsTemplateVo findById(Long id) throws SysException;

	/***
	 * 查询模板类型
	 * 
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	List<SmsTemplateVo> selectTempByType(SmsTemplateVo vo) throws SysException;

}
