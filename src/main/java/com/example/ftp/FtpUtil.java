package com.example.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketException;

/**
 * @author lihewei
 */
public class FtpUtil {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private  static  String hostname="http://172.16.3.211";
    private  static  int port=22;
    private  static  String userName="root";
    private  static  String  password="Dcits211dzm8";

    public boolean loginRomteServer() {
        FTPClient ftpClient=null;
        try {

            ftpClient = new FTPClient();

            //登录
            ftpClient.connect(hostname, port);
            ftpClient.login(userName,password);

            int replyCode = ftpClient.getReplyCode();
            if (replyCode==-1){
                return false;
            }else {
                return  true;
            }
        }catch (SocketException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  false;
    }
}
