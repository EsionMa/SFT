package com.wangzhixuan.service.tj.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.DateUtils;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.model.fa.FangAnDingYi;
import com.wangzhixuan.model.statics.OutStatics;
import com.wangzhixuan.model.vo.TiMuXuanXiangVo;
import com.wangzhixuan.model.vo.wj.GongZuoTongJiVo;
import com.wangzhixuan.model.vo.wj.SuiFangTongJiVo;
import com.wangzhixuan.service.ISysRoleResourceService;
import com.wangzhixuan.service.ISysRoleService;
import com.wangzhixuan.service.ISysUserRoleService;
import com.wangzhixuan.service.fa.IFangAnDingYiService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.mapper.wenjuan.WenJuanShiLiMapper;
import com.wangzhixuan.model.SysOrganization;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.tj.TM;
import com.wangzhixuan.model.tj.TMXX;
import com.wangzhixuan.model.tj.WenJuanDaAnTongjiVo;
import com.wangzhixuan.model.wenjuan.TiMu;
import com.wangzhixuan.model.wenjuan.TiMuShiLi;
import com.wangzhixuan.model.wenjuan.TimuXuanXiang;
import com.wangzhixuan.model.wenjuan.WenJuan;
import com.wangzhixuan.model.wenjuan.WenJuanShiLi;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.impl.SysOrganizationServiceImpl;
import com.wangzhixuan.service.phone.IPhoneService;
import com.wangzhixuan.service.tj.ITongjiService;
import com.wangzhixuan.service.tj.WenJuanTongJiBo;
import com.wangzhixuan.service.wenjuan.ITiMuService;
import com.wangzhixuan.service.wenjuan.ITiMuShiLiService;
import com.wangzhixuan.service.wenjuan.ITiMuXuanXiangService;
import com.wangzhixuan.service.wenjuan.IWenJuanService;

@Service
public class TongJiServiceImpl extends ServiceImpl<WenJuanShiLiMapper, WenJuanShiLi> implements ITongjiService {
	@Autowired
	private IWenJuanService wenJuanService;
	@Autowired
	private ITiMuService tiMuService;
	@Autowired
	private ITiMuXuanXiangService tiMuXuanXiangService;
	@Autowired
	private ITiMuShiLiService tiMuShiLiService;
	@Autowired
	private WenJuanShiLiMapper wenJuanShiLiMapper;
	@Autowired
	private SysOrganizationServiceImpl sysOrganizationService;
	@Autowired
	private ISysUserService userService;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private IFangAnDingYiService fangAnDingYiService;

	private Logger logger= LoggerFactory.getLogger(TongJiServiceImpl.class);
	   /**
		*@author: Leslie
		*@Description:
		*@Date 2018/1/19 12:01
		*@Param:[map]
		*@return com.wangzhixuan.model.tj.WenJuanDaAnTongjiVo
		*@throws SysException
		*/
	@Override
	public WenJuanDaAnTongjiVo wenJuanDaAnTongJi(Map<String, Object> map) throws SysException {
		//二个参数判断
		//设置结束时间
		DateUtils.getEndTimeByStaTime(map);
		if (map.get("wjId")==null||map.get("wjId")==""){
			throw  new SysException("请选择一张问卷");
		}
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		//封装问卷相关信息
		WenJuanDaAnTongjiVo vo=new WenJuanDaAnTongjiVo();
		Long wjId=Long.parseLong(map.get("wjId").toString());
		WenJuan wenJuan=wenJuanService.selectById(wjId);
		if (wenJuan==null){
			throw new SysException("暂无此问卷");
		}
		BeanUtils.copyProperties(wenJuan,vo);
		List<TiMu> tiMus=tiMuService.listChoseByWjId(vo.getId());
		if (tiMus==null||tiMus.size()==0){
			throw new SysException("该问卷无题目");
		}
		List<TM> tms=new ArrayList<>();
		vo.setTms(tms);
		for (TiMu tiMu:tiMus){
			TM tm=new TM();
			tms.add(tm);
			BeanUtils.copyProperties(tiMu,tm);
			List<TimuXuanXiang> tmxxList= tiMuXuanXiangService.listTMxx(tiMu.getId());
			if (tmxxList==null||tmxxList.size()==0){
				throw new SysException("该题目无选项");
			}
			map.remove("xxId");
			map.put("tmId",tiMu.getId());
			tm.setCount(tiMuShiLiService.countByCondition(map));
			List<TMXX> xxList=new ArrayList<>();
			tm.setXxs(xxList);
			for (TimuXuanXiang xx:tmxxList){
				TMXX tmxx=new TMXX();
				BeanUtils.copyProperties(xx,tmxx);
				xxList.add(tmxx);
				map.put("xxId",xx.getId());
				tmxx.setChecked(tiMuShiLiService.countByCondition(map));
				tmxx.setCheckedPercent(nf.format(tm.getCount()==0?0:tmxx.getChecked()/tm.getCount()));
			}
		}
		return vo;
	}

