package com.wangzhixuan.model.vo;

import java.io.Serializable;

/**
 * Created by Leslie on 2018/4/11.
 *
 * @author: Leslie
 * @TIME:16:32
 * @Date:2018/4/11 Description:
 */
public class EvaCountVo implements Serializable{
    private static final long serialVersionUID = -5138597020802277482L;
    private String pjlx;
    private Integer count;

    public String getPjlx() {
        return pjlx;
    }

    public void setPjlx(String pjlx) {
        this.pjlx = pjlx;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
