package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipCocAstrict;

public interface VipCocAstrictService extends IService<VipCocAstrict> {
    /***
     * 查询该用户今日是否可以查询！
     * @param qqcode
     * @param type
     */
    Boolean isGoto(String qqcode, String type,Integer number);

    /***
     * 查询成功 后 记录 该用户 今天已经查询！
     * @param qqcode
     * @param type
     */
    void isOk(String qqcode, String type);
}
