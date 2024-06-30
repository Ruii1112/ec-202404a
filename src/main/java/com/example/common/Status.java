package com.example.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 注文状況のenum.
 *
 * @author rui.inoue
 */
public enum Status {
    BEFORE_ORDER(0, "注文前"),
    NOT_PAYMENT(1, "未入金"),
    DEPOSIT(2, "入金済"),
    SHIPPED(3, "発送済"),
    DELIVERY_COMPLETED(4, "発送完了"),
    CANCEL(9, "キャンセル"),
    ;

    private final int key;
    private final String value;

    Status(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static Status of(Integer key){
        for(Status status: Status.values()){
            if(status.key == key){
                return status;
            }
        }
        throw new IndexOutOfBoundsException("The value of Status does not exists");
    }

    public static Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (Status status: Status.values()){
            map.put(status.key, status.value);
        }
        return map;
    }
}
