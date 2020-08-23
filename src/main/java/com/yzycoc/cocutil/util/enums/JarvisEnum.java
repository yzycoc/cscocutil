package com.yzycoc.cocutil.util.enums;

public enum JarvisEnum {
    clanwar("clanwar")
    ,
    leagueinfo("leagueinfo")
    ,
    leaguewar("leaguewar");

    private String type;

    JarvisEnum(String type) {
        this.type = type;
    }
    JarvisEnum() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
