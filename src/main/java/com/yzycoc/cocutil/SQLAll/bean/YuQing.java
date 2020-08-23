package com.yzycoc.cocutil.SQLAll.bean;

import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2020-08-22 12:18
 * @Version 1.0
 **/
@Data
@TableName("xjpublic.yuqing")
public class YuQing {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String pf;
    private String wordZlp;
    private String wordBh;
    private String wordZx;
    private String wordZxBh;
    private String wordLx;
    private String wordLxBH;
    private String wordHd;
    private String wordHdBh;
    private String wordKgj;
    private String wordKgjBh;
    private String cnZlp;
    private String cnBh;
    private String cnZx;
    private String cnZxbh;
    private String cnLx;
    private String cnLXBh;
    private String cnYhd;
    private String cnYhdBh;
    private String cnKgj;
    private String cnKgjBh;
    private String ts1;
    private String ts2;
    private String ts3;
    private String time;
    private String jy;
    private String yj;//意见建议
    private String html;
    private String htmltime;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getHtml() {
        return html;
    }
    public void setHtml(String html) {
        this.html = html;
    }
    public String getHtmltime() {
        return htmltime;
    }
    public void setHtmltime(String htmltime) {
        this.htmltime = htmltime;
    }
    /***
     * 意见建议
     * @return
     */
    public String getYj() {
        return yj;
    }
    /***
     * 意见建议
     * @param yj
     */
    public void setYj(String yj) {
        this.yj = yj;
    }
    /***
     *
     * @return 中国可进攻趋势
     */
    public String getCnKgjBh() {
        return cnKgjBh;
    }
    /***
     *
     * @param cnKgjBh 中国可进攻趋势
     */
    public void setCnKgjBh(String cnKgjBh) {
        this.cnKgjBh = cnKgjBh;
    }

    /**
     * @return pf 当前鱼情评分
     */
    public String getPf() {
        return pf;
    }

    /**
     * @param pf 当前鱼情评分
     */
    public void setPf(String pf) {
        pf = txtIsNull(pf);
        this.pf = pf;
    }

    /**
     * @return wordZlp 每分钟有效的战利品數
     */
    public String getWordZlp() {
        return wordZlp;
    }

    /**
     * @param wordZlp 每分钟有效的战利品數
     */
    public void setWordZlp(String wordZlp) {
        wordZlp = txtIsNull(wordZlp);
        this.wordZlp = wordZlp;
    }

    /**
     * @return wordBh 此刻的净变化:
     */
    public String getWordBh() {
        return wordBh;
    }

    /**
     * @param wordBh 此刻的净变化:
     */
    public void setWordBh(String wordBh) {
        wordBh = txtIsNull(wordBh);
        this.wordBh = wordBh;
    }

    /**
     * @return wordZx 在线
     */
    public String getWordZx() {
        return wordZx;
    }

    /**
     * @param wordZx 在线
     */
    public void setWordZx(String wordZx) {
        wordZx = txtIsNull(wordZx);
        this.wordZx = wordZx;
    }

    /**
     * @return wordZxBh 在线变化趋势
     */
    public String getWordZxBh() {
        return wordZxBh;
    }

    /**
     * @param wordZxBh 在线变化趋势
     */
    public void setWordZxBh(String wordZxBh) {
        wordZxBh = txtIsNull(wordZxBh);
        this.wordZxBh = wordZxBh;
    }

    /**
     * @return wordLx 离线
     */
    public String getWordLx() {
        return wordLx;
    }

    /**
     * @param wordLx 离线
     */
    public void setWordLx(String wordLx) {
        wordLx = txtIsNull(wordLx);
        this.wordLx = wordLx;
    }

    /**
     * @return wordLxBH 离线变化趋势
     */
    public String getWordLxBH() {
        return wordLxBH;
    }

    /**
     * @param wordLxBH 离线变化趋势
     */
    public void setWordLxBH(String wordLxBH) {
        wordLxBH = txtIsNull(wordLxBH);
        this.wordLxBH = wordLxBH;
    }

    /**
     * @return wordHd 有护盾
     */
    public String getWordHd() {
        return wordHd;
    }

    /**
     * @param wordHd 有护盾
     */
    public void setWordHd(String wordHd) {
        wordHd = txtIsNull(wordHd);
        this.wordHd = wordHd;
    }

