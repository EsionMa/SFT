package com.wangzhixuan.model.vo.wj;

import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.MenZhenInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.phone.Phone;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Leslie on 2018/3/28.
 *
 * @author: Leslie
 * @TIME:18:39
 * @Date:2018/3/28 Description: 关于执行随访这一块的所有信息
 */
public class PageSatisFaction extends WenJuanShiLi implements Serializable{
    /**
     * 患者信息
     */
    private HuanZheInfo huanZheInfo;
    /**
     * 住院信息
     */
    private ZhuYuanInfo zhuYuanInfo;
    /**
     * 录音数量
     */
    private List<Phone> audios;
    /**
     *  标记
     */
    private String tagsName;
    /**
     * 执行人姓名
     */
    private String zxname;
    /**
     * 门诊信息
     */
    private MenZhenInfo menZhenInfo;
    /**
     *  出院诊断 OR 门诊诊断
     */
    private String cyzd;

    public HuanZheInfo getHuanZheInfo() {
        return huanZheInfo;
    }

    public void setHuanZheInfo(HuanZheInfo huanZheInfo) {
        this.huanZheInfo = huanZheInfo;
    }

    public ZhuYuanInfo getZhuYuanInfo() {
        return zhuYuanInfo;
    }

    public void setZhuYuanInfo(ZhuYuanInfo zhuYuanInfo) {
        this.zhuYuanInfo = zhuYuanInfo;
    }

    public List<Phone> getAudios() {
        return audios;
    }

    public void setAudios(List<Phone> audios) {
        this.audios = audios;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public String getZxname() {
        return zxname;
    }

    public void setZxname(String zxname) {
        this.zxname = zxname;
    }

    public MenZhenInfo getMenZhenInfo() {
        return menZhenInfo;
    }

    public void setMenZhenInfo(MenZhenInfo menZhenInfo) {
        this.menZhenInfo = menZhenInfo;
    }

    public String getCyzd() {
        return cyzd;
    }

    public void setCyzd(String cyzd) {
        this.cyzd = cyzd;
    }
}
