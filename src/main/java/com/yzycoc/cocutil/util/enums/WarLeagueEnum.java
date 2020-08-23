package com.yzycoc.cocutil.util.enums;

public enum WarLeagueEnum {
    ONE("48000000","Unranked","未定级",""),
    ONE1("48000001","Bronze League III","铜杯三",""),
    ONE2("48000002","Bronze League II","铜杯二",""),
    ONE3("48000003","Bronze League I","铜杯一",""),
    ONE4("48000004","Silver League III","银杯三",""),
    ONE5("48000005","Silver League II","银杯二",""),
    TWO0("48000006","Silver League I","银杯一",""),
    TWO1("48000007","Crystal League III","黄金三",""),
    TWO2("48000008","Gold League II","黄金二",""),
    TWO3("48000009","Gold League I","黄金一",""),
    TWO4("48000010","Crystal League III","水晶三",""),
    TWO5("48000011","Crystal League II","水晶二",""),
    TWO6("48000012","Crystal League I","水晶一",""),
    TWO7("48000013","Master League III","大师三",""),
    TWO8("48000014","Master League II","大师二",""),
    TWO9("48000015","Master League I","大师一",""),
    TWO10("48000016","Champion League III","冠军三",""),
    TWO11("48000017","Champion League II","冠军二",""),
    TWO12("48000018","Champion League I","冠军一",""),
    ;
    private String id;
    private String name;
    private String cnName;
    private String image;


    /**
     * 根据ID返回 中文名称
     * @param name
     * @return
     */
    public static String getName(String id) {
        if(id==null) {
            return "未知";
        }
        for (WarLeagueEnum c : WarLeagueEnum.values()) {
            if(c.getId().equals(id)) {
                return c.cnName;
            }
        }
        return "未知";
    }


    private WarLeagueEnum(String id, String name, String cnName, String image) {
        this.id = id;
        this.name = name;
        this.cnName = cnName;
        this.image = image;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCnName() {
        return cnName;
    }
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
