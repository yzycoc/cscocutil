package com.yzycoc.cocutil.SQLAll.bean.apply;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import com.yzycoc.util.tableImage.ImageName;
import lombok.Data;

/**
 * @program: cscocutil
 * @description: 用户报名表
 * @author: 936642284
 * @create: 2021-03-04 18:36
 * @Version 1.0
 **/
@Data
public class ApplyCocUser extends BaseEntity<Integer> {
    /***
     * 报名人
     */
    @ImageName("报名人")
    private String userNumber;
    /***
     * 报名唯一部落
     */
    @ImageName("唯一标识")
    private String uuid;
    /***
     * 报名的QQ群
     */
    @ImageName("群号")
    private String groupNumber;
    /***
     * 报名的标签
     */
    @ImageName("报名的标签")
    private String playerTag;
    /**
     * 保存的玩家数据
     */
    @ImageName("保存的玩家数据")
    private String playerJson;
    /***
     * 备注
     */
    @ImageName("备注")
    private String remark;
}
