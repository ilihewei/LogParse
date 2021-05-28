package com.example.logparsing.poi;

import com.example.logparsing.parse.LogParseUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lihewei
 */
public class POIUtil {

    public static final String excelPath2 = "src/main/resources/公司人员信息已经申请授权码的统计清单.xlsx";
    private static final String excelPath = "src/main/resources/公司人员信息统计清单.xlsx";
    private  static  final String GET_EXCEL_PATH150_WRITE ="src/main/resources/神州信息第2次数币测试150人清单_write.xlsx";
    private static final String sheetName1 = "科捷物流";
    private static final String sheetName2 = "神州信息";
    private static final String sheetName3 = "神州数码";
    static Set<String> phones = new HashSet<>();
    static  Set<String> getPhones=new HashSet<>();

     // 从日志中解析出来
        static {
            LogParseUtil logParseUtil = new LogParseUtil();
            try {
                logParseUtil.loadLogData();
                phones.addAll(LogParseUtil.phones);
                System.out.println("log===>"+LogParseUtil.phones.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    static {
        Parse150 parse150=new Parse150();
        parse150.loadSheet();
        System.out.println("par150===>"+Parse150.phones150.size());
        //添加150人手机号
        phones.addAll(Parse150.phones150);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private File file = null;
    private XSSFWorkbook wb = null;

    //校验路径是否存在
    public boolean checkPath() {
        try {
            file = ResourceUtils.getFile(excelPath);
            return file.isFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadExcel() {
        try {
            this.checkPath();
            InputStream ins = new FileInputStream(file);
            //读取excel
            wb = new XSSFWorkbook(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeOperations() {
        try {
            this.loadExcel();
            this.updateSheetRowData();
            this.saveSheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSheetRowData() {
        Iterator<Sheet> sheetIterator = wb.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            this.updateRowData(sheet);
        }
    }

    public boolean updateRowData(Sheet sheet) {
        int phoneCellIndex = 2;
        String used = "已使用";
        Iterator<Row> rows = sheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            row.getCell(phoneCellIndex).setCellType(CellType.STRING);
            String phone = row.getCell(phoneCellIndex).getStringCellValue();
            if (phones.contains(phone)) {
                getPhones.add(phone);
                if (sheet.getSheetName().equals(sheetName3)) {
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(used);
                } else {
                    row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(used);
                }
            }
        }

        System.out.println("pphones=======>"+phones.size());
        System.out.println("getPhones=====>"+getPhones.size());
     //   this.removeAll();
        return true;
    }

    public void removeAll(){
        phones.removeAll(getPhones);
        System.out.println("size===>"+phones.size());
        for (String str:phones) {
            System.out.println("没有找见的匹配的手机号=======>"+str);
        }
    }

    //保存成功
    public void saveSheet() {
        try {
            FileOutputStream fos = new FileOutputStream(excelPath2);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
