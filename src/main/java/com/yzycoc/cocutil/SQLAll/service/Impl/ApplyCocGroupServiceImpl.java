package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocGroup;
import com.yzycoc.cocutil.SQLAll.bean.apply.ApplyCocUser;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.CocBinding;
import com.yzycoc.cocutil.SQLAll.mapper.ApplyCocGroupMapper;
import com.yzycoc.cocutil.SQLAll.service.ApplyCocGroupService;
import com.yzycoc.cocutil.SQLAll.service.ApplyCocUserService;
import com.yzycoc.cocutil.SQLAll.service.CocBindingService;
import com.yzycoc.cocutil.SQLAll.service.CsUserService;
import com.yzycoc.cocutil.service.accomplish.image.ImageApply;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.Utf8Util;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.custom.result.Result;
import com.yzycoc.custom.result.apply.AddApplyFrom;
import com.yzycoc.custom.result.apply.AddUserForm;
import com.yzycoc.custom.result.apply.UpdateGroupForm;
import com.yzycoc.custom.result.apply.UpdateUserForm;
import com.yzycoc.util.BaseLocalThreadPool;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cscocutil
 * @description:
 * @author: 936642284
 * @create: 2021-02-28 16:44
 * @Version 1.0
 **/
@Primary
@Service(value = "ApplyCocGroupService")
public class ApplyCocGroupServiceImpl extends ServiceImpl<ApplyCocGroupMapper, ApplyCocGroup> implements ApplyCocGroupService {
    @Autowired
    private CocEquilibrium cocHttp;
    @Autowired
    private ApplyCocUserService applyCocUserService;
    @Autowired
    private CocBindingService cocBindingService;
    @Autowired
    private CsUserService csUserService;

    @Override
    public Result AddAplyFrom(AddApplyFrom addfrom) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "addPlayerGroup" + addfrom.getGroupNumber();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("本群上一个报名请求还在处理中，请待上一个开始报名结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            //查询 是否存在报名的列表
            addfrom.setType(addfrom.getType().replace("开始报名", ""));
            if (StringUtils.isEmpty(addfrom.getType())) {
                return Result.error("开启报名指令错误，请输入：\n开始报名XX#部落");
            }
            Integer count = query().eq("type", addfrom.getType()).eq("group_number", addfrom.getGroupNumber()).eq("status", "报名中").count();
            if (count != null && count > 0)
                return Result.error("本群还存在尚未结束的" + addfrom.getType() + "报名，请先结束报名，才能开启新的一轮报名。");
            String tag = addfrom.getTag();
            if ("我的部落".equals(tag)) {
                CocBinding one = cocBindingService.query().eq("qqcode", addfrom.getUserNumber()).eq("msg", "我的部落").one();
                if (one == null) {
                    return Result.error("报名失败，因为你还未绑定你自己的部落，无法使用我的部落进行报名。");
                }
                tag = one.getTag();
            }
            AjaxHttpResult ajaxHttpResult = cocHttp.get(tag, ClanApiHttp.ClanRealTime, true);
            if (!ajaxHttpResult.getSuccess()) {
                return Result.error("未查到部落信息！" + ajaxHttpResult.getErrorMsg());
            }
            JSONObject data = ajaxHttpResult.getData();
            ApplyCocGroup apply = new ApplyCocGroup();
            apply.setClanTag(data.getString("tag").replace("#", ""));

            //转义JSON
            String tagName = data.getString("name");
            String name = Utf8Util.stringToUnicode(tagName);//名称乱码
            data.put("name", name);
            String description = Utf8Util.stringToUnicode(data.getString("description"));//部落公告
            data.put("description", description);
            JSONArray memberList = (JSONArray) data.getJSONArray("memberList").clone();
            for (int i = 0; i < memberList.size(); i++) {
                JSONObject member = memberList.getJSONObject(i);
                String Clan_Play_Name = Utf8Util.stringToUnicode(member.getString("name"));
                member.put("name", Clan_Play_Name);
            }
            data.put("memberList", memberList);
            apply.setClanJson(data.toJSONString());
            apply.setStatus("报名中");
            apply.setGroupNumber(addfrom.getGroupNumber());
            apply.setCreateDate(TimeUtiles.getStringDate());
            apply.setCreateName(addfrom.getRobotNumber());
            apply.setType(addfrom.getType());
            apply.setUserNumber(addfrom.getUserNumber());
            apply.setCreateDate(TimeUtiles.getStringDate());
            apply.setUserName(addfrom.getUserNumber());
            apply.setGroupName(addfrom.getGroupName());
            apply.setGroupImage(addfrom.getGroupImage());
            String uuid = addfrom.getUuid();
            if (StringUtils.isNotEmpty(uuid)) {
                if (uuid.length() < 1 || uuid.length() > 8) {
                    return Result.error("唯一标识长度应控制在1-8个字！");
                }
                Integer uuidCount = this.query().eq("uuid", uuid).count();
                if (uuidCount != null && uuidCount > 0) {
                    return Result.error(uuid + " 已被占用，请重新输入新的唯一标识！");
                }
            }
            if (StringUtils.isEmpty(uuid)) {
                String thiscount = String.valueOf(this.count());
                if (addfrom.getRobotNumber().equals("微信机器人")) {
                    uuid = Math.round((Math.random() + 1) * 1000) + thiscount;
                } else {
                    uuid = addfrom.getGroupNumber().substring(0, 2) + addfrom.getUserNumber().substring(0, 2) + thiscount;
                }
            }
            apply.setUuid(uuid);
            this.save(apply);
            StringBuffer result = new StringBuffer();
            result.append("\n┏[" + addfrom.getType() + "]报名开始统计\n");
            result.append("┣" + tagName + "\n");
            result.append("┣标签：" + data.getString("tag") + "\n");
            result.append("┣等级：" + data.getString("clanLevel") + "级\n");
            result.append("┣状态：" + CocApiAndCqCustom.CocTpe(data.getString("type")) + "\n");
            result.append("┣要求：" + data.getString("requiredTrophies") + "奖杯\n");
            result.append("┣部落人数：" + data.getString("members") + "人\n");
            result.append("┣唯一标识：" + uuid + "\n");
            result.append("┣报名口令：报名" + addfrom.getType() + "#玩家标签\n");
            result.append("┗群外报名：报名" + addfrom.getType() + "#玩家标签#" + uuid + "\n");
            return Result.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("开启报名失败，未知错误，请反馈作者！");
    }

