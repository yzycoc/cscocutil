package com.yzycoc.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yzycoc.util.tableImage.ImageName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 19:14
 * @Version 1.0
 **/
@Data
public class DemoEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @ImageName("设备ID")
    private String id;
    @ImageName("类型")
    private String name;
    @ImageName("测试")
    private BigDecimal bigDecimal = new BigDecimal("0");
    public DemoEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
