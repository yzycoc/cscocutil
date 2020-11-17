package com.yzycoc.cocutil.SQLAll.result;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-30 10:00
 * @Version 1.0
 **/
public class VipSysUser {
    private Boolean isUse = true;
    private String qq;//管理QQ
    private Boolean gograde = false;//进群欢迎语
    private Boolean robot = false;//机器人开关
    private Boolean gogradenikename = false;//进群修改群名片
    private Boolean tograde = false;//退群欢送语
    private Boolean cocgotograde = false;//COC一键设置
    private Boolean gotoaddinput = false;//自动同意进群
    /** 禁言 */
    private Boolean spamState = false;
    private Integer statementsum = 3;//相同语句次数
    private Integer msglength;//发言的长度
    private Integer msgfrequency;//30S内发言频率
    private long times;//设置间隔时间
    private long time;//设置禁言时长
    /** coc状态 */
    private Boolean cocState = false;
    private String cocApi;
    /** 图灵状态 */
    private Boolean tulingState = false;
    private String tulinApi;
    /** 查询快递状态 */
    private Boolean emsState = false;
    private String emsApi;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean use) {
        isUse = use;
    }

    public Boolean getGograde() {
        return gograde;
    }

    public void setGograde(Boolean gograde) {
        this.gograde = gograde;
    }

    public Boolean getRobot() {
        return robot;
    }

    public void setRobot(Boolean robot) {
        this.robot = robot;
    }

    public Boolean getGogradenikename() {
        return gogradenikename;
    }

    public void setGogradenikename(Boolean gogradenikename) {
        this.gogradenikename = gogradenikename;
    }

    public Boolean getSpamState() {
        return spamState;
    }

    public void setSpamState(Boolean spamState) {
        this.spamState = spamState;
    }

    public Integer getStatementsum() {
        return statementsum;
    }

    public void setStatementsum(Integer statementsum) {
        this.statementsum = statementsum;
    }

    public Integer getMsglength() {
        return msglength;
    }

    public void setMsglength(Integer msglength) {
        this.msglength = msglength;
    }

    public Integer getMsgfrequency() {
        return msgfrequency;
    }

    public void setMsgfrequency(Integer msgfrequency) {
        this.msgfrequency = msgfrequency;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Boolean getCocState() {
        return cocState;
    }

    public void setCocState(Boolean cocState) {
        this.cocState = cocState;
    }

    public String getCocApi() {
        return cocApi;
    }

    public void setCocApi(String cocApi) {
        this.cocApi = cocApi;
    }

    public Boolean getTulingState() {
        return tulingState;
    }

    public void setTulingState(Boolean tulingState) {
        this.tulingState = tulingState;
    }

    public String getTulinApi() {
        return tulinApi;
    }

    public void setTulinApi(String tulinApi) {
        this.tulinApi = tulinApi;
    }

    public Boolean getEmsState() {
        return emsState;
    }

    public void setEmsState(Boolean emsState) {
        this.emsState = emsState;
    }

    public String getEmsApi() {
        return emsApi;
    }

    public void setEmsApi(String emsApi) {
        this.emsApi = emsApi;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }

    public Boolean getTograde() {
        return tograde;
    }

    public void setTograde(Boolean tograde) {
        this.tograde = tograde;
    }

    public Boolean getCocgotograde() {
        return cocgotograde;
    }

    public void setCocgotograde(Boolean cocgotograde) {
        this.cocgotograde = cocgotograde;
    }

    public Boolean getGotoaddinput() {
        return gotoaddinput;
    }

    public void setGotoaddinput(Boolean gotoaddinput) {
        this.gotoaddinput = gotoaddinput;
    }
}
