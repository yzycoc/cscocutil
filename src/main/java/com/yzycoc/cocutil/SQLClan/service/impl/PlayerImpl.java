package com.yzycoc.cocutil.SQLClan.service.impl;

import com.yzycoc.cocutil.SQLClan.bean.Player;
import com.yzycoc.cocutil.SQLClan.mapper.AlibabaPlayerMapper;
import com.yzycoc.cocutil.SQLClan.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(value="PlayerService")
@Primary
public class PlayerImpl extends ServiceImpl<AlibabaPlayerMapper, Player> implements PlayerService {

	@Autowired
	private AlibabaPlayerMapper mapper;
	
}
