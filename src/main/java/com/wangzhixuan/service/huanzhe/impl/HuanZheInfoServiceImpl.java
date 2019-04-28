package com.wangzhixuan.service.huanzhe.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wangzhixuan.mapper.SysUserMapper;
import com.wangzhixuan.mapper.fuyou.FuYouHuanZheInfoMapper;
import com.wangzhixuan.model.fuyou.FuYouHuanZheInfo;
import com.wangzhixuan.model.fuyou.FuYouZhuYuanInfo;
import com.wangzhixuan.model.vo.fuyou.FuYouHuanZheInfoVo;
import com.wangzhixuan.service.fuyou.IFuYouHuanZheInfoService;
import com.wangzhixuan.service.huanzhe.IMenZhenInfoService;
import com.wangzhixuan.service.huanzhe.IZhuYuanInfoService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.ErrorCode;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.mapper.huanzhe.HuanZheContractMapper;
import com.wangzhixuan.mapper.huanzhe.HuanZheInfoMapper;
import com.wangzhixuan.mapper.huanzhe.HuanZheTagMapper;
import com.wangzhixuan.model.SysDictionaries;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.HuanZheTag;
import com.wangzhixuan.model.vo.huanzhe.HuanZheContractVo;
import com.wangzhixuan.model.vo.huanzhe.HuanZheInfoVo;
import com.wangzhixuan.service.ISysDictionariesService;
import com.wangzhixuan.service.huanzhe.IHuanZheInfoService;

@Service
public class HuanZheInfoServiceImpl extends ServiceImpl<HuanZheInfoMapper, HuanZheInfo> implements IHuanZheInfoService {
	private static final Logger log = LoggerFactory.getLogger(HuanZheInfoServiceImpl.class);
	@Autowired
	private HuanZheInfoMapper huanZheInfoMapper;
	@Autowired
	private HuanZheContractMapper huanZheContractMapper;
	@Autowired
	private ISysDictionariesService dictionariesService;
	@Autowired
	private HuanZheTagMapper huanZheTagMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private IZhuYuanInfoService zhuYuanInfoService;
	@Autowired
	private IMenZhenInfoService menZhenInfoService;
	@Autowired
	private IFuYouHuanZheInfoService fuYouInfoService;

