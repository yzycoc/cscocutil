package com.yzycoc.cocutil.SQLAll.bean.vip;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import com.yzycoc.custom.TimeUtiles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VipCocAstrict extends BaseEntity<Integer> {

    @ApiModelProperty(value = "用户QQ号")
    private String qqcode;

    @ApiModelProperty(value = "限制类型")
    private String type;

    @ApiModelProperty(value = "查询时间")
    private String queryDate;
    //= TimeUtiles.getStringTime()
    @ApiModelProperty(value = "查询次数")
    private Integer sumNumber = 1;

}
