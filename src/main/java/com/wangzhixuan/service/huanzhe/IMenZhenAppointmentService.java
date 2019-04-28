package com.wangzhixuan.service.huanzhe;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.huanzhe.MenZhenAppointment;
import com.wangzhixuan.model.vo.huanzhe.MenZhenAppointmentVo;

import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2018/1/31 0031
 */
public interface IMenZhenAppointmentService extends IService<MenZhenAppointment> {

    /**
     * 分页查询所有记录
     * @param vo
     * @return
     * @throws SysException
     */
    PageInfo selectedDataGrid(MenZhenAppointmentVo vo) throws SysException;

    /**
     *  添加和修改
     * @param vo
     * @return
     * @throws SysException
     */
     void saveAddUpdata(MenZhenAppointmentVo vo, ShiroUser user)throws SysException;

    void delete(Long id) throws SysException;

    MenZhenAppointmentVo findById(Long id) throws SysException;
   /**
    *@author: Leslie
    *@Date 2018/2/27 17:29
    *@param: [map]
    *@return java.lang.Integer
    *@throws
    *@Description:得到一定时间段应约人数
    */
    Integer getCount(Map<String, Object> map);
}
