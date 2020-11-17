package com.yzycoc.cocutil.service.accomplish.yq;

import com.yzycoc.cocutil.util.CocApiAndCqCustom;
import com.yzycoc.cocutil.util.YqHtmlCustom;
import com.yzycoc.config.ConfigParameter;
import com.yzycoc.custom.HttpYqUtils;
import com.yzycoc.custom.TimeUtiles;
import gui.ava.html.Html2Image;

/**
 * @program: cscocutil
 * @description: 合成曲线图
 * @author: yzy
 * @create: 2020-10-27 16:24
 * @Version 1.0
 **/
public class YqCurveImage {
    /**
     * 渲染生成趋势图
     * @param start
     */
    public Boolean Synthesis(long start) {
        try {
            System.out.println("鱼情：合成底部趋势图，正在渲染JS ------->");
            String html = renderJS();
            System.out.println("鱼情：JS渲染完毕，共耗时："+(System.currentTimeMillis() - start)+"ms");
            if(html != null){
                renderImage(html);
                System.out.println("鱼情：图生成完毕，共耗时："+(System.currentTimeMillis() - start)+"ms");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /***
     * 渲染 JS后的得到的代码
     * @return
     */
    public String renderJS() {
        //保存内容到新
        HttpYqUtils httpUtils = HttpYqUtils.getInstance();
        httpUtils.setTimeout(20000);
        httpUtils.setWaitForBackgroundJavaScript(20000);
        try {
            String htmlPageStr = httpUtils.getHtmlPageResponse("file:///"+ ConfigParameter.file_YuQing_HTML);
            int indexOf = htmlPageStr.indexOf("div class=\"lootAvailableDiv\"");
            int indexOf2 = htmlPageStr.indexOf("id=\"yzycoc\"") - 6;
            htmlPageStr = YqHtmlCustom.css+"<"+htmlPageStr.substring(indexOf,indexOf2);
            String html = htmlPageStr.replace("class=\"graphs\"","style=\"font-family:Arial;\"")
                    .replace("class=\"timeScaleHeader\"","style=\"font-size: 12px;padding: 0px;vertical-align: middle;font-variant: small-caps;font-weight: bold;max-width: 55px;\"")
                    .replace("Loot Available", "战<br/>利<br/>品").replace("Players Online", "玩<br/>家<br/>在<br/>线").replace("Shielded Players", "玩<br/>家<br/>离<br/>线")
                    .replace("style=\"height: 100px;\"", "style=\"height: 100px;padding-left: 21px;\"")
                    .replace("style=\"height: 20px;\"", "style=\"height: 20px;padding-left: 23px;\"")
                    .replace("style=\"height: 250px;padding-left:12px;\"", "style=\"height: 250px;padding-left:18px;\"")
                    .replace("style=\"background-color:#EEEEEE;\"", "style=\"background-color:#EEEEEE;FONT-FAMILY: '隶书';FONT-SIZE: 20px;\"")
                    .replace("Time", "")
                    .replace("<div class=\"rotate\">\r\n" +
                            "                                        Loot Index\r\n" +
                            "                                      </div>","")
                    .replace("<div class=\"rotate\">Loot Index</div>","");
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    /***
     * 根据html渲染生成图片
     * @param html
     */
    public void renderImage(String html){
        Html2Image html2Image = Html2Image.fromHtml(html);
        html2Image.getImageRenderer().saveImage(ConfigParameter.file_Yq_qushi_Image);
    }
}
