package com.yzycoc.controller.coc;

import com.yzycoc.cocutil.SQLAll.bean.xjpublic.CocBinding;
import com.yzycoc.cocutil.SQLAll.service.CocBindingService;
import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.custom.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: cscocutil
 * @description: COC相关操作
 * @author: yzy
 * @create: 2020-12-17 14:54
 * @Version 1.0
 **/
@Api(value = "COC接口",tags = "COC绑定相关接口")
@Controller
@RequestMapping(value = "binding")
public class CocBindingApiController {
    @Autowired
    private CocBindingService cocBindingService;

    @Autowired
    private CsUserVipService csUserVipService;
    @ApiOperation(value = "U-获取绑定列表", notes = "V1.0")
    @ApiImplicitParams({
            @ApiImplicitParam(name="qqcode",value = "用户QQ号",dataType = "String")
    })
    @PostMapping(value="/getBindingList")
    @ResponseBody
    public Result<?> getBindingList(String qqcode){
        if(qqcode.contains("wx")){
            qqcode = csUserVipService.wxQqcode(qqcode);
        }
        Result<?> result = cocBindingService.getBindingList(qqcode);
        return result;
    }


    @ApiOperation(value = "U-新增绑定项", notes = "V1.0")
    @ApiImplicitParams({
            @ApiImplicitParam(name="qqcode",value = "用户QQ号",dataType = "String"),
            @ApiImplicitParam(name="msg",value = "绑定内容",dataType = "String"),
            @ApiImplicitParam(name="tag",value = "标签",dataType = "String"),
            @ApiImplicitParam(name="type",value = "类型",dataType = "String")
    })
    @PostMapping(value="/addBinding")
    @ResponseBody
    public Result<?> addBinding(String qqcode,String msg,String tag,String type){
        if(qqcode.contains("wx")){
            qqcode = csUserVipService.wxQqcode(qqcode);
        }
        Result<?> result = cocBindingService.addBinding(qqcode,msg,tag,type);
        return result;
    }


    @ApiOperation(value = "U-查询绑定的信息", notes = "V1.0")
    @ApiImplicitParams({
            @ApiImplicitParam(name="qqcode",value = "用户QQ号",dataType = "String"),
            @ApiImplicitParam(name="msg",value = "绑定内容",dataType = "String"),
    })
    @PostMapping(value="/getBinding")
    @ResponseBody
        public Result<?> getBinding(String qqcode,String msg){
        if(qqcode.contains("wx")){
            qqcode = csUserVipService.wxQqcode(qqcode);
        }
        CocBinding cocBinding = cocBindingService.query().eq("msg", msg).eq("qqcode", qqcode).one();
        if(cocBinding == null){
            return Result.error("未查询到相关绑定信息。");
        }else{
            return Result.ok(cocBinding.getType()+"#"+cocBinding.getTag());
        }
    }


    @ApiOperation(value = "U-删除绑定信息", notes = "V1.0")
    @ApiImplicitParams({
            @ApiImplicitParam(name="qqcode",value = "用户QQ号",dataType = "String"),
            @ApiImplicitParam(name="msg",value = "绑定内容",dataType = "String"),
    })
    @PostMapping(value="/removeBinding")
    @ResponseBody
    public Result<?> removeBinding(String qqcode,String msg){
        if(qqcode.contains("wx")){
            qqcode = csUserVipService.wxQqcode(qqcode);
        }
        CocBinding cocBinding = cocBindingService.query().eq("msg", msg).eq("qqcode", qqcode).one();
        if(cocBinding == null){
            return Result.error("删除失败，未查询到此触发词绑定的相关信息。");
        }else{
            cocBindingService.removeById(cocBinding);
            return Result.ok("删除成功，已解除绑定。");
        }
    }



}
