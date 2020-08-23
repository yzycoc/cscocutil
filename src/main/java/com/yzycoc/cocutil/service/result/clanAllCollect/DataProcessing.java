package com.yzycoc.cocutil.service.result.clanAllCollect;

import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-23 10:10
 * @Version 1.0
 **/
public class DataProcessing {

    //主世界奖杯位于那个排行处
    private String trophies[][]= {{"传奇","无"},{"泰坦","无"},{"冠军","无"},{"大师","无"},{"水晶","无"},{"金杯","无"},{"银杯","无"},{"铜杯","无"},{"无杯","无"}};
    //大本营分布情况
    private String townHallLevel[][] = {
            {"135","无","13-5"},
            {"134","无","13-4"},
            {"133","无","13-3"},
            {"132","无","13-2"},
            {"131","无","13-1"},
            {"125","无","12-5"},
            {"124","无","12-4"},
            {"123","无","12-3"},
            {"122","无","12-2"},
            {"121","无","12-1"},
            {"11","无","11本"},
            {"10","无","10本"},
            {"9","无","9本"},
            {"8","无","8本"},
            {"7","无","7本"},
            {"6","无","6本"},
            {"5","无","5本"},
            {"4","无","4本"},
            {"3","无","3本"}};
    //夜世界分布情况
    private String builderHallLevel[][] = {{"9","无"},{"8","无"},{"7","无"},{"6","无"},{"5","无"},{"4","无"},{"3","无"},{"2","无"}};
    //捐兵前三的用户
    private String donations[][] = {{"","","0"},{"","","0"},{"","","0"}};
    String MaxexpLevel[] = new String[3];
    private String maxTrophies[][] = {{"无","-","0"},{"无","-","0"}};
    private Integer donationssum = 0,//捐兵综合
            coLeader = 0,//副首领
            admin = 0//长老
                    ;

    private String expSum,//平均经验等级
            townHalSum,//平均大本营
            attackWinsSum,//平均进攻胜场
            troSum,//平均主世界奖杯
            versusTrophiesSum;//平均夜世界奖杯
    public Integer getDonationssum() {
        return donationssum;
    }

    public String getExpSum() {
        return expSum;
    }

    public void setExpSum(String expSum) {
        this.expSum = expSum;
    }

    public String getTownHalSum() {
        return townHalSum;
    }

    public void setTownHalSum(String townHalSum) {
        this.townHalSum = townHalSum;
    }

    public String getAttackWinsSum() {
        return attackWinsSum;
    }

    public void setAttackWinsSum(String attackWinsSum) {
        this.attackWinsSum = attackWinsSum;
    }

    public String getTroSum() {
        return troSum;
    }

    public void setTroSum(String troSum) {
        this.troSum = troSum;
    }

    public String getVersusTrophiesSum() {
        return versusTrophiesSum;
    }

    public void setVersusTrophiesSum(String versusTrophiesSum) {
        this.versusTrophiesSum = versusTrophiesSum;
    }

    public void setDonationssum(Integer donationssum) {
        this.donationssum = donationssum;
    }

    public Integer getCoLeader() {
        return coLeader;
    }

    public void setCoLeader(Integer coLeader) {
        this.coLeader = coLeader;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String[][] getMaxTrophies() {
        return maxTrophies;
    }

    public void setMaxTrophies(String[][] maxTrophies) {
        this.maxTrophies = maxTrophies;
    }

    public String[] getMaxexpLevel() {
        return MaxexpLevel;
    }

    public void setMaxexpLevel(String[] maxexpLevel) {
        MaxexpLevel = maxexpLevel;
    }

    public String[][] getTrophies() {
        return trophies;
    }

    public void setTrophies(String[][] trophies) {
        this.trophies = trophies;
    }

    public String[][] getTownHallLevel() {
        return townHallLevel;
    }

    public void setTownHallLevel(String[][] townHallLevel) {
        this.townHallLevel = townHallLevel;
    }

    public String[][] getBuilderHallLevel() {
        return builderHallLevel;
    }

    public void setBuilderHallLevel(String[][] builderHallLevel) {
        this.builderHallLevel = builderHallLevel;
    }

    public String[][] getDonations() {
        return donations;
    }

    public void setDonations(String[][] donations) {
        this.donations = donations;
    }

}
