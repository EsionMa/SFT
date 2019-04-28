package com.wangzhixuan.service.wenjuan;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.vo.WenJuanVo;
import com.wangzhixuan.model.wenjuan.WenJuan;

import java.util.Map;

public interface IWenJuanService extends IService<WenJuan> {
    /**
     *  删除问卷
     * @param vo
     * @param user
     * @throws SysException
     */
    void delete(WenJuanVo vo, ShiroUser user) throws SysException;

    /**
     *  保存或修改
     * @param vo
     * @param user
     * @throws SysException
     */
    void saveOrUpdate(WenJuanVo vo, ShiroUser user) throws SysException;

    /**
     *  查询问卷列表
     * @param pageInfo
     * @return
     * @throws SysException
     */
    PageInfo selectDateGrid(PageInfo pageInfo) throws SysException;

    /**
     *  通过id查询问卷
     * @param id
     * @return
     * @throws SysException
     */
	WenJuanVo queryDetailById(Long id) throws SysException;

	PageInfo findByWjzt(PageInfo pageInfo) throws SysException;
    /***
     *@author: Leslie
     *@Date 2018/1/23 18:16
     *@param: [pageInfo]
     *@return com.wangzhixuan.commons.result.PageInfo
     *@throws
     *@Description:根据问卷id查询其相关回答者等信息
     */
    PageInfo listAnswerOfWenJuan(PageInfo pageInfo);
   /***
    *@author: Leslie
    *@Date 2018/1/24 17:54
    *@param: [vo]
    *@return com.wangzhixuan.model.vo.WenJuanVo
    *@throws
    *@Description:根据传入参数是wjslId,还是itemId,判断进行获取一个问卷的详细信息
    */
    WenJuanVo answerDetail(WenJuanVo vo);
    /***
     *@author: Leslie
     *@Date 2018/1/24 18:58
     *@param: [vo]
     *@return com.wangzhixuan.model.vo.WenJuanVo
     *@throws
     *@Description:初步判断是有选项还是无选项的问卷
     */
    WenJuanVo queryDetail(WenJuanVo vo);
}
