package com.wangzhixuan.mapper.wenjuan;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.WenJuanItem;
import com.wangzhixuan.model.wenjuan.WenJuanShiLiItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2018/1/26.
 *
 * @author: Leslie
 * @TIME:16:30
 * @Date:2018/1/26 Description:问卷实例中间表Mapper
 */
public interface WenJuanShiLiItemMapper extends BaseMapper<WenJuanShiLiItem>{
    /***
     *@author: Leslie
     *@Date 2018/1/29 11:15
     *@param: [id]
     *@return void
     *@throws
     *@Description:删除某个问卷实例相关的问卷
     */
    void deleteByWjslId(@Param("id") Long id);
    /***
     *@author: Leslie
     *@Date 2018/1/29 11:30
     *@param: [id]
     *@return java.lang.Integer
     *@throws
     *@Description:计算某个问卷的回答数有多少
     */
    Integer countByWjId(@Param("id") Long id);
   /***
    *@author: Leslie
    *@Date 2018/1/29 11:36
    *@param: [page, wjId]
    *@return java.util.List<com.wangzhixuan.model.vo.WenJuanVo>
    *@throws
    *@Description:根据问卷id，查询已回答的问卷id中的
    */
    List<WenJuanVo> queryWjByWjId( Pagination page, @Param("wjId") Long wjId);
   /**
    *@author: Leslie
    *@Date 2018/2/1 17:22
    *@param: [wjslId]
    *@return java.util.List<com.wangzhixuan.model.wenjuan.WenJuanShiLiItem>
    *@throws
    *@Description:通过问卷实例id查找问卷
    */
    List<WenJuanShiLiItem> listWenjuans(@Param("wjslId") Long wjslId);
}
