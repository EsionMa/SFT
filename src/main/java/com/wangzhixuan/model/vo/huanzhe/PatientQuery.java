package com.wangzhixuan.model.vo.huanzhe;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Leslie on 2018/1/2.
 *
 * @author: Leslie
 * @TIME:14:46
 * @Date:2018/1/2 Description:
 */
public class PatientQuery implements Serializable{
    private static final long serialVersionUID = 1771978387967716243L;
    private Date boStTime;
    private Date boEdTime;
    private List<String> ids;
    private String zyNo;

    public Date getBoStTime() {
        return boStTime;
    }

    public void setBoStTime(Date boStTime) {
        this.boStTime = boStTime;
    }

    public Date getBoEdTime() {
        return boEdTime;
    }

    public void setBoEdTime(Date boEdTime) {
        this.boEdTime = boEdTime;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getZyNo() {
        return zyNo;
    }

    public void setZyNo(String zyNo) {
        this.zyNo = zyNo;
    }
}
