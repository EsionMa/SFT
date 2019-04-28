package com.wangzhixuan.service.wenjuan.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.mapper.fa.FangAnDingYiItemMapper;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.wenjuan.*;
import com.wangzhixuan.service.ISysUserService;
import com.wangzhixuan.service.wenjuan.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.wenjuan.WenJuanItemMapper;
import com.wangzhixuan.mapper.wenjuan.WenJuanMapper;
import com.wangzhixuan.mapper.wenjuan.WenJuanShiLiMapper;
import com.wangzhixuan.model.vo.TiMuVo;
import com.wangzhixuan.model.vo.WenJuanVo;

@Service
public class WenJuanServiceImpl extends ServiceImpl<WenJuanMapper, WenJuan> implements IWenJuanService {
	private static final Logger logger = LoggerFactory.getLogger(WenJuanServiceImpl.class);
	@Autowired
	private FangAnDingYiItemMapper fangAnDingYiItemMapper;
	@Autowired
	private ITiMuService tiMuService;
	@Autowired
	private WenJuanMapper wenJuanMapper;
	@Autowired
	private WenJuanItemMapper wenJuanItemMapper;
	@Autowired
	private WenJuanShiLiMapper wenJuanShiLiMapper;
	@Autowired
	private ITiMuShiLiService tiMuShiLiService;
	@Autowired
	private IWenJuanShiLiService wenJuanShiLiService;
	@Autowired
    private ISysUserService userService;
	@Autowired
	private IWenJuanShiLiItemService iWenJuanShiLiItemService;

