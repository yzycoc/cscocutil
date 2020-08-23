package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.yzycoc.cocutil.SQLAll.bean.YuQing;
import com.yzycoc.cocutil.SQLAll.mapper.YuQingMapper;
import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="YuQingService")
@Primary
public class YuQingImpl extends ServiceImpl<YuQingMapper, YuQing> implements YuQingService {

}
