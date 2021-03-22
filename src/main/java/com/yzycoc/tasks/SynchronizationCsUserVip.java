package com.yzycoc.tasks;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.cocutil.SQLAll.service.VipLogService;
import com.yzycoc.cocutil.SQLMy.bean.MyCsUserVip;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserVipService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.Utf8Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-27 20:00
 * @Version 1.0
 **/
@Slf4j
@Component
public class SynchronizationCsUserVip {

    @Autowired // 系统服务器
    private CsUserVipService csUserVipService;
    @Autowired
    private MyCsUserVipService myCsUserVipService;
    @Autowired
    private VipLogService vipLogService;

    private static Integer count_All= 0 ;

    //@Scheduled(cron="0 0/5 * * * ? ")
    @Scheduled(cron="0/20 * * * * ?")
    public void tasks() {
        int count = vipLogService.count();
        if(count != count_All){
            count_All = count;
            this.saves();
        }
    }
    @Transactional
    public void saves() {
        List<CsUserVip> list = csUserVipService.list();
        if(list.size() >= 1){
            log.info("更新CsUserVip,用户资料!");
            QueryWrapper qw = new QueryWrapper();
            qw.eq("1","1");
            myCsUserVipService.remove(qw);
            for (CsUserVip csUserVip : list) {
                MyCsUserVip myCsUserVip = new MyCsUserVip();
                BeanUtils.copyProperties(csUserVip, myCsUserVip);
                myCsUserVip.setId(csUserVip.getId());
                myCsUserVipService.saveOrUpdate(myCsUserVip);
            }
        }
    }


}
