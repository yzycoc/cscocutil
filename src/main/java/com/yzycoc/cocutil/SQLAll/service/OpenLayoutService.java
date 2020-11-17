package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.OpenLayout;
import com.yzycoc.cocutil.service.result.ClanResult;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-11-13 10:20
 * @Version 1.0
 **/
public interface OpenLayoutService extends IService<OpenLayout> {
    /***
     * 获取一个随机 阵型
     * @return
     */
    ClanResult getRandomFormation();

    ClanResult getFormation(String tag);
}
