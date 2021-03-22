package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.ScoreUuid;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-25 15:46
 * @Version 1.0
 **/

public interface ScoreUuidService extends IService<ScoreUuid> {

    String saveNumber(String number,String remark);

    String saveNumber(String number);
}
