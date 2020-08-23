package com.yzycoc.cocutil.service.accomplish.image;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.JarvisEnum;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-23 13:28
 * @Version 1.0
 **/
public class ImageJarvis {
    /**
     * 两个参数
     * @param tag
     * @param jarvisEnum
     * @return
     */
    public ClanResult get(String tag, JarvisEnum jarvisEnum) {
        Map<String,Object> data = new HashMap<>();
        data.put("method", jarvisEnum.getType());
        data.put("info", "#"+tag);
        data.put("auth", ConfigParameter.Jarvis_AUTO);
        ClanResult jarvis = HttpRequest.getJarvis(ConfigParameter.Jarvis_API, data, HttpMethod.GET);
        if(!jarvis.getSuccess()){
            return jarvis;
        }
        return HttpRequest.getOSSImage(jarvis.getResult());
    }

    /***
     *
     * @param tag 标签
     * @param jarvisEnum
     * @param war 第三个参数
     * @return
     */
    public ClanResult get(String tag, JarvisEnum jarvisEnum,String war) {
        Map<String,Object> data = new HashMap<>();
        data.put("method", jarvisEnum.getType());
        data.put("info", "#"+tag);
        data.put("auth", ConfigParameter.Jarvis_AUTO);
        if(jarvisEnum == JarvisEnum.leaguewar){
            data.put("war", CocApiAndCqCustom.war.get(war));
        }
        ClanResult jarvis = HttpRequest.getJarvis(ConfigParameter.Jarvis_API, data, HttpMethod.GET);
        if(!jarvis.getSuccess()){
            return jarvis;
        }
        return HttpRequest.getOSSImage(jarvis.getResult());
    }
}
