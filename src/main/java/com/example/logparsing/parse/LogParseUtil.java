package com.example.logparsing.parse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lihewei
 */
public class LogParseUtil {

    private static final String telephonePhoneSting = "电话号码：";
    private static final String userNameString = "姓名：";
    private static final String logPath = "src/main/resources/spring.log";
    private File file = null;
    private BufferedReader reader = null;
    public final static Set<String> phones = new HashSet<>();


    //加载数据
    public boolean checkPath() {
        file = new File(logPath);
        return file.isFile();
    }

    public void loadLogData() throws IOException {
        boolean b = this.checkPath();
        if (b) {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            reader = new BufferedReader(isr);
            String tempString = null;
            Set<String> phones = new HashSet<>();
            try {
                while ((tempString = reader.readLine()) != null) {
                    if (tempString.contains(telephonePhoneSting)) {
                        this.parsePhone(tempString);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // return true;
      //  return b;
    }

    //解析手机号和姓名
    public void parsePhone(String tempStr) {
        int phoneIndex = tempStr.indexOf(telephonePhoneSting);
        int nameIndex = tempStr.indexOf(userNameString);
        String phone = tempStr.substring(tempStr.length() - 11);
       // System.out.println(phone);
      //  String userName = tempStr.substring(nameIndex + 3, phoneIndex - 3);
        phones.add(phone);
      //  System.out.println(phones.size()+"=========log");
       // JSONObject data = new JSONObject();
     //   data.put("userName", userName);
     //   data.put("phone", phone);
       // jsonArray.add(data);
     //   System.out.println("=====>" + jsonArray.size());
    }

}