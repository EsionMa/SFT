/**
 * 2018-01-18 17:59:39
 */
package com.wangzhixuan.task;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.vo.sms.SmsSendVo;
import com.wangzhixuan.service.sms.ISmsSendService;

/**
 * @author Esion
 *
 */
@Component
public class SmsAutoSendTask {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ISmsSendService smsSendService;

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void sendMessages() {
		logger.debug("执行定时任务，获取短信数据，并且匹配短信");
		logger.debug("任务执行时间为:{}", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		Date currentTime = DateUtils.parse(DateUtils.format(new Date(), "yyyy-MM-dd HH:00:00"));
		SmsSendVo sendVo = new SmsSendVo();
		sendVo.setStatus(1);
		sendVo.setSendTime(currentTime);
		List<SmsSendVo> vos = smsSendService.selectPlannedSms(sendVo);
		// 判断是否存在待发送短信
		if (vos != null && vos.size() > 0) {
			logger.debug("执行短信发送任务开始,开始时间：{}", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			for (SmsSendVo smsSendVo : vos) {
				smsSendVo.setTimeType(2);
				try {
					smsSendService.sendPlannedMsg(smsSendVo);
				} catch (Exception e) {
					logger.warn("执行短信发送任务中断,中断时间：{}", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					logger.warn(e.getMessage());
				}
			}
			logger.debug("执行短信发送任务结束,结束时间：{}", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			logger.debug("在该时间：{}，没有待发送的短信", DateUtils.format(currentTime, "yyyy-MM-dd HH:mm:ss"));
		}
	}

}
