package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-09-08 20:29
 * @Version 1.0
 **/
@Data
public class MyIps {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String createName; // 更新人
    private String createDate; // 更新时间
    private String ip;//ip
    private Integer prot;//端口
    private Boolean status;//网络状态
    private String remark;//服务器备注
    private String cocApi;
    private String updateDate;
    private Integer weight;
    private String intranetIp;//内网IP



    public String getIntranetIp() {
        return intranetIp;
    }
    public void setIntranetIp(String intranetIp) {
        this.intranetIp = intranetIp;
    }


    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Integer getProt() {
        return prot;
    }
    public void setProt(Integer prot) {
        this.prot = prot;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getCocApi() {
        return cocApi;
    }
    public void setCocApi(String cocApi) {
        this.cocApi = cocApi;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

}

