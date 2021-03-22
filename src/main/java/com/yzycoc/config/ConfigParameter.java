package com.yzycoc.config;

import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.custom.CacheMap;

import java.io.File;
import java.util.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-02 10:59
 * @Version 1.0
 **/
public class ConfigParameter {
    //小栗子系统文件
    public static Map<String,String> xlzFile = new HashMap<>();

    public static final String HttpUrl = "http://yzycoc.com/";
    // 正在生成中的缓存
    public static CacheMap<String, Boolean> clanCache = new CacheMap<>();
    //图片生成完毕的缓存
    public static CacheMap<String, ClanResult> clanCacheImage = new CacheMap<>();
    public static final String HttpUrlClan  = HttpUrl + "qq/cocApi/clancoc?tag=";
    public static final String HttpUrlPlayer  = HttpUrl + "qq/cocApi/playercoc?tag=";

    public static final String HttpOSS_Alibaba = "http://yzycoc.oss-cn-shanghai.aliyuncs.com/";
    public static final String Jarvis_API = "http://107.172.140.107:59547/api";
    public static final String Jarvis_AUTO = "dF0bvQfq7WGwPHbq92tQLgGi";//"Mpf7GZgIBcZSBGeFKI9jA0Gu";//"dF0bvQfq7WGwPHbq92tQLgGi";

    public static  String network_Path = "";//外网地址
    public static  String LAN_Path = "";//内网地址
    public static  String network_Path_IP = "";//外网地址
    public static  String LAN_Path_IP = "";//内网地址
    public static  String PROT = "";//端口

    //查看机器人文档的网络文档地址
    public static String http_document ="";
    //兵种最高等级
    public static Integer ClanPlayermax = 273;
    //COC数据是否保存到数据库
    public static boolean clanHttpSaveSql = true;

    //是否使用将常用图片缓存到Map中
    public static final Boolean ImageMapCache = true;
    /***
     * 文件存放地址
     */

    //部落图标存放地址 -- 网络下载下来的
    public static final String filePath_ClanImage = "c:\\cocutil\\clanImage\\";
    //部落冲突基础文件存放地址 -- 提前备份备份好的
    public static final  String filePath_CocAll = "c:\\cocutil\\matter\\";
    //VIP用户专用
    public static final String filePath_vip = "c:\\cocutil\\vip";

    /***生成的文件存放的路径
     * 会进行删除的文件 生成的临时文件，用一次就不会用了
     */
    // 基础部落信息
    public static final String filePath_ImageClan = "c:\\cocutil\\imageClan";
    // 基础玩家信息
    public static final String filePath_ImagePlayer = "c:\\cocutil\\imagePlayer";
    //生成的鱼情信息
    public static final String filePath_Yq = "c:\\cocutil\\imageYq";
    //生成的部落配置信息
    public static final String filePath_ClanAll = "c:\\cocutil\\imageClanAll";
    //生成部落配置统计
    public static final String filePath_ClanCollectText = "c:\\cocutil\\imageClanAllCollectText";
    public static final String filePath_ClanCollectImage = "c:\\cocutil\\imageClanAllCollectImage";
    //jarvis获取的图片存放地址
    public static final String filePath_jarvis_Image = "c:\\cocutil\\imageJarvis";
    //生成的鱼情信息
    public static final String filePath_group = "c:\\cocutil\\group";
    public static final String file_vips = "c:\\cocutil\\vip";
    /***
     * 默认文件存放地址
     */
    //二维码 中间Logo所在位置
    public static final String file_QRcode = "c:\\cocutil\\matter\\QRcode02.png";
    //二维码 Error 报错后的文件
    public static final String file_QRcode_Default = "c:\\cocutil\\matter\\QRcode.png";
    //部落 --头部
    public static final String file_ImageClan_clantent = "c:\\cocutil\\matter\\clantent.png";
    //部落 --内容
    public static final String file_ImageClan_clantitle = "c:\\cocutil\\matter\\clantitle.png";
    //鱼情代码存放位置
    public static final String file_YuQing_HTML = "c:\\cocutil\\yq\\yuqing.html";
    public static final String file_YuQing_TEXT = "c:\\cocutil\\yq\\yuqing.txt";
    //鱼情合成头部图片
    public static final String file_Yq_Image = "c:\\cocutil\\matter\\cocyq.jpg";

    //鱼情合成头部图片
    public static final String file_Yq_Image_Two = "c:\\cocutil\\matter\\cocyq1.jpg";
    //鱼情趋势图
    public static final String file_Yq_qushi_Image = "c:\\cocutil\\yq\\yuqing.png";
    //会员用户过期生成的图片
    public static final String file_vip = "c:\\cocutil\\vip\\userRemove.png";
    public static String filePath[] = {
            filePath_jarvis_Image,filePath_ClanImage,filePath_CocAll,filePath_ImageClan,filePath_ImagePlayer
            ,filePath_Yq,filePath_ClanAll,filePath_ClanCollectText,filePath_ClanCollectImage,filePath_vip,filePath_group
    };
    public static String fileRemove[] = {
            filePath_ImageClan,filePath_ImagePlayer,filePath_Yq,filePath_ClanAll,filePath_ClanCollectText,filePath_ClanCollectImage,
            filePath_jarvis_Image,filePath_group
    };

    static{
        for (int i = 0; i < filePath.length; i++) {
            File file = new File(filePath[i]);
            if(!file.exists()){//如果文件夹不存在
                file.mkdirs();//创建文件夹
            }
        }
    }
    //COC官方提供的API
    public static  String CocApi = " ";


}
