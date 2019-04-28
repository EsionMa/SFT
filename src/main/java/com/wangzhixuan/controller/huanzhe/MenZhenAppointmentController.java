package com.wangzhixuan.controller.huanzhe;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.huanzhe.MenZhenAppointmentVo;
import com.wangzhixuan.service.huanzhe.IMenZhenAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
@Controller()
@RequestMapping(value = "/appointment")
public class MenZhenAppointmentController extends BaseController {
    @Autowired
    private IMenZhenAppointmentService appointmentService;

    /**
     *  查询
     * @param map
     * @return
     */
    @RequestMapping(value = "/dataGrid")
    @ResponseBody
    public RespResult<PageInfo> dataGrid(@RequestBody Map<String, Object> map) {
        RespResult<PageInfo> result = new RespResult<>();
        MenZhenAppointmentVo vo = BeanUtils.mapToBean(map, MenZhenAppointmentVo.class);
        PageInfo pageInfo = appointmentService.selectedDataGrid(vo);
        result.getSuccess(pageInfo);
        return result;
    }

    /**
     *  添加、修改预约信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/saveOrUpdata")
    @ResponseBody
    public RespResult<String> saveOrUpdate(@RequestBody Map<String, Object> map){
        RespResult<String> result = new RespResult<>();
        ShiroUser user = getShiroUser();
        // 日期、时间转换
        String date = map.get("appointDate").toString();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date appointDate = format1.parse(date);
            map.put("appointDate", appointDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        MenZhenAppointmentVo vo = BeanUtils.mapToBean(map,MenZhenAppointmentVo.class);
        appointmentService.saveAddUpdata(vo,user);
        result.getSuccess("","操作成功");
        return result;
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public RespResult<String> delete(@RequestBody Map<String, Object> map) {
        RespResult<String> result = new RespResult<>();
        MenZhenAppointmentVo vo = BeanUtils.mapToBean(map, MenZhenAppointmentVo.class);
        if (vo == null) {
            result.getFail("记录不存在");
        }
        appointmentService.delete(vo.getId());
        result.getSuccess("","删除成功");
        return result;
    }
    @RequestMapping(value = "/findById")
    @ResponseBody
    public RespResult<MenZhenAppointmentVo> findById(@RequestBody Map<String, Object> map) {
        RespResult<MenZhenAppointmentVo> result = new RespResult<>();
        MenZhenAppointmentVo vo = BeanUtils.mapToBean(map, MenZhenAppointmentVo.class);
        if (vo.getId() != null) {
            MenZhenAppointmentVo appointmentById = appointmentService.findById(vo.getId());
            result.getSuccess(appointmentById);
        } else {
            result.getFail(ErrorCode.ReqIsNull);
        }
        return result;
    }
    /**
     *@author: Leslie
     *@Date 2018/2/27 17:29
     *@param: [map]
     *@return com.wangzhixuan.commons.result.RespResult<java.lang.Integer>
     *@throws
     *@Description:传入时间段，得到预约人数
     */
  @RequestMapping("/getCount")
  @ResponseBody
    public RespResult<Integer> getCount(@RequestBody Map<String,Object> map){
        RespResult<Integer> result=new RespResult<>();
        result.getSuccess(appointmentService.getCount(map));
        return result;
  }
}
