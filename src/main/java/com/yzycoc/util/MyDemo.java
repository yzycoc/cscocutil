/*
package com.yzycoc.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.util.tableImage.ImageName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 18:47
 * @Version 1.0
 **//*

public class MyDemo<T> {

    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray troops = jsonObject.getJSONArray("spells");
        for (int i = 0; i < troops.size(); i++) {
            JSONObject JSON = troops.getJSONObject(i);
            String village = JSON.getString("village");
            if(village.equals("home")){
                String name = JSON.getString("name");
                String key = JSON.getString("village")+"|"+name;
                //System.out.println("h.put(\""+key+"\",\"\");");
                name = toLowerCaseFirstOne(name.replace(".","").replace(" ",""));
                String setname = toLowerCaseFirstOne2(name);
                //System.out.println("@ImageName(\""+h.get(key)+"\")\nprivate String "+name+";");
               */
/* System.out.println("case \""++"\"");
                System.out.println();
                System.out.println();*//*

                System.out.println("case \""+JSON.getString("name")+"\":\n" +
                        "                            data.set"+setname+"(JSON.getString(\"level\"));\n" +
                        "                            break;");
            }

        }
        troops = jsonObject.getJSONArray("troops");
        for (int i = 0; i < troops.size(); i++) {
            JSONObject JSON = troops.getJSONObject(i);
            String village = JSON.getString("village");
            if(village.equals("home")){
                String name = JSON.getString("name");
                String key = JSON.getString("village")+"|"+name;
                //System.out.println("h.put(\""+key+"\",\"\");");
                name = toLowerCaseFirstOne(name.replace(".","").replace(" ",""));
                String setname = toLowerCaseFirstOne2(name);
                //System.out.println("@ImageName(\""+h.get(key)+"\")\nprivate String "+name+";");
               */
/* System.out.println("case \""+JSON.getString("name")+"\":\n" +
                        "                            data.set"+setname+"(JSON.getString(\"level\"));\n" +
                        "                            break;");*//*


            }

        }
        for (int i = 0; i < troops.size(); i++) {
            JSONObject JSON = troops.getJSONObject(i);
            if(JSON.getString("village").equals("builderBase")){
                String name = JSON.getString("name");
                String key = JSON.getString("village")+"|"+name;
                name = toLowerCaseFirstOne(name.replace(".","").replace(" ",""));
                //System.out.println("@ImageName(\""+h.get(key)+"\")\nprivate String bb"+name+";");
                String setname = toLowerCaseFirstOne2("Bb"+name);
                */
/*System.out.println("case \""+JSON.getString("name")+"\":\n" +
                        "                            data.set"+setname+"(JSON.getString(\"level\"));\n" +
                        "                            break;");*//*

            }
        }
    }

    private static Map<String,String> h = new HashMap<>();

    static{
        h.put("home|Barbarian","野蛮人");
        h.put("home|Archer","弓箭手");
        h.put("home|Goblin","哥布林");
        h.put("home|Giant","巨人");
        h.put("home|Wall Breaker","炸弹人");
        h.put("home|Balloon","气球");
        h.put("home|Wizard","法师");
        h.put("home|Healer","天使");
        h.put("home|Dragon","龙");
        h.put("home|P.E.K.K.A","皮卡");
        h.put("home|Minion","亡灵");
        h.put("home|Hog Rider","野猪");
        h.put("home|Valkyrie","武神");
        h.put("home|Golem","石头人");
        h.put("home|Witch","女巫");
        h.put("home|Lava Hound","狗");
        h.put("home|Bowler","蓝胖");
        h.put("home|Baby Dragon","龙宝宝");
        h.put("home|Miner","矿工");
        h.put("home|Super Barbarian","超级野蛮人");
        h.put("home|Super Archer","");
        h.put("home|Super Wall Breaker","超级炸弹人");
        h.put("home|Super Giant","超级巨人");
        h.put("builderBase|Raged Barbarian","野蛮人");
        h.put("builderBase|Sneaky Archer","弓箭手");
        h.put("builderBase|Beta Minion","亡灵");
        h.put("builderBase|Boxer Giant","巨人");
        h.put("builderBase|Bomber","炸弹人");
        h.put("builderBase|Super P.E.K.K.A","皮卡");
        h.put("builderBase|Cannon Cart","炮车");
        h.put("builderBase|Drop Ship","骷髅飞艇");
        h.put("builderBase|Baby Dragon","龙宝宝");
        h.put("builderBase|Night Witch","女巫");
        h.put("home|Wall Wrecker","攻城车");
        h.put("home|Battle Blimp","飞艇");
        h.put("home|Yeti","雪怪");
        h.put("home|Sneaky Goblin","超级哥布林");
        h.put("home|Ice Golem","冰人");
        h.put("home|Electro Dragon","电龙");
        h.put("home|Stone Slammer","战争机器");
        h.put("home|Inferno Dragon","地狱龙");
        h.put("home|Super Valkyrie","超级武神");
        h.put("home|Super Witch","超级女巫");
        h.put("builderBase|Hog Glider","飞猪");
        h.put("home|Siege Barracks","攻城训练营");
        h.put("home|Ice Hound","冰狗");
        h.put("home|Headhunter","猎手");
        h.put("home|Super Wizard","超级法师");
        h.put("home|Super Minion","超级亡灵");
        h.put("home|Log Launcher","滚木车");
        h.put("home|Lightning Spell","雷电");
        h.put("home|Healing Spell","治疗");
        h.put("home|Rage Spell","狂暴");
        h.put("home|Jump Spell","弹跳");
        h.put("home|Freeze Spell","冰冻");
        h.put("home|Poison Spell","毒药");
        h.put("home|Earthquake Spell","地震");
        h.put("home|Haste Spell","快速");
        h.put("home|Clone Spell","克隆");
        h.put("home|Skeleton Spell","骷髅");
        h.put("home|Bat Spell","蝙蝠");
        h.put("home|Invisibility Spell","复制");

    }
    */
