/**
 * 2017-08-09 18:05:15
 */
package com.wangzhixuan.service.sms;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.sms.SmsRcv;
import com.wangzhixuan.model.vo.sms.SmsRcvVo;

/**
 * @author Esion
 *
 */
public interface ISmsRcvService extends IService<SmsRcv>{
	/**
	 * 保存
	 * 
	 * @param vo
	 * @throws SysException
	 */
	boolean saveMessage(SmsRcvVo vo) throws SysException;

	/**
	 * 查询（分页）
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	PageInfo selectDataGrid(SmsRcvVo vo) throws SysException;

	/**
	 * 删除（可批量）
	 * 
	 * @param ids
	 * @throws SysException
	 */
	void deleteMsgs(Long ids) throws SysException;
}
