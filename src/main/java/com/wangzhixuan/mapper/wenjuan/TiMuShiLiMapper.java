package com.wangzhixuan.mapper.wenjuan;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.vo.TiMuShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.TiMuShiLi;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TiMuShiLiMapper extends BaseMapper<TiMuShiLi>{
	Long save(TiMuShiLiVo tmsl);

    Integer countByCondition(Map<String, Object> map);
    /***
     *@author: Leslie
     *@Date 2018/1/24 14:37
     *@param: [wjId]
     *@return java.lang.Integer
     *@throws
     *@Description:通问卷Id统计有多少人回答这个问卷
     */
    Integer countByWjId(@Param("wjId") Long wjId);
   /***
    *@author: Leslie
    *@Date 2018/1/24 15:34
    *@param: [page, wjId]
    *@return java.util.List<com.wangzhixuan.model.vo.WenJuanVo>
    *@throws
    *@Description:根据问卷id找出用户相关信息
    */
    List<WenJuanVo> queryWjByWjId( Pagination page, @Param("wjId") Long wjId);
}
