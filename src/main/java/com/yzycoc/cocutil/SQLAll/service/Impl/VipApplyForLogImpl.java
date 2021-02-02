package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipApplyForLog;
import com.yzycoc.cocutil.SQLAll.mapper.VipApplyForLogMapper;
import com.yzycoc.cocutil.SQLAll.service.VipApplyForLogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-22 17:29
 * @Version 1.0
 **/
@Primary
@Service(value = "VipApplyForLogService")
public class VipApplyForLogImpl extends ServiceImpl<VipApplyForLogMapper, VipApplyForLog> implements VipApplyForLogService {
}
