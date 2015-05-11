package com.aemiot.android.library.tools;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set; 

public class DataCategory<T> {
    
    public DataCategory() {

    }
    
    /*数据集构造*/
    public DataCategory(Collection<T> datas) {
        this.datas = datas;
        notifiyDataChanged();
    }
    
    public DataCategory(Collection<T> datas, Classifiable<T> handler) {
        this.datas = datas;
        notifiyDataChanged(handler);
    }
    
    public DataCategory(T[] datas) {
        this(Arrays.asList(datas));
    }
    
    public DataCategory(T[] datas, Classifiable<T> handler) {
        this(Arrays.asList(datas), handler);
    }
    
    /*设置数据*/
    public void setDatas(Collection<T> datas) {
        this.datas = datas;
        notifiyDataChanged();
    }
    
    public void setDatas(Collection<T> datas, Classifiable<T> handler) {
        this.datas = datas;
        notifiyDataChanged(handler);
    }
    
    public void notifiyDataChanged() {
        if(datas == null) {
            map = null;
            count = 0;
            return ;
        }
        if(map == null) {
            map = new HashMap<String,Set<T>>();
        }
        else {
            map.clear();
        }
        for(T data : datas) {
            @SuppressWarnings("unchecked")
            String category = ((Classifiable<T>)data).getCategory(data);
            Set<T> set = map.get(category);
            if(set == null) {
                set = new HashSet<T>();
                map.put(category, set);
                count++;
            }
            set.add(data);
        }
    }
    
    public void notifiyDataChanged(Classifiable<T> handler) {
        if(datas == null) {
            map = null;
            count = 0;
            return ;
        }
        if(map == null) {
            map = new HashMap<String,Set<T>>();
        }
        else {
            map.clear();
        }
        for(T data : datas) {
            String category = handler.getCategory(data);
            Set<T> set = map.get(category);
            if(set == null) {
                set = new HashSet<T>();
                map.put(category, set);
                count++;
            }
            set.add(data);
        }
    }
    
    /*分类结果*/
    public int getCategoryCount() {
        return count;
    }
    
    public Set<String> getCategorySet() {
        return map.keySet();
    }
    
    public Set<T> getDataSetByCategory(String category) {
        return map.get(category);
    }
    
    public Map<String,Set<T>> getDataMap() {
        return map;
    }
    
    
    private Collection<T> datas = null;
    private Map<String,Set<T>> map = null;
    private int count = 0;
    
    public static void main(String[] args) {
        String ts[] = {"2015/04/01", "2015/04/15", "2012/04/01"};
        DataCategory<String> handler = new DataCategory<String>(ts, new Classifiable<String>(){

            @Override
            public String getCategory(String t) {
                return t.substring(0, 4);
            }
            
        });
        System.out.println(handler.getCategoryCount());
        Set<String> set = handler.getDataSetByCategory("2012");
        if(set != null) {
            for(String t : set) {
                System.out.println(t);
            }
        }
    }
}
