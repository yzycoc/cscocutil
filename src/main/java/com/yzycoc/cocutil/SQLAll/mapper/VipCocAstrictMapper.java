package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.vip.VipCocAstrict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

@Mapper
public interface VipCocAstrictMapper extends BaseMapper<VipCocAstrict> {

    @Select("select count(1) ID from  vip_coc_astrict t1 left join (\n" +
            "select qqnumber,eternity,end_time,uuid,wx_id from cs_user_vip where eternity = '1' or end_time >= now()\n" +
            ") t2 on t1.qqcode = t2.qqnumber where ( qqcode = #{qqcode} or wx_id = #{qqcode} or uuid = #{qqcode}) and t1.type = #{type} and sum_number >= #{sumNumber} and query_date = date_format(now(), '%Y-%m-%d') and (eternity != '1'  or eternity is null)and ( end_time < now() or end_time is null)")
    Integer isGoto(@Param("qqcode")String qqcode,@Param("type") String type,@Param("sumNumber")Integer sumNumber);
}
