package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUserPrivate;
import com.yzycoc.from.AddCsUserPrivate;
import org.apache.ibatis.annotations.*;

/**
 * @program: cscocutil
 * @description: maooer
 * @author: yzy
 * @create: 2021-01-11 20:07
 * @Version 1.0
 **/

@Mapper
public interface CsUserPrivateMapper extends BaseMapper<CsUserPrivate> {

    @Select("select count(1) val from ( select * from ts_ips) t1 left join (select * from cs_user_private ) t2 on  t1.qqcode = t2.robot_number  where t2.qq_number = #{add.userNumber} and t1.state = '1'")
    Integer getIsUser(@Param("add") AddCsUserPrivate add);
}
