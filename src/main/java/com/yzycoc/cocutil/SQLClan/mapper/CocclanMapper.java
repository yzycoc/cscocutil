package com.yzycoc.cocutil.SQLClan.mapper;

import com.yzycoc.cocutil.SQLClan.bean.Cocclan;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface CocclanMapper extends BaseMapper<Cocclan>{
	void saves(Cocclan cocclan);
}
