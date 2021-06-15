package com.yzycoc.custom.result.apply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @program: cscocutil
 * @description:
 * @author: 936642284
 * @create: 2021-03-04 18:30
 * @Version 1.0
 **/
@Data
public class AddUserForm {
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
    @ApiModelProperty(value = "用户QQ")
    private String userName;
    @ApiModelProperty(value = "群名称")
    private String groupName;
    @ApiModelProperty(value = "用户头像")
    private String userImage;

    public String getUserName() {
        if(StringUtils.isEmpty(userName)){
            return userNumber;
        }
        return userName;
    }

    public String getGroupName() {
        if(StringUtils.isEmpty(groupName)){
            return groupNumber;
        }
        return groupName;
    }

    public String getUserImage() {
        if(StringUtils.isEmpty(userImage)){
            return "http://q.qlogo.cn/g?b=qq&s=100&nk="+userNumber;
        }
        return userImage;
    }
}
