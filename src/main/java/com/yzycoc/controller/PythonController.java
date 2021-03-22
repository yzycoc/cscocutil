package com.yzycoc.controller;

import com.yzycoc.util.GetIp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value="/test")
@Api(value = "Z-爬虫专用",tags = "python")
public class PythonController {

    @ApiOperation(value = "B-机器人绑定QQ群", notes = "V1.0")
    @GetMapping(value = "/getIp")
    @ResponseBody
    public String getmanage(HttpServletRequest request){
        String ip = GetIp.getIp(request);
        return ip;
    }

}
