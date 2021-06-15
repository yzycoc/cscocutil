package com.yzycoc.cocutil.util;

import com.yzycoc.config.ConfigParameter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;


/**
 * @program: cscocutil
 * @description: 工具类
 * @author: yzy
 * @create: 2020-08-10 20:31
 * @Version 1.0
 **/
public class CocApiAndCqCustom {
    //主世界兵种列表
    public static String[] troops_home = {"Barbarian","Archer","Giant","Goblin","Wall Breaker","Balloon"
            ,"Wizard","Healer","Dragon","P.E.K.K.A","Baby Dragon","Miner","Electro Dragon","Yeti"
            ,"Minion","Hog Rider","Valkyrie","Golem","Witch","Lava Hound","Bowler","Ice Golem"
            ,"Headhunter"};
    //夜世界兵种列表
    public static String[] troops_builderBase = {"Raged Barbarian","Sneaky Archer","Boxer Giant","Beta Minion","Bomber","Baby Dragon","Cannon Cart","Night Witch","Drop Ship","Super P.E.K.K.A","Hog Glider"};
    //三王信息
    public static String[] heroes = {"Barbarian King","Archer Queen","Grand Warden","Royal Champion","Battle Machine"};//男王、女王、守护、
    //法术信息
    public static String[] spells = {"Lightning Spell","Healing Spell","Rage Spell","Jump Spell","Freeze Spell","Clone Spell","Poison Spell","Earthquake Spell","Haste Spell","Skeleton Spell","Bat Spell","Invisibility Spell"};
    //工程车
    public static String[] s = {"Wall Wrecker","Battle Blimp","Stone Slammer","Siege Barracks","Log Launcher"};

    //主世界兵种列表
    public static String[] troops_home_zhanChon = {"L.A.S.S.I","Electro Owl","Mighty Yak","Unicorn"};
    private static Map<String,String> mapnation =new HashMap<String, String>();

    public static final Map<String, String> war = new HashMap<>();


    static{
        war.put("1", "1");war.put("一", "1");war.put("第一场", "1");war.put("第1场", "1");
        war.put("2", "2");war.put("二", "2");war.put("第二场", "2");war.put("第2场", "2");
        war.put("3", "3");war.put("三", "3");war.put("第三场", "3");war.put("第3场", "3");
        war.put("4", "4");war.put("四", "4");war.put("第四场", "4");war.put("第4场", "4");
        war.put("5", "5");war.put("五", "5");war.put("第五场", "5");war.put("第5场", "5");
        war.put("6", "6");war.put("六", "6");war.put("第六场", "6");war.put("第6场", "6");
        war.put("7", "7");war.put("七", "7");war.put("第七场", "7");war.put("第7场", "7");
        war.put("8", "8");war.put("八", "8");war.put("第八场", "8");war.put("第8场", "8");
    }
    /***
     * 查询此村庄加入状态
     * @param type
     * @return
     */
    public static String CocTpe(String type) {
        if(type.equals("inviteOnly")) {
            return "只有批准了才可加入";
        }else if(type.equals("open")) {
            return "任何人可加入";
        }else if(type.equals("closed")) {
            return "不可加入";
        }else {
            return type;
        }
    }
    /***
     * 战争频率
     * @param warFrequency
     * @return
     */
    public static String CocWarFrequency(String warFrequency) {
        if(warFrequency.equals("always")) {
            return "始终";
        }else if(warFrequency.equals("lessThanOncePerWeek")) {
            return "很少";
        }else if(warFrequency.equals("moreThanOncePerWeek")) {
            return "一周两次";
        }else if(warFrequency.equals("lessThanOncePerWeek")) {
            return "一周一次";
        }else {
            return "未设置";
        }
    }
    /***
     * 玩家职位
     * @param role
     * @return
     */
    public static String CocRole(String role) {
        if(role.equals("leader")) {
            return "首领";
        }else if(role.equals("coLeader")) {
            return "副首领";
        }else if(role.equals("admin")) {
            return "长老";
        }else if(role.equals("member")){
            return "成员";
        }else {
            return "未知";
        }

    }

    /***
     * 返回玩家大本营等级 合二为一
     * @param townHallLevel 大本营等级
     * @param townHallWeaponLevel 别名等级
     * @return
     */
    public static String CocTownHallLevel(Integer townHallLevel,Integer townHallWeaponLevel) {
        if(townHallLevel==12||townHallLevel==13) {
            return "Lv."+townHallLevel+"-"+townHallWeaponLevel;
        }else {
            return "Lv."+townHallLevel;
        }
    }

