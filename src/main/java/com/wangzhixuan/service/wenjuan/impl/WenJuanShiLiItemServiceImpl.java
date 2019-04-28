package com.wangzhixuan.service.wenjuan.impl;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.mapper.wenjuan.WenJuanShiLiItemMapper;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.WenJuanItem;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;
import com.wangzhixuan.model.wenjuan.WenJuanShiLiItem;
import com.wangzhixuan.service.wenjuan.IWenJuanShiLiItemService;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 2018/1/26.
 *
 * @author: Leslie
 * @TIME:16:32
 * @Date:2018/1/26 Description:实现层
 */
@Service
public class WenJuanShiLiItemServiceImpl extends ServiceImpl<WenJuanShiLiItemMapper,WenJuanShiLiItem> implements IWenJuanShiLiItemService {
    @Autowired
    private WenJuanShiLiItemMapper itemMapper;
    /****
     *@author: Leslie
     *@Date 2018/1/26 16:37
     *@param: [vo]
     *@return void
     *@throws
     *@Description:保存或者是更新中间表
     */
    @Override
    public Long wenJuanShiLiItemSave(WenJuanShiLi vo, WenJuanVo wenJuanVo) {
        //再新增
        WenJuanShiLiItem wenJuanShiLiItem=new WenJuanShiLiItem();
        //装入患者等相关信息
        wenJuanShiLiItem.setWjslId(vo.getId());
        wenJuanShiLiItem.setDeptName(vo.getDeptName());
        wenJuanShiLiItem.setDeptId(vo.getDeptId());
        wenJuanShiLiItem.setExeUserId(vo.getUpdateUserId());
        wenJuanShiLiItem.setHzId(vo.getHzId());
        wenJuanShiLiItem.setHzName(vo.getHzName());
        wenJuanShiLiItem.setCreateTime(vo.getUpdateTime());
        wenJuanShiLiItem.setFaType(vo.getFaType());
        //装入问卷等相关信息
        wenJuanShiLiItem.setWjId(wenJuanVo.getId());
        wenJuanShiLiItem.setWjzt(wenJuanVo.getWjzt());
        wenJuanShiLiItem.setScore(wenJuanVo.getGrade());
        insert(wenJuanShiLiItem);
        return wenJuanShiLiItem.getId();
    }

    @Override
    public void deleteByWjslId( Long id) {
        if (id==null){
            throw new SysException("问卷实例id为空");
        }
        itemMapper.deleteByWjslId(id);
    }

    @Override
    public Integer countByWjId(Long id) {
        if (id==null){
            return 0;
        }
        return itemMapper.countByWjId(id);
    }

    @Override
    public List<WenJuanVo> queryWjByWjId(Page<Map<String, Object>> page, Long wjId) {
        if (wjId==null){
            return null;
        }
        return itemMapper.queryWjByWjId(page,wjId);
    }

    @Override
    public List<WenJuanShiLiItem> listWenjuans(Long wjslId) {
        if (wjslId==null){
            throw  new SysException("问卷实例id为空");
        }
        return itemMapper.listWenjuans(wjslId);
    }
}
