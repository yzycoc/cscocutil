package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.yzycoc.cocutil.SQLAll.bean.PlayerName;
import com.yzycoc.cocutil.SQLAll.mapper.PlayerNameMapper;
import com.yzycoc.cocutil.SQLAll.service.PlayerNameService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class PlayerNameImpl extends ServiceImpl<PlayerNameMapper, PlayerName> implements PlayerNameService {

}
