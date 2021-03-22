package com.yzycoc.util.tableImage;

import lombok.Data;

import java.awt.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 10:46
 * @Version 1.0
 **/
@Data
public class ImageUtilConfig {


    /***
     * 文字的大小
     */
    protected Font textFont =  new Font("微软雅黑",Font.PLAIN,30);
    /***
     * 标题文字大小
     */
    protected Font textTitleFont =  new Font("微软雅黑",Font.BOLD,30);
    /***
     * 设置文字颜色
     */
    protected Color textColor =  Color.black;


    /***
     * 边框的颜色
     */
    protected Color colorLine = new Color(142,170,219);
    /***
     * 边框样式
     */
    protected LineStype lineStype = LineStype.ALL;
    /***
     * 头部背景色
     */
    protected Color colorRectTitleBack = new Color(68,114,196);
    /**
     * 头部文字颜色
     */
    protected Color textTitleColor = Color.white;
    /***
     * 是否使用斑马纹
     */
    protected Boolean zebra = true;
    /***
     * 斑马纹 背景色
     */
    protected Color zebraColor = new Color(217,226,243);
    /***
     * 宽度外边距
     */
    protected int marginWidth = 22;
    /***
     * 高度外边距
     */
    protected int marginHeight = 12;
}