	/**
	 * @Author: Leslie
	 * @Description:问卷实例统计
	 * @Date 2017/8/25 14:30
	 */
	@Override
	public SuiFangTongJiVo sfCount(Map<String, Object> map) throws SysException {
		DateUtils.getEndTimeByStaTime(map);
		if (map.get("faId")==null ||map.get("faId")==""){
			throw new SysException("方案类型为空");
		}
		//返回的vo 对象
		SuiFangTongJiVo vo=new SuiFangTongJiVo();
		//封装数据的对象
		List<OutStatics> bos = new ArrayList<>();
		//数据装入对象
		vo.setVos(bos);
		Long faId=Long.parseLong(map.get("faId").toString());
		List<SysOrganization> organizations=fangAnDingYiService.listOrgsByFaId(faId);
		FangAnDingYi fangAnDingYi=fangAnDingYiService.selectById(faId);
		vo.setFaName(fangAnDingYi==null?null:fangAnDingYi.getName());
		 // 根据方案，查询该方案所用的科室
		    // 1.查询总和
			bos.add(statics(map,"总计"));
			//查询每个科室,科室名字一并传过去，查询费sql
			for (SysOrganization item : organizations) {
				map.put("deptId",item.getId());
				bos.add(statics(map,item.getName()));
			}
		return vo;
	}