    /***
     * 用户进行报名
     * @param addUser
     * @return
     */
    @Override
    public Result addUser(AddUserForm addUser) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "addUser" + addUser.getTag();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("此村庄正在进行报名处理中，请等待结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            ApplyCocGroup applyGroup = null;
            if (StringUtils.isNotEmpty(addUser.getUuid())) {
                applyGroup = this.query().eq("uuid", addUser.getUuid()).one();
                if (applyGroup == null) {
                    return Result.error("根据您提供的\n[" + addUser.getUuid() + "]\n未找到相应的报名项，请核对后唯一标识后在报名！");
                } else if (applyGroup != null && !"报名中".equals(applyGroup.getStatus())) {
                    return Result.error("此报名项，已不在报名中，你可以联系相关负责人了解详情！\n负责人:" + applyGroup.getUserNumber());
                }
            }
            String msgType = addUser.getMsg().replace("\\u005b", "[").replace("\\u005d", "]");
            ;
            List<String> list = typeMsg(msgType);
            String remark = null;
            String typeMsg = msgType.replace("报名", "");
            if (list.size() > 0) {
                if (list.size() > 1 || list.get(0).length() > 20 || list.get(0).length() <= 0)
                    return Result.error("报名失败，请您重新输入备注信息，请保证备注只有1-20个文字，且不包含特殊字符。");
                remark = list.get(0);
            }
            if (StringUtils.isNotEmpty(remark))
                typeMsg = typeMsg.replace("[" + remark + "]", "");
            if (applyGroup == null) {
                applyGroup = this.query().eq("group_number", addUser.getGroupNumber()).eq("type", typeMsg).eq("status", "报名中").one();
                if (applyGroup == null) {
                    return Result.error("报名失败，无法找到[" + typeMsg + "]对应的报名内容，请核对后报名");
                } else if (applyGroup != null && !"报名中".equals(applyGroup.getStatus())) {
                    return Result.error("此报名项，已不在报名中，你可以联系相关负责人了解详情！\n负责人:" + applyGroup.getUserNumber());
                }
            }
            /** 可以开始报名了 */
            String uuid = applyGroup.getUuid();
            String playerTag = addUser.getTag();
            if ("我的村庄".equals(playerTag)) {
                CocBinding one = cocBindingService.query().eq("qqcode", addUser.getUserNumber()).eq("msg", "我的村庄").one();
                if (one == null) {
                    return Result.error("报名失败，因为你还未绑定你自己的村庄，无法使用我的村庄进行报名。");
                }
                playerTag = one.getTag();
            }
            //查询村庄是否已经 报名过
            ApplyCocUser applyCocUser = applyCocUserService.query().eq("uuid", uuid).eq("player_tag", playerTag).one();
            if (applyCocUser != null) {
                String applyRemark = applyCocUser.getRemark();
                if (StringUtils.isNotEmpty(remark) && !remark.equals(applyRemark)) {
                    applyCocUser.setRemark(remark);
                    applyCocUserService.updateById(applyCocUser);
                    applyRemark = StringUtils.isNotEmpty(applyRemark) ? "\n┣修改前备注内容:\n┣" + applyRemark : "";
                    return Result.ok("┏该村庄已报名!\n┣本次成功修改备注!" + applyRemark + "\n┣修改后备注内容：\n┗" + remark);
                } else {
                    return Result.error("[#" + playerTag + "]该村庄已报名，请勿重复报名！");
                }
            }
            //查询此村庄是否存在
            AjaxHttpResult playerResult = cocHttp.get(playerTag, ClanApiHttp.player, true);
            if (!playerResult.getSuccess()) {
                return Result.error("报名失败，无法查询到玩家是否存在，异常问题：" + playerResult.getErrorMsg());
            }
            JSONObject data = playerResult.getData();
            applyCocUser = new ApplyCocUser();
            if (StringUtils.isNotEmpty(remark))
                applyCocUser.setRemark(remark);
            String playerName = data.getString("name");
            String name = Utf8Util.stringToUnicode(playerName);
            data.put("name", name);
            JSONObject clan = data.getJSONObject("clan");
            if (clan != null) {
                String ClanName = Utf8Util.stringToUnicode(clan.getString("name"));
                clan.put("name", ClanName);
            }
            applyCocUser.setGroupNumber(addUser.getGroupNumber());
            applyCocUser.setPlayerJson(data.toJSONString());
            applyCocUser.setPlayerTag(data.getString("tag").replace("#", ""));
            applyCocUser.setUserNumber(addUser.getUserNumber());
            applyCocUser.setUuid(uuid);
            applyCocUser.setCreateName(addUser.getRobotNumber());
            applyCocUser.setCreateDate(TimeUtiles.getStringDate());
            applyCocUser.setGroupName(addUser.getGroupName());
            applyCocUser.setUserName(addUser.getUserName());
            applyCocUser.setUserImage(addUser.getUserImage());
            applyCocUserService.save(applyCocUser);
            StringBuffer result = new StringBuffer();
            result.append("\n┏[" + applyGroup.getType() + "]报名成功");

            JSONObject clanJSON = JSONObject.parseObject(applyGroup.getClanJson());
            String clanName = Utf8Util.unicodeToString(clanJSON.getString("name"));
            result.append("\n┣部落:" + clanName);
            result.append("\n┣部落标签:" + clanJSON.getString("tag"));
            result.append("\n┣玩家:" + playerName);
            result.append("\n┣玩家标签:" + data.getString("tag"));
            String townHallLevel = data.getString("townHallLevel");
            if (StringUtils.isNotEmpty(data.getString("townHallWeaponLevel"))) {
                townHallLevel += "-" + data.getString("townHallWeaponLevel");
            }
            result.append("\n┣大本：" + townHallLevel);
            //是否在部落内
            String isClan = "不在部落内";
            if (data.getJSONObject("clan") != null) {
                String playerClanTag = data.getJSONObject("clan").getString("tag").replace("#", "");
                if (playerClanTag.equals(applyGroup.getClanTag())) {
                    isClan = "在部落内";
                }
            }
            result.append("\n┣状态：" + isClan);
            // 实验室 等级
            Integer sumNumber = 0;
            JSONArray troops = data.getJSONArray("troops");
            for (int i = 0; i < troops.size(); i++) {
                if ("home".equals(troops.getJSONObject(i).getString("village"))) {
                    sumNumber += troops.getJSONObject(i).getInteger("level");
                }
            }
            JSONArray spells = data.getJSONArray("spells");
            for (int i = 0; i < spells.size(); i++) {
                sumNumber += spells.getJSONObject(i).getInteger("level");
            }
            result.append("\n┣战星：" + data.getString("warStars"));
            result.append("\n┣实验室科技：" + sumNumber);
            Integer playCount = applyCocUserService.query().eq("uuid", uuid).count();
            result.append("\n┗现报名数：" + playCount + "人");
            return Result.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("报名失败，未知错误，请反馈作者！");
    }


