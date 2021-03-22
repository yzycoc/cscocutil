package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.TulinAppkey;

public interface TulinAppkeyService extends IService<TulinAppkey> {

    String getTulinAppkey(String qq);
}
