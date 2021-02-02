package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.CocBinding;
import com.yzycoc.custom.result.Result;

/**
 * @program: cscocutil
 * @description: 服务层
 * @author: yzy
 * @create: 2020-12-17 15:14
 * @Version 1.0
 **/
public interface CocBindingService extends IService<CocBinding> {
    Result<?> getBindingList(String qqcode);

    Result<?> addBinding(String qqcode, String msg, String tag, String type);
}
