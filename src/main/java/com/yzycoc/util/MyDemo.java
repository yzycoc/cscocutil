package com.yzycoc.util;

import com.yzycoc.util.tableImage.ImageName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yao
 * @create: 2021-03-20 18:47
 * @Version 1.0
 **/
public class MyDemo<T> {

    public void m(List<T> e) throws IllegalAccessException {
        /*String class_name = clazz.getName();
        Field[] tableFields = clazz.getDeclaredFields();
        Class<?> superClazz = clazz.getSuperclass();
        for (Field field : tableFields) {
            field.setAccessible(true);
            System.out.println(class_name + ":" + field.getName());
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println(class_name + ":" + method.getName());
        }*/
        for (int i = 0; i < e.size(); i++) {
            T t = e.get(i);
            Class<?> aClass = t.getClass();
            System.out.println(t.toString());
            if(i == 0){
                Field[] field = aClass.getDeclaredFields();
                if(field != null){
                    for(Field fie : field){
                        if(!fie.isAccessible()){
                            fie.setAccessible(true);
                        }
                        ImageName annotation = fie.getAnnotation(ImageName.class);
                        System.out.println(annotation.value());
                    }
                }
            }
        }
    }

    public <T> List<List<String>> objListToStringList(List<T> list){
        List<List<String>> res=new ArrayList<>();
        for(T element:list){
            Field[] declaredFields = element.getClass().getDeclaredFields();
            List<String> values=new ArrayList<>();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                try {
                    Object obj = field.get(element);
                    values.add(obj.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            res.add(values);
        }
        return res;
    }
}
