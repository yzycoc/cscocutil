package com.yzycoc.cocutil.SQLClan.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@Data
@TableName(value="player")
public class PlayerJson{
	
	private String json;

	private String updatetime;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
}
