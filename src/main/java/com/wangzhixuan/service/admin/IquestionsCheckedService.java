package com.wangzhixuan.service.admin;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.admin.QuestionsChecked;
import com.wangzhixuan.model.vo.admin.CheckedQuery;
import com.wangzhixuan.model.vo.admin.QuestionsCheckedVo;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * Created by Leslie on 2018/1/29.
 *
 * @author: Leslie
 * @TIME:16:34
 * @Date:2018/1/29 Description:
 */
public interface IquestionsCheckedService extends IService<QuestionsChecked>{
    /***
     *@author: Leslie
     *@Date 2018/1/29 17:35
     *@param: map
     *@return void
     *@throws
     *@Description:添加抽查
     */
    void addChecked(Map<String,Object> map, ShiroUser user);
   /***
    *@author: Leslie
    *@Date 2018/1/31 17:01
    *@param: [pageInfo]
    *@return com.wangzhixuan.commons.result.PageInfo
    *@throws
    *@Description:各种条件查询，分页
    */
    PageInfo selectData(CheckedQuery checkedQuery, Subject subject);
    /**
     *@author: Leslie
     *@Date 2018/2/2 14:48
     *@param: [map]
     *@return com.wangzhixuan.model.vo.admin.CheckedQueryVo
     *@throws
     *@Description:查询问卷详细信息
     */
    QuestionsCheckedVo queryDetailById(Map<String, Object> map);
    /**
     *@author: Leslie
     *@Date 2018/2/2 16:00
     *@param: [map]
     *@return void
     *@throws
     *@Description:保存抽查结果
     */
    void saveChecked(Map<String, Object> map,ShiroUser user);

}
