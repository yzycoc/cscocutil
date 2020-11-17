package com.yzycoc.cocutil.SQLAll.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yzycoc.custom.TimeUtiles;
import lombok.Data;

import java.sql.*;
import java.util.UUID;

/**
 * @program: cscocutil
 * @description: 图片生成后保存位置和网络下载路径
 * @author: yzy
 * @create: 2020-10-30 10:35
 * @Version 1.0
 **/
@Data
public class ImageDown {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String fileName;//文件名
    private String fileUuid;//查询文件的UUID
    private String filePath;//文件的路径
    private String sum;//查询次数
    private String createDate;//插入时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ImageDown() {
    }

    public ImageDown(String fileName, String fileUuid, String filePath) {
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.sum = "0";
        this.createDate = TimeUtiles.getStringDate();
    }
}
