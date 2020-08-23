package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.*;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.cocutil.util.enums.WarLeagueEnum;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.*;
import com.yzycoc.custom.result.AjaxHttpResult;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import com.forte.qqrobot.utils.BaseLocalThreadPool;
/**
 * @program: cscocutil
 * @description: 生成部落图片
 * @author: yzy
 * @create: 2020-08-10 20:49
 * @Version 1.0
 **/
public class ImageClan {
    Logger log= LoggerFactory.getLogger(getClass());


    /***
     * 生成图片
     * @param tag
     * @param service
     * @return
     */
    public ClanResult get(String tag, CocEquilibrium service) {
        long startTime=System.currentTimeMillis();
        log.info("开始生成部落图片:"+tag);
        AjaxHttpResult ajaxHttpResult = service.get(tag, ClanApiHttp.Clan, true);
        if(!ajaxHttpResult.getSuccess()) {
            return new ClanResult(false,ajaxHttpResult.getErrorMsg());
        }
        Map<String, BufferedImage> tagData = TagData(ajaxHttpResult.getData());
        ClanResult clanResult = ImageClanHc(ajaxHttpResult.getData(), tagData);
        log.info("部落"+tag+"图片生成完毕，生成状态["+clanResult.getSuccess()+"]耗时"+(System.currentTimeMillis() - startTime)+"ms");
        return clanResult;
    }


