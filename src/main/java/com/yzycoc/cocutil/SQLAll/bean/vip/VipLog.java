package com.yzycoc.cocutil.SQLAll.bean.vip;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-24 13:00
 * @Version 1.0
 **/
@Data
public class VipLog extends BaseEntity<Integer> {
    @ApiModelProperty(value = "xml内容")
    private String msg;
    @ApiModelProperty(value = "用户QQ")
    private String qqcode;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "此记录的编号")
    private String uuid;
    @ApiModelProperty(value = "付款人QQ号")
    private String moneyNumber;
    @ApiModelProperty(value = "支付金额")
    private String money;
    @ApiModelProperty(value = "机器人QQ号")
    private String robotNumber;
    @ApiModelProperty(value = "获取的积分")
    private String score;
    @ApiModelProperty(value = "机器人红包余额")
    private String robotMoney;
}
