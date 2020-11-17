package com.yzycoc.cocutil.SQLClan.service.impl;

import com.yzycoc.cocutil.SQLClan.bean.PlayerName;
import com.yzycoc.cocutil.SQLClan.mapper.PlayerNameMapper;
import com.yzycoc.cocutil.SQLClan.service.PlayerNameService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="PlayerNameService")
@Primary
public class PlayerNameImpl extends ServiceImpl<PlayerNameMapper, PlayerName> implements PlayerNameService {

}
