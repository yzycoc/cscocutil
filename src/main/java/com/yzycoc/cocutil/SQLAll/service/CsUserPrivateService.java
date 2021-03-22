package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserPrivate;
import com.yzycoc.custom.result.Result;
import com.yzycoc.from.AddCsUserPrivate;
import com.yzycoc.util.RedisUtil;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-11 20:08
 * @Version 1.0
 **/
public interface CsUserPrivateService extends IService<CsUserPrivate> {
    //同步好友数据
    void SynchronizeUser(String user);

    /***
     * 兑换添加群
     * @param addCsUserPrivate
     * @param redisUtil
     * @return
     */
    Result<?> addCsUserPrivate(AddCsUserPrivate addCsUserPrivate, RedisUtil redisUtil);
}
