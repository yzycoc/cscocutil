package com.yzycoc.cocutil.SQLMy.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-18 15:00
 * @Version 1.0
 **/
@Data
@TableName("lexicon")
public class MyLexicon extends BaseEntity<Integer> {

    /** 关键词 **/
    private String antistop;
    /** 应答 **/
    private String response;
    /** 类型 模糊查找，精确查找 **/
    private String type;
    /** 顺序 暂时不启用  **/
    private String number;
    /** 私聊，还是群里回答他 **/
    private String code;
    /** 是否启用 **/
    private Boolean isuser;
    /** QQ群号码 **/
    private String qunnumber;
    /** 记录使用次数 **/
    private Integer sum;



}
