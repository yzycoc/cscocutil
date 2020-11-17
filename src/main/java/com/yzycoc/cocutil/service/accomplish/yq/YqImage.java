package com.yzycoc.cocutil.service.accomplish.yq;

import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import com.yzycoc.cocutil.util.ImageMapCache;
import com.yzycoc.config.ConfigParameter;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @program: cscocutil
 * @description: 合成鱼情图
 * @author: yzy
 * @create: 2020-10-27 17:26
 * @Version 1.0
 **/
public class YqImage {
    public Boolean getImage(YuQing yQ, String uid) {
        Graphics2D g = null;
        try {
            BufferedImage Image = ImageIO.read(new File(ConfigParameter.file_Yq_Image));//buffer.get("cocheader");
            g = (Graphics2D)Image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setFont(new Font("STENCIL STD",Font.BOLD,55));
            g.setColor(Color.black);
            //世界
            g.drawString(yQ.getWordZlp(), 621, 500);
            g.drawString(yQ.getWordZx(), 347, 714);
            g.drawString(yQ.getWordLx(), 347, 828);
            g.drawString(yQ.getWordHd(), 406, 935);
            g.drawString(yQ.getWordKgj(), 406, 1042);
            //中国
            g.drawString(yQ.getCnZlp(), 2035, 458);

            g.drawString(yQ.getCnZx(), 1760, 680);
            g.drawString(yQ.getCnLx(), 1760, 787);
            g.drawString(yQ.getCnYhd(), 1830, 893);
            g.drawString(yQ.getCnKgj(), 1830, 1003);

            //百分比
            g.setFont(new Font("Myriad Pro Cond ",Font.PLAIN,40));
            String txt = "";
            Integer bfb = 35;
            txt = yQ.wgetWordZxBfb();
            g.drawString(txt, 347+(NameWidth(yQ.getWordZx())*bfb), 714);
            txt = yQ.wgetWordLxBfb();
            g.drawString(txt, 347+(NameWidth(yQ.getWordLx())*bfb), 828);
            txt = yQ.wgetWordHdBfb();
            g.drawString(txt, 406+(NameWidth(yQ.getWordHd())*bfb), 937);
            txt = yQ.wgetWordKgjBfb();
            g.drawString(txt, 406+(NameWidth(yQ.getWordKgj())*bfb), 1042);

            txt = yQ.wgetCnZxBfb();
            g.drawString(txt, 1760+(NameWidth(yQ.getCnZx())*bfb), 675);
            txt = yQ.wgetCnLxBfb();
            g.drawString(txt, 1760+(NameWidth(yQ.getCnLx())*bfb), 777);
            txt = yQ.wgetCnYhdBfb();
            g.drawString(txt, 1830+(NameWidth(yQ.getCnYhd())*bfb), 893);
            txt = yQ.wgetCnKgjBfb();
            g.drawString(txt, 1830+(NameWidth(yQ.getCnKgj())*bfb), 1003);
            //获取时间
            g.setFont(new Font("微软雅黑",Font.BOLD,65));
            g.drawString(yQ.getTime(), 2474, 1313);
            g.setFont(new Font("微软雅黑",Font.BOLD,45));
            g.drawString(yQ.getTs1(), 215, 1467);
            g.drawString(yQ.getTs2(), 215, 1645);
            g.drawString(yQ.getTs3(), 215, 1821);

            //设置变化趋势
            g.setFont(new Font("Hobo Std",Font.BOLD,55));
            //世界的
            String wordBh = yQ.getWordBh();
            quShi(g,wordBh);
            g.drawString(wordBh, 723, 605);
            BufferedImage buff = getBuff(wordBh);
            g.drawImage(buff, 650, 535, 60, 100, null);
            //中国的
            String cnBh = yQ.getCnBh();
            quShi(g,cnBh);
            g.drawString(cnBh, 2125, 571);
            buff = getBuff(cnBh);
            g.drawImage(buff, 2064, 505, 60, 100, null);

            g.setFont(new Font("Hobo Std",Font.BOLD,45));
            //全世界
            int whiht = 1054;
            int heigth = 719;
            cnBh = yQ.getWordZxBh();
            quShi(g,cnBh);
            g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);

            heigth+=110;
            cnBh = yQ.getWordLxBH();
            quShi(g,cnBh);
            //g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);

            heigth+=110;
            cnBh = yQ.getWordHdBh();
            quShi(g,cnBh);
            g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);

