package com.wangzhixuan.model.vo.phone;


import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.phone.Phone;

import java.util.Date;

/**
 * Created by Leslie on 2017/8/8.
 * TIME:9:59
 * Date:2017/8/8.
 * Description:
 */
public class PhoneVo extends Phone {
    private HuanZheInfo huanZheInfo;
    private SysUser user;
    private Integer page = 1;
    private Integer rows = 15;

    private String hzName;

    private String holdTime;//通话时间，为String类型
    //通话时间直接返回为String类型
    public String getHoldTime() {
        Date creTime=getCreTime();
        Date endTime=getEndTime();
        if (creTime!=null){
            if (endTime!=null) {
                long differ = endTime.getTime() - creTime.getTime();
                long diffSeconds = differ / 1000 % 60;
                long diffMinutes = differ / (60 * 1000) % 60;
                return diffMinutes + "分" + diffSeconds + "秒";
            }
        }
        return "";
    }


    public String getHzName() {
        return hzName;
    }

    public void setHzName(String hzName) {
        this.hzName = hzName;
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

    public HuanZheInfo getHuanZheInfo() {
        return huanZheInfo;
    }

    public void setHuanZheInfo(HuanZheInfo huanZheInfo) {
        this.huanZheInfo = huanZheInfo;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
