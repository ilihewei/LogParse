package com.example.logparsing.poi;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Parse150 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String excelPath150 = "src/main/resources/神州信息第2次数币测试150人清单.xlsx";
    private final XSSFSheet sheet = null;
    private File file = null;
    private XSSFWorkbook wb = null;
    public static Set<String> phones150 = new HashSet<>();
    public  static String cellValue="手机号";

    //校验路径是否存在
    public boolean checkPath() {
        try {
            file = ResourceUtils.getFile(excelPath150);
            return file.isFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadExcel() {
        try {
            InputStream ins = new FileInputStream(file);
            //读取excel
            wb = new XSSFWorkbook(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadSheet() {
        try {

            boolean checkPath = this.checkPath();
            if(checkPath) {
                this.loadExcel();
                this.hasSheets();
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public void hasSheets() {
        Iterator<Sheet> sheetIterator = wb.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            this.getSheetData(sheet);
        }
    }

    public void getSheetData(Sheet sheet) {
        int phoneCellIndex = 2;
        Iterator<Row> rows = sheet.rowIterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            try {
                row.getCell(phoneCellIndex).setCellType(CellType.STRING);
                String stringCellValue = row.getCell(phoneCellIndex).getStringCellValue();
                if (!stringCellValue.equals(cellValue)){

                    String phone = row.getCell(phoneCellIndex).getStringCellValue();
                    phones150.add(phone);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
