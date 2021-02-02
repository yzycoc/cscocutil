package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.score.ScireStore;
import com.yzycoc.cocutil.SQLAll.mapper.ScireStoreMapper;
import com.yzycoc.cocutil.SQLAll.service.ScireStoreService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-16 20:12
 * @Version 1.0
 **/
@Service(value = "ScireStoreService")
@Primary
public class ScireStoreImpl extends ServiceImpl<ScireStoreMapper, ScireStore> implements ScireStoreService {
}
