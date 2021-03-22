package com.yzycoc.cocutil.SQLAll.bean;

import lombok.Data;

@Data
public class CsutilLog extends BaseEntity<Integer> {

    private String msg;
    private String code;
    private String qqcode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQqcode() {
        return qqcode;
    }

    public void setQqcode(String qqcode) {
        this.qqcode = qqcode;
    }
}
