package com.yzycoc.cocutil.service;

import com.yzycoc.cocutil.service.result.ClanResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: cscocutil
 * @description: 一般其他接口服务层
 * @author: yzy
 * @create: 2020-10-29 15:49
 * @Version 1.0
 **/
public interface ApiService {
    /***
     * 重新启动机器人
     * @param path
     */
    void resultQQRobot(String path);

    /***
     * 下载图片
     * @param id
     * @return
     */
    ClanResult imageDown(String id, HttpServletResponse response,HttpServletRequest request);
}
