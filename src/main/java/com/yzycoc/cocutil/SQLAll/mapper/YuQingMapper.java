package com.yzycoc.cocutil.SQLAll.mapper;

import com.yzycoc.cocutil.SQLAll.bean.xjpublic.YuQing;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface YuQingMapper extends BaseMapper<YuQing>{
    @Select("select msg  FROM  xjpublic.yq  where  id  in  (select  t.id  from  (select id from xjpublic.yq where type=#{type} ORDER BY RAND() limit 1)  as  t )")
    String getyuqing(String type);
}
