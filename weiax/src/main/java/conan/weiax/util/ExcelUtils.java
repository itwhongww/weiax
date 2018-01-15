package conan.weiax.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel工具类
 */
public class ExcelUtils {
    /**
     * 导出excel方法
     *
     * @param exportPath 导出路径
     * @param array      数据（第一条数据为表头）
     */
    public static void exportExcel(String exportPath, List<List<String>> array, HttpServletResponse response) {
        //第一步，根据版本创建一个webbook，对应一个Excel文件
        Workbook wb;
        if (exportPath != null && exportPath.endsWith(".xls")) {//2003版
            wb = new HSSFWorkbook();
        } else {//2007版
            wb = new XSSFWorkbook();
        }
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet,命名为"sheet1"
        Sheet sheet = wb.createSheet("sheet1");
        // 第三步，创建单元格，并设置值表头 设置表头居中
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        // 第四步，在sheet中添加行,并写入数据。注意老版本poi对Excel的行数列数有限制short
        Row row;
        Cell cell;
        List<String> arrayChild;
        for (int i = 0; i < array.size(); i++) {
            row = sheet.createRow(i);
            arrayChild = array.get(i);
            for (int j = 0; j < arrayChild.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(arrayChild.get(j));
                cell.setCellStyle(style);
            }
        }

        // 第五步，将文件存到指定位置
        try {
            // path是指欲下载的文件的路径。
            File file = new File(exportPath);
            // 取得文件名。
            String filename = file.getName();
            // 设置response的Header
            response.addHeader("content-Type","application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            wb.write(toClient);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> getList() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> child1 = new ArrayList<String>();
        child1.add("姓名");
        child1.add("年龄");
        child1.add("性别");
        child1.add("工作");
        List<String> child2 = new ArrayList<String>();
        child2.add("hongww");
        child2.add("18");
        child2.add("男");
        child2.add("好");
        List<String> child3 = new ArrayList<String>();
        child3.add("lixin");
        child3.add("28");
        child3.add("女");
        child3.add("很好");
        list.add(child1);
        list.add(child2);
        list.add(child3);
        return list;
    }
}
