package com.wangzhixuan.mapper.sms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.sms.SmsTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
public interface SmsUserMapper extends BaseMapper<SysUser> {
	/**
	 * 查询（分页）
	 * 
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectUserPage(Pagination page, Map<String, Object> params);

	/*
	 * 查询类别(去重)
	 */
	List<SmsTemplate> selectType();
}
