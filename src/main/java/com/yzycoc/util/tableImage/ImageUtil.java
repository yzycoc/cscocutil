package com.yzycoc.util.tableImage;

import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 10:21
 * @Version 1.0
 **/
public class ImageUtil extends ImageUtilConfig{
    //图像
    private BufferedImage image;
    //图像工具
    private Graphics2D g;
    //文字最高高度
    private Integer textHeight = 0;
    //文字最长长度
    private Integer textwidth = 0;
    public BufferedImage tableIamge(List<List<String>> data){
        int maxWidth = 0;
        // 计算所有数据中 最大宽度
        Integer[] maxWidthList = new Integer[data.get(0).size()];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                if(StringUtils.isNotEmpty(data.get(i).get(j))){
                    JLabel label = new JLabel(data.get(i).get(j));
                    label.setFont(super.textFont);
                    FontMetrics metrics = label.getFontMetrics(label.getFont());
                    int sizeLength = metrics.stringWidth(label.getText()); //字符串的宽
                    int sizeWidth = maxWidthList[j] == null?0:maxWidthList[j];
                    if(sizeLength > sizeWidth){
                        maxWidthList[j] = sizeLength + marginWidth;
                    }
                    if(sizeLength > textwidth){
                        textwidth = sizeLength + marginWidth;
                    }
                    int textH = metrics.getHeight();
                    if(textH > textHeight){
                        textHeight = textH + marginHeight;
                    }
                }
            }
        }
        int maxHeight = (data.size()) * textHeight ;
        // 赋值宽度
        for (Integer value : maxWidthList) {
            maxWidth += value ;
        }
        image = new BufferedImage(maxWidth+2, maxHeight+2, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g.fillRect(0, 0, maxWidth, maxHeight);
        //设置斑马纹
        for (int i = 0; i < data.size(); i++) {
            int start = 0;
            for (int j = 0; j < maxWidthList.length ; j++) {
                if(i == 0){
                    g.setColor(super.colorRectTitleBack);
                    g.fillRect(start,i*textHeight,maxWidthList[j],textHeight);
                }
                if(i != 0 &&  i%2 == 0 && super.zebra){
                    g.setColor(super.zebraColor);
                    g.fillRect(start,i*textHeight,maxWidthList[j],textHeight);
                }
                start += maxWidthList[j];
            }
            if(zebra == false) break;
        }
        //画边框线
        g.setColor(super.colorLine);
        for (int i = 0; i < data.size(); i++) {
            int start = 0;
            for (int j = 0; j < maxWidthList.length ; j++) {
                //设置头部的颜色
                if(super.lineStype == LineStype.ALL){
                    g.drawLine(start,i*textHeight,start,(i+1)*textHeight);//左
                    g.drawLine(start,(i+1)*textHeight,start + maxWidthList[j],(i+1)*textHeight);//下
                    g.drawLine(start,i*textHeight,start + maxWidthList[j],i*textHeight);//上
                    start += maxWidthList[j];
                    g.drawLine(start,i*textHeight,start,(i+1)*textHeight);//右边
                }else if(super.lineStype == LineStype.bottom){
                    g.drawLine(start,(i+1)*textHeight,start + maxWidthList[j],(i+1)*textHeight);//下
                    start += maxWidthList[j];
                }else if(super.lineStype == LineStype.top){
                    g.drawLine(start,i*textHeight,start + maxWidthList[j],i*textHeight);//上
                    start += maxWidthList[j];
                }
            }
        }
        for (int i = 0; i < data.size(); i++) {
            int start = 0;
            for (int j = 0; j < maxWidthList.length ; j++) {
                if(i == 0){
                    g.setFont(super.textTitleFont);
                    g.setColor(super.textTitleColor);
                }else if(i == 1){
                    g.setFont(super.textFont);
                    g.setColor(super.textColor);
                }
                content(data.get(i).get(j),start,(i+1)*textHeight - (textHeight / 4),maxWidthList[j]);
                start += maxWidthList[j];
            }
        }
        return image;
    }

    /***
     *
     * @param text 文本内容
     * @param x    起始点X轴坐标
     * @param y    起始点Y轴坐标
     * @param maxWidth  文字最大长度
     */
    public  void content(String text , int x , int y , int maxWidth) {
        if(StringUtils.isNotEmpty(text)){
            FontMetrics fontMetrics = g.getFontMetrics(super.textFont);
            int textWidth = fontMetrics.stringWidth(text);
            int X = (maxWidth - textWidth) / 2;
            g.drawString(text,x+X,y);
        }
    }


    /***
     * @param text 文本内容
     * @param x    起始点X轴坐标
     * @param y    起始点Y轴坐标
     * @param maxWidth  文字最大长度
     */
    public  void drawString(String text , int x , int y , int maxWidth) {
        if(StringUtils.isEmpty(text)){
            return ;
        }
        JLabel label = new JLabel(text);
        label.setFont(super.textFont);
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


    public ImageUtil() {
    }
}
