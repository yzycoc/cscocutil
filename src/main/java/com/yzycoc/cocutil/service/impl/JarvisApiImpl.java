package com.yzycoc.cocutil.service.impl;

import com.yzycoc.cocutil.service.JarvisApiService;
import com.yzycoc.cocutil.service.accomplish.image.ImageJarvis;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.enums.JarvisEnum;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description: 傻仙接口
 * @author: yzy
 * @create: 2020-08-23 13:17
 * @Version 1.0
 **/
@Service
public class JarvisApiImpl implements JarvisApiService {
    @Override
    public ClanResult getClanwar(String tag, JarvisEnum jarvisEnum) {
        return new ImageJarvis().get(tag,jarvisEnum);
    }

    @Override
    public ClanResult getClanwar(String tag, JarvisEnum jarvisEnum, String war) {
        return new ImageJarvis().get(tag,jarvisEnum, war);
    }
}
