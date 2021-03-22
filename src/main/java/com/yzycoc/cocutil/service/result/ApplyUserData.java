package com.yzycoc.cocutil.service.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-21 16:14
 * @Version 1.0
 **/
@Data
public class ApplyUserData {
    @ApiModelProperty(value = "玩家名")
    private String name = "-";
    @ApiModelProperty(value = "玩家标签")
    private String tag= "-";
    private String townHallLevel= "-";//大本营等级
    private String builderHallLevel= "-";//夜世界大本营
    private Integer number;//科技百分比
    private String barbarianKing= "-";//蛮王
    private String archerQueen= "-";;//女王
    private String grandWarden = "-";;//永旺
    private String royalChampion = "-";;//闰土

    private String expLevel = "-";//经验等级
    private String trophies= "-";//现主世界奖杯
    private String warStars= "-";//战争的胜利之星
    private String attackWins = "-";//进攻胜利场次
    private String role = "-";//所在部落权限 首领副首领等
    private String donations = "-";//捐兵
    private String versusBattleWinCount = "-";//对战胜率数

    private String isClan = "不在部落";//是否在部落

    private String createDate;//报名时间

    private String userNumber;//报名用户

    private String groupNumber;//报名的群

    private String robotNumber;//报名的机器人

    private String remark;
}
