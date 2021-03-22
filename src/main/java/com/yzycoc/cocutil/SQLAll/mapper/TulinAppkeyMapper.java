package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.TulinAppkey;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

@Mapper
public interface TulinAppkeyMapper extends BaseMapper<TulinAppkey> {
    @Select("select *  FROM  xjpublic.tulin_appkey  where  id  in  (select  t.id  from  (select id from xjpublic.tulin_appkey where data_time is null or sum<100 or data_time !=  DATE_FORMAT(NOW(),'%Y-%m-%d') ORDER BY RAND() limit 1)  as  t )")
    TulinAppkey getTulinAppkey();
}
