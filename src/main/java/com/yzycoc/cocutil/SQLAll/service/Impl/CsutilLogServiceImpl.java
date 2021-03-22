package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.CsutilLog;
import com.yzycoc.cocutil.SQLAll.mapper.CsutilLogMapper;
import com.yzycoc.cocutil.SQLAll.service.CsutilLogService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service(value = "CsutilLogSrrvice")
public class CsutilLogServiceImpl extends ServiceImpl<CsutilLogMapper, CsutilLog> implements CsutilLogService {
}
