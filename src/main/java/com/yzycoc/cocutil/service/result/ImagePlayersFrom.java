package com.yzycoc.cocutil.service.result;

import com.yzycoc.cocutil.util.ImageMapCache;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description: 玩家信息处理后接收数据
 * @author: yzy
 * @create: 2020-08-21 21:11
 * @Version 1.0
 **/
public class ImagePlayersFrom {
    private BufferedImage clan = null;//部落图标
    private List<BufferedImage> gxbq = new ArrayList<>();//部落个性标签
    private BufferedImage grth = null;//杯段，默认无杯段
    private BufferedImage erweima  = null;
    private List<PlayleTroops> troops_home  = new ArrayList<>();//主世界兵种列表
    private List<PlayleTroops> troops_builderBase  = new ArrayList<>();//夜世界兵种列表
    private List<PlayleTroops> heroes  = new ArrayList<>();//英雄列表
    private List<PlayleTroops> spells  = new ArrayList<>();//法术列表
    private List<PlayleTroops> gcd  = new ArrayList<>();//攻城车列表
    private String clanName = "";
    private String clanTag = "";
    private String clanClanLevel = "";
    private List<String> play_one = new ArrayList<>();
    private List<String> play_two = new ArrayList<>();
    private List<String> play_three = new ArrayList<>();
    private List<String> play_five = new ArrayList<>();

    public List<String> getPlay_five() {
        return play_five;
    }

    public void setPlay_five(List<String> play_five) {
        this.play_five = play_five;
    }

    public List<String> getPlay_two() {
        return play_two;
    }

    public void setPlay_two(List<String> play_two) {
        this.play_two = play_two;
    }

    public List<String> getPlay_three() {
        return play_three;
    }

    public void setPlay_three(List<String> play_three) {
        this.play_three = play_three;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public String getClanTag() {
        return clanTag;
    }

    public void setClanTag(String clanTag) {
        this.clanTag = clanTag;
    }

    public String getClanClanLevel() {
        return clanClanLevel;
    }

    public void setClanClanLevel(String clanClanLevel) {
        this.clanClanLevel = clanClanLevel;
    }

    public BufferedImage getClan() {
        if(clan == null)
            return ImageMapCache.getImage("badgeUrlsClan");
        return clan;
    }

    public void setClan(BufferedImage clan) {
        this.clan = clan;
    }

    public List<BufferedImage> getGxbq() {
        return gxbq;
    }

    public void setGxbq(List<BufferedImage> gxbq) {
        this.gxbq = gxbq;
    }

    public BufferedImage getGrth() {
        if(grth == null)
            return ImageMapCache.getImage("Plaryleague29000000");
        return grth;
    }

    public void setGrth(BufferedImage grth) {
        this.grth = grth;
    }

    public BufferedImage getErweima() {
        if(erweima == null )
            return ImageMapCache.getImage("QRcode");
        return erweima;
    }

    public void setErweima(BufferedImage erweima) {
        this.erweima = erweima;
    }

    public List<PlayleTroops> getTroops_home() {
        return troops_home;
    }

    public void setTroops_home(List<PlayleTroops> troops_home) {
        this.troops_home = troops_home;
    }

    public List<PlayleTroops> getTroops_builderBase() {
        return troops_builderBase;
    }

    public void setTroops_builderBase(List<PlayleTroops> troops_builderBase) {
        this.troops_builderBase = troops_builderBase;
    }

    public List<PlayleTroops> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<PlayleTroops> heroes) {
        this.heroes = heroes;
    }

    public List<PlayleTroops> getSpells() {
        return spells;
    }

    public void setSpells(List<PlayleTroops> spells) {
        this.spells = spells;
    }

    public List<PlayleTroops> getGcd() {
        return gcd;
    }

    public void setGcd(List<PlayleTroops> gcd) {
        this.gcd = gcd;
    }

    public List<String> getPlay_one() {
        return play_one;
    }

    public void setPlay_one(List<String> play_one) {
        this.play_one = play_one;
    }
}
