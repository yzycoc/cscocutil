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
    public ClanResult getImageClan(String tag);

    /***
     * 生成玩家信息
     * @param tag
     * @return
     */
    public ClanResult getImagePlayer(String tag);

    /***
     * 生成鱼情信息
     * @return
     */
    public ClanResult getImageYq();

    /***
     * 生成部落配置 -- 大本营
     * @param tag
     * @return
     */
    public ClanResult getImageClanAll(String tag);

    /***
     * 部落配置分析 -- 文本
     * @param tag
     * @return
     */
    public ClanResult getImageClanAllCollectText(String tag);


    /***
     * 部落配置分析 -- 图片
     * @param tag
     * @return
     */
    public ClanResult getImageClanAllCollectImage(String tag);

    /***
     * 查询部落名
     * @param Name
     * @return
     */
    public ClanResult getNameClan(String Name);
}
