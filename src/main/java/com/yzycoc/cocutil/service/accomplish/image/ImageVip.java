package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import com.yzycoc.cocutil.SQLAll.bean.vip.*;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.*;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cscocutil
 * @description: 会员合成图片
 * @author: yzy
 * @create: 2021-01-23 10:21
 * @Version 1.0
 **/
public class ImageVip {

    /***
     * 合成收款二维码
     * @return
     * @param httpUrl
     * @param uid
     */
    public BufferedImage compound(String httpUrl, VipApplyForLog uid) {
        try {
            BufferedImage I = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"money.png"));
            Graphics2D g = (Graphics2D)I.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.drawImage(ErweimaQRCodeUtil.createImage(httpUrl, ConfigParameter.file_QRcode, true), 141, 240, 249,249,null);
            Map<String, String> map = new HashMap<>();
            map.put("b","qq");
            map.put("nk",uid.getUserNumber());
            map.put("s","640");
            g.drawImage(HttpClientUtils.httpGetNull("http://q1.qlogo.cn/g",map), 54, 56, 130,130,null);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.setColor(Color.white);
            g.drawString("处理BOT："+uid.getRobotNumber(),195,80);
            g.drawString("赞助QQ："+uid.getUserNumber(),195,120);
            String numberVal = uid.getNumber() == -1?"永久":(uid.getNumber()+"月");
            g.drawString("赞助期限："+numberVal,195,160);
            g.setFont(new Font("Monospaced",Font.BOLD,10));
            g.drawString(uid.getCreateDate(),322,179);
            Font stencil_std = new Font("STENCIL STD", Font.BOLD, 30);
            g.setFont(stencil_std);
            BigDecimal divide = new BigDecimal(uid.getCreateName()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
            Integer X = content(divide.toString(), 600, stencil_std, g);
            g.drawString(divide.toString(),X,578);
            return I;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        VipLog vipLog = new VipLog();
        vipLog.setQqcode("1260422870");
        vipLog.setMoneyNumber("1260422870");
        vipLog.setScore("60");
        vipLog.setMoney("300");
        Score score = new Score();
        CsUserVip csUserVip =new CsUserVip();
        //String qqName = getQqName(vipLog.getQqcode());
        //System.out.println(qqName);
        csUserVip.setEternity(false);
        csUserVip.setVipMember(31);
        score.setNumber(1000);
        csUserVip.setEternity(false);
        csUserVip.setEndTime(TimeUtiles.getStringTime());
        BufferedImage compound = new ImageVip().resultOkImage(vipLog,score,csUserVip);
        try {
            Thumbnails.of(compound).outputFormat("png").scale(1f).outputQuality(1f).toFile(new File("G:\\网络游戏\\酷Q\\酷Q Air\\新建文件夹\\1\\1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public BufferedImage resultOkImage(VipLog vipLog, Score score, CsUserVip csUserVip) {
        try {
            BufferedImage I = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"moneyok.png"));
            Graphics2D g = (Graphics2D)I.createGraphics();
            g.setColor(Color.white);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            Map<String, String> map = new HashMap<>();
            map.put("b","qq");
            map.put("nk",vipLog.getQqcode());
            map.put("s","640");
            g.drawImage(HttpClientUtils.httpGetNull("http://q1.qlogo.cn/g",map), 82, 132, 110,110,null);
            Font stencil_std = new Font("微软雅黑", Font.PLAIN, 28);
            g.setFont(stencil_std);
            g.drawString(getQqName(vipLog.getQqcode()),210,165);
            List<String> txt = new ArrayList<>();
            txt.add("● 您本次积分上涨："+vipLog.getScore() + "↑");
            txt.add("● 现积分:"+score.getNumber());
            txt.add("● 您本次支付金额:"+vipLog.getMoney()+"元");
            if(csUserVip.getEternity()){
                txt.add("● 会员时间:永久授权");
            }else{
                txt.add("● 结束时间:"+csUserVip.getEndTime());
                txt.add("● 会员总时间:"+csUserVip.getVipMember());
            }
            txt.add("● 可绑定的群聊数:"+csUserVip.getGroup()+"个");
            txt.add("● 可添加BOT好友数:"+csUserVip.getFriend()+"个");
            txt.add("● 可永久授权:"+csUserVip.getGroupEternity()+"个群");
            String binding = csUserVip.getBinding() == 0?"无限制":(csUserVip.getBinding()+"个");
            txt.add("● 可coc绑定:"+binding);
            if(csUserVip.getNumber().compareTo(BigDecimal.ZERO) != 0){
                txt.add("● 赞助余额:"+csUserVip.getNumber().toString());
            }
            for (int i = 0; i < txt.size(); i++) {
                drawString(g,stencil_std,txt.get(i),85,(295 + i*40),450);
            }
            stencil_std = new Font("STENCIL STD", Font.PLAIN, 30);
            g.setFont(stencil_std);
            g.drawString(vipLog.getMoneyNumber(),78,110);
            stencil_std = new Font("STENCIL STD", Font.PLAIN, 25);
            g.setFont(stencil_std);
            g.drawString("("+vipLog.getQqcode()+")",207,195);
            Integer X = content(String.valueOf(csUserVip.getVipGrade()), 55, stencil_std, g);
            g.drawString(String.valueOf(csUserVip.getVipGrade()),292+X,234);
            return I;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /***
     *
     * @param g Graphics2D
     * @param font Font
     * @param text 文本内容
     * @param x    起始点X轴坐标
     * @param y    起始点Y轴坐标
     * @param maxWidth  文字最大长度
     */
    public static void drawString(Graphics2D g , Font font , String text , int x , int y , int maxWidth) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int textH = metrics.getHeight();
        int textW = metrics.stringWidth(label.getText()); //字符串的宽
        String tempText = text;
        while(textW > maxWidth) {
            int n = textW / maxWidth;
            int subPos = tempText.length() / n;
            String drawText = tempText.substring(0 , subPos);
            int subTxtW = metrics.stringWidth(drawText);
            while(subTxtW > maxWidth) {
                subPos--;
                drawText = tempText.substring(0 , subPos);
                subTxtW = metrics.stringWidth(drawText);
            }
            g.drawString(drawText , x , y);
            y += textH;
            textW = textW - subTxtW;
            tempText = tempText.substring(subPos);
        }

        g.drawString(tempText , x , y);
    }

    public static String getQqName(String qqNumber){
        try {
            Map map = new HashMap<>();
            map.put("uins",qqNumber);
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("Content-Type","application/json;charset=GBK");
            String text = HttpClientUtils.httpsPostGBK("https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg",objectObjectHashMap ,map);
            Pattern p = Pattern.compile("\\{+.*\\}");
            Matcher m = p.matcher(text);
            if(m.find()){
                String mpedId ="{"+ m.group().substring(1, m.group().length() - 1)+"}";
                JSONObject json = JSONObject.parseObject(mpedId);
                JSONArray jsonArray = json.getJSONArray(qqNumber);
                if(jsonArray.size() == 8){
                    return jsonArray.getString(6);
                }
            }
        }catch (Exception e){
            System.out.println("名称获取错误："+e.getMessage());
        }
        return "";
    }

    /***
     * 文字居中
     * @param text 文本内容
     * @param width 宽度
     * @param font Font
     * @param g  Graphics2D
     * @return
     */
    private static Integer content(String text,Integer width,Font font,Graphics2D g){
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        int X = (width - textWidth) / 2;
        return X;
    }


    public BufferedImage resultMyScoreOkImage(String userNumber, Score score, CsUserVip csUserVip, Integer count,Integer privateNumber) {
        try {
            BufferedImage I = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"score.png"));
            Graphics2D g = (Graphics2D)I.createGraphics();
            g.setColor(Color.white);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            Map<String, String> map = new HashMap<>();
            map.put("b","qq");
            map.put("nk",userNumber);
            map.put("s","640");
            g.drawImage(HttpClientUtils.httpGetNull("http://q1.qlogo.cn/g",map), 82, 132, 110,110,null);
            Font stencil_std = new Font("微软雅黑", Font.PLAIN, 28);
            g.setFont(stencil_std);
            g.drawString(getQqName(userNumber),210,165);
            List<String> txt = new ArrayList<>();
            txt.add("● 积分:"+score.getNumber());
            if(csUserVip.getEternity()){
                txt.add("● 会员时间:永久授权");
            }else{
                txt.add("● 结束时间:"+csUserVip.getEndTime());
                txt.add("● 会员总时间:"+csUserVip.getVipMember());
            }

            Integer group = csUserVip.getGroup();
            txt.add("———————————————");
            txt.add("● 您总共可授权："+group+"个群聊");
            txt.add("● 已授权："+count+"个群聊");
            txt.add("● 剩余："+(group-count)+"个群可授权！");
            txt.add("———————————————");
            txt.add("● 总共可添加BOT好友数："+csUserVip.getFriend()+"个");
            txt.add("● 已经添加运行BOT："+privateNumber+"个");
            txt.add("● 剩余可添加："+(csUserVip.getFriend() - privateNumber)+"个");
            txt.add("——————————");
            txt.add("● 可永久授权："+csUserVip.getGroupEternity()+"个群");
            String binding = csUserVip.getBinding() == 0?"无限制":(csUserVip.getBinding()+"个");
            txt.add("● 可coc绑定："+binding);
            if(csUserVip.getNumber().compareTo(BigDecimal.ZERO) != 0){
                txt.add("● 赞助过剩："+csUserVip.getNumber().toString());
            }
            for (int i = 0; i < txt.size(); i++) {
                drawString(g,stencil_std,txt.get(i),85,(295 + i*40),450);
            }
            stencil_std = new Font("STENCIL STD", Font.PLAIN, 25);
            g.setFont(stencil_std);
            g.drawString("("+userNumber+")",207,195);
            Integer X = content(String.valueOf(csUserVip.getVipGrade()), 55, stencil_std, g);
            g.drawString(String.valueOf(csUserVip.getVipGrade()),292+X,234);
            return I;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
