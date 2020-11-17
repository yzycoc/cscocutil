package com.yzycoc.cocutil.SQLClan.service.impl;

import com.yzycoc.cocutil.SQLClan.bean.PlayerJson;
import com.yzycoc.cocutil.SQLClan.mapper.PlayerJsonMapper;
import com.yzycoc.cocutil.SQLClan.service.PlayerJsonService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="PlayerJsonService")
@Primary
public class PlayerJsonImpl extends ServiceImpl<PlayerJsonMapper, PlayerJson> implements PlayerJsonService {

}
