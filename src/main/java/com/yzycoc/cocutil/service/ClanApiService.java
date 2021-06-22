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
     * @param realTime 是否实时数据
     * @return
     */
     ClanResult getImageClan(String tag,Boolean realTime);

    /***
     * 生成玩家信息
     * @param tag
     * @param realTime 是否实时数据
     * @return
     */
     ClanResult getImagePlayer(String tag,Boolean realTime);

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
     * @param qqcode
     * @return
     */
     ClanResult getImageClanAll(String tag, String qqcode);

    /***
     * 部落配置分析 -- 文本
     * @param tag
     * @param qqcode
     * @return
     */
     ClanResult getImageClanAllCollectText(String tag, String qqcode);


    /***
     * 部落配置分析 -- 图片
     * @param tag
     * @param qqcode
     * @return
     */
     ClanResult getImageClanAllCollectImage(String tag, String qqcode);

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


    ClanResult getImageClanStatistics(String tag,String qqcode,String type);

    ClanResult imageClanStatisticsHtml(String tag, String qqcode, String type);
}
