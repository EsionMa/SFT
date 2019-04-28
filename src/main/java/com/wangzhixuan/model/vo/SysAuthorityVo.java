package com.wangzhixuan.model.vo;


import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.model.SysAuthority;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leslie on 2017/12/6.
 *
 * @author: Leslie
 * @TIME:16:53
 * @Date:2017/12/6 Description:针对easyUi dateTimebox 时间转换
 */
public class SysAuthorityVo extends SysAuthority implements Serializable{
    private static final long serialVersionUID = 266694984572183267L;

    private String endTime;

    public String getEndTime() {
        if (this.getTime()!=null){
           return DateUtils.format(this.getTime(),"yyyy/MM/dd HH:mm:ss");
        }else {
            return null;
        }
    }

    /**
     * 设置调用父类的时间setTime方法，达到修改的目的
     * @param endTime
     */
    public void setTime(String endTime){
        if (endTime!=null){
            this.setTime(DateUtils.parse(endTime));
        }
    }
    /**
     * 直接将父类的时间转换过来
     */
    public void setEndTime(String endTime) {
      this.endTime=endTime;
    }
}
