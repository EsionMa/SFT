package com.wangzhixuan.service.wenjuan;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.TiMuShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.TiMuShiLi;
import com.wangzhixuan.service.tj.WenJuanTongJiBo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITiMuShiLiService extends IService<TiMuShiLi> {
    void saveOrUpdate(TiMuShiLiVo vo, ShiroUser user) throws SysException;

    void saveOrUpdate(List<TiMuShiLiVo> vo, Long wjslId, Long wjId, Date time, Long deptId, Long doctorId, ShiroUser user, Long itemId) throws SysException;

    TiMuShiLiVo queryByWjslId(Long wjslId,Long itemId, Long tmId, Long xxId) throws SysException;

    boolean deleteBySlId(Long slId) throws SysException;
    /***
     *@author: Leslie
     *@Date 2018/1/24 10:13
     *@param: [wjId, tmId]
     *@return int
     *@throws
     *@Description:修改下，根据问卷id和实例id判断共有多少个实际答题数
     */
    int countWjIdAndTmId(Long wjId, Long id) throws SysException;

    int countWjIdAndTmIdAndXxId(Long wjId, Long tmId, Long xxId, WenJuanTongJiBo bo) throws SysException;

    List<TiMuShiLi> queryByWjIdAndTmId(Long wjId, Long tmId, WenJuanTongJiBo bo) throws SysException;
   /**
    *@author: Leslie
    *@Description: 通过科室，时间，题目id,计算某个选项的数量
    *@Date 2018/1/19 14:45
    *@Param: map
    *@return void
    *@throws
    */
    Integer countByCondition(Map<String, Object> map);

   /***
    *@author: Leslie
    *@Date 2018/1/24 15:52
    *@param: [page, wjId]
    *@return java.util.List<com.wangzhixuan.model.vo.WenJuanVo>
    *@throws
    *@Description:通过问卷id得到问卷实例id集合
    */
    List<WenJuanVo> queryWjByWjId(Page<Map<String,Object>> page, Long wjId);
}