	@Override
	public void saveOrUpdate(WenJuanVo vo, ShiroUser user) throws SysException {
		if (vo.getTiMus() == null || vo.getTiMus().size() < 1) {
			throw new SysException(ErrorCode.ReqWJTMLessThanOne);
		}
		Double wjScore=0D;
		for (TiMu item:vo.getTiMus()){
			wjScore+=item.getScore();
		}
		if (vo.getId() == null) {
			vo.setCreateTime(new Date());
			vo.setCreateUserId(user.getId());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			vo.setWenjuanScore(wjScore);
			insert(vo);
			// 保存
		} else {
			// 更新
			Long beforeId = vo.getId();
			// 逻辑删除
			WenJuanVo wjVo = new WenJuanVo();
			wjVo.setId(beforeId);
			delete(wjVo, user);
			// 新添问卷
			vo.setId(null);
			vo.setStatus("1");
			vo.setCreateTime(new Date());
			vo.setCreateUserId(user.getId());
			vo.setUpdateTime(new Date());
			vo.setUpdateUserId(user.getId());
			vo.setWenjuanScore(wjScore);
			insert(vo);
			// 更新方案内容中的问卷（表：hzsf_fa_dy_item_wj）
			fangAnDingYiItemMapper.updateWenJuanByItemId(vo.getId(), beforeId);
			// 更新未执行问卷实例中的问卷（表：hzsf_wj_sl）
			WenJuanShiLi shili = new WenJuanShiLi();
			shili.setWjId(vo.getId());
			EntityWrapper<WenJuanShiLi> slWrapper = new EntityWrapper<>();
			slWrapper.where("status<>{0}", "2");
			slWrapper.where("wj_id={0}", beforeId);
			wenJuanShiLiMapper.update(shili, slWrapper);
		}
		for (int i = 0; i < vo.getTiMus().size(); i++) {
			TiMuVo tiMuVo = vo.getTiMus().get(i);
			/**
			 * 判断是源自于模板引用还是自己添加，如果是添加
			 * 则新增，同时增加到模板，如果是引用，那么设置为私有，
			 * 则不会添加到模板中
			 */
			if (tiMuVo.getId()==null){
				//先添加一个模板
                tiMuService.saveOrUpdate(tiMuVo, user);
            }
			/***
			 * 从题目模板中选中题目，
			 * 添加一个新的问卷，
			 * 即添加新的题目，
			 * 达到模板的在作用，
			 * 以后不管题目怎么修改，
			 * 都会毫无关系
			 */
			tiMuVo.setSy("1");
			tiMuVo.setId(null);
			tiMuService.saveOrUpdate(tiMuVo, user);
			WenJuanItem wenJuanItem = new WenJuanItem(vo.getId(), tiMuVo.getId());
			wenJuanItem.setCreateTime(new Date());
			wenJuanItem.setCreateUserId(user.getId());
			wenJuanItem.setUpdateTime(new Date());
			wenJuanItem.setUpdateUserId(user.getId());
			wenJuanItemMapper.insert(wenJuanItem);
		}
	}
	@Override
	public void delete(WenJuanVo vo, ShiroUser user) throws SysException {
		if (vo == null || vo.getId() == null) {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
		vo.setUpdateTime(new Date());
		vo.setUpdateUserId(user.getId());
		vo.setStatus("9");//状态改为9 就是删除
		updateById(vo);
	}

	@Override
	public PageInfo selectDateGrid(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.addCondition("status", "1");
		//执行搜索条件
		List<Map<String, Object>> list = wenJuanMapper.selectPage(page, pageInfo.getCondition());
		//计算每个问卷有多少人回答
		for (Map<String,Object> item:list){
			item.put("wjCount",iWenJuanShiLiItemService.countByWjId(Long.parseLong(item.get("id").toString())));
		}
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public WenJuanVo queryDetailById(Long id) throws SysException {
		if (id == null) {
			throw new SysException("查询问卷详情，问卷ID不能为空");
		}
		WenJuanVo vo = new WenJuanVo();
		// 问卷
		// if (id != null) {
		WenJuan wj = wenJuanMapper.selectById(id);
		if (wj == null) {
			throw new SysException("ID为" + id + "的问卷不存在，请联系管理员核实");
		}
		BeanUtils.copy(wj, vo);
		// }
		// boolean wjzxFlag = false;
		// 问卷实例
		// if (wjslId != null) {
		// WenJuanShiLi wenJuanShiLi = wenJuanShiLiMapper.selectById(wjslId);
		// if (wenJuanShiLi == null) {
		// throw new SysException("问卷实例（问卷执行）不存在");
		// }
		// wjzxFlag = true;
		// // id = wenJuanShiLi.getWjId();
		// vo.setJhsfDate(wenJuanShiLi.getJhsfDate());
		// vo.setDcTime(wenJuanShiLi.getDcTime());
		// vo.setDcxj(wenJuanShiLi.getDcxj());
		// // 评价来源"1"(出院随访)
		// List<PingJiaVo> pingJias = pingJiaService.selectByPjLyAndExtId("1",
		// "" + wjslId);
		// vo.setPingJia(pingJias);
		// List<Map<String, Object>> wjList =
		// fangAnDingYiItemMapper.selectWenJuans(wenJuanShiLi.getFaItemId());
		// }
		// vo.setId(wj.getId());
		// vo.setWjzt(wj.getWjzt());
		// 此处知识空题目
		List<TiMuVo> tiMus = new ArrayList<>();
		vo.setTiMus(tiMus);
		List<WenJuanItem> wenJuanItems = wenJuanItemMapper.queryByWenJuanId(id);
		if (wenJuanItems != null && wenJuanItems.size() > 0) {
			for (int i = 0; i < wenJuanItems.size(); i++) {
				WenJuanItem wenJuanItem = wenJuanItems.get(i);
				TiMuVo timuVo = new TiMuVo();
				timuVo.setId(wenJuanItem.getTmId());
				// 状态为"1"的题目
				timuVo.setStatus("1");
				timuVo = tiMuService.queryDetailById(timuVo);
				if (timuVo == null) {
					logger.warn("问卷{}id为{}发现异常题目题目ID为{}", wj.getWjzt(), wj.getId(), wenJuanItem.getTmId());
					continue;
				}
				timuVo.setSeq(wenJuanItem.getSeq());
				tiMus.add(timuVo);
				// List<TimuXuanXiang> tmxxs = timuVo.getTmxxs();
				// if (wjzxFlag) {
				// for (int j = 0; j < tmxxs.size(); j++) {
				// TimuXuanXiang timuXuanXiang = tmxxs.get(j);
				// TiMuShiLiVo queryByWjslId =
				// tiMuShiLiService.queryByWjslId(wjslId, wenJuanItem.getTmId(),
				// timuXuanXiang.getId());
				// if (queryByWjslId != null) {
				// timuXuanXiang.setSfxz("Y");
				// timuXuanXiang.setStnr(queryByWjslId.getDtnr());
				// } else {
				// timuXuanXiang.setSfxz("N");
				// }
				// }
				// // 填空题
				// if ("3".equals(timuVo.getTxxz())) {
				// TiMuShiLiVo tiMuShiLiVo =
				// tiMuShiLiService.queryByWjslId(wjslId, timuVo.getId(), null);
				// if (tiMuShiLiVo != null) {
				// timuVo.setDtnr(tiMuShiLiVo.getDtnr());
				// }
				// }
				// }
			}
		}
		return vo;
	}

	@Override
	public PageInfo findByWjzt(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		if (pageInfo.getCondition() != null) {
			List<Map<String, Object>> list = wenJuanMapper.findByWjzt(page, pageInfo.getCondition());
			//计算每个问卷有多少人回答
			for (Map<String,Object> item:list){
				item.put("wjCount",iWenJuanShiLiItemService.countByWjId(Long.parseLong(item.get("id").toString())));
			}
			pageInfo.setRows(list);
			pageInfo.setTotal(page.getTotal());
			return pageInfo;
		} else {
			throw new SysException("请输入问卷主题");
		}
	}
   /***
	*@author: Leslie
	*@Date 2018/1/23 18:17
	*@param: [pageInfo]
	*@return com.wangzhixuan.commons.result.PageInfo
	*@throws
	*@Description:根据问卷id查询其回答等相关信息
	*/
   @Override
    public PageInfo listAnswerOfWenJuan(PageInfo pageInfo) {
   	if (pageInfo.getCondition().get("id")==null||pageInfo.getCondition().get("id")==""){
   		throw new SysException("问卷id为空");
	}
	Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	//获得问卷id
	Long wjId=Long.parseLong(pageInfo.getCondition().get("id").toString());
   	//回答的该问卷有多少，因为即使方案停下来了，不会删除相关已做过随访的，所以只要status=2的问卷实例
       // 分页
   	List<WenJuanVo> vos= iWenJuanShiLiItemService.queryWjByWjId(page,wjId);
   	  if (vos!=null&&vos.size()>0){
   	  	//迭代添加其他信息
		  for (WenJuanVo item:vos){
			  WenJuan wenJuan=selectById(item.getId());
		      item.setId(wjId);
		      item.setWjCount(tiMuService.countByWj(wjId));
		      if (item.getUpdateUserId()!=null){
                  SysUser user=userService.selectUserById(item.getUpdateUserId());
                  item.setUserName(user==null?"未知":user.getName());
              }else {
		          item.setUserName("未知");
              }
		  	//实际答题数(根据id计算)
			item.setAnswerCount(tiMuShiLiService.countWjIdAndTmId(wjId,item.getItemId()));
			//总分数
			 item.setWenjuanScore(wenJuan.getWenjuanScore());
		  }
	  }
	  pageInfo.setRows(vos);
   	  pageInfo.setTotal(page.getTotal());
   	  return pageInfo;
    }
    @Override
    public WenJuanVo answerDetail(WenJuanVo vo) {
      if (vo.getId()==null){
          throw new SysException("问卷id为空");
      }
      if (StringUtils.isBlank(vo.getWjzt())){
          throw new SysException("问卷主题为空");
      }
      if (vo.getItemId()!=null){
      		return wenJuanShiLiService.getWenJuanVo(vo.getId(),null,vo.getWjzt(),vo.getItemId());
		}else if (vo.getWjslId()!=null){
		  	return wenJuanShiLiService.getWenJuanVo(vo.getId(),vo.getWjslId(),vo.getWjzt(),null);
	  }else {
		  //TODO 抽查问卷相关
			throw new SysException("问卷实例id或完成实例Id不为空");
	  }
    }
    @Override
    public WenJuanVo queryDetail(WenJuanVo vo) {
       if (vo.getId()==null){
           throw new SysException("问卷id为空");
       }
       if (vo.getWjslId()==null&&vo.getItemId()==null){
		   return queryDetailById(vo.getId());
       }
		return  answerDetail(vo);
    }
}
