package com.yzycoc.custom.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-23 16:29
 * @Version 1.0
 **/
@Data
public class Xlzdom4jXmlData {
    @ApiModelProperty(value = "原始文本")
    private String text;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "文本内容")
    private String val;
}
