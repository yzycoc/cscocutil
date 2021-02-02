package com.yzycoc.tasks;

import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.Utf8Util;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-20 20:35
 * @Version 1.0
 **/
@Component
public class FileRemovesXlzTask {
    //@Scheduled(cron="1/10 * * * * ? ")
    @Scheduled(cron="0 0 5,17 * * ?")
    public void removeDelete() {
        Map<String, String> xlzFile = ConfigParameter.xlzFile;
        System.out.println("删除文件开始！0 0 5,17 * * ?");
        for (String key : xlzFile.keySet()) {
            System.out.println(key);
            Utf8Util.delAllFile(key,new Date());
        }
    }

}
