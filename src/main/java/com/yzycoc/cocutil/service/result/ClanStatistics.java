package com.yzycoc.cocutil.service.result;

import com.yzycoc.util.tableImage.ImageName;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 部落统计需要的数据
 * @author: yao
 * @create: 2021-03-28 16:20
 * @Version 1.0
 **/
@Data
public class ClanStatistics {
    @ImageName("村庄名")
    private String name;
    @ImageName("标签")
    private String tag;
    @ImageName("大本营")
    private String townHallLevel;
    @ImageName("夜世界")
    private String builderHallLevel;
    @ImageName("蛮王")
    private String barbarianKing= "-";//蛮王
    @ImageName("女王")
    private String archerQueen= "-";;//女王
    @ImageName("守护")
    private String grandWarden = "-";;//永旺
    @ImageName("闰土")
    private String royalChampion = "-";;//闰土
    @ImageName("等级")
    private String expLevel = "-";//经验等级
    @ImageName("奖杯/最高")
    private String trophies= "-";//现主世界奖杯
    @ImageName("战星")
    private String warStars = "-";//战争的胜利之星
    @ImageName("进攻/防守")
    private String attackWins = "-";//进攻胜利场次
    @ImageName("职位")
    private String role = "-";//所在部落权限 首领副首领等
    @ImageName("捐/收")
    private String donations = "-";//捐兵
    @ImageName("对战胜场")
    private String versusBattleWinCount = "-";//对战胜率数
/*
    *//*  兵种 *//*
    private String barbarian;
    private String archer;
    private String giant;
    private String goblin;
    private String wallBreaker;
    private String balloon;
    private String wizard;
    private String healer;
    private String dragon;
    private String pEKKA;
    private String babyDragon;
    private String miner;
    private String electroDragon;
    private String yeti;

    private String minion;
    private String hogRider;
    private String valkyrie;
    private String golem;
    private String witch;
    private String lavaHound;
    private String bowler;
    private String iceGolem;
    private String headhunter;

    private String superBarbarian;
    private String superArcher;
    private String superGiant;
    private String sneakyGoblin;

    private String superWallBreaker;
    private String superWizard;
    private String infernoDragon;
    private String superMinion;
    private String superValkyrie;
    private String superWitch;
    private String iceHound;


    private String wallWrecker;
    private String battleBlimp;
    private String stoneSlammer;
    private String siegeBarracks;
    private String logLauncher;

    private String bbragedBarbarian;
    private String bbsneakyArcher;
    private String bbboxerGiant;
    private String bbbetaMinion;
    private String bbbomber;
    private String bbbabyDragon;
    private String bbcannonCart;
    private String bbnightWitch;
    private String bbdropShip;
    private String bbsuperPEKKA;
    private String bbhogGlider;

    private String lightningSpell;
    private String healingSpell;
    private String rageSpell;
    private String jumpSpell;
    private String freezeSpell;
    private String poisonSpell;
    private String earthquakeSpell;
    private String hasteSpell;
    private String cloneSpell;
    private String skeletonSpell;
    private String batSpell;
    private String invisibilitySpell;*/
}
