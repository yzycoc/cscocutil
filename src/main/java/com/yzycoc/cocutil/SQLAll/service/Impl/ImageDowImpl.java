package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.ImageDown;
import com.yzycoc.cocutil.SQLAll.mapper.ImageDownMapper;
import com.yzycoc.cocutil.SQLAll.service.ImageDownService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description: 图片保存接口
 * @author: yzy
 * @create: 2020-10-30 16:05
 * @Version 1.0
 **/
@Service(value = "ImageDownService")
@Primary
public class ImageDowImpl extends ServiceImpl<ImageDownMapper, ImageDown> implements ImageDownService {
}
