package com.yzycoc;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.forte.qqrobot.beans.messages.result.GroupList;
import com.forte.qqrobot.beans.messages.result.inner.Group;
import com.forte.qqrobot.bot.*;
import com.forte.qqrobot.sender.MsgSender;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@SpringBootApplication
public class CscocutilApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CscocutilApplication.class, args);
        BotManager bean = run.getBean(BotManager.class);

        System.out.println("读取默认配置项，配置到类文件");
        Environment env = SpringContextUtil.getBean(Environment.class);

        //配置最高管理员
        String administrator = env.getProperty("simbot.administrator.qq");
        if(!StringUtils.isEmpty(administrator))
            ConfigParameter.RobotAdministratorQq = administrator;

        String bots = env.getProperty("simbot.core.bots");
        String[] bot = bots.split(",");
        for (int i = 0; i < bot.length; i++) {
            String[] Bot = bot[i].split(":");
            if(Bot.length == 2){
                String qqCode = Bot[0];
                MsgSender sender = bean.getBot(qqCode).getSender();
                StringBuffer result = new StringBuffer("￥￥机器人启动成功￥￥");
                GroupList groupList = sender.GETTER.getGroupList();
                Group[] list = groupList.getList();
                result.append("\n● 顶级："+ConfigParameter.RobotAdministratorQq);
                result.append("\n● 加入群："+list.length+"个");
                System.out.println(result.toString());
                sender.SENDER.sendPrivateMsg(ConfigParameter.RobotAdministratorQq,result.toString());
            }
        }






    }

}
