package com.yzycoc.cocutil.service.result;

/**
 * @program: cscocutil
 * @description: 兵种数据信息
 * @author: yzy
 * @create: 2020-08-21 21:13
 * @Version 1.0
 **/
public class PlayleTroops {
    private String number;//兵种等级 放在图片上的数据
    private Boolean max;//是否是满级
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Boolean getMax() {
        return max;
    }
    public void setMax(Boolean max) {
        this.max = max;
    }
}
