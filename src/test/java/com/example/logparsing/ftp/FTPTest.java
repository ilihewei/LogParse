package com.example.logparsing.ftp;

import com.example.ftp.FtpUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FTPTest {

    private  FtpUtil ftpUtil;
    /**
     * 1.链接
     * 2.下载
     * 3.覆盖
     */
    @Before
    public void beforeLoad(){
        ftpUtil=new FtpUtil();
    }

    @Test
    public void whenLinkServerThenLoginSuccess(){
        boolean flag = ftpUtil.loginRomteServer();
        Assert.assertTrue(flag);
    }

}
