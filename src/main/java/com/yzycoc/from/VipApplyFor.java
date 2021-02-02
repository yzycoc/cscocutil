package com.yzycoc.from;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: Vip申请
 * @author: yzy
 * @create: 2021-01-22 16:47
 * @Version 1.0
 **/
@Data
public class VipApplyFor {
    @ApiModelProperty(value = "操作用户号码")
    private String userNumber;
    @ApiModelProperty(value = "需要数量")
    private Integer  number;//几个月
    @ApiModelProperty(value = "被操纵的机器人QQ")
    private String robotNumber;
}
