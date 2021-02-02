package com.yzycoc.cocutil.SQLMy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLMy.bean.MyCsUserVip;
import com.yzycoc.cocutil.SQLMy.mapper.MyCsUserVipMapper;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserVipService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-27 19:37
 * @Version 1.0
 **/
@Service(value = "MyCsUserVipService")
@Primary
public class MyCsUserVipImpl extends ServiceImpl<MyCsUserVipMapper, MyCsUserVip> implements MyCsUserVipService {
}
