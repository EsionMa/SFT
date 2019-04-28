package com.wangzhixuan.model.vo;

import java.util.List;

/**
 * Created by Leslie on 2018/1/29.
 *
 * @author: Leslie
 * @TIME:17:51
 * @Date:2018/1/29 Description:接收抽查参数
 */
public class CheckedVo {
    private Long wjId;
    private List<WenJuanShiLiVo> wenJuanShiLiVos;

    public Long getWjId() {
        return wjId;
    }

    public void setWjId(Long wjId) {
        this.wjId = wjId;
    }

    public List<WenJuanShiLiVo> getWenJuanShiLiVos() {
        return wenJuanShiLiVos;
    }

    public void setWenJuanShiLiVos(List<WenJuanShiLiVo> wenJuanShiLiVos) {
        this.wenJuanShiLiVos = wenJuanShiLiVos;
    }
}
