package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.accomplish.common.ClanAllCollect;
import com.yzycoc.cocutil.service.accomplish.common.ClanListAll;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.service.result.clanAll.ClanAllListHttp;
import com.yzycoc.cocutil.service.result.clanAllCollect.DataProcessing;
import com.yzycoc.cocutil.util.*;
import com.yzycoc.cocutil.util.enums.WarLeagueEnum;
import com.yzycoc.config.ConfigParameter;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @program: cscocutil
 * @description: 部落配置 图片版
 * @author: yzy
 * @create: 2020-08-22 18:39
 * @Version 1.0
 **/
public class ImageClanAllCollectImage {


    public ClanResult get(String tag, CocEquilibrium cocEquil) {
        long startTime=System.currentTimeMillis();
        System.out.println("开始部落配置统计[图片]:"+tag);
        ClanAllListHttp clanHttp = ClanListAll.ClanHttp(tag,cocEquil);
        if(!clanHttp.getSuccess()) {
            return new ClanResult(false, clanHttp.getResult());
        }
        DataProcessing data = ClanAllCollect.dataProcessing(clanHttp.getClan(),clanHttp.getMemberPlayer());
        if(data == null){
            return new ClanResult(false, "数据合成失败，请稍后重试！");
        }
        ClanResult resutle = ImageCreate(data,clanHttp);
        System.out.println("部落配置统计[图片]完毕，耗时： " + (System.currentTimeMillis() - startTime) + "ms");
        return resutle;
    }

    private ClanResult ImageCreate(DataProcessing data, ClanAllListHttp clanHttp) {
        Graphics2D g = null;
        Graphics2D cocg = null;
        Graphics2D cg = null;
        Graphics2D cocImageAllG = null;
        try {
            /** 3.图片生成区   **/
            //获取头部图片
            BufferedImage headerImage = ImageIO.read(new File(ConfigParameter.filePath_CocAll + "cocheader.png"));//buffer.get("cocheader");
            g = (Graphics2D)headerImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            JSONObject clans = clanHttp.getClan();

            //部落名称
            Font font = new Font("微软雅黑",Font.BOLD,36);
            g.setFont(font);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
            g.drawString(clans.getString("name"), 180, 140);//左边 宽度 右边高度
            g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,30));
            //主世界总奖杯
            g.setColor(Color.yellow);
            g.drawImage(ImageMapCache.getImage("JBZclan"), 172, 155, 35, 40, null);
            g.drawString(clans.getString("clanPoints"), 210, 184);
            //夜世界总奖杯
            g.drawImage(ImageMapCache.getImage("JBYclan"), 360, 153, 35, 40, null);
            g.drawString(clans.getString("clanVersusPoints"), 400, 181);

            //国家 等级 人数 进步罗情况
            StringBuffer ClanAllNumber = new StringBuffer();
            String locations = clans.getJSONObject("location")==null?"":clans.getJSONObject("location").getString("name");

            ClanAllNumber.append(CocApiAndCqCustom.location(locations)+" | ");
            ClanAllNumber.append(clans.getString("clanLevel")+"级 | ");
            ClanAllNumber.append(clans.getString("members")+"人 | ");
            JSONObject warLeague = clans.getJSONObject("warLeague");
            if(warLeague!=null) {
                ClanAllNumber.append(WarLeagueEnum.getName(warLeague.getString("id"))+"|");
            }
            ClanAllNumber.append(CocApiAndCqCustom.CocTpe(clans.getString("type")));

            g.setFont(new Font("微软雅黑",Font.TYPE1_FONT,22));
            g.setColor(Color.white);
            g.drawString(ClanAllNumber.toString(), 180, 220);

            //塞入部落标签
            g.setFont(new Font("微软雅黑",Font.ITALIC,24));
            g.setColor(Color.blue);
            g.drawString(clans.getString("tag"), 208, 265);

            //塞入部落图标
            g.drawImage(clanHttp.getClanImage(), 55, 125, 120, 120, null);

            //放部落竞赛图片
            JSONArray jsonArray = clans.getJSONArray("labels");
            for (int i = 0; i < jsonArray.size(); i++) {
                String labelsId ="clanid" + jsonArray.getJSONObject(i).getString("id");
                g.drawImage(ImageMapCache.getImage(labelsId), 50+(i*60), 295, 50, 50, null);
            }

