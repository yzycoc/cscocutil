package com.yzycoc.cocutil.SQLAll.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.DlUrl;
import com.yzycoc.cocutil.SQLAll.mapper.DlUrlMapper;
import com.yzycoc.cocutil.SQLAll.service.DlUrlService;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description: 短链方法实现
 * @author: yzy
 * @create: 2020-08-05 10:55
 * @Version 1.0
 **/
@Service(value="DlUrlService")
public class DlUrlImpl extends ServiceImpl<DlUrlMapper, DlUrl> implements DlUrlService{

}
