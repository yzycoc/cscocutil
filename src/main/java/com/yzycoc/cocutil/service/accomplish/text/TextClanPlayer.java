package com.yzycoc.cocutil.service.accomplish.text;

import com.alibaba.fastjson.*;
import com.yzycoc.cocutil.SQLClan.bean.PlayerName;
import com.yzycoc.cocutil.SQLClan.service.PlayerNameService;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.HttpClientUtils;
import com.yzycoc.custom.Utf8Util;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.util.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-24 21:48
 * @Version 1.0
 **/
public class TextClanPlayer {
    public ClanResult get(String[] split, PlayerNameService playerNameService) {
        List<PlayerName> list = new ArrayList<PlayerName>();
        try {
            if(split.length == 2) {
                String mybatislike = Utf8Util.Mybatislike(Utf8Util.stringToUnicode(split[1]));
                list = PlayerUTF(playerNameService.query().like("name", mybatislike).last(" limit 0,5 ").list());
            }else if(split.length == 4&&"分页".equals(split[2])) {
                try {
                    if(isNumericZidai(split[3])) {
                        String mybatislike = Utf8Util.Mybatislike(Utf8Util.stringToUnicode(split[1]));
                        String number = split[3];
                        Integer Num = Integer.valueOf(number);
                        list = PlayerUTF(playerNameService.query().like("name", mybatislike).last(" limit "+(Num-1)*5+",5 ").list());
                    }else {
                        return new ClanResult(false,"输入的人数请输入纯数字切大于1。");
                    }
                } catch (Exception e) {
                    return new ClanResult(false,"输入的页数存在语法错误");
                }
            }
            Map<String, String> maps = new HashMap<>();
            if(list.size() == 0) {
                if(split.length == 4 && "经验等级".equals(split[2])) {
                    if(StringUtils.isEmpty(split[3])) {
                        maps.put("minExpLevel", split[3]);
                    }
                }else if(split.length>4) {
                    return new   ClanResult(false,"参数错误，请你查看"+ ConfigParameter.http_document +"。查看查询部落名指令详情。");
                }
                maps.put("q", split[1]);
                maps.put("page", "0");
                maps.put("nameEquality", "true");
                String httpsGetTxt = HttpClientUtils.httpsGetTxt("https://api.clashofstats.com/search/players", maps);
                JSONObject playersdata = JSON.parseObject(httpsGetTxt);
                JSONArray items = playersdata.getJSONArray("items");
                if(items!=null&&items.size()>0) {
                    Integer max  = 5;
                    if(items.size()<=5) {
                        max = items.size();
                    }
                    for (int i = 0; i < max; i++) {
                        JSONObject jsons = items.getJSONObject(i);
                        PlayerName pl = new PlayerName();
                        pl.setTag(jsons.getString("tag"));
                        pl.setName(jsons.getString("name"));
                        pl.setClanname(jsons.getString("clanName"));
                        pl.setTrophies(jsons.getInteger("trophies"));
                        list.add(pl);
                    }
                }else {
                    return new ClanResult(false,"无法查询到昵称为："+split[1]+" 的玩家。");
                }
            }
            return new ClanResult(true,result(list,split[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ClanResult(false,"无法查询到昵称为："+split[1]+" 的玩家。");
    }


    private List<PlayerName> PlayerUTF(List<PlayerName> list) {
        for (int i = 0; i < list.size(); i++) {
            PlayerName playerName = list.get(i);
            playerName.setName(Utf8Util.unicodeToString(playerName.getName()));
            playerName.setClanname(Utf8Util.unicodeToString(playerName.getClanname()));
            list.set(i, playerName);
        }
        return list;
    }


    public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /***
     * 处理 列表信息  然后返回
     * @param list
     */
    /*private String result(List<PlayerName> list,String tagname) {
        StringBuffer result = new StringBuffer();
        result.append(""+tagname+" 相关村庄");
        int size = list.size();
        if(size>3) {
            size = 3;
        }
        for (int i = 0; i < size; i++) {
            PlayerName Play = list.get(i);
            String name = Play.getName();//玩家名称
            String tag = Play.getTag();
            result.append("\n\n●昵称："+name);
            result.append("\n●标签："+tag);
            Integer townHallLevel = Play.getTown();
            if(townHallLevel!=null) {
                String towlevel = Play.getTowlevel()==null?"":"-"+Play.getTowlevel();
                String bu = Play.getBuillevel()==null?"":"|夜"+Play.getBuillevel()+"本";
                result.append("\n●大本："+townHallLevel+towlevel+bu);
            }
        }
        return result.toString();
    }*/
    private String result(List<PlayerName> list,String tagname) {
        StringBuffer result = new StringBuffer();
        result.append("≮-"+tagname+" 相关村庄-≯");
        int size = list.size();
        if(size>5) {
            size = 5;
            result.append("\n获取"+list.size()+"+数据，仅显示前5条！");
        }
        for (int i = 0; i < size; i++) {
            PlayerName Play = list.get(i);
            String name = Play.getName();//玩家名称
            String clanName = Play.getClanname();//部落名称
            Integer trophies = Play.getTrophies();//部落等级
            String tag = Play.getTag();
            String exp = Play.getExp()==null?"":"("+Play.getExp()+"级)";
            result.append("\n\n●昵称："+name);
            result.append("\n●标签："+tag+exp);
            if(trophies!=null) {
                result.append("\n●奖杯："+trophies);
            }
            if(StringUtils.isNotEmpty(clanName)) {
                result.append("\n●部落："+clanName);
            }
            Integer townHallLevel = Play.getTown();
            if(townHallLevel!=null) {
                String towlevel = Play.getTowlevel()==null?"":"-"+Play.getTowlevel();
                String bu = Play.getBuillevel()==null?"":"|夜"+Play.getBuillevel()+"本";
                result.append("\n●大本："+townHallLevel+towlevel+bu);
            }
        }
        return result.toString();
    }
}
