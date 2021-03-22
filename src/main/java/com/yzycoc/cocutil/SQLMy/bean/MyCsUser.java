package com.yzycoc.cocutil.SQLMy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import lombok.Data;

import java.util.Date;
@Data
@TableName("cs_user")
public class MyCsUser  extends CsUser {
    private Integer id;
}
