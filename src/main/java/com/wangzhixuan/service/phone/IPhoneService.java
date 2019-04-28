package com.wangzhixuan.service.phone;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.phone.Phone;
import com.wangzhixuan.model.vo.WenJuanShiLiDataGridQuery;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.vo.phone.PhoneVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/8/8. TIME:10:00 Date:2017/8/8. Description:
 */

public interface IPhoneService extends IService<Phone> {
	/**
	 * 保存电话
	 * 
	 * @param phone
	 * @param user
	 */
	void saveInNum(Phone phone, ShiroUser user);

	/**
	 * 
	 * @param pageInfo
	 * @return
	 */
	PageInfo selectDataGridByCondition(PageInfo pageInfo);

	/**
	 * 查询所有
	 * 
	 * @param pageInfo
	 * @return
	 */
	PageInfo findAll(PageInfo pageInfo);

	/**
	 * 条件搜索
	 * 
	 * @param phone
	 * @return
	 */
	List<Phone> findByCondition(Phone phone);

	/**
	 * 查看电话
	 * 
	 * @param phone
	 * @param user
	 */
	void checked(Phone phone, ShiroUser user);

	/**
	 * 拨打次数
	 * 
	 * @param id
	 * @return
	 */
	Integer getCallTimes(Long id);

	/**
	 * 查询通话记录
	 * @param vo
	 * @return
	 */
	PageInfo selectRecord(PhoneVo vo);



	/**
	 * 删除记录
	 * @param id
	 */
	void deleteRecord(Long id);
}
