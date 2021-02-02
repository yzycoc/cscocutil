package com.yzycoc.cocutil.SQLAll.bean.score;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 积分商城
 * @author: yzy
 * @create: 2020-12-16 20:04
 * @Version 1.0
 **/
@Data
public class ScireStore extends BaseEntity<Integer> {
    @ApiModelProperty(value ="兑换名称")
    private String name;//兑换名称
    @ApiModelProperty(value ="兑换所需要的积分")
    private Integer number;//兑换所需要的积分
    @ApiModelProperty(value ="计量单位")
    private String unit;//计量单位
    @ApiModelProperty(value ="解释")
    private String nameRemark;//解释

    public ScireStore(Integer id) {
        super(id);
    }
}
