package com.wangzhixuan.controller;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.result.Result;
import com.wangzhixuan.model.huanzhe.HuanZheContract;
import com.wangzhixuan.model.huanzhe.HuanZheInfo;
import com.wangzhixuan.model.huanzhe.ZhuYuanInfo;
import com.wangzhixuan.model.vo.SysUploadRecordsVo;
import com.wangzhixuan.model.vo.huanzhe.PatientQuery;
import com.wangzhixuan.service.IImportService;
import com.wangzhixuan.service.ISysUploadRecordsService;
import com.wangzhixuan.service.excel.impl.ExeclServiceImpl;
import com.wangzhixuan.service.sqlserver.IMySqlService;
import com.wangzhixuan.service.sqlserver.IOracleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Leslie on 2018/1/16.
 *
 * @author: Leslie
 * @TIME:11:44
 * @Date:2018/1/16 Description:
 */
@Controller
@RequestMapping(value = "/dataImport")
public class SysImportController extends BaseController {

    @Autowired
    private IImportService importService;
    @Autowired
    private IOracleService oracleService;
    @Autowired
    private IMySqlService mySqlService;
    @Autowired
    private ExeclServiceImpl execlService;
    @Autowired
    private ISysUploadRecordsService uploadRecordsService;

    /**
     * 跳转至导数据页面
     *
     * @return
     */
    @GetMapping(value = "/dataQueryManager")
    public ModelAndView manager() {
        return new ModelAndView("admin/dataImport/dataQuery");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     * @author: Leslie
     * @Date 2018/1/23 14:37
     * @param: []
     * @Description: 跳转至异常数据页面
     */
    @GetMapping(value = "/notFoundQueryManager")
    public ModelAndView foundManager() {
        return new ModelAndView("admin/dataImport/notFoundQuery");
    }

    /**
     * @return java.lang.Object
     * @throws
     * @author: Leslie
     * @Description: 在his数据库查询住院信息相关信息
     * @Date 2018/1/22 18:49
     * @param: [query, result]
     */
    @PostMapping(value = "/query")
    @ResponseBody
    public Object queryPatient(PatientQuery query) {
        //所有时间未进行处理
        return oracleService.getAllHos(query);
    }

    /**
     * @return java.lang.Object
     * @throws
     * @author: Leslie
     * @Date 2018/1/23 14:45
     * @param: [query]
     * @Description: 寻找两边数据库不一致的数据
     */
    @PostMapping(value = "/notFoundQuery")
    @ResponseBody
    public Object notFoundQuery(PatientQuery query) {
        if (query.getBoEdTime() == null || query.getBoStTime() == null) {
            return null;
        }
        Integer his = oracleService.count(query);
        Integer suifang = mySqlService.count(query);
        if (!his.equals(suifang)) {
            List<String> list = mySqlService.findZyNos(query);
            query.setIds(list);
            List<ZhuYuanInfo> zhuYuanInfos = oracleService.getNotFoundHos(query);
            return zhuYuanInfos;
        }
        return null;
    }

    /**
     * @return java.lang.Object
     * @throws
     * @author: Leslie
     * @Date 2018/1/23 10:21
     * @param: [query]
     * @Description: 根据住院号导入患者, 进行事务添加，改写事务配置
     */
    @PostMapping(value = "/import")
    @ResponseBody
    public Object importPatient(@Valid PatientQuery query) {
        RespResult<List<ZhuYuanInfo>> respResult = new RespResult<>();
        if (!importService.existHos(query)) {
            List<HuanZheInfo> huanZheInfos = oracleService.getAllPatients(query);
            //其实都是一张表，判断一次次够了
            if (huanZheInfos == null || huanZheInfos.size() == 0) {
                throw new SysException("该条件下未能查找到该患者");
            }
            List<HuanZheContract> contracts = oracleService.getAllContract(query);
            List<ZhuYuanInfo> zhuYuanInfos = oracleService.getAllHos(query);
            mySqlService.insertData(contracts, zhuYuanInfos, huanZheInfos);
            respResult.getSuccess(zhuYuanInfos, "导入成功");
        } else {
            respResult.getFail("随访通内该搜索条件下的病人,无需重新导入");
        }
        return respResult;
    }

    /**
     * 上传记录页
     *
     * @return
     */

    @GetMapping(value = "/excelImport")
    public String managers() {
        return "admin/dataImport/manualImport";
    }

    /**
     * 上传记录
     *
     * @param page  当前页
     * @param rows  记录数
     * @param sort  排序
     * @param order 顺序
     * @return
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Integer page, Integer rows,
                             @RequestParam(value = "sort", defaultValue = "uploadTime") String sort,
                             @RequestParam(value = "order", defaultValue = "DESC") String order, SysUploadRecordsVo vo) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        uploadRecordsService.selectDataGrid(pageInfo, vo);
        return pageInfo;
    }

    /**
     * 导入文件页
     *
     * @return
     */
    @GetMapping("/uploadPage")
    public String addPage() {
        return "admin/dataImport/importAdd";
    }

    /**
     * 选择上传文件，上传目标
     *
     * @param file 文件路径
     * @param id   上传目标表id
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Object add(MultipartFile file, Long id) {
        RespResult<String> result = new RespResult<>();
        if (file.isEmpty()) {
           return renderError("未选择上传文件，请重试。");
        } else {
            try {
                execlService.insertData(file, id, getShiroUser());
            }catch (Exception e){
                return renderError(e.getMessage());
            }
        }
        result.getSuccess("", "文件上传成功");
        return result;
    }

}

