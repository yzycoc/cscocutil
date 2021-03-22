package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserPrivate;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserPrivateRemove;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserPrivateMapper;
import com.yzycoc.cocutil.SQLAll.service.*;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.result.Result;
import com.yzycoc.from.AddCsUserPrivate;
import com.yzycoc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-11 20:08
 * @Version 1.0
 **/
@Service(value = "CsUserPrivateService")
@Primary
public class CsUserPrivateImpl extends ServiceImpl<CsUserPrivateMapper,CsUserPrivate> implements CsUserPrivateService {

    @Autowired
    private CsUserPrivateRemoveService csUserPrivateRemoveService;
    @Autowired
    private CsUserVipService csUserVipService;
    @Transactional
    @Override
    public void SynchronizeUser(String user) {
        String[] splitNumber = user.split(",");
        if(splitNumber.length >= 2){
            String robotNumber = splitNumber[0];
            List<CsUserPrivate> list = this.query().eq("robot_number", robotNumber).list();
            List<String> csUserPrivateList = new ArrayList<>();
            for (CsUserPrivate csUserPrivate : list) {
                csUserPrivateList.add(csUserPrivate.getQqNumber());
            }
            if(list.size() - splitNumber.length > 120){
                System.out.println("好友数差距太大，暂停处理。机器人BOT:" + robotNumber);
                return ;
            }
            for (int i = 1; i < splitNumber.length; i++) {
                String newCode = splitNumber[i];
                if(csUserPrivateList.contains(newCode)){
                    //清楚 数据库 还存在的
                    csUserPrivateList.remove(newCode);
                }else{
                    CsUserPrivate csUserPrivate = new CsUserPrivate(newCode, robotNumber);
                    this.save(csUserPrivate);
                }
            }
            System.out.println("本次减少用户"+csUserPrivateList.size() + ",机器人BOT:"+ robotNumber);
            for (String deleteQqCode : csUserPrivateList) {
                QueryWrapper qw = new QueryWrapper();
                qw.eq("qq_number",deleteQqCode);
                qw.eq("robot_number",robotNumber);
                this.remove(qw);
                CsUserPrivateRemove csUserPrivateRemove = new CsUserPrivateRemove(deleteQqCode, robotNumber);
                csUserPrivateRemove.setCreateDate(TimeUtiles.getStringDate());
                csUserPrivateRemoveService.save(csUserPrivateRemove);
            }
        }
    }

    @Override
    public Result<?> addCsUserPrivate(AddCsUserPrivate addCsUserPrivate, RedisUtil redisUtil) {
        Object friend = redisUtil.get("friend");
        if(friend == null){
            return  Result.error("好友添加验证码已过期，请重新尝试！");
        }
        String frMSG = String.valueOf(friend);
        if(!(addCsUserPrivate.getMsg()).contains(frMSG))
            return Result.error("验证信息错误，请在http://yzycoc.com/qq/ts/state中复制好友添加码（防机器人）！");
        CsUserVip csUserVip = csUserVipService.query().eq("qqnumber", addCsUserPrivate.getUserNumber()).one();
        if(csUserVip==null){
            csUserVip = new CsUserVip();
        }
        Integer isUser = baseMapper.getIsUser(addCsUserPrivate);
        if(isUser!=null && isUser >= csUserVip.getFriend()){
            Integer count = this.query().eq("robot_number", addCsUserPrivate.getRobotNumber()).eq("qq_number", addCsUserPrivate.getUserNumber()).count();
            count = count == null?0:count;
            isUser = isUser - count;
            if(isUser >= 1){
                return Result.error("普通用户仅可添加一个运行的BOT使用。");
            }
        }
        CsUserPrivate csUserPrivate = new CsUserPrivate(addCsUserPrivate.getUserNumber(),addCsUserPrivate.getRobotNumber());
        csUserPrivate.setCreateDate(TimeUtiles.getStringDate());
        this.save(csUserPrivate);
        return Result.ok("添加成功！");
    }

}
