package com.yzycoc;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.yzycoc.cocutil.SQLAll.bean.MyIps;
import com.yzycoc.cocutil.SQLAll.service.MyIpsService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.*;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.custom.result.Result;
import com.yzycoc.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.UUID;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
public class CscocutilApplication {
    public static void main(String[] args) {
        SpringApplication.run(CscocutilApplication.class, args);
        Environment env = SpringContextUtil.getBean(Environment.class);
        String port = env.getProperty("server.port");
        InetAddress address = null;
        String myips = "";
        try {
            address = InetAddress.getLocalHost();
            myips = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        AjaxHttpResult sendTxtGets = HttpRequest.doGet("http://pv.sohu.com/cityjson?ie=utf-8", new HashMap<>());
        String sendTxtGet = sendTxtGets.getErrorMsg();
        String IP = sendTxtGet.substring(sendTxtGet.indexOf("cip") + 7, sendTxtGet.indexOf("cid") - 4);
        ConfigParameter.network_Path = "http://"+IP+":"+port+"/";
        ConfigParameter.LAN_Path = "http://"+myips +":"+port;
        ConfigParameter.PROT = port;
        ConfigParameter.network_Path_IP = IP;
        ConfigParameter.LAN_Path_IP = myips;
        System.out.println("外网API："+ConfigParameter.network_Path);
        System.out.println("局域网API："+ConfigParameter.LAN_Path);
        System.out.println("swagger-UI API："+ConfigParameter.LAN_Path+"/swagger-ui.html");
        MyIpsService myIpsService = SpringContextUtil.getBean(MyIpsService.class);
        MyIps myIps = myIpsService.query().eq("ip", IP).eq("prot",port).one();
        if(myIps == null){
            myIps = new MyIps();
            myIps.setIp(IP);
            myIps.setProt(Integer.valueOf(port));
            myIps.setStatus(true);
            myIps.setWeight(1);//权重
            myIps.setCreateDate(TimeUtiles.getStringDate());
            myIps.setCreateName("系统");
            System.out.println("请重启系统，填入APPKEY");
        }else{
            System.out.println("密钥更新为 ["+ myIps.getCocApi()+"]");
            ConfigParameter.CocApi =" "+ myIps.getCocApi();
        }
        myIps.setIntranetIp(myips);
        myIps.setUpdateDate(TimeUtiles.getStringDate());
        myIpsService.saveOrUpdate(myIps);
        RedisUtil redisUtil = SpringContextUtil.getBean(RedisUtil.class);
        try {
            Object object = redisUtil.get("friend");
            if(StringUtils.isEmpty(object)) {
                String randomUUID = UUID.randomUUID().toString().replaceAll("-","").substring(0, 6).toUpperCase();
                redisUtil.set("friend", randomUUID,3600);
                System.out.println("redis:"+randomUUID);
            }else{
                String randomUUID = String.valueOf(object);
                System.out.println("redis:"+randomUUID);
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }

}
