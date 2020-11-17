package com.yzycoc.cocutil.SQLAll.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.DlUrl;
import com.yzycoc.cocutil.SQLAll.mapper.DlUrlMapper;
import com.yzycoc.cocutil.SQLAll.service.DlUrlService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description: 短链方法实现
 * @author: yzy
 * @create: 2020-08-05 10:55
 * @Version 1.0
 **/
@Service(value="DlUrlService")
@Primary
public class DlUrlImpl extends ServiceImpl<DlUrlMapper, DlUrl> implements DlUrlService{
    @Autowired
    private DlUrlMapper dao;
    @Override
    public String dl(String url) {
        DlUrl dl = new DlUrl();
        String dlNumber = TimeUtiles.DlNumber(dao.getCountId());
        dl.setCreateDate(TimeUtiles.getStringTime());
        dl.setDlurl(dlNumber);
        dl.setSum("0");
        dl.setUrl(url);
        this.save(dl);
        return ConfigParameter.HttpUrl.replaceAll("http://", "www.")+"i/"+dlNumber;
    }
}
