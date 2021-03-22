package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.CocBinding;
import com.yzycoc.cocutil.SQLAll.mapper.CocBindingMapper;
import com.yzycoc.cocutil.SQLAll.service.CocBindingService;
import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserVipService;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.custom.TimeUtiles;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.custom.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-12-17 15:15
 * @Version 1.0
 **/
@Service(value = "CocBindingService")
@Primary
public class CocBindingImpl extends ServiceImpl<CocBindingMapper, CocBinding> implements CocBindingService {
    @Autowired
    private CocEquilibrium cocHttp;
    @Autowired
    private MyCsUserVipService mycsUserVipService;
    @Override
    public Result<?> getBindingList(String qqcode) {
        List<CocBinding> list = this.query().eq("qqcode", qqcode).list();
        StringBuffer gomsg  = new StringBuffer();
        gomsg.append("≮-用户："+qqcode+"-≯\r\n" + "◎━━-绑定列表-━━◎\n ※类型  丨  ※标签  丨  ※触发词");
        if(list.size()>0){
            for (CocBinding c : list) {
                gomsg.append("\n"+ c.getType()+"|#"+c.getTag()+"|"+c.getMsg());
            }
        }else {
            gomsg.append("\n◎您还未绑定部落");
        }
        gomsg.append("\n◎━━━----◎----━━━◎");
        return Result.ok(gomsg.toString());
    }
    @Transactional
    @Override
    public synchronized Result<?> addBinding(String qqcode, String msg, String tag, String type) {
        try {
            tag = tag.toUpperCase();
            CocBinding cocBinding = this.query().eq("msg", msg).eq("qqcode", qqcode).one();
            if(cocBinding!=null)
                return Result.error("绑定失败，此触发此已存在绑定的内容。【#"+cocBinding.getTag()+"-"+cocBinding.getType()+"】");
            Integer qqco = this.query().eq("qqcode", qqcode).count();
            CsUserVip one = mycsUserVipService.query().eq("qqnumber", qqcode).one();
            if(one == null){
                one = new CsUserVip();
            }
            Integer binding = one.getBinding();
            if(qqco!=null&&qqco>binding&&binding!=0){
                return Result.error("绑定失败，普通用户只能绑定3个哦！");
            }
            if(!one.getVipState()&&!"村庄".equals(type)&&!"玩家".equals(type)&&!"部落".equals(type)){
                return Result.error("非会员无法绑定"+type+"。");
            }
            AjaxHttpResult ajaxHttpResult = new AjaxHttpResult();
            if("村庄".equals(type)||"玩家".equals(type)){
                ajaxHttpResult = cocHttp.get(tag, ClanApiHttp.player, true);
            }else{
                ajaxHttpResult = cocHttp.get(tag, ClanApiHttp.Clan, true);
            }
            if(ajaxHttpResult!=null&&ajaxHttpResult.getSuccess()){
                cocBinding = new CocBinding();
                cocBinding.setCreateDate(TimeUtiles.getStringDate());
                cocBinding.setCreateName(qqcode);
                cocBinding.setMsg(msg);
                cocBinding.setType(type);
                cocBinding.setQqcode(qqcode);
                cocBinding.setTag(tag);
                this.save(cocBinding);
                StringBuffer resultMsg = new StringBuffer();
                resultMsg.append("绑定成功，您下次可以输入");
                if(type.equals("村庄")&&msg.equals("我的村庄")){
                    resultMsg.append("【@我 我的村庄】");
                }else if(type.equals("部落")&&msg.equals("我的部落")){
                    resultMsg.append("【@我 我的部落】");
                }else{
                    resultMsg.append("【查询绑定#"+msg+"】");
                }
                resultMsg.append("查询#"+tag+"的"+type+"信息！");
                return Result.ok(resultMsg.toString());
            }else if(ajaxHttpResult!=null&&!ajaxHttpResult.getSuccess()){
                return Result.error(    ajaxHttpResult.getErrorMsg());
            }
            return Result.error("请稍后重试，或确认标签后重新绑定。");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("无法查询到此标签是否真实，请稍后重试。");
        }

    }
}
