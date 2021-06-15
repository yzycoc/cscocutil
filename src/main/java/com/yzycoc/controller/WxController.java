package com.yzycoc.controller;

import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.custom.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-04-11 11:36
 * @Version 1.0
 **/
@Api(value = "微信接口",tags = "微信相关接口")
@Controller
@RequestMapping(value = "wx")
public class WxController {
    @Autowired
    private CsUserVipService csUserVipService;

    @ApiOperation(value = "U-新增绑定项", notes = "V1.0")
    @PostMapping(value="/getWxbinding")
    @ResponseBody
    public Result<?> getWxbinding(String userNumber){
        return csUserVipService.getWxBingding(userNumber);
    }

    @ApiOperation(value = "U-进行绑定", notes = "V1.0")
    @PostMapping(value="/addBinding")
    @ResponseBody
    public Result<?> getAddBinding(String wxId,String uuid){
        return csUserVipService.getAddBinding(wxId,uuid);
    }


}
