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

    /*  兵种 */
    @ImageName("野蛮人")
    private String barbarian;
    @ImageName("弓箭手")
    private String archer;
    @ImageName("巨人")
    private String giant;
    @ImageName("哥布林")
    private String goblin;
    @ImageName("炸弹人")
    private String wallBreaker;
    @ImageName("气球")
    private String balloon;
    @ImageName("法师")
    private String wizard;
    @ImageName("天使")
    private String healer;
    @ImageName("龙")
    private String dragon;
    @ImageName("皮卡")
    private String pEKKA;
    @ImageName("龙宝宝")
    private String babyDragon;
    @ImageName("矿工")
    private String miner;
    @ImageName("电龙")
    private String electroDragon;
    @ImageName("雪怪")
    private String yeti;

    @ImageName("亡灵")
    private String minion;
    @ImageName("野猪")
    private String hogRider;
    @ImageName("武神")
    private String valkyrie;
    @ImageName("石头人")
    private String golem;
    @ImageName("女巫")
    private String witch;
    @ImageName("狗")
    private String lavaHound;
    @ImageName("蓝胖")
    private String bowler;
    @ImageName("冰人")
    private String iceGolem;
    @ImageName("猎手")
    private String headhunter;

    @ImageName("超级野蛮人")
    private String superBarbarian;
    @ImageName("超级弓箭手")
    private String superArcher;
    @ImageName("超级巨人")
    private String superGiant;
    @ImageName("超级哥布林")
    private String sneakyGoblin;

    @ImageName("超级炸弹人")
    private String superWallBreaker;
    @ImageName("超级法师")
    private String superWizard;
    @ImageName("地狱龙")
    private String infernoDragon;
    @ImageName("超级亡灵")
    private String superMinion;
    @ImageName("超级武神")
    private String superValkyrie;
    @ImageName("超级女巫")
    private String superWitch;
    @ImageName("冰狗")
    private String iceHound;


    @ImageName("攻城车")
    private String wallWrecker;
    @ImageName("飞艇")
    private String battleBlimp;
    @ImageName("战争机器")
    private String stoneSlammer;
    @ImageName("攻城训练营")
    private String siegeBarracks;
    @ImageName("滚木车")
    private String logLauncher;

    @ImageName("夜->野蛮人")
    private String bbragedBarbarian;
    @ImageName("弓箭手")
    private String bbsneakyArcher;
    @ImageName("巨人")
    private String bbboxerGiant;
    @ImageName("亡灵")
    private String bbbetaMinion;
    @ImageName("炸弹人")
    private String bbbomber;
    @ImageName("龙宝宝")
    private String bbbabyDragon;
    @ImageName("炮车")
    private String bbcannonCart;
    @ImageName("女巫")
    private String bbnightWitch;
    @ImageName("骷髅飞艇")
    private String bbdropShip;
    @ImageName("皮卡")
    private String bbsuperPEKKA;
    @ImageName("飞猪")
    private String bbhogGlider;

    @ImageName("雷电")
    private String lightningSpell;
    @ImageName("治疗")
    private String healingSpell;
    @ImageName("狂暴")
    private String rageSpell;
    @ImageName("弹跳")
    private String jumpSpell;
    @ImageName("冰冻")
    private String freezeSpell;
    @ImageName("毒药")
    private String poisonSpell;
    @ImageName("地震")
    private String earthquakeSpell;
    @ImageName("快速")
    private String hasteSpell;
    @ImageName("克隆")
    private String cloneSpell;
    @ImageName("骷髅")
    private String skeletonSpell;
    @ImageName("蝙蝠")
    private String batSpell;
    @ImageName("复制")
    private String invisibilitySpell;
}
