package com.yzycoc.custom.emuns;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-27 23:05
 * @Version 1.0
 **/
public enum CacheCocEmuns {
    Yuqing("yq",10),
    clan("clan",5),
    player("player",5),
    clanImage("clanImage",5),
    clanText("clanText",5),
    clanAll("clanAll",5),
    clanName("clanName",0),
    clanwar("clanwar", 5),
    leagueinfo("leagueinfo",5)//联赛统计
    ,
    leaguewar("leaguewar",5);//联赛对战;

    CacheCocEmuns(String redisType, Integer redisTime) {
        this.redisType = redisType;
        this.redisTime = redisTime;
    }

    private String redisType;
    private Integer redisTime;

    public String getRedisType() {
        return redisType;
    }

    public void setRedisType(String redisType) {
        this.redisType = redisType;
    }

    public Integer getRedisTime() {
        return redisTime;
    }

    public void setRedisTime(Integer redisTime) {
        this.redisTime = redisTime;
    }
}
