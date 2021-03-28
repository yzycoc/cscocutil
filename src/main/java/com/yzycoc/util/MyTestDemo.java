package com.yzycoc.util;

import com.yzycoc.util.tableImage.ImageUtil;
import com.yzycoc.util.tableImage.ImageUtilConfig;
import com.yzycoc.util.tableImage.LineStype;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 11:00
 * @Version 1.0
 **/
public class MyTestDemo {

    public static void main(String[] args){
        try {

            /*MyDemo<DemoEntity> objectMyDemo = new MyDemo<>();
            List<DemoEntity> ima = new ArrayList<>();
            ima.add(new DemoEntity("测试","heigths"));
            ima.add(new DemoEntity("高度","demo"));
            objectMyDemo.m(ima);
            List<List<String>> lists = objectMyDemo.objListToStringList(ima);
            for (List<String> list : lists) {
                for (String s : list) {
                    System.out.print(s+"\t");
                }
            }*/
            /*ImageUtil imageUtil = new ImageUtil();
            //imageUtil.setTextFont(new Font("微软雅黑",Font.PLAIN,12));
            //imageUtil.setTextTitleFont(new Font("微软雅黑",Font.BOLD,12));
            imageUtil.setZebra(true);
            imageUtil.setLineStype(LineStype.ALL);
            List<List<String>> data=  new ArrayList<>();
            List<String> list = new ArrayList<>();
            list.add("标题");
            list.add("QQ");
            list.add("内容");
            data.add(list);

            list = new ArrayList<>();
            list.add("4");
            list.add("23333撒地方");
            list.add("1234");
            data.add(list);


            list = new ArrayList<>();
            list.add("3");
            list.add("123412213423142阿斯蒂芬343312312");
            list.add("1234");
            data.add(list);


            list = new ArrayList<>();
            list.add("123");
            list.add("撒地方阿斯顿打撒");
            list.add("1234");
            data.add(list);

            BufferedImage bufferedImage = imageUtil.tableIamge(data);
            ImageIO.write(bufferedImage, "png", new File("G:\\imagedemo\\test.png"));*/
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
