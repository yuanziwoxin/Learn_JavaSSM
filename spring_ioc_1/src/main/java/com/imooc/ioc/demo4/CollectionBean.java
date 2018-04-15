package com.imooc.ioc.demo4;

import java.util.*;

public class CollectionBean {
    private String[] str;//数组类型

    private List<String> list;//List集合类型

    private Set<String> set;//Set集合类型

    private Map<String,Integer> map;//Map集合

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public Properties getPt() {
        return pt;
    }

    public void setPt(Properties pt) {
        this.pt = pt;
    }

    private Properties pt;//属性类型

    @Override
    public String toString() {
        return "CollectionBean{" +
                "str=" + Arrays.toString(str) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", pt=" + pt +
                '}';
    }
}
