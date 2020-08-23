package com.yzycoc.qqrobot.controller.message;

import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;

/**
 * @program: cscocutil
 * @description: 私聊消息队列
 * @author: yzy
 * @create: 2020-08-08 22:05
 * @Version 1.0
 **/
@Beans
public class QqPrivateRecord {

    /**
     * 监听私信消息并复读
     */
    @Listen(MsgGetTypes.privateMsg)
    public void hello(PrivateMsg msg, MsgSender sender){
        System.out.println("收到消息：" + msg);
        sender.SENDER.sendPrivateMsg(msg.getQQCode(),msg.getMsg());
    }
}
