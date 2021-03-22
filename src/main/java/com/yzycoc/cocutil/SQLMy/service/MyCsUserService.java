package com.yzycoc.cocutil.SQLMy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLMy.bean.MyCsUser;

public interface MyCsUserService extends IService<MyCsUser> {


    Boolean Synchronization();

}
