package com.yzycoc.cocutil.SQLMy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLMy.bean.MyLexicon;
import com.yzycoc.custom.result.Result;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-18 15:04
 * @Version 1.0
 **/
public interface MyLexiconService extends IService<MyLexicon> {
    /***
     * 同步两边表打的数据
     * @return
     */
    Result<?> SynchronizationLexicon();
}
