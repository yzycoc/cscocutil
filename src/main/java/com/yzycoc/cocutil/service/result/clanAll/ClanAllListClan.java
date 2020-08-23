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
public class ClanAllListClan {
    private String clanTag;//标签
    private String clanName;//玩家
    private String clanPoints;//奖杯
    private String clanVersusPoints;//也世界奖杯
    private String number;//联赛
    private List<String> labels = new ArrayList<>();//
    private List<ClanAllListPlayer> player = new ArrayList<>();
    private Integer[][] townHallLevel = new Integer[9][10];
    public String getClanTag() {
        return clanTag;
    }
    public void setClanTag(String clanTag) {
        this.clanTag = clanTag;
    }
    public String getClanName() {
        return clanName;
    }
    public void setClanName(String clanName) {
        this.clanName = clanName;
    }
    public String getClanPoints() {
        return clanPoints;
    }
    public void setClanPoints(String clanPoints) {
        this.clanPoints = clanPoints;
    }
    public String getClanVersusPoints() {
        return clanVersusPoints;
    }
    public void setClanVersusPoints(String clanVersusPoints) {
        this.clanVersusPoints = clanVersusPoints;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public List<String> getLabels() {
        return labels;
    }
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
    public List<ClanAllListPlayer> getPlayer() {
        return player;
    }
    public void setPlayer(List<ClanAllListPlayer> player) {
        this.player = player;
    }
    public Integer[][] getTownHallLevel() {
        return townHallLevel;
    }
    public void setTownHallLevel(Integer[][] townHallLevel) {
        this.townHallLevel = townHallLevel;
    }
}
