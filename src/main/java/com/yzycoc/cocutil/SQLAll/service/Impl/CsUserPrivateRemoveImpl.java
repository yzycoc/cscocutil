package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserPrivateRemove;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserPrivateRemoveMapper;
import com.yzycoc.cocutil.SQLAll.service.CsUserPrivateRemoveService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-14 19:35
 * @Version 1.0
 **/
@Service(value = "CsUserPrivateRemoveService")
@Primary
public class CsUserPrivateRemoveImpl extends ServiceImpl<CsUserPrivateRemoveMapper, CsUserPrivateRemove> implements CsUserPrivateRemoveService {
}
