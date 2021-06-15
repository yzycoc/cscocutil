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
        ClanResult resutle = new ImageClanAllImage().ImageAll(tag,clanHttp,clan);
        return resutle;
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

            Integer[][] townHallLevel = new Integer[10][10];
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
                townHallLevel[9][9] = (townHallLevel[9][9] == null?0:townHallLevel[9][9]) + 1;
                townHallLevel[t1][9] = (townHallLevel[t1][9] == null?0:townHallLevel[t1][9]) + 1;
                townHallLevel[9][t2] = (townHallLevel[9][t2] == null?0:townHallLevel[9][t2])  + 1;
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
            e.printStackTrace();
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
