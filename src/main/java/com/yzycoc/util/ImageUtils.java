package com.yzycoc.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.custom.HttpClientUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cscocutil
 * @description: 图像工具类
 * @author: yao
 * @create: 2021-03-28 22:08
 * @Version 1.0
 **/
public class ImageUtils {
    /***
     * 文字自动换行
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



    /***
     * 文字居中
     * @param text 文本内容
     * @param width 宽度
     * @param font Font
     * @param g  Graphics2D
     * @return
     */
    public static Integer content(String text,Integer width,Font font,Graphics2D g){
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        int X = (width - textWidth) / 2;
        return X;
    }
}
