package com.yzycoc.controller;

import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.result.Result;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @program: cscocutil
 * @description: 系统工具类
 * @author: yzy
 * @create: 2021-01-20 17:57
 * @Version 1.0
 **/
@Controller
@RequestMapping(value="/system")
@Api(value = "系统工具",tags = "系统工具类")
public class SystemController {

    @ApiOperation(value = "A-保存文件", notes = "V1.0")
    @PostMapping(value="setMapFile")
    @ApiImplicitParam(name = "file",value = "文件路径")
    @ResponseBody
    public void savexlzFile(String file){
        System.out.println("系统文件："+file);
        ConfigParameter.xlzFile.put(file,"1");
        File files = new File(file);
        if(!files.exists()){//如果文件夹不存在
            files.mkdirs();//创建文件夹
        }
    }

}
