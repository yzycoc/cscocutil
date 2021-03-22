package com.yzycoc.cocutil.SQLClan.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="cocclan")
public class ClanJson {
	private String json;

	private String updatetime;
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
}
