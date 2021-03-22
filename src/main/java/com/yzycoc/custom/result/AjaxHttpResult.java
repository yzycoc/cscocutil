package com.yzycoc.custom.result;

import com.alibaba.fastjson.JSONObject;
import com.yzycoc.custom.TimeUtiles;

/**
 * @program: cscocutil
 * @description: Ajax请求
 * @author: yzy
 * @create: 2020-08-02 11:18
 * @Version 1.0
 **/
public class AjaxHttpResult {
    private Boolean success;
    private String errorMsg;
    private JSONObject data;
    /***
     * 数据获取的时间
     */
    private String time = TimeUtiles.getStringDate();

    public AjaxHttpResult(Boolean success, String errorMsg, JSONObject data) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public AjaxHttpResult(Boolean success, String data) {
        if(success == true){
            try {
                this.data = JSONObject.parseObject(data);
            }catch (Exception e){}
        }
        this.errorMsg = data;
        this.success = success;
    }

    public AjaxHttpResult(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }

    public AjaxHttpResult(JSONObject data) {
        this.success = true;
        this.data = data;
    }
    public AjaxHttpResult(Boolean success) {
        this.success = true;
    }
    public AjaxHttpResult() {
        super();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AjaxHttpResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}

