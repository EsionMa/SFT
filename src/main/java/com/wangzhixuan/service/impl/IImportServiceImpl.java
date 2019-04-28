package com.wangzhixuan.service.impl;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import com.wangzhixuan.service.IImportService;
import com.wangzhixuan.service.sqlserver.IMySqlService;
import com.wangzhixuan.service.sqlserver.IOracleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leslie on 2018/1/23.
 *
 * @author: Leslie
 * @TIME:10:02
 * @Date:2018/1/23 Description: 导入实现类
 */
@Service
public class IImportServiceImpl implements IImportService {

    private  static  final Logger logger= LoggerFactory.getLogger(IMySqlService.class);

    @Autowired
    private IMySqlService mySqlService;
    @Autowired
    private IOracleService oracleService;


    @Override
    public void saveTest(PatientQuery patientQuery) {
        List<HuanZheInfo> huanZheInfos=oracleService.getAllPatients(patientQuery);
        mySqlService.insertHuanzhe(huanZheInfos,null);
        //.....存1，存2，存3
    }

    /**
     *@author: Leslie
     *@Date 2018/1/23 10:02
     *@param: [query]
     *@return boolean
     *@throws SysException
     * @Description: 查询his中是否存在该患者
     */
    @Override
    public boolean existHos(PatientQuery query) throws SysException{
        if (query.getZyNo()==null){
            throw new SysException("请输入住院号");
        }
        /**
         * 查询条件下患者是否存在于系统
         */

        if (mySqlService.existHos(query)>0){
            logger.info("已经存在该病人，无需重复导入");
            return true;
        }
        return false;
    }
}
