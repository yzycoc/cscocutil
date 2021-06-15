package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.OpenLayout;
import com.yzycoc.cocutil.SQLAll.mapper.OpenLayoutMapper;
import com.yzycoc.cocutil.SQLAll.service.DlUrlService;
import com.yzycoc.cocutil.SQLAll.service.OpenLayoutService;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import org.apache.commons.lang.StringUtils;
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
    public ClanResult getRandomFormation(String type) {
        OpenLayout open = mapper.getRandomFormation();

        StringBuffer result = new StringBuffer();
        result.append("┏编号："+open.getNumber());
        if(StringUtils.isEmpty(open.getLabel())){
            result.append("\n┣"+open.getNumber());
        }
        result.append("\n┗↓点击链接复制阵型↓\n");
        String id = TimeUtiles.DlNumber(open.getId());
        result.append(ConfigParameter.HttpUrl+"qq/cocApi/openlayout/"+id);
        return new ClanResult(result.toString());
    }

    @Override
    public ClanResult getFormation(String tag) {
        List<OpenLayout> number = this.query().eq("number", tag).list();
        if(number.size()>0){
            OpenLayout open = number.get(number.size() - 1);
            StringBuffer result = new StringBuffer();
                result.append("┏编号："+open.getNumber());
            if(StringUtils.isEmpty(open.getLabel())){
                result.append("\n┣"+open.getNumber());
            }
            result.append("\n┗↓点击链接复制阵型↓\n");
            String id = TimeUtiles.DlNumber(open.getId());
            result.append(ConfigParameter.HttpUrl+"qq/cocApi/openlayout/"+id);
            return new ClanResult(result.toString());
        }
        return new ClanResult(false,"未找到阵型！");
    }
}
