package com.yzycoc.cocutil.SQLAll.bean.apply;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 部落报名状态
 * @author: 936642284
 * @create: 2021-02-28 15:09
 * @Version 1.0
 **/
@Data
public class ApplyCocGroup extends BaseEntity<Integer> {
    /***
     * QQ群
     */
    private String groupNumber;
    /***
     * 操作者QQ
     */
    private String userNumber;
    /***
     * 部落标签
     */
    private String clanTag;
    /***
     * 部落名称
     */
    private String clanJson;

    /***
     * 报名的类型
     */
    private String type;
    /***
     * 开启状态
     */
    private String status;
    /***
     * uuid
     */
    private String uuid;
}
