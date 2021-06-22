
package com.yzycoc.controller;

import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import com.yzycoc.cocutil.SQLAll.service.*;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.custom.result.Result;
import com.yzycoc.from.*;
import com.yzycoc.util.RedisUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;



import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: cscocutil
 * @description: 用户控制器
 * @author: yzy
 * @create: 2020-12-14 18:04
 * @Version 1.0
*/
@Controller
@RequestMapping(value="/user")
@Api(value = "U-用户相关接口",tags = "用户操作接口")
public class UserApiController {
    @Autowired
    private CsUserService csUserService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CsUserPrivateService csUserPrivateService;
    @Autowired
    private CsUserVipService csUserVipService;
    /***
     * 获取用户登录的所有JSON
     * @return
     */

    @ApiOperation(value = "A-获取用户登录的所有JSON", notes = "V1.0")
    @ApiImplicitParam(name="json",value = "小栗子发送过来的用户JSON",dataType = "String")
    @PostMapping(value="/findAll")
    @ResponseBody
    public List<CsUser> findAll(String json){
        List<CsUser> result = csUserService.getList(json);
        return result;
    }

    @ApiOperation(value = "A-获取所有群聊JSON", notes = "V1.0")
    @ApiImplicitParam(name="json",value = "小栗子发送过来的群聊JSON",dataType = "String")
    @PostMapping(value="/findGroupAll")
    @ResponseBody
    public List<CsUser> findGroupAll(String json){
        List<CsUser> result = csUserService.getGroupList(json);
        return result;
    }

    @ApiOperation(value = "B-机器人绑定QQ群", notes = "V1.0")
    @PostMapping(value = "/addGroup")
    @ResponseBody
    public Result<?> addGroup(AddCsUser addCsUser){
        Result<?> result = csUserService.addCsUser(addCsUser);
        System.out.println("机器人授权，状态："+result.isSuccess()+",群号"+addCsUser.getGroupNumber()+",授权时间:"+addCsUser.getNumber());
        return result;
    }


    @ApiOperation(value = "C-机器人换绑QQ群", notes = "V1.0")
    @PostMapping(value = "/updateGroup")
    @ResponseBody
    public Result<?> updateGroup(UpdateGroup updateGroup){
        return csUserService.updateGroup(updateGroup);
    }


    @ApiOperation(value = "C-机器人换绑bot管理", notes = "V1.0")
    @PostMapping(value = "/updateBotAdmin")
    @ResponseBody
    public Result<?> updateBotAdmin(UpdateBot updateBot){
        return csUserService.updateBotAdmin(updateBot);
    }

    @ApiOperation(value = "C-删除群授权", notes = "V1.0")
    @PostMapping(value = "/deleteGroup")
    @ResponseBody
    public Result<?> deleteGroup(DeleteBot deleteBot){
        return csUserService.deleteGroup(deleteBot);
    }

    @ApiOperation(value = "A-查询剩余积分", notes = "V1.0")
    @GetMapping(value = "/getMyScore")
    @ResponseBody
    public Result<?> getMyScore(String userNumber){
        StringBuffer result = csUserVipService.getMyScore(userNumber);
        return Result.ok(result.toString());
    }


    @ApiOperation(value = "A-查询剩余积分Vip", notes = "V1.0")
    @GetMapping(value = "/getMyScoreImage")
    @ApiImplicitParam(name = "userNumber",value = "唯一标识")
    public void getMyScoreImage(String userNumber, HttpServletRequest request, HttpServletResponse response){
        csUserVipService.resultMyScoreOkImage(userNumber,request,response);
    }

    @ApiOperation(value = "A-查询授权情况", notes = "V1.0")
    @GetMapping(value = "/getAuthorization")
    @ResponseBody
    public Result<?> getAuthorization(String userNumber){
        return csUserService.getAuthorization(userNumber);
    }

    @ApiOperation(value = "A-查询授权情况", notes = "V1.2")
    @GetMapping(value = "/getAuthorization2")
    @ResponseBody
    public ClanResult getAuthorization2(String userNumber){
        return csUserService.getAuthorization2(userNumber);
    }



    @ApiOperation(value = "E-获取好友的申请码", notes = "V1.0")
    @GetMapping(value = "/getFriend")
    @ResponseBody
    public  Result<?> getFriend(){
        try {
            Object object = redisUtil.get("friend");
            if(StringUtils.isEmpty(object)) {
                String randomUUID = UUID.randomUUID().toString().replaceAll("-","").substring(0, 6).toUpperCase();
                redisUtil.set("friend", randomUUID,3600);
                return Result.ok("请在添加好友时在验证信息里输入:"+randomUUID);
            }else{
                String randomUUID = String.valueOf(object);
                return Result.ok("请在添加好友时在验证信息里输入:"+randomUUID);
            }
        }catch (Exception e){
            return Result.error("获取失败，请稍后重试！");
        }
    }

    @ApiOperation(value = "E-绑定添加机器人好友", notes = "V1.0")
    @PostMapping(value = "/addPrivate")
    @ResponseBody
    public Result<?> addPrivate(AddCsUserPrivate addCsUserPrivate){
        Result<?> result = csUserPrivateService.addCsUserPrivate(addCsUserPrivate,redisUtil);
        return result;
    }

    @ApiOperation(value = "E-同步好友信息", notes = "V1.0")
    @PostMapping(value = "/getSynchronizeUser")
    @ResponseBody
    public Result<?> getSynchronizeUser(String user){
        System.out.println("同步好友信息开始！");
        csUserPrivateService.SynchronizeUser(user);
        return null;
    }


}
