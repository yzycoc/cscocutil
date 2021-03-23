package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.bean.score.ScireStore;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipApplyForLog;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipLog;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserPrivateMapper;
import com.yzycoc.cocutil.SQLAll.mapper.CsUserVipMapper;
import com.yzycoc.cocutil.SQLAll.service.*;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserService;
import com.yzycoc.cocutil.service.accomplish.image.ImageVip;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.XmlCustom;
import com.yzycoc.custom.result.Xlzdom4jXmlResult;
import com.yzycoc.from.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-21 11:41
 * @Version 1.0
 **/
@Service(value="CsUserVipService")
@Primary
@Slf4j
public class CsUserVipImpl extends ServiceImpl<CsUserVipMapper, CsUserVip> implements CsUserVipService {
    @Autowired//积分商城
    private ScireStoreService scireStoreService;
    @Autowired//转账记录
    private VipApplyForLogService logVipApplyForService;
    @Autowired
    private VipLogService vipLogService;
    @Autowired
    private ScoreUuidService scoreUuidService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CsUserService csUserService;
    @Autowired
    private CsUserPrivateMapper csUserPrivateMapper;
    @Override
    public List<Dom4jResult> dom4jXml(String xml, String robotNumber, String moneyNumber) {
        //储存的是用户日志
        List<Dom4jResult> result = new ArrayList<>();
        VipLog vipLog = new VipLog();
        vipLog.setMsg(xml);
        try {
            if(xml.contains("收到一笔转账")){
                vipLog.setCreateDate(TimeUtiles.getStringDate());
                Xlzdom4jXmlResult date = XmlCustom.lzdom4jXmlResult(xml);
                //获取赞助的个人信息
                List<VipApplyForLog> list = logVipApplyForService.query().eq("uuid", date.getUuid().getVal()).orderByDesc("create_date").list();
                if(list == null || list.size()  == 0){
                    //未找到收益人
                    Dom4jResult re = new Dom4jResult();
                    re.setQq(date.getVal().getQqnumber());
                    re.setMsg("本次支付检测失败，您本次赞助并不会对你带来任何收益！因信息校验失败，如果疑问请反馈：936642284，带上截图及时间！");
                    re.setSuccess(false);
                    result.add(re);
                    vipLog.setCreateName("失败");
                    vipLogService.save(vipLog);
                    return result;
                }
                VipApplyForLog uid = list.get(0);
                uid.setNewRobotNumber(robotNumber);
                //会员记录的信息
                CsUserVip one = this.query().eq("qqnumber",uid.getUserNumber()).one();
                //购买情况
                Integer number = uid.getNumber();
                Integer day = number * 31;
                SimpleDateFormat sdf = TimeUtiles.datetimeFormat.get();
                Calendar cal = Calendar.getInstance();
                if(one == null){
                    one = new CsUserVip();
                    one.setQqnumber(uid.getUserNumber());
                    one.setNumber(new BigDecimal(0));
                    if(number == -1){
                        one.setEternity(true);
                        one.setVipMember(8888);
                        int i = one.getGroupEternity() == null ? 0 : one.getGroupEternity();
                        one.setGroupEternity(i+1);
                    }else{
                        one.setEternity(false);
                        one.setVipMember(day);
                    }
                    cal.add(Calendar.DATE,day);
                    one.setEndTime(sdf.format(cal.getTime()));
                }else{
                    //续费
                    vipLog.setRemark("兑换方续时");
                    if(one.getEternity()){
                        //已经是永久授权的人 修改金币余额
                        BigDecimal money = one.getNumber();
                        BigDecimal sum =money.add(new BigDecimal(uid.getCreateName()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
                        one.setNumber(sum);
                        //新增一个永久可授权的群聊
                        int i = one.getGroupEternity() == null ? 0 : one.getGroupEternity();
                        one.setGroupEternity(i+1);
                    }else{
                        //不是永久的用户
                        if(number == -1){
                            //永久授权
                            one.setEternity(true);
                            one.setVipMember(8888);
                            int i = one.getGroupEternity() == null ? 0 : one.getGroupEternity();
                            one.setGroupEternity(i+1);
                        }else{
                            one.setVipMember(one.getVipMember() + day);
                            Date parse = sdf.parse(one.getEndTime());
                            if(new Date().getTime()<=parse.getTime()){
                                cal.setTime(parse);
                            }
                            cal.add(Calendar.DATE,day);
                            one.setEndTime(sdf.format(cal.getTime()));
                        }
                    }
                }
                //处理VIP会员表数据
                this.saveOrUpdate(one);
                //处理充值情况表的信息
                String newUserNumber = uid.getNewUserNumber()==null?"":(uid.getNewUserNumber()+",");
                uid.setNewUserNumber(newUserNumber+date.getVal().getQqnumber());
                uid.setStatus(true);
                logVipApplyForService.updateById(uid);
                //处理购买日志信息 合成图片
                vipLog.setQqcode(uid.getUserNumber());
                //奖励对应
                String uuid = UUID.randomUUID().toString();
                if(!one.getQqnumber().equals(date.getVal().getQqnumber())){
                    Dom4jResult re = new Dom4jResult();
                    re.setQq(date.getVal().getQqnumber());
                    re.setMsg(uuid);
                    re.setSuccess(true);
                    result.add(re);
                }
                Dom4jResult re = new Dom4jResult();
                re.setQq(one.getQqnumber());
                re.setMsg(uuid);
                re.setSuccess(true);
                result.add(re);
                //其他奖励数据 积分
                Score qq_num = scoreService.query().eq("qq_num", one.getQqnumber()).one();
                if(qq_num == null){
                    qq_num = new Score();
                    qq_num.setQqNum(one.getQqnumber());
                    qq_num.setNumber(0);
                }
                Integer scoreNumber = qq_num.getNumber();
                if(number == -1){
                    scoreNumber = scoreNumber + (30*600);
                    vipLog.setScore("18000");
                }else{
                    scoreNumber = scoreNumber + (number*600);
                    vipLog.setScore(String.valueOf(number*600));
                }
                qq_num.setNumber(scoreNumber);
                qq_num.setCreateDate(TimeUtiles.getStringDate());
                scoreService.saveOrUpdate(qq_num);
                vipLog.setMoneyNumber(date.getVal().getQqnumber());
                vipLog.setMoney(date.getMoney().getVal());
                vipLog.setCreateDate(TimeUtiles.getStringDate());
                vipLog.setCreateName("成功");
                vipLog.setRobotNumber(robotNumber);
                vipLog.setUuid(uuid);
            }else{
                vipLog.setCreateName("无法解析！");
            }
        }catch (Exception e){
            e.printStackTrace();
            //未找到收益人
            Dom4jResult re = new Dom4jResult();
            re.setQq("936642284");
            re.setMsg("支付情况出现异常，紧急，请进行处理。");
            re.setSuccess(false);
            result.add(re);
            vipLog.setCreateName("失败");
        }
        vipLog.setRobotMoney(moneyNumber);
        vipLogService.save(vipLog);
        return result;
    }

    @Override
    public void resultMyScoreOkImage(String userNumber, HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = null;
        try {
            Score score = scoreService.query().eq("qq_num", userNumber).one();
            CsUserVip csUserVip = this.query().eq("qqnumber", userNumber).one();
            Integer count = csUserService.query().eq("create_name", userNumber).count();
            AddCsUserPrivate addCsUserPrivate = new AddCsUserPrivate();
            addCsUserPrivate.setUserNumber(userNumber);
            Integer privateNumber = csUserPrivateMapper.getIsUser(addCsUserPrivate);

            image = new ImageVip().resultMyScoreOkImage(userNumber,score,csUserVip,count,privateNumber);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            response.setContentType("image/png");
            response.addHeader("Content-Disposition", "attachment; filename=" + "QrCode");
            ServletOutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = fis.read(buf)) != -1)
                out.write(buf, 0, n);
            fis.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 申请转载数据
     * @param vip
     * @return
     */
    @Override
    public VipApplyForResult applyFor(VipApplyFor vip) {
        //需要的费用
        Integer cost = 0;
        VipApplyForResult result = new VipApplyForResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE,-5);
            VipApplyForLog one = logVipApplyForService.query().eq("user_number", vip.getUserNumber()).ge("create_date",sdf.format(cal.getTime())).one();
            if(one!=null&&"已生成".equals(one.getUuidstatus())){
                result.setSuccess(false);
                result.setMessage("客官,赞助需冷静,您距上次赞助申请还未满5分钟，请在考虑考虑吧。[bq60]");
                return result;
            }
            //新增的时间
            Integer number = vip.getNumber();
            if(number == null || number == 0 || number > 100){
                result.setSuccess(false);
                result.setMessage("您赞助的单数存在异常。异常数："+number);
                return result;
            }else if(number == -1){
                cost = scireStoreService.getById(new ScireStore(889)).getNumber();
            }else{
                cost = scireStoreService.getById(new ScireStore(888)).getNumber() * number;
            }
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            VipApplyForLog vipApplyForLog = new VipApplyForLog();
            vipApplyForLog.setNumber(number);
            vipApplyForLog.setUuid(uuid);
            vipApplyForLog.setCreateDate(TimeUtiles.getStringDate());
            vipApplyForLog.setCreateName(String.valueOf(cost));
            vipApplyForLog.setUserNumber(vip.getUserNumber());
            vipApplyForLog.setRobotNumber(vip.getRobotNumber());
            logVipApplyForService.save(vipApplyForLog);
            result.setCost(cost);
            result.setUuid(uuid);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("统计金额时出现异常，请稍后重试！");
        }
        return result;
    }

