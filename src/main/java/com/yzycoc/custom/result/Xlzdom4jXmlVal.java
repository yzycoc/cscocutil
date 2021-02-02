package com.yzycoc.custom.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-23 16:27
 * @Version 1.0
 **/
@Data
public class Xlzdom4jXmlVal {
    @ApiModelProperty(value = "原始JSON")
    private String text;
    @ApiModelProperty(value = "QQ号")
    private String qqnumber;
    @ApiModelProperty(value = "QQ名")
    private String name;
    @ApiModelProperty(value = "余下的文本")
    private String title;

}
