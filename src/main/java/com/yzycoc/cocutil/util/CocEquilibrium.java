package com.yzycoc.cocutil.util;


import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.custom.HttpRequest;
import com.yzycoc.util.RedisUtil;
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

    public AjaxHttpResult get(String tagCode, ClanApiHttp api, Boolean Istag){
        if(Istag!=null&&Istag) {
            Boolean istag = isTag(tagCode);
            if(istag==false) {
                return new AjaxHttpResult(false,"请输入正确的标签！");
            }
            tagCode = tagCode.toUpperCase();
        }
        String url = api.getPath()+tagCode;
        AjaxHttpResult sendGetCoc = HttpRequest.cocHttp(url, redisUtil, 5000, 5000, api.getTime());
        if(sendGetCoc.getSuccess()) {
            /*if(!"获取缓存".equals(sendGetCoc.getErrorMsg())) {
                //保存数据 -- 暂时不做了
            }*/
            return sendGetCoc;
        }else {
            if(sendGetCoc.getErrorMsg()!=null) {
                AjaxHttpResult sqlTag = SqlTag(sendGetCoc.getErrorMsg(),tagCode,api);

            }
        }
        return new AjaxHttpResult();
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
            if(api == ClanApiHttp.Clan) {


            }

        }
        return coc;
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
}
