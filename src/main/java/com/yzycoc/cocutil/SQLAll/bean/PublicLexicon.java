package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.yzycoc.util.tableImage.ImageName;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-18 15:08
 * @Version 1.0
 **/
@Data
@TableName("public_lexicon")
public class PublicLexicon extends BaseEntity<Integer>{
    /** 关键词 **/
    @ImageName("关键字")
    private String antistop;
    /** 应答 **/
    @ImageName("应答")
    private String response;
    /** 类型 模糊查找，精确查找 **/
    @ImageName("模糊查找")
    private String type;
    /** 顺序 暂时不启用  **/
    @ImageName("顺序")
    private String number;
    /** 私聊，还是群里回答他 **/
    @ImageName("代码")
    private String code;
    /** 父级 */
    @ImageName("Fuji")
    private Integer publictypeId;
}

