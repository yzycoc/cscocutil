package com.yzycoc.cocutil.SQLAll.bean;

import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-25 15:45
 * @Version 1.0
 **/
@Data
public class ScoreUuid extends BaseEntity<Integer>{
    private String uuid;//兑换的UUID
    private String number;//可以兑换的数量
    private String qqcode;//经过兑换的QQ号
    private String startdate;//生成的时间
    private Boolean isUser;//是否使用
}
