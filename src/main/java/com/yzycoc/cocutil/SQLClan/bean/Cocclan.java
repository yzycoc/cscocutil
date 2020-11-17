package com.yzycoc.cocutil.SQLClan.bean;

import org.springframework.data.annotation.Id;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@Data
@TableName(value="cocclan")
public class Cocclan {
	@Id
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String tag;
	private String name;//部落名称
	private Integer level;//部落等级
	private Integer points;//主世界奖杯
	private Integer verpoints;//夜世界奖杯
	private Integer members;//部落人数
	private Integer location;//部落地址
	private Integer league;//联赛等级ID
	
	private String json;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getVerpoints() {
		return verpoints;
	}
	public void setVerpoints(Integer verpoints) {
		this.verpoints = verpoints;
	}
	public Integer getMembers() {
		return members;
	}
	public void setMembers(Integer members) {
		this.members = members;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public Integer getLeague() {
		return league;
	}
	public void setLeague(Integer league) {
		this.league = league;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	
	
}
