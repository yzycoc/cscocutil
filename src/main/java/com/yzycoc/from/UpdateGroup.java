package com.yzycoc.from;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-16 22:00
 * @Version 1.0
 **/
@Data
@ApiModel(value="修改机器人绑定的群", description="修改机器人绑定的群")
public class UpdateGroup {

    @ApiModelProperty(value = "操作用户号码")
    private String userNumber;

    @ApiModelProperty(value ="robot号码")
    private String robotNumber;

    @ApiModelProperty(value = "QQ群号码")
    private String groupNumber;


}
