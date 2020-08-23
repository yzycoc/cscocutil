/*
package com.yzycoc.qqrobot.controller.config;

import com.forte.qqrobot.SimpleRobotApplication;
import com.forte.qqrobot.beans.messages.result.GroupList;
import com.forte.qqrobot.beans.messages.result.inner.Group;
import com.forte.qqrobot.depend.DependGetter;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.simbot.component.mirai.MiraiApp;
import com.simbot.component.mirai.MiraiConfiguration;
import com.simplerobot.core.springboot.configuration.SpringBootDependGetter;

*/
/**
 * @program: cscocutil
 * @description: QQ启动程序
 * @author: yzy
 * @create: 2020-08-08 17:07
 * @Version 1.0
 **//*

@SimpleRobotApplication(resources = "application.properties")
public class QqRobot implements MiraiApp {

    private DependGetter dependGetter;
    public QqRobot(SpringBootDependGetter dependGetter){
        this.dependGetter = dependGetter;
    }


    */
/***
     * 启动前塞入 配置信息
     * @param configuration
     *//*

    @Override
    public void before(MiraiConfiguration configuration) {
        configuration.setDependGetter(dependGetter);
    }

    */
/***
     * 启动后执行的程序
     * @param cqCodeUtil
     * @param sender
     *//*

    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }

}
*/
