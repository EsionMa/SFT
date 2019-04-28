package com.wangzhixuan.service.admin.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.admin.QuestionsCheckedMapper;
import com.wangzhixuan.model.admin.QuestionsChecked;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.TiMuShiLiVo;
import com.wangzhixuan.model.vo.WenJuanShiLiVo;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.vo.admin.CheckedQuery;
import com.wangzhixuan.model.vo.admin.QuestionsCheckedVo;
import com.wangzhixuan.model.wenjuan.WenJuan;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;
import com.wangzhixuan.service.admin.IquestionsCheckedService;
import com.wangzhixuan.service.wenjuan.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Leslie on 2018/1/29.
 *
 * @author: Leslie
 * @TIME:16:36
 * @Date:2018/1/29 Description:
 */
@Service
public class QuestionsCheckedServiceImpl extends ServiceImpl<QuestionsCheckedMapper,QuestionsChecked> implements IquestionsCheckedService {
   @Autowired
   private IWenJuanService wenJuanService;
   @Autowired
   private QuestionsCheckedMapper questionsCheckedMapper;
   @Autowired
   private ITiMuService tiMuService;
   @Autowired
   private ITiMuShiLiService tiMuShiLiService;
   @Autowired
   private ITiMuXuanXiangService xuanXiangService;
   @Autowired
   private IWenJuanShiLiItemService wenJuanShiLiItemService;
    @Override
    @SuppressWarnings(value = "unchecked")
    public void addChecked(Map<String,Object> map, ShiroUser user) {
        if (user==null){
            throw new SysException("登录用户失效");
        }
        if (map.get("wjId")==null||"".equals(map.get("wjId"))){
            throw new SysException("请选择一张问卷");
        }
        Object wenjuans=map.get("shiLis");

        if (wenjuans==null||wenjuans==""){
            throw new SysException("请至少选择一个执行人");
        }
        List<Map<String,Object>> maps=(ArrayList<Map<String,Object>>)wenjuans;
        Long Id=Long.parseLong(map.get("wjId").toString());
        WenJuan wenJuan=wenJuanService.selectById(Id);
        if (wenJuan==null){
            throw new SysException("该问卷不存在");
        }
        for (Map<String,Object> wenjuan: maps){
            WenJuanShiLiVo item= BeanUtils.mapToBean(wenjuan,WenJuanShiLiVo.class);
           QuestionsChecked questionsChecked=new QuestionsChecked();
           //问卷id
           questionsChecked.setWjId(Id);
           //问卷主题
           questionsChecked.setWjName(wenJuan.getWjzt());
           //实例id
           questionsChecked.setWjslId(item.getId());
           //科室名
           questionsChecked.setDeptName(item.getDeptName());
           //科室Id
           questionsChecked.setDeptId(item.getDeptId());
           //调查时间
           questionsChecked.setDcTime(item.getDcTime());
           //当时调查人id
           questionsChecked.setDcUserId(item.getUpdateUserId());
           //患者名
            questionsChecked.setHzName(item.getHzName());
            //离院时间
            questionsChecked.setOtherTime(item.getOtherTime());
            //方案类型
            questionsChecked.setFaType(item.getFaType());
            //患者Id
            questionsChecked.setHzId(item.getHzId());
            questionsChecked.setFaId(item.getFaId());
            questionsChecked.setStatus("WEICHOUCHA");
            questionsChecked.setHzly(item.getHzly());
            questionsChecked.setCreateTime(new Date());
            questionsChecked.setCreateUserId(user.getId());
            questionsChecked.setDcUserName(item.getZxname());
            questionsChecked.setDoctorName(item.getDoctorName());
            insert(questionsChecked);
        }
    }

