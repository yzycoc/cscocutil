package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.*;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserFreeLog;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserLog;
import com.yzycoc.cocutil.SQLAll.bean.score.ScireStore;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserMapper;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserPrivateMapper;
import com.yzycoc.cocutil.SQLAll.service.*;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserService;
import com.yzycoc.cocutil.service.accomplish.image.ImageCsUser;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.result.Result;
import com.yzycoc.from.*;
import com.yzycoc.util.tableImage.ImageTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
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
    /*@Autowired//免费人员兑换记录
    private CsUserLogService csUserLogService;*/
    @Autowired
    private TsIpsService tsIpsService;
    @Autowired
    private CsUserVipService csUserVipService;
    @Autowired
    private MyCsUserService myCsUserService;
    @Autowired
    private CsUserPrivateService csUserPrivateService;
    /**
     * 申请过免费授权的用户
     */
    @Autowired
    private CsUserFreeLogService csUserFreeLogService;
    @Override
    public List<CsUser> getList(String json) {
        List<CsUser> result = new ArrayList<>();
        try {
            System.out.println("\n----------------------------------------------------------\n" +
                    json +
                    "\n----------------------------------------------------------");
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
                result.add(csUser);
            }
            list = this.query().in("robot_number", robotListQq).ge("perpetual", "1").list();
            for (CsUser csUser : list) {
                result.add(csUser);
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
                Integer qqcode = csUserFreeLogService.query().eq("user_number", addCsUser.getUserNumber()).count();
                if(qqcode!=null && qqcode>=1) return Result.error("你已存在申请过免费的兑换次数，无法领取哦。");
                Integer count = this.query().eq("create_name", addCsUser.getUserNumber()).count();
                if(count != null && count >= 1) return Result.error("你已存在兑换过的群聊，无法领取哦。");
                Integer group_number = csUserFreeLogService.query().eq("group_number", addCsUser.getUserNumber()).count();
                if(group_number !=null && qqcode>=1) return Result.error("此群以没有申请免费的使用资格。");
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
                //保存免费授权人的信息
                CsUserFreeLog csUserFreeLog = new CsUserFreeLog();
                csUserFreeLog.setCreateName(addCsUser.getRobotNumber());
                csUserFreeLog.setUserNumber(addCsUser.getUserNumber());
                csUserFreeLog.setGroupNumber(addCsUser.getGroupNumber());
                csUserFreeLog.setCreateDate(TimeUtiles.getStringDate());
                csUserFreeLogService.save(csUserFreeLog);
                return Result.ok("\n[bq124]免费授权申请成功\n※剩余授权时长:\n"+(groupNumber.getPerpetual()?"永久授权":TimeUtiles.datetimeFormat.get().format(groupNumber.getTime()))+"\n※bot管理员:\n"+groupNumber.getCreateName());
            }
            CsUserVip csUserVip = csUserVipService.query().eq("qqnumber", addCsUser.getUserNumber()).one();
            if(csUserVip == null){
                csUserVip = new CsUserVip();
            }

            if(number == -1){
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
                Boolean islyh = false;//是否是老用户 续费
                CsUser groupNumber = this.query().eq("group_Number", addCsUser.getGroupNumber()).one();
                if(groupNumber!=null){
                    if(groupNumber.getPerpetual()){
                        return Result.error("此群已是永久授权，无法再次授权。bot管理:"+groupNumber.getCreateName());
                    }else if(new Date().getTime()<=groupNumber.getTime().getTime()&&!groupNumber.getRobotNumber().equals(addCsUser.getRobotNumber())){
                        return Result.error("此群已和bot："+groupNumber.getRobotNumber()+"绑定使用，需要新增使用时间请在bot:"+groupNumber.getRobotNumber()+"下进行次续费授权操作。\n需要更换绑定机器人请通知bot管理："+groupNumber.getCreateName()+"在新机器人里进行更换绑定bot。");
                    }else if(new Date().getTime()>groupNumber.getTime().getTime()&&!groupNumber.getRobotNumber().equals(addCsUser.getRobotNumber())){
                        groupNumber.setRobotNumber(addCsUser.getRobotNumber());
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
                        return Result.error("您已达到群授权上限，允许授权数："+group+"，您已授权："+count+"个群聊。您可以通过赞助的方式增加群授权数量。");
                    groupNumber = new CsUser();
                    groupNumber.setCreateDate(TimeUtiles.getStringDate());
                    groupNumber.setCreateName(addCsUser.getUserNumber());
                    groupNumber.setGroupNumber(addCsUser.getGroupNumber());
                    groupNumber.setRobotNumber(addCsUser.getRobotNumber());
                }
                Integer robot_number = this.query()
                        .eq("robot_Number", addCsUser.getRobotNumber()).ge("time", TimeUtiles.getStringDate())
                        .or()
                        .eq("robot_Number", addCsUser.getRobotNumber()).eq("perpetual", "1")
                        .count();
                if(robot_number!=null&&robot_number>500&&!islyh){
                    return Result.error("此bot授权的群已达到500。[PS：一个QQ号做多添加500个群]，为避免加群失败，请跟换bot进行授权。\n您可以访问http://yzycoc.com/qq/ts/state查询正在运行的其他机器人！");
                }
                if(number == -1){
                    groupNumber.setPerpetual(true);
                    Integer groupEternity = csUserVip.getGroupEternity();
                    //获取该用户总共绑定的群聊 0
                    Integer count = this.query().eq("create_name", addCsUser.getUserNumber()).eq("perpetual", "1").count();
                    if(groupEternity<=count){
                        return Result.error("你好，你没有群永久授权的资格。需要永久授权，您可以赞助以后获得，多次赞助可累加永久授权资格！");
                    }
                }else{
                    groupNumber.setPerpetual(false);
                }
                cal.add(Calendar.MONTH,number);
                groupNumber.setTime(cal.getTime());
                this.saveOrUpdate(groupNumber);
                //积分变更
                one.setNumber(one.getNumber() - cost);
                scoreService.saveOrUpdate(one);
                //同步更新后的内容
                new Thread(()->{
                    myCsUserService.Synchronization();
                }).start();
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
                return Result.error("更换bot的周期为2日一次，请勿频繁更换哦。\n下次可更换的时间：\n"+TimeUtiles.time_sdf.get().format(cal.getTime()));
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
                //同步更新后的内容
                new Thread(()->{
                    myCsUserService.Synchronization();
                }).start();
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

            String createDate = csUser.getCreateDate();
            Date parse = TimeUtiles.datetimeFormat.get().parse(createDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.DATE,2);
            if(new Date().getTime()<cal.getTime().getTime()){
                return Result.error("更换bot管理的周期为2日一次，请勿频繁更换哦。\n下次可更换的时间：\n"+TimeUtiles.time_sdf.get().format(cal.getTime()));
            }
            //获取兑换需要的积分
            Integer cost = scireStoreService.getById(new ScireStore(10)).getNumber();
            //查询当前用户所有的积分
            Score user = scoreService.query().eq("qq_Num", updateBot.getUserNumber()).one();
            Integer userCost = 0;
            if(user!=null) userCost = user.getNumber();
            //查询新用户的积分
            Score newUser = scoreService.query().eq("qq_Num", updateBot.getNewNumber()).one();
            Integer newUserCost = 0;
            if(newUser!=null)
                newUserCost = newUser.getNumber();
            if(userCost<cost){
                return Result.error(updateBot.getUserNumber()+":积分不足:"+cost+"，无法更换。");
            }else if(newUserCost < cost){
                return Result.error(updateBot.getNewNumber()+":积分不足:"+cost+"，无法更换。");
            }
            CsUserVip csUserVip = csUserVipService.query().eq("qqnumber", updateBot.getNewNumber()).one();
            if(csUser.getPerpetual()!=null&&csUser.getPerpetual()){
                Integer groupEternity = csUserVip.getGroupEternity();
                Integer count = this.query().eq("create_name", updateBot.getUserNumber()).eq("perpetual", "1").count();
                if(groupEternity<=count){
                    return Result.error("你好，待转的新BOT管理没有永久授权的资格，无法进行转让。");
                }
            }else{
                //查询新授权的用户，是否拥有授权资格
                Integer count = this.query().eq("create_name", updateBot.getNewNumber()).count();
                Integer group = csUserVip.getGroup();
                if(count!= null && count >= group)
                    return Result.error("待转的新BOT管理“"+updateBot.getNewNumber()+"”已达到群授权上限，允许授权数："+group+"，他已授权："+count+"个群聊，无法进行转让。");
            }
            csUser.setCreateDate(TimeUtiles.getStringDate());
            csUser.setCreateName(updateBot.getNewNumber());
            this.saveOrUpdate(csUser);
            user.setNumber(user.getNumber() - cost);
            scoreService.saveOrUpdate(user);
            newUser.setNumber(newUser.getNumber() - cost);
            scoreService.saveOrUpdate(newUser);
            //同步更新后的内容
            new Thread(()->{
                myCsUserService.Synchronization();
            }).start();
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
                        int i = TimeUtiles.differentDays(new Date(), csUser.getTime());
                        if(i>0){
                            result.append("\\u23F3剩余"+i+"天\\u23F3\n");
                        }else{
                            result.append("\\u23F3已过期\\u23F3\n");
                        }
                    }
                }
            }
            result.append("◎━━━----◎----━━━◎");
            return Result.ok(result.toString());
        }
        return Result.ok("您未在任何群进行授权。");
    }

    @Override
    public ClanResult getAuthorization2(String userNumber) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            List<CsUserImageVo> list = baseMapper.listCreateNumber(userNumber);
            if(list.size()==0){
                return new ClanResult(false,"您还未授权过任何群里！");
            }
            List<CsUserImage> data = new ArrayList<>();
            for (CsUserImageVo csUserImageVo : list) {
                CsUserImage csUserImage = new CsUserImage();
                csUserImage.setRemark(csUserImageVo.getRemark());
                csUserImage.setRobotNumber(csUserImageVo.getRobotNumber());
                csUserImage.setGroupNumber(csUserImageVo.getGroupNumber());
                csUserImage.setName(csUserImageVo.getName());
                if(csUserImageVo.getPerpetual()){
                    csUserImage.setPerpetual("★永久授权★");
                }else{
                    int i = TimeUtiles.differentDays(new Date(), csUserImageVo.getTime());
                    if(i>1){
                        csUserImage.setPerpetual("剩余"+i+"天");
                    }else if(i >= 0){
                        csUserImage.setPerpetual(sdf.format(csUserImageVo.getTime()));
                    }else{
                        csUserImage.setPerpetual("已过期"+(i*-1) + "天");
                    }
                }
                data.add(csUserImage);
            }
            try {
                ClanResult realTime = new ImageCsUser().getRealTime(data,userNumber);
                return realTime;
            }catch (Exception e){
                Map<String,List<CsUserImageVo>> cs = new HashMap<>();
                for (CsUserImageVo csUser : list) {
                    List<CsUserImageVo> csUsers = cs.get(csUser.getRobotNumber());
                    if(csUsers == null){
                        csUsers = new ArrayList<>();
                        csUsers.add(csUser);
                        cs.put(csUser.getRobotNumber(),csUsers);
                    }else{
                        csUsers.add(csUser);
                        cs.put(csUser.getRobotNumber(),csUsers);
                    }
                }
                StringBuffer result = new StringBuffer();
                for (String key : cs.keySet()) {
                    result.append("\\uD83E\\uDD16"+key+"\\uD83E\\uDD16\n");
                    List<CsUserImageVo> csUsers = cs.get(key);
                    for (CsUserImageVo csUser : csUsers) {
                        result.append("\\uD83D\\uDC65群"+csUser.getGroupNumber()+"\n");
                        if(csUser.getPerpetual()){
                            result.append("\\uD83D\\uDC8E永久授权\\uD83D\\uDC8E\n");
                        }else{
                            int i = TimeUtiles.differentDays(new Date(), csUser.getTime());
                            if(i>0){
                                result.append("\\u23F3剩余"+i+"天\\u23F3\n");
                            }else{
                                result.append("\\u23F3已过期\\u23F3\n");
                            }
                        }
                    }
                }
                return new ClanResult(false, result.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ClanResult(false,"您还未授权过任何群里！");
    }

    @Override
    public Result<?> deleteGroup(DeleteBot deleteBot) {
        try {
            CsUser csUser = this.query().in("group_Number", deleteBot.getGroupNumber()).one();
            if(csUser==null) return Result.error(deleteBot.getGroupNumber()+"此群未授权,无法进行解除。");

            if(!deleteBot.getUserNumber().equals(csUser.getCreateName()))
                return Result.error("权限不足，本群bot管理为"+csUser.getCreateName()+",请通知bot管理进行操作。");

            if(!deleteBot.getRobotNumber().equals(csUser.getRobotNumber()))
                return Result.error("请在原机器人："+csUser.getRobotNumber()+"进行群解绑指令操作。");
            String createDate = csUser.getCreateDate();
            Date parse = TimeUtiles.datetimeFormat.get().parse(createDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse);
            cal.add(Calendar.DATE,2);
            if(new Date().getTime()<cal.getTime().getTime())
                return Result.error("群授权相关操作的冷却周期为2日，请勿频繁操作哦。仓鼠还是希望和您考虑清楚后在操作了。\n下次可更换的时间：\n"+TimeUtiles.time_sdf.get().format(cal.getTime()));
            this.removeById(csUser);
            return Result.ok("群授权解绑成功了，这就要和"+deleteBot.getGroupNumber()+"的群友再见了，有幸和大家共处的这段时间，可能由于是我不够优秀，但是大家需要我随时都会回来的，我也会一直进步的，再会[bq39]。");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("未知原因，造成处理失败。");
    }


}
