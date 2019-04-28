package com.wangzhixuan.controller.admin;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.admin.CheckedQuery;
import com.wangzhixuan.model.vo.admin.QuestionsCheckedVo;
import com.wangzhixuan.service.admin.IquestionsCheckedService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Leslie on 2018/1/29.
 *
 * @author: Leslie
 * @TIME:16:58
 * @Date:2018/1/29 Description:
 */
@Controller
@RequestMapping(value = "/questionChecked")
public class QuestionCheckedController extends BaseController{
    @Autowired
    private IquestionsCheckedService iquestionsCheckedService;

    /***
     *@author: Leslie
     *@Date 2018/1/29 17:34
     *@param: [map]
     *@return com.wangzhixuan.commons.result.RespResult<java.lang.String>
     *@throws
     *@Description:增新增查
     */
    @ResponseBody
    @RequestMapping(value="/addChecked")
    @RequiresRoles(value = { "admin", "system" },logical= Logical.OR)
    public RespResult<String> addChecked(@RequestBody Map<String,Object> map){
       RespResult<String> result=new RespResult<>();
       iquestionsCheckedService.addChecked(map,getShiroUser());
       result.getSuccess("抽查成功");
       return  result;
   }
   /**
    *@author: Leslie
    *@Date 2018/2/2 14:18
    *@param: [map]
    *@return com.wangzhixuan.commons.result.RespResult<com.wangzhixuan.commons.result.PageInfo>
    *@throws
    *@Description:抽查条件查询
    */
   @RequiresRoles(value = { "admin", "system" },logical= Logical.OR)
   @ResponseBody
   @RequestMapping(value = "/selectData")
    public RespResult<PageInfo> selectData(@RequestBody Map<String,Object> map){
       RespResult<PageInfo> result = new RespResult<>();
       CheckedQuery vo = BeanUtils.mapToBean(map,CheckedQuery.class);
       vo.setExeUserId(getShiroUser().getId());
       PageInfo  pageInfo = iquestionsCheckedService.selectData(vo, SecurityUtils.getSubject());
       result.getSuccess(pageInfo);
       return result;
   }

    @ResponseBody
    @RequestMapping(value = "queryDetailById")
    @RequiresRoles(value = {"admin","system"},logical = Logical.OR)
    public  RespResult<QuestionsCheckedVo> queryDetailById(@RequestBody Map<String,Object> map){
       RespResult<QuestionsCheckedVo> result=new RespResult<>();
      result.getSuccess(iquestionsCheckedService.queryDetailById(map));
      return result;
    }
    /**
     *@author: Leslie
     *@Date 2018/2/2 15:58
     *@param: [map]
     *@return com.wangzhixuan.commons.result.RespResult<java.lang.String>
     *@throws
     *@Description:保存抽查相关信息
     */
    @ResponseBody
    @RequestMapping(value = "/saveChecked")
    public RespResult<String> saveChecked(@RequestBody Map<String,Object> map){
        RespResult<String> result=new RespResult<>();
        iquestionsCheckedService.saveChecked(map,getShiroUser());
        result.getSuccess("抽查成功");
        return result;
    }
}
