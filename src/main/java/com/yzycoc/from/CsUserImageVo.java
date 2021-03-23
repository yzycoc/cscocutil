package com.yzycoc.from;

import com.yzycoc.util.tableImage.ImageName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: cscocutil -> com.yzycoc.from
 * @description:
 * @author: XinDa2020
 * @create: 2021/3/23 11:11:27
 * @Version 1.0
 **/
@Data
public class CsUserImageVo {
    @ImageName("授权状态")
    private String robotNumber;//robotQQ
    @ImageName("QQ群号")
    private String groupNumber;//QQ群号
    @ImageName("授权状态")
    private Boolean perpetual;//是否永久
    @ImageName("授权状态")
    private Date time;
    @ImageName("time")
    private String name;
    @ImageName("备注")
    private String remark;
}