    /**
     * @return wordHdBh 有护盾变化趋势
     */
    public String getWordHdBh() {
        return wordHdBh;
    }

    /**
     * @param wordHdBh 有护盾变化趋势
     */
    public void setWordHdBh(String wordHdBh) {
        wordHdBh = txtIsNull(wordHdBh);
        this.wordHdBh = wordHdBh;
    }

    /**
     * @return wordKgj 可攻击
     */
    public String getWordKgj() {
        return wordKgj;
    }

    /**
     * @param wordKgj 可攻击
     */
    public void setWordKgj(String wordKgj) {
        wordKgj = txtIsNull(wordKgj);
        this.wordKgj = wordKgj;
    }

    /**
     * @return wordKgjBh 可攻击变化趋势
     */
    public String getWordKgjBh() {
        return wordKgjBh;
    }

    /**
     * @param wordKgjBh 可攻击变化趋势
     */
    public void setWordKgjBh(String wordKgjBh) {
        wordKgjBh = txtIsNull(wordKgjBh);
        this.wordKgjBh = wordKgjBh;
    }

    /**
     * @return cnZlp 每分钟变化趋势
     */
    public String getCnZlp() {
        return cnZlp;
    }

    /**
     * @param cnZlp 每分钟变化趋势
     */
    public void setCnZlp(String cnZlp) {
        cnZlp = txtIsNull(cnZlp);
        this.cnZlp = cnZlp;
    }

    /**
     * @return cnBh 每分钟战利品变化趋势
     */
    public String getCnBh() {
        return cnBh;
    }

    /**
     * @param cnBh 每分钟战利品变化趋势
     */
    public void setCnBh(String cnBh) {
        cnBh = txtIsNull(cnBh);
        this.cnBh = cnBh;
    }

    /**
     * @return cnZx 中国在线
     */
    public String getCnZx() {
        return cnZx;
    }

    /**
     * @param cnZx 中国在线
     */
    public void setCnZx(String cnZx) {
        cnZx = txtIsNull(cnZx);
        this.cnZx = cnZx;
    }

    /**
     * @return cnZxbh 中国玩家在线变化趋势
     */
    public String getCnZxbh() {
        return cnZxbh;
    }

    /**
     * @param cnZxbh 中国玩家在线变化趋势
     */
    public void setCnZxbh(String cnZxbh) {
        cnZxbh = txtIsNull(cnZxbh);
        this.cnZxbh = cnZxbh;
    }

    /**
     * @return cnLx 中国离线
     */
    public String getCnLx() {
        return cnLx;
    }

    /**
     * @param cnLx 中国离线
     */
    public void setCnLx(String cnLx) {
        cnLx = txtIsNull(cnLx);
        this.cnLx = cnLx;
    }

    /**
     * @return cnLXBh 中国离线玩家变化
     */
    public String getCnLXBh() {
        return cnLXBh;
    }

    /**
     * @param cnLXBh 中国离线玩家变化
     */
    public void setCnLXBh(String cnLXBh) {
        cnLXBh = txtIsNull(cnLXBh);
        this.cnLXBh = cnLXBh;
    }

    /**
     * @return cnYhd 中国有护盾
     */
    public String getCnYhd() {
        return cnYhd;
    }

    /**
     * @param cnYhd 中国有护盾
     */
    public void setCnYhd(String cnYhd) {
        cnYhd = txtIsNull(cnYhd);
        this.cnYhd = cnYhd;
    }

    /**
     * @return cnYhdBh 中国有护盾变化趋势
     */
    public String getCnYhdBh() {
        return cnYhdBh;
    }

    /**
     * @param cnYhdBh 中国有护盾变化趋势
     */
    public void setCnYhdBh(String cnYhdBh) {
        cnYhdBh = txtIsNull(cnYhdBh);
        this.cnYhdBh = cnYhdBh;
    }

    /**
     * @return cnKgj 中国可攻击人数
     */
    public String getCnKgj() {
        return cnKgj;
    }

    /**
     * @param cnKgj 中国可攻击人数
     */
    public void setCnKgj(String cnKgj) {
        cnKgj = txtIsNull(cnKgj);
        this.cnKgj = cnKgj;
    }

