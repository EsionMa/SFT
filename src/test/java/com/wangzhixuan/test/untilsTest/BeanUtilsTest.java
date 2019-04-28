package com.wangzhixuan.test.untilsTest;

import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.model.vo.icd.IcdCodeList;
import com.wangzhixuan.test.base.BaseTest;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Leslie on 2018/2/5.
 *
 * @author: Leslie
 * @TIME:15:34
 * @Date:2018/2/5 Description:
 */

public class BeanUtilsTest extends BaseTest{
    @Test
    public void mapToBean(){
        String[] strings={"1x","2x","3x","3x"};
        Map<String,Object> map=new HashedMap();
        map.put("codes",strings);
        IcdCodeList xx= BeanUtils.mapToBean(map, IcdCodeList.class);
        for (String item:xx.getCodes()){
            System.out.println(item);
        }
    }



}
