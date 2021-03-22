package com.yzycoc.tasks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipCocAstrict;
import com.yzycoc.cocutil.SQLAll.service.VipCocAstrictService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.SpringContextUtil;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.Utf8Util;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-10-08 08:48
 * @Version 1.0
 **/
@Component
public class FileRemoveTask {
    @Scheduled(cron="0 0 4 * * ?")
    public void tasks() {
        System.out.println("删除文件开始！0 0 4 * * ? *");
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 120);
        for (int i = 0; i < ConfigParameter.fileRemove.length; i++) {
            String remove = ConfigParameter.fileRemove[i];
            Utf8Util.delAllFile(remove,date);
        }
        //删除昨天的日志
        VipCocAstrictService vipcoc = SpringContextUtil.getBean(VipCocAstrictService.class);
        QueryWrapper<VipCocAstrict> qw = new QueryWrapper<>();
        String dataTime = TimeUtiles.endTime(-1);
        qw.le("query_date",dataTime);
        vipcoc.remove(qw);
    }



}