    @Override
    public PageInfo selectData(CheckedQuery checkedQuery, Subject subject) {
        PageInfo pageInfo=new PageInfo(checkedQuery.getPage(),checkedQuery.getRows());
        Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
        if (checkedQuery.getExeUserId()==null){
            throw new SysException("请重新登录再试");
        }
        //抽查是只有管理员，还是谁创建谁执行，权限分配？
        if (subject.hasRole("admin")||subject.hasRole("system")){
            checkedQuery.setExeUserId(null);
        }
        List<QuestionsCheckedVo> questionsCheckedVos=questionsCheckedMapper.slectData(page,checkedQuery);
        pageInfo.setRows(questionsCheckedVos);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public QuestionsCheckedVo queryDetailById(Map<String, Object> map) {
        if (map==null||map.get("id")==null||map.get("id")==""){
            throw new SysException("抽查id为空");
        }
        Long id=Long.parseLong(map.get("id").toString());
        QuestionsChecked checkedQuery= questionsCheckedMapper.selectById(id);
        QuestionsCheckedVo vo=BeanUtils.copy(checkedQuery,QuestionsCheckedVo.class);
        WenJuanVo wenJuanVo=new WenJuanVo();
        wenJuanVo.setId(vo.getId());
        wenJuanVo.setWjslId(vo.getWjslId());
        wenJuanVo.setWjzt(vo.getWjName());
        vo.setWenJuanVo(wenJuanService.queryDetail(wenJuanVo));
        return vo;
    }

    @Override
    public void saveChecked(Map<String, Object> map,ShiroUser user) {
        if (user==null){
            throw new SysException("用户登录身份过期");
        }
        if (map==null){
            throw new SysException("无内容保存");
        }
        QuestionsCheckedVo vo=BeanUtils.mapToBean(map,QuestionsCheckedVo.class);
        if (vo==null){
            vo=new QuestionsCheckedVo();
        }
        if (vo.getId()==null){
            throw new SysException("id为空");
        }
        QuestionsChecked questionsChecked=selectById(vo.getId());
        if (questionsChecked==null){
            throw new SysException("该任务不是抽查任务");
        }
        if (vo.getStatus()==null||"".equals(vo.getStatus())){
            throw new SysException("调查状态不能为空");
        }
        questionsChecked.setContent(vo.getContent());
        questionsChecked.setUpdateUserId(user.getId());
        questionsChecked.setCheckedName(user.getName());
        questionsChecked.setUpdateTime(new Date());
        if ("WEICHOUCHA".equals(vo.getStatus())){
            questionsChecked.setStatus("YICHOUCHA");
        }else {
            questionsChecked.setStatus(vo.getStatus());
        }

        //如果不是已经抽查，是其他状态，不会保存问卷
        if ("YICHOUCHA".equals(questionsChecked.getStatus())){
            WenJuanVo wenJuanVo=vo.getWenJuanVo();
            Double wjScore = 0D;
            List<TiMuShiLiVo> tmsls = wenJuanVo.getTmsls();
            // 计算每道题的分数
            if (tmsls != null && tmsls.size() > 0) {
                for (TiMuShiLiVo timusl : tmsls) {
                    if ("1".equals(timusl.getTxxz())) {
                        Long tmId = timusl.getTmId();
                        Long xxId = timusl.getXxId();
                        Double tmScore = tiMuService.selectScoreById(tmId);
                        Double scorePercent = xuanXiangService.selectPercent(xxId);
                        timusl.setScore(tmScore * scorePercent);
                        wjScore += tmScore * scorePercent;
                    }
                }
                wenJuanVo.setGrade(wjScore);
                //将调查的内容全部换成问卷实例
                WenJuanShiLi wenJuanShiLi=new WenJuanShiLi();
                BeanUtils.copyProperties(questionsChecked,wenJuanShiLi);
                //表示为抽查所做的问卷
                wenJuanShiLi.setFaType(9);
                wenJuanShiLi.setId(questionsChecked.getWjslId());
               Long itemId=wenJuanShiLiItemService.wenJuanShiLiItemSave(wenJuanShiLi, wenJuanVo);
               //将所添加的问卷实例添加到抽查实例中
               questionsChecked.setItemId(itemId);
                tiMuShiLiService.saveOrUpdate(tmsls, questionsChecked.getWjslId(), wenJuanVo.getId(), questionsChecked.getOtherTime(),questionsChecked.getDeptId(),null,user,itemId);
            }
        }
        updateById(questionsChecked);
    }



}