            //处理部落配置配置
            BufferedImage cocdiandian = ImageMapCache.getImage("cocdiandian");
            int h_heigth = 480;
            int h_width = 10;
            int h_i = 0;
            String[][] townHallLevel = data.getTownHallLevel();
            g.drawImage(cocdiandian, 80, h_heigth, 20, 30, null);
            g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,30));
            g.setColor(Color.white);
            g.drawString("主世界配置", 115, h_heigth+25);
            h_heigth+=35;
            for (String[] strings : townHallLevel) {
                    String User_number = strings[1];
                if(!"无".equals(User_number)) {
                    BufferedImage coc = ImageIO.read(new File(ConfigParameter.filePath_CocAll + "moban\\"+strings[0]+".png"));
                    cocg = (Graphics2D)coc.createGraphics();
                    cocg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    if(User_number.length()>=2) {
                        cocg.setFont(new Font("微软雅黑",Font.BOLD,20));
                    }else {
                        cocg.setFont(new Font("微软雅黑",Font.BOLD,26));
                    }
                    cocg.setColor(Color.yellow);
                    cocg.drawString(User_number+" 个", 153, 62);
                    g.drawImage(coc, h_width, h_heigth, 228, 187, null);
                    if(h_i==3) {
                        h_heigth+=80;
                        h_width = 10;
                        h_i = -1;
                    }else {
                        h_width+=170;
                    }
                    h_i++;
                    coc.flush();
                }
            }
            if(h_width!=10) {
                h_heigth+=120;
                h_width = 10;
                h_i = 0;
            }else {
                h_heigth+=40;
            }
            g.drawImage(cocdiandian, 80, h_heigth, 20, 30, null);
            g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,30));
            g.setColor(Color.white);
            g.drawString("夜世界配置", 115, h_heigth+25);
            h_heigth+=35;
            String[][] builderHallLevel = data.getBuilderHallLevel();
            for (String[] strings : builderHallLevel) {
                String User_number = strings[1];
                if(!"无".equals(User_number)) {
                    BufferedImage coc = ImageIO.read(new File(ConfigParameter.filePath_CocAll + "moban\\y"+strings[0]+".png"));
                    cocg = (Graphics2D)coc.createGraphics();
                    cocg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    if(User_number.length()>=2) {
                        cocg.setFont(new Font("微软雅黑",Font.BOLD,20));
                    }else {
                        cocg.setFont(new Font("微软雅黑",Font.BOLD,26));
                    }
                    cocg.setColor(Color.yellow);
                    cocg.drawString(User_number+" 个", 153, 62);
                    g.drawImage(coc, h_width, h_heigth, 228, 187, null);
                    if(h_i==3) {
                        h_heigth+=80;
                        h_width = 10;
                        h_i = -1;
                    }else {
                        h_width+=170;
                    }
                    h_i++;
                    coc.flush();
                }
            }
            if(h_width!=10) {
                h_heigth+=120;
                h_width = 10;
                h_i = 0;
            }else {
                h_heigth+=40;
            }
            g.drawImage(cocdiandian, 80, h_heigth, 20, 30, null);
            g.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,30));
            g.setColor(Color.white);
            g.drawString("段位配置", 115, h_heigth+25);
            h_heigth+=35;
            String[][] trophies = data.getTrophies();
            for (String[] strings : trophies) {
                String User_number = strings[1];
                if(!"无".equals(User_number)) {
                    BufferedImage coc = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"moban\\"+strings[0]+".png"));
                    cocg = (Graphics2D)coc.createGraphics();
                    cocg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                    if(User_number.length()>=2) {
                        cocg.setFont(new Font("微软雅黑",Font.BOLD,20));
                    }else {
                        cocg.setFont(new Font("微软雅黑",Font.BOLD,26));
                    }
                    cocg.setColor(Color.yellow);
                    cocg.drawString(User_number+" 个", 153, 62);
                    g.drawImage(coc, h_width, h_heigth, 228, 187, null);
                    if(h_i==3) {
                        h_heigth+=80;
                        h_width = 10;
                        h_i = -1;
                    }else {
                        h_width+=170;
                    }
                    h_i++;
                    coc.flush();
                }
            }
            if(h_width==10) {
                h_heigth-=80;
            }


            /** 处理底部图片 **/
            BufferedImage coc_content = ImageIO.read(new File(ConfigParameter.filePath_CocAll + "coccontent.png"));//buffer.get("coccontent");
            cg = (Graphics2D)coc_content.createGraphics();
            cg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            cg.setColor(Color.yellow);
            //捐兵最高的三个
            String[][] donations = data.getDonations();
            int donationslength = 125;
            for (int i = donations.length; i > 0; i--) {
                String[] dondate = donations[i-1];//获取玩家信息
                if(NameWidth(dondate[0])>11) {
                    cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,28-(NameWidth(dondate[0])/2)));
                }else {
                    cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,23));
                }
                cg.drawString(dondate[0], 130, donationslength);
                cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,23));
                cg.drawString(dondate[1], 425 - (NameWidth(dondate[1])*8), donationslength);
                cg.drawString(dondate[2], 638 - (NameWidth(dondate[2])*8), donationslength);
                donationslength+=40;

            }
            //统计等级最高的一个
            String[] maxexpLevel = data.getMaxexpLevel();
            String maxlevel = maxexpLevel[0];

            int nameexpLevellength = NameWidth(maxlevel);
            if(nameexpLevellength>23) {
                cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,17));
                cg.drawString(maxlevel, 185- (NameWidth(maxlevel)*4), 360);
            }else {
                cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,23));
                cg.drawString(maxlevel, 194- (NameWidth(maxlevel)*6), 360);
            }
            cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,23));
            cg.drawString(maxexpLevel[1], 425 - (NameWidth(maxexpLevel[1])*8), 360);
            cg.drawString(maxexpLevel[2], 638 - (NameWidth(maxexpLevel[2])*8), 360);
            //最高奖杯
            String[][] maxTrophies = data.getMaxTrophies();
            int maxTrophieslength = 505;
            for (int i = 0; i < maxTrophies.length; i++) {
                String[] max = maxTrophies[i];
                String namelength = max[0];
                int nameWidth = NameWidth(namelength);
                if(nameWidth>=22) {
                    cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,14));
                    cg.drawString(namelength,  205- (NameWidth(namelength)*3), maxTrophieslength-6);
                }else if(nameWidth<22&&nameWidth>13) {
                    cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,17));
                    cg.drawString(namelength,  205- (NameWidth(namelength)*4), maxTrophieslength-6);
                }else {
                    cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,23));
                    cg.drawString(namelength,  195- (NameWidth(namelength)*6), maxTrophieslength);
                }
                cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,23));
                cg.drawString(max[1], 425 - (NameWidth(max[1])*8), maxTrophieslength);
                cg.drawString(max[2], 638 - (NameWidth(max[2])*8), maxTrophieslength);
                maxTrophieslength+=40;
            }
            cg.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,26));
            //其他文字
            cg.drawString(data.getExpSum(), 240, 605);
            cg.drawString(data.getTownHalSum(), 620, 605);
            cg.drawString(data.getDonationssum().toString(), 240, 675);
            cg.drawString(data.getAttackWinsSum(), 620, 675);
            String loLeader = data.getCoLeader().toString()+" 个";
            String loAdmin = data.getAdmin().toString()+" 个";
            cg.drawString(loLeader,385, 744);
            cg.drawString(loAdmin, 600 , 744);
            cg.drawString(data.getTroSum(), 380 , 817);
            cg.drawString(data.getVersusTrophiesSum(), 615 , 817);
            /** 头部和内容进行合并 */
            headerImage = headerImage.getSubimage(0, 0, headerImage.getWidth(), h_heigth + 100);
            BufferedImage cocImageAll = new BufferedImage(headerImage.getWidth(), headerImage.getHeight()+coc_content.getHeight(), BufferedImage.TYPE_INT_RGB);
            cocImageAllG = (Graphics2D)cocImageAll.createGraphics();
            cocImageAllG.drawImage(headerImage,0, 0, headerImage.getWidth()-1, headerImage.getHeight() , null);
            cocImageAllG.drawImage(coc_content,0, headerImage.getHeight(), headerImage.getWidth(), coc_content.getHeight(), null);
            //ImageIO.write(cocImageAll, "png", new File("G:\\酷Q\\酷Q Air\\新建文件夹\\"+TimeUtils.getTimeS()+".png"));
            String saveFilePath =clanHttp.getClan().getString("tag").substring(1,clanHttp.getClan().getString("tag").length());
            Thumbnails.of(cocImageAll).outputFormat("png").scale(1f).outputQuality(1f).toFile(new File(ConfigParameter.filePath_ClanCollectImage+"\\"+saveFilePath));
            cocImageAll.flush();
            headerImage.flush();
            coc_content.flush();
            return new ClanResult(true,saveFilePath,ConfigParameter.filePath_ClanCollectImage,"png");
        } catch (Exception e) {
            return new ClanResult(false,"图片生成异常，请稍后重试！");
        }finally {
            if(cocImageAllG!=null) {
                cocImageAllG.dispose();
            }
            if(cg!=null) {
                cg.dispose();
            }
            if(g!=null) {
                g.dispose();
            }
            if(cocg!=null) {
                cocg.dispose();
            }
        }
    }
    //处理文字
    private static int NameWidth(String dondate) {
        int dondatelength = 0;
        try {
            dondatelength = dondate.getBytes("GB2312").length;
        } catch (UnsupportedEncodingException e) {
            dondatelength = dondate.length();
        }
        return dondatelength;
    }
}
