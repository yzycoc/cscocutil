package com.yzycoc.cocutil.SQLAll.bean.vip;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: cscocutil
 * @description: 会员用户
 * @author: yzy
 * @create: 2021-01-21 10:21
 * @Version 1.0
 **/
@Data
public class CsUserVip extends BaseEntity<Integer> {
    @ApiModelProperty(value = "用户QQ号")
    private String qqnumber;
    @ApiModelProperty(value = "拥有的金额")
    private BigDecimal number = new BigDecimal(0);

    @ApiModelProperty(value = "会员总天数")
    private Integer vipMember = 0;

    @ApiModelProperty(value = "会员结束时间")
    private String endTime ;

    @ApiModelProperty(value = "是否永久会员")
    private Boolean eternity = false;

    @ApiModelProperty(value = "可以永久授权的群数量")
    private Integer groupEternity = 0;

    @ApiModelProperty(value = "公众号")
    private String uuid;

    @ApiModelProperty(value = "微信ID")
    private String wxId;
    /***
     * 是否是会员
     * @return
     */
    public Boolean getVipState() {
        if(eternity!=null && eternity){
            return true;
        }
        if(endTime == null){
            return false;
        }else{
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parse = sdf.parse(endTime);
                return !parse.before(new Date());
            }catch (Exception e){
                System.out.println("时间比较出错"+endTime);
            }
        }
        return false;
    }

    /***
     * Vip等级
     * @return
     */
    public Integer getVipGrade() {
        if(eternity!=null&&eternity){
            return 10;
        }
        if(vipMember!=null&&getVipState()){
            if(vipMember >= 558){//18
                return 6;
            } else if (vipMember >= 372) {//12
                return 5;
            }else if (vipMember >= 279) {//9
                return 4;
            }else if (vipMember >= 186) {//6
                return 3;
            }else if (vipMember >= 92) {//3
                return 2;
            }else{
                return 1;
            }
        }
        return 0;
    }

    /***
     * 获取coc绑定
     * @return
     */
    public Integer getBinding() {
        if(eternity!=null&&eternity){
            return 0;
        }
        if(vipMember!=null&&getVipState()){
            if(vipMember >= 558){//18
                return 0;
            } else if (vipMember >= 372) {//12
                return 0;
            }else if (vipMember >= 279) {//9
                return 16;
            }else if (vipMember >= 186) {//6
                return 12;
            }else if (vipMember >= 92) {//3
                return 9;
            }else{
                return 6;
            }
        }
        return 3;
    }

    /***
     * 可以绑定的群聊
     * @return
     */
    public Integer getGroup() {
        if(eternity!=null&&eternity){
            return 10;
        }
        if(vipMember!=null&&getVipState()){
            if(vipMember >= 558){//18
                return 6;
            } else if (vipMember >= 372) {//12
                return 5;
            }else if (vipMember >= 279) {//9
                return 3    ;
            }else if (vipMember >= 186) {//6
                return 3;
            }else if (vipMember >= 92) {//3
                return 3;
            }else{
                return 2;
            }
        }
        return 1;
    }

    /***
     * 可以添加机器人的好友
     * @return
     */
    public Integer getFriend() {
        if(eternity!=null&&eternity){
            return 6;
        }
        if(vipMember!=null&&getVipState()){
            if(vipMember >= 558){//18
                return 4;
            } else if (vipMember >= 372) {//12
                return 3;
            }else if (vipMember >= 279) {//9
                return 2;
            }else if (vipMember >= 186) {//6
                return 2;
            }else if (vipMember >= 92) {//3
                return 1;
            }else{
                return 1;
            }
        }
        return 1;
    }
}
