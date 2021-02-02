package com.yzycoc.from;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 跟换BOT管理
 * @author: yzy
 * @create: 2020-12-17 10:08
 * @Version 1.0
 **/
@Data
@ApiModel(value="换BOT管理", description="换BOT管理")
public class UpdateBot {
    @ApiModelProperty(value = "操作用户号码")
    private String userNumber;

    @ApiModelProperty(value = "QQ群号码")
    private String groupNumber;

    @ApiModelProperty(value = "新的用户")
    private String newNumber;

}
