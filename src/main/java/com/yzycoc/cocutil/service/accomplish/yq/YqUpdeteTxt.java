package com.yzycoc.cocutil.service.accomplish.yq;

import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import com.yzycoc.cocutil.service.accomplish.text.TestYq;
import com.yzycoc.custom.SpringContextUtil;
import com.yzycoc.custom.TimeUtiles;

/**
 * @program: cscocutil
 * @description: 鱼情更新文字内容
 * @author: yzy
 * @create: 2020-10-27 16:02
 * @Version 1.0
 **/
public class YqUpdeteTxt {

    public YuQing getYuQing(YuQingService service) {
        YuQing byId = service.getById(1);
        String htmltime = byId.getHtmltime();
        long datadifference1 = TimeUtiles.datadifference(htmltime);
        if(datadifference1 > 500){
            System.out.println("鱼情：正在获取新的鱼情数据 ============>");
            YqGetHtml yu = new YqGetHtml();
            //获取鱼情相关详细数据
            byId = yu.getYuQingEntity(service);
            //获取鱼情当前时段的特殊代码
            String yqHtml = yu.getYqStartHtml();
            byId.setHtml(yqHtml);
            byId.setHtmltime(TimeUtiles.getStringDate());
            byId.setId(1);
            //保存到数据库做持久化处理
            service.updateById(byId);
        }
        return byId;
    }





}
