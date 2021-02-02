package com.yzycoc.cocutil.SQLAll.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: cscocutil
 * @description: 积分列表
 * @author: yzy
 * @create: 2020-12-16 20:00
 * @Version 1.0
 **/
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
}
