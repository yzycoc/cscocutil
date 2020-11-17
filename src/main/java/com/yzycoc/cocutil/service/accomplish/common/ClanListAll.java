package com.yzycoc.cocutil.service.accomplish.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.result.clanAll.ClanAllListHttp;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.HttpClientUtils;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.util.BaseLocalThreadPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @program: cscocutil
 * @description: 获取部落成员全部信息
 * @author: yzy
 * @create: 2020-08-22 13:46
 * @Version 1.0
 **/
public class ClanListAll {


    public static ClanAllListHttp ClanHttp(String clanAllTag, CocEquilibrium cocservice) {
        try {
            AjaxHttpResult cocHttpResult = cocservice.get(clanAllTag, ClanApiHttp.Clan, true);
            if(!cocHttpResult.getSuccess()) {
                return new ClanAllListHttp(false,cocHttpResult.getErrorMsg());
            }
            JSONObject clans = cocHttpResult.getData();
            Integer members = clans.getInteger("members")==null?0:clans.getInteger("members");
            if(members==null||members<1) {
                return new ClanAllListHttp(false,"部落无人，请直接查询部落信息。");
            }
            ClanAllListHttp clanAllListHttp = new ClanAllListHttp();
            //取玩家信息
            List<JSONObject> palyer = new ArrayList<>();
            JSONArray palyerList = clans.getJSONArray("memberList");
            Executor threadPool = BaseLocalThreadPool.getThreadPool();
            CountDownLatch count = new CountDownLatch(palyerList.size());//玩家线程阻塞
            for (int i = 0; i < palyerList.size(); i++) {
                JSONObject jsonObject = palyerList.getJSONObject(i);
                threadPool.execute(()->{
                    //获取玩家信息保存到 玩家集合当中去
                    try {
                        String Playertag = jsonObject.getString("tag").substring(1, jsonObject.getString("tag").length());
                        AjaxHttpResult cocResult = cocservice.get(Playertag, ClanApiHttp.player, true);
                        if(cocResult.getSuccess()) {
                            palyer.add(cocResult.getData());
                        }else {
                            System.out.println("查询部落配置未找到成员:"+clans.getString("name")+"    "+clans.getString("tag")+"   玩家："+jsonObject.getString("tag"));
                        }
                    } catch (Exception e) {
                        System.out.println("查询部落配置获取部落成员失败:"+clans.getString("name")+"    "+clans.getString("tag")+"   玩家："+jsonObject.getString("tag"));
                    }finally {
                        count.countDown();
                    }
                });
            }


            //获取部落图片
            CountDownLatch badgeUrlscount = new CountDownLatch(1);
            Executor threadPool2 = BaseLocalThreadPool.getThreadPool();
            threadPool2.execute(()->{
                String badgerUrlslarge = clans.getJSONObject("badgeUrls").getString("medium");
                if(badgerUrlslarge!=null) {
                    try {
                        //首先获取本地图片
                        String ImageFile = ConfigParameter.filePath_ClanImage + badgerUrlslarge.replaceAll("https://api-assets.clashofclans.com/badges/200/","");
                        BufferedImage image =  CocApiAndCqCustom.getImagesClanImage(ImageFile);
                        if(image==null) {
                            BufferedImage httpsGet = HttpClientUtils.httpsGet(badgerUrlslarge, null);
                            clanAllListHttp.setClanImage(httpsGet);
                            ImageIO.write(httpsGet, "png", new File(ImageFile));
                        }else {
                            clanAllListHttp.setClanImage(image);
                        }
                    } catch (Exception e) {
                        System.out.println("获取部落图片失败:"+clans.getString("name")+"    "+clans.getString("tag"));
                    }finally {
                        badgeUrlscount.countDown();
                    }
                }else {
                    badgeUrlscount.countDown();
                }
            });
            count.await();
            badgeUrlscount.await();
            if(palyer.size() < palyerList.size()) {
                return new ClanAllListHttp(false,"由于网络问题，有“"+(members - palyer.size())+"”个玩家数据丢失，请重新查询。");
            }
            clanAllListHttp.setClan(clans);
            clanAllListHttp.setMemberPlayer(palyer);
            clanAllListHttp.setSuccess(true);
            return clanAllListHttp;
        } catch (Exception e2) {
            return new ClanAllListHttp(false,"获取配置异常，请重新查询。如反复查询异常，请反馈给作者。");
        }
    }
}
