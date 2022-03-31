package cn.edu.zut.gradesign.utils;

import cn.edu.zut.gradesign.bean.user.Clazz;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ismech
 * 脱敏工具类
 * 移除或者添加部分字段
 * map list
 */
public  class HideUtils {

    //list 对其中Object中的字段进行脱敏
    //Object 转map  基于 hutool

    public static Map objToMap(Object obj){
        Map<String, Object> map = BeanUtil.beanToMap(obj);
        return map;
    }


    public static List removeObject(List<Map> lst,String[] keys){
        for (Map map1:lst){
            for (String key:keys){
                if (map1.get(key).toString().length()>4){
                    map1.put(key,map1.get(key).toString().substring(0,-5)+"****");
                }else{
                    map1.put(key,"****");
                }
            }
        }
        return lst;
    }
    //获取List<Object> 的 Object的指定字段value

    public static List oneValueINList(List<Clazz> list, String key){
        List lst = new LinkedList();
        for (Object obj:list){
            Map<String, Object> map = BeanUtil.beanToMap(obj);
            lst.add(map.get(key));
        }
       return lst;
    }
}
