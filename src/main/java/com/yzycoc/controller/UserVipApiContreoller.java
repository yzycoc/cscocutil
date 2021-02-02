package com.yzycoc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.cocutil.service.ApiService;
import com.yzycoc.from.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-21 15:44
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping(value="/uservip")
@Api(value = "VIP用户相关接口",tags = "VIP用户操作接口")
public class UserVipApiContreoller {
    @Autowired
    private CsUserVipService csUserVipService;
    @Autowired
    private ApiService apiService;
    @ApiOperation(value = "A-获取全部会员用户", notes = "V1.0")
    @PostMapping(value="findAll")
    @ResponseBody
    public List<CsUserVip> getList(){
        return csUserVipService.list();
    }
    
    @ApiOperation(value = "B-申请结算", notes = "V1.0")
    @PostMapping(value="applyFor")
    @ResponseBody
    public VipApplyForResult applyFor(VipApplyFor vip){
        return csUserVipService.applyFor(vip);
    }
    
    @ApiOperation(value = "B-获取二维码", notes = "V1.0")
    @RequestMapping(value = "getQrcode",method = {RequestMethod.GET} )
    @ApiImplicitParam(name = "json",value = "文件")
    public void getQrcode(String json,String uuid,HttpServletRequest request, HttpServletResponse response){
        try {
            JSONObject data = JSON.parseObject(json);
            String httpUrl = data.getString("data");
            apiService.Qrcode(httpUrl,request,response,uuid);
        }catch (Exception e){
            e.printStackTrace();
            log.info("获取收款二维码失败！");
        }
    }

    @ApiOperation(value = "D-解析用户数据", notes = "V1.0")
    @PostMapping(value="addMoney")
    @ApiImplicitParam(name = "xml",value = "付款数据")
    @ResponseBody
    public List<Dom4jResult> addMoney(String xml, String robotNumber,String money){
        return csUserVipService.dom4jXml(xml,robotNumber,money);
    }

    @ApiOperation(value = "D-获取成功后的图片", notes = "V1.0")
    @RequestMapping(value = "resutOkImage",method = {RequestMethod.GET} )
    @ApiImplicitParam(name = "uuid",value = "唯一标识")
    public void resutOkImage(String uuid,HttpServletRequest request, HttpServletResponse response){
        csUserVipService.resultOkImage(uuid,request,response);
    }
}
