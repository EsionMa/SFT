/**
 * 2018-03-23 16:55:46
 */
package com.wangzhixuan.util;

import com.wangzhixuan.commons.SysException;
import com.wangzhixuan.commons.utils.BeanUtils;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.Excel;
import jxl.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author Esion
 */
@Component
public class JxlExcelUtil {


    public static void main(String[] args) throws Exception {
        File file = new File("C:/Users/Administrator/Desktop/text.xls");
        List<Excel> list = readExcel(new FileInputStream(file), Excel.class);
        for (Excel excel : list) {
            System.err.println(excel.getName());
        }
    }

    /**
     * 读取数据并转换相应实体
     *
     * @param is
     * @param t
     * @param <T>目标实体类
     * @return
     */
    public static <T> List<T> readExcel(InputStream is, Class<T> t) {

        List<T> list = new ArrayList<>();
        //1:创建workbook
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(is);
            //2:获取第一个工作表sheet
            Sheet sheet = workbook.getSheet(0);
            //3:获取数据
            System.out.println("行：" + sheet.getRows());
            System.out.println("列：" + sheet.getColumns());

            for (int i = 1; i < sheet.getRows(); i++) {
                Map<String, Object> map = new HashMap<>();
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    if (cell.getType() == CellType.DATE) {
                        DateCell dc = (DateCell) cell;
                        Date date = dc.getDate();//获取单元格的date类型
//                        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd ");
//                        // 这句很重要，必须采用标准的GMT时区，不然解析的时间会不正确
//                        time.setTimeZone(TimeZone.getTimeZone("GMT"));
//                        String data = time.format(date);
                        map.put(sheet.getCell(j, 0).getContents(), date);
                        System.out.print(" " + date);
                    } else {
                        if (StringUtils.isNotBlank(cell.getContents())) {
                            map.put(sheet.getCell(j, 0).getContents(), cell.getContents());
                            System.out.print(cell.getContents() + " ");
                        }
                    }

                }
                T newBean = BeanUtils.mapToBean(map, t);
                list.add(newBean);
                System.out.println();
            }


            System.out.print(list);

            //最后一步：关闭资源
            workbook.close();
        } catch (Exception e) {
            throw new SysException("上传文件出错，请确认后再上传！");
//            e.printStackTrace();
        }
        return list;
    }
}
