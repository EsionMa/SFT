package com.wangzhixuan.service.huanzhe.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.huanzhe.MenZhenAppointmentMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.huanzhe.MenZhenAppointment;
import com.wangzhixuan.model.vo.huanzhe.MenZhenAppointmentVo;
import com.wangzhixuan.service.ISysDictionariesService;
import com.wangzhixuan.service.ISysOrganizationService;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.huanzhe.IMenZhenAppointmentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
@Service
public class MenZhenAppointmentService extends ServiceImpl<MenZhenAppointmentMapper,MenZhenAppointment> implements IMenZhenAppointmentService {
    @Autowired
    private ISysOrganizationService sysOrganizationService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDictionariesService sysDictionariesService;

    @Override
    public PageInfo selectedDataGrid(MenZhenAppointmentVo vo) throws SysException {
        Page<Map<String,Object>> page = new Page<>(vo.getPage(),vo.getRows());
        EntityWrapper<MenZhenAppointment> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(vo.getHzName())){
            wrapper.where("hz_name={0}",vo.getHzName());
        }
        if (vo.getDeptId()!=null){
            wrapper.where("dept_id={0}",vo.getDeptId());
        }
        if (StringUtils.isNotBlank(vo.getStatus())){
            wrapper.where("status={0}",vo.getStatus());
        }
        if (vo.getStaDate() != null) {
            wrapper.where("appointment_date>={0}", vo.getStaDate());
        }
        if (vo.getEndDate() != null) {
            wrapper.where("appointment_date<={0}", vo.getEndDate());
        }
        wrapper.orderBy("appointment_date", false);
        selectMapsPage(page,wrapper);
        for (Map<String, Object> item : page.getRecords()) {
            if (item.get("deptId") != null && item.get("deptId") != "") {
                SysOrganization deptName = sysOrganizationService.selectById(item.get("deptId").toString());
                item.put("deptName", deptName == null ? null : deptName.getName());
            }
            if (item.containsKey("doctorId")){
                SysUser user = userService.selectById(item.get("doctorId").toString());
                item.put("doctorName",user == null ? null : user.getName());
            }
            if (item.containsKey("createUserId")) {
                SysUser userName = userService.selectById(item.get("createUserId").toString());
                item.put("createUser", userName == null ? null : userName.getLoginName());
            }

        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public void saveAddUpdata(MenZhenAppointmentVo vo, ShiroUser user) throws SysException {

        if(vo.getId() == null ){
            vo.setCreateUserId(user.getId());
            vo.setCreateTime(new Date());
            insert(vo);
        }else {
            vo.setUpdateUserId(user.getId());
            vo.setUpdateTime(new Date());
            updateById(vo);
        }

    }

    @Override
    public void delete(Long id) throws SysException {
        if (id != null){
            deleteById(id);
        }
    }
    @Override
    public MenZhenAppointmentVo findById(Long id) throws SysException {
        if (id != null){
            MenZhenAppointment appointment = selectById(id);
            MenZhenAppointmentVo menZhenAppointmentVo = BeanUtils.copy(appointment,MenZhenAppointmentVo.class);
            return menZhenAppointmentVo;
        } else {
            throw new SysException(ErrorCode.ReqIdIsNull);
        }
    }

    @Override
    public Integer getCount(Map<String, Object> map) {
        if (map==null){
            throw new SysException("参数为空");
        }
        if (map.get("staDate")==null||map.get("endDate")==""){
            throw new SysException("开始时间不为空");
        }
        if (map.get("endDate")==null||map.get("endDate")==""){
            throw new SysException("结束时间不为空");
        }
        Wrapper<MenZhenAppointment> wrapper=new EntityWrapper<>();
        wrapper.where("appointment_date>={0}",map.get("staDate"));
        wrapper.where("appointment_date<{0}",map.get("endDate"));
        return selectCount(wrapper);
    }
}
