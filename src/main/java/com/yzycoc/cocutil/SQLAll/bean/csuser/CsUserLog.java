package com.yzycoc.cocutil.SQLAll.bean.csuser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 记录
 * @author: yzy
 * @create: 2021-01-09 18:06
 * @Version 1.0
 **/
@Data
public class CsUserLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String qqcode;

    public CsUserLog(String qqcode) {
        this.qqcode = qqcode;
    }
}
