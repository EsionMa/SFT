/**
 * 2017-08-08 12:13:10
 */
package com.wangzhixuan.service.sms;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.sms.SmsSend;
import com.wangzhixuan.model.vo.fa.FangAnDingYiItemVo;
import com.wangzhixuan.model.vo.sms.SmsSendVo;

/**
 * @author Esion
 *
 */
public interface ISmsSendService extends IService<SmsSend> {
	/**
	 * 发送及时短信
	 * 
	 * @param vo
	 * @throws Exception
	 */
	void sendInstantMsg(SmsSendVo vo) throws Exception;

	/**
	 * 生成待发送短信
	 * 
	 * @param vo
	 * @param fangAnDingYiItemVo
	 * @param infos
	 * @throws Exception
	 */
	void generatePlannedMsg(SmsSendVo vo, FangAnDingYiItemVo fangAnDingYiItemVo, List<Long> infos) throws Exception;

	/**
	 * 发送待发送短信
	 * 
	 * @param vo
	 * @throws Exception
	 */
	void sendPlannedMsg(SmsSendVo vo) throws Exception;

	/**
	 * 查询（分页）（发送）
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	PageInfo selectDataGrid(SmsSendVo vo) throws SysException;

	/**
	 * 删除（可批量）（发送）
	 * 
	 * @param ids
	 * @throws SysException
	 */
	void deleteMsgs(Long ids) throws SysException;

	/**
	 * 开启短信服务
	 * 
	 * @throws Exception
	 */
	void startService() throws Exception;

	/**
	 * 查询待发送短信
	 * 
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	List<SmsSendVo> selectPlannedSms(SmsSendVo vo) throws SysException;

	/**
	 * 删除暂停当天之后的计划短信
	 * 
	 * @param pauseTime
	 */
	void delPlannedSmsByPauseTime(Date pauseTime);
}