    /***
     * 捐收比
     * @param donations 捐兵
     * @param donationsReceived 收兵
     * @return 捐收比
     */
    public static String DonationsJuanShou(String donations, String donationsReceived) {
        try {
            int a = Integer.valueOf(donations);
            int b = Integer.valueOf(donationsReceived);
            int tmp = a;
            if(a > b) {
                tmp = b;
            }
            for(int i = tmp; i > 0; i--) {
                if(a % i == 0 && b % i ==0) {
                    System.out.println(a + ":" + b + "=" + (a / i) + ":" + (b / i));
                    return (a / i) + ":" + (b / i);
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /***
     * 捐收比 超进化
     * @param donations 捐兵
     * @param donationsReceived 收兵
     * @return CQ语言
     */
    public static String DonationsJuanShouIng(String donations, String donationsReceived) {
        try {
            int juan = Integer.valueOf(donations);
            int shou = Integer.valueOf(donationsReceived);
            double bili = 0.0;
            if(juan>shou) {
                bili = juan / shou;
                if(bili>5) {
                    return "[CQ:emoji,id=9989]";
                }
            }else {
                bili = shou / juan;
                if(bili>5) {
                    return "[CQ:emoji,id=10060]";
                }
            }

        } catch (Exception e) {
        }
        return "";
    }
    /****
     * 返回部落所属杯段
     * @param trophies
     */
    public static String trophies(String trophies) {
        if(trophies==null) {
            return "无杯";
        }
        int parseInt = Integer.parseInt(trophies);
        if(parseInt>=5000) {
            return "传奇";
        }else if(parseInt>=4100) {
            return "泰坦";
        }else if(parseInt>=3200) {
            return "冠军";
        }else if(parseInt>=2600) {
            return "大师";
        }else if(parseInt>=2000) {
            return "水晶";
        } else if(parseInt>=1400) {
            return "金杯";
        }else if(parseInt>=800) {
            return "银杯";
        }else if(parseInt>=400) {
            return "铜杯";
        }else{
            return "无杯";
        }
    }
    public static String location(String name) {
        if(name==null||name=="") {
            return "未设置";
        }
        String string = mapnation.get(name);
        if(string==null) {
            return "未知";
        }else {
            return string;
        }
    }

    /***
     * 获取部落图标
     * @param ImageFile
     * @return
     */
    public static BufferedImage getImagesClanImage(String ImageFile) {
        File file = new File(ImageFile);
        BufferedImage image = null;
        if(!file.exists()){
            return null;
        }
        try{
            image = ImageIO.read(file);
        }catch(Exception e){
            System.out.println("转换图像失败"+e.toString());
        }
        return image;
    }


    static {
        mapnation.put("International", "国际");
        mapnation.put("Afghanistan", "阿富汗");
        mapnation.put("Aland Islands", "奥兰群岛");
        mapnation.put("Albania", "阿尔巴尼亚");
        mapnation.put("Algeria", "阿尔及利亚");
        mapnation.put("American Samoa", "美属萨摩亚");
        mapnation.put("Andorra", "安道尔");
        mapnation.put("Angola", "安哥拉");
        mapnation.put("Anguilla", "安圭拉");
        mapnation.put("Antigua and Barbuda", "安提瓜和巴布达");
        mapnation.put("Argentina", "阿根廷");
        mapnation.put("Armenia", "亚美尼亚");
        mapnation.put("Aruba", "阿鲁巴");
        mapnation.put("Ascension Island", "阿松森岛");
        mapnation.put("Australia", "澳大利亚");
        mapnation.put("Austria", "奥地利");
        mapnation.put("Azerbaijan", "阿塞拜疆");
        mapnation.put("Bangladesh", "孟加拉");
        mapnation.put("Bahrain", "巴林");
        mapnation.put("Bahamas", "巴哈马");
        mapnation.put("Barbados", "巴巴多斯");
        mapnation.put("Belarus", "白俄罗斯");
        mapnation.put("Belgium", "比利时");
        mapnation.put("Belize", "伯利兹");
        mapnation.put("Benin", "贝宁");
        mapnation.put("Bermuda", "百慕大");
        mapnation.put("Bhutan", "不丹");
        mapnation.put("Bolivia", "玻利维亚");
        mapnation.put("Bosnia and Herzegovina", "波黑");
        mapnation.put("Botswana", "博茨瓦纳");
        mapnation.put("Bouvet Island", "布维岛");
        mapnation.put("Brazil", "巴西");
        mapnation.put("Brunei", "文莱");
        mapnation.put("British Indian Ocean Territory", "英属印度洋领地");
        mapnation.put("British Virgin Islands", "维尔京(英)");
        mapnation.put("Bulgaria", "保加利亚");
        mapnation.put("Burkina Faso", "布基纳法索");
        mapnation.put("Burundi", "布隆迪");
        mapnation.put("Cambodia", "柬埔寨");
        mapnation.put("Cameroon", "喀麦隆");
        mapnation.put("Canada", "加拿大");
        mapnation.put("Cape Verde", "佛得角");
        mapnation.put("Central African Republic", "中非");
        mapnation.put("Chad", "乍得");
        mapnation.put("Chile", "智利");
        mapnation.put("Christmas Island", "圣诞岛");
        mapnation.put("Cocos (Keeling) Islands", "科科斯(基林)群岛");
        mapnation.put("Colombia", "哥伦比亚");
        mapnation.put("Comoros", "科摩罗");
        mapnation.put("Congo (DRC)", "刚果(金)");
        mapnation.put("Congo (Republic)", "刚果(布)");
        mapnation.put("Cook Islands", "库克群岛");
        mapnation.put("Costa Rica", "哥斯达黎加");
        mapnation.put("Cote D'Ivoire", "科特迪瓦");
        mapnation.put("China", "中国");
        mapnation.put("Croatia", "克罗地亚");
        mapnation.put("Cuba", "古巴");
        mapnation.put("Czech", "捷克");
        mapnation.put("Cyprus", "塞浦路斯");
        mapnation.put("Denmark", "丹麦");
        mapnation.put("Djibouti", "吉布提");
        mapnation.put("Dominica", "多米尼加");
        mapnation.put("East Timor", "东帝汶");
        mapnation.put("Ecuador", "厄瓜多尔");
        mapnation.put("Egypt", "埃及");
        mapnation.put("Equatorial Guinea", "赤道几内亚");
        mapnation.put("Eritrea", "厄立特里亚");
        mapnation.put("Estonia", "爱沙尼亚");
        mapnation.put("Ethiopia", "埃塞俄比亚");
        mapnation.put("Faroe Islands", "法罗群岛");
        mapnation.put("Fiji", "斐济");
        mapnation.put("Finland", "芬兰");
        mapnation.put("France", "法国");
        mapnation.put("French Guiana", "法属圭亚那");
        mapnation.put("French Polynesia", "法属波利尼西亚");
        mapnation.put("Gabon", "加蓬");
        mapnation.put("Gambia", "冈比亚");
        mapnation.put("Georgia", "格鲁吉亚");
        mapnation.put("Germany", "德国");
        mapnation.put("Ghana", "加纳");
        mapnation.put("Gibraltar", "直布罗陀");
        mapnation.put("Greece", "希腊");
        mapnation.put("Grenada", "格林纳达");
        mapnation.put("Guadeloupe", "瓜德罗普岛");
        mapnation.put("Guam", "关岛");
        mapnation.put("Guatemala", "危地马拉");
        mapnation.put("Guernsey", "根西岛");
        mapnation.put("Guinea-Bissau", "几内亚比绍");
        mapnation.put("Guinea", "几内亚");
        mapnation.put("Guyana", "圭亚那");
        mapnation.put("Haiti", "海地");
        mapnation.put("Honduras", "洪都拉斯");
        mapnation.put("Hong Kong", "中国香港");
        mapnation.put("Hungary", "匈牙利");
        mapnation.put("Iceland", "冰岛");
        mapnation.put("India", "印度");
        mapnation.put("Indonesia", "印尼");
        mapnation.put("Iran", "伊朗");
        mapnation.put("Iraq", "伊拉克");
        mapnation.put("Ireland", "爱尔兰");
        mapnation.put("Isle of Man", "马恩岛");
        mapnation.put("Israel", "以色列");
        mapnation.put("Italy", "意大利");
        mapnation.put("Jamaica", "牙买加");
        mapnation.put("Japan", "日本");
        mapnation.put("Jersey", "泽西岛");
        mapnation.put("Jordan", "约旦");
        mapnation.put("Kazakhstan", "哈萨克斯坦");
        mapnation.put("Kenya", "肯尼亚");
        mapnation.put("Kiribati", "基里巴斯");
        mapnation.put("South Korea", "韩国");
        mapnation.put("North Korea", "朝鲜");
        mapnation.put("Kuwait", "科威特");
        mapnation.put("Kyrgyzstan", "吉尔吉斯斯坦");
        mapnation.put("Laos", "老挝");
        mapnation.put("Latvia", "拉脱维亚");
        mapnation.put("Lebanon", "黎巴嫩");
        mapnation.put("Lesotho", "莱索托");
        mapnation.put("Liberia", "利比里亚");
        mapnation.put("Libya", "利比亚");
        mapnation.put("Liechtenstein", "列支敦士登");
        mapnation.put("Lithuania", "立陶宛");
        mapnation.put("Luxembourg", "卢森堡");
        mapnation.put("Macedonia", "马其顿");
        mapnation.put("Macau", "中国澳门");
        mapnation.put("Malawi", "马拉维");
        mapnation.put("Malaysia", "马来西亚");
        mapnation.put("Madagascar", "马达加斯加");
        mapnation.put("Maldives", "马尔代夫");
        mapnation.put("Mali", "马里");
        mapnation.put("Malta", "马耳他");
        mapnation.put("Marshall Islands", "马绍尔群岛");
        mapnation.put("Martinique", "马提尼克岛");
        mapnation.put("Mauritania", "毛里塔尼亚");
        mapnation.put("Mauritius", "毛里求斯");
        mapnation.put("Mayotte", "马约特");
        mapnation.put("Mexico", "墨西哥");
        mapnation.put("Micronesia", "密克罗尼西亚");
        mapnation.put("Moldova", "摩尔多瓦");
        mapnation.put("Monaco", "摩纳哥");
        mapnation.put("Mongolia", "蒙古");
        mapnation.put("Montenegro", "黑山");
        mapnation.put("Montserrat", "蒙特塞拉特");
        mapnation.put("Morowaro", "摩洛哥");
        mapnation.put("Mozambique", "莫桑比克");
        mapnation.put("Myanmar (Burma)", "缅甸");
        mapnation.put("Namibia", "纳米比亚");
        mapnation.put("Nauru", "瑙鲁");
        mapnation.put("Nepal", "尼泊尔");
        mapnation.put("Netherlands", "荷兰");
        mapnation.put("New Caledonia", "新喀里多尼亚");
        mapnation.put("New Zealand", "新西兰");
        mapnation.put("Nicaragua", "尼加拉瓜");
        mapnation.put("Niger", "尼日尔");
        mapnation.put("Nigeria", "尼日利亚");
        mapnation.put("Niue", "纽埃");
        mapnation.put("Norfolk Island", "诺福克岛");
        mapnation.put("Norway", "挪威");
        mapnation.put("Oman", "阿曼");
        mapnation.put("Pakistan", "巴基斯坦");
        mapnation.put("Palau", "帕劳");
        mapnation.put("Palestine", "巴勒斯坦");
        mapnation.put("Panama", "巴拿马");
        mapnation.put("Papua New Guinea", "巴布亚新几内亚");
        mapnation.put("Peru", "秘鲁");
        mapnation.put("Philippines", "菲律宾");
        mapnation.put("Pitcairn Islands", "皮特凯恩群岛");
        mapnation.put("Poland", "波兰");
        mapnation.put("Portugal", "葡萄牙");
        mapnation.put("Puerto Rico", "波多黎各");
        mapnation.put("Qatar", "卡塔尔");
        mapnation.put("Reunion", "留尼汪岛");
        mapnation.put("Romania", "罗马尼亚");
        mapnation.put("Rwanda", "卢旺达");
        mapnation.put("Russia", "俄罗斯");
        mapnation.put("Saint Helena", "圣赫勒拿");
        mapnation.put("Saint Kitts-Nevis", "圣基茨和尼维斯");
        mapnation.put("Saint Lucia", "圣卢西亚");
        mapnation.put("St. Vincent & Grenadines", "圣文森特和格林纳丁斯");
        mapnation.put("El Salvador", "萨尔瓦多");
        mapnation.put("Samoa", "萨摩亚");
        mapnation.put("San Marino", "圣马力诺");
        mapnation.put("Sao Tome and Principe", "圣多美和普林西比");
        mapnation.put("Saudi Arabia", "沙特阿拉伯");
        mapnation.put("Senegal", "塞内加尔");
        mapnation.put("Seychelles", "塞舌尔");
        mapnation.put("Sierra Leone", "塞拉利昂");
        mapnation.put("Singapore", "新加坡");
        mapnation.put("Serbia", "塞尔维亚");
        mapnation.put("Slovakia", "斯洛伐克");
        mapnation.put("Slovenia", "斯洛文尼亚");
        mapnation.put("Solomon Islands", "所罗门群岛");
        mapnation.put("Somalia", "索马里");
        mapnation.put("South Africa", "南非");
        mapnation.put("Spain", "西班牙");
        mapnation.put("Sri Lanka", "斯里兰卡");
        mapnation.put("Sudan", "苏丹");
        mapnation.put("Suriname", "苏里南");
        mapnation.put("Swaziland", "斯威士兰");
        mapnation.put("Sweden", "瑞典");
        mapnation.put("Switzerland", "瑞士");
        mapnation.put("Syria", "叙利亚");
        mapnation.put("Tajikistan", "塔吉克斯坦");
        mapnation.put("Tanzania", "坦桑尼亚");
        mapnation.put("Taiwan", "中国台湾");
        mapnation.put("Thailand", "泰国");
        mapnation.put("Trinidad and Tobago", "特立尼达和多巴哥");
        mapnation.put("Timor-Leste", "东帝汶");
        mapnation.put("Togo", "多哥");
        mapnation.put("Tokelau", "托克劳");
        mapnation.put("Tonga", "汤加");
        mapnation.put("Tunisia", "突尼斯");
        mapnation.put("Turkey", "土耳其");
        mapnation.put("Turkmenistan", "土库曼斯坦");
        mapnation.put("Tuvalu", "图瓦卢");
        mapnation.put("Uganda", "乌干达");
        mapnation.put("Ukraine", "乌克兰");
        mapnation.put("United Arab Emirates", "阿联酋");
        mapnation.put("United Kingdom", "英国");
        mapnation.put("United States", "美国");
        mapnation.put("Uruguay", "乌拉圭");
        mapnation.put("U.S. Virgin Islands", "维尔京(美)");
        mapnation.put("Uzbekistan", "乌兹别克斯坦");
        mapnation.put("Vanuatu", "瓦努阿图");
        mapnation.put("Vatican City", "梵蒂冈");
        mapnation.put("Venezuela", "委内瑞拉");
        mapnation.put("Vietnam", "越南");
        mapnation.put("Wallis and Futuna", "瓦利斯群岛和富图纳群岛");
        mapnation.put("Western Sahara", "西撒哈拉");
        mapnation.put("Yemen", "也门");
        mapnation.put("Yugoslavia", "南斯拉夫");
        mapnation.put("Zambia", "赞比亚");
        mapnation.put("Zimbabwe", "津巴布韦");
    }
    public static Integer getTownHallLevel(Integer townHallLevel) {
        if(townHallLevel==null) {
            return 7;
        }else if(townHallLevel == 14) {
            return 0;
        }else if(townHallLevel == 13) {
            return 1;
        }else if(townHallLevel == 12) {
            return 2;
        }else if(townHallLevel == 11) {
            return 3;
        }else if(townHallLevel == 10) {
            return 4;
        }else if(townHallLevel == 9) {
            return 5;
        }else if(townHallLevel == 8) {
            return 6;
        }else if(townHallLevel == 7) {
            return 7;
        }else {
            return 8;
        }
    }
    public static Integer getLeague(Integer id) {
        if(id == 29000022) {
            return 0;
        }else if(id == 29000021||id == 29000020||id == 29000019) {
            return 1;
        }else if(id == 29000018||id == 29000017||id == 29000016) {
            return 2;
        }else if(id == 29000015||id == 29000014||id == 29000013) {
            return 3;
        }else if(id == 29000012||id == 29000011||id == 29000010) {
            return 4;
        }else if(id == 29000009||id == 29000008||id == 29000007) {
            return 5;
        }else if(id == 29000006||id == 29000005||id == 29000004) {
            return 6;
        }else if(id == 29000003||id == 29000002||id == 29000001) {
            return 7;
        }else{
            return 8;
        }
    }


    public static void main(String[] args) {
        //System.out.println(YqHtmlCustom.htmlAll.toString() );
        new CocApiAndCqCustom().saveTxt("123");
    }
    /***
     * 鱼情
     * @param sendGetCoc 需要储存的内容
     */
    public void saveTxt(String sendGetCoc) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(ConfigParameter.file_YuQing_TEXT).getChannel();
            outputChannel = new FileOutputStream( ConfigParameter.file_YuQing_HTML).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            inputChannel.close();
            outputChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        File file = new File(ConfigParameter.file_YuQing_HTML);
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(sendGetCoc);
            fw.close();
        } catch (IOException e) {
            System.out.println("写TXT文件报错");
        }
    }
}
