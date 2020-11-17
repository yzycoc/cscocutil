package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.DlUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DlUrlMapper extends BaseMapper<DlUrl> {
    @Select("select max(id) from dl_url")
    int getCountId();
}
