package com.yzycoc.cocutil.SQLAll.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.DlUrl;
import com.yzycoc.cocutil.SQLAll.bean.PublicLexicon;
import com.yzycoc.cocutil.SQLAll.mapper.DlUrlMapper;
import com.yzycoc.cocutil.SQLAll.service.DlUrlService;
import com.yzycoc.cocutil.SQLAll.service.PublicLexiconService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.TimeUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private PublicLexiconService publicLexiconService;
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

    @Override
    public String dl(String url, String... createTime) {


        return url;
    }

    @Override
    public void dlAddLix(String url, String... createTime) {
        DlUrl dl = new DlUrl();
        String dlNumber = TimeUtiles.DlNumber(dao.getCountId());
        dl.setCreateDate(TimeUtiles.getStringTime());
        dl.setDlurl(dlNumber);
        dl.setSum("0");
        dl.setUrl(url);
        dl.setCreateName(createTime[0]);
        this.save(dl);

        String dlUrl = ConfigParameter.HttpUrl.replaceAll("http://", "www.") + "i/" + dlNumber;
        for (String name : createTime) {
            PublicLexicon p = new PublicLexicon();
            p.setCreateDate(TimeUtiles.getStringDate());
            p.setCreateName(createTime[0]);
            p.setAntistop(name+"信息");
            p.setCode("public");
            p.setNumber("全发");
            StringBuffer v = new StringBuffer();
            v.append("● 查询内容:"+createTime[0]);
            v.append("\n● 详情信息:"+dlUrl);
            p.setResponse(v.toString());
            p.setType("完全");
            p.setPublictypeId(14);
            publicLexiconService.save(p);
        }
    }
}
