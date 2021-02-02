package com.yzycoc.controller;

import com.yzycoc.cocutil.SQLAll.bean.PublicLexicon;
import com.yzycoc.cocutil.SQLAll.service.PublicLexiconService;
import com.yzycoc.cocutil.SQLMy.bean.MyLexicon;
import com.yzycoc.cocutil.SQLMy.service.MyLexiconService;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-10 16:48
 * @Version 1.0
 **/
@Controller
@RequestMapping(value="/test")
@Api(value = "测试",tags = "测试接口")
public class TestController {

}
