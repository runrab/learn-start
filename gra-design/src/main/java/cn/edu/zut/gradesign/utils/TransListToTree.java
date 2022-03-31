package cn.edu.zut.gradesign.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author o
 */
public class TransListToTree {
    /**
     * 把list转换成树结构
     * @param arr 数据集合
     * @param id 集合中的id的字段名
     * @param pid 集合中的父id的字段名
     * @param child 要添加到孩子节点的节点名称
     * @return 树结构组成的集合
     */
    public static List<Map> listToTree(List<Map> arr, String id, String pid, String child){
        List<Map> r = new ArrayList<>();
        Map hash = new HashMap();
        //将数组转为Object的形式，key为数组中的id
        for(int i=0;i<arr.size();i++){
            Map json =  arr.get(i);
            hash.put(json.get(id), json);
        }
        //遍历结果集
        for(int j=0;j<arr.size();j++){
            //单条记录
            Map aVal =  arr.get(j);
            //在hash中取出key为单条记录中pid的值
            Map hashVP =  (Map) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(hashVP!=null){
                //检查是否有child属性
                if(hashVP.get(child)!=null){
                    List<Map> ch = (List) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }else{
                    List<Map> ch = new ArrayList<>();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            }else{
                r.add(aVal);
            }
        }
        return r;
    }
}
