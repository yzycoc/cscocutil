package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import com.yzycoc.cocutil.SQLAll.mapper.YuQingMapper;
import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="YuQingService")
@Primary
public class YuQingImpl extends ServiceImpl<YuQingMapper, YuQing> implements YuQingService {
    @Autowired
    private YuQingMapper yuQingMapper;
    @Override
    public String getYqjy(String valueOf) {
        return yuQingMapper.getyuqing(valueOf);
    }
}
