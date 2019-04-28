package com.wangzhixuan.mapper.wenjuan;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.wenjuan.WenJuanItem;

public interface WenJuanItemMapper extends BaseMapper<WenJuanItem>{
	Integer deleteByWenJuanId(@Param("wjId") Long wenJuanId);
	List<WenJuanItem> queryByWenJuanId(@Param("wjId") Long wenJuanId);
}
