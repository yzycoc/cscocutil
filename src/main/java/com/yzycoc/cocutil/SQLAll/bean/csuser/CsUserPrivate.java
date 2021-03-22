package com.yzycoc.cocutil.SQLAll.bean.csuser;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 仓鼠机器人 用户QQ号
 * @author: yzy
 * @create: 2021-01-11 19:41
 * @Version 1.0
 **/
@Data
public class CsUserPrivate extends BaseEntity<Integer> {

    /** 用户QQ号 */
    private String qqNumber;
    /** 机器人QQ */
    private String robotNumber;

    public CsUserPrivate(Integer id, String qqNumber, String robotNumber) {
        super(id);
        this.qqNumber = qqNumber;
        this.robotNumber = robotNumber;
    }

    public CsUserPrivate(Integer id, String createDate, String createName, String qqNumber, String robotNumber) {
        super(id, createDate, createName);
        this.qqNumber = qqNumber;
        this.robotNumber = robotNumber;
    }

    public CsUserPrivate(String qqNumber, String robotNumber) {
        this.qqNumber = qqNumber;
        this.robotNumber = robotNumber;
    }

    public CsUserPrivate() {
    }
}
