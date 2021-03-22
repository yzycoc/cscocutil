package com.yzycoc.custom.result.apply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 修改规则
 * @author: 936642284
 * @create: 2021-03-06 21:35
 * @Version 1.0
 **/
@Data
public class UpdateGroupForm {
    @ApiModelProperty(value = "群号")
    private String groupNumber;
    @ApiModelProperty(value = "机器人QQ")
    private String robotNumber;
    @ApiModelProperty(value = "用户QQ")
    private String userNumber;
    @ApiModelProperty(value = "修改状态")
    private String msg;
    @ApiModelProperty(value = "唯一编码")
    private String uuid;
}
