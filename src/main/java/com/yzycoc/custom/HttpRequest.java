package com.yzycoc.custom;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.util.RedisUtil;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.util.Map;
import java.util.logging.Logger;
/**
 * @program: cscocutil
 * @description: Http请求数据
 * @author: yzy
 * @create: 2020-08-02 11:14
 * @Version 1.0
 **/
public class HttpRequest {
    private static Logger log = Logger.getLogger(HttpRequest.class.toString());
    /**
     * 向指定的URL发送GET方法的请求
     * @param url    发送请求的URL
     * @param data  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return   AjaxHttpResult    远程资源的响应结果
     *  msg :反馈内容
     *  success 请求状态
     */
    public static AjaxHttpResult doGet(String url, Map<String, ?> data) {
        AjaxHttpResult result = new AjaxHttpResult(false,"");
        BufferedReader bufferedReader = null;
        int responseCode = 101;
        try {
            String urlNameString = url;
            if(data.size()>0) {
                urlNameString +="?"+ urlEncode(data);
            }
            URL realUrl = new URL(urlNameString);
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
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
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

    /**
     * 向指定的URL发送POST方法的请求
     * @param url    发送请求的URL
     * @param data  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return       远程资源的响应结果
     */
    public static AjaxHttpResult doPost(String url, Map<String, ?> data) {
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        int responseCode = 101;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(urlEncode(data));
            out.flush();
            responseCode = connection.getResponseCode();
            if(responseCode == 200){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line = "";
                String resultMsg = "";
                while(null != (line = bufferedReader.readLine())) {
                    resultMsg += line;
                }
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
        }finally {
            try {
                if(null != out) {
                    out.close();
                }
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                System.out.println("发送请示失败"+url);
            }
        }
    }


    /**
     * 向指定的URL发送GET方法的请求 HTTPS（简易伪装）
     * @param url    发送请求的URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return   AjaxHttpResult    远程资源的响应结果
     *  msg :反馈内容
     *  success 请求状态
     */
    public static AjaxHttpResult doGetHttps(String url, Map<String, ?> param) {
        BufferedReader bufferedReader = null;
        int responseCode = 101;
        String urlNameString = url;
        try {
            if(param.size()>0) {
                urlNameString +="?"+ urlEncode(param);
            }
            URL realUrl = new URL(urlNameString);
            SSLContext sslcontext = SSLContext.getInstance("TLS");//第一个参数为协议,第二个参数为提供者(可以缺省)
            TrustManager[] tm = {new MyX509TrustManager()};
            sslcontext.init(null, tm, new SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
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
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
        }finally {
            System.out.println("状态："+responseCode +"请求【"+urlNameString+"】");
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 向指定的URL发送POST方法的请求
     * @param url    发送请求的URL
     * @param data  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return       远程资源的响应结果
     */
    public static AjaxHttpResult doPostHttps(String url, Map<String, ?> data) {
        AjaxHttpResult result = new AjaxHttpResult(false,"");
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        int responseCode = 101;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            SSLContext sslcontext = SSLContext.getInstance("TLS");//第一个参数为协议,第二个参数为提供者(可以缺省)
            TrustManager[] tm = {new MyX509TrustManager()};
            sslcontext.init(null, tm, new SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(urlEncode(data));
            out.flush();
            responseCode = connection.getResponseCode();
            if(responseCode == 200){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line = "";
                String resultMsg = "";
                while(null != (line = bufferedReader.readLine())) {
                    resultMsg += line;
                }
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
        }finally {
            try {

                if(null != out) {
                    out.close();
                }
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                System.out.println("发送请示失败"+url);
            }
        }
    }



    /**
     * 向指定的URL发送GET方法的请求
     * @param url    发送请求的URL
     * @param data  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return   AjaxHttpResult    远程资源的响应结果
     *  msg :反馈内容
     *  success 请求状态
     */
    public static AjaxHttpResult doGet(String url, Map<String, ?> data,Map<String, String> header) {
        AjaxHttpResult result = new AjaxHttpResult(false,"");
        BufferedReader bufferedReader = null;
        int responseCode = 101;
        try {
            String urlNameString = url;
            if(data.size()>0) {
                urlNameString +="?"+ urlEncode(data);
            }
            URL realUrl = new URL(urlNameString);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            for (Map.Entry<String, String> i : header.entrySet()) {
               connection.setRequestProperty(i.getKey(), i.getValue());
            }
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
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
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

    /**
     * 向指定的URL发送POST方法的请求
     * @param url    发送请求的URL
     * @param data  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return       远程资源的响应结果
     */
    public static AjaxHttpResult doPost(String url, Map<String, ?> data,Map<String, String> header) {
        AjaxHttpResult result = new AjaxHttpResult(false,"");
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        int responseCode = 101;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            for (Map.Entry<String, String> i : header.entrySet()) {
                connection.setRequestProperty(i.getKey(), i.getValue());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(urlEncode(data));
            out.flush();
            responseCode = connection.getResponseCode();
            if(responseCode == 200){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line = "";
                String resultMsg = "";
                while(null != (line = bufferedReader.readLine())) {
                    resultMsg += line;
                }
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
        }finally {
            try {

                if(null != out) {
                    out.close();
                }
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                System.out.println("发送请示失败"+url);
            }
        }
    }


    /**
     * 向指定的URL发送GET方法的请求 HTTPS（简易伪装）
     * @param url    发送请求的URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return   AjaxHttpResult    远程资源的响应结果
     *  msg :反馈内容
     *  success 请求状态
     */
    public static AjaxHttpResult doGetHttps(String url, Map<String, ?> param,Map<String, String> header) {
        BufferedReader bufferedReader = null;
        int responseCode = 101;
        String urlNameString = url;
        try {
            if(param.size()>0) {
                urlNameString +="?"+ urlEncode(param);
            }
            URL realUrl = new URL(urlNameString);
            SSLContext sslcontext = SSLContext.getInstance("TLS");//第一个参数为协议,第二个参数为提供者(可以缺省)
            TrustManager[] tm = {new MyX509TrustManager()};
            sslcontext.init(null, tm, new SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            for (Map.Entry<String, String> i : header.entrySet()) {
                connection.setRequestProperty(i.getKey(), i.getValue());
            }
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
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
        }finally {
            System.out.println("状态："+responseCode +"请求【"+urlNameString+"】");
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 向指定的URL发送POST方法的请求
     * @param url    发送请求的URL
     * @param data  请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return       远程资源的响应结果
     */
    public static AjaxHttpResult doPostHttps(String url, Map<String, ?> data,Map<String, String> header) {
        AjaxHttpResult result = new AjaxHttpResult(false,"");
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        int responseCode = 101;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            SSLContext sslcontext = SSLContext.getInstance("TLS");//第一个参数为协议,第二个参数为提供者(可以缺省)
            TrustManager[] tm = {new MyX509TrustManager()};
            sslcontext.init(null, tm, new SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            for (Map.Entry<String, String> i : header.entrySet()) {
                connection.setRequestProperty(i.getKey(), i.getValue());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(urlEncode(data));
            out.flush();
            responseCode = connection.getResponseCode();
            if(responseCode == 200){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line = "";
                String resultMsg = "";
                while(null != (line = bufferedReader.readLine())) {
                    resultMsg += line;
                }
                return  new AjaxHttpResult(true,resultMsg);
            }else{
                return  new AjaxHttpResult("错误码："+String.valueOf(responseCode));
            }
        }catch (Exception e) {
            System.out.println("发送GET请求出现异常！！！"  + e);
            return  new AjaxHttpResult("发送GET请求出现异常！！！");
        }finally {
            try {

                if(null != out) {
                    out.close();
                }
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e2) {
                System.out.println("发送请示失败"+url);
            }
        }
    }

    /***
     * COC专用的Http请求
     * @param urlPath  路径
     * @param redis    redis
     * @param staTime 设置连接主机服务器的超时时间
     * @param endTime 设置读取远程返回的数据时间
     * @param api 缓存存储的时间
     * @return
     */
    public static AjaxHttpResult cocHttp(String urlPath, RedisUtil redis, int staTime, int endTime, ClanApiHttp api) {
        if(api.getTime()>0){
            String jsons = (String)redis.get(urlPath);
            if(jsons!=null) {
                System.out.println("调用缓存数据"+urlPath);
                return new AjaxHttpResult(true,"获取缓存", JSONObject.parseObject(jsons));
            }
        }
        long startTime=System.currentTimeMillis();
        AjaxHttpResult result = new AjaxHttpResult();
        result.setSuccess(false);
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        int responseCode = 0;
        try {
            //3、打开和URL之间的连接
            // 创建远程url连接对象
            URL url = new URL(urlPath);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("authorization", "Bearer " + ConfigParameter.CocApi);
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(staTime);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(endTime);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                try {
                    JSONObject clans = JSON.parseObject(sbf.toString());
                    result.setSuccess(true);
                    result.setErrorMsg("获取成功");
                    result.setData(clans);
                    if(api.getTime()>0){
                        redis.set(urlPath, sbf.toString(),api.getTime());
                    }
                } catch (Exception e) {
                    result.setSuccess(false);
                    result.setErrorMsg(sbf.toString());
                }
            }else {
                result.setSuccess(false);
                result.setErrorMsg(String.valueOf(connection.getResponseCode()));
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMsg(null);
            responseCode = 888;
        } finally {
            // 关闭资源
            System.out.println("请求状态："+responseCode+"\t地址：["+urlPath+"] 耗时："+(System.currentTimeMillis()-startTime)+"ms");
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
        return result;
    }

    /***
     * 发送请求
     * @param requestUrl
     * @param data
     * @param method
     * @return
     */
    public static ClanResult getJarvis(String requestUrl, Map<String,Object> data, HttpMethod method){
        long startTime=System.currentTimeMillis();
        HttpURLConnection conn = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        if(null == method || HttpMethod.GET == method){
            requestUrl = requestUrl+"?"+urlEncode(data);
        }
        int responseCode = 0;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            System.out.println(requestUrl);
            //设置请求方式
            if(HttpMethod.GET == method || null == method){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                //使用URL连接输出
                conn.setDoOutput(true);
            }
            //设置请求内核
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
            conn.setRequestProperty("Cookie","_ga=GA1.2.1599053079.1569457387; _gid=GA1.2.2047943731.1569457387");
            //设置使用缓存
            conn.setUseCaches(false);
            //设置链接超时时间,毫秒为单位
            conn.setConnectTimeout(60000);
            //设置读取超时时间，毫秒为单位
            conn.setReadTimeout(60000);
            //开启链接
            conn.connect();
            //获取反馈的情况200
            responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                //获取反馈回来的数据类型
                String contentType = conn.getContentType();
                if("application/json; charset=utf-8".equals(contentType)) {
                    //处理post请求时的参数
                    if(null != data && HttpMethod.POST == method){
                        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlEncode(data));
                    }
                    //获取字符输入流
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                    String strContent = null;
                    while((strContent = br.readLine()) != null){
                        sb.append(strContent);
                    }
                    String content = sb.toString();
                    JSONObject j = JSON.parseObject(content);
                    Integer ok = j.getInteger("ok");
                    String msg = j.getString("msg");
                    if(ok  == 1){
                        return new ClanResult(true,msg);
                    }else{
                        return new ClanResult(false,msg);
                    }
                }
            }
            return new ClanResult(false,"查询失败，接口正在维护！");
        } catch (Exception e) {
            return new ClanResult(false,"查询超时，无法获取图片！");
        }finally{
            long endTime=System.currentTimeMillis();
            System.out.println(responseCode+"傻仙接口调用时间： "+(endTime-startTime)+"ms  "+data.get("info"));
            //关闭流和链接
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn){
                conn.disconnect();
            }
        }
    }

    public static ClanResult getOSSImage(String retrueImagePath) {
        long startTime1 = System.currentTimeMillis();
        HttpURLConnection conn = null;
        BufferedReader br = null;
        int responseCode = 0;
        try {
            String Url = ConfigParameter.HttpOSS_Alibaba + retrueImagePath;
            URL url = new URL(Url);
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //设置请求内核
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
            conn.setRequestProperty("Referer","936642284");
            //设置使用缓存
            conn.setUseCaches(false);
            //设置链接超时时间,毫秒为单位
            conn.setConnectTimeout(60000);
            //设置读取超时时间，毫秒为单位
            conn.setReadTimeout(60000);
            //设置当前链接是否自动处理重定向。setFollowRedirects设置所有的链接是否自动处理重定向
            //conn.setInstanceFollowRedirects(false);
            //开启链接
            conn.connect();
            //获取反馈的情况200
            responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                //获取反馈回来的数据类型
                String contentType = conn.getContentType();
                if("image/png".equals(contentType)){
                    InputStream inStream = conn.getInputStream();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] date = new byte[1024];
                    int is = 0;
                    while((is = inStream.read(date)) != -1){
                        outStream.write(date,0,is);
                    }
                    String fileurl= ConfigParameter.filePath_jarvis_Image+"\\"+retrueImagePath;
                    File file = new File(fileurl);
                    FileOutputStream op = new FileOutputStream(file);
                    op.write(outStream.toByteArray());
                    op.close();
                    return new ClanResult(true,retrueImagePath.replaceAll(".png",""),ConfigParameter.filePath_jarvis_Image,"png");
                }else {
                    return new ClanResult(false,"未知反馈，请截图反馈作者"+contentType);
                }
            }
            return new ClanResult(false,"查询失败，接口正在维护！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ClanResult(false,"查询超时，无法获取图片！");
        }finally{
            long endTime=System.currentTimeMillis();
            System.out.println(responseCode+": OOS下载图片用时： "+(endTime-startTime1)+"ms");
            //关闭流和链接
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn){
                conn.disconnect();
            }
        }
    }

    /***
     * 转码
     * @param data
     * @return
     */
    public static String urlEncode(Map<String, ?> data) {
        if(data == null){
            return "";
        }else{
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ?> i : data.entrySet()) {
                try {
                    sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }
    //将map型转为请求参数型
    public static String urlEncode(String data) {
        if(data == null){
            return "";
        }else{
            StringBuilder sb = new StringBuilder();
            try {
                sb.append(URLEncoder.encode(data + "", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }
}
