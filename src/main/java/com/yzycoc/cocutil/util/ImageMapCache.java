package com.yzycoc.cocutil.util;

import com.yzycoc.config.ConfigParameter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cscocutil
 * @description: 常用图片缓存
 * @author: yzy
 * @create: 2020-08-21 21:44
 * @Version 1.0
 **/
public class ImageMapCache {
    private static Map<String, BufferedImage> redis = new HashMap<>();

    static {
        if(ConfigParameter.ImageMapCache){
            String[] clansId={
                    //部落个性标签
                    "clanid000","clanid56000000","clanid56000001","clanid56000002","clanid56000003","clanid56000004","clanid56000005","clanid56000006","clanid56000007","clanid56000008","clanid56000009","clanid56000010","clanid56000011","clanid56000012","clanid56000013","clanid56000014","clanid56000015"
                    //玩家个性标签
                    ,"PlayersId57000000","PlayersId57000001","PlayersId57000002","PlayersId57000003","PlayersId57000004","PlayersId57000005","PlayersId57000006","PlayersId57000007","PlayersId57000008","PlayersId57000009","PlayersId57000010","PlayersId57000011","PlayersId57000012","PlayersId57000013","PlayersId57000014","PlayersId57000015","PlayersId57000016","PlayersId57000017"
                    //奖杯
                    ,"Plaryleague29000000","Plaryleague29000001","Plaryleague29000002","Plaryleague29000003","Plaryleague29000004","Plaryleague29000005","Plaryleague29000006","Plaryleague29000007","Plaryleague29000008","Plaryleague29000009","Plaryleague29000010","Plaryleague29000011","Plaryleague29000012","Plaryleague29000013","Plaryleague29000014","Plaryleague29000015","Plaryleague29000016","Plaryleague29000017","Plaryleague29000018","Plaryleague29000019","Plaryleague29000020","Plaryleague29000021","Plaryleague29000022"
                    //大本营
                    ,"Lv3","Lv4","Lv5","Lv6","Lv7","Lv8","Lv9","Lv10","Lv11","Lv12-1","Lv12-2","Lv12-3","Lv12-4","Lv12-5","Lv13-1","Lv13-2","Lv13-3","Lv13-4","Lv13-5"
                    //夜世界
                    ,"BH2","BH3","BH4","BH5","BH6","BH7","BH8","BH9"
                    //鱼情
                    ,"down","up"
            };
            serRedisImage("JBZclan");//奖杯
            serRedisImage("JBYclan");//奖杯 - 夜世界
            //部落配置 红手指
            serRedisImage("cocdiandian");
            serRedisImage("QRcode");//二维码
        }
    }

    /***
     * 获取图片
     * @param fileName
     * @param isPng
     * @return
     */
    public static BufferedImage getImage(String fileName,Boolean isPng){
        try {
            StringBuffer filePath =new StringBuffer(ConfigParameter.filePath_CocAll);
            filePath.append(fileName);
            if(isPng){
                filePath.append(".png");
            }else{
                filePath.append(".jpg");
            }
            BufferedImage redisImage = redis.get(filePath.toString());
            if(redisImage!= null){
                return redisImage;
            }
            BufferedImage read = ImageIO.read(new File(filePath.toString()));
            if(read != null) {
                setRedis(fileName,read);
                return read;
            }
        } catch (IOException e) {

        }
        System.out.println("无法获取图片"+fileName);
        return null;
    }
    /***
     * 获取图片
     * @param fileName
     * @return
     */
    public static BufferedImage getImage(String fileName){
        try {
            StringBuffer filePath =new StringBuffer(ConfigParameter.filePath_CocAll);
            filePath.append(fileName);
            filePath.append(".png");
            BufferedImage redisImage = redis.get(filePath.toString());
            if(redisImage!= null){
                return redisImage;
            }
            BufferedImage read = ImageIO.read(new File(filePath.toString()));
            if(read != null) {
                setRedis(fileName,read);
                return read;
            }
        } catch (IOException e) {}
        System.out.println("无法获取图片"+fileName);
        return null;
    }
    /***
     * 将 fileName文件 存到Map中
     * @param fileName 文件名称
     * @return
     */
    public static void serRedisImage(String fileName,Boolean isPng){
        try {
            StringBuffer filePath =new StringBuffer(ConfigParameter.filePath_CocAll);
            filePath.append(fileName);
            if(isPng){
                filePath.append(".png");
            }else{
                filePath.append(".jpg");
            }
            BufferedImage read = ImageIO.read(new File(filePath.toString()));
            setRedis(fileName,read);
        } catch (IOException e) {
        }
    }
    /***
     * 将 fileName文件 存到Map中
     * @param fileName 文件名称
     * @return
     */
    public static void serRedisImage(String fileName){
        try {
            StringBuffer filePath =new StringBuffer(ConfigParameter.filePath_CocAll);
            filePath.append(fileName);
            filePath.append(".png");
            BufferedImage read = ImageIO.read(new File(filePath.toString()));
            if(read!=null){
                setRedis(fileName,read);
                return ;
            }
        } catch (IOException e) {}
        System.out.println("获取文件失败"+fileName);
    }


    /***
     * 将图片保存到缓存Map中
     * @param fileName
     * @param bufferedImage
     */
    public static void setRedis(String fileName,BufferedImage bufferedImage){
        if(ConfigParameter.ImageMapCache){
            redis.put(fileName,bufferedImage);
        }
    }
}
