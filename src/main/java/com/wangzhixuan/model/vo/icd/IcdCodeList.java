package com.wangzhixuan.model.vo.icd;

import java.util.List;
import java.util.Set;

/**
 * Created by Leslie on 2018/2/5.
 *
 * @author: Leslie
 * @TIME:15:48
 * @Date:2018/2/5 Description:mapToBean无法转换list
 */
public class IcdCodeList {
    private Set<String> codes;

    public Set<String> getCodes() {
        return codes;
    }

    public void setCodes(Set<String> codes) {
        this.codes = codes;
    }
}