	@Override
	public PageInfo selectDataGrid(HuanZheInfoVo vo) throws SysException {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(vo.getPage(), vo.getRows());
		//查询患者表数据
		List<Map<String, Object>> list = huanZheInfoMapper.findByQuery(page, vo);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				if (map == null) {
					continue;
				}
				long id = Long.parseLong(map.get("id").toString());
				//拿到患者id查询患者是否存在标记
				List<HuanZheTag> huanZheTags = huanZheTagMapper.listByHzId(id);
				StringBuilder huanzheTagsSb = new StringBuilder();
				if (huanZheTags != null && huanZheTags.size() > 0) {
					for (int j = 0; j < huanZheTags.size(); j++) {
						//患者可以存在多个标记
						huanzheTagsSb.append(huanZheTags.get(j).getTagName()).append(",");
					}
					huanzheTagsSb.setLength(huanzheTagsSb.length() - 1);
				}
				/*// 得到userName
				if (map.get("createUserId") != null) {
					Long userId = Long.parseLong(map.get("createUserId").toString());
					map.put("userName", sysUserMapper.selectById(userId).getName());
				}*/

				if (map.get("hzly") != null ) {
					String hzly = map.get("hzly").toString();
					if(StringUtils.isNotBlank(hzly)){
						map.put("hzly", dictionariesService.getByCode(hzly).getName());
					}

				}

				//查到的患者标记
				map.put("tagsName", huanzheTagsSb.toString());
				map.put("tags", huanZheTags);
				//查询医保类型
				Object ybtypeObj = map.get("ybtype");
				if (ybtypeObj != null) {
					String ybtype = ybtypeObj.toString();
					SysDictionaries ybtypeDic = dictionariesService.getByCode(ybtype);
					map.put("ybtypeName", ybtypeDic == null ? "" : ybtypeDic.getName());
				}
				/*// List<HuanZheContractVo> huanZheContractVos =
				// huanZheContractMapper.selectByHzId(id);
				// map.put("contracts", huanZheContractVos);*/
			}
		}
		//分页显示数据
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	@Override
	public HuanZheInfoVo saveOrUpdate(HuanZheInfoVo vo, ShiroUser user) throws SysException {
		// 保存基本信息
		log.debug("保存患者基本信息");
		if (StringUtils.isBlank(vo.getHzno())) {
			throw new SysException("患者号hzNO不能为空");
		}

		HuanZheInfo exitHzNo = selectByHzNo(vo.getHzno());
		vo.setUpdateTime(new Date());
		vo.setUpdateUserId(user.getId());
		if (vo.getId() == null) {
			if (exitHzNo != null) {
				throw new SysException("该患者号以存在");
			}
			vo.setCreateUserId(user.getId());
			vo.setCreateTime(new Date());
			insert(vo);
		} else {
			log.debug("删除患者联系人");
			huanZheContractMapper.deleteByHzId(vo.getId());
			log.debug("删除妇幼信息");
			fuYouInfoService.deleteByHzId(vo.getId());
			updateById(vo);
		}
		List<HuanZheContractVo> contracts = vo.getContracts();
		//查询患者联系人
		if (contracts != null && contracts.size() > 0) {
			log.debug("保存{}个患者联系人信息", contracts.size());
			// 保存联系人信息
			for (HuanZheContractVo huanZheContractVo : contracts) {
				huanZheContractVo.setHzId(vo.getId());
				huanZheContractMapper.insert(huanZheContractVo);
			}
		}
		List<FuYouHuanZheInfoVo> fuYouHuanZheInfoVos = vo.getPregnant();
		//查询患者孕产信息
		if (fuYouHuanZheInfoVos != null && fuYouHuanZheInfoVos.size() > 0) {
			log.debug("保存{}个妇幼信息", fuYouHuanZheInfoVos.size());
			// 保存患者妇幼信息
			for (FuYouHuanZheInfoVo fuYouHuanZheInfoVo : fuYouHuanZheInfoVos) {
				fuYouHuanZheInfoVo.setHzId(vo.getId());
				fuYouInfoService.insert(fuYouHuanZheInfoVo);
			}
		}
		return vo;
	}

	@Override
	public HuanZheInfoVo selectDetailById(HuanZheInfoVo vo) throws SysException {
		if (vo.getId() == null) {
			throw new SysException(ErrorCode.ReqIdIsNull);
		}
		HuanZheInfo huanZheInfo = selectById(vo.getId());
		if (huanZheInfo == null) {
			throw new SysException("查询用户不存在【ID】");
		}
		//TODO 已经查询一遍了
//		StringBuilder huanzheTagsSb = new StringBuilder();
//		List<HuanZheTag> huanZheTags = huanZheTagMapper.listByHzId(vo.getId());
//		if (huanZheTags != null && huanZheTags.size() > 1) {
//			for (int j = 0; j < huanZheTags.size(); j++) {
//				huanzheTagsSb.append(huanZheTags.get(j).getTagName()).append(",");
//			}
//			huanzheTagsSb.setLength(huanzheTagsSb.length() - 1);
//		}
//		vo.setTagsName(huanzheTagsSb.toString());
//		vo.setTags(huanZheTags);
		BeanUtils.copyProperties(huanZheInfo, vo);
		//时间换到后台转换
		List<HuanZheContractVo> huanZheContracts = huanZheContractMapper.selectByHzId(vo.getId());
		vo.setContracts(huanZheContracts);
		//查询患者是否有孕产信息
		List<FuYouHuanZheInfoVo> fuYouHuanZheInfoVos = fuYouInfoService.selectByHzId(vo.getId());
		vo.setPregnant(fuYouHuanZheInfoVos);
		return vo;
	}

	@Override
	public HuanZheInfo selectByHzNoAndHzId(String hzNo, Long hzId) throws SysException {
		if (hzId == null && StringUtils.isEmpty(hzNo)) {
			throw new SysException("患者ID和患者编号不能同时为空");
		}
		HuanZheInfo exitHzNo = huanZheInfoMapper.selectByhzNoAndhzId(hzNo, hzId);
		return exitHzNo;
	}

	@Override
	public List<Map<String, String>> queryHzContractMap(String hzNo, Long hzId) throws SysException {
		// 查询患者自己联系方式
		if (hzId == null && StringUtils.isEmpty(hzNo)) {
			throw new SysException("患者ID和患者编号不能同时为空");
		}
		HuanZheInfo exitHzNo = huanZheInfoMapper.selectByhzNoAndhzId(hzNo, hzId);
		if (exitHzNo == null) {
			return null;
		}
		List<Map<String, String>> huanZheContracts = getPhone(null, exitHzNo.getFixphone(), "固定电话", "本人");
		huanZheContracts = getPhone(huanZheContracts, exitHzNo.getMobilephone(), "移动电话", "本人");
		huanZheContracts = getPhone(huanZheContracts, exitHzNo.getIpphone(), "IP电话", "本人");
		List<HuanZheContract> huanZheContracts1 = queryHzContract(exitHzNo.getId());
		if (huanZheContracts1 != null && huanZheContracts1.size() > 0) {
			for (int i = 0; i < huanZheContracts1.size(); i++) {
				HuanZheContract huanZheContract = huanZheContracts1.get(i);
				getPhone(huanZheContracts, huanZheContract.getFixphone(), "固定电话", huanZheContract.getGxtype());
				getPhone(huanZheContracts, huanZheContract.getMobilephone(), "移动电话", huanZheContract.getGxtype());
			}
		}
		return huanZheContracts;
	}

	@Override
	public List<HuanZheContract> queryHzContract(Long hzId) throws SysException {
		EntityWrapper<HuanZheContract> wrapper = new EntityWrapper<>();
		wrapper.where("hz_id={0}", hzId);
		return huanZheContractMapper.selectList(wrapper);
	}

	private List<Map<String, String>> getPhone(List<Map<String, String>> list, String phone, String phoneType,
			String userType) {
		if (list == null) {
			list = new ArrayList<>();
		}
		if (StringUtils.isBlank(phone)) {
			return list;
		}
		Map<String, String> map = new HashMap<>();
		map.put("phoneNo", phone);
		map.put("phoneType", phoneType);
		map.put("gxtype", userType);
		list.add(map);
		return list;
	}

	@Override
	public void addTag(Long hzId, List<String> tagCodes, ShiroUser user) throws SysException {
		HuanZheTag entity = new HuanZheTag();
		//添加标记之前先删除原有的标记
		huanZheTagMapper.deleteByHzId(hzId);
		log.debug("保存患者标签,hzId为{},tagCodes数量为{}", hzId, tagCodes == null ? 0 : tagCodes.size());
		for (int i = 0; i < tagCodes.size(); i++) {
			String tagCode = tagCodes.get(i);
			SysDictionaries tagCodeDic = dictionariesService.getByCode(tagCode);
			if (tagCodeDic == null) {
				log.debug("保存患者标签code为:{}不存在", tagCode);
				continue;
			}
			entity.setHzId(hzId);
			entity.setTagCode(tagCode);
			entity.setTagName(tagCodeDic.getName());
			entity.setCreateTime(new Date());
			entity.setCreateUserId(user.getId());
			huanZheTagMapper.insert(entity);
		}
	}

	@Override
	public HuanZheInfo selectByHzNo(String hzNo) throws SysException {
		if (StringUtils.isEmpty(hzNo)) {
			throw new SysException("患者号hzNo不能为空");
		}
		HuanZheInfo exitHzNo = huanZheInfoMapper.selectByhzNoAndhzId(hzNo, null);
		return exitHzNo;
	}

	@Override
	public void delete(Long id) throws SysException {
		if (id != null) {
			deleteById(id);
		}
	}

	/**
	 * @Author: wangjun
	 * @Description:患者信息模糊查询
	 * @Date 2017/8/1 15:00
	 */
	@Override
	public PageInfo findByCondition(PageInfo pageInfo) throws SysException {
		Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
		Map<String, Object> conMap = pageInfo.getCondition();
		if (conMap.get("tagsName") != null && conMap.get("name") != null && conMap.get("hzno") != null
				&& conMap.get("mobilePhone") != null) {
			throw new SysException("条件为空");
		} else {
			List<HuanZheInfoVo> list;
			if (conMap.get("tagsName") != null) {
				List<Long> ids = huanZheTagMapper.findIdsByCode(conMap.get("tagsName").toString());
				conMap.put("ids", ids);
				// in查询
				list = huanZheInfoMapper.findByCondition(conMap);
			} else {
				list = huanZheInfoMapper.findByCondition(conMap);
			}
			if (list != null && list.size() > 0) {
				// 遍历患者
				for (int i = 0; i < list.size(); i++) {
					HuanZheInfoVo vo = list.get(i);
					if (vo == null) {
						continue;
					}
					long id = vo.getId();
					// tags标记
					List<HuanZheTag> huanZheTags = huanZheTagMapper.listByHzId(id);
					StringBuilder huanzheTagsSb = new StringBuilder();
					if (huanZheTags != null && huanZheTags.size() > 0) {
						for (int j = 0; j < huanZheTags.size(); j++) {
							huanzheTagsSb.append(huanZheTags.get(j).getTagName()).append(",");
						}
						huanzheTagsSb.setLength(huanzheTagsSb.length() - 1);
					}
					//
					vo.setTagsName(huanzheTagsSb.toString());
					vo.setTags(huanZheTags);
					// 证件类型名
					if (vo.getCardType() != null || vo.getCardType() != "") {
						SysDictionaries sysDictionaries = dictionariesService.getByCode(vo.getCardType());
						if (sysDictionaries != null) {
							vo.setCardName(sysDictionaries.getName());
						}
					}
					// 医保类型名
					if (vo.getYbtype() != null || vo.getYbtype() != "") {
						SysDictionaries sysDictionaries = dictionariesService.getByCode(vo.getYbtype());
						if (sysDictionaries != null) {
							vo.setYbtypeName(sysDictionaries.getName());
						}
					}
					// 患者来源类型
					if (vo.getHzly() != null) {
						SysDictionaries sys = dictionariesService.getByCode(vo.getHzly());
						if (sys != null) {
							vo.setHzly(sys.getName());
						}
					}
					// 联系人
					List<HuanZheContractVo> huanZheContractVos = huanZheContractMapper.selectByHzId(id);
					vo.setContracts(huanZheContractVos);
					List<FuYouHuanZheInfoVo> fuYouHuanZheInfoVos = fuYouInfoService.selectByHzId(vo.getId());
					vo.setPregnant(fuYouHuanZheInfoVos);

				}

			}
			pageInfo.setRows(list);
			pageInfo.setTotal(page.getTotal());
			return pageInfo;
		}
		/*
		 * if (pageInfo.getCondition()!=null){ List<Map<String, Object>>
		 * list=huanZheInfoMapper.findByCondition(pageInfo.getCondition()); if
		 * (list != null && list.size() > 0) { for (int i = 0; i < list.size();
		 * i++) { Map<String, Object> map = list.get(i); if (map == null) {
		 * continue; } long id = Long.parseLong(map.get("id").toString());
		 * List<HuanZheTag> huanZheTags = huanZheTagMapper.listByHzId(id);
		 * StringBuilder huanzheTagsSb = new StringBuilder(); if (huanZheTags !=
		 * null && huanZheTags.size() > 1) { for (int j = 0; j <
		 * huanZheTags.size(); j++) {
		 * huanzheTagsSb.append(huanZheTags.get(j).getTagName()).append(","); }
		 * huanzheTagsSb.setLength(huanzheTagsSb.length() - 1); } //得到userName
		 * if (map.get("createUserId")!=null){ Long
		 * userId=Long.parseLong(map.get("createUserId").toString());
		 * map.put("userName",sysUserMapper.selectById(userId).getName()); }
		 * //得到患者来源 if (map.get("hzly")!=null){ String
		 * hzly=map.get("hzly").toString();
		 * map.put("hzly",dictionariesMapper.listByCode(hzly).get(0).getName());
		 * }
		 * 
		 * // map.put("tagsName", huanzheTagsSb.toString()); map.put("tags",
		 * huanZheTags); Object ybtypeObj = map.get("ybtype"); if (ybtypeObj !=
		 * null) { String ybtype = ybtypeObj.toString(); SysDictionaries
		 * ybtypeDic = dictionariesService.getByCode(ybtype);
		 * map.put("ybtypeName", ybtypeDic == null ? "" : ybtypeDic.getName());
		 * } List<HuanZheContractVo> huanZheContractVos =
		 * huanZheContractMapper.selectByHzId(id); map.put("contracts",
		 * huanZheContractVos); } } pageInfo.setRows(list);
		 * pageInfo.setTotal(list.size()); return pageInfo; }else { throw new
		 * SysException("传入条件为空"); }
		 */
	}

	/**
	 * @Author: Leslie
	 * @Description:根据电话号查出患者名和其id
	 * @Date 2017/8/16 17:45
	 */
	@Override
	public Map<String, Object> findByphone(String num) throws SysException {
		if (num == null || num == "") {
			throw new SysException("传入num为空");
		}
		return huanZheInfoMapper.findByNum(num);
	}

	@Override
	public HuanZheInfo selectByYbNoOrPhone(HuanZheInfoVo vo) throws SysException {
		EntityWrapper<HuanZheInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(vo.getMobilephone())) {
			wrapper.or("mobilephone={0}", vo.getMobilephone());
		}
		if (StringUtils.isNotBlank(vo.getYbno())) {
			wrapper.or("ybno={0}", vo.getYbno());
		}
		if (StringUtils.isNotBlank(vo.getZlcard())) {
			wrapper.or("zlcard={0}", vo.getZlcard());
		}
		List<HuanZheInfo> huanZheInfos = selectList(wrapper);
		if (huanZheInfos != null && huanZheInfos.size() > 0) {
			return huanZheInfos.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> getHosOrOut(Map<String, Object> map) {
		if (map.get("hzId") == null || map.get("hzId") == "") {
			throw new SysException("患者id为空");
		}
		if (map.get("hzly") == null || map.get("hzly") == "") {
			throw new SysException("患者来源为空");
		}
		if (map.get("otherTime") == null || map.get("otherTime") == "") {
			throw new SysException("患者离院时间为空");
		}
		Integer hzly = Integer.parseInt(map.get("hzly").toString());
		if (1 == hzly) {
			return zhuYuanInfoService.getByTimeAndId(map);
		} else if (2 == hzly) {
			return menZhenInfoService.getByTimeAndId(map);
		} else {
			return null;
		}
	}

	@Override
	public Integer getBirthCount(Map<String, Object> map) {
		if (map == null) {
			throw new SysException("参数为空");
		}
		if (map.get("staDate") == null || map.get("endDate") == "") {
			throw new SysException("开始时间不为空");
		}
		if (map.get("endDate") == null || map.get("endDate") == "") {
			throw new SysException("结束时间不为空");
		}
		return huanZheInfoMapper.getBirthCount(map);
	}

}
