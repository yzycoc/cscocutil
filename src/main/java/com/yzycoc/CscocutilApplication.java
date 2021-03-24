package com.yzycoc;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.yzycoc.cocutil.SQLAll.bean.MyIps;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.cocutil.SQLAll.service.CsUserService;
import com.yzycoc.cocutil.SQLAll.service.MyIpsService;
import com.yzycoc.cocutil.SQLMy.bean.MyCsUser;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.*;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.custom.result.Result;
import com.yzycoc.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
@Slf4j
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
        String path = env.getProperty("server.servlet.context-path");
        MyIpsService myIpsService = SpringContextUtil.getBean(MyIpsService.class);
        MyIps myIps = myIpsService.query().eq("ip", IP).eq("prot",port).one();
        String appkey_String = null;
        if(myIps == null){
            myIps = new MyIps();
            myIps.setIp(IP);
            myIps.setProt(Integer.valueOf(port));
            myIps.setStatus(true);
            myIps.setWeight(1);//权重
            myIps.setCreateDate(TimeUtiles.getStringDate());
            myIps.setCreateName("系统");
            appkey_String = "请重启系统，填入APPKEY";
        }else{
            appkey_String = "["+ myIps.getCocApi()+"]";
            ConfigParameter.CocApi =" "+ myIps.getCocApi();
        }
        myIps.setIntranetIp(myips);
        myIps.setUpdateDate(TimeUtiles.getStringDate());
        myIpsService.saveOrUpdate(myIps);
        RedisUtil redisUtil = SpringContextUtil.getBean(RedisUtil.class);
        String randomUUID = null;
        try {
            Object object = redisUtil.get("friend");
            if(StringUtils.isEmpty(object)) {
                randomUUID = UUID.randomUUID().toString().replaceAll("-","").substring(0, 6).toUpperCase();
                redisUtil.set("friend", randomUUID,3600);
            }else{
                randomUUID = String.valueOf(object);
            }
            MyCsUserService bean = SpringContextUtil.getBean(MyCsUserService.class);
            System.out.println("----------------------------------------------------------\n\t" +
                    "仓鼠机器人后端程序运行成功-Spring-Boot is running! Access URLs:\n\t" +
                    "外网API: \t"+ConfigParameter.network_Path  + path + "\n\t" +
                    "局域网API: \t" + ConfigParameter.LAN_Path +  path + "\n\t" +
                    "Swagger文档: \t" + ConfigParameter.LAN_Path + path + "swagger-ui.html\n\t" +
                    "redis好友申请: \t"+randomUUID+" \n\t" +
                    "群更新状态: \t"+bean.Synchronization()+"\n\t"+
                    "密钥: \t "+appkey_String+" \n" +
                    "----------------------------------------------------------");

        }catch (Exception e){
           e.printStackTrace();
        }
       /*
        MyCsUserService myCsUserService = SpringContextUtil.getBean(MyCsUserService.class);
        CsUserService csUserService = SpringContextUtil.getBean(CsUserService.class);
        List<MyCsUser> list = myCsUserService.list();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,1);
        SimpleDateFormat SDF =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (MyCsUser myCsUser : list) {
        String groupNumber = myCsUser.getGroupNumber();
        Boolean perpetual = myCsUser.getPerpetual();
        if(!perpetual){
        CsUser group_number = csUserService.query().eq("group_number", groupNumber).one();
        if(group_number == null){
        group_number = new CsUser();
        myCsUser.setId(null);
        BeanUtils.copyProperties(myCsUser,group_number);
        csUserService.save(group_number);
        System.out.println("新增成功");
        }
        }else{
        CsUser group_number = csUserService.query().eq("group_number", groupNumber).one();
        if(group_number == null){
        group_number = new CsUser();
        myCsUser.setId(null);
        myCsUser.setPerpetual(false);
        myCsUser.setCreateDate(SDF.format(cal.getTime()));
        BeanUtils.copyProperties(myCsUser,group_number);
        csUserService.save(group_number);
        System.out.println("新增成功"+group_number.getGroupNumber());
        }
        }
        }
        */
    }

}
