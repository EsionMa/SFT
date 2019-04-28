/**
 * 2017-08-08 12:13:57
 */
package com.wangzhixuan.service.sms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Library;
import org.smslib.Message.MessageEncodings;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.crypto.AESKey;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.commons.utils.PhoneFormatCheckUtil;
import com.wangzhixuan.commons.utils.SuiFangUtils;
import com.wangzhixuan.mapper.sms.SmsSendMapper;
import com.wangzhixuan.model.fa.FangAnDingYi;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.sms.SmsSend;
import com.wangzhixuan.model.vo.fa.FangAnDingYiItemVo;
import com.wangzhixuan.model.vo.fa.FangAnMenZhenGroupVo;
import com.wangzhixuan.model.vo.fa.FangAnZhuYuanGroupVo;
import com.wangzhixuan.model.vo.sms.SmsRcvVo;
import com.wangzhixuan.model.vo.sms.SmsSendVo;
import com.wangzhixuan.service.fa.IFangAnDingYiService;
import com.wangzhixuan.service.fa.IFangAnMenZhenGroupService;
import com.wangzhixuan.service.fa.IFangAnZhuYuanGroupService;
import com.wangzhixuan.service.huanzhe.IMenZhenInfoService;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import com.wangzhixuan.service.sms.ISmsRcvService;
import com.wangzhixuan.service.sms.ISmsSendService;
import gnu.io.CommPortIdentifier;

/**
 * @author Esion
 *
 */
@Service
public class SmsSendServiceImpl extends ServiceImpl<SmsSendMapper, SmsSend> implements ISmsSendService {
	@Autowired
	private SmsSendMapper sendMapper;
	@Autowired
	private ISmsRcvService smsRcvService;
	@Autowired
	private IFangAnDingYiService fangAnDingYiService;
	@Autowired
	private IFangAnZhuYuanGroupService zhuYuanGroupService;
	@Autowired
	private IFangAnMenZhenGroupService menZhenGroupService;
	@Autowired
	private IZhuYuanInfoService zhuYuanInfoService;
	@Autowired
	private IMenZhenInfoService menZhenInfoService;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static org.smslib.Service service;
	private static CommPortIdentifier portId;
	private static SerialModemGateway gateway;
	private static Enumeration<CommPortIdentifier> portList;

	@Override
	public void sendInstantMsg(SmsSendVo vo) throws Exception {
		String phone = vo.getToNumber();
		if (StringUtils.isNotBlank(phone)) {
			if (PhoneFormatCheckUtil.isChinaPhoneLegal(phone)) {
				// 短信内容
				String content = vo.getContent();
				if (StringUtils.isNotBlank(vo.getRcverName())) {
					StringBuilder sb = new StringBuilder(vo.getContent());
					if (content.indexOf("{") > 0 && content.indexOf("}") > 0) {
						sb.replace(content.indexOf("{"), content.indexOf("}") + 1, vo.getRcverName());
						vo.setContent(sb.toString());
					}
				} else {
					vo.setContent(content);
				}
				sendMessage(vo);
			} else {
				throw new SysException("手机号码" + phone + "无效");
			}
		} else {
			throw new SysException("接收号码不能为空");
		}
	}

