package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.PublicLexicon;
import com.yzycoc.cocutil.SQLAll.mapper.PublicLexiconMapper;
import com.yzycoc.cocutil.SQLAll.service.PublicLexiconService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @Version 1.0
 * @description:
 * @author: yzy
 * @create: 2020-12-18 15:09
 **/
@Service(value = "PublicLexiconService")
@Primary
public class IPublicLexiconImpl extends ServiceImpl<PublicLexiconMapper, PublicLexicon>  implements PublicLexiconService {



}
