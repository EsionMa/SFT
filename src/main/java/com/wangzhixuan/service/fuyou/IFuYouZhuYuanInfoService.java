package com.wangzhixuan.service.fuyou;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.fuyou.FuYouZhuYuanInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public interface IFuYouZhuYuanInfoService extends IService<FuYouZhuYuanInfo>{
    /**
     * 保存或更新
     * @param vo
     * @throws SysException
     */
    void saveOrUpdate(FuYouZhuYuanInfoVo vo) throws SysException;

    /**
     * 暂未使用
     *  查询
     * @param vo
     * @return
     * @throws SysException
     */
    PageInfo selectedData(FuYouZhuYuanInfoVo vo)throws SysException;

    /**
     * 根据患者ID删除
     * @param zyId
     * @throws SysException
     */
    void deleteByHzId(Long zyId) throws SysException;
    /**
     * 根据患者ID查询
     *
     * @param zyId
     * @return
     * @throws SysException
     */
    List<FuYouZhuYuanInfoVo> selectByHzId(Long zyId) throws SysException;
}