    @Override
    public Result UpdateGroup_restart(UpdateGroupForm updateGroup) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "addPlayerGroup" + updateGroup.getGroupNumber();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("本群上一个报名请求还在处理中，请待上一个开始报名结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            StringBuffer result = new StringBuffer();
            ApplyCocGroup applyGroup = null;
            if (StringUtils.isEmpty(updateGroup.getUuid())) {
                Integer count = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "已结束").count();
                if (count != 1) {
                    if (count == 0) {
                        result.append("本群不存在已结束报名的统计，所以无法重新开启。");
                    } else {
                        result.append("不知道你需要重启那场报名的统计呢，你可以告诉我是那场吗？\n指令提示：\n重启报名#唯一标识");
                    }
                    return Result.error(result.toString());
                }
                applyGroup = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "已结束").one();
            } else {
                applyGroup = query().eq("uuid", updateGroup.getUuid()).one();
            }
            if (applyGroup == null) {
                return Result.error("抱歉，无发找到您需要操作的报名项，请核对后重新操作。");
            }else if(!applyGroup.getUserNumber().equals(updateGroup.getUserNumber())){
                return Result.error("此报名项操作只能由报名的发起者"+applyGroup.getUserNumber()+"进行操作。");
            }

            Integer count = query().eq("type", applyGroup.getType()).eq("group_number", applyGroup.getGroupNumber()).eq("status", "报名中").count();
            if (count != null && count > 0)
                return Result.error("本群还存在尚未结束的" + applyGroup.getType() + "报名，请先结束报名，才能开启重启此报名。");

            AjaxHttpResult ajaxHttpResult = cocHttp.get(applyGroup.getClanTag(), ClanApiHttp.ClanRealTime, true);
            String tagName = null;
            if (ajaxHttpResult.getSuccess()) {
                JSONObject data = ajaxHttpResult.getData();
                String tagName2 = data.getString("name");
                String name = Utf8Util.stringToUnicode(tagName2);//名称乱码
                data.put("name", name);
                String description = Utf8Util.stringToUnicode(data.getString("description"));//部落公告
                data.put("description", description);
                JSONArray memberList = (JSONArray) data.getJSONArray("memberList").clone();
                for (int i = 0; i < memberList.size(); i++) {
                    JSONObject member = memberList.getJSONObject(i);
                    String Clan_Play_Name = Utf8Util.stringToUnicode(member.getString("name"));
                    member.put("name", Clan_Play_Name);
                }
                data.put("memberList", memberList);
                applyGroup.setClanJson(data.toJSONString());
                tagName = ajaxHttpResult.getData().getString("name");
            }
            applyGroup.setCreateDate(TimeUtiles.getStringDate());
            applyGroup.setStatus("报名中");
            this.updateById(applyGroup);
            JSONObject data = JSONObject.parseObject(applyGroup.getClanJson());
            if (!ajaxHttpResult.getSuccess()) {
                tagName = Utf8Util.unicodeToString(data.getString("name"));
            }
            result.append("\n┏[" + applyGroup.getType() + "]报名开始统计\n");
            result.append("┣" + tagName + "\n");
            result.append("┣标签：" + data.getString("tag") + "\n");
            result.append("┣等级：" + data.getString("clanLevel") + "级\n");
            result.append("┣状态：" + CocApiAndCqCustom.CocTpe(data.getString("type")) + "\n");
            result.append("┣要求：" + data.getString("requiredTrophies") + "奖杯\n");
            result.append("┣部落人数：" + data.getString("members") + "人\n");
            result.append("┣唯一标识：" + applyGroup.getUuid() + "\n");
            result.append("┣报名口令：报名" + applyGroup.getType() + "#玩家标签\n");
            result.append("┣群外报名：报名" + applyGroup.getType() + "#玩家标签#" + applyGroup.getUuid() + "\n");
            Integer playCount = applyCocUserService.query().eq("uuid", applyGroup.getUuid()).count();
            result.append("┗现报名数：" + playCount + "人");
            return Result.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("操作失败，未知错误，请反馈作者！");
    }

    @Override
    public Result UpdateGroup_down(UpdateGroupForm updateGroup) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "addPlayerGroup" + updateGroup.getGroupNumber();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("本群上一个报名请求还在处理中，请待上一个开始报名结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            StringBuffer result = new StringBuffer();
            ApplyCocGroup applyGroup = null;
            if (StringUtils.isEmpty(updateGroup.getUuid())) {
                Integer count = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "报名中").count();
                if (count != 1) {
                    if (count == 0) {
                        result.append("本群不存在正在报名的统计，所以无法结束。");
                    } else {
                        result.append("不知道你需要结束那场报名的统计呢，你可以告诉我是那场吗？\n指令提示：\n结束报名#唯一标识");
                    }
                    return Result.error(result.toString());
                }
                applyGroup = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "报名中").one();
            } else {
                applyGroup = query().eq("uuid", updateGroup.getUuid()).one();
            }

            if (applyGroup == null) {
                return Result.error("抱歉，无发找到您需要操作的报名项，请核对后重新操作。");
            }else if(!applyGroup.getUserNumber().equals(updateGroup.getUserNumber())){
                return Result.error("此报名项操作只能由报名的发起者"+applyGroup.getUserNumber()+"进行操作。");
            }
            AjaxHttpResult ajaxHttpResult = cocHttp.get(applyGroup.getClanTag(), ClanApiHttp.ClanRealTime, true);
            String tagName = null;
            if (ajaxHttpResult.getSuccess()) {
                JSONObject data = ajaxHttpResult.getData();
                String tagName2 = data.getString("name");
                String name = Utf8Util.stringToUnicode(tagName2);//名称乱码
                data.put("name", name);
                String description = Utf8Util.stringToUnicode(data.getString("description"));//部落公告
                data.put("description", description);
                JSONArray memberList = (JSONArray) data.getJSONArray("memberList").clone();
                for (int i = 0; i < memberList.size(); i++) {
                    JSONObject member = memberList.getJSONObject(i);
                    String Clan_Play_Name = Utf8Util.stringToUnicode(member.getString("name"));
                    member.put("name", Clan_Play_Name);
                }
                data.put("memberList", memberList);
                applyGroup.setClanJson(data.toJSONString());
                tagName = ajaxHttpResult.getData().getString("name");
            }
            applyGroup.setStatus("已结束");
            this.updateById(applyGroup);
            JSONObject data = JSONObject.parseObject(applyGroup.getClanJson());
            if (!ajaxHttpResult.getSuccess()) {
                tagName = Utf8Util.unicodeToString(data.getString("name"));
            }
            result.append("\n┏[" + applyGroup.getType() + "]报名已结束\n");
            result.append("┣" + tagName + "\n");
            result.append("┣标签：" + data.getString("tag") + "\n");
            result.append("┣等级：" + data.getString("clanLevel") + "级\n");
            result.append("┣状态：" + CocApiAndCqCustom.CocTpe(data.getString("type")) + "\n");
            result.append("┣要求：" + data.getString("requiredTrophies") + "奖杯\n");
            result.append("┣部落人数：" + data.getString("members") + "人\n");
            Integer playCount = applyCocUserService.query().eq("uuid", applyGroup.getUuid()).count();
            result.append("┗报名人数：" + playCount + "人");
            return Result.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("操作失败，未知错误，请反馈作者！");
    }

    @Override
    public Result UpdateGroup_remove(UpdateGroupForm updateGroup) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "addPlayerGroup" + updateGroup.getGroupNumber();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("本群上一个报名请求还在处理中，请待上一个开始报名结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            StringBuffer result = new StringBuffer();
            ApplyCocGroup applyGroup = null;
            if (StringUtils.isEmpty(updateGroup.getUuid())) {
                Integer count = query().eq("group_number", updateGroup.getGroupNumber()).count();
                if (count != 1) {
                    if (count == 0) {
                        result.append("本群不存在报名统计记录，所以无法删除。");
                    } else {
                        result.append("不知道你需要删除那场报名的统计呢，你可以告诉我是那场吗？\n指令提示：\n删除报名#唯一标识");
                    }
                    return Result.error(result.toString());
                }
                applyGroup = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "报名中").one();
            } else {
                applyGroup = query().eq("uuid", updateGroup.getUuid()).one();
            }
            if (applyGroup == null) {
                return Result.error("抱歉，无发找到您需要进行删除的报名项，请核对后重新操作。");
            }else if(!applyGroup.getUserNumber().equals(updateGroup.getUserNumber())){
                return Result.error("此报名项操作只能由报名的发起者"+applyGroup.getUserNumber()+"进行操作。");
            }
            this.removeById(applyGroup);
            QueryWrapper qw = new QueryWrapper();
            qw.eq("uuid", applyGroup.getUuid());
            applyCocUserService.remove(qw);
            JSONObject data = JSONObject.parseObject(applyGroup.getClanJson());
            String tagName = Utf8Util.unicodeToString(data.getString("name"));
            result.append("\n┏[" + applyGroup.getType() + "]报名已永久删除 √\n");
            result.append("┣" + tagName + "\n");
            result.append("┣标签：" + data.getString("tag") + "\n");
            result.append("┣等级：" + data.getString("clanLevel") + "级\n");
            Integer playCount = applyCocUserService.query().eq("uuid", applyGroup.getUuid()).count();
            result.append("┣唯一标识：" + applyGroup.getUuid() + "\n");
            result.append("┣报名类型：" + applyGroup.getType() + "\n");
            result.append("┗报名人数：" + playCount + "人");
            return Result.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("操作失败，未知错误，请反馈作者！");
    }

    @Override
    public Result updateUser(UpdateUserForm updateUser) {
        //用户取消报名
        ConfigParameter.clanCache.detect();
        String clanCacheName = "addUser" + updateUser.getTag();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("此村庄正在进行报名处理中，请等待结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            ApplyCocGroup applyGroup = null;
            if (StringUtils.isNotEmpty(updateUser.getUuid())) {
                applyGroup = this.query().eq("uuid", updateUser.getUuid()).one();
                if (applyGroup == null) {
                    return Result.error("根据您提供的\n[" + updateUser.getUuid() + "]\n未找到相应的报名项，请核对后唯一标识后在报名！");
                } else if (applyGroup != null && !"报名中".equals(applyGroup.getStatus())) {
                    return Result.error("此报名项，已不在报名统计中，你可以联系相关负责人了解详情！\n负责人:" + applyGroup.getUserNumber());
                }
            }
            updateUser.setMsg(updateUser.getMsg().replace("取消报名", ""));
            if (StringUtils.isEmpty(updateUser.getMsg())) {
                return Result.error("取消报名指令错误，请输入：\n开始报名XX#部落");
            }
            if (applyGroup == null) {
                applyGroup = this.query().eq("group_number", updateUser.getGroupNumber()).eq("type", updateUser.getMsg()).eq("status", "报名中").one();
                if (applyGroup == null) {
                    return Result.error("取消失败，无法找到[" + updateUser.getMsg() + "]对应的报名内容，请核对后报名");
                } else if (applyGroup != null && !"报名中".equals(applyGroup.getStatus())) {
                    return Result.error("此报名项，已不在报名中，你可以联系相关负责人了解详情！\n负责人:" + applyGroup.getUserNumber());
                }
            }
            String playerTag = updateUser.getTag();
            if ("我的村庄".equals(playerTag)) {
                CocBinding one = cocBindingService.query().eq("qqcode", updateUser.getUserNumber()).eq("msg", "我的村庄").one();
                if (one == null) {
                    return Result.error("报名失败，因为你还未绑定你自己的村庄，无法使用我的村庄进行报名。");
                }
                playerTag = one.getTag();
            }
            ApplyCocUser applyUser = applyCocUserService.query().eq("uuid", applyGroup.getUuid()).eq("player_tag", playerTag).one();
            if (applyUser == null) {
                return Result.error("该村庄还未在[" + applyGroup.getType() + "]报名中进行报名，所以不需要取消哦！");
            }
            CsUser csUser = csUserService.query().in("group_Number", updateUser.getGroupNumber()).one();
            Boolean isGoto = false;
            if (csUser.getCreateName().equals(updateUser.getUserNumber()))
                isGoto = true;
            if (applyUser.getUserNumber().equals(updateUser.getUserNumber()) || isGoto) {
                applyCocUserService.removeById(applyUser);
                JSONObject data = JSONObject.parseObject(applyUser.getPlayerJson());
                String tagName = Utf8Util.unicodeToString(data.getString("name"));
                StringBuffer result = new StringBuffer();
                result.append("\n┏已成功取消[" + applyGroup.getType() + "]报名\n");
                result.append("┣" + tagName + "\n");
                result.append("┣标签：" + data.getString("tag") + "\n");
                result.append("┣操作者：" + updateUser.getUserNumber() + "\n");
                Integer playCount = applyCocUserService.query().eq("uuid", applyGroup.getUuid()).count();
                result.append("┗现报名数：" + playCount + "人");
                return Result.ok(result.toString());
            } else {
                return Result.error("取消报名失败，您不是原报名者" + applyGroup.getUserNumber() + "！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("报名失败，未知错误，请反馈作者！");
    }

    /***
     * 更新报名玩家数据
     * @param updateGroup
     * @return
     */
    @Override
    public Result updateApply(UpdateGroupForm updateGroup) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "updateApply" + updateGroup.getGroupNumber();
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return Result.error("本群上一个更新请求还在处理中，请待上一个更新结束后，在进行操作！");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        String uuid = updateGroup.getUuid();
        try {
            StringBuffer result = new StringBuffer();
            ApplyCocGroup applyGroup = null;
            if (StringUtils.isEmpty(updateGroup.getUuid())) {
                Integer count = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "报名中").count();
                if (count != 1) {
                    if (count == 0) {
                        result.append("本群不存在正在报名的统计，所以无法结束。");
                    } else {
                        result.append("不知道你需要更新那场报名的统计呢，你可以告诉我是那场吗？\n指令提示：\n更新报名#唯一标识");
                    }
                    return Result.error(result.toString());
                }
                applyGroup = query().eq("group_number", updateGroup.getGroupNumber()).eq("status", "报名中").one();
                uuid = applyGroup.getUuid();
            } else {
                applyGroup = query().eq("uuid",uuid ).one();
            }

            if (applyGroup == null) {
                return Result.error("抱歉，无发找到您需要操作的报名项，请核对后重新操作。");
            }else if(!applyGroup.getUserNumber().equals(updateGroup.getUserNumber())){
                return Result.error("此报名项操作只能由报名的发起者"+applyGroup.getUserNumber()+"进行操作。");
            }
            List<ApplyCocUser> applyCocUserList = applyCocUserService.query().eq("uuid", uuid).list();
            Executor threadPool = BaseLocalThreadPool.getThreadPool();
            CountDownLatch count = new CountDownLatch(applyCocUserList.size() + 1);//玩家线程阻塞
            //更新部落数据
            String clanTag = applyGroup.getClanTag();
            ApplyCocGroup newapplyGroup = applyGroup;
            threadPool.execute(() -> {
                try {
                    AjaxHttpResult ajaxHttpResult = cocHttp.get(clanTag, ClanApiHttp.ClanRealTime, true);
                    JSONObject data = ajaxHttpResult.getData();
                    String tagName = data.getString("name");
                    String name = Utf8Util.stringToUnicode(tagName);//名称乱码
                    data.put("name", name);
                    String description = Utf8Util.stringToUnicode(data.getString("description"));//部落公告
                    data.put("description", description);
                    JSONArray memberList = (JSONArray) data.getJSONArray("memberList").clone();
                    for (int i = 0; i < memberList.size(); i++) {
                        JSONObject member = memberList.getJSONObject(i);
                        String Clan_Play_Name = Utf8Util.stringToUnicode(member.getString("name"));
                        member.put("name", Clan_Play_Name);
                    }
                    data.put("memberList", memberList);
                    newapplyGroup.setClanJson(data.toJSONString());
                    this.updateById(newapplyGroup);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    count.countDown();
                }
            });
            AtomicReference<Integer> sum = new AtomicReference<>(0);
            for (ApplyCocUser applyCocUser : applyCocUserList) {
                threadPool.execute(() -> {
                    try {
                        String playerTag = applyCocUser.getPlayerTag();
                        AjaxHttpResult playerResult = cocHttp.get(playerTag, ClanApiHttp.playerRealTime, true);
                        JSONObject data = playerResult.getData();
                        String playerName = data.getString("name");
                        String name = Utf8Util.stringToUnicode(playerName);
                        data.put("name", name);
                        JSONObject clan = data.getJSONObject("clan");
                        if (clan != null) {
                            String ClanName = Utf8Util.stringToUnicode(clan.getString("name"));
                            clan.put("name", ClanName);
                        }
                        applyCocUser.setPlayerJson(data.toJSONString());
                        applyCocUserService.updateById(applyCocUser);
                        sum.getAndSet(sum.get() + 1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        count.countDown();
                    }

                });
            }
            count.await();
            result.append("● 更新完毕！");
            result.append("\n● 报名数："+applyCocUserList.size());
            result.append("\n● 成功更新："+sum.get());
            return Result.ok(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConfigParameter.clanCache.remove(clanCacheName);
        }
        return Result.error("操作失败，未知错误，请反馈作者！");
    }

    @Override
    public ClanResult getGroupApply(String groupNumber) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "getGroupApply" + groupNumber;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return new ClanResult(false, "正在查询统计中,请静待结果。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            List<ApplyCocGroup> group_number = this.query().eq("group_number", groupNumber).orderByDesc("create_date").list();
            if (group_number.size() == 0) {
                return new ClanResult(false, "此部落图片已在生成中，请勿重复获取。");
            }
            ClanResult clanResult = new ImageApply().get(groupNumber, group_number, applyCocUserService);
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResult;
        } catch (Exception e) {
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false, "查询玩家出现异常，请反馈作者！");
        }
    }

    @Override
    public ClanResult getGroupApplyAll(String type, String groupNumber, String uuid) {
        ConfigParameter.clanCache.detect();
        String clanCacheName = "getGroupApplyAll" + groupNumber;
        Boolean isExist = ConfigParameter.clanCache.get(clanCacheName);
        if (isExist != null) return new ClanResult(false, "正在查询统计中,请静待结果。");
        ConfigParameter.clanCache.putPlusMinutes(clanCacheName, true, 4);
        try {
            ApplyCocGroup applyGroup = this.query().eq("uuid", uuid).one();
            if (applyGroup == null) {
                return new ClanResult(false, "未找到标识为" + uuid + "的报名列表。");
            }
            List<ApplyCocUser> applyUser = applyCocUserService.query().eq("uuid", uuid).list();
            if (applyUser.size() == 0) {
                return new ClanResult(false, "目前此报名项还没有人进行报名哦。");
            }
            ClanResult clanResult = new ImageApply().getAll(groupNumber, type, applyGroup, applyUser);
            ConfigParameter.clanCache.remove(clanCacheName);
            return clanResult;
        } catch (Exception e) {
            e.printStackTrace();
            ConfigParameter.clanCache.remove(clanCacheName);
            return new ClanResult(false, "查询玩家出现异常，请反馈作者！");
        }
    }


    public static List<String> typeMsg(String msg) {
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        List<String> mpedIds = new ArrayList<>();
        Matcher m = p.matcher(msg);
        while (m.find()) {
            String mpedId = m.group().substring(1, m.group().length() - 1);
            if (!mpedIds.contains(mpedId)) {
                mpedIds.add(mpedId);
            }
        }
        return mpedIds;
    }


}