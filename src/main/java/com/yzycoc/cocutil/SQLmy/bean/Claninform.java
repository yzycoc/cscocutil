package com.yzycoc.cocutil.SQLmy.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 战队赛报名的
 */
@Data
@TableName("claninform")
public class Claninform implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private String qunnumber;
	private String kenning;//代称
	private String type;//部落战，联赛/部落战
	private String time;//用来储存定时任务，间隔时间
	private String msg;//提示语
	private boolean isgoto;//是否开启
	private String clansclanId;
	
	public String getQunnumber() {
		return qunnumber;
	}

	public void setQunnumber(String qunnumber) {
		this.qunnumber = qunnumber;
	}

	public String getKenning() {
		return kenning;
	}

	public void setKenning(String kenning) {
		this.kenning = kenning;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isIsgoto() {
		return isgoto;
	}

	public void setIsgoto(boolean isgoto) {
		this.isgoto = isgoto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public String getClansclanId() {
		return clansclanId;
	}

	public void setClansclanId(String clansclanId) {
		this.clansclanId = clansclanId;
	}
	
	
	
}
