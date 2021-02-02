package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.score.Score;
import com.yzycoc.cocutil.SQLAll.mapper.ScoreMapper;
import com.yzycoc.cocutil.SQLAll.service.ScoreService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: cscocutil
 * @description: 用户列表
 * @author: yzy
 * @create: 2020-12-16 20:01
 * @Version 1.0
 **/
@Primary
@Service(value = "ScoreService")
public class ScoreImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

}
