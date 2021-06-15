package com.yzycoc.controller.coc;


import com.yzycoc.cocutil.SQLAll.service.OpenLayoutService;
import com.yzycoc.cocutil.service.*;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.enums.JarvisEnum;
import com.yzycoc.config.ConfigParameter;
import io.swagger.annotations.*;
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
@Api(value = "COC相关接口",tags = "COC相关接口")
@Controller
public class CocApiController {

    @Autowired
    public ClanApiService clanApiService;
    @Autowired
    public JarvisApiService jarvisApiService;
    @Autowired
    public ApiService apiService;
    @Autowired
    public OpenLayoutService openLayoutService;
    /***
     * 测试链接是否正常
     * @return
     */
    @ApiOperation(value = "查询机器人状态", notes = "V1.0")
    @GetMapping(value={"/state","/"})
    @ResponseBody
    public Boolean state(){
        return true;
    }

    /***
     * 更新鱼情信息
     * @return
     */
    @ApiOperation(value = "更新鱼情信息", notes = "V1.1")
    @GetMapping(value="/yq")
    @ResponseBody
    public int updateYq() {
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
    @ApiOperation(value = "生成部落图片", notes = "V1.1")
    @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String")
    @GetMapping(value="/getImageClan")
    @ResponseBody
    public ClanResult getImageClanRealTime(String tag,Integer realTime){
        if(realTime!=null && realTime == 1){
            return clanApiService.getImageClan(tag,true);
        }
        return  clanApiService.getImageClan(tag,false);
    }
    /***
     * 生成玩家信息
     * @param tag
     * @return
     */
    @ApiOperation(value = "生成玩家信息", notes = "V1.1")
    @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String")
    @GetMapping(value="/getImagePlayer")
    @ResponseBody
    public ClanResult getImagePlayer(String tag,Integer realTime){
        if(realTime!=null && realTime == 1){
            return clanApiService.getImagePlayer(tag,true);
        }
        return clanApiService.getImagePlayer(tag,false);
    }
    /***
     * 生成鱼情信息
     * @return
     */
    @ApiOperation(value = "生成鱼情信息", notes = "V1.1")
    @GetMapping(value="/getImageYq")
    @ResponseBody
    public ClanResult getImageYq(){
        return clanApiService.getImageYq();
    }

    /***
     * coc统计
     * @param tag
     * @return
     */
    @ApiOperation(value = "coc统计", notes = "V1.1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String"),
            @ApiImplicitParam(name="qqcode",value = "查询用户的标识",dataType = "String"),
            @ApiImplicitParam(name="type",value = "类型",dataType = "String"),
    })
    @GetMapping(value="/imageClanstat")
    @ResponseBody
    public ClanResult imageClanStatistics(String tag,String qqcode,String type){
        return clanApiService.getImageClanStatistics(tag,qqcode,type);
    }


    /***
     * coc统计
     * @param tag
     * @return
     */
    @ApiOperation(value = "coc统计HTML", notes = "V1.1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String"),
            @ApiImplicitParam(name="qqcode",value = "查询用户的标识",dataType = "String"),
            @ApiImplicitParam(name="type",value = "类型",dataType = "String"),
    })
    @GetMapping(value="/imageClanStatisticsHtml")
    @ResponseBody
    public ClanResult imageClanStatisticsHtml(String tag,String qqcode,String type){
        return clanApiService.imageClanStatisticsHtml(tag,qqcode,type);
    }
    /***
     * 生成部落配置 -- 大本营
     * @param tag
     * @return
     */
    @ApiOperation(value = "生成部落科技配置", notes = "V1.1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String"),
            @ApiImplicitParam(name="qqcode",value = "查询用户的标识",dataType = "String"),
    })
    @GetMapping(value="/getImageClanAll")
    @ResponseBody
    public ClanResult getImageClanAll(String tag,String qqcode)
    {
        return clanApiService.getImageClanAll(tag,qqcode);
    }
    /***
     * 部落配置分析 -- 文本
     * @param tag
     * @return
     */
    @ApiOperation(value = "部落配置分析[文本]", notes = "V1.1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String"),
            @ApiImplicitParam(name="qqcode",value = "查询用户的标识",dataType = "String"),
    })
    @GetMapping(value="/getImageClanAllCollectText")
    @ResponseBody
    public ClanResult getImageClanAllCollectText(String tag,String qqcode){
        return clanApiService.getImageClanAllCollectText(tag,qqcode);
    }
    /***
     * 部落配置分析 -- 图片
     * @param tag
     * @return
     */
    @ApiOperation(value = "部落配置分析[图片]", notes = "V1.1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String"),
            @ApiImplicitParam(name="qqcode",value = "查询用户的标识",dataType = "String"),
    })
    @GetMapping(value="/getImageClanAllCollectImage")
    @ResponseBody
    public ClanResult getImageClanAllCollectImage(String tag,String qqcode){
        return clanApiService.getImageClanAllCollectImage(tag,qqcode);
    }
    /***
     * 查询部落名
     * @param name
     * @return
     */
    @ApiOperation(value = "查询部落名", notes = "V1.1")
    @ApiImplicitParam(name="name",value = "原查询内容",dataType = "String")
    @GetMapping(value="/getNameClan")
    @ResponseBody
    public ClanResult getNameClan(String name){
        System.out.println("查询部落名:"+name);
        String[] tag = name.split("#");
        return clanApiService.getNameClan(tag);
    }
    /***
     * 查询玩家名
     * @param name
     * @return
     */
    @ApiOperation(value = "查询玩家名", notes = "V1.1")
    @ApiImplicitParam(name="name",value = "原查询内容",dataType = "String")
    @GetMapping(value="/getNamePlayer")
    @ResponseBody
    public ClanResult getNamePlayer(String name){
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
    @ApiOperation(value = "jarvis", notes = "V1.1")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String"),
            @ApiImplicitParam(name="jarvis",value = "类型clanwar：部落战，leagueinfo：联赛，leaguewar：联赛对战",dataType = "String")

    })
    @GetMapping(value="/getJarvis")
    @ResponseBody
    public ClanResult getJarvis(String tag, String jarvis, String war){
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
    @ApiOperation(value = "获取部落", notes = "V1.1")
    @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String")
    @GetMapping(value="/getClan")
    @ResponseBody
    public ClanResult getClan(String tag){
        return clanApiService.getClan(tag);
    }

    /**
     * 获取部落
     * @param tag
     * @return
     */
    @ApiOperation(value = "获取部落", notes = "V1.1")
    @ApiImplicitParam(name="tag",value = "标签[不带#]",dataType = "String")
    @GetMapping(value="/getPlayer")
    @ResponseBody
    public ClanResult getPlayer(String tag){
        return clanApiService.getPlayer(tag);
    }

    /**
     * 获取一个随机阵型
     * @return
     */
    @ApiOperation(value = "获取一个随机阵型", notes = "V1.1")
    @GetMapping(value="/getRandomFormation")
    @ResponseBody
    public ClanResult getRandomFormation(String type){
         System.out.println("获取随机阵型！");
        return openLayoutService.getRandomFormation(type);
    }
    /**
     * 获取一个阵型
     * @return
     */
    @ApiOperation(value = "根据标号查询阵型", notes = "V1.1")
    @ApiImplicitParam(name="tag",value = "编号",dataType = "String")
    @GetMapping(value="/getFormation")
    @ResponseBody
    public ClanResult getFormation(String tag){
        System.out.println("获取阵型:"+tag);
        return openLayoutService.getFormation(tag);
    }
    /***
     * 重新启动机器人
     * @param path
     */
    @ApiOperation(value = "重新启动机器人", notes = "V1.1")
    @GetMapping(value="/result")
    @ResponseBody
    public void result(String path){
        apiService.resultQQRobot(path);
    }



    /***
     * 图片下载
     * @param id
     * @param response
     * @param request
     * @return
     */
    @ApiOperation(value = "图片下载", notes = "V1.1")
    @ApiImplicitParam(name="id",value = "uuid",dataType = "String")
    @GetMapping(value="/imageDown")
    public void imageDown(String id, HttpServletResponse response,HttpServletRequest request){
        ClanResult  result = apiService.imageDown(id,response,request);
        System.out.println("----------------------------------------------------------\n\t" +
                "请求地址: \t"+ request.getRequestURL() + "\n\t" +
                "图片: \t" + request.getQueryString() + "\n\t" +
                "请求反馈结果: \t "+result.toString()+" \n" +
                "----------------------------------------------------------");
    }

}
