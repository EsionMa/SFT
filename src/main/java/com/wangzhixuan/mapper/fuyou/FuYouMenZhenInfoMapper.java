package com.wangzhixuan.mapper.fuyou;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wangzhixuan.model.fuyou.FuYouMenZhenInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.model.vo.fuyou.FuYouMenZhenInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public interface FuYouMenZhenInfoMapper extends BaseMapper<FuYouMenZhenInfo> {

    List<FuYouMenZhenInfoVo> selectedById(@Param("mzId")String mzId);
    List<Map<String,Object>> selectedData(Pagination page, Map<String,Object> params);
}
