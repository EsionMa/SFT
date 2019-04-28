package com.wangzhixuan.model.vo.zhishiku;

import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.model.icd.Icd;
import com.wangzhixuan.model.zhishiku.ZhiShiKu;
import com.wangzhixuan.model.zhishiku.ZhiShiKuNode;

import java.util.List;

public class NodeVo extends ZhiShiKuNode {

    private PageInfo zhiShiKuVos;

    private List<Icd> icds;

    public PageInfo getZhiShiKuVos() {
        return zhiShiKuVos;
    }

    public void setZhiShiKuVos(PageInfo zhiShiKuVos) {
        this.zhiShiKuVos = zhiShiKuVos;
    }

    public List<Icd> getIcds() {
        return icds;
    }

    public void setIcds(List<Icd> icds) {
        this.icds = icds;
    }
}