    /**
     * @return ts1 当前状态提示
     */
    public String getTs1() {
        return ts1;
    }

    /**
     * @param ts1 当前状态提示
     */
    public void setTs1(String ts1) {
        ts1 = txtIsNull(ts1);
        this.ts1 = ts1;
    }

    /**
     * @return ts2 提示状态1
     */
    public String getTs2() {
        return ts2;
    }

    /**
     * @param ts2 提示状态1
     */
    public void setTs2(String ts2) {
        ts2 = txtIsNull(ts2);
        this.ts2 = ts2;
    }

    /**
     * @return ts3 提示状态2
     */
    public String getTs3() {
        return ts3;
    }

    /**
     * @param ts3 提示状态2
     */
    public void setTs3(String ts3) {
        ts3 = txtIsNull(ts3);
        this.ts3 = ts3;
    }

    /**
     * @return time 获取时间
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time 获取时间
     */
    public void setTime(String time) {
        time = txtIsNull(time);
        this.time = time;
    }

    /**
     * @return jy 打鱼的建议
     */
    public String getJy() {
        return jy;
    }

    /**
     * @param jy 打鱼的建议
     */
    public void setJy(String jy) {
        jy = txtIsNull(jy);
        this.jy = jy;
    }



    @Override
    public String toString() {
        return "YuQing [pf=" + pf + ", wordZlp=" + wordZlp + ", wordBh=" + wordBh + ", wordZx=" + wordZx + ", wordZxBh="
                + wordZxBh + ", wordLx=" + wordLx + ", wordLxBH=" + wordLxBH + ", wordHd=" + wordHd + ", wordHdBh="
                + wordHdBh + ", wordKgj=" + wordKgj + ", wordKgjBh=" + wordKgjBh + ", cnZlp=" + cnZlp + ", cnBh=" + cnBh
                + ", cnZx=" + cnZx + ", cnZxbh=" + cnZxbh + ", cnLx=" + cnLx + ", cnLXBh=" + cnLXBh + ", cnYhd=" + cnYhd
                + ", cnYhdBh=" + cnYhdBh + ", cnKgj=" + cnKgj + ", cnKgjBh=" + cnKgjBh + ", ts1=" + ts1 + ", ts2=" + ts2
                + ", ts3=" + ts3 + ", time=" + time + ", jy=" + jy + "]";
    }
    private String txtIsNull(String date) {
        return date == null ? "-" : date;
    }
    /***
     *
     * @param date
     * @return
     */
    private String txtIsBfb(String date) {
        try {
            if(!StringUtils.isEmpty(date)) {
                int parseInt = Integer.parseInt(date);
                Integer sum = Integer.parseInt(this.wordZx)+Integer.parseInt(this.wordLx);
                return "("+String.format("%.1f", (float) parseInt / (float) sum * 100)+"%)";
            }
        } catch (Exception e) {
        }
        return date == null ? "-" : date;
    }

    /**
     * @return wordZxBfb 在线百分比
     */
    public String wgetWordZxBfb(){
        return txtIsBfb(wordZx);
    }



    /**
     * @return wordLxBfb 离线百分比
     */
    public String wgetWordLxBfb(){
        return txtIsBfb(wordLx);
    }



    /**
     * @return wordKgjBfb 可攻击百分比
     */
    public String wgetWordKgjBfb(){
        return txtIsBfb(wordKgj);
    }



    /**
     * @return wordHdBfb 有护盾百分比
     */
    public String wgetWordHdBfb(){
        return txtIsBfb(wordHd);
    }



    /**
     * @return cnZxBfb 中国在线玩家百分比
     */
    public String wgetCnZxBfb(){
        return txtIsBfb(cnZx);
    }



    /**
     * @return cnLxBfb 中国离线玩家百分比
     */
    public String wgetCnLxBfb(){
        return txtIsBfb(cnLx);
    }



    /**
     * @return cnYhdBfb 中国有护盾百分比
     */
    public String wgetCnYhdBfb(){
        return txtIsBfb(cnYhd);
    }



    /**
     * @return cnKgjBfb 中国可攻击人数百分比
     */
    public String wgetCnKgjBfb(){
        return txtIsBfb(cnKgj);
    }
}
