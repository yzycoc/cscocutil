package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.result.*;
import com.yzycoc.cocutil.util.*;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.ErweimaQRCodeUtil;
import com.yzycoc.custom.HttpClientUtils;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.util.BaseLocalThreadPool;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @program: cscocutil
 * @description: 生成玩家图片
 * @author: yzy
 * @create: 2020-08-21 21:04
 * @Version 1.0
 **/
public class ImagePlayer {

    Logger log= LoggerFactory.getLogger(getClass());


    public ClanResult get(String tag, CocEquilibrium cocEquil) {
        long startTime=System.currentTimeMillis();
        log.info("开始生成村庄图片:"+tag);
        AjaxHttpResult ajaxHttpResult = cocEquil.get(tag, ClanApiHttp.player, true);
        if(!ajaxHttpResult.getSuccess()) {
            return new ClanResult(false,ajaxHttpResult.getErrorMsg());
        }
        JSONObject playersdata = ajaxHttpResult.getData();
        ImagePlayersFrom tagData = TagData(playersdata);
        //部落图片合成图片
        try {
            long endTime=System.currentTimeMillis();
            log.info("V2.0玩家 获取玩家数据成功  共耗时："+(endTime-startTime)+"ms");
            return  ImageCompound(playersdata, tagData, tag,ajaxHttpResult.getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        log.info("V2.0玩家 获取玩家数据失败  共耗时："+(endTime-startTime)+"ms");
        return new ClanResult(false,tag+"玩家图片生成失败");
    }

    public ClanResult getRealTime(String tag, CocEquilibrium cocEquil) {
        long startTime=System.currentTimeMillis();
        log.info("开始生成村庄图片:"+tag);
        AjaxHttpResult ajaxHttpResult = cocEquil.get(tag, ClanApiHttp.playerRealTime, true);
        if(!ajaxHttpResult.getSuccess()) {
            return new ClanResult(false,ajaxHttpResult.getErrorMsg());
        }
        JSONObject playersdata = ajaxHttpResult.getData();
        ImagePlayersFrom tagData = TagData(playersdata);
        //部落图片合成图片
        try {
            long endTime=System.currentTimeMillis();
            log.info("V2.0玩家 获取玩家数据成功  共耗时："+(endTime-startTime)+"ms");
            return  ImageCompound(playersdata, tagData, tag,ajaxHttpResult.getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        log.info("V2.0玩家 获取玩家数据失败  共耗时："+(endTime-startTime)+"ms");
        return new ClanResult(false,tag+"玩家图片生成失败");
    }

    private ClanResult ImageCompound(JSONObject player, ImagePlayersFrom tagData, String tag,String time) throws IOException {
        BufferedImage I = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"playImage.png"));//buffer.get("cocheader");
        Graphics2D g = (Graphics2D)I.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        /***
         * 弄头部数据 玩家
         */
        g.drawImage(tagData.getGrth(), 100, 305, 300, 300, null);

        g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,80));
        g.setColor(Color.white);
        String play_name = txt(player.getString("name"),2);
        g.drawString(play_name, 400, 435);
        String play_tag = txt(player.getString("tag"),2);
        g.setFont(new Font("微软雅黑",Font.ITALIC,40));
        g.drawString(play_tag, 410, 550);
        List<BufferedImage> gxbq = tagData.getGxbq();
        Integer gxbq_Image_whdth = 24;
        for (BufferedImage bufferedImage : gxbq) {
            g.drawImage(bufferedImage, gxbq_Image_whdth+100, 623, 80, 80, null);
            gxbq_Image_whdth+=100;
        }

        /***
         * 弄部落信息
         */
        g.drawString(tagData.getClanTag(), 1190, 1455);
        g.drawImage(tagData.getClan(), 870, 1230, 300, 300, null);
        String clanName = tagData.getClanName();
        if("".equals(clanName)) {
            g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,60));
            g.setColor(Color.gray);
            g.drawString("无部落", 1170, 1300);
        }else{
            Integer ClanName_Szie =60;
            if(clanName.length()>13) {
                ClanName_Szie = 45;
            }
            g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,ClanName_Szie));
            g.setColor(Color.BLACK);
            g.drawString(clanName, 1170, 1300);
        }

        g.setFont(new Font("微软雅黑",Font.PLAIN,40));
        g.setColor(Color.gray);
        g.drawString(tagData.getClanClanLevel(), 1190, 1370);
        /***
         * 玩家个人数据
         */
        //第一列
        g.setFont(new Font("微软雅黑",Font.BOLD,60));
        g.setColor(Color.BLACK);
        Integer whdth = 390;
        Integer heigth =1900;
        List<String> play_one = tagData.getPlay_one();
        for (int j = 0; j < play_one.size(); j++) {//-(NameWidth(play_one.get(j))*3)
            g.drawString(play_one.get(j), whdth-(NameWidth(play_one.get(j))*12),heigth);
            whdth += 590;
        }

        //第二列
        heigth+=120;
        whdth= 370;
        g.setColor(new Color(210,105,30));
        List<String> play_two = tagData.getPlay_two();
        for (int j = 0; j < play_two.size(); j++) {
            g.drawString(play_two.get(j), whdth,heigth);
            whdth += 590;
        }

        //第三四列
        heigth+=180;
        whdth= 390;
        g.setColor(Color.BLACK);
        List<String> play_three = tagData.getPlay_three();
        for (int j = 0; j < play_three.size(); j++) {
            g.drawString(play_three.get(j), whdth-(NameWidth(play_three.get(j))*14),heigth);
            whdth += 588;
            if(j==2) {
                heigth+=155;
                whdth= 390;
            }
        }

        //第五列
        heigth+=290;
        whdth= 390;
        g.setColor(new Color(210,105,30));
        List<String> play_five = tagData.getPlay_five();
        for (int j = 0; j < play_five.size(); j++) {
            g.drawString(play_five.get(j), whdth-(NameWidth(play_five.get(j))*12),heigth);
            whdth += 590;
        }
        //英雄数据
        Color level = Color.white;//普通等级颜色
        Color level_back = new Color(0,160,233);//普通背景颜色
        Color maxlevel = Color.white;//满级等级颜色
        Color maxlevel_back =new Color(255,140,0);//满级背景颜色
        whdth= 230;
        heigth = 2980;
        Integer txt_width = whdth+35;
        Integer txt_heigth = heigth+75;
        List<PlayleTroops> heroes = tagData.getHeroes();
        for (int j = 0; j < heroes.size(); j++) {
            PlayleTroops playleTroops = heroes.get(j);
            if(playleTroops==null) {
                g.setColor(new Color(128,128,128));
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(Color.white);
                g.drawString("无", txt_width-15, txt_heigth);
            }else if(playleTroops!=null&&playleTroops.getMax()) {
                g.setColor(maxlevel_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(maxlevel);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }else {
                g.setColor(level_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(level);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }
            whdth +=252;
            txt_width +=252;
        }

        whdth= 230;
        heigth = 3410;
        txt_width = whdth+35;
        txt_heigth = heigth+75;
        List<PlayleTroops> troops_home = tagData.getTroops_home();
        for (int j = 0; j < troops_home.size(); j++) {
            PlayleTroops playleTroops = troops_home.get(j);
            if(playleTroops==null) {
                g.setColor(new Color(128,128,128));
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(Color.white);
                g.drawString("无", txt_width-15, txt_heigth);
            }else if(playleTroops!=null&&playleTroops.getMax()) {
                g.setColor(maxlevel_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(maxlevel);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }else {
                g.setColor(level_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(level);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }
            whdth +=244;
            txt_width +=244;
            if((j+1)%7==0) {
                whdth= 230;
                heigth += 285;
                txt_width = whdth+35;
                txt_heigth = heigth+75;
            }
        }


        whdth= 215;
        heigth = 4690;
        txt_width = whdth+35;
        txt_heigth = heigth+75;
        List<PlayleTroops> gcd = tagData.getGcd();
        for (int j = 0; j < gcd.size(); j++) {
            PlayleTroops playleTroops = gcd.get(j);
            if(playleTroops==null) {
                g.setColor(new Color(128,128,128));
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(Color.white);
                g.drawString("无", txt_width-15, txt_heigth);
            }else if(playleTroops!=null&&playleTroops.getMax()) {
                g.setColor(maxlevel_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(maxlevel);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }else {
                g.setColor(level_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(level);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }
            whdth +=244;
            txt_width +=244;
        }

        whdth= 215;
        heigth = 5060;
        txt_width = whdth+35;
        txt_heigth = heigth+75;
        List<PlayleTroops> troops_builderBase = tagData.getTroops_builderBase();
        for (int j = 0; j < troops_builderBase.size(); j++) {
            PlayleTroops playleTroops = troops_builderBase.get(j);
            if(playleTroops==null) {
                g.setColor(new Color(128,128,128));
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(Color.white);
                g.drawString("无", txt_width-15, txt_heigth);
            }else if(playleTroops!=null&&playleTroops.getMax()) {
                g.setColor(maxlevel_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(maxlevel);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }else {
                g.setColor(level_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(level);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }
            whdth +=244;
            txt_width +=244;
            if((j+1)%7==0) {
                whdth= 230;
                heigth += 285;
                txt_width = whdth+35;
                txt_heigth = heigth+75;
            }
        }

        whdth= 225;
        heigth = 5720;
        txt_width = whdth+35;
        txt_heigth = heigth+75;
        List<PlayleTroops> spells = tagData.getSpells();
        for (int j = 0; j < spells.size(); j++) {
            PlayleTroops playleTroops = spells.get(j);
            if(playleTroops==null) {
                g.setColor(new Color(128,128,128));
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(Color.white);
                g.drawString("无", txt_width-15, txt_heigth);
            }else if(playleTroops!=null&&playleTroops.getMax()) {
                g.setColor(maxlevel_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(maxlevel);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }else {
                g.setColor(level_back);
                g.fillArc(whdth,heigth , 100, 100, 0,360);
                g.setColor(level);
                g.drawString(playleTroops.getNumber(), playleTroopsTxt(playleTroops.getNumber(),txt_width), txt_heigth);
            }
            whdth +=245;
            txt_width +=245;
            if((j+1)%7==0) {
                whdth= 230;
                heigth += 285;
                txt_width = whdth+35;
                txt_heigth = heigth+75;
            }
        }
        //6300
        //放入二维码
        g.drawImage(tagData.getErweima(), 204, 1030, 600, 600, null);
        g.setFont(new Font("微软雅黑",Font.BOLD,50));
        g.setColor(Color.black);
        g.drawString(time,383,6645);
        //player
        String saveFilePath = tag.substring(1,tag.length());
        Thumbnails.of(I).outputFormat("jpg").scale(1f).outputQuality(0.45f).toFile(new File(ConfigParameter.filePath_ImagePlayer+"\\"+saveFilePath));
        I.flush();
        I =null;
        g.dispose();
        //Thumbnails.of(I).outputFormat("jpg").scale(1f).outputQuality(0.45f).toFile(new File("G:\\酷Q\\酷Q Air\\新建文件夹\\"+TimeUtils.getTimeS()));
        return new ClanResult(true,saveFilePath,ConfigParameter.filePath_ImagePlayer,"jpg");
    }
    private int playleTroopsTxt(String txt,Integer whdth) {
        switch (txt.length()) {
            case 1:
                return whdth - 3;
            case 2:
                return whdth - 24;
            default:
                break;
        }
        return whdth;
    }
    private static int NameWidth(String dondate) {
        int dondatelength = 0;
        try {
            dondatelength = dondate.getBytes("GB2312").length;
        } catch (UnsupportedEncodingException e) {
            dondatelength = dondate.length();
        }
        return dondatelength;
    }



    /***
     * 处理生成数据
     * @param play
     * @return
     */
    private ImagePlayersFrom TagData(JSONObject play) {
        ImagePlayersFrom form = new ImagePlayersFrom();
        Executor threadPool = BaseLocalThreadPool.getThreadPool();
        JSONArray jsonArray = play.getJSONArray("labels");
        Integer Count_Labels = 0;
        if(jsonArray!=null) {
            Count_Labels = jsonArray.size();
        }
        CountDownLatch scount = new CountDownLatch(3+Count_Labels);//专门为获取部落图片开启的线程 ，最后关闭
        JSONObject jsonObject = play.getJSONObject("clan");
        //获取部落图标
        if(jsonObject!=null) {
            String CLAN_tag = jsonObject.getString("tag");
            form.setClanName(jsonObject.getString("name"));
            form.setClanTag(CLAN_tag);
            form.setClanClanLevel(jsonObject.getString("clanLevel")+"级 | "+ CocApiAndCqCustom.CocRole(play.getString("role")));
            JSONObject CLAN_badgeUrls = play.getJSONObject("clan");
            if(CLAN_badgeUrls!=null) {
                String badgerUrlslarge = CLAN_badgeUrls.getJSONObject("badgeUrls").getString("medium");
                threadPool.execute(()->{
                    try {
                        String ImageFile = ConfigParameter.filePath_ClanImage + badgerUrlslarge.replaceAll("https://api-assets.clashofclans.com/badges/200/","");
                        BufferedImage image = CocApiAndCqCustom.getImagesClanImage(ImageFile);
                        if(image==null) {
                            BufferedImage httpsGet = HttpClientUtils.httpsGet(badgerUrlslarge, null);
                            form.setClan(httpsGet);
                            ImageIO.write(httpsGet, "png", new File(ImageFile));
                        }else {
                            form.setClan(image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("玩家图获取部落标签获取部落片失败!");
                    }finally {
                        scount.countDown();
                    }
                });
            }else{
                scount.countDown();
            }
        }else{
            scount.countDown();
        }

        //获取玩家个性标签
        List<BufferedImage> Labels_List = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            //获取标签ID
            String labels_Id = "PlayersId" + jsonArray.getJSONObject(i).getString("id");
            //图片地址
            String Labels_URL = jsonArray.getJSONObject(i).getJSONObject("iconUrls").getString("small");
            threadPool.execute(()->{
                BufferedImage labels_Image = ImageMapCache.getImage(labels_Id);
                if(labels_Image==null) {
                    try {//C:\cocutil\matter\end
                        BufferedImage httpsGet = HttpClientUtils.httpsGet(Labels_URL, null);

                        ImageIO.write(httpsGet, "png", new File(ConfigParameter.filePath_CocAll+"\\"+labels_Id+".png"));
                        ImageMapCache.setRedis("PlayersId"+labels_Id, httpsGet);
                        Labels_List.add(httpsGet);
                        System.out.println("获取成功：个性标签");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("生成失败！");
                    }finally {
                        scount.countDown();
                    }
                }else {
                    Labels_List.add(labels_Image);
                    scount.countDown();
                }
            });
        }

        form.setGxbq(Labels_List);
        /***
         * 玩家杯段
         */
        JSONObject League_Entity = play.getJSONObject("league");
        if(League_Entity!=null) {
            String League_ID = League_Entity.getString("id");
            BufferedImage bufferedImage = ImageMapCache.getImage("Plaryleague"+League_ID);
            String League_URL = play.getJSONObject("league").getJSONObject("iconUrls").getString("small");
            if(bufferedImage==null&&League_URL!=null) {
                threadPool.execute(()->{
                    try {
                        BufferedImage League_Image = HttpClientUtils.httpsGet(League_URL, null);
                        ImageIO.write(League_Image, "png", new File(ConfigParameter.filePath_CocAll+"Plaryleague"+League_ID+".png"));
                        form.setGrth(League_Image);
                        ImageMapCache.setRedis("Plaryleague"+League_ID,League_Image);
                        System.out.println("获取成功：部落标签");
                    } catch (IOException e) {
                        System.out.println("获取玩家杯段失败");
                    }finally {
                        scount.countDown();
                    }
                });
            }else {
                form.setGrth(bufferedImage);
                scount.countDown();
            }
        }else {
            scount.countDown();
        }

        /***
         * 二维码
         */
        String Play_TAG = play.getString("tag");
        if(Play_TAG!=null) {
            String PLAY_TXT =ConfigParameter.HttpUrlPlayer+Play_TAG.substring(1, Play_TAG.length());
            threadPool.execute(()->{
                try {
                    BufferedImage image = ErweimaQRCodeUtil.createImage(PLAY_TXT, ConfigParameter.file_QRcode, true);
                    form.setErweima(image);
                } catch (Exception e) {
                }finally {
                    scount.countDown();
                }
            });
        }else {
            scount.countDown();
        }
        //处理数据
        JSONArray troo = play.getJSONArray("troops");//兵种等级排序
        HashMap<String, PlayleTroops> troopsmap = new HashMap<>();
        Troops_Play(troo,troopsmap);
        JSONArray heroes = play.getJSONArray("heroes");//英雄
        Troops(heroes,troopsmap);
        JSONArray spells = play.getJSONArray("spells");//法术
        Troops(spells,troopsmap);

        //主世界兵种列表
        List<PlayleTroops> troops_home  = new ArrayList<>();
        for (int i = 0; i < CocApiAndCqCustom.troops_home.length; i++) {
            troops_home.add(troopsmap.get(CocApiAndCqCustom.troops_home[i]+"home"));
        }
        form.setTroops_home(troops_home);

        //夜世界
        List<PlayleTroops> troops_builderBase  = new ArrayList<>();
        for (int i = 0; i < CocApiAndCqCustom.troops_builderBase.length; i++) {
            troops_builderBase.add(troopsmap.get(CocApiAndCqCustom.troops_builderBase[i]+"builderBase"));
        }
        form.setTroops_builderBase(troops_builderBase);

        //三王
        List<PlayleTroops> sw  = new ArrayList<>();
        for (int i = 0; i < CocApiAndCqCustom.heroes.length; i++) {
            sw.add(troopsmap.get(CocApiAndCqCustom.heroes[i]));
        }
        form.setHeroes(sw);

        //法术
        List<PlayleTroops> fs  = new ArrayList<>();
        for (int i = 0; i < CocApiAndCqCustom.spells.length; i++) {
            fs.add(troopsmap.get(CocApiAndCqCustom.spells[i]));
        }
        form.setSpells(fs);

        //攻城车
        List<PlayleTroops> gcd  = new ArrayList<>();
        for (int i = 0; i < CocApiAndCqCustom.s.length; i++) {
            gcd.add(troopsmap.get(CocApiAndCqCustom.s[i]+"home"));
        }
        form.setGcd(gcd);

        //获取总捐兵数
        JSONArray achievements = play.getJSONArray("achievements");
        String zjbsum = "";
        for (int i = 0; i < achievements.size(); i++) {
            String name = achievements.getJSONObject(i).getString("name");
            if("Friend in Need".equals(name)) {
                zjbsum = achievements.getJSONObject(i).getString("value");
                break;
            }
        }
        //玩家个人数据 第一列数据
        List<String> play_one = new ArrayList<>();
        play_one.add(CocApiAndCqCustom.CocTownHallLevel(play.getInteger("townHallLevel"), play.getInteger("townHallWeaponLevel")));
        play_one.add("Lv."+txt(play.getString("builderHallLevel"),3));
        play_one.add(txt(play.getString("expLevel"),3));
        form.setPlay_one(play_one);
        //玩家个人数据 第二列数据
        List<String> play_two = new ArrayList<>();
        play_two.add(txt(play.getString("trophies"),3));
        play_two.add(txt(play.getString("versusTrophies"),3));
        play_two.add(txt(play.getString("warStars"),3));
        form.setPlay_two(play_two);
        //玩家个人数据 第三四列数据
        List<String> play_three = new ArrayList<>();
        play_three.add(txt(play.getString("donations"),0));
        play_three.add(txt(play.getString("donationsReceived"),0));
        play_three.add(zjbsum);
        play_three.add(txt(play.getString("attackWins"),0));
        play_three.add(txt(play.getString("defenseWins"),0));
        play_three.add(txt(play.getString("versusBattleWins"),0));
        form.setPlay_three(play_three);
        //玩家个人数据 第五列数据
        List<String> play_five = new ArrayList<>();
        play_five.add(txt(play.getString("bestTrophies"),0));
        play_five.add(txt(play.getString("bestVersusTrophies"),0));
        String legendStatistics = play.getJSONObject("legendStatistics")==null?"—":play.getJSONObject("legendStatistics").getString("legendTrophies");
        play_five.add(legendStatistics);
        form.setPlay_five(play_five);
        //主程序阻塞
        try {
            scount.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return form;
    }
    /**
     * 	处理玩家兵种
     * @param troo
     * @param troopsmap
     */
    private static void Troops_Play(JSONArray troo, HashMap<String, PlayleTroops> troopsmap) {
        for (int i = 0; i < troo.size(); i++) {
            PlayleTroops e = new PlayleTroops();
            JSONObject troops = troo.getJSONObject(i);
            String name = troops.getString("name")+ troops.getString("village");//名称
            Integer  number= troops.getInteger("level")==null?0:troops.getInteger("level");//玩家的等级
            Integer maxLevel = troops.getInteger("maxLevel")==null?0:troops.getInteger("maxLevel");//最高等级
            if(number == maxLevel) {
                e.setMax(true);
            }else {
                e.setMax(false);
            }

            e.setNumber(number.toString());
            troopsmap.put(name, e);
        }
    }
    /**
     * 	处理玩家兵种
     * @param troo
     * @param troopsmap
     */
    private static void Troops(JSONArray troo, HashMap<String, PlayleTroops> troopsmap) {
        for (int i = 0; i < troo.size(); i++) {
            PlayleTroops e = new PlayleTroops();
            JSONObject troops = troo.getJSONObject(i);
            String name = troops.getString("name");//名称
            Integer  number= troops.getInteger("level")==null?0:troops.getInteger("level");//玩家的等级
            Integer maxLevel = troops.getInteger("maxLevel")==null?0:troops.getInteger("maxLevel");//最高等级
            if(number == maxLevel) {
                e.setMax(true);
            }else {
                e.setMax(false);
            }
            e.setNumber(number.toString());
            troopsmap.put(name, e);
        }
    }
    public static String txt(String txt,Integer type) {
        switch (type) {
            case 1:
                return txt==null?"无":txt;
            case 0:
                return txt==null?"—":txt;
            case 2:
                return txt==null?"":txt;
            case 3:
                return txt==null?"0":txt;
            default:
                return txt==null?"":txt;
        }

    }

}
