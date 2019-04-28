package com.wangzhixuan.controller.phone;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.phone.Phone;
import com.wangzhixuan.model.vo.huanzhe.HuanZheInfoVo;
import com.wangzhixuan.model.vo.phone.PhoneVo;
import com.wangzhixuan.service.phone.IPhoneService;

/**
 * Created by Leslie on 2017/8/8. TIME:9:26 Date:2017/8/8. Description:
 */
@Controller
@RequestMapping("/phone")
public class PhoneController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);
	/** linux系统存放录音路径 */
	private static final String filePath = "/root/suifang/audio/";
	/** windows系统存放录音路径 */
	/* private static final String filePath = "D:/suifang/audio/"; */

	@Autowired
	private IPhoneService phoneService;

	@RequestMapping("/saveNum")
	@ResponseBody
	public RespResult<String> saveNum(@RequestBody Map<String, Object> map) throws SysException {
		RespResult<String> result = new RespResult<>();
		String cre = map.get("creTime").toString();
		String end = map.get("endTime").toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = null;
		Date creTime = null;
		try {
			endTime = dateFormat.parse(end);
			creTime = dateFormat.parse(cre);
		} catch (ParseException e) {
			throw new SysException("时间转换出错");
		} finally {
			map.put("creTime", creTime);
			map.put("endTime", endTime);
		}

		Phone vo = BeanUtils.mapToBean(map, Phone.class);
		phoneService.saveInNum(vo, getShiroUser());
		result.getSuccess("save success");
		return result;

	}

	/**
	 * @Author: Leslie
	 * @Description:查询所有通话记录
	 * @Date 2017/8/9 18:14
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public RespResult<PageInfo> findAll(@RequestBody Map<String, Object> map) throws SysException {
		RespResult<PageInfo> result = new RespResult<>();
		HuanZheInfoVo vo = BeanUtils.mapToBean(map, HuanZheInfoVo.class);
		if (vo == null) {
			vo = new HuanZheInfoVo();
		}
		PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
		// Map<String, Object> condition = new HashMap<>();
		/* condition.put("name", vo.getName()); */
		phoneService.findAll(pageInfo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * 通话记录
	 * 
	 * @param map
	 * @return
	 * @throws SysException
	 */
	@RequestMapping("/selectPhone")
	@ResponseBody
	public RespResult<PageInfo> selectPhone(@RequestBody Map<String, Object> map) throws SysException {
		RespResult<PageInfo> result = new RespResult<>();
		PhoneVo vo = BeanUtils.mapToBean(map, PhoneVo.class);
		PageInfo pageInfo = phoneService.selectRecord(vo);
		result.getSuccess(pageInfo);
		return result;
	}

	/**
	 * @Author: Leslie
	 * @Description:文件上传
	 * @Date 2017/8/9 18:15
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public RespResult<String> upload(@RequestParam("multipartFile") MultipartFile[] multipartFile, Phone phone)
			throws SysException {
		RespResult<String> respResult = new RespResult<>();
		String date = DateUtils.format(new Date(), "yyyy-MM-dd");
		String newPath = filePath + date;
		File file = new File(newPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (multipartFile == null || multipartFile.length < 1) {
			// 不能抛出异常
			logger.debug("没有录音上传，一个通话记录");
		} else {
			String str = multipartFile[0].getOriginalFilename();
			// 文件名
			String fileName = str.substring(str.lastIndexOf("\\") + 1, str.length());
			try {
				FileUtils.writeByteArrayToFile(new File(newPath + "/" + fileName), multipartFile[0].getBytes());
			} catch (IOException e) {
				logger.error("*******录音文件传输异常********");
				logger.error(e.getMessage());
				logger.error("*******录音文件传输异常********");
			}
			phone.setFileLoad(date + "/" + fileName);
		}
		phoneService.saveInNum(phone, getShiroUser());
		respResult.getSuccess("success");
		return respResult;
	}

	/**
	 * @Author: Leslie
	 * @Description:电话记录模糊查询，查询时根据患者号，患者id等查询，至少有一个条件
	 * @Date 2017/8/9 17:33
	 */
	@RequestMapping("/findByCondition")
	@ResponseBody
	public RespResult<PageInfo> findByCondition(@RequestBody Map<String, Object> map) throws SysException {
		RespResult<PageInfo> result = new RespResult<>();
		PhoneVo vo = BeanUtils.mapToBean(map, PhoneVo.class);
		PageInfo info = new PageInfo(vo.getPage(), vo.getRows());
		Map<String, Object> conMap = new HashMap<>();
		conMap.put("hzId", vo.getHzId());
		info.setCondition(conMap);
		phoneService.selectDataGridByCondition(info);
		result.getSuccess(info);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/delRecord", method = RequestMethod.POST)
	public RespResult<String> deleteRecord(@RequestBody Map<String, Object> map) {
		RespResult<String> result = new RespResult<>();
		PhoneVo vo = BeanUtils.mapToBean(map, PhoneVo.class);
		if (vo == null) {
			result.getFail("该记录不存在");
		}
		phoneService.deleteRecord(vo.getId());
		result.getSuccess("删除成功");
		return result;
	}
}
