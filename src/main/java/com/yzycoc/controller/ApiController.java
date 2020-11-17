package com.yzycoc.controller;


import com.yzycoc.cocutil.SQLAll.service.OpenLayoutService;
import com.yzycoc.cocutil.service.*;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.enums.JarvisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: cscocutil
 * @description: API接口
 * @author: yzy
 * @create: 2020-08-30 11:19
 * @Version 1.0
 **/
@Controller
public class ApiController {

    @Autowired
    private ClanApiService clanApiService;
    @Autowired
    private JarvisApiService jarvisApiService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private OpenLayoutService openLayoutService;
    /***
     * 测试链接是否正常
     * @return
     */
    @RequestMapping(value={"/state","/"})
    @ResponseBody
    private Boolean state(){
        return true;
    }

    /***
     * 更新鱼情信息
     * @return
     */
    @RequestMapping(value="/yq")
    @ResponseBody
    private int updateYq() {
        try {
            return clanApiService.updateYq()==true?1:0;
        } catch (Exception e) {
            System.out.println("鱼情获取失败！！！");
            return 0;
        }
    }

    /***
     * 生成部落图片
     * @param tag
     * @return
     */
    @RequestMapping(value="/getImageClan")
    @ResponseBody
    private ClanResult getImageClan(String tag){
       return clanApiService.getImageClan(tag);
    }
    /***
     * 生成玩家信息
     * @param tag
     * @return
     */
    @RequestMapping(value="/getImagePlayer")
    @ResponseBody
    private ClanResult getImagePlayer(String tag){
        return clanApiService.getImagePlayer(tag);
    }
    /***
     * 生成鱼情信息
     * @return
     */
    @RequestMapping(value="/getImageYq")
    @ResponseBody
    private ClanResult getImageYq(){
        return clanApiService.getImageYq();
    }
    /***
     * 生成部落配置 -- 大本营
     * @param tag
     * @return
     */
    @RequestMapping(value="/getImageClanAll")
    @ResponseBody
    private ClanResult getImageClanAll(String tag)
    {
        return clanApiService.getImageClanAll(tag);
    }
    /***
     * 部落配置分析 -- 文本
     * @param tag
     * @return
     */
    @RequestMapping(value="/getImageClanAllCollectText")
    @ResponseBody
    private ClanResult getImageClanAllCollectText(String tag){
        return clanApiService.getImageClanAllCollectText(tag);
    }
    /***
     * 部落配置分析 -- 图片
     * @param tag
     * @return
     */
    @RequestMapping(value="/getImageClanAllCollectImage")
    @ResponseBody
    private ClanResult getImageClanAllCollectImage(String tag){
        return clanApiService.getImageClanAllCollectImage(tag);
    }
    /***
     * 查询部落名
     * @param name
     * @return
     */
    @RequestMapping(value="/getNameClan")
    @ResponseBody
    private ClanResult getNameClan(String name){
        System.out.println("查询部落名:"+name);
        String[] tag = name.split("#");
        return clanApiService.getNameClan(tag);
    }
    /***
     * 查询玩家名
     * @param name
     * @return
     */
    @RequestMapping(value="/getNamePlayer")
    @ResponseBody
    private ClanResult getNamePlayer(String name){
        System.out.println("查询玩家名:"+name);
        String[] tag = name.split("#");
        return clanApiService.getNamePlayer(tag);
    }

    /**
     * jarvis 通用接口
     * @param tag
     * @param jarvis
     * @param war
     * @return
     */
    @RequestMapping(value="/getJarvis")
    @ResponseBody
    private ClanResult getJarvis(String tag, String jarvis, String war){
        JarvisEnum jarvisEnum = JarvisEnum.get(jarvis);
        if(jarvisEnum == null){
            return new ClanResult(false,"无法寻找对应的列表，请反馈作者："+jarvis);
        }
        if(StringUtils.isEmpty(war)){
            return jarvisApiService.getClanwar(tag,jarvisEnum);
        }
        return jarvisApiService.getClanwar(tag,jarvisEnum,war);
    }

    /**
     * 获取部落
     * @param tag
     * @return
     */
    @RequestMapping(value="/getClan")
    @ResponseBody
    private ClanResult getClan(String tag){
        return clanApiService.getClan(tag);
    }

    /**
     * 获取部落
     * @param tag
     * @return
     */
    @RequestMapping(value="/getPlayer")
    @ResponseBody
    private ClanResult getPlayer(String tag){
        return clanApiService.getPlayer(tag);
    }

    /**
     * 获取一个随机阵型
     * @return
     */
    @RequestMapping(value="/getRandomFormation")
    @ResponseBody
    private ClanResult getRandomFormation(){
         System.out.println("获取随机阵型！");
        return openLayoutService.getRandomFormation();
    }
    /**
     * 获取一个随机阵型
     * @return
     */
    @RequestMapping(value="/getFormation")
    @ResponseBody
    private ClanResult getFormation(String tag){
        System.out.println("获取阵型:"+tag);
        return openLayoutService.getFormation(tag);
    }
    /***
     * 重新启动机器人
     * @param path
     */
    @RequestMapping(value="/result")
    @ResponseBody
    private void result(String path){
        apiService.resultQQRobot(path);
    }



    /***
     * 图片下载
     * @param id
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value="/imageDown")
    @ResponseBody
    private ClanResult imageDown(String id, HttpServletResponse response,HttpServletRequest request){
        System.out.println(request.getRequestURL());
        System.out.println(request.getQueryString());
        ClanResult  result = apiService.imageDown(id,response,request);
        return result;
    }

}
