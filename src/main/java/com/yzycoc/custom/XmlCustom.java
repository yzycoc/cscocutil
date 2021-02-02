package com.yzycoc.custom;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzycoc.custom.result.Xlzdom4jXmlResult;
import org.dom4j.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: cscocutil
 * @description: xml
 * @author: yzy
 * @create: 2021-01-22 09:43
 * @Version 1.0
 **/
public class XmlCustom {


    /***
     * 解析转账list
     * @param xml
     * @return
     */
    public static List<String> xlzpublic(String xml){
        List<String> result = new ArrayList<>();
        try {
            //解析 XML
            Document xmlDocument = DocumentHelper.parseText(xml);
            //获取根路径
            Element rootElement = xmlDocument.getRootElement();
            Attribute brief = rootElement.attribute("brief");
            //获取 名称 测试
            String value = brief.getValue();
            result.add(rootElement.attribute("url").getValue());
            if(value.contains("转账")){
                List<Element> element = rootElement.elements();
                for (Element el : element) {
                    List<Element> elements2 = el.elements();
                    for (Element el2 : elements2) {
                        if(!StringUtils.isEmpty(el2.getText())){
                            result.add(el2.getText());
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * String 转 org.dom4j.Document
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document strToDocument(String xml){
        try {
            //加上xml标签是为了获取最外层的标签，如果不需要可以去掉
            return DocumentHelper.parseText("<xml>"+xml+"</xml>");
        } catch (DocumentException e) {
            return null;
        }
    }

    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject documentToJSONObject(String xml){
        return elementToJSONObject(strToDocument(xml).getRootElement());
    }

    /**
     * org.dom4j.Element 转  com.alibaba.fastjson.JSONObject
     * @param node
     * @return
     */
    public static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
        for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();// 所有一级子节点的list
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {// 遍历所有一级子节点
                if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
                    result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
                else {
                    if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
                        result.put(e.getName(), new JSONArray());// 没有则创建
                    ((JSONArray) result.get(e.getName())).add(elementToJSONObject(e));// 将该一级节点放入该节点名称的属性对应的值中
                }
            }
        }
        return result;
    }

    public static Xlzdom4jXmlResult lzdom4jXmlResult(String xml) {
        Xlzdom4jXmlResult result = new Xlzdom4jXmlResult();
        List<String> xlzpublic = XmlCustom.xlzpublic(xml);
        if(xlzpublic.size() == 8){
            for (int i = 0; i < xlzpublic.size(); i++) {
                if(i==0){
                    result.setHttpurl(xlzpublic.get(i));
                }else if(i == 1){
                    result.setStatus(xlzpublic.get(i));
                }else if(i == 2){
                    result.setDate(xlzpublic.get(i));
                }else if(i == 3){
                    result.setVal(xlzpublic.get(i));
                }else if(i == 4){
                    result.setTime(xlzpublic.get(i));
                }else if(i == 5){
                    result.setUuid(xlzpublic.get(i));
                }else if(i == 6){
                    result.setMoney(xlzpublic.get(i));
                }else if(i == 7){
                    result.setRemark(xlzpublic.get(i));
                }
            }
        }else{
            if(xml.contains("转账留言")){
                String xmlend = xml.substring(xml.indexOf("color=\"#000000\">转账留言："), xml.length());
                String xmlend2 = xmlend.substring(xmlend.indexOf(">"), xmlend.indexOf("<"));
                result.setUuid(xmlend2);
            }
            try {
                if(xml.contains("钱包余额")){
                    String xmlend = xml.substring(xml.indexOf("color=\"#000000\">"), xml.length());
                    String xmlend2 = xmlend.substring(xmlend.indexOf(">"), xmlend.indexOf("<"));
                    result.setVal(xmlend2);
                }
            }catch (Exception e){
                System.out.println("解析失败"+xml);
            }
        }
        return result;
    }
}
