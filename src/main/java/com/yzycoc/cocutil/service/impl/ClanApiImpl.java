package com.yzycoc.cocutil.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import com.yzycoc.cocutil.SQLAll.service.OpenLayoutService;
import com.yzycoc.cocutil.SQLAll.service.VipCocAstrictService;
import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import com.yzycoc.cocutil.SQLClan.service.ClanNameService;
import com.yzycoc.cocutil.SQLClan.service.PlayerNameService;
import com.yzycoc.cocutil.service.ClanApiService;
import com.yzycoc.cocutil.service.accomplish.image.*;
import com.yzycoc.cocutil.service.accomplish.text.TextClanName;
import com.yzycoc.cocutil.service.accomplish.text.TextClanPlayer;
import com.yzycoc.cocutil.service.accomplish.yq.YqGetHtml;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.result.AjaxHttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @program: cscocutil
 * @description: Coc工具实现
 * @author: yzy
 * @create: 2020-08-10 20:41
 * @Version 1.0
 **/
@Service
public class ClanApiImpl implements ClanApiService {
    @Autowired
    private CocEquilibrium cocHttp;
    @Autowired
    private ClanNameService clannameService;
    @Autowired
    private PlayerNameService playerNameService;
    @Autowired
    private YuQingService yuQingService;
    @Autowired
    private OpenLayoutService openLayoutService;
    @Autowired //用户限制登录
    private VipCocAstrictService vipCocAstrictService;
    @Override
    public ClanResult getImageClan(String tag,Boolean realTime) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "getImageClan" + tag;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"此部落图片已在生成中，请勿重复获取。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        try {
            ClanResult clanResults = null;
            if(realTime!=null&&realTime){
                clanResults = new ImageClan().getRealTime(tag, cocHttp);
            }else{
                clanResults = new ImageClan().get(tag, cocHttp);
            }
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResults;
        } catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"查询部落出现异常，请反馈作者！");
        }
    }
    @Override
    public ClanResult getImagePlayer(String tag,Boolean realTime) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "getImagePlayer" + tag;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"此部落图片已在生成中，请勿重复获取。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        try {
            ClanResult clanResult = null;
            if(realTime!=null&&realTime){
                clanResult = new ImagePlayer().getRealTime(tag, cocHttp);
            }else{
                clanResult = new ImagePlayer().get(tag, cocHttp);
            }
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResult;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"查询玩家出现异常，请反馈作者！");
        }
    }
    
    
    @Override
    public ClanResult getImageYq() {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "yq";
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"鱼情图片已在生成中，请稍等几分钟后重新获取，");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        //实现
        try {
            ClanResult yqRedis = ConfigParameter.clanCacheImage.get("yqRedis");
            if(yqRedis != null){
                return yqRedis;
            }
            ClanResult clanResult = new ImageYq().get();
            ConfigParameter.clanCache.remove(clanCacheName);
            if(clanResult.getSuccess()){
                ConfigParameter.clanCacheImage.putPlusMinutes("yqRedis",clanResult,3);
            }
            return clanResult;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"鱼情出现异常，请反馈作者！");
        }
    }

    @Override
    public Boolean updateYq() {
        try {
            ConfigParameter.clanCache.detect();
            Boolean isExist = ConfigParameter.clanCache.get("getyuqing");
            if(isExist != null) return false;
            ConfigParameter.clanCache.putPlusMinutes("getyuqing",true,4);
            System.out.println("正在获取鱼情中。。。。。");
            YqGetHtml yu = new YqGetHtml();
            YuQing yuQingEntity = yu.getYuQingEntity(yuQingService);
            yuQingEntity.setHtmltime(TimeUtiles.getStringDate());
            String yqHtml = yu.getYqStartHtml();
            yuQingEntity.setHtml(yqHtml);
            yuQingEntity.setId(1);
            yuQingService.updateById(yuQingEntity);
            System.out.println("鱼情获取成功！！！");
            ConfigParameter.clanCache.remove("getyuqing");
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public ClanResult getImageClanAll(String tag, String qqcode) {
        String clanCacheName = "getImageClanAll" + tag;
        String clanCacheQqcode = "ImageClanAllCode"+qqcode;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"此部落科技配置图片已在生成中，请勿重复获取。");

        isExist = ConfigParameter.clanCache.get(clanCacheQqcode);
        if(isExist != null) return new ClanResult(false,"您上一个查询还未完成，请等待上一个查询结束后在查询。");
        //添加 该部落 的查询 限制
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        //用户唯一标识的限制
        ConfigParameter.clanCache.putPlusMinutes(clanCacheQqcode,true,4);
        //查询用户是否还有可查询的剩余次数
        Boolean t = vipCocAstrictService.isGoto(qqcode,"getImageClanAll",1);
        if(t){
            return new ClanResult(false,"由于用户增加，为减少服务器负担，每日仅提供一次查询，每日0点恢复。如需增加查询，您可以赞助后即恢复无限制查询机会。");
        }
        try {
            ClanResult clanResults = new ImageClanAll().get(tag,cocHttp);
            if(clanResults.getSuccess()){
                /**如果成功，则此用户无法进行查询 + 1*/
                vipCocAstrictService.isOk(qqcode,"getImageClanAll");
            }
            ConfigParameter.clanCache.remove(clanCacheName);
            ConfigParameter.clanCache.remove(clanCacheQqcode);
            return clanResults;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            ConfigParameter.clanCache.remove(clanCacheQqcode);
            return new ClanResult(false,"查询部落科技配置图片异常，请反馈作者！");
        }

    }

    @Override
    public ClanResult getImageClanAllCollectText(String tag, String qqcode) {
        String clanCacheName = "getImageClanAllCollectText" + tag;
        String clanCacheQqcode = "getImageClanAllCollectText" + qqcode;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"此部落配置图片已在生成中，请勿重复获取。");

        isExist = ConfigParameter.clanCache.get(clanCacheQqcode);
        if(isExist != null) return new ClanResult(false,"您上一个查询还未完成，请等待上一个查询结束后在查询。");
        //添加 该部落 的查询 限制
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        //用户唯一标识的限制
        ConfigParameter.clanCache.putPlusMinutes(clanCacheQqcode,true,4);
        //查询用户是否还有可查询的剩余次数
        Boolean t = vipCocAstrictService.isGoto(qqcode,"getImageClanAll",1);
        if(t){
            return new ClanResult(false,"由于用户增加，为减少服务器负担，每日仅提供一次查询，每日0点恢复。如需增加查询，您可以赞助后即恢复无限制查询机会。");
        }
        try {
            ClanResult clanResults = new ImageClanAllCollectText().get(tag,cocHttp);
            ConfigParameter.clanCache.remove(clanCacheName);
            ConfigParameter.clanCache.remove(clanCacheQqcode);
            return clanResults;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            ConfigParameter.clanCache.remove(clanCacheQqcode);
            return new ClanResult(false,"查询部落配置出现异常，请反馈作者！");
        }
    }

    @Override
    public ClanResult getImageClanAllCollectImage(String tag, String qqcode) {
        String clanCacheName = "getImageClanAllCollectImage" + tag;
        String clanCacheQqcode = "getImageClanAllCollectImage" + qqcode;

        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"此部落配置图片已在生成中，请勿重复获取。");

        isExist = ConfigParameter.clanCache.get(clanCacheQqcode);
        if(isExist != null) return new ClanResult(false,"您上一个查询还未完成，请等待上一个查询结束后在查询。");

        //添加 该部落 的查询 限制
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        //用户唯一标识的限制
        ConfigParameter.clanCache.putPlusMinutes(clanCacheQqcode,true,4);
        //查询用户是否还有可查询的剩余次数
        Boolean t = vipCocAstrictService.isGoto(qqcode,"getImageClanAll",1);
        if(t){
            return new ClanResult(false,"由于用户增加，为减少服务器负担，每日仅提供一次查询，每日0点恢复。如需增加查询，您可以赞助后即恢复无限制查询机会。");
        }
        try {
            ClanResult clanResults = new ImageClanAllCollectImage().get(tag,cocHttp);
            ConfigParameter.clanCache.remove(clanCacheName);
            ConfigParameter.clanCache.remove(clanCacheQqcode);
            return clanResults;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            ConfigParameter.clanCache.remove(clanCacheQqcode);
            return new ClanResult(false,"查询部落配置图片出现异常，请反馈作者！");
        }
    }

    @Override
    public ClanResult getNameClan(String Name[]) {
        String clanCacheName = "getNameClan" + Arrays.toString(Name);
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"正在查询部落名中，请勿重复查询。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,2);
        try {
            ClanResult clanResults = new TextClanName().get(Name,clannameService);
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResults;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"查询部落名异常，请反馈作者！");
        }

    }

    @Override
    public ClanResult getNamePlayer(String[] Name) {
        String clanCacheName = "getNamePlayer" + Arrays.toString(Name);
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"正在查询玩家名中，请勿重复查询。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,2);
        try {
            ClanResult clanResults = new TextClanPlayer().get(Name,playerNameService);
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResults;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"查询玩家名异常，请反馈作者！");
        }
    }

    @Override
    public ClanResult getClan(String tag) {
        String clanCacheName = "getClan" + tag;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"正在获取玩家信息中，请勿重复获取。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        try {
            AjaxHttpResult ajaxHttpResult = cocHttp.get(tag, ClanApiHttp.Clan, true);
            ClanResult clanResult = new ClanResult();
            if(ajaxHttpResult.getSuccess()){
                JSONObject data = ajaxHttpResult.getData();
                clanResult.setSuccess(true);
                clanResult.setResult(data.toString());
            }else{
                clanResult.setSuccess(false);
                clanResult.setResult("部落信息获取失败。");
            }
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResult;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"获取部落JSON异常，请反馈作者！");
        }

    }

    @Override
    public ClanResult getPlayer(String tag) {
        String clanCacheName = "getPlayer" + tag;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if(isExist != null) return new ClanResult(false,"正在获取玩家信息中，请勿重复获取。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName,true,4);
        try {
            AjaxHttpResult ajaxHttpResult = cocHttp.get(tag, ClanApiHttp.player, true);
            ClanResult clanResult = new ClanResult();
            if(ajaxHttpResult.getSuccess()){
                JSONObject data = ajaxHttpResult.getData();
                clanResult.setSuccess(true);
                clanResult.setResult(data.toString());
            }else{
                clanResult.setSuccess(false);
                clanResult.setResult("部落信息获取失败。");
            }
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResult;
        }catch (Exception e){
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false,"获取玩家JSON异常，请反馈作者！");
        }

    }

}
