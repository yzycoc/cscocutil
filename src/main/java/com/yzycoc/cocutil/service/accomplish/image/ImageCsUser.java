package com.yzycoc.cocutil.service.accomplish.image;

import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.CocEquilibrium;
import com.yzycoc.cocutil.util.enums.ClanApiHttp;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.result.AjaxHttpResult;
import com.yzycoc.from.CsUserImage;
import com.yzycoc.util.tableImage.ImageTable;
import com.yzycoc.util.tableImage.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: cscocutil -> com.yzycoc.cocutil.service.accomplish.image
 * @description: 合成图片
 * @author: XinDa2020
 * @create: 2021/3/23 14:14:01
 * @Version 1.0
 **/
@Slf4j
public class ImageCsUser {


    /***
     * 实时生成生成图片
     * @return
     */
    public ClanResult getRealTime(List<CsUserImage> data, String userNumber) throws Exception {
        long startTime=System.currentTimeMillis();
        log.info("开始生成用户群授权信息！");
        String saveFilePath = "group" + userNumber;
        ImageUtil imageUtil = new ImageUtil();
        BufferedImage image = imageUtil.tableIamge(new ImageTable<CsUserImage>().table(data));
        Thumbnails.of(image).outputFormat("jpg").scale(1f).outputQuality(1f).toFile(new File(ConfigParameter.filePath_group+"\\"+saveFilePath));
        log.info("用户群授权信息图片生成完毕，耗时"+(System.currentTimeMillis() - startTime)+"ms");
        return new ClanResult(true,saveFilePath, ConfigParameter.filePath_group,"jpg");
    }
}
