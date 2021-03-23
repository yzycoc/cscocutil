package com.yzycoc.from;

import com.yzycoc.util.tableImage.ImageName;
import lombok.Data;

import java.util.Date;

/**
 * @program: cscocutil -> com.yzycoc.from
 * @description:
 * @author: XinDa2020
 * @create: 2021/3/23 13:13:45
 * @Version 1.0
 **/
@Data
public class CsUserImage {
    @ImageName("QQ群号")
    private String groupNumber;//QQ群号
    @ImageName("授权截止时间")
    private String perpetual;//是否永久
    @ImageName("授权机器人")
    private String robotNumber;//robotQQ
    @ImageName("BOT 昵称")
    private String name;
    @ImageName("BOT 备注")
    private String remark;
}
