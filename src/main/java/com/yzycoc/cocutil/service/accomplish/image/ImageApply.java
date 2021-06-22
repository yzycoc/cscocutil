package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocGroup;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocUser;
import com.yzycoc.cocutil.SQLAll.service.ApplyCocUserService;
import com.yzycoc.cocutil.service.result.ApplyData;
import com.yzycoc.cocutil.service.result.ApplyUserData;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.service.result.clanAll.ClanAllListPlayer;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.cocutil.util.enums.WarLeagueEnum;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.ErweimaQRCodeUtil;
import com.yzycoc.custom.HttpClientUtils;
import com.yzycoc.custom.Utf8Util;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.util.BaseLocalThreadPool;
import com.yzycoc.util.tableImage.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-18 22:38
 * @Version 1.0
 **/
@Slf4j
public class ImageApply {


    /***
     * 查询所有报名
     * @param groupNumber
     * @param group_number
     * @param applyCocUserService
     * @return
     */
    public ClanResult get(String groupNumber, List<ApplyCocGroup> group_number, ApplyCocUserService applyCocUserService) {
        long startTime=System.currentTimeMillis();
        log.info("开始合成报名概况图片！");
        List<List<String>> lists = ImageData(group_number, applyCocUserService);
        ClanResult result = ImageClanHc(lists,groupNumber);
        log.info("合成报名概况图片完毕，耗时"+(System.currentTimeMillis() - startTime)+"ms");
        return result;
    }

