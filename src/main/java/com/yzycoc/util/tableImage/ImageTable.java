package com.yzycoc.util.tableImage;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-22 20:31
 * @Version 1.0
 **/
public class ImageTable<T> {

    public <T> BufferedImage table(List<T> tableList) throws Exception {
        List<List<String>> result =new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (int i = 0; i < tableList.size(); i++) {
            Field[] field = tableList.get(i).getClass().getDeclaredFields();
            if(field != null && field.length > 0){
                List<String> data = new ArrayList<>();
                for(Field fie : field){
                    if(!fie.isAccessible()){
                        if(!fie.isAccessible()){
                            fie.setAccessible(true);
                        }
                        if(fie.isAnnotationPresent(ImageName.class)){
                            if(i == 0){
                                ImageName annotation = fie.getAnnotation(ImageName.class);
                                String value = annotation.value();
                                title.add(value);
                            }
                            Object obj = fie.get(tableList.get(i));
                            String addVal = "/";
                            if(obj!=null){
                                addVal = obj.toString();
                                if(addVal.length() > 1000){
                                    addVal = "--文字过长--";
                                }
                            }
                            data.add(addVal);
                        }
                    }
                }
                if(i == 0){
                    result.add(title);
                }
                result.add(data);
            }else{
                throw new NullPointerException("类为空，无法找到field类");
            }
        }
        return new ImageUtil().tableIamge(result);
    }

}
