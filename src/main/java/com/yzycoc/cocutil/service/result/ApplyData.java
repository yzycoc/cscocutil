package com.yzycoc.cocutil.service.result;

import lombok.Data;

/**
 * @program: cscocutil
 * @description: 群报名概况
 * @author: yao
 * @create: 2021-03-20 09:37
 * @Version 1.0
 **/
@Data
public class ApplyData {
    /***
     * 报名的机器人
     */
    private String robotNumber;
    /***
     * 开启报名的人
     */
    private String userNumber;
    /***
     *
     */
    private String uuid;
    /***
     * 部落标签
     */
    private String tag;
    /***
     * 部落名称
     */
    private String name;
    /***
     * 报名开始时间
     */
    private String createData;



}