	@Override
	public List<GongZuoTongJiVo> workCount(Map<String, Object> map, ShiroUser user) throws SysException {
		if (user==null){
			throw new SysException("无法获取当前用户");
		}
		//TODO 待修改
		DateUtils.getEndTimeByStaTime(map);
		List<GongZuoTongJiVo> vos = new ArrayList<>();
         	if (map.get("deptId")==null||map.get("deptId")==""){
         		throw  new SysException("请选择科室");
			}
			SysOrganization sysOrganization=sysOrganizationService.selectById(map.get("deptId").toString());
         	String deptName=sysOrganization==null?null:sysOrganization.getName();
			if (map.get("userId")==null||map.get("userId")==""){
				Long deptId=Long.parseLong(map.get("deptId").toString());
				List<SysUser> users=userService.queryZxrList(deptId,2);
				map.put("deptId",deptId);
				GongZuoTongJiVo dvo=userStatics(map);
				dvo.setUserName("总计");
				vos.add(dvo);
				for (SysUser i:users){
					map.put("userId",i.getId());
					GongZuoTongJiVo uvo=userStatics(map);
					//选择了科室后，都是只显示这一个科室，所以直接使用统计的科室
					uvo.setDeptName(deptName);
					uvo.setUserName(i.getName());
					vos.add(uvo);
				}
			}else {
				Long userId=Long.parseLong(map.get("userId").toString());
				map.put("userId",userId);
				GongZuoTongJiVo uvo=userStatics(map);
				SysUser userN=userService.selectById(userId);
				uvo.setUserName(userN==null?null:userN.getName());
				uvo.setDeptName(deptName);
				vos.add(uvo);
			}



		return vos;
	}
	/**
	 * 随访统计通用方法
	 * 优化，优化，还是使用group by  可能会好点
	 * @param map
	 * @return
	 */
	@SuppressWarnings(value = "unchecked")
	public OutStatics statics(Map<String,Object> map,String deptName) {
		//所有随访任务
		Double all;
		//1未随访
		Integer waitSf=0;
		//2.已随访
		Integer alreadySf=0;
		//3.死亡患者
		Integer deadSf=0;
		//4.预约随访
		Integer appointmentSf=0;
		//5.空号错号
		Integer errorNumSf=0;
		//6.无人接听
		Integer noResponseSf=0;
		//7.拒绝随访
		Integer refuseSf=0;
		//其他状态
		Integer otherSf=0;
		NumberFormat nf = NumberFormat.getPercentInstance();
		// id不为空，查询某个科室记录
			//封装好的时间判断方法
			OutStatics vo = new OutStatics();
			//离院患者
			vo.setLeaveCount(wenJuanShiLiMapper.leaveCount(map));
			//所有随访任务
			vo.setAllVisitCount(wenJuanShiLiMapper.allCount(map));
			all=vo.getAllVisitCount().doubleValue();
			List<Map<String,Object>> list=wenJuanShiLiMapper.statusCount(map);
			for (Map<String,Object> item:list){
				if ("1".equals(item.get("status").toString())){
					waitSf=Integer.parseInt(item.get("total").toString());
				}else if ("2".equals(item.get("status").toString())){
					alreadySf=Integer.parseInt(item.get("total").toString());
				}else if ("3".equals(item.get("status").toString())){
					deadSf=Integer.parseInt(item.get("total").toString());
				}else if ("4".equals(item.get("status").toString())){
					appointmentSf=Integer.parseInt(item.get("total").toString());
				}else if ("5".equals(item.get("status").toString())){
					errorNumSf=Integer.parseInt(item.get("total").toString());
				}else if ("6".equals(item.get("status").toString())){
					noResponseSf=Integer.parseInt(item.get("total").toString());
				}else if ("7".equals(item.get("status").toString())){
					refuseSf=Integer.parseInt(item.get("total").toString());
				}else {
					otherSf+=Integer.parseInt(item.get("total").toString());
				}
			}

						/*// 未随访
			map.put("status", 1);
			waitSf=wenJuanShiLiMapper.sfCount(map);
			vo.setWaitVisit(waitSf+"人（"+nf.format(all == 0 ? 0 : waitSf.doubleValue() / all)+")");
			// 已随访
			map.put("status", 2);
			alreadySf=wenJuanShiLiMapper.sfCount(map);
			vo.setAlreadyVisit(alreadySf+"人("+nf.format(all == 0 ? 0 : alreadySf.doubleValue() / all)+")");
			// 死亡患者
			map.put("status", 3);
			deadSf=wenJuanShiLiMapper.sfCount(map);
			vo.setDeadCount(deadSf+"人("+nf.format(all == 0 ? 0 : deadSf.doubleValue() / all)+")");
			// 预约随访
			map.put("status", 4);
			appointmentSf=wenJuanShiLiMapper.sfCount(map);
			vo.setAppointmentVisit(appointmentSf+"人("+nf.format(all == 0 ? 0 : appointmentSf.doubleValue() / all)+")");
			// 空号错号
			map.put("status", 5);
			errorNumSf=wenJuanShiLiMapper.sfCount(map);
			vo.setErrorNum(errorNumSf+"人("+nf.format(all == 0 ? 0 : errorNumSf.doubleValue() / all)+")");
			// 无人接听
			map.put("status", 6);
			noResponseSf=wenJuanShiLiMapper.sfCount(map);
			vo.setNoResponse(noResponseSf+"人("+nf.format(all == 0 ? 0 : noResponseSf.doubleValue() / all)+")");
			// 拒绝随访
			map.put("status", 7);
			refuseSf=wenJuanShiLiMapper.sfCount(map);
			vo.setRefuseCount(refuseSf+"人("+nf.format(all == 0 ? 0 : refuseSf.doubleValue() / all)+")");*/
			nf.setMinimumFractionDigits(2);
			vo.setSuccessPercent(nf.format(all == 0 ? 0 : alreadySf / all));
			vo.setAllVisitPercent(nf.format(all==0?0: (all-waitSf)/all));
			vo.setWaitVisit(waitSf.toString());
			vo.setAlreadyVisit(alreadySf.toString());
			vo.setDeadCount(deadSf.toString());
			vo.setErrorNum(errorNumSf.toString());
			vo.setRefuseCount(refuseSf.toString());
			vo.setNoResponse(noResponseSf.toString());
			vo.setOtherCount(otherSf.toString());
			vo.setAppointmentVisit(appointmentSf.toString());
		//id为空，计算所有数量总和，不为空，则为该科室出院人数
		if (map.get("deptId")==null||map.get("deptId")==""){
			vo.setDeptName(deptName);
			vo.setDeptId(null);
		}else {
			//设置科室id
			vo.setDeptId(Long.parseLong(map.get("deptId").toString()));
			//设置科室名
			vo.setDeptName(deptName);
		}
		return vo;
	}

