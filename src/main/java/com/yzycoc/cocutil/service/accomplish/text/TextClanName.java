package com.yzycoc.cocutil.service.accomplish.text;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLClan.bean.ClanName;
import com.yzycoc.cocutil.SQLClan.service.ClanNameService;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.cocutil.util.enums.CocState;
import com.yzycoc.cocutil.util.enums.WarLeagueEnum;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.*;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.util.RedisUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description: 查询部落名
 * @author: yzy
 * @create: 2020-08-23 16:29
 * @Version 1.0
 **/
public class TextClanName {

    public ClanResult get(String[] split, ClanNameService clannameService) {
        List<ClanName> list =new  ArrayList<ClanName>();
        if(split.length == 2) {//查询数据库数据
            String mybatislike = Utf8Util.Mybatislike(Utf8Util.stringToUnicode(split[1]));
            list =  clanUtf8(clannameService.query().like("name", mybatislike).last(" limit 0,5 ").list());
        }else if(split.length == 4&&"分页".equals(split[2])) {
            try {
                if(isNumericZidai(split[3])) {
                    String mybatislike = Utf8Util.Mybatislike(Utf8Util.stringToUnicode(split[1]));
                    String number = split[3];
                    Integer Num = Integer.valueOf(number);
                    list = clanUtf8(clannameService.query().like("name", mybatislike).last(" limit "+(Num-1)*5+",5 ").list());
                }else {
                    return new ClanResult(false,"输入的人数请输入纯数字切大于1。");
                }
            } catch (Exception e) {
                return new ClanResult(false,"输入的页数存在语法错误");
            }
        }
        if(list.size()==0) {
            if(split[1].length()<3) {
                return new ClanResult(false,"官方查询最低三个字！");
            }
            String urlEncode = HttpRequest.urlEncode(split[1]);
            ClanResult form = getForm(split);
            if(form.getSuccess()) {
                String url = "https://api.clashofclans.com/v1/clans?name="+urlEncode+form.getResult()+"&limit=5";
                /*url = "http://47.100.197.180:8081/coc?url=" +BackEndHttpRequest.urlEncode(url);*/
                AjaxHttpResult sendGetCoc = HttpRequest.cocHttp(url, SpringContextUtil.getBean(RedisUtil.class), 10000, 30000, ClanApiHttp.clanName);
                if(sendGetCoc.getSuccess()) {
                    JSONObject parseObject = sendGetCoc.getData();
                    JSONArray items = parseObject.getJSONArray("items");
                    for (int i = 0; i < items.size(); i++) {
                        JSONObject jsonObject = items.getJSONObject(i);
                        ClanName clanName = new ClanName();
                        clanName.setLevel(jsonObject.getInteger("clanLevel"));
                        clanName.setMembers(jsonObject.getInteger("members"));
                        clanName.setName(jsonObject.getString("name"));
                        clanName.setTag(jsonObject.getString("tag"));
                        clanName.setPoints(jsonObject.getInteger("clanPoints"));
                        JSONObject warLeague = jsonObject.getJSONObject("warLeague");
                        if(warLeague!=null) {
                            clanName.setLeague(warLeague.getInteger("id"));
                        }
                        list.add(clanName);
                    }
                }else {
                    return new ClanResult(false, new CocEquilibrium().ResultType(sendGetCoc.getErrorMsg(), url).getErrorMsg());
                }
            }else {
                return new ClanResult(form.getResult());
            }
        }
        if(list.size()==0) {
            return new ClanResult(false,"查询失败，无法查询到名称为："+split[1]+" 的部落。");
        }else {
            return new ClanResult(false,result(list,split[1]));
        }
    }




    private List<ClanName> clanUtf8(List<ClanName> list) {
        for (int i = 0; i < list.size(); i++) {
            ClanName clanName = list.get(i);
            clanName.setName(Utf8Util.unicodeToString(clanName.getName()));
            list.set(i, clanName);
        }
        return list;
    }




    private ClanResult getForm(String[] split) {
        String result = "";
        if(split.length == 4) {
            switch (split[2]) {
                case "国家":
                    String name = CocState.getAll(split[3]);
                    if(name == null) {
                        return new ClanResult(false, "无法查询到"+split[3]+"这个国家的编码。");
                    }else {
                        result = "&locationId="+name+"&limit=5";
                    }
                    break;
                case "人数":
                    try {
                        if(isNumericZidai(split[3])) {
                            result = "&minMembers="+split[3]+"&limit=5";
                        }else {
                            return new ClanResult(false,"输入的人数请输入纯数字。");
                        }
                    } catch (Exception e) {
                        return new ClanResult(false,"输入的人数存在语法错误");
                    }
                    break;
                case "奖杯":
                    try {
                        if(isNumericZidai(split[3])) {
                            result = "&minClanPoints="+split[3]+"&limit=5";
                        }else {
                            return new ClanResult(false,"输入的奖杯请输入纯数字。");
                        }
                    } catch (Exception e) {
                        return new ClanResult(false,"输入的奖杯存在语法错误");
                    }
                    break;
                case "等级":
                    try {
                        if(isNumericZidai(split[3])) {
                            result = "&minClanLevel="+split[3]+"&limit=5";
                        }else {
                            return new ClanResult(false,"输入的等级请输入纯数字。");
                        }
                    } catch (Exception e) {
                        return new ClanResult(false,"输入的奖杯存在语法错误");
                    }
                    break;
                default:
                    break;
            }
        }else if(split.length == 5) {
            try {
                if(isNumericZidai(split[3])&&isNumericZidai(split[4])) {
                    result = "&minClanLevel="+split[3]+"&maxMembers="+split[4]+"&limit=5";
                }else {
                    return new ClanResult(false, "输入的等级请输入纯数字。");
                }
            } catch (Exception e) {
                return new ClanResult(false, "输入的奖杯存在语法错误");
            }
        }else if(split.length == 2) {
            return new ClanResult(result);
        }
        if(StringUtils.isNotEmpty(result)) {
            return new ClanResult(result);
        }else {
            return new ClanResult(false, "参数错误，请你查看"+ ConfigParameter.http_document +"。查看熙酱查询部落名指令详情。");
        }
    }



    private String result(List<ClanName> list, String name2) {
        StringBuffer result = new StringBuffer();
        result.append(name2+" 查询反馈");
        int size = list.size();
        if(size>5) {
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            ClanName clanName = list.get(i);
            result.append("\n\n●"+clanName.getName());
            result.append("\n●"+clanName.getTag());
            String league = String.valueOf(clanName.getLeague());
            if(StringUtils.isNotEmpty(league)) {
                result.append("\n●"+ WarLeagueEnum.getName(league));
            }
            result.append("\n●"+clanName.getLevel()+"级|"+clanName.getMembers()+"人|"+clanName.getPoints());
        }
        return result.toString();
    }
    public static boolean isNumericZidai(String str) {
        try {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            Integer Num = Integer.valueOf(str);
            if(Num>0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