    private ClanResult ImageClanHc(JSONObject data, Map<String, BufferedImage> tagData) {
        log.info("正在合成部落图片！" + data.getString("tag"));
        BufferedImage I = null;
        Graphics2D g = null;
        try {
            String tag = data.getString("tag");
            I = ImageIO.read(new File(ConfigParameter.file_ImageClan_clantent));//buffer.get("cocheader");
            g = (Graphics2D)I.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setFont(new Font("微软雅黑",Font.BOLD,50));
            //弄头部数据
            BufferedImage tagImage = tagData.get(tag);
            if(tag == null) {
                tagImage = ImageMapCache.getImage("badgeUrlsClan");
            }
            g.drawImage(tagImage, 40, 70, 180, 180, null);//部落图标
            g.drawString(data.getString("name"), 240, 100);


            StringBuffer ClanData = new StringBuffer();
            String locations = data.getJSONObject("location")==null?"":data.getJSONObject("location").getString("name");
            //第二行 部落基本数据
            ClanData.append(CocApiAndCqCustom.location(locations)+" | ");
            ClanData.append(data.getString("clanLevel")+"级 | ");
            ClanData.append(data.getString("members")+"人 | ");
            JSONObject warLeague = data.getJSONObject("warLeague");
            if(warLeague!=null) {
                ClanData.append(WarLeagueEnum.getName(warLeague.getString("id"))+" | ");
            }
            ClanData.append(CocApiAndCqCustom.CocWarFrequency(data.getString("warFrequency"))+" | ");
            ClanData.append(CocApiAndCqCustom.CocTpe(data.getString("type")));
            g.setFont(new Font("微软雅黑",Font.PLAIN,30));
            g.drawString(ClanData.toString(), 240, 152);//塞入第二行数据
            g.setFont(new Font("微软雅黑",Font.BOLD,23));
            g.drawString(tag, 240, 210);//部落标签


            g.setFont(new Font("Segoe UI Black",Font.BOLD,40));
            g.drawString(data.getString("clanPoints"), 470, 215);
            g.drawString(data.getString("clanVersusPoints"), 716, 215);

            JSONArray lables = data.getJSONArray("labels");
            for (int j = 0; j < lables.size(); j++) {
                JSONObject lablesID = lables.getJSONObject(j);
                String lablesIDs = lablesID.getString("id");
                BufferedImage bufferedImage = tagData.get(lablesIDs);
                if(bufferedImage!=null) {
                    g.drawImage(bufferedImage, 880+(j * 90), 160, 80,80, null);
                }
            }

            String description =data.getString("description").replaceAll("   ", "");
            g.setFont(new Font("微软雅黑",Font.PLAIN,23));
            g.setColor(Color.black);
            String[] stringCd = StringCd(description,38,38);
            for (int j = 0; j < stringCd.length; j++) {
                g.drawString(stringCd[j], 294, 281+(j*30));
            }

            Integer x = 20;
            Integer y = 419;
            BufferedImage Image = ImageIO.read(new File(ConfigParameter.file_ImageClan_clantitle));//buffer.get("cocheader");
            JSONArray memberList = data.getJSONArray("memberList");
            for (int j = 0; j < memberList.size(); j++) {
                BufferedImage read = Image;//ImageIO.read(new File(ConfigParameter.all + "clantitle.png"));//buffer.get("cocheader");
                g.setFont(new Font("微软雅黑",Font.BOLD,23));
                //夜世界 少一次更换
                //tagTxt.append(me.getString("versusTrophies"));
                JSONObject me = memberList.getJSONObject(j);
                g.setColor(Color.black);
                int types = j%2;
                if(types == 0) {
                    x = 20;
                    y = y + 85;
                }else {
                    x = 600;
                }
                g.drawImage(read, x, y, 550,80, null);
                JSONObject league = me.getJSONObject("league");
                if(league!=null) {
                    BufferedImage leagueImage = tagData.get(league.getString("id"));
                    if(leagueImage!=null) {
                        g.drawImage(leagueImage, x+15, y+10, 40,40, null);
                    }else {
                        g.drawImage(ImageMapCache.getImage("Plaryleague29000000"), x+15, y+10, 40,40, null);
                    }
                }
                g.drawString(me.getString("trophies"), x+15, y+74);


                String rankTxt = "";
                Integer previousClanRank = me.getInteger("previousClanRank");//
                Integer clanRank = me.getInteger("clanRank");
                String clanRankTxt = String.valueOf(clanRank);
                if(previousClanRank>clanRank) {//上升 绿色
                    g.setColor(new Color(24,137,71));
                    rankTxt = "▲";
                }else if(previousClanRank<clanRank){//下降红色
                    g.setColor(new Color(148,9,6));
                    rankTxt = "▼";
                }else {//不变灰色
                    g.setColor(new Color(168,168,168));
                    rankTxt = "●";
                }
                g.drawString(clanRankTxt, x+75-(clanRankTxt.length()*10), y+28);
                g.drawString(rankTxt, x+60, y+48);
                g.setFont(new Font("微软雅黑",Font.PLAIN,18));
                StringBuffer tagTxt = new StringBuffer();
                tagTxt.append(me.getString("tag")+" | ");
                tagTxt.append(me.getString("expLevel")+" | ");
                String cocRole = CocApiAndCqCustom.CocRole(me.getString("role"));
                tagTxt.append(cocRole+" | ");
                Integer donations = me.getInteger("donations");
                Integer donationsReceived = me .getInteger("donationsReceived");
                if(donations>donationsReceived) {
                    g.setColor(new Color(24,137,71));
                }else if(donations<donationsReceived) {
                    g.setColor(new Color(235,86,53));
                }else {
                    g.setColor(Color.BLACK);
                }
                tagTxt.append(donations+":"+donationsReceived);
                g.drawString(tagTxt.toString(), x+120, y+68);
                String PlayName = me.getString("name");
                if("首领".equals(cocRole)) {
                    g.setColor(new Color(250, 137, 95));
                }else if("副首领".equals(cocRole)) {
                    g.setColor(new Color(0,104,182));
                }else if("长老".equals(cocRole)) {
                    g.setColor(new Color(70,53,87));
                }else {
                    g.setColor(Color.BLACK);
                }
                if(PlayName.length()<=11) {
                    g.setFont(new Font("微软雅黑",Font.BOLD,28));
                    g.drawString(PlayName, x+120, y+36);
                }else {
                    g.setFont(new Font("微软雅黑",Font.BOLD,20));
                    g.drawString(PlayName, x+120, y+39);
                }
                g.setFont(new Font("微软雅黑",Font.BOLD,23));
                g.setColor(new Color(95,95,95));
                g.drawString("夜:"+me.getString("versusTrophies"), x+440, y+34);
            }
            g.drawImage(tagData.get("ewm"+tag), 60, 245,200,200, null);
            g.setFont(new Font("微软雅黑",Font.BOLD,33));
			/*g.drawString("扫码进入仓鼠网站查看详情", 450, y+170);
			g.drawString("信息将会更加详细 也可进入游戏查看！", 450, y+280);*/
            g.drawString("生成时间："+ TimeUtiles.getStringDate("MM月dd日 HH时mm分"), 40, y+170);
            g.drawString("@仓鼠机器人", 40, y+120);
            g.drawString("部落 v2.3", 1000, y+170);
            I =I.getSubimage(0, 0, I.getWidth(), y + 200);
            //Thumbnails.of(I).outputFormat("jpg").scale(1f).outputQuality(1f).toFile(new File("G:\\酷Q\\酷Q Air\\新建文件夹\\"+TimeUtils.getTimeS()));
            String fileResultName = tag.substring(1,tag.length());
            Thumbnails.of(I).outputFormat("jpg").scale(1f).outputQuality(0.75f).toFile(new File(ConfigParameter.filePath_ImageClan + "\\"+fileResultName));
            return new ClanResult(true,fileResultName,ConfigParameter.filePath_ImageClan,"jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            I.flush();
            I =null;
            g.dispose();
        }
        return new ClanResult(false,"【"+data.getString("tag")+"】部落图片合成失败，请稍后重试！");
    }

    /***
     * 数据处理区
     * @param data
     * @return
     */
    private Map<String, BufferedImage> TagData(JSONObject data) {
        String tag = data.getString("tag");
        JSONArray labels = data.getJSONArray("labels");
        JSONArray memberList = data.getJSONArray("memberList");
        Integer countDownSize  = 2 + labels.size() + memberList.size();
        Map<String,BufferedImage> map = new HashMap<>();
        Executor threadPool = BaseLocalThreadPool.getThreadPool();
        CountDownLatch count = new CountDownLatch(countDownSize);
        JSONObject badgeUrls = data.getJSONObject("badgeUrls");
        if(badgeUrls!=null) {
            String badgerUrlslarge = badgeUrls.getString("medium");
            if(badgerUrlslarge!=null) {
                threadPool.execute(()->{
                    try {
                        String ImageFile = ConfigParameter.filePath_ClanImage+badgerUrlslarge.replaceAll("https://api-assets.clashofclans.com/badges/200/","");
                        BufferedImage image = CocApiAndCqCustom.getImagesClanImage(ImageFile);
                        if(image==null) {
                            log.info("获取部落图标"+badgerUrlslarge);
                            BufferedImage httpsGet = HttpClientUtils.httpsGet(badgerUrlslarge, null);
                            map.put(tag,httpsGet);
                            ImageIO.write(httpsGet, "png", new File(ImageFile));
                        }else {
                            map.put(tag,image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("无法获取到该部落的部落图标"+tag);
                    }finally {
                        count.countDown();
                    }
                });
            }else {
                count.countDown();
            }
        }else {
            count.countDown();
        }
        for (int i = 0; i < labels.size(); i++) {
            JSONObject ClanID = labels.getJSONObject(i);
            Executor threadPool3 = BaseLocalThreadPool.getThreadPool();
            threadPool3.execute(()->{
                String getIDS = ClanID.getString("id");
                String ImageFile =ConfigParameter.filePath_CocAll + "clanid"+getIDS+".png";
                BufferedImage image = CocApiAndCqCustom.getImagesClanImage(ImageFile);
                if(image==null) {//获取这个ID图片为空则去获取网络图片
                    log.info("获取部落个性标签"+getIDS);
                    BufferedImage bufferedImage = HttpClientUtils.httpsGet(ClanID.getJSONObject("iconUrls").getString("small"), null);
                    try {
                        ImageIO.write(bufferedImage, "png", new File(ConfigParameter.filePath_CocAll + "clanid"+getIDS+".png"));
                        map.put(getIDS, bufferedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("获取图片失败");
                    }finally {
                        count.countDown();
                    }
                }else {
                    map.put(getIDS, image);
                    count.countDown();
                }
            });
        }

        for (int i = 0; i < memberList.size(); i++) {
            JSONObject play = memberList.getJSONObject(i);
            JSONObject league = play.getJSONObject("league");
            if(league!=null) {
                try {
                    String PlayId = league.getString("id");
                    BufferedImage buff = map.get(PlayId);
                    if(buff == null) {
                        String ImageFile =ConfigParameter.filePath_CocAll +  "Plaryleague"+PlayId+".png";
                        BufferedImage image = CocApiAndCqCustom.getImagesClanImage(ImageFile);
                        if(image==null) {//获取这个ID图片为空则去获取网络图片
                            log.info("获取玩家图标");
                            String iconUrls = league.getJSONObject("iconUrls").getString("tiny");
                            BufferedImage httpsGet = HttpClientUtils.httpsGet(iconUrls, null);
                            ImageIO.write(httpsGet, "png", new File(ConfigParameter.filePath_CocAll + "Plaryleague"+PlayId+".png"));
                            map.put(PlayId, httpsGet);
                        }else {
                            map.put(PlayId, image);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("玩家图片获取图片失败");
                } finally {
                    count.countDown();
                }
            }else {
                count.countDown();
            }
        }
        String PLAY_TXT = ConfigParameter.HttpUrl+"qq/cocApi/clancoc?tag="+tag.substring(1, tag.length());
        threadPool.execute(()->{
            try {
                BufferedImage image = ErweimaQRCodeUtil.createImage(PLAY_TXT, ConfigParameter.file_QRcode, true);
                map.put("ewm"+tag,image);
            }finally {
                count.countDown();
            }
        });
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }

    /***
     * 分割字符长度
     * @param number
     * @return
     */
    private String[] StringCd(String number,Integer sum,Integer start) {
        if(number==null) {
            String[] rems = {""};
            return rems;
        }
        Integer sums = number.length()-start;
        Integer size = 1;
        if(sums<=0) {
            size = 1;
        }else {
            size =  sums%sum == 0?sums/sum + 1:sums/sum+2;
        }
        String[] remsg =  new String[size];
        Integer min = 0,max = start;
        for (int i = 0; i < remsg.length; i++) {
            if(i==0) {
                if(number.length()>start) {
                    remsg[i] = number.substring(min, max);
                }else {
                    remsg[i] = number.substring(min, number.length());
                }
                min = start;
                max+=sum;
            }else {
                if(max>number.length()) {
                    remsg[i] = number.substring(min, number.length());
                }else {
                    remsg[i] = number.substring(min, max);
                }
                min =  max;
                max+=sum;
            }

        }
        return remsg;
    }
}
