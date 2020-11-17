package com.yzycoc.cocutil.service.result;


import com.yzycoc.cocutil.SQLAll.bean.ImageDown;
import com.yzycoc.cocutil.SQLAll.service.ImageDownService;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.SpringContextUtil;

import java.util.UUID;

/**
 * @program: cscocutil
 * @description: Coc完成返回的参数
 * @author: yzy
 * @create: 2020-08-10 20:43
 * @Version 1.0
 **/
public class ClanResult {
    private Boolean success;//状态
    private String result;//返回内容 错误内容 ， 文件名称
    private String filePath;//返回的路径
    private String fileType;//返回的文件类型
    private String http;
    public ClanResult() {
        super();
    }
    public ClanResult(String result) {
        this.success = true;
        this.result = result;
    }

    public ClanResult(Boolean success, String result) {
        this.success = success;
        this.result = result;
    }

    /***
     * 插入图片
     * @param success
     * @param result
     * @param filePath
     * @param fileType
     */
    public ClanResult(Boolean success, String result, String filePath, String fileType) {
        this.success = success;
        this.result = result;
        this.filePath = filePath;
        this.fileType = fileType;
        if(success != null && success == true && fileType != null ){
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            ImageDown imageDowm = new ImageDown(result + "." + fileType, uuid, getPath());
            SpringContextUtil.getBean(ImageDownService.class).save(imageDowm);
            this.http = ConfigParameter.network_Path + "imageDown?id="+uuid;
        }
    }

    public String getPath(){
        if(fileType != null){
            return this.filePath+"\\"+this.result+"."+this.fileType;
        }
        return null;
    }


    @Override
    public String toString() {
        return "ClanResult{" +
                "success=" + success +
                ", result='" + result + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }
}
