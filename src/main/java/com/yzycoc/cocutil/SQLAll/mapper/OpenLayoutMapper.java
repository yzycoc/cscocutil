package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.OpenLayout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: cscocutil
 * @description: 查询阵型
 * @author: yzy
 * @create: 2020-11-13 10:19
 * @Version 1.0
 **/
@Mapper
public interface OpenLayoutMapper extends BaseMapper<OpenLayout> {
    @Select("SELECT  * FROM open_layout WHERE id IN (SELECT t.id FROM (SELECT id FROM open_layout  ORDER BY RAND() LIMIT 1 ) AS t )")
    OpenLayout getRandomFormation();
}
