package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipCocAstrict;
import com.yzycoc.cocutil.SQLAll.mapper.VipCocAstrictMapper;
import com.yzycoc.cocutil.SQLAll.service.CsUserVipService;
import com.yzycoc.cocutil.SQLAll.service.VipCocAstrictService;
import com.yzycoc.custom.TimeUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("VipCocAstrictService")
public class VipCocAstrictServiceImpl extends ServiceImpl<VipCocAstrictMapper, VipCocAstrict> implements VipCocAstrictService {
    @Autowired
    private CsUserVipService csUserVipService;
    /***
     * 用户限制
     * @param qqcode
     * @param type
     * @return
     */
    @Override
    public Boolean isGoto(String qqcode, String type,Integer number) {
        Integer id = baseMapper.isGoto(qqcode,type,number);
        if(id == null){
            return false;
        }else{
            if(id>0){
                return true;
            }
            return false;
        }
    }

    @Override
    public void isOk(String qqcode, String type) {
        String stringTime = TimeUtiles.getStringTime();
        VipCocAstrict one = this.query().eq("qqcode", qqcode).eq("query_date", stringTime).eq("type",type).one();
        if(one == null){
            try {
                CsUserVip wx_id = csUserVipService.query().eq("wx_id", qqcode).or().eq("uuid",qqcode).one();
                if(wx_id != null){
                    one = this.query().eq("qqcode", wx_id.getQqnumber()).eq("query_date", stringTime).eq("type",type).one();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(one!=null){
            one.setSumNumber(one.getSumNumber() + 1);
        }else{
            one = new VipCocAstrict();
            one.setType(type);
            one.setQqcode(qqcode);
            one.setQueryDate(stringTime);
        }
        this.saveOrUpdate(one);
    }
}
