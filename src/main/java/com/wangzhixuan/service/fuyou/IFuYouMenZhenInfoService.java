package com.wangzhixuan.service.fuyou;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.fuyou.FuYouMenZhenInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public interface IFuYouMenZhenInfoService extends IService<FuYouMenZhenInfo>{
    /**
     * 保存或更新
     * @param vo
     * @throws SysException
     */
    void saveOrUpdate(FuYouMenZhenInfoVo vo) throws SysException;

    PageInfo selectedData(FuYouMenZhenInfoVo vo)throws SysException;

    /**
     * 根据门诊患者ID删除
     *
     * @param mzId
     * @throws SysException
     */
    void deleteByHzId(Long mzId) throws SysException;
    /**
     * 根据患者ID查询
     *
     * @param mzId
     * @return
     * @throws SysException
     */
    List<FuYouMenZhenInfoVo> selectByHzId(Long mzId) throws SysException;
}
