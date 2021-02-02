package com.yzycoc.cocutil.service.accomplish.image;

import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.service.result.clanAll.*;
import com.yzycoc.cocutil.util.ImageMapCache;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @program: cscocutil
 * @description: 合成图片
 * @author: yzy
 * @create: 2020-11-22 12:59
 * @Version 1.0
 **/
public class ImageClanAllImage {
    public ClanResult ImageAll(String tag, ClanAllListHttp clanHttp, ClanAllListClan clanData)   {
        try {
            BufferedImage I = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"clanAll.jpg"));
            Graphics2D g = (Graphics2D)I.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            //图片
            g.drawImage(clanHttp.getClanImage(), 50, 130, 460,460,null);
            List<String> labels = clanData.getLabels();
            Integer width = 535;
            for (String string : labels) {
                g.drawImage(ImageMapCache.getImage(string), width, 188, 130,130,null);
                width +=140;
            }
            Integer[][] townHallLevel = clanData.getTownHallLevel();
            width = 1100;
            Integer height = 257;
            g.setFont(new Font("STENCIL STD",Font.BOLD,55));
            g.setColor(Color.black);
            for (int j = 0; j < townHallLevel.length; j++) {
                Integer[] integers = townHallLevel[j];
                for (int k = 0; k < integers.length; k++) {
                    String sum =integers[k] == null?null:String.valueOf(integers[k]);
                    if(!StringUtils.isEmpty(sum)&&!"null".equals(sum)&&!"0".equals(sum)) {
                        g.drawString(sum, width + (sum.length()==1?22:0), height);
                    }
                    if(k == (integers.length - 2)) {
                        width += 140;
                    }else {
                        width += 127;
                    }
                }
                width = 1100;
                if(j == (townHallLevel.length - 2)) {
                    height += 103;
                }else {
                    height += 85;
                }
            }
            g.setFont(new Font("STENCIL STD",Font.BOLD,85));
            g.setColor(Color.white);
            g.drawString(clanData.getClanPoints(), 189, 897);
            g.drawString(clanData.getClanVersusPoints(), 613, 897);
            g.setFont(new Font("微软雅黑",Font.BOLD,95));
            g.drawString(clanData.getNumber(), 657, 470);

            g.setFont(new Font("微软雅黑",Font.BOLD,55));
            g.drawString(clanData.getClanName(), 105, 635);
            g.drawString(clanData.getClanTag(), 105, 742);
            width = I.getWidth();
            height = I.getHeight() + (clanData.getPlayer().size() * 130) -100 ;
            BufferedImage cocImageAll = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = cocImageAll.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setBackground(new Color(230,230,230));
            g.clearRect(0, 0, width, cocImageAll.getHeight());
            g.drawImage(I, 0, 0,width,I.getHeight(),null);

            BufferedImage allList = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"allList.png"));
            height =I.getHeight() - 465;
            for (int j = 0; j < clanData.getPlayer().size(); j++) {
                new ImageClanAllImage().hc(g,clanData,j,allList,height,width);
                height+= 130;
            }
            g.setFont(new Font("微软雅黑",Font.BOLD,80));
            g.setColor(Color.gray);
            g.drawString("生成时间："+ TimeUtiles.getStringDate("MM月dd日 HH时mm分"), 40, height+130);
            g.drawString("@仓鼠机器人", 40, height+310);
            g.drawString("部落配置 v1.0", 1700, height+310);
            String saveFilePath =clanData.getClanTag().substring(1,clanData.getClanTag().length());
            ImageIO.write(cocImageAll, "jpg", new File(ConfigParameter.filePath_ClanAll+"\\"+saveFilePath+".jpg"));
            //Thumbnails.of(cocImageAll).outputFormat("jpg").scale(1f).outputQuality(1f).toFile(new File(ConfigParameter.filePath_ClanAll+"\\"+saveFilePath));
            //Thumbnails.of(cocImageAll).outputFormat("jpg").scale(1f).outputQuality(0.45f).toFile(new File("G:\\酷Q\\酷Q Air\\新建文件夹\\"+TimeUtils.getStringDate("MM月ddHHmmss")));
            return new ClanResult(true,saveFilePath,ConfigParameter.filePath_ClanAll,"jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ClanResult(false,"图片生成失败。请稍后重试！");
    }

    private void hc(Graphics2D g, ClanAllListClan clanData, int j, BufferedImage allList, Integer height, Integer width) {
        ClanAllListPlayer player = clanData.getPlayer().get(j);

        g.drawImage(allList, 0, height,width,220,null);
        g.setColor(Color.black);
        g.setFont(new Font("微软雅黑",Font.BOLD,35));
        g.drawString(player.getName(), 120, height+110);
        g.setColor(Color.gray);
        g.drawString(player.getTag(), 278, height+150);
        g.drawString(player.getTrophy(), 150, height+150);

        if(player.getName().length()>12) {
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
        }else {
            g.setFont(new Font("微软雅黑",Font.BOLD,35));
        }
        g.setColor(Color.black);
        BufferedImage image3 = ImageMapCache.getImage(player.getLeagueId());
        if(image3!=null) {
            g.drawImage(image3, 8, height+65, 100, 100, null);
        }
        BufferedImage image = ImageMapCache.getImage("Lv"+player.getTownHallLevel());
        if(image!=null) {
            g.drawImage(image, 582, height+73, 80, 80, null);

        }
        BufferedImage image2 = ImageMapCache.getImage("BH"+player.getBuilderHallLevel());
        if(image2!=null) {
            g.drawImage(image2, 832, height+73, 80, 80, null);
        }

        g.setFont(new Font("STENCIL STD",Font.BOLD,38));
        g.setColor(Color.white);
        g.drawString(player.getTownHallLevel(), 670, height+130);
        g.drawString("Lv."+player.getBuilderHallLevel(), 930, height+130);
        g.drawString(player.getNumber()+"%", 1193, height+130);

        //男王
        if(player.getBarbarianKing()!=null) {
            if(player.getBarbarianKings()) {
                g.setColor(new Color(255,215,0));
            }else {
                g.setColor(Color.white);
            }
            g.drawString(player.getBarbarianKing(), 1449, height+130);
        }else {
            g.setColor(Color.white);
            g.fillRect(1326, height+65, 205, 100);
        }
        //女王
        if(player.getArcherQueen()!=null) {
            if(player.getArcherQueens()) {
                g.setColor(new Color(255,215,0));
            }else {
                g.setColor(Color.white);
            }
            g.drawString(player.getArcherQueen(), 1645, height+130);
        }else {
            g.setColor(Color.white);
            g.fillRect(1528, height+65, 205, 100);
        }
        //咏王
        if(player.getGrandWarden()!=null) {
            if(player.getGrandWardens()) {
                g.setColor(new Color(255,215,0));
            }else {
                g.setColor(Color.white);
            }
            g.drawString(player.getGrandWarden(), 1836, height+130);
        }else {
            g.setColor(Color.white);
            g.fillRect(1719, height+65, 205, 100);
        }
        //闰土
        if(player.getRoyalChampion()!=null) {
            if(player.getRoyalChampions()) {
                g.setColor(new Color(255,215,0));
            }else {
                g.setColor(Color.white);
            }
            g.drawString(player.getRoyalChampion(), 2040, height+130);
        }else {
            g.fillRect(1915, height+65, 205, 100);
        }
        List<String> labels2 = player.getLabels();
        Integer widthLabels = 2130;
        for (String string : labels2) {
            g.drawImage(ImageMapCache.getImage(string), widthLabels, height+70, 80,80,null);
            widthLabels+=90;
        }
    }
}
