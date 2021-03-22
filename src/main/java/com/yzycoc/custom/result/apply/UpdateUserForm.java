package com.yzycoc.custom.result.apply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: 936642284
 * @create: 2021-03-07 11:26
 * @Version 1.0
 **/
@Data
public class UpdateUserForm {
    @ApiModelProperty(value = "机器人QQ")
    private String robotNumber;
    @ApiModelProperty(value = "群号")
    private String groupNumber;
    @ApiModelProperty(value = "用户QQ")
    private String userNumber;
    @ApiModelProperty(value = "唯一标识")
    private String uuid;
    @ApiModelProperty(value = "报名内容")
    private String msg;
    @ApiModelProperty(value = "标签")
    private String tag;
}
