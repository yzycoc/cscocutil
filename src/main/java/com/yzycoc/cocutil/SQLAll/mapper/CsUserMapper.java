package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.from.CsUserImageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: cscocutil
 * @description: Mapepr
 * @author: yzy
 * @create: 2020-12-14 17:58
 * @Version 1.0
 **/
@Mapper
public interface CsUserMapper extends BaseMapper<CsUser> {

    @Select("SELECT cs_user.group_number,perpetual,time,cs_user.robot_number,name,remark FROM cs_user  left join ts_ips on cs_user.robot_number = ts_ips.qqcode where cs_user.create_name  = #{userNumber} order by robot_number asc")
    List<CsUserImageVo> listCreateNumber(String userNumber);
}
