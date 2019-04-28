package com.wangzhixuan.controller;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import com.wangzhixuan.service.task.ITaskService;
import com.wangzhixuan.service.wenjuan.IWenJuanShiLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Leslie on 2017/11/16.
 *
 * @author: Leslie
 * @TIME:16:12
 * @Date:2017/11/16 Description:
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    @Autowired
    private IZhuYuanInfoService zhuYuanInfoService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IWenJuanShiLiService wenJuanShiLiService;

    /**
     * 查询昨日出院人数
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public RespResult<Integer> export(){
        return zhuYuanInfoService.export();
    }
    @RequestMapping(value = "/exportSuccess")
    @ResponseBody
    public RespResult<String> exportSuccess(){
        return taskService.export();
    }
    @RequestMapping(value = "/plan")
    @ResponseBody
    public RespResult<Integer> plan(@RequestBody Map<String,Object> map){
        return wenJuanShiLiService.plan(map,getShiroUser());
    }
    @RequestMapping(value = "/already")
    @ResponseBody
    public RespResult<Integer> already(@RequestBody Map<String,Object> map){
        return wenJuanShiLiService.already(map,getShiroUser());
    }
    @RequestMapping(value = "/getSatisfaction")
    @ResponseBody
    public RespResult<Map<String,Object>> getCountByDate(@RequestBody Map<String,Object> map){
        return  wenJuanShiLiService.getCountByDate(map);
    }
}
