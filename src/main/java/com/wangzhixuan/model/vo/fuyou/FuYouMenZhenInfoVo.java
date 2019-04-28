package com.wangzhixuan.model.vo.fuyou;

import com.wangzhixuan.model.fuyou.FuYouMenZhenInfo;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public class FuYouMenZhenInfoVo extends FuYouMenZhenInfo{
    private static final long serialVersionUID = -3696050252008901740L;
    private Integer page = 1;
    private Integer rows = 20;

    private List<MenZhenInfo> hzName;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public List<MenZhenInfo> getHzName() {
        return hzName;
    }

    public void setHzName(List<MenZhenInfo> hzName) {
        this.hzName = hzName;
    }
}
