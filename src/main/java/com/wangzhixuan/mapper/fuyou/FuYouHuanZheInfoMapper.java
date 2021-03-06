package com.wangzhixuan.mapper.fuyou;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.fuyou.FuYouHuanZheInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public interface FuYouHuanZheInfoMapper extends BaseMapper<FuYouHuanZheInfo> {

	List<FuYouHuanZheInfoVo> selectByHzId(@Param("hzId") Long hzId);
}
