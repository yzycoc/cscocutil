package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.CsUserLog;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserLogMapper;
import com.yzycoc.cocutil.SQLAll.service.CsUserLogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-09 18:11
 * @Version 1.0
 **/
@Service(value = "CsUserLogService")
@Primary
public class CsUserLogImpl extends ServiceImpl<CsUserLogMapper, CsUserLog> implements CsUserLogService {
}
