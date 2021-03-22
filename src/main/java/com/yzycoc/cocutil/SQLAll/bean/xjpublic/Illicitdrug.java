package com.yzycoc.cocutil.SQLAll.bean.xjpublic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 机器人聊天 违禁词统计
 */
@Data
@TableName("xjpublic.illicitdrug")
public class Illicitdrug{

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private Integer id;
    //违禁词
    private String msg;
    //
    private String reply;
}
