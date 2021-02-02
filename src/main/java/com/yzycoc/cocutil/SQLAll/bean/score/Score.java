package com.yzycoc.cocutil.SQLAll.bean.score;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 积分列表
 * @author: yzy
 * @create: 2020-12-16 19:51
 * @Version 1.0
 **/
@Data
@ApiModel(value="积分列表", description="设备信息表")
public class Score {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private Integer id;
    @ApiModelProperty(value = "新增时间")
    private String createDate;
    @ApiModelProperty(value = "处理人")
    private String createName;
    @ApiModelProperty(value = "积分数")
    private Integer number = 0;
    @ApiModelProperty(value = "qq号")
    private String qqNum;
}
