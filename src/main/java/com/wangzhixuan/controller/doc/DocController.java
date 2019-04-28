package com.wangzhixuan.controller.doc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.vo.wj.SuiFangTongJiVo;
import com.wangzhixuan.service.tj.ITongjiService;
import com.wangzhixuan.util.ExcelUtil;

@Controller
@RequestMapping("/doc")
public class DocController extends BaseController {
	@Autowired
	private ITongjiService tongJiService;
/*
	@RequestMapping(value = "/excel/sftj")
	@ResponseBody
	public void wenJuanDaAnTongJi(HttpServletResponse response, String staTime, String endTime) throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("staTime", Date.parse(staTime));
		map.put("endTime", Date.parse(endTime));
		List<SuiFangTongJiVo> sftjVoList = tongJiService.sfCount(map);
		Date date = new Date(Date.parse(staTime));
		try {
			ExcelUtil.exportExcel(getShiroUser().getId(), date, sftjVoList, response);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}*/

	public static void main(String[] args) {
		System.out.println(DateUtils.parse("Sun Oct 01 2017 00:00:00 GMT 0800 (中国标准时间)"));
	}
}
