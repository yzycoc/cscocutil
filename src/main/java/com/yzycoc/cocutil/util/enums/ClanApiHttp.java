package com.yzycoc.cocutil.util.enums;

/***
 * Http请求体
 *
 *
 */
public enum ClanApiHttp {
    ClanPlayer("https://api.clashofclans.com/v1/clans/%23",30),
    Clan("https://api.clashofclans.com/v1/clans/%23",300),
    player("https://api.clashofclans.com/v1/players/%23",420),
    //Clan("http://47.100.197.180:8855/coctest?tag=",1),
    //player("http://47.100.197.180:8855/coc?tag=",1);
    //Clan("http://47.100.197.180:8855/coctest?tag=",1),
    //player("http://47.100.197.180:8081/player?tag=",1);

    //Clan("http://47.116.19.225:8081/c?tag=",500),
    //player("http://47.116.19.225:8081/p?tag=",600)




    ;

    private ClanApiHttp(String path, long time) {
        this.path = path;
        this.time = time;
    }

    private String path;

    private long time;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
