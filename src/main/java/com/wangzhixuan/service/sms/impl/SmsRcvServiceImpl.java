/**
 * 2017-08-09 18:08:56
 */
package com.wangzhixuan.service.sms.impl;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.mapper.sms.SmsRcvMapper;
import com.wangzhixuan.model.sms.SmsRcv;
import com.wangzhixuan.model.vo.sms.SmsRcvVo;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;
import com.wangzhixuan.service.sms.ISmsRcvService;

/**
 * @author Esion
 *
 */
@Service
public class SmsRcvServiceImpl extends ServiceImpl<SmsRcvMapper, SmsRcv> implements ISmsRcvService {
	@Autowired
	private IHuanZheInfoService huanZheInfoService;

	/**
	 * @Author: Leslie
	 * @Description:保存前获取患者id和姓名
	 * @Date 2017/8/16 18:04
	 */
	@Override
	public boolean saveMessage(SmsRcvVo vo) throws SysException {
		if (vo != null) {
			return insert(vo);
		} else {
			throw new SysException(ErrorCode.ReqIsNull);
		}
	}

	@Override
	public PageInfo selectDataGrid(SmsRcvVo vo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		EntityWrapper<SmsRcv> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(vo.getFromNumber())) {
			wrapper.where("from_number={0}", vo.getFromNumber());
		}
		// if (vo.getSendTime() != null) {
		// wrapper.where("send_time>{0}", vo.getStatus());
		// }
		selectMapsPage(page, wrapper);
		for (Map<String, Object> map : page.getRecords()) {
			if (map.get("fromNumber") != null) {
				Map<String, Object> hzMap = huanZheInfoService.findByphone(map.get("fromNumber").toString());
				map.put("senderName", hzMap == null ? null : hzMap.get("name"));
			}
		}
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

}
