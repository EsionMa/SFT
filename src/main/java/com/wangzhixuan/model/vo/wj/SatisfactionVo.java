package com.wangzhixuan.model.vo.wj;

import com.wangzhixuan.model.phone.Phone;
import com.wangzhixuan.model.pj.PingJia;
import com.wangzhixuan.model.vo.phone.PhoneVo;
import com.wangzhixuan.model.vo.pj.PingJiaVo;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;
import com.wangzhixuan.model.wenjuan.WenJuanShiLiItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Leslie on 2018/2/1.
 *
 * @author: Leslie
 * @TIME:16:46
 * @Date:2018/2/1 Description:当时随访的相关信息
 */
public class SatisfactionVo extends WenJuanShiLi implements Serializable{
    private static final long serialVersionUID = -7867021943541783178L;
    /**
     * 方案名字
     */
    private String faName;
    /**
     * 一堆问卷
     */
    private List<WenJuanShiLiItem> itemVos;
    /**
     * 一堆评价
     */
    private List<PingJiaVo> pingJias;
    /**
     * 一堆录音
     */
    private List<Phone> phoneVos;


    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public List<WenJuanShiLiItem> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<WenJuanShiLiItem> itemVos) {
        this.itemVos = itemVos;
    }

    public List<PingJiaVo> getPingJias() {
        return pingJias;
    }

    public void setPingJias(List<PingJiaVo> pingJias) {
        this.pingJias = pingJias;
    }

    public List<Phone> getPhoneVos() {
        return phoneVos;
    }

    public void setPhoneVos(List<Phone> phoneVos) {
        this.phoneVos = phoneVos;
    }
}
