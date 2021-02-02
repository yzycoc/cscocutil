package com.yzycoc.cocutil.SQLAll.bean;

import lombok.Data;

/**
 * @program: cscocutil
 * @description: 仓鼠机器人 用户QQ号
 * @author: yzy
 * @create: 2021-01-11 19:41
 * @Version 1.0
 **/
@Data
public class CsUserPrivateRemove extends BaseEntity<Integer> {

    /** 用户QQ号 */
    private String qqNumber;
    /** 机器人QQ */
    private String robotNumber;

    public CsUserPrivateRemove(Integer id, String qqNumber, String robotNumber) {
        super(id);
        this.qqNumber = qqNumber;
        this.robotNumber = robotNumber;
    }

    public CsUserPrivateRemove(Integer id, String createDate, String createName, String qqNumber, String robotNumber) {
        super(id, createDate, createName);
        this.qqNumber = qqNumber;
        this.robotNumber = robotNumber;
    }

    public CsUserPrivateRemove(String qqNumber, String robotNumber) {
        this.qqNumber = qqNumber;
        this.robotNumber = robotNumber;
    }

    public CsUserPrivateRemove() {
    }
}
