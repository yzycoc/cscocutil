package com.yzycoc.from;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-24 12:51
 * @Version 1.0
 **/
@Data
public class Dom4jResult {
    @ApiModelProperty(value = "发送QQ号")
    private String qq;
    @ApiModelProperty(value = "发送内容")
    private String msg;

    private Boolean success;
}
