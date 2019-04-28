package com.wangzhixuan.model.vo.wj;

import com.wangzhixuan.model.statics.OutStatics;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:17:54
 * @Date:2017/12/25 Description:
 */
public class SuiFangTongJiVo implements Serializable{

    private static final long serialVersionUID = -8874803268529181989L;

    private List<OutStatics> vos;
    private String faName;

    public List<OutStatics> getVos() {
        return vos;
    }

    public void setVos(List<OutStatics> vos) {
        this.vos = vos;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }
}