	/**
	 * 工作统计通用方法
	 * @param map
	 * @return
	 */
	@SuppressWarnings(value = "unchecked")
	public GongZuoTongJiVo userStatics(Map<String,Object> map){
		//所有随访任务
		Double all;
		//1未随访
		Integer waitSf=0;
		//2.已随访
		Integer alreadySf=0;
		//3.死亡患者
		Integer deadSf=0;
		//4.预约随访
		Integer appointmentSf=0;
		//5.空号错号
		Integer errorNumSf=0;
		//6.无人接听
		Integer noResponseSf=0;
		//7.拒绝随访
		Integer refuseSf=0;
		Integer otherCount=0;
		NumberFormat nf = NumberFormat.getPercentInstance();
		GongZuoTongJiVo vo = new GongZuoTongJiVo();
		if (map.get("userId") != null && map.get("userId") != "") {
			SysUser user = userService.selectById(Long.parseLong(map.get("userId").toString()));
			vo.setUserName(user == null ? null : user.getName());
			// 拨打电话次数
		/*	vo.setCallTimes(phoneService.getCallTimes(user.getId()));*/
			//所有随访,因为前几次都会将status 赋值，所有每次这里都要清空
			map.remove("status");
			vo.setAllVisitCount(wenJuanShiLiMapper.userAllCount(map));
			 all = vo.getAllVisitCount().doubleValue();
			// 未随访,此时没有update_user_id 标识，所以采用gc_doctor_id
			map.put("status", 1);
			 waitSf=wenJuanShiLiMapper.userAllCount(map);
			vo.setWaitVisit(waitSf.toString());
			List<Map<String,Object>> list=wenJuanShiLiMapper.userSfCount(map);
			for (Map<String,Object> item:list){
				if ("2".equals(map.get("status").toString())){
					alreadySf=Integer.parseInt(map.get("total").toString());
				}else if ("3".equals(map.get("status").toString())){
					deadSf=Integer.parseInt(map.get("total").toString());
				}else if ("4".equals(map.get("status").toString())){
					appointmentSf=Integer.parseInt(map.get("total").toString());
				}else if ("5".equals(map.get("status").toString())){
					errorNumSf=Integer.parseInt(map.get("total").toString());
				}else if ("6".equals(map.get("status").toString())){
					noResponseSf=Integer.parseInt(map.get("total").toString());
				}else if ("7".equals(map.get("status").toString())){
					refuseSf=Integer.parseInt(map.get("total").toString());
				}else if ("3".equals(map.get("status").toString())){
					deadSf=Integer.parseInt(map.get("total").toString());
				}else {
					otherCount=otherCount+Integer.parseInt(map.get("total").toString());
				}
			}
			vo.setRefuseCount(refuseSf+"人("+nf.format(all == 0 ? 0 : refuseSf.doubleValue() / all)+")");
			nf.setMinimumFractionDigits(2);
			vo.setSuccessPercent(nf.format(all == 0 ? 0 : alreadySf / all));
			vo.setAllVisitPercent(nf.format(all==0?0:(all-waitSf)/all));

			vo.setWaitVisit(waitSf.toString());
			vo.setAlreadyVisit(alreadySf.toString());
			vo.setDeadCount(deadSf.toString());
			vo.setErrorNum(errorNumSf.toString());
			vo.setAppointmentVisit(appointmentSf.toString());
			vo.setNoResponse(noResponseSf.toString());
			vo.setRefuseCount(refuseSf.toString());
			vo.setOtherCount(otherCount.toString());
			return vo;
		} else {
          OutStatics suiFangTongJiVo=statics(map,null);
          BeanUtils.copyProperties(suiFangTongJiVo,vo);
          return vo;
		}
	}
}
