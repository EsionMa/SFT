package com.wangzhixuan.model.vo.wj;

import java.util.List;

public class ChouChaBo {
    private List<Long> wjSlId;//问卷实例Id
    private Long chouChaWjId;//抽查问卷ID

    public List<Long> getWjSlId() {
        return wjSlId;
    }

    public void setWjSlId(List<Long> wjSlId) {
        this.wjSlId = wjSlId;
    }

    public Long getChouChaWjId() {
        return chouChaWjId;
    }

    public void setChouChaWjId(Long chouChaWjId) {
        this.chouChaWjId = chouChaWjId;
    }

}
