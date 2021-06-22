package com.yzycoc.cocutil.service.accomplish.common;

import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.result.clanAllCollect.DataProcessing;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-23 12:00
 * @Version 1.0
 **/
public class ClanAllCollect {
    /***
     * 数据处理
     * @param clans
     * @param palyer
     * @return
     */
    public static DataProcessing dataProcessing(JSONObject clans, List<JSONObject> palyer) {
        try {
            /****
             * 设置最初的变量
             */
            Map<String,Integer> maptrophies = new HashMap<String,Integer>();//主世界奖杯分布
            Map<String,Integer> mapbuilderHallLevel = new HashMap<String,Integer>();//主世界奖杯分布
            Map<String,Integer> mapHallLevelMap = new HashMap<String,Integer>();//主世界大本营分布
            String dondate[][] = {{"无","-","0"},{"无","-","0"},{"无","-","0"}};//捐兵前三的用户
            //获取部落捐兵总和         长老        大本营总
            int donationssum = 0,
                    coLeader = 0,//副首领
                    admin = 0,//长老
                    townHallLevelsum = 0,//大本营总
                    tro = 0,//现主世界奖杯总
                    versusTrophiessum = 0,	//夜世界奖杯数
                    expLevelsum = 0,//经验等级
                    attackWins= 0;//部落进攻胜利总场次
            ;
            String MaxexpLevel[] = {"无","-","0"};//部落等级最高

            String maxTrophies[][] = {{"无","-","0"},{"无","-","0"}};//部落等级最高
            /***
             * 处理数据保存到 DataProcessing 对象里取
             */
            DataProcessing data = new DataProcessing();
            //循环遍历所有玩家信息
            for (int i = 0; i < palyer.size(); i++) {
                JSONObject play = palyer.get(i);
                //查询各部落 1.杯段 ，然后塞入到 trophies
                String troph = CocApiAndCqCustom.trophies(play.getString("trophies"));
                Integer trophiessum = maptrophies.get(troph) == null?1:(maptrophies.get(troph)+1);
                maptrophies.put(troph, trophiessum);

                //查询各夜世界大本营等级排序情况
                Integer builderHallLevelsum = mapbuilderHallLevel.get(play.getString("builderHallLevel")) == null ? 1:(mapbuilderHallLevel.get(play.getString("builderHallLevel"))+1);
                mapbuilderHallLevel.put(play.getString("builderHallLevel"),builderHallLevelsum);

                //查询各主世界大本营等级排序情况
                String town = play.getString("townHallLevel");
                if("12".equals(town)||"13".equals(town)||"14".equals(town)) {
                    town = town + play.getString("townHallWeaponLevel");
                }
                mapHallLevelMap.put(town,mapHallLevelMap.get(town)==null?1:(mapHallLevelMap.get(town)+1));
                //处理捐兵前三的用户  + 获取部落捐兵总和
                Integer donations = play.getInteger("donations")==null?0:play.getInteger("donations");
                donationssum +=donations;//获取部落捐兵总和
                Integer don_1 = Integer.valueOf(dondate[0][2]);//取数据库捐兵数--最低捐兵
                Integer don_2 = Integer.valueOf(dondate[1][2]);//取数据库捐兵数--
                Integer don_3 = Integer.valueOf(dondate[2][2]);//取数据库捐兵数--最高捐兵
                if(donations>don_3) {
                    dondate[0][0] = dondate[1][0];
                    dondate[0][1] = dondate[1][1];
                    dondate[0][2] = dondate[1][2];
                    dondate[1][0] = dondate[2][0];
                    dondate[1][1] = dondate[2][1];
                    dondate[1][2] = dondate[2][2];
                    dondate[2][0] = play.getString("name");
                    dondate[2][1] = play.getString("tag");
                    dondate[2][2] = play.getString("donations");
                }else if(donations<don_3&&donations>don_2) {
                    dondate[0][0] = dondate[1][0];
                    dondate[0][1] = dondate[1][1];
                    dondate[0][2] = dondate[1][2];
                    dondate[1][0] = play.getString("name");
                    dondate[1][1] = play.getString("tag");
                    dondate[1][2] = play.getString("donations");
                }else if(donations>don_1&&donations<don_2) {
                    dondate[0][0] = play.getString("name");
                    dondate[0][1] = play.getString("tag");
                    dondate[0][2] = play.getString("donations");
                }

                //部落等级最高
                Integer MaxexpLevelIf = Integer.valueOf(MaxexpLevel[2]==null?"0":MaxexpLevel[2]);
                Integer expLevelMaxinteger = play.getInteger("expLevel")==null?0:play.getInteger("expLevel");
                if(MaxexpLevelIf<expLevelMaxinteger) {
                    MaxexpLevel[0] = play.getString("name");
                    MaxexpLevel[1] = play.getString("tag");
                    MaxexpLevel[2] = play.getString("expLevel");
                }
                //获取副首领 长老个数
                String role = play.getString("role");
                if("coLeader".equals(role)) {
                    coLeader ++;
                }else if("admin".equals(role)) {
                    admin ++;
                }
                //奖杯最高的用户
                Integer trophi = play.getInteger("trophies");
                if(trophi>Integer.valueOf(maxTrophies[0][2])) {
                    maxTrophies[0][0] = play.getString("name");
                    maxTrophies[0][1] = play.getString("tag");
                    maxTrophies[0][2] = play.getString("trophies");
                }
                Integer versusTrop = play.getInteger("versusTrophies");
                if(versusTrop>Integer.valueOf(maxTrophies[1][2])) {
                    maxTrophies[1][0] = play.getString("name");
                    maxTrophies[1][1] = play.getString("tag");
                    maxTrophies[1][2] = play.getString("versusTrophies");
                }
                //统计部落大本营总共多少本
                Integer townHallLevel = play.getInteger("townHallLevel");
                townHallLevelsum +=townHallLevel;
                //现主世界奖杯总
                tro += play.getInteger("trophies");
                //夜世界奖杯数
                versusTrophiessum += play.getInteger("versusTrophies");
                //经验等级
                expLevelsum += play.getInteger("expLevel");
                //进攻胜利场次
                attackWins += play.getInteger("attackWins");

            }
            //存入捐兵前三
            data.setDonations(dondate);
            //将Map的值 转储到数组中，变成有序的   夜世界大本排行
            String[][] builderHallLevel = data.getBuilderHallLevel();
            for (int i = 0; i < builderHallLevel.length; i++) {
                Integer variable = mapbuilderHallLevel.get(builderHallLevel[i][0]);
                if(variable!=null&&variable>0) {
                    builderHallLevel[i][1] = variable.toString();
                }
            }
            data.setBuilderHallLevel(builderHallLevel);
            //主世界大本排行
            String[][] townHallLevel = data.getTownHallLevel();
            for (int i = 0; i < townHallLevel.length; i++) {
                Integer variable = mapHallLevelMap.get(townHallLevel[i][0]);
                if(variable!=null&&variable>0) {
                    townHallLevel[i][1] = variable.toString();
                }
            }
            data.setTownHallLevel(townHallLevel);

            //奖杯排行
            String[][] trophies = data.getTrophies();
            for (int i = 0; i < trophies.length; i++) {
                Integer variable = maptrophies.get(trophies[i][0]);
                if(variable!=null&&variable>0) {
                    trophies[i][1] = variable.toString();
                }
            }
            int size = palyer.size();
            //计算平均经验等级
            double exp = expLevelsum / size;
            String expSum = doubleString(exp);
            String townHalSum = doubleString((double)townHallLevelsum / size);
            String attackWinsSum = doubleString((double)attackWins / size);
            String troSum = doubleString((double)tro / size);
            String versusTrophiesSum = doubleString((double)versusTrophiessum / size);
            data.setExpSum(expSum);
            data.setTownHalSum(townHalSum);
            data.setAttackWinsSum(attackWinsSum);
            data.setTroSum(troSum);
            data.setVersusTrophiesSum(versusTrophiesSum);

            data.setTrophies(trophies);
            data.setMaxexpLevel(MaxexpLevel);
            data.setMaxTrophies(maxTrophies);
            data.setDonationssum(donationssum);//捐兵总和
            data.setCoLeader(coLeader);//副首领
            data.setAdmin(admin);//长老
            return data;
        }catch (Exception e){
            return null;
        }
    }
    private static String doubleString(double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(number);
    }

}
