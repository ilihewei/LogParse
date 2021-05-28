package com.example.logparsing.poi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class POIUtilTest {

    private POIUtil poiUtil;

    @Before
    public  void load(){
        poiUtil=new POIUtil();
        poiUtil.checkPath();
        poiUtil.loadExcel();
    }

    //执行操作
    @Test
    public void loadSheet(){
       poiUtil.executeOperations();
       // Assert.assertEquals(s,"ok");
    }


    @Test
    public void whenSheetsThenUpdateCellValue(){
        //当一个文件有多个sheet，操作
        poiUtil.updateSheetRowData();
        poiUtil.printlnData();
       // Assert.assertEquals(flag,true);
    }

    @Test
    public void whenLoadingThenPrintln(){

    }


//    @Test
//    public void saveSheet() throws IOException {
//        File file = new File(POIUtil.excelPath2);
//        poiUtil.saveSheet();
//       Assert.assertEquals(file.isFile(),true);
//    }


}
