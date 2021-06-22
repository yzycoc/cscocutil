package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: cscocutil
 * @description:查询阵型
 * @author: yzy
 * @create: 2020-08-29 09:40
 * @Version 1.0
 **/
@Data
@TableName("open_layout")
public class OpenLayout  implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String imageUrl;//图片地址
    private String url;//阵型地址
    private String type;//阵型类型
    private String remark;//底部 方便给版权的
    private String number;//编号
    private String createName; // 更新人
    private String createDate; // 更新时间
    private String dlurl;//短链的
    private String dldate;
    private String label;//标签

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCreateName() {
        return createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getDlurl() {
        return dlurl;
    }
    public void setDlurl(String dlurl) {
        this.dlurl = dlurl;
    }
    public String getDldate() {
        return dldate;
    }
    public void setDldate(String dldate) {
        this.dldate = dldate;
    }

}
