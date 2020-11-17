package com.yzycoc.cocutil.SQLClan.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="player")
public class PlayerName {
	private String tag;//玩家标签
	private String name;//玩家名称
	private Integer town;//大本营
	private Integer towlevel;//大本营等级
	private Integer exp;//玩家等级
	private String clanname;//玩家标签
	private Integer trophies;//主世界奖杯
	private Integer buillevel;//夜世界大本营
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTown() {
		return town;
	}
	public void setTown(Integer town) {
		this.town = town;
	}
	public Integer getTowlevel() {
		return towlevel;
	}
	public void setTowlevel(Integer towlevel) {
		this.towlevel = towlevel;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Integer getTrophies() {
		return trophies;
	}
	public void setTrophies(Integer trophies) {
		this.trophies = trophies;
	}
	public Integer getBuillevel() {
		return buillevel;
	}
	public void setBuillevel(Integer buillevel) {
		this.buillevel = buillevel;
	}
	public String getClanname() {
		return clanname;
	}
	public void setClanname(String clanname) {
		this.clanname = clanname;
	}
	
	
}
