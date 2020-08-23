package com.yzycoc.qqrobot.controller.message;

import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;

/**
 * @program: cscocutil
 * @description: 群聊天消息队列
 * @author: yzy
 * @create: 2020-08-08 22:06
 * @Version 1.0
 **/
@Beans
public class QqpublicRecord {
    /**
     * 监听私信消息并复读
     */
    @Listen(MsgGetTypes.groupMsg)
    public void hello(GroupMsg msg, MsgSender sender){
        System.out.println(msg.getThisCode()+"收到消息：" + msg);
    }
}
