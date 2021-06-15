package com.yzycoc.cocutil.SQLMy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.PublicLexicon;
import com.yzycoc.cocutil.SQLAll.service.PublicLexiconService;
import com.yzycoc.cocutil.SQLMy.bean.MyLexicon;
import com.yzycoc.cocutil.SQLMy.mapper.MyLexiconMapper;
import com.yzycoc.cocutil.SQLMy.service.MyLexiconService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.HttpClientUtils;
import com.yzycoc.custom.HttpRequest;
import com.yzycoc.custom.result.Result;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.javassist.compiler.Lex;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-18 15:05
 * @Version 1.0
 **/
@Service(value = "MyLexcionService")
@Primary
public class IMyLexcionImpl extends ServiceImpl<MyLexiconMapper, MyLexicon> implements MyLexiconService {
    @Autowired
    private PublicLexiconService publicLexiconService;
    @Transactional
    @Override
    public Result<?> SynchronizationLexicon() {
        System.out.println("正在更新词库！");
        List<String> ids = new ArrayList<>();
        ids.add("14");//COC所有信息
        ids.add("1");//部落冲突各个版本下载
        ids.add("5");//微信机器人专用词库
        ids.add("6");//微信机器人需要下载的图片
        ids.add("10");//天使专属词库
        ids.add("18");//需要下载图片的
        ids.add("7");//公共的
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("1","1");
        this.remove(queryWrapper);
        List<PublicLexicon> lexicon = publicLexiconService.query().in("publictype_id", ids).list();
        for (int i = 0; i < lexicon.size(); i++) {
            PublicLexicon pl = lexicon.get(i);
            MyLexicon myLexicon = new MyLexicon();
            if(pl.getPublictypeId() == 18 || pl.getPublictypeId() == 6 ){
                String response = pl.getResponse();
                response = HttpImage(response,pl.getAntistop());
                pl.setResponse(response);
            }
            BeanUtils.copyProperties(pl, myLexicon);
            myLexicon.setIsuser(true);
            myLexicon.setSum(0);
            if(pl.getPublictypeId() == 10 || pl.getPublictypeId() == 18){
                myLexicon.setQunnumber("QQ");
            }else if(pl.getPublictypeId() == 5 || pl.getPublictypeId() == 6){
                myLexicon.setQunnumber("微信");
            }else{
                myLexicon.setQunnumber("公共");
            }
            this.save(myLexicon);
            System.out.print(lexicon.get(i).getId()+",");
        }
        return Result.ok("更新成功");
    }

    private String HttpImage(String response,String antistop) {
        String pattern = "\\[CQ[^\\[]+\\]";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(response);
        List<String> slist = new ArrayList<String>();
        while (m.find()) {
            slist.add(m.group());
        }
        for (int i = 0; i < slist.size(); i++) {
            try {
                String name = slist.get(i);
                String[] split = name.replace("[", "").replace("]", "").split(",");
                if(split.length == 2&&split[0].contains("CQ:image")){
                    String values = split[1];
                    String url = values.split("=")[1];
                    String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
                    File dest = new File(ConfigParameter.file_vips+"\\"+fileName);
                    if(!dest.exists() || dest.length() == 0){
                        InputStream inputStream = HttpClientUtils.httpsGetFile(url, new HashMap<>());
                        // 本例是储存到本地文件系统，fileRealName为你想存的文件名称
                        OutputStream output = new FileOutputStream(dest);
                        int len = 0;
                        byte[] ch = new byte[1024];
                        while ((len = inputStream.read(ch)) != -1) {
                            output.write(ch, 0, len);
                        }
                        output.close();
                        System.out.print(url+"|"+fileName+"|");
                    }
                    response = response.replace(url,ConfigParameter.file_vips+"\\"+fileName);
                    System.out.println(fileName+"文件存在！");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return response;
    }
}
