package com.yzycoc.cocutil.SQLAll.bean.vip;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-22 17:25
 * @Version 1.0
 **/
@Data
public class VipApplyForLog extends BaseEntity<Integer> {
    @ApiModelProperty(value = "申请的唯一编码")
    private String uuid;
    @ApiModelProperty(value = "申请用户")
    private String userNumber;
    @ApiModelProperty(value = "购买数")
    private Integer number;
    @ApiModelProperty(value = "操作的机器人")
    private String robotNumber;
    @ApiModelProperty(value = "机器人状态")
    private Boolean status;
    @ApiModelProperty(value = "付款人")
    private String newUserNumber;
    @ApiModelProperty(value = "收费机器人")
    private String newRobotNumber;
    @ApiModelProperty(value = "图片生成状态")
    private String uuidstatus;
}
