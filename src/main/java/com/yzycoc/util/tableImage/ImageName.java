package com.yzycoc.util.tableImage;

import java.lang.annotation.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 19:33
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ImageName {
    /**
     * 字段中文名
     */
    String value() default  "name";
}
