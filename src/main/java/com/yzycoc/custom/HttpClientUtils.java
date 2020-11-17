package com.yzycoc.custom;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpUtils;

import com.yzycoc.config.ConfigParameter;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: cscocutil
 * @description: Https
 * @author: yzy
 * @create: 2020-08-02 10:53
 * @Version 1.0
 **/
public class HttpClientUtils {



    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);


    /**
     * 定义类型，用于获取不同类型的httpclient
     */
    enum CLIENT_TYPE {
        HTTP, HTTPS
    }

    /**
     * https post请求
     *
     * @param url        请求地址
     * @param headerMap  请求头信息
     * @param contentMap 请求体信息
     * @return 结果返回
     */
    public static String httpsPost(String url, Map<String, String> headerMap, Map<String, String> contentMap) {
        return httpsPost(url, headerMap, contentMap, "UTF-8");
    }

    /**
     * http post请求
     *
     * @param url        请求地址
     * @param headerMap  请求头信息
     * @param contentMap 请求体信息
     * @return 结果返回
     */
    public static String httpPost(String url, Map<String, String> headerMap, Map<String, String> contentMap) {
        return httpPost(url, headerMap, contentMap, "UTF-8");
    }

    /**
     * https get请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @return 结果返回
     */
    public static BufferedImage httpsGet(String url, Map<String, String> paramMap) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<BufferedImage> future = new FutureTask<BufferedImage>(new Callable<BufferedImage>() {
            @Override
            public BufferedImage call() throws Exception {
                return httpsGet(url, paramMap, "UTF-8");
            }
        });
        executor.execute(future);
        try {
            return future.get(90000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
        }finally {
            future.cancel(true);
            executor.shutdown();
        }

        return null;
    }
    /**
     * https get请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @return 结果返回
     */
    public static String httpsGetTxt(String url, Map<String, String> paramMap) {
        return httpsGetTxt(url, paramMap, "UTF-8");
    }

    /**
     * http get请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @return 结果返回
     */
    public static BufferedImage httpGet(String url, Map<String, String> paramMap) {
        return httpGet(url, paramMap, "UTF-8");
    }

    /**
     * @param url        请求地址
     * @param headerMap  请求头信息
     * @param contentMap 请求体信息
     * @param charset    编码类型
     * @return 结果返回
     */
    public static String httpsPost(String url, Map<String, String> headerMap, Map<String, String> contentMap, String charset) {
        return post(url, headerMap, contentMap, charset, CLIENT_TYPE.HTTPS);
    }

    /**
     * @param url        请求地址
     * @param headerMap  请求头信息
     * @param contentMap 请求体信息
     * @param charset    编码类型
     * @return 结果返回
     */
    public static String httpPost(String url, Map<String, String> headerMap, Map<String, String> contentMap, String charset) {
        return post(url, headerMap, contentMap, charset, CLIENT_TYPE.HTTP);
    }

    /**
     * @param url      请求地址
     * @param paramMap 请求参数
     * @param charset  编码类型
     * @return 结果返回
     */
    public static BufferedImage httpsGet(String url, Map<String, String> paramMap, String charset) {
        return get(url, paramMap, charset, CLIENT_TYPE.HTTPS);
    }
    /**
     * @param url      请求地址
     * @param paramMap 请求参数
     * @param charset  编码类型
     * @return 结果返回
     */
    public static String httpsGetTxt(String url, Map<String, String> paramMap, String charset) {
        return getTxt(url, paramMap, charset, CLIENT_TYPE.HTTPS);
    }
    /**
     * @param url      请求地址
     * @param paramMap 请求参数
     * @param charset  编码类型
     * @return 结果返回
     */
    public static BufferedImage httpGet(String url, Map<String, String> paramMap, String charset) {
        return get(url, paramMap, charset, CLIENT_TYPE.HTTP);
    }

    /**
     * post 请求的实际方法
     *
     * @param url        请求地址
     * @param headerMap  请求头信息
     * @param contentMap 请求体信息
     * @param charset    编码类型
     * @param type       协议类型
     * @return 结果返回
     */
    private static String post(String url, Map<String, String> headerMap, Map<String, String> contentMap, String charset, CLIENT_TYPE type) {

        String result = null;
        HttpClient httpClient = null;
        try {
            HttpPost post = new HttpPost(url);
            if (headerMap!=null) {// 设置请求头
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }

            if (contentMap!=null) {// 设置请求体
                List<NameValuePair> content = getNameValuePairList(contentMap);
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, charset);
                post.setEntity(entity);
            }

            httpClient = getClient(type);//这里是重点，根据不同协议获取不同类型的client端
            HttpResponse response = httpClient.execute(post);//发送请求并接收返回数据
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("请求：" + url + " 异常:" + ex.getMessage());
        } finally {
            try {
                if (null != httpClient && null != httpClient.getConnectionManager()) {
                    httpClient.getConnectionManager().shutdown();
                }
            } catch (Exception e) {
                logger.error("请求：" + url + " 流关闭异常或者httpclient关闭异常");
            }
        }
    }

    /**
     * get 请求的实际方法
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @param charset  编码类型
     * @param type     协议类型
     * @return 结果返回
     */
    private static BufferedImage get(String url, Map<String, String> paramMap, String charset, CLIENT_TYPE type) {

        HttpClient httpClient = null;
        try {
            if (paramMap!=null) {// 拼接参数
                // 设置请求体
                List<NameValuePair> content = getNameValuePairList(paramMap);
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, charset);
                String params = EntityUtils.toString(entity);
                url = url + "?" + params;
            }
            HttpGet get = new HttpGet(url);
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            httpClient = getClient(type);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //连接超时时间
                    .setSocketTimeout(1000) //数据传输的超时时间
                    .build();
            get.setConfig(config);
            HttpResponse response = httpClient.execute(get);            //发送请求并接收返回数据
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                String value = resEntity.getContentType().getValue();
                System.out.println("返回图片为"+value);
                InputStream content = resEntity.getContent();
                BufferedImage image = ImageIO.read(content);
                return image;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("请求：" + url + " 异常:" + ex.getMessage());
        } finally {
            try {

            } catch (Exception e) {
                logger.error("请求：" + url + " 流关闭异常或者httpclient关闭异常");
            }
        }
    }

    /**
     * get 请求的实际方法
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @param charset  编码类型
     * @param type     协议类型
     * @return 结果返回
     */
    private static String getTxt(String url, Map<String, String> paramMap, String charset, CLIENT_TYPE type) {
        String result = "";
        HttpClient httpClient = null;
        try {
            if (paramMap!=null) {// 拼接参数
                // 设置请求体
                List<NameValuePair> content = getNameValuePairList(paramMap);
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, charset);
                String params = EntityUtils.toString(entity);
                url = url + "?" + params;
            }
            HttpGet get = new HttpGet(url);
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            httpClient = getClient(type);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
                    .setSocketTimeout(1000*10) //数据传输的超时时间
                    .build();
            get.setConfig(config);
            HttpResponse response = httpClient.execute(get);            //发送请求并接收返回数据
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
                return result;
            }
            return null;
        } catch (Exception ex) {
            throw new RuntimeException("请求：" + url + " 异常:" + ex.getMessage());
        } finally {
            try {

            } catch (Exception e) {
                logger.error("请求：" + url + " 流关闭异常或者httpclient关闭异常");
            }
        }
    }
    private static List<NameValuePair> getNameValuePairList(Map<String, String> paramMap) {
        List<NameValuePair> content = null;
        if (paramMap!=null) {
            // 设置请求体
            content = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                content.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return content;
    }

    /**
     * 根据指定类型返回http、https类型的客户端
     *
     * @param type 类型
     * @return 客户端
     * @throws Exception 异常信息
     */
    private static SSLClient getClient(CLIENT_TYPE type) throws Exception {
        if(type == CLIENT_TYPE.HTTPS) {//https类型
            return new SSLClient();
        } else {
            return new SSLClient();
        }
    }

    /**
     * 自定义SSL client
     */
    static class SSLClient extends DefaultHttpClient {
        public SSLClient() throws Exception {
            super();
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            //传输协议需要根据自己的判断　
            //SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = this.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        }
    }



    public static String httpGetCqRobot(String url, Map<String, String> paramMap,String saveFilePath) {
        HttpClient httpClient = null;
        try {
            if (paramMap!=null) {// 拼接参数
                // 设置请求体
                List<NameValuePair> content = getNameValuePairList(paramMap);
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, "UTF-8");
                String params = EntityUtils.toString(entity);
                url = url + "?" + params;
            }
            HttpGet get = new HttpGet(url);
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            httpClient = getClient(CLIENT_TYPE.HTTPS);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //连接超时时间
                    .setSocketTimeout(1000) //数据传输的超时时间
                    .build();
            get.setConfig(config);
            HttpResponse response = httpClient.execute(get);            //发送请求并接收返回数据
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                String value = resEntity.getContentType().getValue();
                System.out.println("返回图片为"+value);
                String[] split = value.split("/");
                String ImageType = "png";
                InputStream content = resEntity.getContent();
                BufferedImage cocImageAll = ImageIO.read(content);
                Thumbnails.of(cocImageAll).outputFormat(ImageType).scale(1f).outputQuality(1f).toFile(new File(ConfigParameter.filePath_vip+"\\"+saveFilePath));
                return ImageType;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new RuntimeException("请求：" + url + " 异常:" + ex.getMessage());
            return null;
        } finally {
            try {

            } catch (Exception e) {
                logger.error("请求：" + url + " 流关闭异常或者httpclient关闭异常");
            }
        }
    }

    public static void main(String[] args) {
        String a = "image/gif";
        String[] split = a.split("/");
        System.out.println(Arrays.toString(split));
    }
}
