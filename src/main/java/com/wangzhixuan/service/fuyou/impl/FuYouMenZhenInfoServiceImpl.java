package com.wangzhixuan.service.fuyou.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.fuyou.FuYouMenZhenInfoMapper;
import com.wangzhixuan.model.fuyou.FuYouHuanZheInfo;
import com.wangzhixuan.model.fuyou.FuYouMenZhenInfo;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouMenZhenInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@Service
public class FuYouMenZhenInfoServiceImpl extends ServiceImpl<FuYouMenZhenInfoMapper,FuYouMenZhenInfo> implements IFuYouMenZhenInfoService{

    @Autowired
    private IFuYouMenZhenInfoService fuYouMenZhenInfoService;
    @Autowired
    private FuYouMenZhenInfoMapper fuYouMenZhenInfoMapper;

    @Override
    public void saveOrUpdate(FuYouMenZhenInfoVo vo) throws SysException {
        if(vo.getMzId() == null){
            insert(vo);
        }else {
            updateById(vo);
        }
    }

    @Override
    public PageInfo selectedData(FuYouMenZhenInfoVo vo) throws SysException {
        return null;
    }

    @Override
    public void deleteByHzId(Long mzId) throws SysException {
        EntityWrapper<FuYouMenZhenInfo> wrapper = new EntityWrapper<>();
        wrapper.where("mz_id={0}", mzId);
        delete(wrapper);
    }

    @Override
    public List<FuYouMenZhenInfoVo> selectByHzId(Long mzId) throws SysException {
        List<FuYouMenZhenInfoVo> vos = null;
        EntityWrapper<FuYouMenZhenInfo> wrapper = new EntityWrapper<>();
        wrapper.where("mz_id={0}", mzId);
        List<FuYouMenZhenInfo> fuYouMenZhenInfos = selectList(wrapper);
        if (fuYouMenZhenInfos != null && fuYouMenZhenInfos.size() > 0) {
            vos = new ArrayList<>();
            for (FuYouMenZhenInfo fuYouMenZhenInfo : fuYouMenZhenInfos) {
                vos.add(BeanUtils.copy(fuYouMenZhenInfo, FuYouMenZhenInfoVo.class));
            }
        }

        return vos;
    }


}
