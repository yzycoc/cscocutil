package com.yzycoc.cocutil.service.result;

/**
 * @program: cscocutil
 * @description: Coc完成返回的参数
 * @author: yzy
 * @create: 2020-08-10 20:43
 * @Version 1.0
 **/
public class ClanResult {
    private Boolean success;//状态
    private String result;//返回内容
    private String filePath;//返回的路径
    private String fileType;//返回的文件类型
    public ClanResult() {
        super();
    }
    public ClanResult(String result) {
        this.success = true;
        this.result = result;
    }
    public ClanResult(String result,String filePath) {
        this.success = true;
        this.result = result;
        this.filePath = filePath;
    }
    public ClanResult(Boolean success, String result) {
        this.success = success;
        this.result = result;
    }

    public ClanResult(Boolean success, String result, String filePath) {
        this.success = success;
        this.result = result;
        this.filePath = filePath;
    }

    public ClanResult(Boolean success, String result, String filePath, String fileType) {
        this.success = success;
        this.result = result;
        this.filePath = filePath;
        this.fileType = fileType;
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

}
