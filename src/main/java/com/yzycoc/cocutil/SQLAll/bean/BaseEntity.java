package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: cscocutil
 * @description: 实体类
 * @author: yzy
 * @create: 2020-12-16 20:05
 * @Version 1.0
 **/
@Data
public class BaseEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private T id;
    @ApiModelProperty(value = "新增时间")
    private String createDate;
    @ApiModelProperty(value = "处理人")
    private String createName;

    public BaseEntity(T id) {
        this.id = id;
    }

    public BaseEntity(T id, String createDate, String createName) {
        this.id = id;
        this.createDate = createDate;
        this.createName = createName;
    }

    public BaseEntity() {
    }
}
