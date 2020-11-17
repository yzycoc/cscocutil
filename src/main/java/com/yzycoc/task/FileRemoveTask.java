package com.yzycoc.task;

import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.SpringContextUtil;
import com.yzycoc.util.RedisUtil;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-10-08 08:48
 * @Version 1.0
 **/
public class FileRemoveTask {
    @Scheduled(cron="0 0 4 * * ? *")
    public void task() {
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 120);
        for (int i = 0; i < ConfigParameter.fileRemove.length; i++) {
            String remove = ConfigParameter.fileRemove[i];
            delAllFile(remove,date);
        }
    }
    /***
     * 删除指定文件夹下所有文件
     * 删除两个小时钱的文件夹
     * @param path 文件夹完整绝对路径
     * @param date 删除两个小时钱的文件夹
     * @return
     */
    public static  boolean delAllFile(String path, Date date) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                if (new Date(temp.lastModified()).before(date)) {
                }
                temp.delete();
            }
            if (temp.isDirectory()) {
                //delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }
}
