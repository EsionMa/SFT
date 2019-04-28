package com.wangzhixuan.service.wenjuan;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.model.wenjuan.TimuXuanXiang;

import java.util.List;

public interface ITiMuXuanXiangService extends IService<TimuXuanXiang>{
    List<TimuXuanXiang> selectByTmId(Long tmId);
    /**
     *@author: Leslie
     *@Description: 通过tmId 得到题目选项
     *@Date 2018/1/19 16:30
     *@param: [tmId]
     *@return java.util.List<com.wangzhixuan.model.wenjuan.TimuXuanXiang>
     *@throws
     */
    List<TimuXuanXiang> listTMxx(Long tmId);
    /***
     *@author: Leslie
     *@Date 2018/1/26 18:01
     *@param: [xxId]
     *@return java.lang.Double
     *@throws
     *@Description: 根据选项id查询所占比重
     */
    Double selectPercent(Long xxId);
}
