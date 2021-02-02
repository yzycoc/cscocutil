package com.yzycoc.custom.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-23 16:23
 * @Version 1.0
 **/
public class Xlzdom4jXmlResult {
    @ApiModelProperty(value = "请求地址")
    private String httpurl;
    @ApiModelProperty(value = "请求状态")
    private String status;
    @ApiModelProperty(value = "时间")
    private String date;
    @ApiModelProperty(value = "主旨文本")
    private Xlzdom4jXmlVal val;
    @ApiModelProperty(value = "转账时间")
    private Xlzdom4jXmlData time;
    @ApiModelProperty(value = "转账留言")
    private Xlzdom4jXmlData uuid;
    @ApiModelProperty(value = "转账金额")
    private Xlzdom4jXmlData money;
    @ApiModelProperty(value = "详情")
    private String remark;

    public String getHttpurl() {
        return httpurl;
    }

    public void setHttpurl(String httpurl) {
        this.httpurl = httpurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Xlzdom4jXmlVal getVal() {
        return val;
    }

    public static void main(String[] args) {
        Xlzdom4jXmlResult result = new Xlzdom4jXmlResult();
        result.setVal("(123)1.)(936642284)fsqq");
        System.out.println(result.getVal().toString());

    }
    public void setVal(String val) {
        Xlzdom4jXmlVal xlzdom4jXmlVal = new Xlzdom4jXmlVal();
        xlzdom4jXmlVal.setText(val);
        List<String> mpedIds = new ArrayList<>();
        Pattern p = Pattern.compile("\\(\\d+\\)");
        Matcher m = p.matcher(val);
        while(m.find()){
            String mpedId = m.group().substring(1, m.group().length() - 1);
            if(!mpedIds.contains(mpedId)){
                mpedIds.add(mpedId);
            }
        }
        try {
            xlzdom4jXmlVal.setName(val.substring(0,val.indexOf(mpedIds.get((mpedIds.size()-1)))-1));
            xlzdom4jXmlVal.setQqnumber(mpedIds.get((mpedIds.size()-1)));
            xlzdom4jXmlVal.setTitle(val.substring(val.indexOf(mpedIds.get((mpedIds.size()-1))),val.length()).replace(mpedIds.get((mpedIds.size()-1))+")",""));
            this.val = xlzdom4jXmlVal;
        }catch (Exception e){
            if(mpedIds!=null&&mpedIds.size()>=1){
                xlzdom4jXmlVal.setQqnumber(mpedIds.get((mpedIds.size()-1)));
            }
            this.val = xlzdom4jXmlVal;
        }

    }


    public Xlzdom4jXmlData getTime() {
        return time;
    }

    public void setTime(String time) {
        Xlzdom4jXmlData xlzdom4jXmlData = new Xlzdom4jXmlData();
        xlzdom4jXmlData.setText(time);
        String[] split = time.split("：");
        xlzdom4jXmlData.setTitle(split[0]);
        xlzdom4jXmlData.setVal(split[1]);
        this.time = xlzdom4jXmlData;
    }

    public Xlzdom4jXmlData getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        Xlzdom4jXmlData xlzdom4jXmlData = new Xlzdom4jXmlData();
        xlzdom4jXmlData.setText(uuid);
        String[] split = uuid.split("：");
        xlzdom4jXmlData.setTitle(split[0]);
        xlzdom4jXmlData.setVal(split[1]);
        this.uuid = xlzdom4jXmlData;
    }

    public Xlzdom4jXmlData getMoney() {
        return money;
    }

    public void setMoney(String money) {
        Xlzdom4jXmlData xlzdom4jXmlData = new Xlzdom4jXmlData();
        xlzdom4jXmlData.setText(money);
        String[] split = money.split("：");
        xlzdom4jXmlData.setTitle(split[0]);
        xlzdom4jXmlData.setVal(split[1]);
        this.money = xlzdom4jXmlData;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}

