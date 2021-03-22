package com.yzycoc.cocutil.SQLAll.bean.csuser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: cscocutil
 * @description: 仓鼠机器人 免费用户
 * @author: yzy
 * @create: 2020-12-14 17:56
 * @Version 1.0
 **/
@Data
public class CsUser extends BaseEntity<Integer> {
    @ApiModelProperty(value = "QQ群号",name="groupNumber",example="QQ群号")
    private String groupNumber;//QQ群号
    @ApiModelProperty(value = "是否永久",name="perpetual",example="false")
    private Boolean perpetual;//是否永久
    @ApiModelProperty(value = "机器人QQ",name="robotQQ",example="机器人QQ")
    private String robotNumber;//robotQQ
    @ApiModelProperty(value = "结束时间",name="time",example="时间")
    private Date time;


    @ApiModelProperty(value = "剩余时间时间戳",name="groupTime",example="-1")
    public long getGroupTime() {
        long groupTime = -1;
        if(perpetual!=null&&perpetual){
            groupTime = -1;
        }else{
            groupTime = time.getTime();
        }
        return groupTime;
    }
}
