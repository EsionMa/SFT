package com.wangzhixuan.controller;

import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.EvaCountVo;
import com.wangzhixuan.model.vo.pj.EvaluationVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;
import com.wangzhixuan.service.pj.IPingJiaService;
import com.wangzhixuan.service.pj.impl.PingJiaServiceImpl;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2017/8/24.
 * TIME:21:45
 * Date:2017/8/24.
 * Description:
 */
@Controller()
@RequestMapping(value = "/evaluate")
public class EvaluateController {
    @Autowired
    private IPingJiaService pingJiaService;
    /**
     *@Author: Leslie
     *@Description:评价查询
     *@Date 2017/8/24 21:46
     */
    @ResponseBody
    @RequestMapping(value = "/findByCondition")
    public RespResult<PageInfo> findByCondition(@RequestBody Map<String, Object> map) {
        RespResult<PageInfo> respResult = new RespResult<>();
        PingJiaVo vo = BeanUtils.mapToBean(map, PingJiaVo.class);
        if (vo==null){
            vo=new PingJiaVo();
        }
        Map<String, Object> condition = new HashedMap();
        condition.put("hzName", vo.getHzName());
        condition.put("cyDeptId", vo.getCyDeptId());
        condition.put("evaluateId", vo.getBpjDeptId());
        condition.put("pjlx", vo.getPjlx());
        condition.put("staTime", vo.getStaTime());
        // 增加一个月时间
        condition.put("endTime", org.apache.commons.lang3.time.DateUtils.addMonths(vo.getStaTime(), 1));
        PageInfo pageInfo = new PageInfo(vo.getPage(), vo.getRows());
        pageInfo.setCondition(condition);
        pingJiaService.findByCondition(pageInfo);
        respResult.getSuccess(pageInfo);
        return respResult;
    }
    /**
     * 查询一个月的评价并打印
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findMonthEva")
    public RespResult<Map<String, List<EvaluationVo>>> findMonthEva(@RequestBody Map<String, Object> map) {
        RespResult<Map<String, List<EvaluationVo>>> result = new RespResult<>();
        Map<String, List<EvaluationVo>> evas = pingJiaService.print(map);
        result.getSuccess(evas);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/getFeedBackCount")
    public RespResult<List<EvaCountVo>> getFeedBackCount(@RequestBody Map<String,Object> map){
        RespResult<List<EvaCountVo>> respResult=new RespResult<>();
        List<EvaCountVo> evas=pingJiaService.getFeedBackCount(map);
        respResult.getSuccess(evas);
        return respResult;
    }
}
