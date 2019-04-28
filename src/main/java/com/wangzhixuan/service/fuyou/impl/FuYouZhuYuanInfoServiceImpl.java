package com.wangzhixuan.service.fuyou.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.fuyou.FuYouZhuYuanInfoMapper;
import com.wangzhixuan.model.fuyou.FuYouMenZhenInfo;
import com.wangzhixuan.model.fuyou.FuYouZhuYuanInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouZhuYuanInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouZhuYuanInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@Service
public class FuYouZhuYuanInfoServiceImpl extends ServiceImpl<FuYouZhuYuanInfoMapper,FuYouZhuYuanInfo> implements IFuYouZhuYuanInfoService{
    @Override
    public void saveOrUpdate(FuYouZhuYuanInfoVo vo) throws SysException {
        if (vo.getId() == null){
            insert(vo);
        }else {
            updateById(vo);
        }
    }

    @Override
    public PageInfo selectedData(FuYouZhuYuanInfoVo vo) throws SysException {

        return null;
    }

    @Override
    public void deleteByHzId(Long zyId) throws SysException {
        EntityWrapper<FuYouZhuYuanInfo> wrapper = new EntityWrapper<>();
        wrapper.where("zy_id={0}", zyId);
        delete(wrapper);
    }

    @Override
    public List<FuYouZhuYuanInfoVo> selectByHzId(Long zyId) throws SysException {
        List<FuYouZhuYuanInfoVo> vos = null;
        EntityWrapper<FuYouZhuYuanInfo> wrapper = new EntityWrapper<>();
        wrapper.where("zy_id={0}", zyId);
        List<FuYouZhuYuanInfo> fuYouZhuYuanInfos = selectList(wrapper);
        if (fuYouZhuYuanInfos != null && fuYouZhuYuanInfos.size() > 0) {
            vos = new ArrayList<>();
            for (FuYouZhuYuanInfo fuYouZhuYuanInfo : fuYouZhuYuanInfos) {
                vos.add(BeanUtils.copy(fuYouZhuYuanInfo, FuYouZhuYuanInfoVo.class));
            }
        }
        return vos;
    }
}