            heigth+=110;
            cnBh = yQ.getWordKgjBh();
            quShi(g,cnBh);
            g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);
            g.drawImage(buff, 2000, 1765, 60, 100, null);
            /**
             * 中国
             */
            whiht = 2485;
            heigth = 670;

            cnBh = yQ.getCnZxbh();
            quShi(g,cnBh);
            g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);

            heigth+=110;
            cnBh = yQ.getCnLXBh();
            quShi(g,cnBh);
            //g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);

            heigth+=110;
            cnBh = yQ.getCnYhdBh();
            quShi(g,cnBh);
            g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);

            heigth+=110;
            cnBh = yQ.getCnKgjBh();
            quShi(g,cnBh);
            g.drawString(cnBh,whiht , heigth);
            buff = getBuff(cnBh);
            g.drawImage(buff, whiht-65, heigth-80, 60, 100, null);
            //评分
            ColorZs(g,yQ.getPf());
            g.setFont(new Font("微软雅黑",Font.BOLD,60));
            g.setColor(Color.black);
            String[] dyyj = StringCd(yQ.getYj(),10,5);
            for (int i = 0; i < dyyj.length; i++) {
                if(i==0) {
                    g.drawString(dyyj[i], 2474, 1437);
                }else {
                    g.drawString(dyyj[i], 2150, 1437+(i*65));
                }
            }

            BufferedImage read = ImageIO.read(new File(ConfigParameter.file_Yq_qushi_Image));
            read = read.getSubimage(0, 0, 1200, read.getHeight());
            int width = read.getWidth();
            Integer wdith = Image.getWidth();
            Integer heInteger = read.getHeight()*(Image.getWidth() / width);
            BufferedImage cocImageAll = new BufferedImage(Image.getWidth(), Image.getHeight()+heInteger, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = (Graphics2D)cocImageAll.createGraphics();
            gs.drawImage(Image,0, 0, Image.getWidth(), Image.getHeight() , null);
            gs.drawImage(read,0, Image.getHeight(), wdith, heInteger , null);
            gs.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            gs.setFont(new Font("微软雅黑",Font.BOLD,60));
            gs.setColor(Color.black);
            gs.drawString("鱼情趋势",1263, 2080);
            gs.setFont(new Font("微软雅黑",Font.ITALIC,40));
            gs.drawString("更新时间："+yQ.getHtmltime(),1533, 2080);
            Thumbnails.of(cocImageAll).outputFormat("jpg").scale(1f).outputQuality(0.5f).toFile(
                    new File(ConfigParameter.filePath_Yq+"\\"+uid));
            Image.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            g.dispose();
        }
        return false;
    }
    /***
     * 趋势颜色变化
     * @param g
     * @param wordBh
     */
    private void quShi(Graphics2D g, String wordBh) {
        try {
            double parseDouble = Double.parseDouble(wordBh);
            if(parseDouble>=0) {
                g.setColor(new Color(0,88,30));
            }else {
                g.setColor(new Color(132,38,30));
            }
        } catch (Exception e) {
            g.setColor(Color.black);
        }
    }
    /***
     * 获取上升下降的图标
     * @param txt
     * @return
     */
    private BufferedImage getBuff(String txt) {
        try {
            double parseDouble = Double.parseDouble(txt);
            if(parseDouble>0) {
                return ImageMapCache.getImage("up");
            }else {
                return ImageMapCache.getImage("down");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    /***
     * 求文字长度
     * @param dondate
     * @return
     */
    private int NameWidth(String dondate) {
        int dondatelength = 0;
        dondatelength = dondate.length();

        return dondatelength;
    }

    /***
     * 打鱼意见
     * 分割字符长度
     * @param number
     * @return
     */
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
    /***
     * 颜色换色
     * @param g
     * @param number
     */
    private void ColorZs(Graphics2D g, String number) {
        g.setFont(new Font("STENCIL STD",Font.BOLD,280));
        double parseDouble = Double.parseDouble(number);
        if(parseDouble>=6) {
            g.setColor(new Color(0,88,30));//绿色
        }else if(parseDouble>=3&&parseDouble<6) {
            g.setColor(new Color(255,115,0));//橘黄色
        }else {
            g.setColor(new Color(132,38,30));//红色
        }
        if(number.length()==1) {
            g.drawString(number, 1700, 1739);
        }else if(number.length()==2) {
            g.drawString(number, 1610, 1739);
        }else{
            g.drawString(number, 1570, 1739);
        }
    }
}