    @Override
    public void resultOkImage(String uuid, HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = null;
        try {
            VipLog vipLog = vipLogService.query().eq("uuid", uuid).one();
            Score score = scoreService.query().eq("qq_num", vipLog.getQqcode()).one();
            CsUserVip csUserVip = this.query().eq("qqnumber", vipLog.getQqcode()).one();
            image = new ImageVip().resultOkImage(vipLog,score,csUserVip);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            response.setContentType("image/png");
            response.addHeader("Content-Disposition", "attachment; filename=" + "QrCode");
            ServletOutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = fis.read(buf)) != -1)
                out.write(buf, 0, n);
            fis.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StringBuffer getMyScore(String userNumber) {
        Score user = scoreService.query().eq("qq_Num", userNumber).one();
        Integer userCost = 0;
        if(user!=null)
            userCost = user.getNumber();
        StringBuffer result = new StringBuffer();
        Integer count = csUserService.query().eq("create_name", userNumber).count();
        AddCsUserPrivate addCsUserPrivate = new AddCsUserPrivate();
        addCsUserPrivate.setUserNumber(userNumber);
        Integer privateNumber = csUserPrivateMapper.getIsUser(addCsUserPrivate);
        result.append("┏[@"+userNumber+"]");
        result.append("\n┣积分剩余："+userCost);
        result.append("\n┣可授权群：1个");
        result.append("\n┣已授权群："+count+"个");
        result.append("\n┣可添加机器人：1个");
        result.append("\n┗已添加机器人："+privateNumber+"个");
        return result;
    }
}
