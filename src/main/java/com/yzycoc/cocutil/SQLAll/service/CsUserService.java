package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.custom.result.Result;
import com.yzycoc.from.*;

import java.util.List;

/**
 * @program: cscocutil
 * @description: 服务层
 * @author: yzy
 * @create: 2020-12-14 17:59
 * @Version 1.0
 **/
public interface CsUserService extends IService<CsUser> {
    List<CsUser> getList(String json);

    /***
     * 购买绑定群
     * @param addCsUser
     * @return
     */
    Result<?> addCsUser(AddCsUser addCsUser);

    /***
     * 更换群
     * @param updateGroup
     * @return
     */
    Result<?> updateGroup(UpdateGroup updateGroup);

    /***
     * 更换 updateBot
     * @param updateBot
     * @return
     */
    Result<?> updateBotAdmin(UpdateBot updateBot);

    /***
     * 查询 用户授权情况
     * @param userNumber
     * @return
     */
    Result<?> getAuthorization(String userNumber);

    /***
     * 解除群授权
     * @param deleteBot
     * @return
     */
    Result<?> deleteGroup(DeleteBot deleteBot);
}
