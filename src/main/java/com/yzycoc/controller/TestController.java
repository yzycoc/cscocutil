package com.yzycoc.controller;

import com.yzycoc.cocutil.SQLAll.bean.PublicLexicon;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocUser;
import com.yzycoc.cocutil.SQLAll.service.ApplyCocUserService;
import com.yzycoc.cocutil.SQLAll.service.PublicLexiconService;
import com.yzycoc.cocutil.SQLMy.bean.MyLexicon;
import com.yzycoc.cocutil.SQLMy.service.MyLexiconService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.util.DemoEntity;
import com.yzycoc.util.GetIp;
import com.yzycoc.util.MyDemo;
import com.yzycoc.util.tableImage.ImageTable;
import com.yzycoc.util.tableImage.ImageUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-10 16:48
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping(value="/test")
@Api(value = "Z-测试",tags = "测试接口")
public class TestController {
    @Autowired
    private ApplyCocUserService publicLexiconService;

    @ApiOperation(value = "A-获取访问的所有数据信息", notes = "V1.0")
    @RequestMapping(value = "/message",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getMessge(HttpServletRequest request){
        String ip = GetIp.getIp(request);
        StringBuffer info = new StringBuffer();
        info.append("\n----------------------------------------------------------\n");
        info.append("\t访问此接口的用户信息:\n");
        info.append("\t用户IP: \t "+ip+"\n");
        Enumeration er = request.getHeaderNames();//获取请求头的所有name值
        while(er.hasMoreElements()){
            String name	=(String) er.nextElement();
            String value = request.getHeader(name);
            info.append("\t"+name+": \t "+value+"\n");
        }
        info.append("----------------------------------------------------------");
        log.info(info.toString());
        return info.toString();
    }


    @ApiOperation(value = "I-获取所有数据", notes = "V1.0")
    @RequestMapping(value = "/image",method = {RequestMethod.GET})
    public void getImage(HttpServletResponse response){
        List<ApplyCocUser> list = publicLexiconService.list();
        try {
            BufferedImage image = new ImageUtil().tableIamge(new ImageTable<ApplyCocUser>().table(list));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @ApiOperation(value = "I-获取所有数据", notes = "V1.0")
    @RequestMapping(value = "/imageDemo",method = {RequestMethod.GET})
    public void getImageDemo(HttpServletResponse response){
        try {
            List<DemoEntity> ima = new ArrayList<>();
            ima.add(new DemoEntity("测试","heigths"));
            ima.add(new DemoEntity("高度","demo"));
            BufferedImage image =new ImageUtil().tableIamge(new ImageTable<DemoEntity>().table(ima));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
