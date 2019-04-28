package com.wangzhixuan.service.sms.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.mapper.sms.SmsPatientMapper;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import com.wangzhixuan.service.sms.ISmsPatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
/*
 * 查找通讯录
 */
@Service
public class SmsPatientServiceImpl extends ServiceImpl<SmsPatientMapper, HuanZheInfo> implements ISmsPatientService {
	@Autowired
	private IZhuYuanInfoService zhuYuanInfoService;

	// 患者通讯录
	@Override
	public PageInfo selectPatient(ZhuYuanInfoVo vo) throws SysException {
		return zhuYuanInfoService.selectDataGrid(vo);
	}

}
