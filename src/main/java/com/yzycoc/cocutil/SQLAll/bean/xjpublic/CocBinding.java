package com.yzycoc.cocutil.SQLAll.bean.xjpublic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: coc绑定列表
 * @author: yzy
 * @create: 2020-12-17 15:04
 * @Version 1.0
 **/
@Data
@TableName("xjpublic.coc_binding")
public class CocBinding extends BaseEntity<Integer> {
    @ApiModelProperty(value = "绑定的标签")
    private String msg;
    @ApiModelProperty(value = "绑定的用户")
    private String qqcode;
    @ApiModelProperty(value = "游戏标签")
    private String tag;
    @ApiModelProperty(value = "绑定类型")
    private String type;

    public CocBinding(Integer id, String createDate, String createName, String msg, String qqcode, String tag, String type) {
        super(id, createDate, createName);
        this.msg = msg;
        this.qqcode = qqcode;
        this.tag = tag;
        this.type = type;
    }

    public CocBinding() {
    }
}
