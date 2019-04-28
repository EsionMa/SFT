package com.wangzhixuan.service.phone;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.mapper.phone.PhoneMapper;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.phone.Phone;
import com.wangzhixuan.model.vo.phone.PhoneVo;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/8/8. TIME:10:02 Date:2017/8/8. Description:
 */


@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements IPhoneService {

	private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private PhoneMapper phoneMapper;

	@Autowired
	private IPhoneService phoneService;
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private IHuanZheInfoService huanZheInfoService;

	/**
	 * @Author: Leslie
	 * @Description:保存来电或拨打的电话号码
	 * @Date 2017/8/8 11:15
	 */
	@Override
	public void saveInNum(Phone phone, ShiroUser user) throws SysException {
		if (phone.getPhoneNum() == null || phone.getPhoneNum() == "") {
			throw new SysException("号码为空");
		}
		try {
			phone.setUserId(user.getId());
		} catch (NullPointerException exception) {
			throw new SysException("用户验证过期");
		} finally {
			phone.setFileLoad(phone.getFileLoad());
			phoneMapper.insert(phone);
		}
	}

	/**
	 * @Author: Leslie
	 * @Description:条件查询
	 * @Date 2017/8/9 18:14
	 */
	@Override
	public PageInfo selectDataGridByCondition(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		if (pageInfo.getCondition() == null) {
			throw new SysException("传入条件为空");
		}
		List<Map<String, Object>> list = phoneMapper.findByCondition(page, pageInfo.getCondition());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	/**
	 * @Author: Leslie
	 * @Description:查询所有通话记录
	 * @Date 2017/8/9 18:14
	 */
	@Override
	public PageInfo findAll(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		EntityWrapper<Phone> wrapper = new EntityWrapper<>();
		wrapper.orderBy("create_Time", false);
		selectMapsPage(page,wrapper);
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public List<Phone> findByCondition(Phone phone) {
		EntityWrapper<Phone> wrapper = new EntityWrapper<>();
		if (phone.getSfId() != null) {
			wrapper.where("sf_id={0}", phone.getSfId());
		}
		if (phone.getType() != null) {
			wrapper.where("type={0}", phone.getType());
		}
		if (phone.getHold() != null) {
			wrapper.where("hold={0}", phone.getHold());
		}

		wrapper.orderBy("create_Time", false);
		return selectList(wrapper);
	}

	@Override
	public void checked(Phone phone, ShiroUser user) {
		if (phone != null) {
			if (phone.getChecked() != 1) {
				phone.setChecked(1);
				phoneMapper.updateById(phone);
			}
		} else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}

	@Override
	public Integer getCallTimes(Long id) {
		Integer times = null;
		if (id != null) {
			times = phoneMapper.getCallTimes(id);
		}
		return times;
	}

	/**
	 * 所有记录
	 * @param vo
	 * @return
	 */
	@Override
	public PageInfo selectRecord(PhoneVo vo)throws SysException {
		Page<Map<String, Object>> page = new Page<>(vo.getPage(), vo.getRows());
		EntityWrapper<Phone> wrapper = new EntityWrapper<>();
		if (vo.getHzId()!= null) {
			wrapper.where("hz_id = {0}", vo.getHzName());
		}
		if (vo.getPhoneNum() != null) {
			wrapper.where("phone_num={0}", vo.getPhoneNum());
		}
		if (vo.getType() != null) {
			wrapper.where("type = {0}", vo.getType());
		}

			// 拨打时间
			if (vo.getCreTime() != null) {
				wrapper.where("create_time>={0}", vo.getCreTime());
			}
			if (vo.getEndTime() != null) {
				wrapper.where("create_time<={0}", vo.getEndTime());
			}
			wrapper.orderBy("create_time", false);



		selectMapsPage(page,wrapper);
        for (Map<String, Object> item : page.getRecords()) {
			if( item.get("userId")!=null&& item.get("userId")!="" ) {
				SysUser name = iSysUserService.selectById(item.get("userId").toString());
				item.put("userName", name == null ? null : name.getName());
			}
            if (item.containsKey("hzId")) {
                HuanZheInfo huanZheInfo = huanZheInfoService.selectById(item.get("hzId").toString());
                item.put("hzName", huanZheInfo == null ? null : huanZheInfo.getName());
            }
        }
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	/**
	 * 删除记录
	 * @param id
	 */
	@Override
	public void deleteRecord(Long id) {
		if (id != null) {
			deleteById(id);
		} else {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
	}
}
