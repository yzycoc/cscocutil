package com.yzycoc.cocutil.SQLClan.controller;

import com.alibaba.fastjson.JSONObject;

import com.yzycoc.cocutil.SQLClan.bean.Player;
import com.yzycoc.cocutil.SQLClan.mapper.AlibabaPlayerMapper;
import com.yzycoc.custom.SpringContextUtil;
import com.yzycoc.custom.Utf8Util;
public class PlayerSave implements Runnable{
    private static AlibabaPlayerMapper service = SpringContextUtil.getBean(AlibabaPlayerMapper.class);
    JSONObject json;
    public PlayerSave(String json) {
        super();
        this.json = JSONObject.parseObject(json);
    }
    @Override
    public void run() {
        long startTime=System.currentTimeMillis();
        try {
            Player player = new Player();
            //转json字符集以便保存
            String name = Utf8Util.stringToUnicode(json.getString("name"));
            json.put("name", name);
            player.setName(name);
            player.setTag(json.getString("tag"));
            JSONObject clan = json.getJSONObject("clan");
            if(clan!=null) {
                String ClanName = Utf8Util.stringToUnicode(clan.getString("name"));
                clan.put("name", ClanName);
                player.setClanname(ClanName);
                player.setClantag(clan.getString("tag"));
            }
            player.setExp(json.getInteger("expLevel"));
            player.setTown(json.getInteger("townHallLevel"));
            player.setTowlevel(json.getInteger("townHallWeaponLevel"));
            player.setJson(json.toString());
            player.setBuillevel(json.getInteger("builderHallLevel"));
            player.setTrophies(json.getInteger("trophies"));
            player.setBuildtrophies(json.getInteger("versusTrophies"));
            service.saves(player);
            json.fluentClear();
            json.clear();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("保存玩家数据失败！");
        }finally {
            System.out.println("玩家数据保存结束!耗时"+(System.currentTimeMillis() - startTime)+"ms");
        }

    }
}

