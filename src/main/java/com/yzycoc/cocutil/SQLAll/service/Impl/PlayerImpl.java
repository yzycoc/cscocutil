package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.yzycoc.cocutil.SQLAll.bean.Player;
import com.yzycoc.cocutil.SQLAll.mapper.AlibabaPlayerMapper;
import com.yzycoc.cocutil.SQLAll.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class PlayerImpl extends ServiceImpl<AlibabaPlayerMapper, Player> implements PlayerService {

	@Autowired
	private AlibabaPlayerMapper mapper;
	
}
