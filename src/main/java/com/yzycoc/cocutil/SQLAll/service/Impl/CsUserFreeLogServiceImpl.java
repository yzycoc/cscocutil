package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserFreeLog;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserFreeLogMapper;
import com.yzycoc.cocutil.SQLAll.service.CsUserFreeLogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service(value = "CsUserFreeLogService")
public class CsUserFreeLogServiceImpl extends ServiceImpl<CsUserFreeLogMapper, CsUserFreeLog>  implements CsUserFreeLogService {
}
