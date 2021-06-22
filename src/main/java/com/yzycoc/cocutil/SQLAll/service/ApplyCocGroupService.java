package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocGroup;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.custom.result.Result;
import com.yzycoc.custom.result.apply.AddApplyFrom;
import com.yzycoc.custom.result.apply.AddUserForm;
import com.yzycoc.custom.result.apply.UpdateGroupForm;
import com.yzycoc.custom.result.apply.UpdateUserForm;

/**
 * @program: cscocutil
 * @description:
 * @author: 936642284
 * @create: 2021-02-28 16:43
 * @Version 1.0
 **/
public interface ApplyCocGroupService extends IService<ApplyCocGroup> {
    Result AddAplyFrom(AddApplyFrom addApplyFrom);

    Result addUser(AddUserForm addUser);


    Result UpdateGroup_restart(UpdateGroupForm updateGroup);

    Result UpdateGroup_down(UpdateGroupForm updateGroup);

    Result UpdateGroup_remove(UpdateGroupForm updateGroup);

    Result updateUser(UpdateUserForm updateGroup);

    ClanResult getGroupApply(String groupNumber);

    ClanResult getGroupApplyAll(String type,String groupNumber, String uuid);

    Result updateApply(UpdateGroupForm updateGroup);
}