	@Override
	public void generatePlannedMsg(SmsSendVo vo, FangAnDingYiItemVo fangAnDingYiItemVo, List<Long> infos)
			throws Exception {
		// 自定义发送
		if (vo != null) {
			String phone = vo.getToNumber();
			if (StringUtils.isNotBlank(phone)) {
				if (PhoneFormatCheckUtil.isChinaPhoneLegal(phone)) {
					// 短信内容
					String content = vo.getContent();
					if (StringUtils.isNotBlank(vo.getRcverName())) {
						StringBuilder sb = new StringBuilder(vo.getContent());
						if (content.indexOf("{") > 0 && content.indexOf("}") > 0) {
							sb.replace(content.indexOf("{"), content.indexOf("}") + 1, vo.getRcverName());
							vo.setContent(sb.toString());
						}
					} else {
						vo.setContent(content);
					}
					vo.setStatus(1);
					insert(vo);
				} else {
					throw new SysException("手机号码" + phone + "无效");
				}
			} else {
				throw new SysException("接收号码不能为空");
			}
		}
		// 方案发送
		if (fangAnDingYiItemVo != null) {
			Long faId = fangAnDingYiItemVo.getFaId();
			FangAnDingYi fangAnDingYi = fangAnDingYiService.selectById(faId);
			if (fangAnDingYi == null) {
				logger.warn("创建方案实例警告，不存在的实例，方案ID：{}", faId);
				return;
			}
			// 第一步，选择患者
			if (fangAnDingYi.getHzly() == 1) {
				// 住院方案
				logger.debug("方案生成调查问卷实例，方案ID为{},方案名称为{}", faId, fangAnDingYi.getName());
				FangAnZhuYuanGroupVo fangAnZhuYuanGroupVo = zhuYuanGroupService.findByFangAnId(faId);
				if (fangAnZhuYuanGroupVo == null) {
					logger.debug("方案{},{}不存在住院用户群组，不会生成 问卷实例", faId, fangAnDingYi.getName());
					return;
				}
				if (infos == null || infos.size() < 1) {
					infos = null;
				}
				fangAnZhuYuanGroupVo.setZhuYuanInfoIds(infos);
				createSmsByZyGroup(fangAnDingYi, fangAnZhuYuanGroupVo, fangAnDingYiItemVo, 1, 5000);
			} else if (fangAnDingYi.getHzly() == 2) {
				// 门诊方案
				logger.debug("方案生成调查问卷实例，方案ID为{},方案名称为{}", faId, fangAnDingYi.getName());
				FangAnMenZhenGroupVo fangAnMenZhenGroupVo = menZhenGroupService.findByFangAnId(faId);
				if (fangAnMenZhenGroupVo == null) {
					logger.debug("方案{},{}不存在住院用户群组，不会生成 问卷实例", faId, fangAnDingYi.getName());
					return;
				}
				if (infos == null || infos.size() < 1) {
					infos = null;
				}
				fangAnMenZhenGroupVo.setMenZhenInfoIds(infos);
				// 创建4000条随访执行实例
				createSmsByMzGroup(fangAnDingYi, fangAnMenZhenGroupVo, fangAnDingYiItemVo, 1, 5000);
			} else {
				logger.debug("未知方案类型");
				throw new SysException("未知方案类型");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void createSmsByZyGroup(FangAnDingYi fangAnDingYi, FangAnZhuYuanGroupVo group,
			FangAnDingYiItemVo fangAnDingYiItemVo, Integer nowPage, Integer pageSize) {
		// 符合群组条件的住院患者
		PageInfo pageZhuYuanInfo = zhuYuanInfoService.findByGroup(group, nowPage, pageSize);
		int zyCount = 0;
		List<ZhuYuanInfo> zhuYuanInfos = null;
		if (pageZhuYuanInfo != null) {
			zhuYuanInfos = pageZhuYuanInfo.getRows();
			if (zhuYuanInfos != null) {
				zyCount = zhuYuanInfos.size();
			}
		}
		logger.debug("根据住院来源生成实例第{}次运行，每页{}条数据", nowPage, zyCount);
		if (zyCount < 1) {
			logger.debug("没有获取到符合条件的住院患者，本次不生成问卷实例数据");
		}
		for (int i = 0; i < zyCount; i++) {
			ZhuYuanInfo zhuYuanInfo = zhuYuanInfos.get(i);
			SmsSend smsSend = new SmsSend();
			smsSend.setStatus(1);
			smsSend.setRcverName(zhuYuanInfo.getHzName());
			smsSend.setToNumber(zhuYuanInfo.getPhone());
			smsSend.setContent(fangAnDingYiItemVo.getSmsContent());
			smsSend.setUserId(fangAnDingYiItemVo.getCreateUserId());
			Date cySendTime = null;
			if ("2".equals(fangAnDingYiItemVo.getZxsjLx())) {
				String smsSendTime = DateUtils.format(zhuYuanInfo.getCytime(), "yyyy-MM-dd ")
						+ fangAnDingYiItemVo.getSmsSendTime();
				cySendTime = DateUtils.parse(smsSendTime);
			}
			Date rySendTime = null;
			if ("3".equals(fangAnDingYiItemVo.getZxsjLx())) {
				String smsSendTime = DateUtils.format(zhuYuanInfo.getRytime(), "yyyy-MM-dd ")
						+ fangAnDingYiItemVo.getSmsSendTime();
				rySendTime = DateUtils.parse(smsSendTime);
			}
			List<Date> jhsfDates = SuiFangUtils.getJhsfDate(fangAnDingYiItemVo, cySendTime, rySendTime, null);
			for (int k = 0; k < jhsfDates.size(); k++) {
				// 计划随访时间
				smsSend.setSendTime(jhsfDates.get(k));
				insert(smsSend);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void createSmsByMzGroup(FangAnDingYi fangAnDingYi, FangAnMenZhenGroupVo group,
			FangAnDingYiItemVo fangAnDingYiItemVo, Integer nowPage, Integer pageSize) {
		// 符合群组条件的住院患者
		PageInfo pageMenZhenInfo = menZhenInfoService.findByGroup(group, nowPage, pageSize);
		int mzCount = 0;
		List<MenZhenInfo> menZhenInfos = null;
		if (pageMenZhenInfo != null) {
			menZhenInfos = pageMenZhenInfo.getRows();
			if (menZhenInfos != null) {
				mzCount = menZhenInfos.size();
			}
		}
		logger.debug("根据门诊来源生成实例第{}次运行，每页{}条数据", nowPage, mzCount);
		if (mzCount < 1) {
			logger.debug("没有获取到符合条件的门诊患者，本次不生成问卷实例数据");
		}
		for (int i = 0; i < mzCount; i++) {
			MenZhenInfo menZhenInfo = menZhenInfos.get(i);
			SmsSend smsSend = new SmsSend();
			smsSend.setStatus(1);
			smsSend.setRcverName(menZhenInfo.getHzName());
			smsSend.setToNumber(menZhenInfo.getPhone());
			smsSend.setContent(fangAnDingYiItemVo.getSmsContent());
			smsSend.setUserId(fangAnDingYiItemVo.getCreateUserId());
			Date mzSendTime = null;
			if ("2".equals(fangAnDingYiItemVo.getZxsjLx())) {
				String smsSendTime = DateUtils.format(menZhenInfo.getMzTime(), "yyyy-MM-dd ")
						+ fangAnDingYiItemVo.getSmsSendTime();
				mzSendTime = DateUtils.parse(smsSendTime);
			}
			List<Date> jhsfDates = SuiFangUtils.getJhsfDate(fangAnDingYiItemVo, null, null, mzSendTime);
			for (int k = 0; k < jhsfDates.size(); k++) {
				// 计划随访时间
				smsSend.setSendTime(jhsfDates.get(k));
				insert(smsSend);
			}
		}
	}

	@Override
	public void sendPlannedMsg(SmsSendVo vo) throws Exception {
		sendMessage(vo);
	}

	private void sendMessage(SmsSendVo vo) throws Exception {
		if (portId != null) {
			// 短信发送的回调方法
			OutboundNotification outboundNotification = new OutboundNotification();
			// 发送短信成功后的回调函方法
			service.setOutboundMessageNotification(outboundNotification);
			// 发送短信
			OutboundMessage msg = new OutboundMessage(vo.getToNumber(), vo.getContent());
			// 发中文短信必须的
			msg.setEncoding(MessageEncodings.ENCUCS2);
			boolean isSent = service.queueMessage(msg);
			if (isSent) {
				// 发送成功
				vo.setStatus(2);
			} else {
				// 发送失败
				vo.setStatus(0);
			}
			if (vo.getTimeType() == 1) {
				// 及时发送
				vo.setSendTime(new Date());
				insert(vo);
			} else if (vo.getTimeType() == 2) {
				// 定时发送
				updateById(vo);
			}

			// 队列发送异步短信
			// msg = new OutboundMessage("309999999999", "Wrong number!");
			// service.queueMessage(msg, gateway.getGatewayId());
			// msg = new OutboundMessage("308888888888", "Wrong number!");
			// service.queueMessage(msg, gateway.getGatewayId());
		} else {
			logger.warn("请接入短信猫设备");
			throw new SysException("请接入短信猫设备");
		}
	}

	@Override
	public PageInfo selectDataGrid(SmsSendVo vo) throws SysException {
		Page<SmsSend> page = new Page<>(vo.getPage(), vo.getRows());
		page.setAsc(false);
		EntityWrapper<SmsSend> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(vo.getToNumber())) {
			wrapper.where("to_number={0}", vo.getToNumber());
		}
		if (vo.getStatus() != null) {
			wrapper.where("status={0}", vo.getStatus());
		}
		// if (vo.getSendTime() != null) {
		// wrapper.where("send_time>{0}", vo.getStatus());
		// }
		selectPage(page, wrapper);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public void deleteMsgs(Long ids) throws SysException {
		if (ids != null) {
			deleteById(ids);
		} else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}

	@SuppressWarnings("unchecked")
	public void startService() throws Exception {
		logger.info("***********加载配置文件（开始）************");
		logger.info("Example: Send message from a serial gsm modem.");
		logger.info(Library.getLibraryDescription());
		logger.info("Version: " + Library.getLibraryVersion());
		logger.info("***********加载配置文件（结束）************");
		// 获取服务
		if (service == null) {
			service = org.smslib.Service.getInstance();
		} else {
			// 端口重置
			portId = null;
		}
		// 是否重置网关
		if (service.getGateways().size() > 0) {
			service.stopService();
			service.removeGateway(gateway);
		}
		logger.info("***********开始搜索设备************");
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM8")) {
					logger.info("找到端口:" + portId.getName());
					logger.info("**********搜索设备完毕************");
					logger.info("******************************");
					break;
				}
			}
		}
		if (portId == null) {
			logger.warn("***********未找到端口!!!************");
			logger.warn("*********请接入短信猫设备!!!**********");
			throw new SysException("请接入短信猫设备");
		}
		if (!portId.getName().equals("COM8")) {
			throw new SysException("请检查短信猫设备端口是否正确");
		}
		/*
		 * 设置网关
		 * 
		 * modem.com1:网关ID（即短信猫端口编号）
		 * COM4:串口名称（在window中以COMXX表示端口名称，在linux,unix平台下以ttyS0-N或ttyUSB0-
		 * N表示端口名称 ），通过端口检测程序得到可用的端口
		 * 115200：串口每秒发送数据的bit位数,必须设置正确才可以正常发送短信，可通过程序进行检测。常用的有115200、9600
		 * Huawei：短信猫生产厂商，不同的短信猫生产厂商smslib所封装的AT指令接口会不一致，必须设置正确.常见的有Huawei、
		 * wavecom等厂商 最后一个参数表示设备的型号，可选
		 */
		gateway = new SerialModemGateway("modem." + portId.getName().toLowerCase(), portId.getName(), 9600, null, null);
		// gateway.setProtocol(Protocols.PDU);
		// 设置true，表示该网关可以接收短信,根据需求修改
		gateway.setInbound(true);
		// 设置true，表示该网关可以发送短信,根据需求修改
		gateway.setOutbound(true);
		// sim卡锁，一般默认为0000或1234
		// gateway.setSimPin("0000");
		// 短信服务中心号码（无用）
		// gateway.setSmscNumber("+306942190000");
		// 将网关添加到短信猫服务中
		service.addGateway(gateway);
		// 启用轮循模式
		service.S.SERIAL_POLLING = true;
		// 启动服务
		service.startService();

		// 打印设备信息
		logger.info("***********打印设备信息************");
		logger.info("  Modem Information:");
		logger.info("  Manufacturer: " + gateway.getManufacturer());
		logger.info("  Model: " + gateway.getModel());
		logger.info("  Serial No: " + gateway.getSerialNo());
		logger.info("  SIM IMSI: " + gateway.getImsi());
		logger.info("  Signal Level: " + gateway.getSignalLevel() + " dBm");
		logger.info("  Battery Level: " + gateway.getBatteryLevel() + "%");
		logger.info("***********打印完毕************");

		// 接收短信
		readMessage();
	}

	@Override
	public List<SmsSendVo> selectPlannedSms(SmsSendVo vo) throws SysException {
		List<SmsSendVo> vos = null;
		EntityWrapper<SmsSend> wrapper = new EntityWrapper<>();
		wrapper.where("send_time={0}", vo.getSendTime());
		wrapper.where("status={0}", vo.getStatus());
		List<SmsSend> smsSends = selectList(wrapper);
		if (smsSends != null & smsSends.size() > 0) {
			vos = new ArrayList<>();
			for (SmsSend smsSend : smsSends) {
				vos.add(BeanUtils.copy(smsSend, SmsSendVo.class));
			}
		}
		return vos;
	}

	private void readMessage() throws Exception {
		if (portId != null) {
			// 短信接受和通知的回调方法
			InboundNotification inboundNotification = new InboundNotification();
			CallNotification callNotification = new CallNotification();
			GatewayStatusNotification statusNotification = new GatewayStatusNotification();
			OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();

			service.setInboundMessageNotification(inboundNotification);
			service.setCallNotification(callNotification);
			service.setGatewayStatusNotification(statusNotification);
			service.setOrphanedMessageNotification(orphanedMessageNotification);

			// In case you work with encrypted messages, its a good time to
			// declare your keys.
			// Create a new AES Key with a known key value.
			// Register it in KeyManager in order to keep it active. SMSLib will
			// then automatically
			// encrypt / decrypt all messages send to / received from this
			// number.
			service.getKeyManager().registerKey("+306948494037",
					new AESKey(new SecretKeySpec("0011223344556677".getBytes(), "AES")));
			// 读取所有未读短信
			// 收到的短信列表
			List<InboundMessage> msgList = new ArrayList<InboundMessage>();
			service.readMessages(msgList, MessageClasses.UNREAD);
		} else {
			logger.warn("请接入短信猫设备");
			throw new SysException("请接入短信猫设备");
		}
	}

	/*
	 * 短信发送成功后，调用该接口。并将发送短信的网关和短信内容对象传给process接口
	 */
	private class OutboundNotification implements IOutboundMessageNotification {
		@Override
		public void process(AGateway gateway, OutboundMessage msg) {
			logger.info("Outbound handler called from Gateway: {}", gateway.getGatewayId());
		}
	}

	private class InboundNotification implements IInboundMessageNotification {
		@Override
		public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg) {
			if (msgType == MessageTypes.INBOUND) {
				logger.info(">>> New Inbound message detected from Gateway: {}", gateway.getGatewayId());
			} else if (msgType == MessageTypes.STATUSREPORT) {
				logger.info(">>> New Inbound Status " + "Report message detected from Gateway: {}",
						gateway.getGatewayId());
			}
			logger.info("************接收短信*************");
			logger.info("短信内容：", msg);
			SmsRcvVo vo = new SmsRcvVo();
			if (msg.getOriginator().startsWith("86")) {
				vo.setFromNumber(msg.getOriginator().substring(2));
			} else {
				vo.setFromNumber(msg.getOriginator());
			}
			vo.setContent(msg.getText());
			vo.setRcvTime(msg.getDate());
			try {
				boolean isDelete = smsRcvService.saveMessage(vo);
				if (isDelete) {
					try {
						service.deleteMessage(msg);
					} catch (Exception e) {
						logger.error("接收短信删除失败");
						logger.error(e.getMessage());
					}
				} else {
					logger.error("接收短信保存失败");
				}
			} catch (Exception e) {
				logger.error("接收短信保存失败");
				logger.error(e.getMessage());
			}
		}
	}

	private class CallNotification implements ICallNotification {

		@Override
		public void process(AGateway gateway, String callerId) {
			logger.info("************接收短信（开始）*************");
			logger.info("网关ID={}监测到新短信，来自号码：{}", gateway.getGatewayId(), callerId);
			logger.info("************接收短信（结束）*************");
		}
	}

	/**
	 * 网关状态通知
	 * 
	 * @author Esion
	 *
	 */
	private class GatewayStatusNotification implements IGatewayStatusNotification {
		@Override
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
			logger.info("************Gateway Status*************");
			logger.info("Gateway Status change for {}, OLD: {}, NEW:{}", gateway.getGatewayId(), oldStatus, newStatus);
			logger.info("************Gateway Status*************");
		}
	}

	/**
	 * 
	 * @author Esion
	 *
	 */
	private class OrphanedMessageNotification implements IOrphanedMessageNotification {
		@Override
		public boolean process(AGateway gateway, InboundMessage msg) {
			// Since we are just testing, return FALSE and keep the orphaned
			// message part.
			logger.info("************Orphaned message*************");
			logger.info("************Orphaned message part detected from {}*************", gateway.getGatewayId());
			logger.info("消息内容：{}", msg);
			logger.info("************Orphaned message*************");
			return false;
		}
	}

	@Override
	public void delPlannedSmsByPauseTime(Date pauseTime) {
		EntityWrapper<SmsSend> wrapper = new EntityWrapper<>();
		wrapper.where("send_time>{0}", pauseTime);
		Integer disablePlannedSms = sendMapper.delete(wrapper);
		logger.debug("作废的计划短信{}条数据", disablePlannedSms);
	}

}
