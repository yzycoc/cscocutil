package com.yzycoc.cocutil.service.accomplish.yq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import com.yzycoc.custom.*;
import com.yzycoc.custom.result.AjaxHttpResult;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cscocutil
 * @description: 获取鱼情官网相关信息，发送HTTP相关请求
 * @author: yzy
 * @create: 2020-10-27 16:14
 * @Version 1.0
 **/
public class YqGetHtml {


    /***
     * 获取鱼情 页面源代码
     * 获取变化部分的HTML
     * 合成曲线图
     * @return
     */
    public String getYqStartHtml() {
        Map<String,Object> data = new HashMap<String,Object>();
        AjaxHttpResult ajaxHttpResult = HttpRequest.doGet("http://clashofclansforecaster.com/", data);
        String sendTxtGet = ajaxHttpResult.getErrorMsg();
        String htmls = "<script type=\"text/javascript\">"+sendTxtGet.substring(sendTxtGet.indexOf("var adblock = true;"), sendTxtGet.indexOf("(function(i,s,o,g,r,a,m)") - 10)+"</body></html>";
        htmls = htmls.replaceAll("adframe.js", "http://clashofclansforecaster.com/adframe.js");
        return htmls;
    }

    /***
     * 获取鱼情相关信息
     * 详细数据
     * @return
     */
    public YuQing getYuQingEntity(YuQingService service) {
        YuQing y = new YuQing();
        JSONObject json =null ;
        try {
            Map<String,Object> data = new HashMap<String,Object>();
            AjaxHttpResult ajaxHttpResult = HttpRequest.doGet("http://www.clashofclansforecaster.com/STATS.json", data);
            if(!ajaxHttpResult.getSuccess()){
                return null;
            }
            json = ajaxHttpResult.getData();
        } catch (Exception e) {
            return null;
        }
        JSONObject jsonx = json.getJSONObject("currentLoot");
        Double pf = json.getDouble("lootIndexString");
        Integer sout =pf.intValue();

        String valueOf = String.valueOf(pf);
        if("10.0".equals(valueOf)) {
            y.setPf("10");//当前鱼情评分
        }else {
            y.setPf(valueOf);
        }
        y.setWordZlp(jsonx.getString("lootMinutes"));//每分钟有效的战利品數
        y.setWordBh(jsonx.getString("lootMinuteChange"));//此刻的净变化:

        Integer totalPlayers = jsonx.getInteger("totalPlayers");//总玩家
        Integer playersOnline = jsonx.getInteger("playersOnline");//在线玩家
        Integer playersOnlineChange = jsonx.getInteger("playersOnlineChange");//在线人数变化情况

        y.setWordZx(String.valueOf(playersOnline));//在线
        y.setWordZxBh(String.valueOf(playersOnlineChange));//在线变化趋势
        y.setWordLx(String.valueOf(totalPlayers-playersOnline));//离线
        y.setWordLxBH(String.valueOf(Math.negateExact(playersOnlineChange)));//离线变化趋势
        y.setWordHd(jsonx.getString("shieldedPlayers"));//有护盾
        y.setWordHdBh(jsonx.getString("shieldedPlayersChange"));//有护盾变化趋势
        y.setWordKgj(jsonx.getString("attackablePlayers"));//可攻击
        y.setWordKgjBh(jsonx.getString("attackablePlayersChange"));//可攻击变化趋势

        JSONArray object = (JSONArray) json.getJSONArray("regionStats");
        for (int i = 0; i < object.size(); i++) {
            JSONArray jsonArray = object.getJSONArray(i);
            String C_N = jsonArray.getString(1);
            if(C_N.equals("China")) {
                Integer CN_Sum = jsonArray.getInteger(5);//总人数
                Integer Cn_Zx = jsonArray.getInteger(6);//在线玩家
                Integer Cn_Zx_bh = jsonArray.getInteger(7);//在线玩家变化趋势
                y.setCnZlp(jsonArray.getString(8));//每分钟变化趋势
                y.setCnBh(jsonArray.getString(9));//每分钟战利品变化趋势
                y.setCnZx(String.valueOf(Cn_Zx));//中国在线
                y.setCnZxbh(String.valueOf(Cn_Zx_bh));//中国玩家在线变化趋势
                y.setCnLx(String.valueOf(CN_Sum-Cn_Zx));//中国离线
                y.setCnLXBh(String.valueOf(Math.negateExact(Cn_Zx_bh)));//中国离线玩家变化
                y.setCnYhd(jsonArray.getString(10));//中国有护盾
                y.setCnYhdBh(jsonArray.getString(11));//中国有护盾变化趋势
                y.setCnKgj(jsonArray.getString(12));//中国可攻击人数
                y.setCnKgjBh(jsonArray.getString(13));//中国可进攻ATTCKABLE浮动
            }
        }
        //获取情况
        JSONObject jsonObject = json.getJSONObject("forecastMessages");
        String string = jsonObject.getString("chinese-simp");
        String[] toyuqing = toyuqing(string);

        y.setTs1(toyuqing[0]);//当前状态提示
        y.setTs2(toyuqing[1]);//提示状态1
        y.setTs3(toyuqing[2]);//提示状态2
        y.setTime(TimeUtiles.getStringDate("HH时mm分"));//获取时间
        y.setJy(jsonx.getString(""));//打鱼的建议
        //获取语言评价
        String yqjy = service.getYqjy(String.valueOf(sout));
        y.setYj(yqjy);
        return y;
    }

    /***
     * 解析
     * @param html
     * @return
     */
    public static String[] toyuqing(String html) {
        String[] remsg = {"","",""};
        String[] split = html.split("<b>");
        String regEx="\\D";
        for (int o = 0; o < split.length; o++) {
            String string = split[o];
            if(string.indexOf("</b>")!=-1) {
                String[] split2 = string.split("小时");
                String substring = "";
                if(string.indexOf("分钟")!=-1) {
                    for (int i = 0; i < split2.length; i++) {
                        Pattern p= Pattern.compile(regEx);
                        Matcher m=p.matcher(split2[i]);
                        String result=m.replaceAll("").trim();
                        Character ch=result.charAt(0);
                        substring += split2[i].substring(split2[i].indexOf(ch), split2[i].indexOf(ch)+2);
                        if(split2.length==2) {
                            if(i==0) {
                                substring +="小时";
                            }else {
                                substring +="分钟";
                            }
                        }
                        if(split2.length==1) {
                            substring +="分钟";
                        }
                    }
                }else {
                    substring +="不久之";
                }

                String satate = string.substring(0, string.indexOf("</b>"));
                substring = substring.replaceAll(" ", "");
                if(o == 1) {
                    remsg[0] = "现在鱼情状态为 "+satate+" ，将持续 "+substring+" 。";
                }else if(o==2) {
                    remsg[1] = "预计 "+substring+" 后状态转为 "+satate+"  。";
                }else if(o==3) {
                    remsg[2] = "预计 "+substring+" 后状态转为 "+satate+" 。";
                }
            }
        }
        return remsg;
    }
}
