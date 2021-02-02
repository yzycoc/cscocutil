package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.*;
import com.yzycoc.cocutil.SQLAll.bean.score.ScireStore;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserMapper;
import com.yzycoc.cocutil.SQLAll.service.*;
import com.yzycoc.cocutil.SQLMy.bean.MyCsUserVip;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserVipService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.result.Result;
import com.yzycoc.from.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: cscocutil
 * @description: 仓鼠机器人免费用户
 * @author: yzy
 * @create: 2020-12-14 17:59
 * @Version 1.0
 **/
@Service(value="CsUserService")
@Primary
@Slf4j
public class CsUserImpl  extends ServiceImpl<CsUserMapper, CsUser> implements CsUserService {
    @Autowired//积分商城
    private ScireStoreService scireStoreService;
    @Autowired//积分
    private ScoreService scoreService;
    @Autowired//免费人员兑换记录
    private CsUserLogService csUserLogService;
    @Autowired
    private TsIpsService tsIpsService;
    @Autowired
    private CsUserVipService csUserVipService;
    @Override
    public List<Map<String,Object>> getList(String json) {
        List<Map<String,Object>> result = new ArrayList<>();
        try {
            System.out.println(json);
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject QQlist = jsonObject.getJSONObject("QQlist");
            Set<String> keys = QQlist.keySet();
            List<String> robotListQq = new ArrayList<>();
            for (String robotQQ : keys) {
                robotListQq.add(robotQQ);
                String robot = QQlist.toJSONString();
                new Thread(()->{
                    JSONObject newRobot = JSONObject.parseObject(robot);
                    JSONObject newRobotJSONObject = newRobot.getJSONObject(robotQQ);
                    String name = newRobotJSONObject.getString("昵称");
                    TsIps qqcode = tsIpsService.query().eq("qqcode", robotQQ).one();
                    if(qqcode == null){
                        qqcode = new TsIps();
                        qqcode.setQqcode(robotQQ);
                    }
                    if("测试".equals(name)){
                        qqcode.setCodes("VIP");
                    }else{
                        qqcode.setCodes(null);
                    }
                    qqcode.setIp(ConfigParameter.network_Path_IP);
                    qqcode.setName(name);
                    qqcode.setProt(ConfigParameter.PROT);
                    qqcode.setImage("http://q1.qlogo.cn/g?b=qq&nk="+robotQQ+"&s=640");
                    qqcode.setState(true);
                    qqcode.setCreateDate(TimeUtiles.getStringDate());
                    qqcode.setCreateName("BOT");
                    tsIpsService.saveOrUpdate(qqcode);
                }).start();
                System.out.print(" 获取机器人信息："+robotQQ);
            }
            List<CsUser> list = this.query().in("robot_number", robotListQq).ge("time", TimeUtiles.getStringDate()).list();
            for (CsUser csUser : list) {
                Map<String,Object> re  = new HashMap<>();
                re.put("robot",csUser.getRobotNumber());
                re.put("group",csUser.getGroupNumber());
                Boolean perpetual = csUser.getPerpetual();

                long time = -1;
                if(perpetual!=null&&perpetual){
                    time = -1;
                }else{
                    time = csUser.getTime().getTime();
                }
                re.put("time",time);
                result.add(re);
            }
            list = this.query().in("robot_number", robotListQq).ge("perpetual", "1").list();
            for (CsUser csUser : list) {
                Map<String,Object> re  = new HashMap<>();
                re.put("robot",csUser.getRobotNumber());
                re.put("group",csUser.getGroupNumber());
                Boolean perpetual = csUser.getPerpetual();
                long time = -1;
                if(perpetual!=null&&perpetual){
                    time = -1;
                }else{
                    time = csUser.getTime().getTime();
                }
                re.put("time",time);
                result.add(re);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    @Override
    public synchronized Result<?> addCsUser(AddCsUser addCsUser) {
        Integer cost = 0;
        String getNumber = addCsUser.getNumber();
        try {
            //number 新增的时间
            Integer number = Integer.valueOf(getNumber);
            Calendar cal = Calendar.getInstance();
            if(number == null || number == 0 || number > 100){
                return Result.error("您兑换的单数存在异常。购买数："+getNumber);
            }
            if(number == -2){
                //免费
                Integer grade = addCsUser.getGrade();
                if(grade == null || grade < 16) return Result.error("您的QQ等级还未拥有兑换BOT的权利。");
                Integer qqcode = csUserLogService.query().eq("qqcode", addCsUser.getUserNumber()).count();
                if(qqcode!=null && qqcode>=1) return Result.error("你已存在申请过免费的兑换次数，无法领取哦。");
                Integer count = this.query().eq("create_name", addCsUser.getUserNumber()).count();
                if(count != null && count >= 1) return Result.error("你已存在兑换过的群聊，无法领取哦。");
                CsUser groupNumber = this.query().eq("group_Number", addCsUser.getGroupNumber()).one();
                if(groupNumber !=null) return Result.error("此群以没有申请免费的使用资格。");
                groupNumber = new CsUser();
                groupNumber.setCreateDate(TimeUtiles.getStringDate());
                groupNumber.setCreateName(addCsUser.getUserNumber());
                groupNumber.setGroupNumber(addCsUser.getGroupNumber());
                groupNumber.setRobotNumber(addCsUser.getRobotNumber());
                groupNumber.setPerpetual(false);
                cal.add(Calendar.DATE,10);
                groupNumber.setTime(cal.getTime());
                this.saveOrUpdate(groupNumber);
                csUserLogService.save(new CsUserLog(addCsUser.getUserNumber()));
                return Result.ok("\n[bq124]免费授权申请成功\n※剩余授权时长:\n"+(groupNumber.getPerpetual()?"永久授权":TimeUtiles.datetimeFormat.get().format(groupNumber.getTime()))+"\n※bot管理员:\n"+groupNumber.getCreateName());
            }
            CsUserVip csUserVip = csUserVipService.query().eq("qqnumber", addCsUser.getUserNumber()).one();
            if(csUserVip == null){
                csUserVip = new CsUserVip();
            }
            Integer groupEternity = csUserVip.getGroupEternity();
            if(number == -1){
                if(groupEternity<=0){
                    return Result.error("你好，你还没有群永久授权的资格。");
                }
                cost = 0;
            }else{
                cost = scireStoreService.getById(new ScireStore(3)).getNumber() * number;
            }

            Score one = scoreService.query().eq("qq_Num", addCsUser.getUserNumber()).one();
            Integer userCost = 0;
            if(one!=null)
                userCost = one.getNumber();
            if(userCost>=cost){//判断 用户积分 是否大于 需要兑换的积分
                //判断机器人加入的群
                Integer robot_number = this.query().eq("robot_Number", addCsUser.getRobotNumber()).count();
                if(robot_number!=null&&robot_number>480){
                    return Result.error("此bot授权的群已达到480。[PS：一个QQ号做多添加500个群]，为避免加群失败，请跟换bot进行授权。");
                }
                Boolean islyh = false;//是否是老用户 续费
                CsUser groupNumber = this.query().eq("group_Number", addCsUser.getGroupNumber()).one();
                if(groupNumber!=null){
                    if(groupNumber.getPerpetual()){
                        return Result.error("此群已是永久授权，无法再次购买。bot管理:"+groupNumber.getCreateName());
                    }else if(new Date().getTime()<=groupNumber.getTime().getTime()&&!groupNumber.getRobotNumber().equals(addCsUser.getRobotNumber())){
                        return Result.error("此群已和bot："+groupNumber.getRobotNumber()+"绑定使用，需要新增使用时间请在bot:"+groupNumber.getRobotNumber()+"下进行次指令操作。需要更换绑定机器人请通知bot管理："+groupNumber.getCreateName()+"进行更换绑定bot。");
                    }else if(new Date().getTime()<=groupNumber.getTime().getTime()){
                        cal.setTime(groupNumber.getTime());
                        islyh = true;
                    }else{
                        groupNumber.setCreateName(addCsUser.getUserNumber());
                    }
                }else{
                    //判断是否为购买心机器人
                    Integer count = this.query().eq("create_name", addCsUser.getUserNumber()).count();
                    Integer group = csUserVip.getGroup();
                    if(count!= null && count >= group)
                        return Result.error("普通用户仅可为一个群进行授权使用。");
                    groupNumber = new CsUser();
                    groupNumber.setCreateDate(TimeUtiles.getStringDate());
                    groupNumber.setCreateName(addCsUser.getUserNumber());
                    groupNumber.setGroupNumber(addCsUser.getGroupNumber());
                    groupNumber.setRobotNumber(addCsUser.getRobotNumber());
                }
                if(number == -1){
                    groupNumber.setPerpetual(true);
                    csUserVip.setGroupEternity(groupEternity-1);
                    csUserVipService.updateById(csUserVip);
                }else{
                    groupNumber.setPerpetual(false);
                }
                cal.add(Calendar.MONTH,number);
                groupNumber.setTime(cal.getTime());
                this.saveOrUpdate(groupNumber);
                //积分变更
                one.setNumber(one.getNumber() - cost);
                scoreService.saveOrUpdate(one);
                if(islyh){
                    return Result.ok("\n[bq124]续费成功\n※剩余授权时长:\n"+(groupNumber.getPerpetual()?"永久授权":TimeUtiles.datetimeFormat.get().format(groupNumber.getTime()))+"\n※bot管理员:\n"+groupNumber.getCreateName()+"\nPS：续费不会更换bot管理哦。");
                }else{
                    return Result.ok("\n[bq124]兑换成功\n※剩余授权时长:\n"+(groupNumber.getPerpetual()?"永久授权":TimeUtiles.datetimeFormat.get().format(groupNumber.getTime()))+"\n※bot管理员:\n"+groupNumber.getCreateName());
                }
            }else{
                return Result.error("您积分不足:"+cost+"，无法兑换。");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("新增用户群出线异常！");
        }
        return Result.error(404,"操作失败。");
    }

    /***
     * 更换bot绑定的群
     * @param updateGroup
     * @return
     */
    @Transactional
    @Override
    public synchronized Result<?> updateGroup(UpdateGroup updateGroup) {
        try {
            CsUser groupNumber = this.query().eq("group_Number", updateGroup.getGroupNumber()).one();
            if(groupNumber == null) return Result.error(updateGroup.getGroupNumber()+"此群未绑定任何bot,无法更换。");
            if(!groupNumber.getCreateName().equals(updateGroup.getUserNumber()))  return Result.error("权限不足，本群bot管理为"+groupNumber.getCreateName()+"。请通知bot管理进行操作，或者在机器人到期后，您通过兑换成为此群新的bot管理。");
            if(groupNumber.getTime().getTime()<new Date().getTime() && !groupNumber.getPerpetual()) return Result.error(updateGroup.getGroupNumber()+",此群已无授权时间，请重新授权。");
            Integer robot_number = this.query().eq("robot_Number", updateGroup.getRobotNumber()).count();
            if(robot_number!=null&&robot_number>480)  return Result.error("此bot授权的群已达到480。[PS：一个QQ号做多添加500个群]，为避免加群失败，请跟换bot进行授权。");
            if(updateGroup.getRobotNumber().equals(groupNumber.getRobotNumber())) return Result.error("已授权的BOT机器人为更换的BOT，无需更换。");
            String createDate = groupNumber.getCreateDate();
            Date parse = TimeUtiles.datetimeFormat.get().parse(createDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.DATE,2);
            if(new Date().getTime()<cal.getTime().getTime()){
                return Result.error("更换bot的周期为5日一次，请勿频繁更换哦。下次可更换的时间：\n"+TimeUtiles.time_sdf.get().format(cal.getTime()));
            }
            Integer cost = scireStoreService.getById(new ScireStore(9)).getNumber();
            Score one = scoreService.query().eq("qq_Num", updateGroup.getUserNumber()).one();
            Integer userNumber = 0;
            if(one!=null)
                userNumber = one.getNumber();
            if(userNumber>cost){
                groupNumber.setRobotNumber(updateGroup.getRobotNumber());
                groupNumber.setCreateDate(TimeUtiles.getStringDate());
                this.saveOrUpdate(groupNumber);
                one.setNumber(one.getNumber() - cost);
                scoreService.saveOrUpdate(one);
                return Result.ok("※更换成功。\n※请将原bot从群移出，以免增加服务器负担和群内刷屏。");
            }else{
                return Result.error("您积分不足:"+cost+"，无法更换。");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("未知原因，造成处理失败。");
        }

    }

    @Override
    public Result<?> updateBotAdmin(UpdateBot updateBot) {
        try {
            CsUser csUser = this.query().in("group_Number", updateBot.getGroupNumber()).one();
            if(csUser==null)
                return Result.error(updateBot.getGroupNumber()+"此群未授权,无法更换。");
            if(csUser.getTime().getTime()<new Date().getTime() && !csUser.getPerpetual()) return Result.error(updateBot.getGroupNumber()+",此群已无授权时间，请重新授权。");

            if(!csUser.getCreateName().equals(updateBot.getUserNumber()))
                return Result.error("权限不足，本群bot管理为"+csUser.getCreateName()+"。请通知bot管理进行操作，或者在机器人到期后，您通过兑换成为此群新的bot管理。");
            Integer cost = scireStoreService.getById(new ScireStore(10)).getNumber();
            Score user = scoreService.query().eq("qq_Num", updateBot.getUserNumber()).one();
            Integer userCost = 0;
            if(user!=null) userCost = user.getNumber();

            Score newUser = scoreService.query().eq("qq_Num", updateBot.getNewNumber()).one();
            Integer newUserCost = 0;
            if(newUser!=null)
                newUserCost = newUser.getNumber();
            if(userCost<cost){
                return Result.error(updateBot.getUserNumber()+":积分不足:"+cost+"，无法更换。");
            }else if(newUserCost < cost){
                return Result.error(updateBot.getNewNumber()+":积分不足:"+cost+"，无法更换。");
            }
            csUser.setCreateName(updateBot.getNewNumber());
            this.saveOrUpdate(csUser);
            user.setNumber(user.getNumber() - cost);
            scoreService.saveOrUpdate(user);
            newUser.setNumber(newUser.getNumber() - cost);
            scoreService.saveOrUpdate(newUser);
            return Result.ok("更换成功，"+updateBot.getNewNumber()+"已成为"+updateBot.getGroupNumber()+"新的BOT管理。");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("未知原因，造成处理失败。");
        }
    }

    @Override
    public Result<?> getAuthorization(String userNumber) {
        List<CsUser> list = this.query().eq("create_name", userNumber).list();
        if(list != null && list.size()>0){
            StringBuffer result = new StringBuffer();
            Map<String,List<CsUser>> cs = new HashMap<>();
            for (CsUser csUser : list) {
                List<CsUser> csUsers = cs.get(csUser.getRobotNumber());
                if(csUsers == null){
                    csUsers = new ArrayList<>();
                    csUsers.add(csUser);
                    cs.put(csUser.getRobotNumber(),csUsers);
                }else{
                    csUsers.add(csUser);
                    cs.put(csUser.getRobotNumber(),csUsers);
                }
            }
            for (String key : cs.keySet()) {
                result.append("\\uD83E\\uDD16"+key+"\\uD83E\\uDD16\n");
                List<CsUser> csUsers = cs.get(key);
                for (CsUser csUser : csUsers) {
                    result.append("\\uD83D\\uDC65群"+csUser.getGroupNumber()+"\n");
                    if(csUser.getPerpetual()){
                        result.append("\\uD83D\\uDC8E永久授权\\uD83D\\uDC8E\n");
                    }else{
                        result.append("\\u23F3剩余"+TimeUtiles.differentDays(new Date(),csUser.getTime())+"天\\u23F3\n");
                    }
                }
            }
            result.append("◎━━━----◎----━━━◎");
            return Result.ok(result.toString());
        }
        return Result.ok("您未在任何群进行授权。");
    }
}