/**
     * 将字符串的首字母转大写
     * @return
     *//*

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    */
/**
     * 将字符串的首字母转大写
     * @return
     *//*

    public static String toLowerCaseFirstOne2(String s){
        String substring = s.substring(0, 1);
        return substring.toUpperCase() + s.substring(1,s.length());
    }

    public void m(List<T> e) throws IllegalAccessException {
        */
/*String class_name = clazz.getName();
        Field[] tableFields = clazz.getDeclaredFields();
        Class<?> superClazz = clazz.getSuperclass();
        for (Field field : tableFields) {
            field.setAccessible(true);
            System.out.println(class_name + ":" + field.getName());
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println(class_name + ":" + method.getName());
        }*//*

        for (int i = 0; i < e.size(); i++) {
            T t = e.get(i);
            Class<?> aClass = t.getClass();
            System.out.println(t.toString());
            if(i == 0){
                Field[] field = aClass.getDeclaredFields();
                if(field != null){
                    for(Field fie : field){
                        if(!fie.isAccessible()){
                            fie.setAccessible(true);
                        }
                        ImageName annotation = fie.getAnnotation(ImageName.class);
                        System.out.println(annotation.value());
                    }
                }
            }
        }
    }

    public <T> List<List<String>> objListToStringList(List<T> list){
        List<List<String>> res=new ArrayList<>();
        for(T element:list){
            Field[] declaredFields = element.getClass().getDeclaredFields();
            List<String> values=new ArrayList<>();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                try {
                    Object obj = field.get(element);
                    values.add(obj.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            res.add(values);
        }
        return res;
    }
     private static String json = "{\n" +
             "  \"tag\": \"#PR08898Y2\",\n" +
             "  \"name\": \"北凉❤️✨三月✨\",\n" +
             "  \"townHallLevel\": 13,\n" +
             "  \"townHallWeaponLevel\": 5,\n" +
             "  \"expLevel\": 222,\n" +
             "  \"trophies\": 7478,\n" +
             "  \"bestTrophies\": 7558,\n" +
             "  \"warStars\": 1319,\n" +
             "  \"attackWins\": 279,\n" +
             "  \"defenseWins\": 4,\n" +
             "  \"builderHallLevel\": 9,\n" +
             "  \"versusTrophies\": 3354,\n" +
             "  \"bestVersusTrophies\": 3574,\n" +
             "  \"versusBattleWins\": 1634,\n" +
             "  \"role\": \"member\",\n" +
             "  \"donations\": 0,\n" +
             "  \"donationsReceived\": 462,\n" +
             "  \"clan\": {\n" +
             "    \"tag\": \"#22QYVRPYG\",\n" +
             "    \"name\": \"北凉\",\n" +
             "    \"clanLevel\": 15,\n" +
             "    \"badgeUrls\": {\n" +
             "      \"small\": \"https://api-assets.clashofclans.com/badges/70/ZTWCqihAsAEtmyertYtLkykt8qq6AxUrneyLivKPOJw.png\",\n" +
             "      \"large\": \"https://api-assets.clashofclans.com/badges/512/ZTWCqihAsAEtmyertYtLkykt8qq6AxUrneyLivKPOJw.png\",\n" +
             "      \"medium\": \"https://api-assets.clashofclans.com/badges/200/ZTWCqihAsAEtmyertYtLkykt8qq6AxUrneyLivKPOJw.png\"\n" +
             "    }\n" +
             "  },\n" +
             "  \"league\": {\n" +
             "    \"id\": 29000022,\n" +
             "    \"name\": \"Legend League\",\n" +
             "    \"iconUrls\": {\n" +
             "      \"small\": \"https://api-assets.clashofclans.com/leagues/72/R2zmhyqQ0_lKcDR5EyghXCxgyC9mm_mVMIjAbmGoZtw.png\",\n" +
             "      \"tiny\": \"https://api-assets.clashofclans.com/leagues/36/R2zmhyqQ0_lKcDR5EyghXCxgyC9mm_mVMIjAbmGoZtw.png\",\n" +
             "      \"medium\": \"https://api-assets.clashofclans.com/leagues/288/R2zmhyqQ0_lKcDR5EyghXCxgyC9mm_mVMIjAbmGoZtw.png\"\n" +
             "    }\n" +
             "  },\n" +
             "  \"legendStatistics\": {\n" +
             "    \"legendTrophies\": 5965,\n" +
             "    \"previousSeason\": {\n" +
             "      \"id\": \"2021-02\",\n" +
             "      \"rank\": 2469,\n" +
             "      \"trophies\": 5692\n" +
             "    },\n" +
             "    \"bestSeason\": {\n" +
             "      \"id\": \"2020-08\",\n" +
             "      \"rank\": 4,\n" +
             "      \"trophies\": 6713\n" +
             "    },\n" +
             "    \"currentSeason\": {\n" +
             "      \"rank\": 1,\n" +
             "      \"trophies\": 7478\n" +
             "    }\n" +
             "  },\n" +
             "  \"achievements\": [\n" +
             "    {\n" +
             "      \"name\": \"Bigger Coffers\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 14,\n" +
             "      \"target\": 10,\n" +
             "      \"info\": \"Upgrade a Gold Storage to level 10\",\n" +
             "      \"completionInfo\": \"Highest Gold Storage level: 14\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Get those Goblins!\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 225,\n" +
             "      \"target\": 150,\n" +
             "      \"info\": \"Win 150 Stars on the Campaign Map\",\n" +
             "      \"completionInfo\": \"Stars in Campaign Map: 225\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Bigger & Better\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 13,\n" +
             "      \"target\": 8,\n" +
             "      \"info\": \"Upgrade Town Hall to level 8\",\n" +
             "      \"completionInfo\": \"Current Town Hall level: 13\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Nice and Tidy\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 3786,\n" +
             "      \"target\": 500,\n" +
             "      \"info\": \"Remove 500 obstacles (trees, rocks, bushes)\",\n" +
             "      \"completionInfo\": \"Total obstacles removed: 3786\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Release the Beasts\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 1,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Unlock Dragon in the Barracks\",\n" +
             "      \"completionInfo\": null,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Gold Grab\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 2000000000,\n" +
             "      \"target\": 100000000,\n" +
             "      \"info\": \"Steal 100000000 gold\",\n" +
             "      \"completionInfo\": \"Total Gold looted: 2000000000\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Elixir Escapade\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 2000000000,\n" +
             "      \"target\": 100000000,\n" +
             "      \"info\": \"Steal 100000000 elixir\",\n" +
             "      \"completionInfo\": \"Total Elixir looted: 2000000000\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Sweet Victory!\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 7558,\n" +
             "      \"target\": 1250,\n" +
             "      \"info\": \"Achieve a total of 1250 trophies in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Trophy record: 7558\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Empire Builder\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 9,\n" +
             "      \"target\": 4,\n" +
             "      \"info\": \"Upgrade Clan Castle to level 4\",\n" +
             "      \"completionInfo\": \"Current Clan Castle level: 9\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Wall Buster\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 129798,\n" +
             "      \"target\": 2000,\n" +
             "      \"info\": \"Destroy 2000 Walls in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total walls destroyed: 129798\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Humiliator\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 6456,\n" +
             "      \"target\": 2000,\n" +
             "      \"info\": \"Destroy 2000 Town Halls in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total Town Halls destroyed: 6456\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Union Buster\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 61712,\n" +
             "      \"target\": 2500,\n" +
             "      \"info\": \"Destroy 2500 Builder's Huts in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total Builder's Huts destroyed: 61712\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Conqueror\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 12094,\n" +
             "      \"target\": 5000,\n" +
             "      \"info\": \"Win 5000 Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total multiplayer battles won: 12094\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Unbreakable\",\n" +
             "      \"stars\": 2,\n" +
             "      \"value\": 1200,\n" +
             "      \"target\": 5000,\n" +
             "      \"info\": \"Successfully defend against 5000 attacks\",\n" +
             "      \"completionInfo\": \"Total defenses won: 1200\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Friend in Need\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 333772,\n" +
             "      \"target\": 25000,\n" +
             "      \"info\": \"Donate 25000 Clan Castle capacity worth of reinforcements\",\n" +
             "      \"completionInfo\": \"Total capacity donated: 333772\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Mortar Mauler\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 34443,\n" +
             "      \"target\": 5000,\n" +
             "      \"info\": \"Destroy 5000 Mortars in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total Mortars destroyed: 34443\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Heroic Heist\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 43139507,\n" +
             "      \"target\": 1000000,\n" +
             "      \"info\": \"Steal 1000000 Dark Elixir\",\n" +
             "      \"completionInfo\": \"Total Dark Elixir looted: 43139507\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"League All-Star\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 22,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Become a Champion!\",\n" +
             "      \"completionInfo\": null,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"X-Bow Exterminator\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 23568,\n" +
             "      \"target\": 2500,\n" +
             "      \"info\": \"Destroy 2500 X-Bows in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total X-Bows destroyed: 23568\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Firefighter\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 12728,\n" +
             "      \"target\": 5000,\n" +
             "      \"info\": \"Destroy 5000 Inferno Towers in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total Inferno Towers destroyed: 12728\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"War Hero\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 1319,\n" +
             "      \"target\": 1000,\n" +
             "      \"info\": \"Score 1000 Stars for your clan in Clan War battles\",\n" +
             "      \"completionInfo\": \"Total Stars scored for clan in Clan War battles: 1319\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Treasurer\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 902181112,\n" +
             "      \"target\": 100000000,\n" +
             "      \"info\": \"Collect 100000000 gold from the Treasury\",\n" +
             "      \"completionInfo\": \"Total gold collected in Clan War bonuses: 902181112\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Anti-Artillery\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 4419,\n" +
             "      \"target\": 2000,\n" +
             "      \"info\": \"Destroy 2000 Eagle Artilleries in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total Eagle Artilleries destroyed: 4419\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Sharing is caring\",\n" +
             "      \"stars\": 2,\n" +
             "      \"value\": 6624,\n" +
             "      \"target\": 10000,\n" +
             "      \"info\": \"Donate 10000 spell storage capacity worth of spells\",\n" +
             "      \"completionInfo\": \"Total spell capacity donated: 6624\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Keep your village safe\",\n" +
             "      \"stars\": 0,\n" +
             "      \"value\": 0,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Connect your account to a social network for safe keeping.\",\n" +
             "      \"completionInfo\": \"Completed!\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Master Engineering\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 9,\n" +
             "      \"target\": 8,\n" +
             "      \"info\": \"Upgrade Builder Hall to level 8\",\n" +
             "      \"completionInfo\": \"Current Builder Hall level: 9\",\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Next Generation Model\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 1,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Unlock Super P.E.K.K.A in the Builder Barracks\",\n" +
             "      \"completionInfo\": null,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Un-Build It\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 2419,\n" +
             "      \"target\": 2000,\n" +
             "      \"info\": \"Destroy 2000 Builder Halls in Versus battles\",\n" +
             "      \"completionInfo\": \"Total Builder Halls destroyed: 2419\",\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Champion Builder\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 3574,\n" +
             "      \"target\": 3000,\n" +
             "      \"info\": \"Achieve a total of 3000 trophies in Versus battles\",\n" +
             "      \"completionInfo\": \"Versus Trophy record: 3574\",\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"High Gear\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 3,\n" +
             "      \"target\": 3,\n" +
             "      \"info\": \"Gear Up 3 buildings using the Master Builder\",\n" +
             "      \"completionInfo\": \"Total buildings geared up: 3\",\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Hidden Treasures\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 1,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Rebuild Battle Machine\",\n" +
             "      \"completionInfo\": null,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Games Champion\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 129110,\n" +
             "      \"target\": 100000,\n" +
             "      \"info\": \"Earn 100000 points in Clan Games\",\n" +
             "      \"completionInfo\": \"Total Clan Games points: 129110\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Dragon Slayer\",\n" +
             "      \"stars\": 0,\n" +
             "      \"value\": 0,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Slay the Giant Dragon\",\n" +
             "      \"completionInfo\": null,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"War League Legend\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 412,\n" +
             "      \"target\": 250,\n" +
             "      \"info\": \"Score 250 Stars for your clan in War League battles\",\n" +
             "      \"completionInfo\": \"Total Stars scored for clan in War League battles: 412\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Keep your village safe\",\n" +
             "      \"stars\": 0,\n" +
             "      \"value\": 0,\n" +
             "      \"target\": 1,\n" +
             "      \"info\": \"Connect your account to Supercell ID for safe keeping.\",\n" +
             "      \"completionInfo\": \"Completed!\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Well Seasoned\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 56750,\n" +
             "      \"target\": 50000,\n" +
             "      \"info\": \"Earn 50000 points in Season Challenges\",\n" +
             "      \"completionInfo\": \"Total Season Challenges points: 56750\",\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Shattered and Scattered\",\n" +
             "      \"stars\": 3,\n" +
             "      \"value\": 4537,\n" +
             "      \"target\": 4000,\n" +
             "      \"info\": \"Destroy 4000 Scattershots in Multiplayer battles\",\n" +
             "      \"completionInfo\": \"Total Scattershots destroyed: 4537\",\n" +
             "      \"village\": \"home\"\n" +
             "    }\n" +
             "  ],\n" +
             "  \"versusBattleWinCount\": 1634,\n" +
             "  \"labels\": [\n" +
             "    {\n" +
             "      \"id\": 57000010,\n" +
             "      \"name\": \"Hungry Learner\",\n" +
             "      \"iconUrls\": {\n" +
             "        \"small\": \"https://api-assets.clashofclans.com/labels/64/jEvZf9PnfPaqYh2PMLBoJfB1BoBpomerqmsYWDYisKY.png\",\n" +
             "        \"medium\": \"https://api-assets.clashofclans.com/labels/128/jEvZf9PnfPaqYh2PMLBoJfB1BoBpomerqmsYWDYisKY.png\"\n" +
             "      }\n" +
             "    },\n" +
             "    {\n" +
             "      \"id\": 57000016,\n" +
             "      \"name\": \"Newbie\",\n" +
             "      \"iconUrls\": {\n" +
             "        \"small\": \"https://api-assets.clashofclans.com/labels/64/PcgplBTQo2W_PXYqMi0i6g6nrNMjzCM8Ipd_umSnuHw.png\",\n" +
             "        \"medium\": \"https://api-assets.clashofclans.com/labels/128/PcgplBTQo2W_PXYqMi0i6g6nrNMjzCM8Ipd_umSnuHw.png\"\n" +
             "      }\n" +
             "    },\n" +
             "    {\n" +
             "      \"id\": 57000017,\n" +
             "      \"name\": \"Amateur Attacker\",\n" +
             "      \"iconUrls\": {\n" +
             "        \"small\": \"https://api-assets.clashofclans.com/labels/64/8Q08M2dj1xz1Zx-sAre6QO14hOX2aiEvg-FaGGSX-7M.png\",\n" +
             "        \"medium\": \"https://api-assets.clashofclans.com/labels/128/8Q08M2dj1xz1Zx-sAre6QO14hOX2aiEvg-FaGGSX-7M.png\"\n" +
             "      }\n" +
             "    }\n" +
             "  ],\n" +
             "  \"troops\": [\n" +
             "    {\n" +
             "      \"name\": \"Barbarian\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Archer\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Goblin\",\n" +
             "      \"level\": 8,\n" +
             "      \"maxLevel\": 8,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Giant\",\n" +
             "      \"level\": 10,\n" +
             "      \"maxLevel\": 10,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Wall Breaker\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Balloon\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Wizard\",\n" +
             "      \"level\": 10,\n" +
             "      \"maxLevel\": 10,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Healer\",\n" +
             "      \"level\": 6,\n" +
             "      \"maxLevel\": 6,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Dragon\",\n" +
             "      \"level\": 8,\n" +
             "      \"maxLevel\": 8,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"P.E.K.K.A\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Minion\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Hog Rider\",\n" +
             "      \"level\": 10,\n" +
             "      \"maxLevel\": 10,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Valkyrie\",\n" +
             "      \"level\": 8,\n" +
             "      \"maxLevel\": 8,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Golem\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 10,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Witch\",\n" +
             "      \"level\": 5,\n" +
             "      \"maxLevel\": 5,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Lava Hound\",\n" +
             "      \"level\": 6,\n" +
             "      \"maxLevel\": 6,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Bowler\",\n" +
             "      \"level\": 5,\n" +
             "      \"maxLevel\": 5,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Baby Dragon\",\n" +
             "      \"level\": 7,\n" +
             "      \"maxLevel\": 7,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Miner\",\n" +
             "      \"level\": 7,\n" +
             "      \"maxLevel\": 7,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Barbarian\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Archer\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Wall Breaker\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 3,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Giant\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Raged Barbarian\",\n" +
             "      \"level\": 18,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Sneaky Archer\",\n" +
             "      \"level\": 13,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Beta Minion\",\n" +
             "      \"level\": 13,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Boxer Giant\",\n" +
             "      \"level\": 15,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Bomber\",\n" +
             "      \"level\": 16,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super P.E.K.K.A\",\n" +
             "      \"level\": 3,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Cannon Cart\",\n" +
             "      \"level\": 18,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Drop Ship\",\n" +
             "      \"level\": 8,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Baby Dragon\",\n" +
             "      \"level\": 12,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Night Witch\",\n" +
             "      \"level\": 12,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Wall Wrecker\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Battle Blimp\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Yeti\",\n" +
             "      \"level\": 3,\n" +
             "      \"maxLevel\": 3,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Sneaky Goblin\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Ice Golem\",\n" +
             "      \"level\": 5,\n" +
             "      \"maxLevel\": 5,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Electro Dragon\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Stone Slammer\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Inferno Dragon\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Valkyrie\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Witch\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 1,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Hog Glider\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 18,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Siege Barracks\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Ice Hound\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Headhunter\",\n" +
             "      \"level\": 3,\n" +
             "      \"maxLevel\": 3,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Wizard\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Super Minion\",\n" +
             "      \"level\": 1,\n" +
             "      \"maxLevel\": 2,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Log Launcher\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    }\n" +
             "  ],\n" +
             "  \"heroes\": [\n" +
             "    {\n" +
             "      \"name\": \"Barbarian King\",\n" +
             "      \"level\": 75,\n" +
             "      \"maxLevel\": 75,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Archer Queen\",\n" +
             "      \"level\": 75,\n" +
             "      \"maxLevel\": 75,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Grand Warden\",\n" +
             "      \"level\": 50,\n" +
             "      \"maxLevel\": 50,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Battle Machine\",\n" +
             "      \"level\": 30,\n" +
             "      \"maxLevel\": 30,\n" +
             "      \"village\": \"builderBase\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Royal Champion\",\n" +
             "      \"level\": 25,\n" +
             "      \"maxLevel\": 25,\n" +
             "      \"village\": \"home\"\n" +
             "    }\n" +
             "  ],\n" +
             "  \"spells\": [\n" +
             "    {\n" +
             "      \"name\": \"Lightning Spell\",\n" +
             "      \"level\": 9,\n" +
             "      \"maxLevel\": 9,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Healing Spell\",\n" +
             "      \"level\": 8,\n" +
             "      \"maxLevel\": 8,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Rage Spell\",\n" +
             "      \"level\": 6,\n" +
             "      \"maxLevel\": 6,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Jump Spell\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Freeze Spell\",\n" +
             "      \"level\": 7,\n" +
             "      \"maxLevel\": 7,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Poison Spell\",\n" +
             "      \"level\": 7,\n" +
             "      \"maxLevel\": 7,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Earthquake Spell\",\n" +
             "      \"level\": 5,\n" +
             "      \"maxLevel\": 5,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Haste Spell\",\n" +
             "      \"level\": 5,\n" +
             "      \"maxLevel\": 5,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Clone Spell\",\n" +
             "      \"level\": 6,\n" +
             "      \"maxLevel\": 6,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Skeleton Spell\",\n" +
             "      \"level\": 7,\n" +
             "      \"maxLevel\": 7,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Bat Spell\",\n" +
             "      \"level\": 5,\n" +
             "      \"maxLevel\": 5,\n" +
             "      \"village\": \"home\"\n" +
             "    },\n" +
             "    {\n" +
             "      \"name\": \"Invisibility Spell\",\n" +
             "      \"level\": 4,\n" +
             "      \"maxLevel\": 4,\n" +
             "      \"village\": \"home\"\n" +
             "    }\n" +
             "  ]\n" +
             "}";
}
*/