    private ClanResult ImageClanHc(List<List<String>> lists, String groupNumber) {
        try {
            String saveFilePath = groupNumber;
            BufferedImage bufferedImage = new ImageUtil().tableIamge(lists);
            Thumbnails.of(bufferedImage).outputFormat("jpg").scale(1f).outputQuality(0.45f).toFile(new File(ConfigParameter.filePath_group+"\\"+saveFilePath));
            return new ClanResult(true,saveFilePath, ConfigParameter.filePath_group,"jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
       return new ClanResult(false,"出现错误，请稍后重试！");
    }

    private List<List<String>> ImageData(List<ApplyCocGroup> group_number, ApplyCocUserService applyCocUserService){
        List<List<String>> result = new ArrayList<>();
        List<String> applyData = new ArrayList<>();
        applyData.add("开始报名时间");
        applyData.add("机器人账号");
        applyData.add("启动报名者");
        applyData.add("部落标签");
        applyData.add("部落名称");
        applyData.add("部落人数");
        applyData.add("报名人数");
        applyData.add("唯一标识");
        result.add(applyData);
        for (ApplyCocGroup applyCocGroup : group_number) {
            applyData = new ArrayList<>();
            Integer baomingSum = applyCocUserService.query().eq("uuid", applyCocGroup.getUuid()).count();
            if(baomingSum == null) baomingSum = 0;
            JSONObject clan = JSONObject.parseObject(applyCocGroup.getClanJson());
            applyData.add(applyCocGroup.getCreateDate());
            applyData.add(applyCocGroup.getCreateName());
            applyData.add(applyCocGroup.getUserName());
            applyData.add("#"+applyCocGroup.getClanTag());
            applyData.add(Utf8Util.unicodeToString(clan.getString("name")));
            applyData.add(clan.getString("members"));
            applyData.add(String.valueOf(baomingSum));
            applyData.add(applyCocGroup.getUuid());
            result.add(applyData);
        }
        return  result;
    }

    /***
     * 查询详细的报名情况
     * @param applyGroup
     * @param applyUser
     * @return
     */
    public ClanResult getAll(String groupNumber,String type,ApplyCocGroup applyGroup, List<ApplyCocUser> applyUser) {
        List<List<String>> lists = UserTag(type,applyGroup, applyUser);
        ClanResult result= UserImage(applyGroup.getGroupName(),applyGroup,lists,type);
        return result;
    }

    private ClanResult UserImage(String groupNumber,ApplyCocGroup applyGroup, List<List<String>> lists,String type) {
        try {
            String saveFilePath = applyGroup.getGroupNumber() + applyGroup.getUuid();
            ImageUtil imageUtil = new ImageUtil();
            BufferedImage bufferedImage = imageUtil.tableIamge(lists);
            BufferedImage  image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight() + 200, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.fillRect(0, 0, image.getWidth(), image.getHeight());
            Font f = new Font("微软雅黑", Font.PLAIN, 30);
            g.setFont(f);
            g.drawImage(bufferedImage, 0, 200, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
            JSONObject JSON = JSONObject.parseObject(applyGroup.getClanJson());
            Map<String, BufferedImage> tagData = TagData(groupNumber,JSON,type);
            g.drawImage(tagData.get("ewm"), image.getWidth()-200, 0 ,200,200, null);

            JSONArray labels = JSON.getJSONArray("labels");
            for (int i = 0; i < labels.size(); i++) {
                JSONObject ClanID = labels.getJSONObject(i);
                String getIDS = ClanID.getString("id");
                BufferedImage bufferedImage1 = tagData.get(getIDS);
                g.drawImage(bufferedImage1,  210 , (i * 60) + ((i+1)*6) ,60,60, null);
            }
            g.drawImage(tagData.get(JSON.getString("tag")), 0, 0 ,200,200, null);
            g.setColor(Color.black);
            JSONObject warLeague = JSON.getJSONObject("warLeague");
            StringBuffer txt = new StringBuffer();
            if(warLeague!=null) {
                txt.append("   联赛等级："+WarLeagueEnum.getName(warLeague.getString("id")));
            }
            txt.append("部落人数："+JSON.getString("members"));
            txt.append("   部落等级："+JSON.getString("clanLevel"));
            txt.append("级  类型："+CocApiAndCqCustom.CocTpe(JSON.getString("type")));
            txt.append( "   所需奖杯：" + JSON.getString("requiredTrophies"));
            Boolean isWarLogPublic = JSON.getBoolean("isWarLogPublic");
            if(isWarLogPublic){
                txt.append( "   战争日志：公开");
            }else{
                txt.append( "   战争日志：未公开");
            }
            txt.append( "   部落战：胜/平/败|"+JSONString(JSON,"warWins")+"/"+JSONString(JSON,"warTies")+"/"+JSONString(JSON,"warLosses"));
            content(g,f,txt.toString(),200,190,bufferedImage.getWidth() - 400);
            f = new Font("微软雅黑", Font.BOLD, 50);
            txt = new StringBuffer();
            txt.append(Utf8Util.unicodeToString(JSON.getString("name"))+"【"+JSON.getString("tag")+"】"+" /   群："+groupNumber);
            g.setFont(f);
            g.setColor(Color.orange);
            content(g,f,txt.toString(),200,80,bufferedImage.getWidth() - 400);
            Thumbnails.of(image).outputFormat("jpg").scale(1f).outputQuality(1f).toFile(new File(ConfigParameter.filePath_group+"\\"+saveFilePath));
            return new ClanResult(true,saveFilePath, ConfigParameter.filePath_group,"jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ClanResult(false,"出现错误，请稍后重试！");
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
    public static void content(Graphics2D g , Font font , String text , int x , int y , int maxWidth) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        int X = (maxWidth - textWidth) / 2;
        g.drawString(text,x+X,y);
    }

    /***
     * 数据处理区
     * @param data
     * @param type
     * @return
     */
    public Map<String, BufferedImage> TagData(String groupNumber, JSONObject data, String type) {
        String tag = data.getString("tag");
        JSONArray labels = data.getJSONArray("labels");
        Integer countDownSize  = 2 + labels.size() ;
        Map<String,BufferedImage> map = new HashMap<>();
        Executor threadPool = BaseLocalThreadPool.getThreadPool();
        CountDownLatch count = new CountDownLatch(countDownSize);
        JSONObject badgeUrls = data.getJSONObject("badgeUrls");
        if(badgeUrls!=null) {
            String badgerUrlslarge = badgeUrls.getString("medium");
            if(badgerUrlslarge!=null) {
                threadPool.execute(()->{
                    try {
                        String ImageFile = ConfigParameter.filePath_ClanImage+badgerUrlslarge.replaceAll("https://api-assets.clashofclans.com/badges/200/","");
                        BufferedImage image = CocApiAndCqCustom.getImagesClanImage(ImageFile);
                        if(image==null) {
                            log.info("获取部落图标"+badgerUrlslarge);
                            BufferedImage httpsGet = HttpClientUtils.httpsGet(badgerUrlslarge, null);
                            map.put(tag,httpsGet);
                            ImageIO.write(httpsGet, "png", new File(ImageFile));
                        }else {
                            map.put(tag,image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("无法获取到该部落的部落图标"+tag);
                    }finally {
                        count.countDown();
                    }
                });
            }else {
                count.countDown();
            }
        }else {
            count.countDown();
        }
/*
        threadPool.execute(()->{
            try {//http://p.qlogo.cn/gh/194644518/194644518/0
                Map<String, String> par = new HashMap<>();
                map.put("groupImage", HttpClientUtils.httpGetNull("http://p.qlogo.cn/gh/"+groupNumber+"/"+groupNumber+"/0",par));
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                count.countDown();
            }
        });*/

        for (int i = 0; i < labels.size(); i++) {
            JSONObject ClanID = labels.getJSONObject(i);
            Executor threadPool3 = BaseLocalThreadPool.getThreadPool();
            threadPool3.execute(()->{
                String getIDS = ClanID.getString("id");
                String ImageFile =ConfigParameter.filePath_CocAll + "clanid"+getIDS+".png";
                BufferedImage image = CocApiAndCqCustom.getImagesClanImage(ImageFile);
                if(image==null) {//获取这个ID图片为空则去获取网络图片
                    log.info("获取部落个性标签"+getIDS);
                    BufferedImage bufferedImage = HttpClientUtils.httpsGet(ClanID.getJSONObject("iconUrls").getString("small"), null);
                    try {
                        ImageIO.write(bufferedImage, "png", new File(ConfigParameter.filePath_CocAll + "clanid"+getIDS+".png"));
                        map.put(getIDS, bufferedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("获取图片失败");
                    }finally {
                        count.countDown();
                    }
                }else {
                    map.put(getIDS, image);
                    count.countDown();
                }
            });
        }
        // "网页版本清单将在后期版本推出！";//
        String PLAY_TXT =ConfigParameter.HttpUrlapplyCocGroup + type;//+tag.substring(1, tag.length());
        threadPool.execute(()->{
            try {
                BufferedImage image = ErweimaQRCodeUtil.createImage(PLAY_TXT, ConfigParameter.file_QRcode, true);
                map.put("ewm",image);
            }finally {
                count.countDown();
            }
        });
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return map;
    }


    private List<List<String>> UserTag(String type,ApplyCocGroup applyGroup, List<ApplyCocUser> applyUser) {
        List<ApplyUserData> result = new ArrayList<>();
        for (ApplyCocUser user : applyUser) {
            ApplyUserData data = new ApplyUserData();
            JSONObject JSON = JSONObject.parseObject(user.getPlayerJson());
            data.setName(Utf8Util.unicodeToString(JSON.getString("name")));
            data.setTag(JSON.getString("tag"));
            String townHallLevel = JSON.getString("townHallWeaponLevel") == null?"":("-"+JSON.getString("townHallWeaponLevel"));
            data.setTownHallLevel(JSON.getString("townHallLevel")  + townHallLevel);
            data.setBuilderHallLevel(JSON.getString("builderHallLevel"));
            JSONArray heroes = JSON.getJSONArray("heroes");
            for (int i = 0; i < heroes.size(); i++) {
                JSONObject heroesObject = heroes.getJSONObject(i);
                String heroesName = heroesObject.getString("name");
                if("Barbarian King".equals(heroesName)) {
                    data.setBarbarianKing(heroesObject.getString("level"));
                }else if("Archer Queen".equals(heroesName)) {
                    data.setArcherQueen(heroesObject.getString("level"));
                }else if("Grand Warden".equals(heroesName)) {
                    data.setGrandWarden(heroesObject.getString("level"));
                }else if("Royal Champion".equals(heroesName)) {
                    data.setRoyalChampion(heroesObject.getString("level"));
                }
            }
            /** 研究室完成成果 */
            Integer sumNumber = 0;
            JSONArray troops = JSON.getJSONArray("troops");
            for (int i = 0; i < troops.size(); i++) {
                if("home".equals(troops.getJSONObject(i).getString("village"))) {
                    sumNumber +=troops.getJSONObject(i).getInteger("level");
                }
            }
            JSONArray spells = JSON.getJSONArray("spells");
            for (int i = 0; i < spells.size(); i++) {
                sumNumber +=spells.getJSONObject(i).getInteger("level");
            }
            data.setNumber(sumNumber);
            data.setExpLevel(JSONString(JSON,"expLevel"));
            data.setTrophies(JSONString(JSON,"trophies") +"/"+JSONString(JSON,"bestTrophies"));
            data.setWarStars(JSONString(JSON,"warStars"));
            data.setAttackWins(JSONString(JSON,"attackWins") +"/"+JSONString(JSON,"defenseWins"));
            data.setRole(CocApiAndCqCustom.CocRole(JSONString(JSON,"role")));
            data.setDonations(JSONString(JSON,"donations") +"/"+JSONString(JSON,"donationsReceived"));
            data.setVersusBattleWinCount(JSONString(JSON,"versusBattleWinCount"));
            JSONObject clan = JSON.getJSONObject("clan");
            if(clan!=null){
                String tag = clan.getString("tag");
                if(tag.equals("#"+applyGroup.getClanTag())){
                    data.setIsClan("在部落");
                }
            }
            data.setCreateDate(user.getCreateDate());
            data.setUserNumber(user.getUserName());
            data.setGroupNumber(user.getGroupName());
            data.setRobotNumber(user.getCreateName());
            data.setRemark(user.getRemark()==null?"":user.getRemark());
            result.add(data);
        }
        Collections.sort(result, new ImageApply.sortByIdImage(""));

        List<List<String>> data = new ArrayList<>();
        List<String> applyData = new ArrayList<>();
        applyData.add("玩家名");
        applyData.add("游戏标签");
        applyData.add("报名账号");
        applyData.add("大本营");
        applyData.add("夜世界");
        applyData.add("蛮王");
        applyData.add("女王");
        applyData.add("守护");
        applyData.add("闰土");
        applyData.add("科技/"+ConfigParameter.ClanPlayermax);
        applyData.add("等级");
        applyData.add("奖杯/最高");
        applyData.add("战星");
        applyData.add("进攻/防守");
        applyData.add("职位");
        applyData.add("捐/收");
        applyData.add("对战胜场");
        applyData.add("状态");
        applyData.add("报名时间");
        applyData.add("备注");
        applyData.add("报名所在群");
        applyData.add("受理BOT");
        data.add(applyData);
        for (ApplyUserData userData : result) {
            applyData = new ArrayList<>();
            applyData.add(userData.getName());
            applyData.add(userData.getTag());
            applyData.add(userData.getUserNumber());
            applyData.add(userData.getTownHallLevel());
            applyData.add(userData.getBuilderHallLevel());
            applyData.add(userData.getBarbarianKing());
            applyData.add(userData.getArcherQueen());
            applyData.add(userData.getGrandWarden());
            applyData.add(userData.getRoyalChampion());
            applyData.add(String.valueOf(userData.getNumber()));
            applyData.add(userData.getExpLevel());
            applyData.add(userData.getTrophies());
            applyData.add(userData.getWarStars());
            applyData.add(userData.getAttackWins());
            applyData.add(userData.getRole());
            applyData.add(userData.getDonations());
            applyData.add(userData.getVersusBattleWinCount());
            applyData.add(userData.getIsClan());
            applyData.add(userData.getCreateDate());
            applyData.add(userData.getRemark());
            applyData.add(userData.getGroupNumber());
            applyData.add(userData.getRobotNumber());
            data.add(applyData);
        }
        return data;
    }
    class sortByIdImage implements Comparator<Object> {
        private String demo ;

        public sortByIdImage(String s) {
            demo = s;
        }

        public int compare(Object o1, Object o2) {
            String number;
            String number1;
            switch (demo){
                case "玩家名":
                    number = ((ApplyUserData) o1).getName();
                    number1 = ((ApplyUserData) o2).getName();
                case "大本营":
                    number = ((ApplyUserData) o1).getTownHallLevel();
                    number1 = ((ApplyUserData) o2).getTownHallLevel();
                case "蛮王":
                    number = ((ApplyUserData) o1).getBarbarianKing();
                    number1 = ((ApplyUserData) o2).getBarbarianKing();
                case "女王":
                    number = ((ApplyUserData) o1).getArcherQueen();
                    number1 = ((ApplyUserData) o2).getArcherQueen();
                case "守护":
                    number = ((ApplyUserData) o1).getGrandWarden();
                    number1 = ((ApplyUserData) o2).getGrandWarden();
                case "闰土":
                    number = ((ApplyUserData) o1).getRoyalChampion();
                    number1 = ((ApplyUserData) o2).getRoyalChampion();
                case "等级":
                    number = ((ApplyUserData) o1).getExpLevel();
                    number1 = ((ApplyUserData) o2).getExpLevel();
                case "奖杯":
                    number = ((ApplyUserData) o1).getTrophies();
                    number1 = ((ApplyUserData) o2).getTrophies();
                case "战星":
                    number = ((ApplyUserData) o1).getWarStars();
                    number1 = ((ApplyUserData) o2).getWarStars();
                case "职位":
                    number = ((ApplyUserData) o1).getRole();
                    number1 = ((ApplyUserData) o2).getRole();
                case "对战胜场":
                    number = ((ApplyUserData) o1).getVersusBattleWinCount();
                    number1 = ((ApplyUserData) o2).getVersusBattleWinCount();
                case "状态":
                    number = ((ApplyUserData) o1).getIsClan();
                    number1 = ((ApplyUserData) o2).getIsClan();
                case "报名时间":
                    number = ((ApplyUserData) o1).getCreateDate();
                    number1 = ((ApplyUserData) o2).getCreateDate();
                default:
                    number = ((ApplyUserData) o1).getNumber().toString();
                    number1 = ((ApplyUserData) o2).getNumber().toString();
            }


            if(Double.valueOf(number)<Double.valueOf(number1))
                return 1;
            return -1;
        }
    }
    public String JSONString(JSONObject json,String value){
        String date = json.getString(value);
        if(date == null){
            return "-";
        }else{
            return date;
        }
    }
}
