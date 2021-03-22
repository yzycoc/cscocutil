package com.yzycoc.from;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="删除群授权", description="删除群授权")
public class DeleteBot {
    @ApiModelProperty(value = "操作用户号码")
    private String userNumber;

    @ApiModelProperty(value = "QQ群号码")
    private String groupNumber;

    @ApiModelProperty(value = "QQ群号码")
    private String robotNumber;
}
