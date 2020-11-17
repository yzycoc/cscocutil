package com.yzycoc.cocutil.SQLClan.service.impl;

import com.yzycoc.cocutil.SQLClan.bean.ClanJson;
import com.yzycoc.cocutil.SQLClan.mapper.ClanJsonMapper;
import com.yzycoc.cocutil.SQLClan.service.ClanJsonService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="ClanJsonService")
@Primary
public class ClanJsonImpl extends ServiceImpl<ClanJsonMapper, ClanJson> implements ClanJsonService {

}
