package com.wangzhixuan.mapper.phone;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.phone.Phone;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Leslie on 2017/8/8. TIME:11:06 Date:2017/8/8. Description:
 */
public interface PhoneMapper extends BaseMapper<Phone> {
	List<Map<String, Object>> findByCondition(Pagination page, Map<String, Object> map);

	/**
	 * 拨打次数
	 * 
	 * @param userId
	 * @return
	 */
	Integer getCallTimes(@Param("userId") Long userId);

	/**
	 *  查询记录
	 * @param page
	 * @param params
	 * @return
	 */
	List<Map<String, Object>>  selectRecord(Pagination page, Map<String, Object> params);
}
