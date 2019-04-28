package com.wangzhixuan.service.sms;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.vo.huanzhe.ZhuYuanInfoVo;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
public interface ISmsPatientService extends IService<HuanZheInfo> {

	/**
	 * 住院患者信息列表
	 * 
	 * @param vo
	 * @return
	 * @throws SysException
	 */
	PageInfo selectPatient(ZhuYuanInfoVo vo) throws SysException;

}
