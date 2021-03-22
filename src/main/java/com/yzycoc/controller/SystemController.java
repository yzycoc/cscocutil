package com.yzycoc.controller;

import com.yzycoc.cocutil.SQLAll.bean.MyIps;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.Illicitdrug;
import com.yzycoc.cocutil.SQLAll.service.IllicitdrugService;
import com.yzycoc.cocutil.SQLAll.service.MyIpsService;
import com.yzycoc.cocutil.SQLAll.service.ScoreUuidService;
import com.yzycoc.cocutil.SQLAll.service.TulinAppkeyService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.SpringContextUtil;
import com.yzycoc.custom.result.Result;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

/**
 * @program: cscocutil
 * @description: 系统工具类
 * @author: yzy
 * @create: 2021-01-20 17:57
 * @Version 1.0
 **/
@Controller
@RequestMapping(value="/system")
@Api(value = "Z-系统工具",tags = "系统工具类")
public class SystemController {
    /***
     * 机器人聊天 违规词
     */
    @Autowired
    private IllicitdrugService illicitdrugService;
    @Autowired
    private TulinAppkeyService tulinAppkeyService;
    @Autowired
    private ScoreUuidService scoreUuidService;

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



    @ApiOperation(value = "B-修改CocAPPKEY", notes = "V1.0")
    @PostMapping(value="updateAppkey")
    @ApiImplicitParam(name = "appkey",value = "文件路径")
    @ResponseBody
    public Result updateAppkey(String appkey){
        MyIpsService myIpsService = SpringContextUtil.getBean(MyIpsService.class);
        MyIps myIps = myIpsService.query().eq("ip", ConfigParameter.network_Path_IP).eq("prot",ConfigParameter.PROT).one();
        myIps.setCocApi(appkey);
        myIpsService.saveOrUpdate(myIps);
        ConfigParameter.CocApi =" "+ myIps.getCocApi();
        System.out.println("已修改成功："+appkey);
        return Result.ok(ConfigParameter.CocApi);
    }

    @ApiOperation(value = "C-聊天回复违禁词", notes = "V1.0")
    @PostMapping(value="/illicitdrug")
    @ResponseBody
    public List<Illicitdrug> savexlzFile(){
        List<Illicitdrug> list = illicitdrugService.list();
        return list;
    }


    @ApiOperation(value = "D-获取图灵Appkey", notes = "V1.0")
    @GetMapping(value="/getTulinAppkey")
    @ResponseBody
    public String getTulinAppkey(String qq){
        return tulinAppkeyService.getTulinAppkey(qq);
    }

    @ApiOperation(value = "E-积分兑换码Appkey", notes = "V1.0")
    @GetMapping(value="/getScoreAppkey")
    @ResponseBody
    public Result<?> getScoreAppkey(String number,String qqNumber){
        if(StringUtils.isNotEmpty(number)){
            String appkey= scoreUuidService.saveNumber(number, qqNumber);
            return Result.ok(appkey);
        }else{
            return Result.error("参数存在异常！");
        }
    }
}
