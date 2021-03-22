package com.yzycoc.cocutil.SQLAll.bean.xjpublic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("xjpublic.tulin_appkey")
public class TulinAppkey{

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    private String appkey;

    private String dataTime;

    private Integer sum = 0;
}
