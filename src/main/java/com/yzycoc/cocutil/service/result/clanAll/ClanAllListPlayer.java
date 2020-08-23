package com.yzycoc.cocutil.service.result.clanAll;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-22 13:52
 * @Version 1.0
 **/
public class ClanAllListPlayer {
    private String name;
    private String tag;
    private String townHallLevel;//大本营等级
    private String builderHallLevel;//夜世界大本营
    private String number;//科技百分比
    private String barbarianKing;//蛮王
    private Boolean barbarianKings = false;
    private String archerQueen;//女王
    private Boolean archerQueens = false;
    private String grandWarden;//永旺
    private Boolean grandWardens = false;
    private String royalChampion;//闰土
    private Boolean royalChampions = false;
    private List<String> labels = new ArrayList<>();//
    private String leagueId;
    private String trophy;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTownHallLevel() {
        return townHallLevel;
    }
    public void setTownHallLevel(String townHallLevel) {
        this.townHallLevel = townHallLevel;
    }
    public String getBuilderHallLevel() {
        return builderHallLevel;
    }
    public void setBuilderHallLevel(String builderHallLevel) {
        this.builderHallLevel = builderHallLevel;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getBarbarianKing() {
        return barbarianKing;
    }
    public void setBarbarianKing(String barbarianKing) {
        this.barbarianKing = barbarianKing;
    }
    public String getArcherQueen() {
        return archerQueen;
    }
    public void setArcherQueen(String archerQueen) {
        this.archerQueen = archerQueen;
    }
    public String getGrandWarden() {
        return grandWarden;
    }
    public void setGrandWarden(String grandWarden) {
        this.grandWarden = grandWarden;
    }

    public String getRoyalChampion() {
        return royalChampion;
    }
    public void setRoyalChampion(String royalChampion) {
        this.royalChampion = royalChampion;
    }
    public List<String> getLabels() {
        return labels;
    }
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    public String getLeagueId() {
        return leagueId;
    }
    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }
    public String getTrophy() {
        return trophy;
    }
    public void setTrophy(String trophy) {
        this.trophy = trophy;
    }
    public Boolean getBarbarianKings() {
        return barbarianKings;
    }
    public void setBarbarianKings(Boolean barbarianKings) {
        this.barbarianKings = barbarianKings;
    }
    public Boolean getArcherQueens() {
        return archerQueens;
    }
    public void setArcherQueens(Boolean archerQueens) {
        this.archerQueens = archerQueens;
    }
    public Boolean getGrandWardens() {
        return grandWardens;
    }
    public void setGrandWardens(Boolean grandWardens) {
        this.grandWardens = grandWardens;
    }
    public Boolean getRoyalChampions() {
        return royalChampions;
    }
    public void setRoyalChampions(Boolean royalChampions) {
        this.royalChampions = royalChampions;
    }
}
