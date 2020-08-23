package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.Player;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlibabaPlayerMapper extends BaseMapper<Player> {

	void saves(Player player);

}
