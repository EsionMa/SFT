package com.wangzhixuan.mapper.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.admin.QuestionsChecked;
import com.wangzhixuan.model.vo.admin.CheckedQuery;
import com.wangzhixuan.model.vo.admin.QuestionsCheckedVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2018/1/29.
 *
 * @author: Leslie
 * @TIME:16:37
 * @Date:2018/1/29 Description:
 */
public interface QuestionsCheckedMapper extends BaseMapper<QuestionsChecked>{
    /***
     *@author: Leslie
     *@Date 2018/1/31 17:15
     *@param: [pagination, condition]
     *@return java.util.List<com.wangzhixuan.model.vo.admin.QuestionsCheckedVo>
     *@throws
     *@Description:各种条件查询抽查
     */
    List<QuestionsCheckedVo> slectData(Pagination pagination,CheckedQuery condition);
}
