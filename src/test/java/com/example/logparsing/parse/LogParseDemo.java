package com.example.logparsing.parse;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class LogParseDemo {

    BufferedReader reader = null;
    LogParseUtil logParseUtil = null;
    private final File file = null;

    @Before
    public void beforeLoad() {
        logParseUtil = new LogParseUtil();
    }

    @Test
    public void checkPath() {
        boolean b = logParseUtil.checkPath();
        Assert.assertEquals(b, true);
    }

    //读取文件
    @Test
    public void readLogData() throws IOException {
        logParseUtil.loadLogData();
        //Assert.assertThrows(Exception.class);
    }

}
