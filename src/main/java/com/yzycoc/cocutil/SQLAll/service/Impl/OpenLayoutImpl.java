package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.OpenLayout;
import com.yzycoc.cocutil.SQLAll.mapper.OpenLayoutMapper;
import com.yzycoc.cocutil.SQLAll.service.DlUrlService;
import com.yzycoc.cocutil.SQLAll.service.OpenLayoutService;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.custom.TimeUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-11-13 10:20
 * @Version 1.0
 **/
@Service(value = "OpenLayoutService")
@Primary
public class OpenLayoutImpl extends ServiceImpl<OpenLayoutMapper, OpenLayout> implements OpenLayoutService {
    @Autowired
    private DlUrlService dlUrlService;
    @Autowired
    private OpenLayoutMapper mapper;

    @Override
    public ClanResult getRandomFormation() {
        OpenLayout open = mapper.getRandomFormation();
        StringBuffer result = new StringBuffer();
        String dlurl = open.getDlurl();
        if(dlurl == null){
            dlurl = dlUrlService.dl(open.getUrl());
            open.setDlurl(dlurl);
            open.setDldate(TimeUtiles.endDdHhmmss());
            this.updateById(open);
        }
        result.append("[bq190]编号：");
        result.append(open.getNumber());
        result.append("\n");
        result.append("\\uD83C\\uDF0F");
        result.append(dlurl);
        result.append("[CQ:image,file="+open.getImageUrl()+"]");
        return new ClanResult(result.toString());
    }

    @Override
    public ClanResult getFormation(String tag) {
        List<OpenLayout> number = this.query().eq("number", tag).list();
        if(number.size()>0){
            OpenLayout open = number.get(number.size() - 1);
            StringBuffer result = new StringBuffer();
            String dlurl = open.getDlurl();
            if(dlurl == null){
                dlurl = dlUrlService.dl(open.getUrl());
                open.setDlurl(dlurl);
                open.setDldate(TimeUtiles.endDdHhmmss());
                this.updateById(open);
            }
            result.append("[bq190]编号：");
            result.append(open.getNumber());
            result.append("\n");
            result.append("\\uD83C\\uDF0F");
            result.append(dlurl);
            result.append("[CQ:image,file="+open.getImageUrl()+"]");
            return new ClanResult(result.toString());
        }
        return new ClanResult(false,"未找到阵型！");
    }
}
