package com.yzycoc.cocutil.service.accomplish.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.accomplish.common.ClanListAll;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.service.result.ClanStatistics;
import com.yzycoc.cocutil.service.result.clanAll.ClanAllListHttp;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.WarLeagueEnum;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.ErweimaQRCodeUtil;
import com.yzycoc.util.ImageUtils;
import com.yzycoc.util.tableImage.ImageTable;
import com.yzycoc.util.tableImage.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description: 部落统计
 * @author: yao
 * @create: 2021-03-28 16:08
 * @Version 1.0
 **/
public class ImageClanStatistics {

    public ClanResult get(String tag, String type, CocEquilibrium cocEquil) {
        if(type == null) {
            type = "大本营";
        }
        ClanAllListHttp clanHttp = ClanListAll.ClanHttp(tag,cocEquil);
        if(!clanHttp.getSuccess()) {
            return new ClanResult(false, clanHttp.getResult());
        }
        List<ClanStatistics> data = this.getData(clanHttp.getMemberPlayer());
        String saveFilePath = tag + type;

        ClanResult result = ImageClanHc(data,saveFilePath,clanHttp.getClan(),clanHttp.getClanImage());
        return result;
    }

    /***
     * 合成图片
     * @param data
     * @param saveFilePath
     * @param clan
     * @param clanImage
     * @return
     */
    private ClanResult ImageClanHc(List<ClanStatistics> data, String saveFilePath, JSONObject clan, BufferedImage clanImage) {
        try {
            List<List<String>> table = new ImageTable<ClanStatistics>().table(data);
            ImageUtil imageUtil = new ImageUtil();
            BufferedImage bufferedImage = imageUtil.tableIamge(table);
            BufferedImage  image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight() + 200, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.fillRect(0, 0, image.getWidth(), image.getHeight());
            //进行二维码计算
            g.drawImage(ErweimaQRCodeUtil.createImage(ConfigParameter.HttpUrl+"", ConfigParameter.file_QRcode, true), 0, 0 ,200,200, null);
            g.drawImage(clanImage, 300, 0 ,200,200, null);
            Font f = new Font("微软雅黑", Font.BOLD, 30);
            g.setColor(Color.black);
            g.setFont(f);
            g.drawString("进网站",210,35);
            g.drawString("可导出",210,75+40);
            g.drawString("Excel",210,115+80);
            g.drawImage(bufferedImage, 0, 200, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
            //
            g.drawString("部落名："+clan.getString("name"),520,35);
            g.drawString("部落标签："+clan.getString("tag"),520,75);
            JSONObject warLeague = clan.getJSONObject("warLeague");
            if(warLeague!=null) {
                g.drawString("联赛等级："+WarLeagueEnum.getName(warLeague.getString("id")),520,115);
            }else{
                g.drawString("联赛等级：未定级",520,115);
            }
            g.drawString("部落人数："+clan.getString("members"),520,155);
            StringBuffer txt = new StringBuffer();
            txt.append("部落等级："+clan.getString("clanLevel"));
            txt.append("级  类型："+CocApiAndCqCustom.CocTpe(clan.getString("type")));
            txt.append( "   所需奖杯：" + clan.getString("requiredTrophies"));
            Boolean isWarLogPublic = clan.getBoolean("isWarLogPublic");
            if(isWarLogPublic){
                txt.append( "   战争日志：公开");
            }else{
                txt.append( "   战争日志：未公开");
            }
            txt.append( "   部落战：胜/平/败|"+JSONString(clan,"warWins")+"/"+JSONString(clan,"warTies")+"/"+JSONString(clan,"warLosses"));
            g.drawString(txt.toString(),520,195);
            Thumbnails.of(image).outputFormat("jpg").scale(1f).outputQuality(1f).toFile(new File(ConfigParameter.filePath_Statistics+"\\"+saveFilePath));
            return new ClanResult(true,saveFilePath, ConfigParameter.filePath_Statistics,"jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ClanResult(false,"出现错误，请稍后重试！");
    }



    /***
     * 合成数据
     * @param memberPlayer
     * @return
     */
    private List<ClanStatistics> getData(List<JSONObject> memberPlayer) {
        List<ClanStatistics> result = new ArrayList<>();
        for (JSONObject JSON : memberPlayer) {
            ClanStatistics data = new ClanStatistics();
            data.setName(JSON.getString("name"));
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
            data.setExpLevel(JSONString(JSON,"expLevel"));
            data.setTrophies(JSONString(JSON,"trophies") +"/"+JSONString(JSON,"bestTrophies"));
            data.setWarStars(JSONString(JSON,"warStars"));
            data.setAttackWins(JSONString(JSON,"attackWins") +"/"+JSONString(JSON,"defenseWins"));
            data.setRole(CocApiAndCqCustom.CocRole(JSONString(JSON,"role")));
            data.setDonations(JSONString(JSON,"donations") +"/"+JSONString(JSON,"donationsReceived"));
            data.setVersusBattleWinCount(JSONString(JSON,"versusBattleWinCount"));
            JSONArray troops = JSON.getJSONArray("troops");
            for (int i = 0; i < troops.size(); i++) {
                JSONObject troopsJSON = troops.getJSONObject(i);
                String troopsJSONName = troopsJSON.getString("name");
                String village = troopsJSON.getString("village");
                if("home".equals(village)){
                    switch (troopsJSONName){
                        case "Barbarian":
                            data.setBarbarian(troopsJSON.getString("level"));
                            break;
                        case "Archer":
                            data.setArcher(troopsJSON.getString("level"));
                            break;
                        case "Goblin":
                            data.setGoblin(troopsJSON.getString("level"));
                            break;
                        case "Giant":
                            data.setGiant(troopsJSON.getString("level"));
                            break;
                        case "Wall Breaker":
                            data.setWallBreaker(troopsJSON.getString("level"));
                            break;
                        case "Balloon":
                            data.setBalloon(troopsJSON.getString("level"));
                            break;
                        case "Wizard":
                            data.setWizard(troopsJSON.getString("level"));
                            break;
                        case "Healer":
                            data.setHealer(troopsJSON.getString("level"));
                            break;
                        case "Dragon":
                            data.setDragon(troopsJSON.getString("level"));
                            break;
                        case "P.E.K.K.A":
                            data.setPEKKA(troopsJSON.getString("level"));
                            break;
                        case "Minion":
                            data.setMinion(troopsJSON.getString("level"));
                            break;
                        case "Hog Rider":
                            data.setHogRider(troopsJSON.getString("level"));
                            break;
                        case "Valkyrie":
                            data.setValkyrie(troopsJSON.getString("level"));
                            break;
                        case "Golem":
                            data.setGolem(troopsJSON.getString("level"));
                            break;
                        case "Witch":
                            data.setWitch(troopsJSON.getString("level"));
                            break;
                        case "Lava Hound":
                            data.setLavaHound(troopsJSON.getString("level"));
                            break;
                        case "Bowler":
                            data.setBowler(troopsJSON.getString("level"));
                            break;
                        case "Baby Dragon":
                            data.setBabyDragon(troopsJSON.getString("level"));
                            break;
                        case "Miner":
                            data.setMiner(troopsJSON.getString("level"));
                            break;
                        case "Super Barbarian":
                            data.setSuperBarbarian(troopsJSON.getString("level"));
                            break;
                        case "Super Archer":
                            data.setSuperArcher(troopsJSON.getString("level"));
                            break;
                        case "Super Wall Breaker":
                            data.setSuperWallBreaker(troopsJSON.getString("level"));
                            break;
                        case "Super Giant":
                            data.setSuperGiant(troopsJSON.getString("level"));
                            break;
                        case "Wall Wrecker":
                            data.setWallWrecker(troopsJSON.getString("level"));
                            break;
                        case "Battle Blimp":
                            data.setBattleBlimp(troopsJSON.getString("level"));
                            break;
                        case "Yeti":
                            data.setYeti(troopsJSON.getString("level"));
                            break;
                        case "Sneaky Goblin":
                            data.setSneakyGoblin(troopsJSON.getString("level"));
                            break;
                        case "Ice Golem":
                            data.setIceGolem(troopsJSON.getString("level"));
                            break;
                        case "Electro Dragon":
                            data.setElectroDragon(troopsJSON.getString("level"));
                            break;
                        case "Stone Slammer":
                            data.setStoneSlammer(troopsJSON.getString("level"));
                            break;
                        case "Inferno Dragon":
                            data.setInfernoDragon(troopsJSON.getString("level"));
                            break;
                        case "Super Valkyrie":
                            data.setSuperValkyrie(troopsJSON.getString("level"));
                            break;
                        case "Super Witch":
                            data.setSuperWitch(troopsJSON.getString("level"));
                            break;
                        case "Siege Barracks":
                            data.setSiegeBarracks(troopsJSON.getString("level"));
                            break;
                        case "Ice Hound":
                            data.setIceHound(troopsJSON.getString("level"));
                            break;
                        case "Headhunter":
                            data.setHeadhunter(troopsJSON.getString("level"));
                            break;
                        case "Super Wizard":
                            data.setSuperWizard(troopsJSON.getString("level"));
                            break;
                        case "Super Minion":
                            data.setSuperMinion(troopsJSON.getString("level"));
                            break;
                        case "Log Launcher":
                            data.setLogLauncher(troopsJSON.getString("level"));
                            break;
                    }
                }else{
                    switch (troopsJSONName){
                        case "Raged Barbarian":
                            data.setBbragedBarbarian(troopsJSON.getString("level"));
                            break;
                        case "Sneaky Archer":
                            data.setBbsneakyArcher(troopsJSON.getString("level"));
                            break;
                        case "Beta Minion":
                            data.setBbbetaMinion(troopsJSON.getString("level"));
                            break;
                        case "Boxer Giant":
                            data.setBbboxerGiant(troopsJSON.getString("level"));
                            break;
                        case "Bomber":
                            data.setBbbomber(troopsJSON.getString("level"));
                            break;
                        case "Super P.E.K.K.A":
                            data.setBbsuperPEKKA(troopsJSON.getString("level"));
                            break;
                        case "Cannon Cart":
                            data.setBbcannonCart(troopsJSON.getString("level"));
                            break;
                        case "Drop Ship":
                            data.setBbdropShip(troopsJSON.getString("level"));
                            break;
                        case "Baby Dragon":
                            data.setBbbabyDragon(troopsJSON.getString("level"));
                            break;
                        case "Night Witch":
                            data.setBbnightWitch(troopsJSON.getString("level"));
                            break;
                        case "Hog Glider":
                            data.setBbhogGlider(troopsJSON.getString("level"));
                            break;
                    }
                }
            }
            JSONArray spells = JSON.getJSONArray("spells");
            for (int i = 0; i < spells.size(); i++) {
                JSONObject troopsJSON = spells.getJSONObject(i);
                String troopsJSONName = troopsJSON.getString("name");
                switch (troopsJSONName){
                    case "Lightning Spell":
                        data.setLightningSpell(troopsJSON.getString("level"));
                        break;
                    case "Healing Spell":
                        data.setHealingSpell(troopsJSON.getString("level"));
                        break;
                    case "Rage Spell":
                        data.setRageSpell(troopsJSON.getString("level"));
                        break;
                    case "Jump Spell":
                        data.setJumpSpell(troopsJSON.getString("level"));
                        break;
                    case "Freeze Spell":
                        data.setFreezeSpell(troopsJSON.getString("level"));
                        break;
                    case "Poison Spell":
                        data.setPoisonSpell(troopsJSON.getString("level"));
                        break;
                    case "Earthquake Spell":
                        data.setEarthquakeSpell(troopsJSON.getString("level"));
                        break;
                    case "Haste Spell":
                        data.setHasteSpell(troopsJSON.getString("level"));
                        break;
                    case "Clone Spell":
                        data.setCloneSpell(troopsJSON.getString("level"));
                        break;
                    case "Skeleton Spell":
                        data.setSkeletonSpell(troopsJSON.getString("level"));
                        break;
                    case "Bat Spell":
                        data.setBatSpell(troopsJSON.getString("level"));
                        break;
                    case "Invisibility Spell":
                        data.setInvisibilitySpell(troopsJSON.getString("level"));
                        break;
                }
            }
            result.add(data);
        }
        return result;
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
