package com.wangzhixuan.service.wenjuan.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.mapper.wenjuan.TiMuXuanXiangMapper;
import com.wangzhixuan.model.wenjuan.TimuXuanXiang;
import com.wangzhixuan.service.wenjuan.ITiMuXuanXiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiMuXuanXiangServiceImpl extends ServiceImpl<TiMuXuanXiangMapper, TimuXuanXiang> implements ITiMuXuanXiangService {
    @Autowired
    private TiMuXuanXiangMapper xuanXiangMapper;
    @Override
    public List<TimuXuanXiang> selectByTmId(Long tmId) {
        EntityWrapper<TimuXuanXiang> wrapper = new EntityWrapper<>();
        wrapper.where("tm_id={0}", tmId);
        return selectList(wrapper);
    }

    @Override
    public List<TimuXuanXiang> listTMxx(Long tmId) {
        if (tmId==null){
            throw new SysException("题目选项为空");
        }
        return xuanXiangMapper.listByTiMuId(tmId);
    }

    @Override
    public Double selectPercent(Long xxId) {
        Double score=xuanXiangMapper.selectPercent(xxId);
        if (xxId==null||score==null){
            return 0D;
        }
        return  score/100;
    }
}
