package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.accomplish.common.ClanListAll;
import com.yzycoc.cocutil.service.result.clanAll.*;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.*;
import com.yzycoc.cocutil.util.enums.WarLeagueEnum;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * @program: cscocutil
 * @description: 生成部落配置图片
 * @author: yzy
 * @create: 2020-08-22 13:40
 * @Version 1.0
 **/
public class ImageClanAll {
    //生成图片
    public ClanResult get(String tag, CocEquilibrium cocHttp) {
        ClanAllListHttp clanHttp = ClanListAll.ClanHttp(tag,cocHttp);
        if(!clanHttp.getSuccess()) {
            return new ClanResult(false, clanHttp.getResult());
        }
        ClanAllListClan clan = getClanData(clanHttp);
        if(clan == null){
            return new ClanResult(false,"数据处理失败。请稍后重试！");
        }
        ClanResult resutle = ImageAll(tag,clanHttp,clan);
        return resutle;
    }

    private ClanResult ImageAll(String tag, ClanAllListHttp clanHttp, ClanAllListClan clanData)   {
        try {
            BufferedImage I = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"clanAll.jpg"));
            Graphics2D g = (Graphics2D)I.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            //图片
            g.drawImage(clanHttp.getClanImage(), 50, 130, 460,460,null);
            List<String> labels = clanData.getLabels();
            Integer width = 535;
            for (String string : labels) {
                g.drawImage(ImageMapCache.getImage(string), width, 188, 130,130,null);
                width +=140;
            }
            Integer[][] townHallLevel = clanData.getTownHallLevel();
            width = 1100;
            Integer height = 257;
            g.setFont(new Font("STENCIL STD",Font.BOLD,55));
            g.setColor(Color.black);
            for (int j = 0; j < townHallLevel.length; j++) {
                Integer[] integers = townHallLevel[j];
                for (int k = 0; k < integers.length; k++) {
                    String sum =integers[k] == null?null:String.valueOf(integers[k]);
                    if(!StringUtils.isEmpty(sum)&&!"null".equals(sum)&&!"0".equals(sum)) {
                        g.drawString(sum, width + (sum.length()==1?22:0), height);
                    }
                    if(k == (integers.length - 2)) {
                        width += 140;
                    }else {
                        width += 127;
                    }
                }
                width = 1100;
                if(j == (townHallLevel.length - 2)) {
                    height += 103;
                }else {
                    height += 85;
                }
            }
            g.setFont(new Font("STENCIL STD",Font.BOLD,85));
            g.setColor(Color.white);
            g.drawString(clanData.getClanPoints(), 189, 897);
            g.drawString(clanData.getClanVersusPoints(), 613, 897);
            g.setFont(new Font("微软雅黑",Font.BOLD,95));
            g.drawString(clanData.getNumber(), 657, 470);

            g.setFont(new Font("微软雅黑",Font.BOLD,55));
            g.drawString(clanData.getClanName(), 105, 635);
            g.drawString(clanData.getClanTag(), 105, 742);
            width = I.getWidth();
            height = I.getHeight() + (clanData.getPlayer().size() * 130) -100 ;
            BufferedImage cocImageAll = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = cocImageAll.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setBackground(new Color(230,230,230));
            g.clearRect(0, 0, width, cocImageAll.getHeight());
            g.drawImage(I, 0, 0,width,I.getHeight(),null);

