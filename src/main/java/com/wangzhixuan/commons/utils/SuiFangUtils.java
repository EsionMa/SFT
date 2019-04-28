package com.wangzhixuan.commons.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import com.wangzhixuan.model.fa.FangAnDingYiItem;

public class SuiFangUtils {
	public static List<Date> getJhsfDate(FangAnDingYiItem item, Date cyDate, Date ryDate,Date mzDate) {
		List<Date> result = new ArrayList<>();
		Date date = null;
		if ("1".equals(item.getZxsjLx())) {
			// 今天
			date = new Date();
		} else if ("2".equals(item.getZxsjLx())) {
			// 出院
			date = cyDate;
		} else if ("3".equals(item.getZxsjLx())) {
			// 入院
			date = ryDate;
		} else if ("4".equals(item.getZxsjLx())) {
			// 自定义
			date = item.getZxsj();
		} else if ("5".equals(item.getZxsjLx())) {
			// 门诊
			date = mzDate;
		}
		if (date == null) {
			date = new Date();
		}
		date = DateUtils.addDays(date, item.getGjtzx());
		result.add(date);
		Integer zxjg = item.getZxjg();
		Long zts = item.getZts();

		if (zxjg == null || zts == null || zxjg < 1 || zts < 2) {
			return result;
		}
		for (int i = 0; i < zts - 1; i++) {
			result.add(DateUtils.addDays(date, zxjg));
		}
		return result;
	}
}
