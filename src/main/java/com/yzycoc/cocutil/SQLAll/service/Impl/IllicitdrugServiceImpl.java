package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.Illicitdrug;
import com.yzycoc.cocutil.SQLAll.mapper.IllicitdrugMapper;
import com.yzycoc.cocutil.SQLAll.service.IllicitdrugService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service(value = "IllicitdrugService")
public class IllicitdrugServiceImpl extends ServiceImpl<IllicitdrugMapper, Illicitdrug>  implements IllicitdrugService {
}
