package com.yzycoc.cocutil.SQLClan.controller;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-24 23:13
 * @Version 1.0
 **/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.yzycoc.cocutil.SQLClan.bean.Cocclan;
import com.yzycoc.cocutil.SQLClan.mapper.CocclanMapper;
import com.yzycoc.custom.SpringContextUtil;
import com.yzycoc.custom.Utf8Util;
public class CocClanSave implements Runnable{
    private static CocclanMapper service = SpringContextUtil.getBean(CocclanMapper.class);

    JSONObject json;
    public CocClanSave(String json) {
        super();
        this.json =JSONObject.parseObject(json);
    }
    @Override
    public void run() {
        long startTime=System.currentTimeMillis();
        try {
            String name = Utf8Util.stringToUnicode(json.getString("name"));//名称乱码
            json.put("name",name);

            String description = Utf8Util.stringToUnicode(json.getString("description"));//部落公告
            json.put("description",description);

            JSONArray memberList = (JSONArray) json.getJSONArray("memberList").clone();
            for (int i = 0; i < memberList.size(); i++) {
                JSONObject member = memberList.getJSONObject(i);
                String Clan_Play_Name = Utf8Util.stringToUnicode(member.getString("name"));
                member.put("name", Clan_Play_Name);
            }
            json.put("memberList", memberList);

            Cocclan coc = new Cocclan();
            coc.setName(name);
            coc.setTag(json.getString("tag"));
            coc.setLevel(json.getInteger("clanLevel"));
            coc.setMembers(json.getInteger("members"));
            JSONObject warLeague = json.getJSONObject("warLeague");
            if(warLeague!=null) {
                coc.setLeague(warLeague.getInteger("id"));
            }
            JSONObject location = json.getJSONObject("location");
            if(location!=null) {
                coc.setLocation(location.getInteger("id"));
            }
            coc.setPoints(json.getInteger("clanPoints"));
            coc.setVerpoints(json.getInteger("clanVersusPoints"));
            coc.setJson(json.toString());
            service.saves(coc);
            json.fluentClear();
            json.clear();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("cocAll保存部落数据失败！");
        } finally {
            System.out.println("部落数据保存结束!耗时"+(System.currentTimeMillis() - startTime)+"ms");
        }
    }

}

