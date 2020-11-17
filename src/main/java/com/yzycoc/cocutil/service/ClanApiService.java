package com.yzycoc.cocutil.service;


import com.yzycoc.cocutil.service.result.ClanResult;

/**
 * @program: cscocutil
 * @description: Coc工具汇总
 * @author: yzy
 * @create: 2020-08-10 20:40
 * @Version 1.0
 **/
 public interface ClanApiService {
    /***
     * 生成部落图片
     * @param tag 部落标签
     * @return
     */
     ClanResult getImageClan(String tag);

    /***
     * 生成玩家信息
     * @param tag
     * @return
     */
     ClanResult getImagePlayer(String tag);

    /***
     * 生成鱼情信息
     * @return
     */
     ClanResult getImageYq();

    /****
     *
     */
    Boolean updateYq();

    /***
     * 生成部落配置 -- 大本营
     * @param tag
     * @return
     */
     ClanResult getImageClanAll(String tag);

    /***
     * 部落配置分析 -- 文本
     * @param tag
     * @return
     */
     ClanResult getImageClanAllCollectText(String tag);


    /***
     * 部落配置分析 -- 图片
     * @param tag
     * @return
     */
     ClanResult getImageClanAllCollectImage(String tag);

    /***
     * 查询部落名
     * @param Name
     * @return
     */
     ClanResult getNameClan(String Name[]);

    /***
     * 查询玩家名
     * @param Name
     * @return
     */
     ClanResult getNamePlayer(String Name[]);

    /***
     * get获取部落
     * @param tag
     * @return
     */
    ClanResult getClan(String tag);

    /***
     * get  获取玩家
     * @param tag
     * @return
     */
    ClanResult getPlayer(String tag);


}
