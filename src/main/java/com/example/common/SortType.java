package com.example.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 並び替え方法のenum
 *
 * @author krkrHotaru
 */

public enum SortType {
    NAME_ASC(0, " name ASC "),
    PRICE_ASC(1, " price_s ASC "),
    PRICE_DESC(2, " price_s DESC ")
    ;

    private final Integer key;
    private final String value;

    SortType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static SortType of(Integer key){
        for(SortType sortType: SortType.values()){
            if(sortType.key.equals(key)){
                return sortType;
            }
        }
        return null;
    }

    public static Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (SortType sortType: SortType.values()){
            map.put(sortType.key, sortType.value);
        }
        return map;
    }

}
