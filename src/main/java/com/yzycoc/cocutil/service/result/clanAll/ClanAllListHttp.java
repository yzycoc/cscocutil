package com.yzycoc.cocutil.service.result.clanAll;

import com.alibaba.fastjson.JSONObject;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.ImageMapCache;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-22 13:42
 * @Version 1.0
 **/
public class ClanAllListHttp extends ClanResult {
    private JSONObject clan;
    private List<JSONObject> memberPlayer = new ArrayList<JSONObject>();
    private BufferedImage clanImage;



    public JSONObject getClan() {
        return clan;
    }
    public void setClan(JSONObject clan) {
        this.clan = clan;
    }
    public List<JSONObject> getMemberPlayer() {
        return memberPlayer;
    }
    public void setMemberPlayer(List<JSONObject> memberPlayer) {
        this.memberPlayer = memberPlayer;
    }

    public ClanAllListHttp(JSONObject clan, List<JSONObject> memberPlayer) {
        super();
        this.clan = clan;
        this.memberPlayer = memberPlayer;
    }
    public ClanAllListHttp() {
        super();
        // TODO Auto-generated constructor stub
    }
    public ClanAllListHttp(Boolean success, String msg) {
        super(success, msg);
        if(success!=null&&!success){
            System.out.println(msg);
        }
    }
    public ClanAllListHttp(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }
    public BufferedImage getClanImage() {
        if(clanImage==null) {
            clanImage = ImageMapCache.getImage("badgeUrlsClan");
        }
        return clanImage;
    }
    public void setClanImage(BufferedImage clanImage) {
        this.clanImage = clanImage;
    }
    public ClanAllListHttp(String msg, JSONObject clan, List<JSONObject> memberPlayer, BufferedImage clanImage) {
        super(msg);
        this.clan = clan;
        this.memberPlayer = memberPlayer;
        this.clanImage = clanImage;
    }
}
