package com.yzycoc.cocutil.SQLAll.bean;

import lombok.Data;

/**
 * @program: cscocutil
 * @description: 天使集合
 * @author: yzy
 * @create: 2021-01-13 13:58
 * @Version 1.0
 **/
@Data
public class TsIps extends BaseEntity<Integer>{
    private String name;//天使编号
    private String ip;//IP地址
    private String prot;//端口
    private String qqcode;//天使QQ号
    private String codes;//程序的
    private String image;
    private Boolean state;//天使运行状态
    private String number;//天使好友数
    private String groupNumber;//天使加入的群聊数
    private String remark;//备注
    private Integer groupstute;//群状态
    private Integer qqfriend;//


}
