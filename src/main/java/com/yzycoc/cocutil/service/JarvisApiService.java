package com.yzycoc.cocutil.service;

import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.enums.JarvisEnum;

/**
 * @program: cscocutil
 * @description: 数据酷提供的接口
 * @author: yzy
 * @create: 2020-08-23 13:16
 * @Version 1.0
 **/
public interface JarvisApiService {
    /***
     * 部落对战
     * @param tag 部落标签
     * @return
     */
    public ClanResult getClanwar(String tag, JarvisEnum jarvisEnum);


    /***
     * 部落对战
     * @param tag 部落标签
     * @return
     */
    public ClanResult getClanwar(String tag, JarvisEnum jarvisEnum,String war);


}
