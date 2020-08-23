package com.yzycoc.cocutil.SQLAll.mapper;

import com.yzycoc.cocutil.SQLAll.bean.Cocclan;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface CocclanMapper extends BaseMapper<Cocclan>{
	void saves(Cocclan cocclan);
}
