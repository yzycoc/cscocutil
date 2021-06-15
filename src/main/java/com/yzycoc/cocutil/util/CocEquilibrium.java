package com.yzycoc.cocutil.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLClan.bean.ClanJson;
import com.yzycoc.cocutil.SQLClan.bean.PlayerJson;
import com.yzycoc.cocutil.SQLClan.controller.CocClanSave;
import com.yzycoc.cocutil.SQLClan.controller.PlayerSave;
import com.yzycoc.cocutil.SQLClan.service.ClanJsonService;
import com.yzycoc.cocutil.SQLClan.service.PlayerJsonService;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.HttpRequest;
import com.yzycoc.custom.Utf8Util;
import com.yzycoc.util.RedisUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import com.yzycoc.custom.result.AjaxHttpResult;

/**
 * @program: cscocutil
 * @description: Coc发送请求
 * @author: yzy
 * @create: 2020-08-04 21:01
 * @Version 1.0
 **/
@Component
public class CocEquilibrium {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TaskExecutor taskExecutor;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerJsonService service;

    @Autowired
    private ClanJsonService clanservice;
    public AjaxHttpResult get(String tagCode, ClanApiHttp api, Boolean Istag){
        if(Istag!=null&&Istag) {
            Boolean istag = isTag(tagCode);
            if(istag==false) {
                return new AjaxHttpResult(false,"请输入正确的标签！");
            }
            tagCode = tagCode.toUpperCase();
        }
        String url = api.getPath()+tagCode;
        AjaxHttpResult sendGetCoc = HttpRequest.cocHttp(url, redisUtil, 5000, 5000, api);
        if(sendGetCoc.getSuccess()) {
            if(!"获取缓存".equals(sendGetCoc.getErrorMsg())&& ConfigParameter.clanHttpSaveSql) {
                //保存更新数据
                taskclassify(api,sendGetCoc.getData());
            }
            return sendGetCoc;
        }else {
            //进行判定
            if(sendGetCoc.getErrorMsg()!=null) {
                //如果是因为自己原因，返回数据库数据
                AjaxHttpResult sqlTag = SqlTag(sendGetCoc.getErrorMsg(),tagCode,api);
                if(sqlTag.getSuccess()) {
                    return sqlTag;
                }
                AjaxHttpResult resultType = ResultType(sendGetCoc.getErrorMsg(), url);
                if(resultType.getSuccess()){
                    resultType.setSuccess(false);
                    return resultType;
                }
            }
        }
        log.info("第二次发送请求:"+url);
        sendGetCoc = HttpRequest.cocHttp(url, redisUtil,20000,60000,api);
        if(sendGetCoc.getSuccess()){
            if(!"获取缓存".equals(sendGetCoc.getErrorMsg())&& ConfigParameter.clanHttpSaveSql) {
                //保存更新数据
                taskclassify(api,sendGetCoc.getData());
            }
            return sendGetCoc;
        }else{
            if(sendGetCoc.getErrorMsg()!=null) {
                AjaxHttpResult resultType = ResultType(sendGetCoc.getErrorMsg(), url);
                resultType.setSuccess(false);
                return resultType;
            }else {
                return new AjaxHttpResult(false,"查询失败，无法获取游戏数据，请稍等几分钟后重新尝试！");
            }
        }
    }

    /***
     * 返回JSON数据
     * @param tag
     * @param api
     * @return
     */
    public AjaxHttpResult SqlTag(String remsg, String tag, ClanApiHttp api) {
        AjaxHttpResult coc = new AjaxHttpResult();
        coc.setSuccess(false);
        if(remsg!=null&&("403".equals(remsg)||"429".equals(remsg)||"500".equals(remsg)||"503".equals(remsg))) {
            JSONObject object = new JSONObject();
            if(api == ClanApiHttp.Clan || api == ClanApiHttp.ClanRealTime) {
                ClanJson one = clanservice.query().eq("tag", "#"+tag).one();
                if(one!=null) {
                    String json = one.getJson();
                    object = JSONObject.parseObject(json);
                    object.put("name", Utf8Util.unicodeToString(object.getString("name")));
                    object.put("description", Utf8Util.unicodeToString(object.getString("description")));
                    JSONArray memberList =object.getJSONArray("memberList");
                    for (int i = 0; i < memberList.size(); i++) {
                        JSONObject member = memberList.getJSONObject(i);
                        String Clan_Play_Name = Utf8Util.unicodeToString(member.getString("name"));
                        member.put("name", Clan_Play_Name);
                    }
                    coc.setData(object);
                    coc.setSuccess(true);
                    coc.setTime(one.getUpdatetime());
                }

            }
            if(api == ClanApiHttp.player ||  api == ClanApiHttp.playerRealTime) {
                PlayerJson one = service.query().eq("tag","#"+tag).one();
                if(one!=null) {
                    String json = one.getJson();
                    object = JSONObject.parseObject(json);
                    object.put("name", Utf8Util.unicodeToString(object.getString("name")));
                    JSONObject clan = object.getJSONObject("clan");
                    if(clan!=null) {
                        String ClanName = Utf8Util.unicodeToString(clan.getString("name"));
                        clan.put("name", ClanName);
                    }
                    coc.setTime(one.getUpdatetime());
                    coc.setData(object);
                    coc.setSuccess(true);
                }
            }

        }
        return coc;
    }
    /***
     * 获取状态
     * @param msg
     * @param URL
     * @return
     */
    public AjaxHttpResult ResultType(String msg, String URL) {
        if(msg == null) {
            return new AjaxHttpResult(false,"查询超时，请稍等几分钟等待网络环境稳地..");
        }
        switch (msg) {
            case "400":
                return new AjaxHttpResult(true, "标签错误，查询失败，建议您进入游戏直接复制标签。");
            case "403":

                log.error("COCAPI无法使用，请更换！\r\n网址：{}\r\nAPI:{}",URL,ConfigParameter.CocApi);
                return new AjaxHttpResult(true, "查询失败，因为COCAPI密钥出现问题！您可以向作者反馈哦！");
            case "404":
                return new AjaxHttpResult(true,"查询失败，标签错误，游戏接口无法获取数据！");
            case "429":
                log.error("COCAPI已被截流限制！"+URL);
                return new  AjaxHttpResult(true,"由于大量的查询数据，导致官方拒绝了我们的数据请求，您可以转告其他人，短时间内不要获取游戏数据。过段时间以后请在查询！");
            case "500":
                return new AjaxHttpResult(false,"游戏官方数据查询失败，错误码：500，官方服务器内部错误。");
            case "503":
                return new AjaxHttpResult(true,"SC官方服务器维护，请等待恢复！");
            default:
                log.error("查询失败,无法判断错误码！["+msg+"]！请更换！"+URL);
                return new AjaxHttpResult(false,"查询失败，错误代码："+msg);
        }
    }

    /***
     *  max:14	min:3
     *  判定是否是
     * @param tag
     * @return
     */
    public static Boolean isTag(String tag) {
        int length = tag.length();
        if(length>=3&&length<=14) {
            String regex = "^[a-z0-9A-Z]+$";
            return tag.matches(regex);
        }
        return false;
    }

    /**
     * 保存数据到数据库
     * @param api
     * @param object
     */
    private void taskclassify(ClanApiHttp api, JSONObject object) {
        try {
            switch (api) {
                case player:
                    taskExecutor.execute(new PlayerSave(object.toString()));
                    break;
                case Clan:
                    taskExecutor.execute(new CocClanSave(object.toString()));
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            System.out.println("储存玩家数据到CocSQL失败");
        }

    }
}
