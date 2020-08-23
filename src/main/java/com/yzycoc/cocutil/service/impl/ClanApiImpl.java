package com.yzycoc.cocutil.service.impl;

import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import com.yzycoc.cocutil.service.ClanApiService;
import com.yzycoc.cocutil.service.accomplish.image.*;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocEquilibrium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description: Coc工具实现
 * @author: yzy
 * @create: 2020-08-10 20:41
 * @Version 1.0
 **/
@Service
public class ClanApiImpl implements ClanApiService {
    @Autowired
    private CocEquilibrium cocHttp;


    @Override
    public ClanResult getImageClan(String tag) {
        return new ImageClan().get(tag,cocHttp);
    }
    @Override
    public ClanResult getImagePlayer(String tag) {return new ImagePlayer().get(tag,cocHttp); }
    @Override
    public ClanResult getImageYq() {
        return new ImageYq().get();
    }

    @Override
    public ClanResult getImageClanAll(String tag) {
        return new ImageClanAll().get(tag,cocHttp);
    }

    @Override
    public ClanResult getImageClanAllCollectText(String tag) {
        return new ImageClanAllCollectText().get(tag,cocHttp);
    }

    @Override
    public ClanResult getImageClanAllCollectImage(String tag) { return new ImageClanAllCollectImage().get(tag,cocHttp); }

    @Override
    public ClanResult getNameClan(String Name) {
        return null;
    }
}
