package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.CsutilLog;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.TulinAppkey;
import com.yzycoc.cocutil.SQLAll.mapper.TulinAppkeyMapper;
import com.yzycoc.cocutil.SQLAll.service.CsutilLogService;
import com.yzycoc.cocutil.SQLAll.service.TulinAppkeyService;
import com.yzycoc.custom.TimeUtiles;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service(value = "TulinAppkeyService")
public class TulinAppkeyServiceImpl extends ServiceImpl<TulinAppkeyMapper, TulinAppkey> implements TulinAppkeyService {
    @Autowired
    private CsutilLogService csutilLogService;

    @Override
    public String getTulinAppkey(String qq) {
        TulinAppkey tulinAppkey= baseMapper.getTulinAppkey();
        new Thread(()->{
            TulinAppkey tulinAppkeys = tulinAppkey;
            if(tulinAppkeys!=null){
                String time = TimeUtiles.getStringTime();
                if(time.equals(tulinAppkeys.getDataTime())){
                    tulinAppkeys.setSum(tulinAppkeys.getSum()+1);
                }else{
                    tulinAppkeys.setSum(1);
                    tulinAppkeys.setDataTime(time);
                }
                this.updateById(tulinAppkeys);
            }
            if(StringUtils.isNotEmpty(qq)){
                CsutilLog csutilLog = new CsutilLog();
                csutilLog.setQqcode(qq);
                csutilLog.setCode("tulin");
                csutilLogService.save(csutilLog);
            }
        }).start();
        if(tulinAppkey!=null){
            return tulinAppkey.getAppkey();
        }else{
            return "";
        }
    }
}
