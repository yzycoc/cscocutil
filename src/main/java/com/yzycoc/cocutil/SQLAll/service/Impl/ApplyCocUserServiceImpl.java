package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocUser;
import com.yzycoc.cocutil.SQLAll.mapper.ApplyCocUserMapper;
import com.yzycoc.cocutil.SQLAll.service.ApplyCocUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: 936642284
 * @create: 2021-03-04 18:44
 * @Version 1.0
 **/
@Service(value = "ApplyCocUserService")
@Primary
public class ApplyCocUserServiceImpl extends ServiceImpl<ApplyCocUserMapper,ApplyCocUser> implements ApplyCocUserService {
}