            BufferedImage allList = ImageIO.read(new File(ConfigParameter.filePath_CocAll+"allList.png"));
            height =I.getHeight() - 465;
            for (int j = 0; j < clanData.getPlayer().size(); j++) {
                ClanAllListPlayer player = clanData.getPlayer().get(j);

                g.drawImage(allList, 0, height,width,220,null);
                g.setColor(Color.black);
                g.setFont(new Font("微软雅黑",Font.BOLD,35));
                g.drawString(player.getName(), 120, height+110);
                g.setColor(Color.gray);
                g.drawString(player.getTag(), 278, height+150);
                g.drawString(player.getTrophy(), 150, height+150);

                if(player.getName().length()>12) {
                    g.setFont(new Font("微软雅黑",Font.BOLD,20));
                }else {
                    g.setFont(new Font("微软雅黑",Font.BOLD,35));
                }
                g.setColor(Color.black);
                BufferedImage image3 = ImageMapCache.getImage(player.getLeagueId());
                if(image3!=null) {
                    g.drawImage(image3, 8, height+65, 100, 100, null);
                }
                BufferedImage image = ImageMapCache.getImage("Lv"+player.getTownHallLevel());
                if(image!=null) {
                    g.drawImage(image, 582, height+73, 80, 80, null);

                }
                BufferedImage image2 = ImageMapCache.getImage("BH"+player.getBuilderHallLevel());
                if(image2!=null) {
                    g.drawImage(image2, 832, height+73, 80, 80, null);
                }

                g.setFont(new Font("STENCIL STD",Font.BOLD,38));
                g.setColor(Color.white);
                g.drawString(player.getTownHallLevel(), 670, height+130);
                g.drawString("Lv."+player.getBuilderHallLevel(), 930, height+130);
                g.drawString(player.getNumber()+"%", 1193, height+130);

                //男王
                if(player.getBarbarianKing()!=null) {
                    if(player.getBarbarianKings()) {
                        g.setColor(new Color(255,215,0));
                    }else {
                        g.setColor(Color.white);
                    }
                    g.drawString(player.getBarbarianKing(), 1449, height+130);
                }else {
                    g.setColor(Color.white);
                    g.fillRect(1326, height+65, 205, 100);
                }
                //女王
                if(player.getArcherQueen()!=null) {
                    if(player.getArcherQueens()) {
                        g.setColor(new Color(255,215,0));
                    }else {
                        g.setColor(Color.white);
                    }
                    g.drawString(player.getArcherQueen(), 1645, height+130);
                }else {
                    g.setColor(Color.white);
                    g.fillRect(1528, height+65, 205, 100);
                }
                //咏王
                if(player.getGrandWarden()!=null) {
                    if(player.getGrandWardens()) {
                        g.setColor(new Color(255,215,0));
                    }else {
                        g.setColor(Color.white);
                    }
                    g.drawString(player.getGrandWarden(), 1836, height+130);
                }else {
                    g.setColor(Color.white);
                    g.fillRect(1719, height+65, 205, 100);
                }
                //闰土
                if(player.getRoyalChampion()!=null) {
                    if(player.getRoyalChampions()) {
                        g.setColor(new Color(255,215,0));
                    }else {
                        g.setColor(Color.white);
                    }
                    g.drawString(player.getRoyalChampion(), 2040, height+130);
                }else {
                    g.fillRect(1915, height+65, 205, 100);
                }
                List<String> labels2 = player.getLabels();
                Integer widthLabels = 2130;
                for (String string : labels2) {
                    g.drawImage(ImageMapCache.getImage(string), widthLabels, height+70, 80,80,null);
                    widthLabels+=90;
                }
                height+= 130;
            }
            g.setFont(new Font("微软雅黑",Font.BOLD,80));
            g.setColor(Color.gray);
            g.drawString("生成时间："+ TimeUtiles.getStringDate("MM月dd日 HH时mm分"), 40, height+130);
            g.drawString("@仓鼠机器人", 40, height+310);
            g.drawString("部落配置 v1.0", 1700, height+310);
            String saveFilePath =clanData.getClanTag().substring(1,clanData.getClanTag().length());
            //ImageIO.write(cocImageAll, "png", new File(BaseConfiguration.getCqPath()+"\\data\\image\\image\\clanAll\\ClanAll"+tag+".png"));
            Thumbnails.of(cocImageAll).outputFormat("jpg").scale(1f).outputQuality(0.40f).toFile(new File(ConfigParameter.filePath_ClanAll+"\\"+saveFilePath));
            //Thumbnails.of(cocImageAll).outputFormat("jpg").scale(1f).outputQuality(0.45f).toFile(new File("G:\\酷Q\\酷Q Air\\新建文件夹\\"+TimeUtils.getStringDate("MM月ddHHmmss")));
            return new ClanResult(true,saveFilePath,ConfigParameter.filePath_ClanAll,"jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ClanResult(false,"图片生成失败。请稍后重试！");
    }

