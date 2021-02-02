package com.yzycoc.controller;

import com.yzycoc.cocutil.SQLMy.service.MyLexiconService;
import com.yzycoc.custom.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: cscocutil
 * @description: 同步数据库
 * @author: yzy
 * @create: 2020-12-18 15:15
 * @Version 1.0
 **/
@Controller
@RequestMapping(value="/syn")
@Api(value = "同步数据库",tags = "数据同步")
public class SynchronizationController {
    @Autowired
    private MyLexiconService myLexiconService;
    /***
     * 获取用户登录的所有JSON
     * @return
     */
    @ApiOperation(value = "A-同步应答词库", notes = "V1.0")
    @PostMapping(value="/lexicon")
    @ResponseBody
    public Result<?> findAll(){
        return myLexiconService.SynchronizationLexicon();
    }
}
