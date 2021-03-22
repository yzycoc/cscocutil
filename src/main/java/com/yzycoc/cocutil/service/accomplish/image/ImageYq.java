package com.yzycoc.cocutil.service.accomplish.image;

import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import com.yzycoc.cocutil.SQLAll.service.YuQingService;
import com.yzycoc.cocutil.service.accomplish.yq.*;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.*;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: cscocutil
 * @description: 获取鱼情图片
 * @author: yzy
 * @create: 2020-08-22 11:43
 * @Version 1.0
 **/
public class ImageYq {

    public ClanResult get(){
        try {

            long start = System.currentTimeMillis();
            System.out.println("鱼情：正在开始生成鱼情图片！！！================================>");
            SimpleDateFormat sdf = new SimpleDateFormat("MMdd-HHmm");
            String uid = sdf.format(new Date());

            YuQingService service = SpringContextUtil.getBean(YuQingService.class);
            YuQing yuQing = new YqUpdeteTxt().getYuQing(service);
            if(yuQing == null){
                return new ClanResult(false,"鱼情图片合成失败；无法获取鱼情相关数据，请稍后重试！");
            }
            //保存文件内容到html
            new CocApiAndCqCustom().saveTxt(yuQing.getHtml());
            yuQing.setHtml("");
            
            Boolean image =  new YqCurveImage().Synthesis(start);
            if(!image) return new ClanResult(false,"鱼情图片合成失败；合成趋势图失败，请稍后重试！");

            //合成曲线图！
            image = new YqImage().getImageTwo(yuQing, uid);
            if(!image) return new ClanResult(false,"鱼情图片合成失败；合成图片失败，请稍后重试！");
            System.out.println("鱼情：合成图成功。共耗时："+(System.currentTimeMillis() - start));
            return new ClanResult(true,uid, ConfigParameter.filePath_Yq,"jpg");
        }catch (Exception e){
            return new ClanResult(false,"鱼情图片生成失败，请稍后重试");
        }
    }


}
