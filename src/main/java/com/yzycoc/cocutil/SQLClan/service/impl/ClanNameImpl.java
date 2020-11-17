package com.yzycoc.cocutil.SQLClan.service.impl;

import com.yzycoc.cocutil.SQLClan.bean.ClanName;
import com.yzycoc.cocutil.SQLClan.mapper.ClanNameMapper;
import com.yzycoc.cocutil.SQLClan.service.ClanNameService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="ClanNameService")
@Primary
public class ClanNameImpl extends ServiceImpl<ClanNameMapper, ClanName> implements ClanNameService {

}
