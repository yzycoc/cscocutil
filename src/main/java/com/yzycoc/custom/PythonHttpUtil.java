package com.yzycoc.custom;

import com.yzycoc.custom.result.AjaxHttpResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class PythonHttpUtil {
    /**
     * 向指定的URL发送GET方法的请求
     * @param url    发送请求的URL
     * @return   AjaxHttpResult    远程资源的响应结果
     *  msg :反馈内容
     *  success 请求状态
     */
    public static String doGet(String url) {
        AjaxHttpResult result = new AjaxHttpResult(false,"");
        BufferedReader bufferedReader = null;
        int responseCode = 101;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.connect();
            responseCode = connection.getResponseCode();
            if(responseCode == 200){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line = "";
                String resultMsg = "";
                while(null != (line = bufferedReader.readLine())) {
                    resultMsg += line;
                }
                return  resultMsg;
            }else{
                return  null;
            }
        }catch (Exception e) {
            System.out.println("python失败！！！"  + e.getMessage());
            return  null;
        }finally {
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
