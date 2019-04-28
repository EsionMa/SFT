package com.wangzhixuan.service;

import com.wangzhixuan.model.vo.huanzhe.PatientQuery;

/**
 * Created by Leslie on 2018/1/22.
 *
 * @author: Leslie
 * @TIME:19:06
 * @Date:2018/1/22 Description:
 */
public interface IImportService {
    /**
     *@author: Leslie
     *@Date 2018/1/22 19:08
     *@param: [query]
     *@return boolean
     *@throws
     *@Description: 查询在his是否存在该患者
     */
    boolean existHos(PatientQuery query);

    void saveTest(PatientQuery patientQuery);
}
