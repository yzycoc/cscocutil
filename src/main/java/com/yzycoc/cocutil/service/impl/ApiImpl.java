package com.yzycoc.cocutil.service.impl;

import com.yzycoc.cocutil.SQLAll.bean.ImageDown;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipApplyForLog;
import com.yzycoc.cocutil.SQLAll.service.ImageDownService;
import com.yzycoc.cocutil.SQLAll.service.VipApplyForLogService;
import com.yzycoc.cocutil.service.ApiService;
import com.yzycoc.cocutil.service.accomplish.image.ImageVip;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.config.ConfigParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @program: cscocutil
 * @description: 其他接口实现类
 * @author: yzy
 * @create: 2020-10-29 15:51
 * @Version 1.0
 **/
@Service
public class ApiImpl implements ApiService {


    @Autowired
    private ImageDownService imageDownService;

    @Autowired
    private VipApplyForLogService vipApplyForLogService;
    /***
     * 重新启动机器人
     * @param path
     */
    @Override
    public void resultQQRobot(String path) {
        try {
            String clanCacheName = path;
            Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
            if(isExist != null) return ;
            ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,6);
            new Thread(()->{
                System.out.println("接收重启操作："+path);
                try {
                    System.out.println("开始倒计时。");
                    Integer time = 45;
                    while (time >= 0 ){
                        System.out.print(time+" ");
                        Thread.sleep(1000);
                        time --;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行重启CQ操作！");
                Runtime rt = Runtime.getRuntime(); //返回当前应用程序的Runtime对象
                Process ps=null;//制子进程的执行或获取该子进程的信息
                try {
                    ps=rt.exec("cmd.exe /c  start  java -jar  "+path );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ConfigParameter.clanCache.remove(clanCacheName);
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ClanResult imageDown(String id,HttpServletResponse response,  HttpServletRequest request) {
        try {
            System.out.println("正在下载图片，id:"+id);
            ImageDown image = imageDownService.query().eq("file_uuid", id).lt("sum", "50").orderByDesc("create_date").last("  limit 1").one();
            if(image == null){
                return new ClanResult(false,"文件已过期，请重新生成。");
            }
            String filename = image.getFilePath();
            File file = new File(filename);
            filename = getStr(request, image.getFileName());
            if(file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                response.setContentType("application/x-msdownload");
                response.addHeader("Content-Disposition", "attachment; filename=" + filename );
                ServletOutputStream out = response.getOutputStream();
                byte[] buf=new byte[2048];
                int n=0;
                while((n=fis.read(buf))!=-1)
                    out.write(buf, 0, n);
                out.flush();
                out.close();
                fis.close();
                imageDownService.update().set("sum",Integer.valueOf(image.getSum()) + 1).eq("id",image.getId());
                return new ClanResult(true,"完成！");
            }else{
                return new ClanResult(false,"图片不存在，请重新生成。");
            }
        }catch (Exception e){
            return new ClanResult(false,"图片文件下载出现异常啦！");
        }
    }

    @Override
    public void Qrcode(String httpUrl, HttpServletRequest request, HttpServletResponse response, String uuid) {
        BufferedImage image = null;
        try {
            List<VipApplyForLog> list = vipApplyForLogService.query().eq("uuid", uuid).orderByDesc("create_date").list();
            VipApplyForLog uid = list.get(0);
            image = new ImageVip().compound(httpUrl,uid);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            response.setContentType("image/png");
            response.addHeader("Content-Disposition", "attachment; filename=" + "QrCode");
            ServletOutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = fis.read(buf)) != -1)
                out.write(buf, 0, n);
            fis.close();
            out.flush();
            out.close();
            uid.setUuidstatus("已生成");
            vipApplyForLogService.updateById(uid);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getStr(HttpServletRequest request, String fileName) {
        String downloadFileName = null;
        //仅提供了部分代码，因为我们已经明确问题的所在，知道修改那一部分了，（代码中downloadFileName 即代表 *=utf-8'zh_cn'文件名.xx部分）
        String agent = request.getHeader("USER-AGENT");
        try {
            if(agent != null && agent.toLowerCase().indexOf("firefox") > 0){
                //设置字符集
                downloadFileName = "=?UTF-8?B?" + Base64Utils.encodeToString(fileName.getBytes("UTF-8")) + "?=";
            }else{
                downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadFileName;
    }
}
