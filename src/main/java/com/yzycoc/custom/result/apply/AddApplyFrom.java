package com.yzycoc.custom.result.apply;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @program: cscocutil
 * @description: 新增报名信息
 * @author: 936642284
 * @create: 2021-02-28 16:52
 * @Version 1.0
 **/
@Data
public class AddApplyFrom {

    @ApiModelProperty(value = "群号")
    private String groupNumber;
    @ApiModelProperty(value = "操作的用户")
    private String userNumber;
    @ApiModelProperty(value = "绑定的类型")
    private String type;
    @ApiModelProperty(value = "部落标签")
    private String tag;
    @ApiModelProperty(value = "唯一标识")
    private String uuid;
    @ApiModelProperty(value = "机器人QQ")
    private String robotNumber;
    @ApiModelProperty(value = "群名称")
    private String groupName;
    @ApiModelProperty(value = "群头像")
    private String groupImage;


    public String getGroupName() {
        if(StringUtils.isEmpty(groupName)){
            return groupNumber;
        }
        return groupName;
    }

    public String getGroupImage() {
        if(StringUtils.isEmpty(groupImage)){
            return "http://p.qlogo.cn/gh/"+groupNumber+"/"+groupNumber+"/0";
        }
        return groupImage;
    }
}
