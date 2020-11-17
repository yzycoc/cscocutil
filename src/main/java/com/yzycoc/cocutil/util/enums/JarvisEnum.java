package com.yzycoc.cocutil.util.enums;

public enum JarvisEnum {
    clanwar("clanwar") //部落战
    ,
    leagueinfo("leagueinfo")//联赛统计
    ,
    leaguewar("leaguewar");//联赛对战

    private String type;
    public static JarvisEnum get(String jarvis){
        for (JarvisEnum e : JarvisEnum.values()) {
            if(e.getType().equals(jarvis)){
                return e;
            }
        }
        return null;
    }
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
