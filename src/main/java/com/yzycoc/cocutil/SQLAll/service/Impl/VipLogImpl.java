package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipLog;
import com.yzycoc.cocutil.SQLAll.mapper.VipLogMapper;
import com.yzycoc.cocutil.SQLAll.service.VipLogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-24 13:03
 * @Version 1.0
 **/

@Service(value="VipLogService")
@Primary
public class VipLogImpl extends ServiceImpl<VipLogMapper, VipLog>  implements VipLogService {
}
