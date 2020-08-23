package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="cocclan")
public class ClanName {
	private String name;
	private String tag;
	private Integer members;
	private Integer level;
	private Integer points;
	private Integer league;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getMembers() {
		return members;
	}
	public void setMembers(Integer members) {
		this.members = members;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getLeague() {
		return league;
	}
	public void setLeague(Integer league) {
		this.league = league;
	}
	
	
}
