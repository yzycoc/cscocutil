package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.DlUrl;

/**
 * @program: cscocutil
 * @description: 锻炼服务
 * @author: yzy
 * @create: 2020-08-05 10:54
 * @Version 1.0
 **/
public interface DlUrlService extends IService<DlUrl> {

    String dl(String url);
}
