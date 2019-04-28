package com.wangzhixuan.model.vo.wj;

import com.wangzhixuan.model.statics.OutStatics;

import java.io.Serializable;

/**
 * Created by Leslie on 2017/12/25.
 *
 * @author: Leslie
 * @TIME:17:00
 * @Date:2017/12/25 Description:
 */
public class GongZuoTongJiVo extends OutStatics implements Serializable{

    private static final long serialVersionUID = -5682743536265907292L;
    private Integer userId;//随访人id
    private String userName;//随访人姓名
    private Integer callTimes;//随访拨打电话次数

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(Integer callTimes) {
        this.callTimes = callTimes;
    }
}