    //处理数据
    private ClanAllListClan getClanData(ClanAllListHttp clanHttp) {
        try {
            ClanAllListClan  allClan = new ClanAllListClan();
            JSONObject clan = clanHttp.getClan();
            allClan.setClanName(clan.getString("name"));
            allClan.setClanTag(clan.getString("tag"));
            allClan.setClanPoints(clan.getString("clanPoints"));
            allClan.setClanVersusPoints(clan.getString("clanVersusPoints"));
            //联赛等级
            JSONObject warLeague = clan.getJSONObject("warLeague");
            String warLeagueId = warLeague==null?null:warLeague.getString("id");
            allClan.setNumber(WarLeagueEnum.getName(warLeagueId));
            //个性标签
            List<String> clanLabelsList = new ArrayList<>();//
            JSONArray clanLabels = clan.getJSONArray("labels");
            for (int i = 0; i < clanLabels.size(); i++) {
                clanLabelsList.add("clanid"+clanLabels.getJSONObject(i).getString("id"));
            }
            //获取玩家信息
            allClan.setLabels(clanLabelsList);
            List<ClanAllListPlayer> clanPlayerList = new ArrayList<>();

            Integer[][] townHallLevel = new Integer[9][10];
            Integer maxNumber = ConfigParameter.ClanPlayermax;
            for (JSONObject memberPlayer : clanHttp.getMemberPlayer()) {
                ClanAllListPlayer player = new ClanAllListPlayer();
                player.setName(memberPlayer.getString("name"));
                player.setTag(memberPlayer.getString("tag"));
                player.setTrophy(memberPlayer.getString("trophies"));
                String playerTownHallLevel = memberPlayer.getString("townHallLevel") +(memberPlayer.getString("townHallWeaponLevel")==null?"":("-"+ memberPlayer.getString("townHallWeaponLevel")));
                player.setTownHallLevel(playerTownHallLevel);
                player.setBuilderHallLevel(memberPlayer.getString("builderHallLevel"));
                Integer t1 = CocApiAndCqCustom.getTownHallLevel(memberPlayer.getInteger("townHallLevel"));
                Integer leagueId = memberPlayer.getJSONObject("league")==null?29000000:memberPlayer.getJSONObject("league").getInteger("id");
                Integer t2 = CocApiAndCqCustom.getLeague(leagueId);
                townHallLevel[t1][t2] = (townHallLevel[t1][t2]==null?0:townHallLevel[t1][t2]) + 1;
                townHallLevel[8][9] = (townHallLevel[8][9] == null?0:townHallLevel[8][9]) + 1;
                townHallLevel[t1][9] = (townHallLevel[t1][9] == null?0:townHallLevel[t1][9]) + 1;
                townHallLevel[8][t2] = (townHallLevel[8][t2] == null?0:townHallLevel[8][t2])  + 1;
                //System.out.println(memberPlayer.getString("tag")+"大本营"+ memberPlayer.getString("townHallLevel")+"分割"+t1+"奖杯"+(memberPlayer.getJSONObject("league")==null?"无法获取":memberPlayer.getJSONObject("league").getInteger("id"))+"类型"+t2);
                player.setLeagueId("Plaryleague"+leagueId);
                JSONArray playerLabels = memberPlayer.getJSONArray("labels");
                List<String> playerLabelsList = new ArrayList<>();//
                for (int i = 0; i < (playerLabels==null?0:playerLabels.size()); i++) {
                    Integer playerLabelsId = playerLabels.getJSONObject(i).getInteger("id");
                    playerLabelsList.add("PlayersId"+playerLabelsId);
                }
                player.setLabels(playerLabelsList);
                //计算科技
                Integer sumNumber = 0;

                JSONArray troops = memberPlayer.getJSONArray("troops");
                for (int i = 0; i < troops.size(); i++) {
                    if("home".equals(troops.getJSONObject(i).getString("village"))) {
                        sumNumber +=troops.getJSONObject(i).getInteger("level");
                    }
                }

                JSONArray spells = memberPlayer.getJSONArray("spells");
                for (int i = 0; i < spells.size(); i++) {
                    sumNumber +=spells.getJSONObject(i).getInteger("level");
                }
                String divide = (new BigDecimal(sumNumber).multiply(new BigDecimal("100"))).divide(new BigDecimal(maxNumber),1, BigDecimal.ROUND_CEILING).toString();
                player.setNumber(divide);
                JSONArray heroes = memberPlayer.getJSONArray("heroes");
                for (int i = 0; i < heroes.size(); i++) {
                    JSONObject heroesObject = heroes.getJSONObject(i);
                    String heroesName = heroesObject.getString("name");
                    if("Barbarian King".equals(heroesName)) {
                        if(heroesObject.getString("level").equals(heroesObject.getString("maxLevel"))) {
                            player.setBarbarianKings(true);
                        }else {
                            player.setBarbarianKings(false);
                        }
                        player.setBarbarianKing(heroesObject.getString("level"));
                    }else if("Archer Queen".equals(heroesName)) {
                        if(heroesObject.getString("level").equals(heroesObject.getString("maxLevel"))) {
                            player.setArcherQueens(true);
                        }else {
                            player.setArcherQueens(false);
                        }
                        player.setArcherQueen(heroesObject.getString("level"));
                    }else if("Grand Warden".equals(heroesName)) {
                        if(heroesObject.getString("level").equals(heroesObject.getString("maxLevel"))) {
                            player.setGrandWardens(true);
                        }else {
                            player.setGrandWardens(false);
                        }
                        player.setGrandWarden(heroesObject.getString("level"));
                    }else if("Royal Champion".equals(heroesName)) {
                        if(heroesObject.getString("level").equals(heroesObject.getString("maxLevel"))) {
                            player.setRoyalChampions(true);
                        }else {
                            player.setRoyalChampions(false);
                        }
                        player.setRoyalChampion(heroesObject.getString("level"));
                    }
                }
                clanPlayerList.add(player);
            }
            Collections.sort(clanPlayerList, new sortById());
            allClan.setPlayer(clanPlayerList);
            allClan.setTownHallLevel(townHallLevel);
            return allClan;
        }catch (Exception e){
            return null;
        }

    }
    class sortById implements Comparator<Object>{

        public int compare(Object o1, Object o2) {
            if(Double.valueOf(((ClanAllListPlayer)o1).getNumber())<Double.valueOf(((ClanAllListPlayer)o2).getNumber()))
                return 1;
            return -1;
        }

    }



}
