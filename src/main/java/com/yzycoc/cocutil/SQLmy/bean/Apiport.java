package com.yzycoc.cocutil.SQLmy.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/***
 * 储存的各个QQ群的设置信息
 */
@Data
@TableName("apiport")
public class Apiport implements Serializable{

	/**
	 * 踩踩踩
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private String qqnumber;//对应的每个QQ群
	private String type;	//对应的类型
	private String Appkey;		//用户申请的key
	private Boolean isgoto; //是否启用
	private Integer sum;
	private String types;
	private String typeing;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getQqnumber() {
		return qqnumber;
	}

	public void setQqnumber(String qqnumber) {
		this.qqnumber = qqnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppkey() {
		return Appkey;
	}

	public void setAppkey(String appkey) {
		Appkey = appkey;
	}

	public Boolean getIsgoto() {
		return isgoto;
	}

	public void setIsgoto(Boolean isgoto) {
		this.isgoto = isgoto;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTypeing() {
		return typeing;
	}

	public void setTypeing(String typeing) {
		this.typeing = typeing;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
	
	
}
