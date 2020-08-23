package com.yzycoc.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-02 10:59
 * @Version 1.0
 **/
public class ConfigParameter {

    public static final String HttpUrl = "http://yzycoc.com/";

    public static final String HttpUrlClan  = HttpUrl + "";
    public static final String HttpUrlPlayer  = HttpUrl + "qq/cocApi/playercoc?tag=";

    public static final String HttpOSS_Alibaba = "http://yzycoc.oss-cn-shanghai.aliyuncs.com/";
    public static final String Jarvis_API = "http://107.172.140.107:59547/api";
    public static final String Jarvis_AUTO = "Mpf7GZgIBcZSBGeFKI9jA0Gu";

    public static Integer ClanPlayermax = 259;
    //COC官方提供的API
    public static  String CocApi = "";

    public static boolean clanHttpSaveSql = true;
    //可以使用特殊指令的QQ号
    public static List<String> RobotadminQq = new ArrayList<>();
    //最高权限管理员 -- 相当于作者
    public static String RobotAdministratorQq = "936642284";
    //是否使用将常用图片缓存到Map中
    public static final Boolean ImageMapCache = true;
    /***
     * 文件存放地址
     */
    //部落图标存放地址 -- 网络下载下来的
    public static final String filePath_ClanImage = "C:\\cocutil\\clanImage\\";
    //部落冲突基础文件存放地址 -- 提前备份备份好的
    public static final  String filePath_CocAll = "C:\\cocutil\\matter\\";

    //****

    /***生成的文件存放的路径
     * 会进行删除的文件 生成的临时文件，用一次就不会用了
     */
    // 基础部落信息
    public static final String filePath_ImageClan = "C:\\cocutil\\imageClan";
    // 基础玩家信息
    public static final String filePath_ImagePlayer = "C:\\cocutil\\imagePlayer";
    //生成的鱼情信息
    public static final String filePath_Yq = "C:\\cocutil\\imageYq";
    //生成的部落配置信息
    public static final String filePath_ClanAll = "C:\\cocutil\\imageClanAll";
    //生成部落配置统计
    public static final String filePath_ClanCollectText = "C:\\cocutil\\imageClanAllCollectText";
    public static final String filePath_ClanCollectImage = "C:\\cocutil\\imageClanAllCollectImage";
    //jarvis获取的图片存放地址
    public static final String filePath_jarvis_Image = "C:\\cocutil\\imageJarvis";
    /***
     * 默认文件存放地址
     */
    //二维码 中间Logo所在位置
    public static final String file_QRcode = "C:\\cocutil\\matter\\QRcode.jpg";
    //二维码 Error 报错后的文件
    public static final String file_QRcode_Default = "C:\\cocutil\\matter\\QRcode.png";
    //部落 --头部
    public static final String file_ImageClan_clantent = "C:\\cocutil\\matter\\clantent.png";
    //部落 --内容
    public static final String file_ImageClan_clantitle = "C:\\cocutil\\matter\\clantitle.png";
    //鱼情代码存放位置
    public static final String file_YuQing_HTML = "c:\\cocutil\\yq\\yuqing.html";
    //鱼情合成头部图片
    public static final String file_Yq_Image = "C:\\cocutil\\matter\\cocyq.jpg";
    //鱼情趋势图
    public static final String file_Yq_qushi_Image = "C:\\cocutil\\yq\\yuqing.png";

    public static String filePath[] = {filePath_jarvis_Image,filePath_ClanImage,filePath_CocAll,filePath_ImageClan,filePath_ImagePlayer,filePath_Yq,filePath_ClanAll,filePath_ClanCollectText,filePath_ClanCollectImage};
    static{
        for (int i = 0; i < filePath.length; i++) {
            File file = new File(filePath[i]);
            if(!file.exists()){//如果文件夹不存在
                file.mkdirs();//创建文件夹
            }
        }
    }
}
