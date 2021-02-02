package com.yzycoc.from;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 返回数据
 * @author: yzy
 * @create: 2021-01-22 16:58
 * @Version 1.0
 **/
@Data
public class VipApplyForResult {
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    @ApiModelProperty(value = "收费金额")
    private Integer cost;

    @ApiModelProperty(value = "uuid")
    private String uuid;

}
