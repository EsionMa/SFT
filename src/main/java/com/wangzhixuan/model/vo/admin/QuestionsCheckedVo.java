package com.wangzhixuan.model.vo.admin;

import com.wangzhixuan.model.admin.QuestionsChecked;
import com.wangzhixuan.model.vo.WenJuanVo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2018/1/31.
 *
 * @author: Leslie
 * @TIME:16:56
 * @Date:2018/1/31 Description: 返回分页的时候用的
 */
public class QuestionsCheckedVo extends QuestionsChecked implements Serializable{
    private static final long serialVersionUID = -23473974350166465L;
    private Integer page = 1;
    private Integer rows = 25;

    private WenJuanVo wenJuanVo;

    public WenJuanVo getWenJuanVo() {
        return wenJuanVo;
    }

    public void setWenJuanVo(WenJuanVo wenJuanVo) {
        this.wenJuanVo = wenJuanVo;
    }

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


}
