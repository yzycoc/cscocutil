package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.MyIps;
import com.yzycoc.cocutil.SQLAll.mapper.MyIpsMapper;
import com.yzycoc.cocutil.SQLAll.service.MyIpsService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-09-08 20:30
 * @Version 1.0
 **/
@Service(value = "MyIpsService")
@Primary
public class MyIpsImpl extends ServiceImpl<MyIpsMapper, MyIps> implements MyIpsService {
}
