package com.yzycoc.custom.result;

import com.alibaba.fastjson.JSONArray;

/**
 * @program: cscocutil
 * @description: 返回数据
 * @author: yzy
 * @create: 2020-08-02 11:12
 * @Version 1.0
 **/
public class AjaxResult {
    private Boolean success;
    private JSONArray msg;

    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public JSONArray getMsg() {
        return msg;
    }
    public void setMsg(JSONArray msg) {
        this.msg = msg;
    }

    public AjaxResult(JSONArray msg) {
        super();
        this.success=true;
        this.msg = msg;
    }

    public AjaxResult(Boolean success, JSONArray msg) {
        super();
        this.success = success;
        this.msg = msg;
    }
}
