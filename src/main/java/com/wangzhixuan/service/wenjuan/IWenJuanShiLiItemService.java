package com.wangzhixuan.service.wenjuan;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.WenJuanItem;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;
import com.wangzhixuan.model.wenjuan.WenJuanShiLiItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2018/1/26.
 *
 * @author: Leslie
 * @TIME:16:31
 * @Date:2018/1/26 Description:问卷实例中间表Service
 */
public interface IWenJuanShiLiItemService extends IService<WenJuanShiLiItem>{
    /***
     *@author: Leslie
     *@Date 2018/1/26 16:35
     *@param: [vo]
     *@return void
     *@throws
     *@Description:传入一个问卷实例vo,根据情况保存或是更新
     */
    Long  wenJuanShiLiItemSave(WenJuanShiLi shiLi, WenJuanVo wenJuanVo);
   /***
    *@author: Leslie
    *@Date 2018/1/29 11:12
    *@param: [id]
    *@return void
    *@throws
    *@Description:删除问卷实例id相关的问卷
    */
    void deleteByWjslId( Long id);
   /***
    *@author: Leslie
    *@Date 2018/1/29 11:30
    *@param: [id]
    *@return java.lang.Integer
    *@throws
    *@Description:计算问卷id的某个答题数
    */
    Integer countByWjId(Long id);
   /***
    *@author: Leslie
    *@Date 2018/1/29 11:34
    *@param: [page, wjId]
    *@return java.util.List<com.wangzhixuan.model.vo.WenJuanVo>
    *@throws
    *@Description:根据问卷id查询出其列表文卷
    */
    List<WenJuanVo> queryWjByWjId(Page<Map<String,Object>> page, Long wjId);
    /**
     *@author: Leslie
     *@Date 2018/2/1 17:18
     *@param: [wjslId]
     *@return java.util.List<com.wangzhixuan.model.wenjuan.WenJuanShiLiItem>
     *@throws
     *@Description:通过问卷实例id得到所做的问卷
     */
    List<WenJuanShiLiItem> listWenjuans(Long wjslId);
}
