package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class CsUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String createDate;
    private String createName;
    private String groupNumber;//QQ群号
    private Boolean perpetual;//是否永久
    private String robotNumber;//robotQQ
    private Date time;
}
