package com.yzycoc.cocutil.SQLAll.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.ScoreUuid;
import com.yzycoc.cocutil.SQLAll.mapper.ScoreUuidMapper;
import com.yzycoc.cocutil.SQLAll.service.ScoreUuidService;
import com.yzycoc.custom.TimeUtiles;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-25 15:46
 * @Version 1.0
 **/
@Service("ScoreUuidService")
@Primary
public class ScoreUuidImpl extends ServiceImpl<ScoreUuidMapper, ScoreUuid> implements ScoreUuidService {
    //@Insert(" INSERT INTO score_uuid VALUES (null,null,'指令新增',0,${parseInt},null,'${date}','${uuid}') ")
    @Override
    public String saveNumber(String number,String remark) {
        String uuid = UUID.randomUUID().toString();
        ScoreUuid scoreUuid = new ScoreUuid();
        scoreUuid.setUuid(uuid);
        scoreUuid.setIsUser(false);
        String times = TimeUtiles.getStringDate();
        scoreUuid.setCreateDate(TimeUtiles.getStringDate());
        scoreUuid.setCreateName(remark);
        scoreUuid.setStartdate(times);
        scoreUuid.setNumber(number);
        this.save(scoreUuid);
        return uuid;
    }

    @Override
    public String saveNumber(String number) {
        String uuid = UUID.randomUUID().toString();
        ScoreUuid scoreUuid = new ScoreUuid();
        scoreUuid.setUuid(uuid);
        scoreUuid.setIsUser(false);
        String times = TimeUtiles.getStringDate();
        scoreUuid.setCreateDate(times);
        scoreUuid.setCreateName("简单生成");
        scoreUuid.setStartdate(times);
        scoreUuid.setNumber(number.toString());
        this.save(scoreUuid);
        return uuid;
    }


}
