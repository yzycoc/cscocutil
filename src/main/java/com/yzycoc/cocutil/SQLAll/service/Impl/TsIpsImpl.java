package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.TsIps;
import com.yzycoc.cocutil.SQLAll.mapper.TsIpsMapper;
import com.yzycoc.cocutil.SQLAll.service.TsIpsService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-13 14:02
 * @Version 1.0
 **/
@Primary
@Service(value = "TsIpsService")
public class TsIpsImpl extends ServiceImpl<TsIpsMapper, TsIps> implements TsIpsService {
}
