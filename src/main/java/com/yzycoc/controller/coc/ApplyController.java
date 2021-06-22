package com.yzycoc.controller.coc;

import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.cocutil.SQLAll.service.ApplyCocGroupService;
import com.yzycoc.cocutil.SQLAll.service.CsUserService;
import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.custom.result.Result;
import com.yzycoc.custom.result.apply.AddApplyFrom;
import com.yzycoc.custom.result.apply.AddUserForm;
import com.yzycoc.custom.result.apply.UpdateGroupForm;
import com.yzycoc.custom.result.apply.UpdateUserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: cscocutil
 * @description: API接口
 * @author: yzy
 * @create: 2020-08-30 11:19
 * @Version 1.0
 **/
@Api(value = "COC-报名相关接口",tags = "报名相关接口")
@Controller
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private CsUserService csUserService;
    @Autowired
    private ApplyCocGroupService applyCocGroupService;
    @Autowired
    private CsUserVipService csUserVipService;
    @ApiOperation(value = "A-新增coc报名", notes = "V1.0")
    @PostMapping(value="/addGroup")
    @ResponseBody
    public Result add(AddApplyFrom addApplyFrom){
        if(addApplyFrom.getUserNumber().contains("wx")){
            String qq =csUserVipService.isVip(addApplyFrom.getUserNumber());
            if(qq == null){
                return Result.error("报名功能仅VIP赞助用户拥有的权力哦。");
            }else{
                addApplyFrom.setUserNumber(qq);
            }
        }
        CsUser csUser = csUserService.query().in("group_Number", addApplyFrom.getGroupNumber()).one();
        if(csUser==null)
            return Result.error(addApplyFrom.getGroupNumber()+" 此群未授权,无法进行报名相  关操作。");
        if(!csUser.getCreateName().equals(addApplyFrom.getUserNumber()))
            return Result.error("权限不足，本群bot管理为："+csUser.getCreateName()+"。\n请通知bot管理进行报名!!!\n或您可以找他将BOT管理转给你哦！！！");
        return applyCocGroupService.AddAplyFrom(addApplyFrom);
    }

    @ApiOperation(value = "A-报名,用户报名", notes = "V1.0")
    @PostMapping(value="/addUser")
    @ResponseBody
    public Result addUser(AddUserForm addUser){
        return applyCocGroupService.addUser(addUser);
    }

    @ApiOperation(value = "B-群管-报名,关闭/删除", notes = "V1.0")
    @PostMapping(value="/updateGroup")
    @ResponseBody
    public Result addUser(UpdateGroupForm updateGroup){
        updateGroup.setMsg(updateGroup.getMsg().replace("报名",""));
        // 结束报名
        if(updateGroup.getUserNumber().contains("wx")){
            String qq =csUserVipService.isVip(updateGroup.getUserNumber());
            if(qq == null){
                if("重启".equals(updateGroup.getMsg())){
                    return Result.error("此功能仅VIP赞助用户拥有的权力哦。");
                }else{
                    return Result.error("请进行QQ的绑定。");
                }
            }else{
                updateGroup.setUserNumber(qq);
            }
        }
        CsUser csUser = csUserService.query().in("group_Number", updateGroup.getGroupNumber()).one();
        if(csUser==null)
            return Result.error(updateGroup.getGroupNumber()+" 此群未授权,无法进行报名相关操作。");
        if(!csUser.getCreateName().equals(updateGroup.getUserNumber()))
            return Result.error("权限不足，本群bot管理为："+csUser.getCreateName()+"。\n请通知bot管理进行结束报名!!!");
        if("结束".equals(updateGroup.getMsg())){
            return applyCocGroupService.UpdateGroup_down(updateGroup);
        }else if("重启".equals(updateGroup.getMsg())){
            return applyCocGroupService.UpdateGroup_restart(updateGroup);
        }else if("删除".equals(updateGroup.getMsg())){
            return applyCocGroupService.UpdateGroup_remove(updateGroup);
        }
        return Result.error("请检查您的指令，存在异常，无法识别！");
    }

    @ApiOperation(value = "B-用户-取消报名", notes = "V1.0")
    @PostMapping(value="/updateUser")
    @ResponseBody
    public Result updateUser(UpdateUserForm updateGroup){
        return applyCocGroupService.updateUser(updateGroup);
    }

    @ApiOperation(value = "C-查询群报名概况", notes = "V1.1")
    @GetMapping(value="/getGroupApply")
    @ResponseBody
    public ClanResult getGroupApply(String groupNumber,String uuid,String type){
        if(StringUtils.isEmpty(uuid)){
            return applyCocGroupService.getGroupApply(groupNumber);
        }else{
            return applyCocGroupService.getGroupApplyAll(type,groupNumber,uuid);
        }
    }

    @ApiOperation(value = "U-更新部落内部数据", notes = "V1.0")
    @PostMapping(value="/updateApply")
    @ResponseBody
    public Result updateApply(UpdateGroupForm updateGroup){
        return applyCocGroupService.updateApply(updateGroup);
    }
}
